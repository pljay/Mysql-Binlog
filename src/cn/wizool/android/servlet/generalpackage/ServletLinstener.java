/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.or.InstanceListener;
/*  5:   */ import cn.wizool.android.or.MysqlConnection;
/*  6:   */ import cn.wizool.android.or.OpenReplicatorPlus;
/*  7:   */ import cn.wizool.android.or.model.BinlogMasterStatus;
/*  8:   */ import java.io.PrintStream;
/*  9:   */ import java.util.Timer;
/* 10:   */ import javax.servlet.ServletContextEvent;
/* 11:   */ import javax.servlet.ServletContextListener;
/* 12:   */ import org.slf4j.Logger;
/* 13:   */ import org.slf4j.LoggerFactory;
/* 14:   */ 
/* 15:   */ public class ServletLinstener
/* 16:   */   implements ServletContextListener
/* 17:   */ {
/* 18:25 */   private Logger logger = LoggerFactory.getLogger(ServletLinstener.class);
/* 19:26 */   HeartbeatPackage task = new HeartbeatPackage();
/* 20:27 */   Thread thread = new Thread(new MysqlBinlogPackage());
/* 21:   */   
/* 22:   */   public void contextDestroyed(ServletContextEvent arg0)
/* 23:   */   {
/* 24:31 */     this.task.cancel();
/* 25:32 */     this.thread.interrupt();
/* 26:33 */     System.out.println("心线程已经停止");
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void contextInitialized(ServletContextEvent arg0)
/* 30:   */   {
/* 31:41 */     Mysql.getInstance();
/* 32:42 */     OpenReplicatorPlus or = new OpenReplicatorPlus();
/* 33:43 */     or.setUser(Mysql.user);
/* 34:44 */     or.setPassword(Mysql.pass);
/* 35:45 */     or.setHost(Mysql.ip);
/* 36:46 */     or.setPort(Mysql.port);
/* 37:47 */     MysqlConnection.setConnection(Mysql.ip, Mysql.port, Mysql.user, Mysql.pass);
/* 38:48 */     this.logger.info(Mysql.ip);
/* 39:49 */     or.setServerId(3);
/* 40:   */     
/* 41:51 */     BinlogMasterStatus bms = MysqlConnection.getBinlogMasterStatus();
/* 42:52 */     or.setBinlogFileName(bms.getBinlogName());
/* 43:   */     
/* 44:54 */     or.setBinlogPosition(4);
/* 45:55 */     or.setBinlogEventListener(new InstanceListener());
/* 46:   */     try
/* 47:   */     {
/* 48:57 */       this.logger.info("程序启动了");
/* 49:58 */       or.start();
/* 50:   */     }
/* 51:   */     catch (Exception e)
/* 52:   */     {
/* 53:60 */       this.logger.error(e.getMessage(), e);
/* 54:   */     }
/* 55:62 */     new Timer().schedule(this.task, 2000, 180000);
/* 56:63 */     this.logger.info("心跳线程已经启动");
/* 57:64 */     System.out.println("心跳线程已经启动");
/* 58:65 */     this.thread.start();
/* 59:66 */     this.logger.info("slave已经启动");
/* 60:   */   }
/* 61:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.ServletLinstener
 * JD-Core Version:    0.7.0.1
 */