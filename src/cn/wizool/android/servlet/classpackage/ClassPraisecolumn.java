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
/*  23:    */ public class ClassPraisecolumn
/*  24:    */   extends HttpServlet
/*  25:    */ {
/*  26:    */   private static final long serialVersionUID = 1L;
/*  27:    */   
/*  28:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  29:    */     throws ServletException, IOException
/*  30:    */   {
/*  31: 38 */     String code = request.getParameter("classid");
/*  32: 39 */     String date = request.getParameter("date");
/*  33: 40 */     String sql = null;
/*  34: 41 */     System.out.println(code);
/*  35: 42 */     Connection conn = Mysql.getInstance().getConnection();
/*  36: 43 */     String str2 = request.getServerName();
/*  37: 44 */     int port = request.getServerPort();
/*  38: 45 */     String sss = request.getScheme();
/*  39: 46 */     String contextPath = request.getContextPath();
/*  40: 47 */     List<Map<String, String>> list = new ArrayList();
/*  41: 48 */     if (date == null) {
/*  42: 49 */       sql = 
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
/*  56: 63 */         "SELECT\r\ntlk_student.ITEM_STUDENTNAME,\r\nITEM_CONTENTS,\r\nITEM_BIAODATE,\r\n`tlk_表扬栏`.ITEM_LEI,\r\ntlk_student.ITEM_STUDENTPATH\r\nFROM\r\n\t`tlk_表扬栏`,\r\n\t`tlk_表扬栏图片`,\r\n\t\ttlk_student\r\nWHERE\r\n\t`tlk_表扬栏`.ITEM_CLASSID ='" + code + "'AND tlk_student.ID = `tlk_表扬栏`.ITEM_STUDENTID\r\n" + "AND `tlk_表扬栏`.ITEM_LEI = `tlk_表扬栏图片`.ITEM_NAME\r\n" + "order by ITEM_BIAODATE desc ";
/*  57:    */     } else {
/*  58: 66 */       sql = 
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
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77: 85 */         "SELECT\r\n\ttlk_student.ITEM_STUDENTNAME,\r\n\tITEM_CONTENTS,\r\n\tITEM_BIAODATE,\r\n\t`tlk_表扬栏`.ITEM_LEI,\r\n\ttlk_student.ITEM_STUDENTPATH\r\nFROM\r\n\t`tlk_表扬栏` ,`tlk_表扬栏图片`, tlk_student\r\nWHERE\r\n\t`tlk_表扬栏`.ITEM_CLASSID = '" + code + "'\r\n" + "AND (\r\n" + "\t `tlk_表扬栏`.ITEM_BIAODATE<= DATE_add(\r\n" + "\t\t'" + date + "',\r\n" + "\t\tINTERVAL 15 DAY\r\n" + "\t)\r\n" + ")\r\n" + "AND tlk_student.ID = `tlk_表扬栏`.ITEM_STUDENTID\r\n" + "AND `tlk_表扬栏`.ITEM_LEI = `tlk_表扬栏图片`.ITEM_NAME\r\n" + "ORDER BY\r\n" + "\tITEM_BIAODATE DESC";
/*  78:    */     }
/*  79:    */     try
/*  80:    */     {
/*  81: 89 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/*  82: 90 */       ResultSet resultSet = prepareStatement.executeQuery();
/*  83: 91 */       while (resultSet.next())
/*  84:    */       {
/*  85: 92 */         Map<String, String> mapp = new LinkedHashMap();
/*  86: 93 */         String student_path = null;
/*  87: 94 */         if ((resultSet.getString("ITEM_STUDENTPATH") != null) && (resultSet.getString("ITEM_STUDENTPATH").length() > 10))
/*  88:    */         {
/*  89: 95 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_STUDENTPATH").substring(1, resultSet.getString("ITEM_STUDENTPATH").length() - 1), Map.class);
/*  90: 96 */           student_path = (String)mapp.get("path");
/*  91: 97 */           student_path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/*  92:    */         }
/*  93: 99 */         Map<String, String> praisecolumnmap = new LinkedHashMap();
/*  94:100 */         praisecolumnmap.put("name", resultSet.getString("ITEM_STUDENTNAME"));
/*  95:101 */         praisecolumnmap.put("contents", resultSet.getString("ITEM_CONTENTS"));
/*  96:102 */         praisecolumnmap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_BIAODATE")));
/*  97:103 */         praisecolumnmap.put("kinds", resultSet.getString("ITEM_LEI"));
/*  98:104 */         praisecolumnmap.put("image_path", student_path);
/*  99:105 */         list.add(praisecolumnmap);
/* 100:    */       }
/* 101:107 */       resultSet.close();
/* 102:108 */       prepareStatement.close();
/* 103:109 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "访问成功", list)));
/* 104:    */     }
/* 105:    */     catch (SQLException e)
/* 106:    */     {
/* 107:112 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 108:113 */       e.printStackTrace();
/* 117:    */     }
/* 118:    */     finally
/* 119:    */     {
/* 120:    */       try
/* 121:    */       {
/* 122:116 */         conn.close();
/* 123:    */       }
/* 124:    */       catch (SQLException e)
/* 125:    */       {
/* 126:119 */         e.printStackTrace();
/* 127:    */       }
/* 128:    */     }
/* 129:    */   }
/* 130:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassPraisecolumn
 * JD-Core Version:    0.7.0.1
 */