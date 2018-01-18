/*  1:   */ package cn.wizool.android.servlet.attendencepackage;
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
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.LinkedHashMap;
/* 16:   */ import java.util.List;
/* 17:   */ import java.util.Map;
/* 18:   */ import javax.servlet.ServletException;
/* 19:   */ import javax.servlet.http.HttpServlet;
/* 20:   */ import javax.servlet.http.HttpServletRequest;
/* 21:   */ import javax.servlet.http.HttpServletResponse;
/* 22:   */ 
/* 23:   */ public class AttendanceInsertServlet
/* 24:   */   extends HttpServlet
/* 25:   */ {
/* 26:   */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:37 */     String params = null;
/* 30:38 */     params = ReaderBodyInfoUtils.ReadBody(request);
/* 31:39 */     System.out.println(params);
/* 32:40 */     ObjectMapper mapper = new ObjectMapper();
/* 33:41 */     Map<String, String> map = new LinkedHashMap();
/* 34:42 */     List<Map<String, String>> list = new ArrayList();
/* 35:43 */     list = (List)mapper.readValue(params, List.class);
/* 36:44 */     Connection connection = Mysql.getInstance().getConnection();
/* 37:45 */     PreparedStatement prepareStatement = null;
/* 38:   */     try
/* 39:   */     {
/* 40:47 */       for (int i = 0; i < list.size(); i++)
/* 41:   */       {
/* 42:48 */         map = (Map)list.get(i);
/* 43:49 */         String sql = "INSERT INTO `xiaoxuan`.`attendance` (\r\n\r\n\t`cardid`,\r\n\t`pattern`,\r\n\t`starttime`,\r\n\t`endtime`,\r\n\t`date`,\r\n\t`deviceid`,\r\n\t`type`,\r\n\t`startstate`,\r\n\t`endstate`\r\n)\r\nVALUES\r\n\t(\r\n\t\t'" + 
/* 44:   */         
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:63 */           (String)map.get("cardid") + "',\r\n" + 
/* 58:64 */           "\t\t'" + (String)map.get("pattern") + "',\r\n" + 
/* 59:65 */           "\t\t'" + (String)map.get("starttime") + "',\r\n" + 
/* 60:66 */           "\t\t'" + (String)map.get("endtime") + "',\r\n" + 
/* 61:67 */           "\t\t'" + (String)map.get("date") + "',\r\n" + 
/* 62:68 */           "\t\t'" + (String)map.get("deviced") + "',\r\n" + 
/* 63:69 */           "\t\t'" + (String)map.get("type") + "',\r\n" + 
/* 64:70 */           "\t\t'" + (String)map.get("startstate") + "',\r\n" + 
/* 65:71 */           "\t\t'" + "'\r\n" + 
/* 66:72 */           ")";
/* 67:73 */         System.out.println((String)map.get("deviced"));
/* 68:74 */         prepareStatement = connection.prepareStatement(sql);
/* 69:75 */         prepareStatement.executeUpdate();
/* 70:   */       }
/* 71:77 */       prepareStatement.close();
/* 72:78 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok()));
/* 73:   */     }
/* 74:   */     catch (SQLException e)
/* 75:   */     {
/* 76:81 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 77:82 */       e.printStackTrace();
/* 86:   */     }
/* 87:   */     finally
/* 88:   */     {
/* 89:   */       try
/* 90:   */       {
/* 91:85 */         connection.close();
/* 92:   */       }
/* 93:   */       catch (SQLException e)
/* 94:   */       {
/* 95:88 */         e.printStackTrace();
/* 96:   */       }
/* 97:   */     }
/* 98:   */   }
/* 99:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceInsertServlet
 * JD-Core Version:    0.7.0.1
 */