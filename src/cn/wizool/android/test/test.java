/*  1:   */ package cn.wizool.android.test;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import com.fasterxml.jackson.databind.ObjectMapper;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.sql.Connection;
/*  8:   */ import java.sql.PreparedStatement;
/*  9:   */ import java.sql.ResultSet;
/* 10:   */ import java.sql.SQLException;
/* 11:   */ import java.util.LinkedHashMap;
/* 12:   */ import java.util.Map;
/* 13:   */ import org.slf4j.Logger;
/* 14:   */ import org.slf4j.LoggerFactory;
/* 15:   */ 
/* 16:   */ public class test
/* 17:   */ {
/* 18:26 */   private static Logger logger = LoggerFactory.getLogger(test.class);
/* 19:   */   
/* 20:   */   public static void main(String[] args)
/* 21:   */     throws SQLException, IOException
/* 22:   */   {
/* 23:38 */     String sql = "select * from binlog   where eventId='0'";
/* 24:   */     
/* 25:40 */     Connection connection = Mysql.getInstance().getConnection();
/* 26:41 */     PreparedStatement prepareStatement = connection.prepareStatement(sql);
/* 27:42 */     ResultSet executeQuery = prepareStatement.executeQuery();
/* 28:43 */     Map map = new LinkedHashMap();
/* 29:44 */     ObjectMapper objectMapper = new ObjectMapper();
/* 30:45 */     testbean t = null;
/* 31:46 */     String a = null;
/* 32:47 */     while (executeQuery.next())
/* 33:   */     {
/* 34:57 */       map.put("eventId", executeQuery.getString("eventId"));
/* 35:58 */       map.put("databaseName", executeQuery.getString("databaseName"));
/* 36:59 */       map.put("tableName", executeQuery.getString("tableName"));
/* 37:60 */       map.put("eventType", executeQuery.getString("eventType"));
/* 38:61 */       map.put("timestamp", executeQuery.getString("timestamp"));
/* 39:62 */       map.put("timestampReceipt", executeQuery.getString("timestampReceipt"));
/* 40:63 */       map.put("binlogName", executeQuery.getString("binlogName"));
/* 41:64 */       map.put("position", executeQuery.getString("position"));
/* 42:65 */       map.put("nextPostion", executeQuery.getString("nextPostion"));
/* 43:66 */       map.put("serverId", executeQuery.getString("serverId"));
/* 44:67 */       map.put("before", JsonUtils.jsonToPojonotnull(executeQuery.getString("before"), Map.class));
/* 45:68 */       map.put("after", JsonUtils.jsonToPojonotnull(executeQuery.getString("after"), Map.class));
/* 46:69 */       map.put("isDdl", executeQuery.getString("isDdl"));
/* 47:70 */       map.put("sql", executeQuery.getString("sql"));
/* 48:   */     }
/* 49:75 */     logger.info("maptojson:" + JsonUtils.objectToJson(map));
/* 50:76 */     logger.info(objectMapper.writeValueAsString(map).toString().replaceAll("\\\\", "").toString().replace("\"{", "{").replace("}\"", "}"));
/* 51:   */   }
/* 52:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.test.test
 * JD-Core Version:    0.7.0.1
 */