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
/*  23:    */ public class ClassHonorcolumn
/*  24:    */   extends HttpServlet
/*  25:    */ {
	/*  26:    */   private static final long serialVersionUID = 1L;
	/*  27:    */   
	/*  28:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/*  29:    */     throws ServletException, IOException
	/*  30:    */   {
		/*  31: 39 */     String code = request.getParameter("classid");
		/*  32: 40 */     String date = request.getParameter("date");
		/*  33: 41 */     System.out.println(code);
		/*  34: 42 */     String sql = null;
		/*  35: 43 */     Connection conn = Mysql.getInstance().getConnection();
		/*  36: 44 */     String str2 = request.getServerName();
		/*  37: 45 */     int port = request.getServerPort();
		/*  38: 46 */     String sss = request.getScheme();
		/*  39: 47 */     String contextPath = request.getContextPath();
		/*  40: 48 */     List<Map<String, String>> list = new ArrayList();
		/*  41: 49 */     if (date == null)
		/*  42:    */     {
			/*  43: 50 */       sql = 
					/*  44:    */       
					/*  45:    */ 
					/*  46:    */ 
					/*  47:    */ 
					/*  48:    */ 
					/*  49:    */ 
					/*  50:    */ 
					/*  51: 58 */         "SELECT\r\n\tITEM_HONOR,\r\n\tITEM_TITLE,\r\n\tITEM_WINNINGDATE\r\nFROM\r\n\t`tlk_班级荣誉`\r\nWHERE\r\n\tITEM_CLASSID = '" + code + "'" + "order by ITEM_WINNINGDATE desc";
		/*  52:    */     }
		/*  53:    */     else
		/*  54:    */     {
			/*  55: 60 */       sql = 
					/*  56:    */       
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
					/*  68: 73 */         "SELECT\r\n\tITEM_HONOR,\r\n\tITEM_TITLE,\r\n\tITEM_WINNINGDATE\r\nFROM\r\n\t`tlk_班级荣誉`\r\nWHERE\r\n\tITEM_CLASSID = '" + code + "'\r\n" + "AND (\r\n" + "\tITEM_WINNINGDATE<= DATE_add(\r\n" + "\t\t'" + date + "',\r\n" + "\t\tINTERVAL 15 DAY)\r\n" + ")" + "order by ITEM_WINNINGDATE desc";
			/*  69: 74 */       System.out.println(sql);
		/*  70:    */     }
		/*  71:    */     try
		/*  72:    */     {
			/*  73: 77 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
			/*  74: 78 */       ResultSet resultSet = prepareStatement.executeQuery();
			/*  75: 79 */       while (resultSet.next())
			/*  76:    */       {
				/*  77: 80 */         Map<String, String> mapp = new LinkedHashMap();
				/*  78: 81 */         String path = null;
				/*  79: 82 */         if ((resultSet.getString("ITEM_HONOR") != null) && (resultSet.getString("ITEM_HONOR").length() > 10))
				/*  80:    */         {
					/*  81: 83 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_HONOR").substring(1, resultSet.getString("ITEM_HONOR").length() - 1), Map.class);
					/*  82: 84 */           path = (String)mapp.get("path");
					/*  83: 85 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
				/*  84:    */         }
				/*  85: 87 */         Map<String, String> classhonorcolumnmap = new LinkedHashMap();
				/*  86: 88 */         classhonorcolumnmap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_WINNINGDATE")));
				/*  87: 89 */         classhonorcolumnmap.put("title", resultSet.getString("ITEM_TITLE"));
				/*  88: 90 */         classhonorcolumnmap.put("image_path", path);
				/*  89: 91 */         list.add(classhonorcolumnmap);
			/*  90:    */       }
			/*  91: 93 */       resultSet.close();
			/*  92: 94 */       prepareStatement.close();
			/*  93: 95 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "访问成功", list)));
		/*  94:    */     }
		/*  95:    */     catch (SQLException e)
		/*  96:    */     {
			/*  97: 98 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/*  98: 99 */       e.printStackTrace();
		/* 107:    */     }
		/* 108:    */     finally
		/* 109:    */     {
			/* 110:    */       try
			/* 111:    */       {
				/* 112:102 */         conn.close();
			/* 113:    */       }
			/* 114:    */       catch (SQLException e)
			/* 115:    */       {
				/* 116:105 */         e.printStackTrace();
			/* 117:    */       }
		/* 118:    */     }
	/* 119:    */   }
/* 120:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassHonorcolumn
 * JD-Core Version:    0.7.0.1
 */