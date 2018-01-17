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
            	
                if(CDCEventManager.queue.isEmpty() == false)
                {
                	Connection connection = MysqlConnection.setConnection("root", "poiuyt");
                    CDCEvent ce = CDCEventManager.queue.pollFirst();
                    System.out.println(ce.getTableName().split("_")[0].equals("auth"));
                    if(!ce.getTableName().split("_")[0].equals("auth")&&ce.getIsDdl().equals(false)&&!ce.getTableName().equals("t_设备管理")&&!ce.getTableName().equals("binlog")&&!ce.getTableName().equals("attendance")) {
                    String sql="  INSERT INTO `xiaoxuan`.`binlog` (\r\n" + 
                    		"  `eventid`,\r\n" + 
                    		"  `databasename` ,\r\n" + 
                    		"  `tablename` ,\r\n" + 
                    		"  `eventtype` ,\r\n" + 
                    		"  `timestamp` ,\r\n" + 
                    		"  `timestampreceipt` ,\r\n" + 
                    		"  `binlogname` ,\r\n" + 
                    		"  `position` ,\r\n" + 
                    		"  `nextpostion` ,\r\n" + 
                    		"  `serverid`,\r\n" + 
                    		"  `before` ,\r\n" + 
                    		"  `after` ,\r\n" + 
                    		"  `isddl` ,\r\n" + 
                    		"  `sql`)\r\n" + 
                    		"VALUES\r\n" + 
                    		"	(\r\n" + 
                    		"		'"+ce.getEventId()+"',\r\n" + 
                    		"		'"+ce.getDatabaseName()+"',\r\n" + 
                    		"		'"+ce.getTableName()+"',\r\n" + 
                    		"		'"+ce.getEventType()+"',\r\n" + 
                    		"		'"+ce.getTimestamp()+"',\r\n" + 
                    		"		'"+ce.getTimestampReceipt()+"',\r\n" + 
                    		"		'"+ce.getBinlogName()+"',\r\n" + 
                    		"		'"+ce.getPosition()+"',\r\n" + 
                    		"		'"+ce.getNextPostion()+"',  \r\n" + 
                    		"		'"+ce.getServerId()+"',\r\n" + 
                    		"		'"+ce.getBefore()+"' ,\r\n" + 
                    		"		'"+ce.getAfter()+"',\r\n" + 
                    		"		'"+ce.getIsDdl()+"',\r\n" + 
                    		"		'"+ce.getSql()+"'\r\n" + 
                    		")ON DUPLICATE KEY UPDATE `eventid`=VALUES(`eventid`),`databasename`=VALUES(`databasename`),\r\n" + 
                    		"`tablename`=VALUES(`tablename`),`eventtype`=VALUES(`eventtype`),`timestamp`=VALUES(`timestamp`),\r\n" + 
                    		"`timestampreceipt`=VALUES(`timestampreceipt`),`binlogname`=VALUES(`binlogname`),`position`=VALUES(`position`),\r\n" + 
                    		"`nextpostion`=VALUES(`nextpostion`),`serverid`=VALUES(`serverid`),`before`=VALUES(`before`),\r\n" + 
                    		"`after`=VALUES(`after`),`isddl`=VALUES(`isddl`),`sql`=VALUES(`sql`)";
                    System.out.println("ce.getTimestampReceipt()"+ce.getTimestampReceipt());
                   
                    try {
                    	 PreparedStatement prepareStatement = connection.prepareStatement(sql);
                         prepareStatement.executeUpdate();
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
                     Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                    String string=ce.toString();
                    System.out.println(gson.toJson(ce));
                    /*ObjectMapper mapper=new ObjectMapper();
                    String string = null;
					try {
						string = mapper.writeValueAsString(ce);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
                    logger.debug(string);
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