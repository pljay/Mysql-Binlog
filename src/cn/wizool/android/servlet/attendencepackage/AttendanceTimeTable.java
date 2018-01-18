/*  1:   */ package cn.wizool.android.servlet.attendencepackage;
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
/* 21:   */ public class AttendanceTimeTable
/* 22:   */   extends HttpServlet
/* 23:   */ {
/* 24:   */   private static final long serialVersionUID = 1L;
/* 25:   */   
/* 26:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:38 */     Connection connection = Mysql.getInstance().getConnection();
/* 30:39 */     String sql = "SELECT\r\n\tID,\r\n\tITEM_START_ATTENDDATE,\r\n\tITEM_STARTWORKDATE,\r\n\tITEM_END_ATTENDDATE,\r\n\tITEM_START_OFF_ATTENDDATE,\r\n\tITEM_ENDWORKDATE,\r\n\tITEM_END_OFF_ATTENDDATE,\r\n\tITEM_NAME\r\nFROM\r\n\t`tlk_时间管理`";
/* 31:   */     try
/* 32:   */     {
/* 33:51 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
/* 34:52 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 35:53 */       List<Map> list = new ArrayList();
/* 36:54 */       while (resultSet.next())
/* 37:   */       {
/* 38:55 */         Map map = new LinkedHashMap();
/* 39:56 */         map.put("id", resultSet.getString("ID"));
/* 40:57 */         map.put("item_name", resultSet.getString("ITEM_NAME"));
/* 41:58 */         List<Map<String, String>> list1 = new ArrayList();
/* 42:59 */         Map<String, String> map1 = new LinkedHashMap();
/* 43:60 */         map1.put("name", "正常上班");
/* 44:61 */         map1.put("start_date", resultSet.getString("ITEM_START_ATTENDDATE"));
/* 45:62 */         map1.put("end_date", resultSet.getString("ITEM_STARTWORKDATE"));
/* 46:63 */         list1.add(map1);
/* 47:64 */         Map<String, String> map2 = new LinkedHashMap();
/* 48:65 */         map2.put("name", "迟到");
/* 49:66 */         map2.put("start_date", resultSet.getString("ITEM_STARTWORKDATE"));
/* 50:67 */         map2.put("end_date", resultSet.getString("ITEM_END_ATTENDDATE"));
/* 51:68 */         list1.add(map2);
/* 52:69 */         Map<String, String> map3 = new LinkedHashMap();
/* 53:70 */         map3.put("name", "早退");
/* 54:71 */         map3.put("start_date", resultSet.getString("ITEM_START_OFF_ATTENDDATE"));
/* 55:72 */         map3.put("end_date", resultSet.getString("ITEM_ENDWORKDATE"));
/* 56:73 */         list1.add(map3);
/* 57:74 */         Map<String, String> map4 = new LinkedHashMap();
/* 58:75 */         map4.put("name", "正常下班");
/* 59:76 */         map4.put("start_date", resultSet.getString("ITEM_ENDWORKDATE"));
/* 60:77 */         map4.put("end_date", resultSet.getString("ITEM_END_OFF_ATTENDDATE"));
/* 61:78 */         list1.add(map4);
/* 62:79 */         map.put("date", list1);
/* 63:80 */         list.add(map);
/* 64:   */       }
/* 65:82 */       ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 66:   */     }
/* 67:   */     catch (SQLException e)
/* 68:   */     {
/* 69:85 */       e.printStackTrace();
/* 78:   */     }
/* 79:   */     finally
/* 80:   */     {
/* 81:   */       try
/* 82:   */       {
/* 83:88 */         connection.close();
/* 84:   */       }
/* 85:   */       catch (SQLException e)
/* 86:   */       {
/* 87:91 */         e.printStackTrace();
/* 88:   */       }
/* 89:   */     }
/* 90:   */   }
/* 91:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceTimeTable
 * JD-Core Version:    0.7.0.1
 */