/*  1:   */ package cn.wizool.android.servlet.attendencepackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import java.io.IOException;
/*  8:   */ import java.sql.Connection;
/*  9:   */ import java.sql.ResultSet;
/* 10:   */ import java.sql.SQLException;
/* 11:   */ import java.sql.Statement;
/* 12:   */ import java.util.ArrayList;
/* 13:   */ import java.util.LinkedHashMap;
/* 14:   */ import java.util.List;
/* 15:   */ import java.util.Map;
/* 16:   */ import javax.servlet.ServletException;
/* 17:   */ import javax.servlet.http.HttpServlet;
/* 18:   */ import javax.servlet.http.HttpServletRequest;
/* 19:   */ import javax.servlet.http.HttpServletResponse;
/* 20:   */ 
/* 21:   */ public class AttendanceTimeTables
/* 22:   */   extends HttpServlet
/* 23:   */ {
/* 24:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 25:   */     throws ServletException, IOException
/* 26:   */   {
/* 27:28 */     Connection connection = Mysql.getInstance().getConnection();
/* 28:29 */     String sql = "SELECT\r\n\tID,\r\n\tITEM_START_ATTENDDATE,\r\n\tITEM_STARTWORKDATE,\r\n\tITEM_END_ATTENDDATE,\r\n\tITEM_START_OFF_ATTENDDATE,\r\n\tITEM_ENDWORKDATE,\r\n\tITEM_END_OFF_ATTENDDATE,\r\n\tITEM_NAME\r\nFROM\r\n\t`tlk_时间管理`";
/* 29:   */     try
/* 30:   */     {
/* 31:41 */       Statement createStatement = connection.createStatement();
/* 32:42 */       ResultSet resultSet = createStatement.executeQuery(sql);
/* 33:43 */       List<Map<String, String>> list = new ArrayList();
/* 34:44 */       while (resultSet.next())
/* 35:   */       {
/* 36:45 */         Map<String, String> map = new LinkedHashMap();
/* 37:46 */         map.put("id", resultSet.getString("id"));
/* 38:47 */         map.put("start_attenddate", resultSet.getString("item_start_attenddate"));
/* 39:48 */         map.put("startworkdate", resultSet.getString("item_startworkdate"));
/* 40:49 */         map.put("end_attenddate", resultSet.getString("item_end_attenddate"));
/* 41:50 */         map.put("start_off_attenddate", resultSet.getString("item_start_off_attenddate"));
/* 42:51 */         map.put("endworkdate", resultSet.getString("item_endworkdate"));
/* 43:52 */         map.put("end_off_attenddate", resultSet.getString("item_end_off_attenddate"));
/* 44:53 */         map.put("name", resultSet.getString("item_name"));
/* 45:54 */         list.add(map);
/* 46:   */       }
/* 47:56 */       resultSet.close();
/* 48:57 */       createStatement.close();
/* 49:58 */       ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 50:   */     }
/* 51:   */     catch (SQLException e)
/* 52:   */     {
/* 53:61 */       e.printStackTrace();
/* 62:   */     }
/* 63:   */     finally
/* 64:   */     {
/* 65:   */       try
/* 66:   */       {
/* 67:64 */         connection.close();
/* 68:   */       }
/* 69:   */       catch (SQLException e)
/* 70:   */       {
/* 71:67 */         e.printStackTrace();
/* 72:   */       }
/* 73:   */     }
/* 74:   */   }
/* 75:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceTimeTables
 * JD-Core Version:    0.7.0.1
 */