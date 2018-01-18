/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import java.io.IOException;
/*  8:   */ import java.io.PrintStream;
/*  9:   */ import java.sql.Connection;
/* 10:   */ import java.sql.PreparedStatement;
/* 11:   */ import java.sql.SQLException;
/* 12:   */ import javax.servlet.ServletException;
/* 13:   */ import javax.servlet.http.HttpServlet;
/* 14:   */ import javax.servlet.http.HttpServletRequest;
/* 15:   */ import javax.servlet.http.HttpServletResponse;
/* 16:   */ 
/* 17:   */ public class HeartbeatPackageServlet
/* 18:   */   extends HttpServlet
/* 19:   */ {
	/* 20:   */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
			/* 21:   */     throws ServletException, IOException
	/* 22:   */   {
		/* 23:31 */     String parameter = request.getParameter("equipmentid");
		/* 24:32 */     Connection connection = Mysql.getInstance().getConnection();
		/* 25:33 */     String sql = "UPDATE `tlk_安卓设备管理` AS a\r\nSET a.item_lastconnecttime = CURRENT_TIMESTAMP\r\nWHERE\r\na.item_equipmentid='" + 
				/* 26:   */     
				/* 27:   */ 
				/* 28:36 */       parameter + "'";
		/* 29:37 */     System.out.println(sql);
		/* 30:   */     try
		/* 31:   */     {
			/* 32:39 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
			/* 33:40 */       prepareStatement.executeUpdate();
			/* 34:41 */       prepareStatement.close();
			/* 35:42 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok()));
		/* 36:   */     }
		/* 37:   */     catch (SQLException e)
		/* 38:   */     {
			/* 39:45 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/* 40:46 */       e.printStackTrace();
		/* 49:   */     }
		/* 50:   */     finally
		/* 51:   */     {
			/* 52:   */       try
			/* 53:   */       {
				/* 54:49 */         connection.close();
			/* 55:   */       }
			/* 56:   */       catch (SQLException e)
			/* 57:   */       {
				/* 58:52 */         e.printStackTrace();
			/* 59:   */       }
		/* 60:   */     }
	/* 61:   */   }
/* 62:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.HeartbeatPackageServlet
 * JD-Core Version:    0.7.0.1
 */