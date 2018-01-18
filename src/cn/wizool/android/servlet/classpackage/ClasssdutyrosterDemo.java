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
/* 15:   */ import java.util.HashMap;
/* 16:   */ import java.util.List;
/* 17:   */ import java.util.Map;
/* 18:   */ import javax.servlet.ServletException;
/* 19:   */ import javax.servlet.http.HttpServlet;
/* 20:   */ import javax.servlet.http.HttpServletRequest;
/* 21:   */ import javax.servlet.http.HttpServletResponse;
/* 22:   */ 
/* 23:   */ public class ClasssdutyrosterDemo
/* 24:   */   extends HttpServlet
/* 25:   */ {
/* 26:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:37 */     String id = request.getParameter("placeid");
/* 30:38 */     String date = request.getParameter("date");
/* 31:39 */     System.out.println(id);
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
/* 44:52 */         "SELECT\r\n\tc.ITEM_STUDENTNAME,a.ITEM_DATE\r\nFROM\r\n`tlk_教室管理` AS f\r\nLEFT JOIN `tlk_班级基础信息` AS g ON f.ITEM_NUMBER =g.ITEM_CLASSROOMID\r\nLEFT JOIN\t`tlk_值日生管理` AS a ON a.ITEM_CLASSID=g.ID\r\nLEFT JOIN `tlk_值日生` AS b ON a.id = b.ITEM_ZHIID\r\nLEFT JOIN tlk_student AS c ON c.id = b.ITEM_STUDENTID\r\nWHERE f.ID='" + id + "'";
/* 45:   */     } else {
/* 46:55 */       sql = 
/* 47:   */       
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:64 */         "SELECT\r\n\tc.ITEM_STUDENTNAME,a.ITEM_DATE\r\nFROM\r\n`tlk_教室管理` AS f\r\nLEFT JOIN `tlk_班级基础信息` AS g ON f.ITEM_NUMBER =g.ITEM_CLASSROOMID\r\nLEFT JOIN\t`tlk_值日生管理` AS a ON a.ITEM_CLASSID=g.ID\r\nLEFT JOIN `tlk_值日生` AS b ON a.id = b.ITEM_ZHIID\r\nLEFT JOIN tlk_student AS c ON c.id = b.ITEM_STUDENTID\r\nWHERE f.ID='" + id + "'\r\n" + "AND a.ITEM_DATE='" + date + "'";
/* 56:   */     }
/* 57:   */     try
/* 58:   */     {
/* 59:67 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/* 60:68 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 61:69 */       System.out.println("11111111111111");
/* 62:70 */       while (resultSet.next())
/* 63:   */       {
/* 64:71 */         Map<String, String> mapp = new HashMap();
/* 65:72 */         mapp.put("name", resultSet.getString("ITEM_STUDENTNAME"));
/* 66:73 */         mapp.put("date", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_DATE")));
/* 67:74 */         list.add(mapp);
/* 68:   */       }
/* 69:76 */       resultSet.close();
/* 70:77 */       prepareStatement.close();
/* 71:78 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 72:   */     }
/* 73:   */     catch (SQLException e)
/* 74:   */     {
/* 75:81 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 76:82 */       e.printStackTrace();
/* 85:   */     }
/* 86:   */     finally
/* 87:   */     {
/* 88:   */       try
/* 89:   */       {
/* 90:85 */         conn.close();
/* 91:   */       }
/* 92:   */       catch (SQLException e)
/* 93:   */       {
/* 94:88 */         e.printStackTrace();
/* 95:   */       }
/* 96:   */     }
/* 97:   */   }
/* 98:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClasssdutyrosterDemo
 * JD-Core Version:    0.7.0.1
 */