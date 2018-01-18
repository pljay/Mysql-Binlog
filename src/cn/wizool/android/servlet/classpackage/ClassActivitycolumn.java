/*   1:    */ package cn.wizool.android.servlet.classpackage;
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
/*  13:    */ import java.text.SimpleDateFormat;
/*  14:    */ import java.util.ArrayList;
/*  15:    */ import java.util.LinkedHashMap;
/*  16:    */ import java.util.List;
/*  17:    */ import java.util.Map;
/*  18:    */ import javax.servlet.ServletException;
/*  19:    */ import javax.servlet.http.HttpServlet;
/*  20:    */ import javax.servlet.http.HttpServletRequest;
/*  21:    */ import javax.servlet.http.HttpServletResponse;
/*  22:    */ 
/*  23:    */ public class ClassActivitycolumn
/*  24:    */   extends HttpServlet
/*  25:    */ {
	/*  26:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/*  27:    */     throws ServletException, IOException
	/*  28:    */   {
		/*  29: 37 */     String code = request.getParameter("classid");
		/*  30: 38 */     String date = request.getParameter("date");
		/*  31: 39 */     String sql = null;
		/*  32: 40 */     System.out.println(code);
		/*  33: 41 */     Connection conn = Mysql.getInstance().getConnection();
		/*  34: 42 */     String str2 = request.getServerName();
		/*  35: 43 */     int port = request.getServerPort();
		/*  36: 44 */     String sss = request.getScheme();
		/*  37: 45 */     String contextPath = request.getContextPath();
		/*  38: 46 */     List<Map<String, String>> list = new ArrayList();
		/*  39: 47 */     if (date == null) {
			/*  40: 48 */       sql = 
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
					/*  54: 62 */         "SELECT\r\n\tITEM_TITLE,\r\n\tITEM_PICTURE,\r\n\tITEM_STARTTIME,\r\n\tITEM_ENDTIME,\r\n\tITEM_PLACE,\r\n\tITEM_MATTER,\r\n\tITEM_STATE\r\nFROM\r\n\t`tlk_教室管理` AS a\r\n   LEFT JOIN `tlk_班级基础信息` AS b ON a.ITEM_NUMBER = b.ITEM_CLASSROOMIDLEFT JOIN\t`tlk_班级活动` AS c ON b.ID = c.ITEM_CLASSID\r\nWHERE\r\n\tITEM_CLASSID = '" + code + "'\r\n" + "order by ITEM_STARTTIME desc";
		/*  55:    */     } else {
			/*  56: 65 */       sql = 
					/*  57:    */       
					/*  58:    */ 
					/*  59:    */ 
					/*  60:    */ 
					/*  61:    */ 
					/*  62:    */ 
					/*  63:    */ 
					/*  64:    */ 
					/*  65:    */ 
					/*  66:    */ 
					/*  67:    */ 
					/*  68:    */ 
					/*  69: 78 */         "SELECT\r\n\tITEM_TITLE,\r\n\tITEM_PICTURE,\r\n\tITEM_STARTTIME,\r\n\tITEM_ENDTIME,\r\n\tITEM_PLACE,\r\n\tITEM_MATTER,\r\n\tITEM_STATE\r\nFROM\r\n\t`tlk_班级活动`\r\nWHERE\r\n\tITEM_CLASSID = '" + code + "'" + "and('" + date + "'>=ITEM_STARTTIME AND '" + date + "'<=ITEM_ENDTIME )\r\n" + "order by ITEM_STARTTIME desc";
		/*  70:    */     }
		/*  71:    */     try
		/*  72:    */     {
			/*  73: 82 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
			/*  74: 83 */       ResultSet resultSet = prepareStatement.executeQuery();
			/*  75: 84 */       while (resultSet.next())
			/*  76:    */       {
				/*  77: 85 */         Map<String, String> mapp = new LinkedHashMap();
				/*  78: 86 */         String path = null;
				/*  79: 87 */         if ((resultSet.getString("ITEM_PICTURE") != null) && (resultSet.getString("ITEM_PICTURE").length() > 0))
				/*  80:    */         {
					/*  81: 88 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_PICTURE").substring(1, resultSet.getString("ITEM_PICTURE").length() - 1), Map.class);
					/*  82: 89 */           path = (String)mapp.get("path");
					/*  83: 90 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
				/*  84:    */         }
				/*  85: 92 */         Map<String, String> classhonorcolumnmap = new LinkedHashMap();
				/*  86: 93 */         classhonorcolumnmap.put("title", resultSet.getString("ITEM_TITLE"));
				/*  87: 94 */         classhonorcolumnmap.put("start_time", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_STARTTIME")));
				/*  88: 95 */         classhonorcolumnmap.put("end_time", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_ENDTIME")));
				/*  89: 96 */         classhonorcolumnmap.put("place", resultSet.getString("ITEM_PLACE"));
				/*  90: 97 */         classhonorcolumnmap.put("matter", resultSet.getString("ITEM_MATTER"));
				/*  91: 98 */         classhonorcolumnmap.put("picture_path", path);
				/*  92: 99 */         list.add(classhonorcolumnmap);
			/*  93:    */       }
			/*  94:101 */       resultSet.close();
			/*  95:102 */       prepareStatement.close();
			/*  96:103 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
		/*  97:    */     }
		/*  98:    */     catch (SQLException e)
		/*  99:    */     {
			/* 100:106 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/* 101:107 */       e.printStackTrace();
			/* 102:    */      
		/* 110:    */     }
		/* 111:    */     finally
		/* 112:    */     {
			/* 113:    */       try
			/* 114:    */       {
				/* 115:110 */         conn.close();
			/* 116:    */       }
			/* 117:    */       catch (SQLException e)
			/* 118:    */       {
				/* 119:113 */         e.printStackTrace();
			/* 120:    */       }
		/* 121:    */     }
	/* 122:    */   }
/* 123:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassActivitycolumn
 * JD-Core Version:    0.7.0.1
 */