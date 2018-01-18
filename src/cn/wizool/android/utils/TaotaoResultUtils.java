/*   1:    */ package cn.wizool.android.utils;
/*   2:    */ 
/*   3:    */ import com.fasterxml.jackson.databind.JsonNode;
/*   4:    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*   5:    */ import com.fasterxml.jackson.databind.type.TypeFactory;
/*   6:    */ import java.util.List;
/*   7:    */ 
/*   8:    */ public class TaotaoResultUtils
/*   9:    */ {
/*  10: 14 */   private static final ObjectMapper MAPPER = new ObjectMapper();
/*  11:    */   private Integer code;
/*  12:    */   private String msg;
/*  13:    */   private Object items;
/*  14:    */   
/*  15:    */   public static TaotaoResultUtils build(Integer code, String msg, Object items)
/*  16:    */   {
/*  17: 26 */     return new TaotaoResultUtils(code, msg, items);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public static TaotaoResultUtils ok(Object items)
/*  21:    */   {
/*  22: 30 */     return new TaotaoResultUtils(items);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public static TaotaoResultUtils ok()
/*  26:    */   {
/*  27: 34 */     return new TaotaoResultUtils(null);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public TaotaoResultUtils() {}
/*  31:    */   
/*  32:    */   public static TaotaoResultUtils build(Integer code, String msg)
/*  33:    */   {
/*  34: 42 */     return new TaotaoResultUtils(code, msg, null);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public TaotaoResultUtils(Integer code, String msg, Object items)
/*  38:    */   {
/*  39: 46 */     this.code = code;
/*  40: 47 */     this.msg = msg;
/*  41: 48 */     this.items = items;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public TaotaoResultUtils(Object items)
/*  45:    */   {
/*  46: 52 */     this.code = Integer.valueOf(200);
/*  47: 53 */     this.msg = "OK";
/*  48: 54 */     this.items = items;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public Integer getcode()
/*  52:    */   {
/*  53: 62 */     return this.code;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void setcode(Integer code)
/*  57:    */   {
/*  58: 66 */     this.code = code;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public String getMsg()
/*  62:    */   {
/*  63: 70 */     return this.msg;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setMsg(String msg)
/*  67:    */   {
/*  68: 74 */     this.msg = msg;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public Object getitems()
/*  72:    */   {
/*  73: 78 */     return this.items;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setitems(Object items)
/*  77:    */   {
/*  78: 82 */     this.items = items;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static TaotaoResultUtils formatToPojo(String jsonitems, Class<?> clazz)
/*  82:    */   {
/*  83:    */     try
/*  84:    */     {
/*  85: 94 */       if (clazz == null) {
/*  86: 95 */         return (TaotaoResultUtils)MAPPER.readValue(jsonitems, TaotaoResultUtils.class);
/*  87:    */       }
/*  88: 97 */       JsonNode jsonNode = MAPPER.readTree(jsonitems);
/*  89: 98 */       JsonNode items = jsonNode.get("items");
/*  90: 99 */       Object obj = null;
/*  91:100 */       if (clazz != null) {
/*  92:101 */         if (items.isObject()) {
/*  93:102 */           obj = MAPPER.readValue(items.traverse(), clazz);
/*  94:103 */         } else if (items.isTextual()) {
/*  95:104 */           obj = MAPPER.readValue(items.asText(), clazz);
/*  96:    */         }
/*  97:    */       }
/*  98:107 */       return build(Integer.valueOf(jsonNode.get("code").intValue()), jsonNode.get("msg").asText(), obj);
/*  99:    */     }
/* 100:    */     catch (Exception e) {}
/* 101:109 */     return null;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static TaotaoResultUtils format(String json)
/* 105:    */   {
/* 106:    */     try
/* 107:    */     {
/* 108:121 */       return (TaotaoResultUtils)MAPPER.readValue(json, TaotaoResultUtils.class);
/* 109:    */     }
/* 110:    */     catch (Exception e)
/* 111:    */     {
/* 112:123 */       e.printStackTrace();
/* 113:    */     }
/* 114:125 */     return null;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public static TaotaoResultUtils formatToList(String jsonitems, Class<?> clazz)
/* 118:    */   {
/* 119:    */     try
/* 120:    */     {
/* 121:137 */       JsonNode jsonNode = MAPPER.readTree(jsonitems);
/* 122:138 */       JsonNode items = jsonNode.get("items");
/* 123:139 */       Object obj = null;
/* 124:140 */       if ((items.isArray()) && (items.size() > 0)) {
/* 125:141 */         obj = MAPPER.readValue(items.traverse(), 
/* 126:142 */           MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
/* 127:    */       }
/* 128:144 */       return build(Integer.valueOf(jsonNode.get("code").intValue()), jsonNode.get("msg").asText(), obj);
/* 129:    */     }
/* 130:    */     catch (Exception e) {}
/* 131:146 */     return null;
/* 132:    */   }
/* 133:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.TaotaoResultUtils
 * JD-Core Version:    0.7.0.1
 */