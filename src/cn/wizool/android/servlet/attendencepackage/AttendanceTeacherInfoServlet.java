/*   1:    */ package cn.wizool.android.servlet.attendencepackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.utils.JsonUtils;
/*   5:    */ import cn.wizool.android.utils.ResponseUtils;
/*   6:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.sql.Connection;
/*  10:    */ import java.sql.PreparedStatement;
/*  11:    */ import java.sql.ResultSet;
/*  12:    */ import java.sql.SQLException;
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.LinkedHashMap;
/*  15:    */ import java.util.List;
/*  16:    */ import java.util.Map;
/*  17:    */ import javax.servlet.ServletException;
/*  18:    */ import javax.servlet.http.HttpServlet;
/*  19:    */ import javax.servlet.http.HttpServletRequest;
/*  20:    */ import javax.servlet.http.HttpServletResponse;
/*  21:    */ 
/*  22:    */ public class AttendanceTeacherInfoServlet
/*  23:    */   extends HttpServlet
/*  24:    */ {
	/*  25:    */   private static final long serialVersionUID = 1L;
	/*  26:    */   
	/*  27:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/*  28:    */     throws ServletException, IOException
	/*  29:    */   {
		/*  30: 37 */     String id = request.getParameter("schoolid");
		/*  31: 38 */     System.out.println(id);
		/*  32: 39 */     Connection connection = Mysql.getInstance().getConnection();
		/*  33: 40 */     String sql = "SELECT\r\n\ta.ID,\r\n\ta.ITEM_NAME,\r\n\ta.ITEM_TIMENAME AS pattern_name,\r\n\ta.ITEM_PID AS teacherid,\r\n\ta.ITEM_TIMEID AS pattern,\r\n\ta.ITEM_TYPE,\r\n\tb.ITEM_SEX,\r\n\tb.ITEM_PHONE,\r\n\tb.ITEM_EMAIL,\r\n\tb.ITEM_TITLE,\r\n\tb.ITEM_JIDID,\r\n\tb.ITEM_TEACHERID,\r\n\tb.ITEM_SCHOOLID,\r\n\tb.ITEM_TEACHERPATH,\r\n\tc.cardid,\r\n\tc.type,\r\n\tc.state\r\nFROM\r\n\t`tlk_人员考勤时间设置` AS a\r\nLEFT JOIN `tlk_教师基础信息` AS b ON a.ITEM_PID = b.ID\r\nLEFT JOIN card AS c ON c.teacherid = a.ITEM_PID\r\nWHERE\r\n\ta.ITEM_TYPE = '教师'AND b.ITEM_SCHOOLID='" + 
				/*  34:    */     
				/*  35:    */ 
				/*  36:    */ 
				/*  37:    */ 
				/*  38:    */ 
				/*  39:    */ 
				/*  40:    */ 
				/*  41:    */ 
				/*  42:    */ 
				/*  43:    */ 
				/*  44:    */ 
				/*  45:    */ 
				/*  46:    */ 
				/*  47:    */ 
				/*  48:    */ 
				/*  49:    */ 
				/*  50:    */ 
				/*  51:    */ 
				/*  52:    */ 
				/*  53:    */ 
				/*  54:    */ 
				/*  55:    */ 
				/*  56: 63 */       id + "'";
		/*  57:    */     try
		/*  58:    */     {
			/*  59: 65 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
			/*  60: 66 */       ResultSet resultSet = prepareStatement.executeQuery();
			/*  61: 67 */       List<Map<String, String>> list = new ArrayList();
			/*  62: 69 */       while (resultSet.next())
			/*  63:    */       {
				/*  64: 70 */         Map<String, String> map = new LinkedHashMap();
				/*  65: 71 */         map.put("id", resultSet.getString("ID"));
				/*  66: 72 */         map.put("name", resultSet.getString("ITEM_NAME"));
				/*  67: 73 */         map.put("pattern_name", resultSet.getString("pattern_name"));
				/*  68: 74 */         System.out.println(resultSet.getString("pattern_name"));
				/*  69: 75 */         map.put("teacherid", resultSet.getString("teacherid"));
				/*  70: 76 */         map.put("pattern", resultSet.getString("pattern"));
				/*  71: 77 */         map.put("types", resultSet.getString("ITEM_TYPE"));
				/*  72:    */         
				/*  73:    */ 
				/*  74:    */ 
				/*  75:    */ 
				/*  76:    */ 
				/*  77: 83 */         map.put("teacherid", resultSet.getString("ITEM_TEACHERID"));
				/*  78: 84 */         map.put("schoolid", resultSet.getString("ITEM_SCHOOLID"));
				/*  79:    */         
				/*  80: 86 */         map.put("cardid", resultSet.getString("cardid"));
				/*  81: 87 */         map.put("type", resultSet.getString("type"));
				/*  82:    */         
				/*  83: 89 */         list.add(map);
			/*  84:    */       }
			/*  85: 91 */       prepareStatement.close();
			/*  86: 92 */       resultSet.close();
			/*  87: 93 */       ResponseUtils.render(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
		/*  88:    */     }
		/*  89:    */     catch (SQLException e)
		/*  90:    */     {
			/*  91: 96 */       e.printStackTrace();
	/* 100:    */     }
	/* 101:    */     finally
	/* 102:    */     {
		/* 103:    */       try
		/* 104:    */       {
			/* 105: 99 */         connection.close();
		/* 106:    */       }
		/* 107:    */       catch (SQLException e)
		/* 108:    */       {
			/* 109:102 */         e.printStackTrace();
		/* 110:    */       }
	/* 111:    */     }
/* 112:    */   }
/* 113:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceTeacherInfoServlet
 * JD-Core Version:    0.7.0.1
 */