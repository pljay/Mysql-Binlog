/*  1:   */ package cn.wizool.android.servlet.attendencepackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import java.io.IOException;
/*  8:   */ import java.io.PrintStream;
/*  9:   */ import java.sql.Connection;
/* 10:   */ import java.sql.PreparedStatement;
/* 11:   */ import java.sql.ResultSet;
/* 12:   */ import java.sql.SQLException;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.LinkedHashMap;
/* 15:   */ import java.util.List;
/* 16:   */ import java.util.Map;
/* 17:   */ import javax.servlet.ServletException;
/* 18:   */ import javax.servlet.http.HttpServlet;
/* 19:   */ import javax.servlet.http.HttpServletRequest;
/* 20:   */ import javax.servlet.http.HttpServletResponse;
/* 21:   */ 
/* 22:   */ public class AttendanceClassInfoServlet
/* 23:   */   extends HttpServlet
/* 24:   */ {
	/* 25:   */   private static final long serialVersionUID = 1L;
	/* 26:   */   
	/* 27:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/* 28:   */     throws ServletException, IOException
	/* 29:   */   {
		/* 30:35 */     String id = request.getParameter("schoolid");
		/* 31:36 */     System.out.println(id);
		/* 32:37 */     String sql = "SELECT\r\n\ta.ID,\r\n\tb.ITEM_CLASSNUMBER,\r\n\ta.ITEM_TIMENAME AS pattern_name,\r\n\ta.ITEM_PID AS classid,\r\n\ta.ITEM_TIMEID AS pattern,\r\n\ta.ITEM_TYPE,\r\n\tb.ITEM_SCHOOLID\r\nFROM\r\n\t`tlk_人员考勤时间设置` AS a\r\nLEFT JOIN `tlk_班级基础信息` AS b ON b.id = a.ITEM_PID\r\nWHERE\r\n\ta.ITEM_TYPE = '班级'\r\nAND b.ITEM_SCHOOLID = '" + 
				/* 33:   */     
				/* 34:   */ 
				/* 35:   */ 
				/* 36:   */ 
				/* 37:   */ 
				/* 38:   */ 
				/* 39:   */ 
				/* 40:   */ 
				/* 41:   */ 
				/* 42:   */ 
				/* 43:   */ 
				/* 44:   */ 
				/* 45:50 */       id + "'";
		/* 46:51 */     Connection connection = Mysql.getInstance().getConnection();
		/* 47:   */     try
		/* 48:   */     {
			/* 49:53 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
			/* 50:54 */       List<Map<String, String>> list = new ArrayList();
			/* 51:55 */       ResultSet resultSet = prepareStatement.executeQuery();
			/* 52:56 */       while (resultSet.next())
			/* 53:   */       {
				/* 54:57 */         Map<String, String> map = new LinkedHashMap();
				/* 55:58 */         map.put("id", resultSet.getString("id"));
				/* 56:59 */         map.put("class_number", resultSet.getString("ITEM_CLASSNUMBER"));
				/* 57:60 */         map.put("pattern_name", resultSet.getString("pattern_name"));
				/* 58:61 */         map.put("classid", resultSet.getString("classid"));
				/* 59:62 */         map.put("pattern", resultSet.getString("pattern"));
				/* 60:63 */         map.put("type", resultSet.getString("ITEM_TYPE"));
				/* 61:64 */         map.put("schoolid", resultSet.getString("ITEM_SCHOOLID"));
				/* 62:65 */         list.add(map);
			/* 63:   */       }
			/* 64:67 */       ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
		/* 65:   */     }
		/* 66:   */     catch (SQLException e)
		/* 67:   */     {
			/* 68:70 */       e.printStackTrace();
		}
		/* 78:   */     finally
		/* 79:   */     {
			/* 80:   */       try
			/* 81:   */       {
				/* 82:73 */         connection.close();
			/* 83:   */       }
			/* 84:   */       catch (SQLException e)
			/* 85:   */       {
				/* 86:76 */         e.printStackTrace();
			/* 87:   */       }
		/* 88:   */     }
	/* 89:   */   }
/* 90:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceClassInfoServlet
 * JD-Core Version:    0.7.0.1
 */