package or.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import or.CDCEvent;
import or.InstanceListener;
import or.MysqlConnection;
import or.OpenReplicatorPlus;
import or.manager.CDCEventManager;
import or.model.BinlogMasterStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OpenReplicatorTest {
	private static final Logger logger = LoggerFactory.getLogger(OpenReplicatorTest.class);
	private static final String host = "localhost";
	private static final int port = 3307;
	private static final String user = "root";
	private static final String password = "poiuyt";

	public static void main(String[] args){

		//java.util.regex.Pattern.compile( "[^0-9]").matcher("").replaceAll("");
		System.out.println(System.getProperty("file.encoding"));
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		OpenReplicatorPlus or = new OpenReplicatorPlus();
		or.setUser(user);
		or.setPassword(password);
		or.setHost(host);
		or.setPort(port);
		MysqlConnection.setConnection(host, port, user, password);

		or.setServerId(2);
		//配置里的serverId是open-replicator(作为一个slave)的id,不是master的serverId

		BinlogMasterStatus bms = MysqlConnection.getBinlogMasterStatus();
		or.setBinlogFileName(bms.getBinlogName());
		// or.setBinlogFileName("mysql-bin.000010");
		or.setBinlogPosition(4);
		or.setBinlogEventListener(new InstanceListener());
		try {
			logger.info("程序启动了");
			or.start();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		Thread thread = new Thread(new PrintCDCEvent());
		thread.start();
	}

	public static class PrintCDCEvent implements Runnable{
		@Override
		public void run() {
			while(true){
				Gson gson = new GsonBuilder().serializeNulls().create();
				if(CDCEventManager.queue.isEmpty() == false)
				{
					Connection connection = MysqlConnection.setConnection("root", "poiuyt");
					CDCEvent ce = CDCEventManager.queue.pollFirst();
					String  before= gson.toJson(ce.getBefore());
					String  after= gson.toJson(ce.getAfter());
					logger.info(after);
					if ((!ce.getTableName().split("_")[0].equals("auth")) && (ce.getIsDdl().equals(Boolean.valueOf(false))) && (!ce.getTableName().equals("tlk_安卓设备管理")) && (!ce.getTableName().equals("t_设备管理")) && (!ce.getTableName().equals("binlog")) && (!ce.getTableName().equals("attendance")))
					{
						if (gson.toJson(ce.getBefore()).contains("\"["))
						{
							before = before.replace("\"[", "[").replace("]\"", "]");
							after = after.replace("\"[", "[").replace("]\"", "]");
						}
						String sql = "  INSERT INTO `xiaoxuan`.`binlog` (\r\n  `eventid`,\r\n  `databasename` ,\r\n  `tablename` ,\r\n  `eventtype` ,\r\n  `timestamp` ,\r\n  `timestampreceipt` ,\r\n  `binlogname` ,\r\n  `position` ,\r\n  `nextpostion` ,\r\n  `serverid`,\r\n  `before` ,\r\n  `after` ,\r\n  `isddl` ,\r\n  `sql`)\r\nVALUES\r\n\t(\r\n\t\t'" +
								ce.getEventId() + "',\r\n" + 
								"\t\t'" + ce.getDatabaseName() + "',\r\n" + 
								"\t\t'" + ce.getTableName() + "',\r\n" + 
								"\t\t'" + ce.getEventType() + "',\r\n" + 
								"\t\t'" + ce.getTimestamp() + "',\r\n" + 
								"\t\t'" + ce.getTimestampReceipt() + "',\r\n" + 
								"\t\t'" + ce.getBinlogName() + "',\r\n" + 
								"\t\t'" + ce.getPosition() + "',\r\n" + 
								"\t\t'" + ce.getNextPostion() + "',  \r\n" + 
								"\t\t'" + ce.getServerId() + "',\r\n" + 
								"\t\t'" + before + "' ,\r\n" + 
								"\t\t'" + after + "',\r\n" + 
								"\t\t'" + ce.getIsDdl() + "',\r\n" + 
								"\t\t'" + ce.getSql() + "'\r\n" + 
								")ON DUPLICATE KEY UPDATE `eventid`=VALUES(`eventid`),`databasename`=VALUES(`databasename`),\r\n" + 
								"`tablename`=VALUES(`tablename`),`eventtype`=VALUES(`eventtype`),`timestamp`=VALUES(`timestamp`),\r\n" + 
								"`timestampreceipt`=VALUES(`timestampreceipt`),`binlogname`=VALUES(`binlogname`),`position`=VALUES(`position`),\r\n" + 
								"`nextpostion`=VALUES(`nextpostion`),`serverid`=VALUES(`serverid`),`before`=VALUES(`before`),\r\n" + 
								"`after`=VALUES(`after`),`isddl`=VALUES(`isddl`),`sql`=VALUES(`sql`)";

						try {
							PreparedStatement prepareStatement = connection.prepareStatement(sql);
							prepareStatement.executeUpdate();
							logger.info("xiaoxuan.binlog  :  插入一条数据");
							prepareStatement.close();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}/*finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
					}*/
						/*  Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                    String string=ce.toString();
                    System.out.println(gson.toJson(ce));*/
						/*ObjectMapper mapper=new ObjectMapper();
                    String string = null;
					try {
						string = mapper.writeValueAsString(ce);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					}
				}
				else{
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}       
	}
}