/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  5:   */ import java.sql.Connection;
/*  6:   */ import java.sql.PreparedStatement;
/*  7:   */ import java.sql.SQLException;
/*  8:   */ import java.util.TimerTask;
/*  9:   */ 
/* 10:   */ public class HeartbeatPackage
/* 11:   */   extends TimerTask
/* 12:   */ {
	/* 13:   */   public void run()
	/* 14:   */   {
		/* 15:20 */     String sql = "UPDATE `tlk_安卓设备管理` AS a\r\nSET a.item_status = '已断开'\r\nWHERE\r\n\t(\r\n\t\tSELECT\r\n\t\t\tTIMESTAMPDIFF(\r\n\t\t\t\tMINUTE,\r\n\t\t\t\ta.item_lastconnecttime,\r\n\t\t\t\tCURRENT_TIMESTAMP\r\n\t\t\t) >3\r\n\t)";
		/* 26:31 */     System.out.println(sql);
		/* 27:32 */     Connection connection = Mysql.getInstance().getConnection();
		/* 28:   */     try
		/* 29:   */     {
			/* 30:34 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
			/* 31:35 */       prepareStatement.executeUpdate();
			/* 32:36 */       prepareStatement.close();
		/* 33:   */     }
		/* 34:   */     catch (SQLException e)
		/* 35:   */     {
			/* 36:39 */       e.printStackTrace();
		}
		/* 46:   */     finally
		/* 47:   */     {
			/* 48:   */       try
			/* 49:   */       {
				/* 50:43 */         connection.close();
			/* 51:   */       }
			/* 52:   */       catch (SQLException e)
			/* 53:   */       {
				/* 54:46 */         e.printStackTrace();
			/* 55:   */       }
		/* 56:   */     }
	/* 57:   */   }
	/* 58:   */   
	/* 59:   */   public boolean cancel()
	/* 60:   */   {
		/* 61:55 */     return super.cancel();
	/* 62:   */   }
	/* 63:   */   
	/* 64:   */   public long scheduledExecutionTime()
	/* 65:   */   {
		/* 66:61 */     return super.scheduledExecutionTime();
	/* 67:   */   }
	/* 68:   */   
	/* 69:   */   public int hashCode()
	/* 70:   */   {
		/* 71:67 */     return super.hashCode();
	/* 72:   */   }
	/* 73:   */   
	/* 74:   */   public boolean equals(Object obj)
	/* 75:   */   {
		/* 76:73 */     return super.equals(obj);
	/* 77:   */   }
	/* 78:   */   
	/* 79:   */   protected Object clone()
			/* 80:   */     throws CloneNotSupportedException
	/* 81:   */   {
		/* 82:79 */     return super.clone();
	/* 83:   */   }
	/* 84:   */   
	/* 85:   */   public String toString()
	/* 86:   */   {
		/* 87:85 */     return super.toString();
	/* 88:   */   }
	/* 89:   */   
	/* 90:   */   protected void finalize()
			/* 91:   */     throws Throwable
	/* 92:   */   {
		/* 93:91 */     super.finalize();
	/* 94:   */   }
/* 95:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.HeartbeatPackage
 * JD-Core Version:    0.7.0.1
 */