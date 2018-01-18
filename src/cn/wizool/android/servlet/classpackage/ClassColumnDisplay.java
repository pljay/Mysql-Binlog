/*  1:   */ package cn.wizool.android.servlet.classpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import java.io.IOException;
/*  8:   */ import java.io.PrintStream;
/*  9:   */ import java.net.URLDecoder;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import java.sql.PreparedStatement;
/* 12:   */ import java.sql.ResultSet;
/* 13:   */ import java.sql.SQLException;
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.LinkedHashMap;
/* 16:   */ import java.util.List;
/* 17:   */ import java.util.Map;
/* 18:   */ import javax.servlet.ServletException;
/* 19:   */ import javax.servlet.http.HttpServlet;
/* 20:   */ import javax.servlet.http.HttpServletRequest;
/* 21:   */ import javax.servlet.http.HttpServletResponse;
/* 22:   */ 
/* 23:   */ public class ClassColumnDisplay
/* 24:   */   extends HttpServlet
/* 25:   */ {
	/* 26:   */   private static final long serialVersionUID = 8473739713339310051L;
	/* 27:   */   
	/* 28:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/* 29:   */     throws ServletException, IOException
	/* 30:   */   {
		/* 31:37 */     String id = request.getParameter("classid");
		/* 32:38 */     System.out.println(id);
		/* 33:39 */     List<Map> list = new ArrayList();
		/* 34:40 */     Connection conn = Mysql.getInstance().getConnection();
		/* 35:41 */     String str2 = request.getServerName();
		/* 36:42 */     int port = request.getServerPort();
		/* 37:43 */     String sss = request.getScheme();
		/* 38:44 */     String contextPath = request.getContextPath();
		/* 39:45 */     String sql = "SELECT\r\n\ta.*, b.*\r\nFROM\r\n\t`tlk_安卓页面展示设置` AS a\r\nLEFT JOIN `tlk_栏目管理` AS b ON a.ITEM_COLUMNNAME = b.ID\r\nWHERE\r\n\tITEM_CLASSID = '" + 
				/* 40:   */     
				/* 41:   */ 
				/* 42:   */ 
				/* 43:   */ 
				/* 44:   */ 
				/* 45:51 */       id + "'\r\n" + 
				/* 46:52 */       "ORDER BY\r\n" + 
				/* 47:53 */       "\tcast(ITEM_COLUMNNUMBER as SIGNED INTEGER) ASC";
		/* 48:   */     try
		/* 49:   */     {
			/* 50:55 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
			/* 51:56 */       ResultSet resultSet = prepareStatement.executeQuery();
			/* 52:57 */       while (resultSet.next())
			/* 53:   */       {
				/* 54:58 */         Map map = new LinkedHashMap();
				/* 55:59 */         map.put("column_number", Integer.valueOf(resultSet.getInt("item_columnnumber")));
				/* 56:60 */         map.put("class_id", resultSet.getString("item_classid"));
				/* 57:61 */         map.put("column_name", resultSet.getString("item_column_name"));
				/* 58:62 */         map.put("column_id", resultSet.getString("item_columnname"));
				/* 59:63 */         map.put("column_id", resultSet.getString("item_displaytype"));
				/* 60:64 */         Map<String, String> mapp = new LinkedHashMap();
				/* 61:65 */         String imagename = null;
				/* 62:66 */         String path = null;
				/* 63:67 */         if ((resultSet.getString("item_tp") != null) && (resultSet.getString("item_tp").length() > 5))
				/* 64:   */         {
					/* 65:68 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("item_tp").substring(1, resultSet.getString("item_tp").length() - 1), Map.class);
					/* 66:69 */           imagename = (String)mapp.get("name");
					/* 67:70 */           path = (String)mapp.get("path");
					/* 68:71 */           path = URLDecoder.decode(path, "utf-8");
					/* 69:72 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
				/* 70:   */         }
				/* 71:74 */         map.put("image_name", imagename);
				/* 72:75 */         map.put("image_path", path);
				/* 73:76 */         map.put("column_type", Integer.valueOf(resultSet.getInt("item_columnsize")));
				/* 74:77 */         list.add(map);
			/* 75:   */       }
			/* 76:79 */       resultSet.close();
			/* 77:80 */       prepareStatement.close();
			/* 78:81 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "访问成功", list)));
		/* 79:   */     }
		/* 80:   */     catch (SQLException e)
		/* 81:   */     {
			/* 82:85 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/* 83:86 */       e.printStackTrace();
		/* 92:   */     }
		/* 93:   */     finally
		/* 94:   */     {
			/* 95:   */       try
			/* 96:   */       {
				/* 97:89 */         conn.close();
			/* 98:   */       }
			/* 99:   */       catch (SQLException e)
			/* :0:   */       {
				/* :1:92 */         e.printStackTrace();
			/* :2:   */       }
		/* :3:   */     }
	/* :4:   */   }
/* :5:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassColumnDisplay
 * JD-Core Version:    0.7.0.1
 */