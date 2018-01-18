/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ReaderBodyInfoUtils;
/*  6:   */ import cn.wizool.android.utils.ResponseUtils;
/*  7:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  8:   */ import com.fasterxml.jackson.databind.ObjectMapper;
/*  9:   */ import java.io.IOException;
/* 10:   */ import java.io.PrintStream;
/* 11:   */ import java.sql.Connection;
/* 12:   */ import java.sql.PreparedStatement;
/* 13:   */ import java.sql.SQLException;
/* 14:   */ import java.util.LinkedHashMap;
/* 15:   */ import java.util.Map;
/* 16:   */ import java.util.UUID;
/* 17:   */ import javax.servlet.ServletException;
/* 18:   */ import javax.servlet.http.HttpServlet;
/* 19:   */ import javax.servlet.http.HttpServletRequest;
/* 20:   */ import javax.servlet.http.HttpServletResponse;
/* 21:   */ 
/* 22:   */ public class DeviceInfoInsertServlet
/* 23:   */   extends HttpServlet
/* 24:   */ {
/* 25:   */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/* 26:   */     throws ServletException, IOException
/* 27:   */   {
/* 28:39 */     String params = null;
/* 29:40 */     params = ReaderBodyInfoUtils.ReadBody(request);
/* 30:41 */     Map<String, String> map = new LinkedHashMap();
/* 31:42 */     ObjectMapper mapper = new ObjectMapper();
/* 32:43 */     map = (Map)mapper.readValue(params, Map.class);
/* 33:44 */     Connection connection = Mysql.getInstance().getConnection();
/* 34:45 */     PreparedStatement prepareStatement = null;
/* 35:   */     try
/* 36:   */     {
/* 37:47 */       String sql = "INSERT INTO `tlk_安卓设备管理` (\r\n\r\n\t`id`,\r\n\t`item_equipmentid`,\r\n\t`item_status`,\r\n\t`item_lastconnecttime`,\r\n\t`item_mac`,\r\n\t`item_ip`,\r\n\t`item_placeid`,\r\n\t`item_placeroom`,\r\n\t`item_schoolid`\r\n)\r\nVALUES\r\n\t(\r\n\t\t'" + 
/* 38:   */       
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:   */ 
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:61 */         UUID.randomUUID().toString() + "',\r\n" + 
/* 52:62 */         "\t\t'" + (String)map.get("equipmentid") + "',\r\n" + 
/* 53:63 */         "\t\t'" + "已连接" + "',\r\n" + 
/* 54:64 */         "\t\tcurrent_timestamp(),\r\n" + 
/* 55:65 */         "\t\t'" + (String)map.get("mac") + "',\r\n" + 
/* 56:66 */         "\t\t'" + (String)map.get("ipaddress") + "',\r\n" + 
/* 57:67 */         "\t\t'" + (String)map.get("classid") + "',\r\n" + 
/* 58:68 */         "\t\t'" + (String)map.get("classroom") + "',\r\n" + 
/* 59:69 */         "\t\t'" + (String)map.get("schoolid") + "'\r\n" + 
/* 60:70 */         ")ON DUPLICATE KEY UPDATE `item_equipmentid`=VALUES(`item_equipmentid`),`item_status`=VALUES(`item_status`),`item_lastconnecttime`=VALUES(`item_lastconnecttime`),\r\n" + 
/* 61:71 */         "`item_mac`=VALUES(`item_mac`),`item_ip`=VALUES(`item_ip`),`item_placeid`=VALUES(`item_placeid`),\r\n" + 
/* 62:72 */         "`item_placeroom`=VALUES(`item_placeroom`),`item_schoolid`=VALUES(`item_schoolid`)\r\n";
/* 63:73 */       System.out.println((String)map.get("equipmentid"));
/* 64:74 */       System.out.println(sql);
/* 65:75 */       prepareStatement = connection.prepareStatement(sql);
/* 66:76 */       prepareStatement.executeUpdate();
/* 67:77 */       prepareStatement.close();
/* 68:78 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok()));
/* 69:   */     }
/* 70:   */     catch (SQLException e)
/* 71:   */     {
/* 72:80 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 73:81 */       e.printStackTrace();
/* 82:   */     }
/* 83:   */     finally
/* 84:   */     {
/* 85:   */       try
/* 86:   */       {
/* 87:84 */         connection.close();
/* 88:   */       }
/* 89:   */       catch (SQLException e)
/* 90:   */       {
/* 91:86 */         e.printStackTrace();
/* 92:   */       }
/* 93:   */     }
/* 94:   */   }
/* 95:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.DeviceInfoInsertServlet
 * JD-Core Version:    0.7.0.1
 */