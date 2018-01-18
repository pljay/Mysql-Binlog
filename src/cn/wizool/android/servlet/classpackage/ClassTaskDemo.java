/*  1:   */ package cn.wizool.android.servlet.classpackage;
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
/* 13:   */ import java.text.SimpleDateFormat;
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.LinkedHashMap;
/* 16:   */ import java.util.List;
/* 17:   */ import java.util.Map;
/* 18:   */ import javax.servlet.ServletException;
/* 19:   */ import javax.servlet.http.HttpServlet;
/* 20:   */ import javax.servlet.http.HttpServletRequest;
/* 21:   */ import javax.servlet.http.HttpServletResponse;
/* 22:   */ 
/* 23:   */ public class ClassTaskDemo
/* 24:   */   extends HttpServlet
/* 25:   */ {
/* 26:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:37 */     String id = request.getParameter("classid");
/* 30:38 */     System.out.println(id);
/* 31:39 */     String date = request.getParameter("date");
/* 32:40 */     Connection conn = Mysql.getInstance().getConnection();
/* 33:41 */     List<Map<String, String>> list = new ArrayList();
/* 34:42 */     String sql = "";
/* 35:43 */     if (date == null) {
/* 36:44 */       sql = 
/* 37:   */       
/* 38:   */ 
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:   */ 
/* 45:53 */         "SELECT\r\n\ta.ITEM_SUBJECT,\r\n\ta.ITEM_CON,\r\n\ta.ITEM_SHIJIAN\r\nFROM\r\n\t`tlk_教室管理` AS f\r\nLEFT JOIN `tlk_班级基础信息` AS g ON f.ITEM_NUMBER =g.ITEM_CLASSROOMID\r\nLEFT JOIN\t`tlk_布置作业` as a on a.ITEM_CLASSID=g.id \r\nWHERE\r\n\tg.id = '" + id + "'\r\n";
/* 46:   */     } else {
/* 47:56 */       sql = 
/* 48:   */       
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:66 */         "SELECT\r\n\ta.ITEM_SUBJECT,\r\n\ta.ITEM_CON,\r\n\ta.ITEM_SHIJIAN\r\nFROM\r\n\t`tlk_教室管理` AS f\r\nLEFT JOIN `tlk_班级基础信息` AS g ON f.ITEM_NUMBER =g.ITEM_CLASSROOMID\r\nLEFT JOIN\t`tlk_布置作业` as a on a.ITEM_CLASSID=g.id \r\nWHERE\r\n\tg.id = '" + id + "'\r\n" + "AND a.ITEM_SHIJIAN = '" + date + "'";
/* 58:   */     }
/* 59:   */     try
/* 60:   */     {
/* 61:69 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/* 62:70 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 63:71 */       while (resultSet.next())
/* 64:   */       {
/* 65:72 */         Map<String, String> mapp = new LinkedHashMap();
/* 66:73 */         mapp.put("date", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_SHIJIAN")));
/* 67:74 */         mapp.put("subject", resultSet.getString("ITEM_SUBJECT"));
/* 68:75 */         mapp.put("tast", resultSet.getString("ITEM_CON"));
/* 69:76 */         list.add(mapp);
/* 70:   */       }
/* 71:78 */       resultSet.close();
/* 72:79 */       prepareStatement.close();
/* 73:80 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 74:   */     }
/* 75:   */     catch (SQLException e)
/* 76:   */     {
/* 77:83 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 78:84 */       e.printStackTrace();
/* 87:   */     }
/* 88:   */     finally
/* 89:   */     {
/* 90:   */       try
/* 91:   */       {
/* 92:87 */         conn.close();
/* 93:   */       }
/* 94:   */       catch (SQLException e)
/* 95:   */       {
/* 96:90 */         e.printStackTrace();
/* 97:   */       }
/* 98:   */     }
/* 99:   */   }
/* :0:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassTaskDemo
 * JD-Core Version:    0.7.0.1
 */