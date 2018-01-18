/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import java.io.IOException;
/*  8:   */ import java.sql.Connection;
/*  9:   */ import java.sql.PreparedStatement;
/* 10:   */ import java.sql.ResultSet;
/* 11:   */ import java.sql.SQLException;
/* 12:   */ import java.util.ArrayList;
/* 13:   */ import java.util.LinkedHashMap;
/* 14:   */ import java.util.List;
/* 15:   */ import java.util.Map;
/* 16:   */ import javax.servlet.ServletException;
/* 17:   */ import javax.servlet.http.HttpServlet;
/* 18:   */ import javax.servlet.http.HttpServletRequest;
/* 19:   */ import javax.servlet.http.HttpServletResponse;
/* 20:   */ 
/* 21:   */ public class ConferenceServlet
/* 22:   */   extends HttpServlet
/* 23:   */ {
/* 24:   */   private static final long serialVersionUID = 1L;
/* 25:   */   
/* 26:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:35 */     String id = request.getParameter("schoolid");
/* 30:36 */     String sql = "SELECT\r\n\t*\r\nFROM\r\n\ttlk_会议\r\nWHERE\r\n\tITEM_SCHOOLID = '" + id + "'";
/* 36:42 */     Connection connection = Mysql.getInstance().getConnection();
/* 37:   */     try
/* 38:   */     {
/* 39:44 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
/* 40:45 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 41:46 */       List<Map<String, String>> list = new ArrayList();
/* 42:47 */       while (resultSet.next())
/* 43:   */       {
/* 44:48 */         Map<String, String> map = new LinkedHashMap();
/* 45:49 */         map.put("title", resultSet.getString("item_title"));
/* 46:50 */         map.put("item", resultSet.getString("item_name"));
/* 47:51 */         map.put("content", resultSet.getString("item_hylc"));
/* 48:52 */         map.put("username", resultSet.getString("item_peoplename"));
/* 49:53 */         map.put("startdate", resultSet.getDate("item_startdate") + "\t" + resultSet.getTime("item_startdate"));
/* 50:54 */         map.put("enddate", resultSet.getDate("item_enddate") + "\t" + resultSet.getTime("item_enddate"));
/* 51:55 */         map.put("peopleid", resultSet.getString("item_peopleid"));
/* 52:56 */         map.put("id", resultSet.getString("id"));
/* 53:57 */         map.put("schoolid", resultSet.getString("item_schoolid"));
/* 54:58 */         list.add(map);
/* 55:   */       }
/* 56:60 */       prepareStatement.close();
/* 57:61 */       resultSet.close();
/* 58:62 */       ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 59:   */     }
/* 60:   */     catch (SQLException e)
/* 61:   */     {
/* 62:65 */       ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 72:   */     }
/* 73:   */     finally
/* 74:   */     {
/* 75:   */       try
/* 76:   */       {
/* 77:69 */         connection.close();
/* 78:   */       }
/* 79:   */       catch (SQLException e)
/* 80:   */       {
/* 81:72 */         e.printStackTrace();
/* 82:   */       }
/* 83:   */     }
/* 84:   */   }
/* 85:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.ConferenceServlet
 * JD-Core Version:    0.7.0.1
 */