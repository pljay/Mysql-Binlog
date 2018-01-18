/*  1:   */ package cn.wizool.android.utils;
/*  2:   */ 
/*  3:   */ import com.fasterxml.jackson.core.JsonGenerator;
/*  4:   */ import com.fasterxml.jackson.core.JsonProcessingException;
/*  5:   */ import com.fasterxml.jackson.databind.JavaType;
/*  6:   */ import com.fasterxml.jackson.databind.JsonSerializer;
/*  7:   */ import com.fasterxml.jackson.databind.ObjectMapper;
/*  8:   */ import com.fasterxml.jackson.databind.SerializerProvider;
/*  9:   */ import com.fasterxml.jackson.databind.type.TypeFactory;
/* 10:   */ import java.io.IOException;
/* 11:   */ import java.util.List;
/* 12:   */ 
/* 13:   */ public class JsonUtils
/* 14:   */ {
/* 15:21 */   private static final ObjectMapper MAPPER = new ObjectMapper();
/* 16:   */   
/* 17:   */   public static String objectToJson(Object data)
/* 18:   */   {
/* 19:   */     try
/* 20:   */     {
/* 21:32 */       return MAPPER.writeValueAsString(data);
/* 22:   */     }
/* 23:   */     catch (JsonProcessingException e)
/* 24:   */     {
/* 25:35 */       e.printStackTrace();
/* 26:   */     }
/* 27:37 */     return null;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public static <T> T jsonToPojo(String jsonData, Class<T> beanType)
/* 31:   */   {
/* 32:   */     try
/* 33:   */     {
/* 34:49 */       return MAPPER.readValue(jsonData, beanType);
/* 35:   */     }
/* 36:   */     catch (Exception e)
/* 37:   */     {
/* 38:52 */       e.printStackTrace();
/* 39:   */     }
/* 40:54 */     return null;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public static <T> List<T> jsonToList(String jsonData, Class<T> beanType)
/* 44:   */   {
/* 45:66 */     JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, new Class[] { beanType });
/* 46:   */     try
/* 47:   */     {
/* 48:68 */       return (List)MAPPER.readValue(jsonData, javaType);
/* 49:   */     }
/* 50:   */     catch (Exception e)
/* 51:   */     {
/* 52:71 */       e.printStackTrace();
/* 53:   */     }
/* 54:74 */     return null;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public static <T> T jsonToPojonotnull(String jsonData, Class<T> beanType)
/* 58:   */   {
/* 59:   */     try
/* 60:   */     {
/* 61:79 */       MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer()
/* 62:   */       {
/* 63:   */         public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
/* 64:   */           throws IOException, JsonProcessingException
/* 65:   */         {
/* 66:88 */           jg.writeString("");
/* 67:   */         }
/* 68:90 */       });
/* 69:91 */       return MAPPER.readValue(jsonData, beanType);
/* 70:   */     }
/* 71:   */     catch (Exception e)
/* 72:   */     {
/* 73:94 */       e.printStackTrace();
/* 74:   */     }
/* 75:96 */     return null;
/* 76:   */   }
/* 77:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.JsonUtils
 * JD-Core Version:    0.7.0.1
 */