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
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.LinkedHashMap;
/*  15:    */ import java.util.List;
/*  16:    */ import java.util.Map;
/*  17:    */ import javax.servlet.ServletException;
/*  18:    */ import javax.servlet.http.HttpServlet;
/*  19:    */ import javax.servlet.http.HttpServletRequest;
/*  20:    */ import javax.servlet.http.HttpServletResponse;
/*  21:    */ 
/*  22:    */ public class ClassStudentList
/*  23:    */   extends HttpServlet
/*  24:    */ {
/*  25:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  26:    */     throws ServletException, IOException
/*  27:    */   {
/*  28: 35 */     String parameter = request.getParameter("placeid");
/*  29: 36 */     System.out.println(parameter);
/*  30: 37 */     Connection connection = Mysql.getInstance().getConnection();
/*  31: 38 */     List<Map<String, String>> list = new ArrayList();
/*  32: 39 */     String str2 = request.getServerName();
/*  33: 40 */     int port = request.getServerPort();
/*  34: 41 */     String sss = request.getScheme();
/*  35: 42 */     String contextPath = request.getContextPath();
/*  36: 43 */     String sql = "SELECT\r\n\ta.ID,\r\n\ta.ITEM_STUDENTID,\r\n\ta.ITEM_STUDENTNAME,\r\n\ta.ITEM_CLASSID,\r\n\ta.ITEM_AGE,\r\n\ta.ITEM_ADDRESS,\r\n\ta.ITEM_SEX,\r\n\ta.ITEM_PARENTPHONE,\r\n\ta.ITEM_STUDENTPATH,\r\n\ta.ITEM_SCHOOLID,\r\n\ta.col,\r\n\ta.ROW\r\nFROM\r\n\t`tlk_教室管理` AS f\r\nLEFT JOIN `tlk_班级基础信息` AS g ON f.ITEM_NUMBER =g.ITEM_CLASSROOMID\r\nLEFT JOIN\ttlk_student as a on a.ITEM_CLASSID=g.id\r\nWHERE\r\n\tf.id = '" + 
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
/*  54: 61 */       parameter + "'\r\n" + 
/*  55: 62 */       "order by col asc, ROW asc";
/*  56:    */     try
/*  57:    */     {
/*  58: 64 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
/*  59: 65 */       ResultSet resultSet = prepareStatement.executeQuery();
/*  60: 66 */       while (resultSet.next())
/*  61:    */       {
/*  62: 67 */         Map<String, String> map = new LinkedHashMap();
/*  63: 68 */         map.put("id", resultSet.getString("ID"));
/*  64: 69 */         map.put("student_id", resultSet.getString("ITEM_STUDENTID"));
/*  65: 70 */         map.put("student_name", resultSet.getString("ITEM_STUDENTNAME"));
/*  66: 71 */         map.put("class_id", resultSet.getString("ITEM_CLASSID"));
/*  67: 72 */         map.put("age", resultSet.getString("ITEM_AGE"));
/*  68: 73 */         map.put("address", resultSet.getString("ITEM_ADDRESS"));
/*  69: 74 */         map.put("sex", resultSet.getString("ITEM_SEX"));
/*  70: 75 */         map.put("phone_number", resultSet.getString("ITEM_PARENTPHONE"));
/*  71: 76 */         map.put("school_id", resultSet.getString("ITEM_SCHOOLID"));
/*  72: 77 */         map.put("column", resultSet.getString("col"));
/*  73: 78 */         map.put("row", resultSet.getString("row"));
/*  74: 79 */         Map<String, String> mapp = new LinkedHashMap();
/*  75: 80 */         String imagename = null;
/*  76: 81 */         String path = null;
/*  77: 82 */         if ((resultSet.getString("ITEM_STUDENTPATH") != null) && (resultSet.getString("ITEM_STUDENTPATH").length() > 10))
/*  78:    */         {
/*  79: 83 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_STUDENTPATH").substring(1, resultSet.getString("ITEM_STUDENTPATH").length() - 1), Map.class);
/*  80: 84 */           imagename = (String)mapp.get("name");
/*  81: 85 */           path = (String)mapp.get("path");
/*  82: 86 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/*  83:    */         }
/*  84: 88 */         map.put("image_name", imagename);
/*  85: 89 */         map.put("image_path", path);
/*  86: 90 */         list.add(map);
/*  87:    */       }
/*  88: 92 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/*  89:    */     }
/*  90:    */     catch (SQLException e)
/*  91:    */     {
/*  92: 95 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/*  93: 96 */       e.printStackTrace();
/* 102:    */     }
/* 103:    */     finally
/* 104:    */     {
/* 105:    */       try
/* 106:    */       {
/* 107:100 */         connection.close();
/* 108:    */       }
/* 109:    */       catch (SQLException e)
/* 110:    */       {
/* 111:103 */         e.printStackTrace();
/* 112:    */       }
/* 113:    */     }
/* 114:    */   }
/* 115:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassStudentList
 * JD-Core Version:    0.7.0.1
 */