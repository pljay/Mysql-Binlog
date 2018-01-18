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
/* 12:   */ import java.text.SimpleDateFormat;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.LinkedHashMap;
/* 15:   */ import java.util.List;
/* 16:   */ import java.util.Map;
/* 17:   */ import javax.servlet.ServletException;
/* 18:   */ import javax.servlet.http.HttpServlet;
/* 19:   */ import javax.servlet.http.HttpServletRequest;
/* 20:   */ import javax.servlet.http.HttpServletResponse;
/* 21:   */ import org.slf4j.Logger;
/* 22:   */ import org.slf4j.LoggerFactory;
/* 23:   */ 
/* 24:   */ public class ModeswitchDemo
/* 25:   */   extends HttpServlet
/* 26:   */ {
/* 27:37 */   private static Logger logger = LoggerFactory.getLogger(ModeswitchDemo.class);
/* 28:   */   private static final long serialVersionUID = -3039013583935817963L;
/* 29:   */   
/* 30:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 31:   */     throws ServletException, IOException
/* 32:   */   {
/* 33:42 */     String id = request.getParameter("equipmentid");
/* 34:43 */     Connection conn = Mysql.getInstance().getConnection();
/* 35:44 */     String sql = "SELECT\r\n\tITEM_EQUIPMENTID,\r\n\tITEM_WEEK,\r\n\tITEM_START_TIME,\r\n\tITEM_END_TIME,\r\n\tITEM_SHOWMODE\r\nFROM\r\n\t`tlk_安卓设备模式选择`\r\nWHERE\r\n\tITEM_EQUIPMENTID = '" + 
/* 36:   */     
/* 37:   */ 
/* 38:   */ 
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:53 */       id + "'\r\n" + 
/* 45:54 */       "ORDER BY\r\n" + 
/* 46:55 */       "\tITEM_WEEK";
/* 47:   */     try
/* 48:   */     {
/* 49:57 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/* 50:58 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 51:59 */       List<Map<String, String>> list = new ArrayList();
/* 52:60 */       while (resultSet.next())
/* 53:   */       {
/* 54:61 */         Map<String, String> map = new LinkedHashMap();
/* 55:62 */         map.put("week", resultSet.getString("ITEM_WEEK"));
/* 56:63 */         map.put("equipment_id", resultSet.getString("ITEM_EQUIPMENTID"));
/* 57:64 */         map.put("start_time", new SimpleDateFormat("HH:mm").format(resultSet.getTime("ITEM_START_TIME")));
/* 58:65 */         map.put("end_time", new SimpleDateFormat("HH:mm").format(resultSet.getTime("ITEM_END_TIME")));
/* 59:66 */         map.put("showmode", resultSet.getString("ITEM_SHOWMODE"));
/* 60:67 */         list.add(map);
/* 61:   */       }
/* 62:69 */       resultSet.close();
/* 63:70 */       prepareStatement.close();
/* 64:71 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 65:   */     }
/* 66:   */     catch (SQLException e)
/* 67:   */     {
/* 68:75 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 69:76 */       e.printStackTrace();
/* 78:   */     }
/* 79:   */     finally
/* 80:   */     {
/* 81:   */       try
/* 82:   */       {
/* 83:79 */         conn.close();
/* 84:   */       }
/* 85:   */       catch (SQLException e)
/* 86:   */       {
/* 87:82 */         e.printStackTrace();
/* 88:   */       }
/* 89:   */     }
/* 90:   */   }
/* 91:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.ModeswitchDemo
 * JD-Core Version:    0.7.0.1
 */