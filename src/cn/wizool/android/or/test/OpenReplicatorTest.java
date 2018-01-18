/*   1:    */ package cn.wizool.android.or.test;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.com.google.code.or.OpenReplicator;
/*   4:    */ import cn.wizool.android.or.CDCEvent;
/*   5:    */ import cn.wizool.android.or.InstanceListener;
/*   6:    */ import cn.wizool.android.or.MysqlConnection;
/*   7:    */ import cn.wizool.android.or.manager.CDCEventManager;
/*   8:    */ import cn.wizool.android.or.model.BinlogMasterStatus;
/*   9:    */ import com.google.gson.Gson;
/*  10:    */ import com.google.gson.GsonBuilder;
/*  11:    */ import java.io.PrintStream;
/*  12:    */ import java.sql.Connection;
/*  13:    */ import java.sql.PreparedStatement;
/*  14:    */ import java.sql.SQLException;
/*  15:    */ import java.util.TimeZone;
/*  16:    */ import java.util.concurrent.ConcurrentLinkedDeque;
/*  17:    */ import java.util.concurrent.TimeUnit;
/*  18:    */ import org.slf4j.Logger;
/*  19:    */ import org.slf4j.LoggerFactory;
/*  20:    */ 
/*  21:    */ public class OpenReplicatorTest
/*  22:    */ {
/*  23: 27 */   private static final Logger logger = LoggerFactory.getLogger(OpenReplicatorTest.class);
/*  24:    */   private static final String host = "localhost";
/*  25:    */   private static final int port = 3307;
/*  26:    */   private static final String user = "root";
/*  27:    */   private static final String password = "poiuyt";
/*  28:    */   
/*  29:    */   public static void main(String[] args)
/*  30:    */   {
/*  31: 37 */     TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
/*  32: 38 */     OpenReplicator or = new OpenReplicator();
/*  33: 39 */     or.setUser("root");
/*  34: 40 */     or.setPassword("poiuyt");
/*  35: 41 */     or.setHost("localhost");
/*  36: 42 */     or.setPort(3307);
/*  37: 43 */     MysqlConnection.setConnection("localhost", 3307, "root", "poiuyt");
/*  38:    */     
/*  39: 45 */     or.setServerId(2);
/*  40:    */     
/*  41:    */ 
/*  42: 48 */     BinlogMasterStatus bms = MysqlConnection.getBinlogMasterStatus();
/*  43: 49 */     or.setBinlogFileName(bms.getBinlogName());
/*  44:    */     
/*  45: 51 */     or.setBinlogPosition(4L);
/*  46: 52 */     or.setBinlogEventListener(new InstanceListener());
/*  47:    */     try
/*  48:    */     {
/*  49: 54 */       logger.info("程序启动了");
/*  50: 55 */       or.start();
/*  51:    */     }
/*  52:    */     catch (Exception e)
/*  53:    */     {
/*  54: 57 */       logger.error(e.getMessage(), e);
/*  55:    */     }
/*  56: 60 */     Thread thread = new Thread(new PrintCDCEvent());
/*  57: 61 */     thread.start();
/*  58:    */   }
/*  59:    */   
/*  60:    */   public static class PrintCDCEvent
/*  61:    */     implements Runnable
/*  62:    */   {
/*  63:    */     public void run()
/*  64:    */     {
/*  65:    */       for (;;)
/*  66:    */       {
/*  67: 69 */         if (!CDCEventManager.queue.isEmpty())
/*  68:    */         {
/*  69: 71 */           Connection connection = MysqlConnection.setConnection("root", "poiuyt");
/*  70: 72 */           CDCEvent ce = (CDCEvent)CDCEventManager.queue.pollFirst();
/*  71: 73 */           System.out.println(ce.getTableName().split("_")[0].equals("auth"));
/*  72: 74 */           if ((!ce.getTableName().split("_")[0].equals("auth")) && (ce.getIsDdl().equals(Boolean.valueOf(false))) && (!ce.getTableName().equals("t_设备管理")) && (!ce.getTableName().equals("binlog")) && (!ce.getTableName().equals("attendance")))
/*  73:    */           {
/*  74: 75 */             String sql = "  INSERT INTO `xiaoxuan`.`binlog` (\r\n  `eventid`,\r\n  `databasename` ,\r\n  `tablename` ,\r\n  `eventtype` ,\r\n  `timestamp` ,\r\n  `timestampreceipt` ,\r\n  `binlogname` ,\r\n  `position` ,\r\n  `nextpostion` ,\r\n  `serverid`,\r\n  `before` ,\r\n  `after` ,\r\n  `isddl` ,\r\n  `sql`)\r\nVALUES\r\n\t(\r\n\t\t'" + 
/*  75:    */             
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91: 92 */               ce.getEventId() + "',\r\n" + 
/*  92: 93 */               "\t\t'" + ce.getDatabaseName() + "',\r\n" + 
/*  93: 94 */               "\t\t'" + ce.getTableName() + "',\r\n" + 
/*  94: 95 */               "\t\t'" + ce.getEventType() + "',\r\n" + 
/*  95: 96 */               "\t\t'" + ce.getTimestamp() + "',\r\n" + 
/*  96: 97 */               "\t\t'" + ce.getTimestampReceipt() + "',\r\n" + 
/*  97: 98 */               "\t\t'" + ce.getBinlogName() + "',\r\n" + 
/*  98: 99 */               "\t\t'" + ce.getPosition() + "',\r\n" + 
/*  99:100 */               "\t\t'" + ce.getNextPostion() + "',  \r\n" + 
/* 100:101 */               "\t\t'" + ce.getServerId() + "',\r\n" + 
/* 101:102 */               "\t\t'" + ce.getBefore() + "' ,\r\n" + 
/* 102:103 */               "\t\t'" + ce.getAfter() + "',\r\n" + 
/* 103:104 */               "\t\t'" + ce.getIsDdl() + "',\r\n" + 
/* 104:105 */               "\t\t'" + ce.getSql() + "'\r\n" + 
/* 105:106 */               ")ON DUPLICATE KEY UPDATE `eventid`=VALUES(`eventid`),`databasename`=VALUES(`databasename`),\r\n" + 
/* 106:107 */               "`tablename`=VALUES(`tablename`),`eventtype`=VALUES(`eventtype`),`timestamp`=VALUES(`timestamp`),\r\n" + 
/* 107:108 */               "`timestampreceipt`=VALUES(`timestampreceipt`),`binlogname`=VALUES(`binlogname`),`position`=VALUES(`position`),\r\n" + 
/* 108:109 */               "`nextpostion`=VALUES(`nextpostion`),`serverid`=VALUES(`serverid`),`before`=VALUES(`before`),\r\n" + 
/* 109:110 */               "`after`=VALUES(`after`),`isddl`=VALUES(`isddl`),`sql`=VALUES(`sql`)";
/* 110:111 */             System.out.println("ce.getTimestampReceipt()" + ce.getTimestampReceipt());
/* 111:    */             try
/* 112:    */             {
/* 113:114 */               PreparedStatement prepareStatement = connection.prepareStatement(sql);
/* 114:115 */               prepareStatement.executeUpdate();
/* 115:116 */               prepareStatement.close();
/* 116:117 */               connection.close();
/* 117:    */             }
/* 118:    */             catch (SQLException e)
/* 119:    */             {
/* 120:120 */               e.printStackTrace();
/* 121:    */             }
/* 122:122 */             Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
/* 123:123 */             String string = ce.toString();
/* 124:124 */             System.out.println(gson.toJson(ce));
/* 125:    */             
/* 126:    */ 
/* 127:    */ 
/* 128:    */ 
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:    */ 
/* 133:133 */             OpenReplicatorTest.logger.debug(string);
/* 134:    */           }
/* 135:    */         }
/* 136:    */         else
/* 137:    */         {
/* 138:    */           try
/* 139:    */           {
/* 140:138 */             TimeUnit.SECONDS.sleep(1L);
/* 141:    */           }
/* 142:    */           catch (InterruptedException e)
/* 143:    */           {
/* 144:140 */             e.printStackTrace();
/* 145:    */           }
/* 146:    */         }
/* 147:    */       }
/* 148:    */     }
/* 149:    */   }
/* 150:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.test.OpenReplicatorTest
 * JD-Core Version:    0.7.0.1
 */