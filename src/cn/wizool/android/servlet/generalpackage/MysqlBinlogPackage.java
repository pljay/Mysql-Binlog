/*  1:   */ package cn.wizool.android.servlet.generalpackage;
import cn.wizool.android.JDBC.Mysql;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.or.CDCEvent;
import cn.wizool.android.or.MysqlConnection;
/*  5:   */ import cn.wizool.android.or.manager.CDCEventManager;
/*  6:   */ import com.google.gson.Gson;
/*  7:   */ import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
/* 11:   */ import java.sql.SQLException;
/* 13:   */ import java.util.concurrent.TimeUnit;
/* 14:   */ import org.slf4j.Logger;
/* 15:   */ import org.slf4j.LoggerFactory;
/* 16:   */ 
/* 17:   */ public class MysqlBinlogPackage
/* 18:   */   implements Runnable
/* 19:   */ {
	/* 20:19 */   private static Logger logger = LoggerFactory.getLogger(MysqlBinlogPackage.class);
	/* 21:   */   
	public void run()
	/* 23:   */   {
		/* 24:   */     for (;;)
		/* 25:   */     {
			/* 26:25 */       if (!CDCEventManager.queue.isEmpty())
			/* 27:   */       {
				/* 28:27 */        	Connection connection = MysqlConnection.setConnection("root", "poiuyt");
									PreparedStatement prepareStatement=null;
				/* 29:28 */         CDCEvent ce = (CDCEvent)CDCEventManager.queue.pollFirst();
				/* 30:29 */         Gson gson = new GsonBuilder().serializeNulls().create();
				/* 31:32 */         if ((!ce.getTableName().split("_")[0].equals("auth")) && (ce.getIsDdl().equals(Boolean.valueOf(false))) && (!ce.getTableName().equals("tlk_安卓设备管理")) && (!ce.getTableName().equals("t_设备管理")) && (!ce.getTableName().equals("binlog")) && (!ce.getTableName().equals("attendance")))
				/* 32:   */         {
					/* 33:33 */           System.out.println(gson.toJson(ce));
					/* 34:34 */           System.out.println(ce.getTableName().split("_")[0].equals("auth"));
					/* 35:35 */           String after = gson.toJson(ce.getBefore());
					/* 36:36 */           String before = gson.toJson(ce.getAfter());
					logger.info(after);
					/* 37:37 */           if (gson.toJson(ce.getBefore()).contains("\"["))
					/* 38:   */           {
						/* 39:38 */             before = before.replace("\"[", "[").replace("]\"", "]");
						/* 40:39 */             after = after.replace("\"[", "[").replace("]\"", "]");
					/* 41:   */           }
					/* 42:41 */           String sql = "  INSERT INTO `xiaoxuan`.`binlog` (\r\n  `eventid`,\r\n  `databasename` ,\r\n  `tablename` ,\r\n  `eventtype` ,\r\n  `timestamp` ,\r\n  `timestampreceipt` ,\r\n  `binlogname` ,\r\n  `position` ,\r\n  `nextpostion` ,\r\n  `serverid`,\r\n  `before` ,\r\n  `after` ,\r\n  `isddl` ,\r\n  `sql`)\r\nVALUES\r\n\t(\r\n\t\t'" +
							/* 59:58 */             ce.getEventId() + "',\r\n" + 
							/* 60:59 */             "\t\t'" + ce.getDatabaseName() + "',\r\n" + 
							/* 61:60 */             "\t\t'" + ce.getTableName() + "',\r\n" + 
							/* 62:61 */             "\t\t'" + ce.getEventType() + "',\r\n" + 
							/* 63:62 */             "\t\t'" + ce.getTimestamp() + "',\r\n" + 
							/* 64:63 */             "\t\t'" + ce.getTimestampReceipt() + "',\r\n" + 
							/* 65:64 */             "\t\t'" + ce.getBinlogName() + "',\r\n" + 
							/* 66:65 */             "\t\t'" + ce.getPosition() + "',\r\n" + 
							/* 67:66 */             "\t\t'" + ce.getNextPostion() + "',  \r\n" + 
							/* 68:67 */             "\t\t'" + ce.getServerId() + "',\r\n" + 
							/* 69:68 */             "\t\t'" + before + "' ,\r\n" + 
							/* 70:69 */             "\t\t'" + after + "',\r\n" + 
							/* 71:70 */             "\t\t'" + ce.getIsDdl() + "',\r\n" + 
							/* 72:71 */             "\t\t'" + ce.getSql() + "'\r\n" + 
							/* 73:72 */             ")ON DUPLICATE KEY UPDATE `eventid`=VALUES(`eventid`),`databasename`=VALUES(`databasename`),\r\n" + 
							/* 74:73 */             "`tablename`=VALUES(`tablename`),`eventtype`=VALUES(`eventtype`),`timestamp`=VALUES(`timestamp`),\r\n" + 
							/* 75:74 */             "`timestampreceipt`=VALUES(`timestampreceipt`),`binlogname`=VALUES(`binlogname`),`position`=VALUES(`position`),\r\n" + 
							/* 76:75 */             "`nextpostion`=VALUES(`nextpostion`),`serverid`=VALUES(`serverid`),`before`=VALUES(`before`),\r\n" + 
							/* 77:76 */             "`after`=VALUES(`after`),`isddl`=VALUES(`isddl`),`sql`=VALUES(`sql`)";
					/* 78:   */           try
					/* 79:   */           {
						/* 80:78 */            prepareStatement = connection.prepareStatement(sql);
						/* 81:79 */            prepareStatement.executeUpdate();
					/* 83:   */           }
					/* 84:   */           catch (SQLException e)
					/* 85:   */           {
						/* 86:84 */             e.printStackTrace();
					/* 87:   */           }finally {
						try {
							prepareStatement.close();
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				/* 88:   */         }
			/* 89:   */       }
			/* 90:   */       else
			/* 91:   */       {
				/* 92:   */         try
				/* 93:   */         {
					/* 94:92 */           TimeUnit.SECONDS.sleep(1L);
				/* 95:   */         }
				/* 96:   */         catch (InterruptedException e)
				/* 97:   */         {
					/* 98:94 */           e.printStackTrace();
				/* 99:   */         }
			/* :0:   */       }
		/* :1:   */     }
	/* :2:   */   }
/* :3:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.MysqlBinlogPackage
 * JD-Core Version:    0.7.0.1
 */