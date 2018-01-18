/*   1:    */ package cn.wizool.android.test;
/*   2:    */ 
/*   3:    */ public class testbean
/*   4:    */ {
/*   5:    */   private String eventId;
/*   6:    */   private String databaseName;
/*   7:    */   private String tableName;
/*   8:    */   private String eventType;
/*   9:    */   private String timestamp;
/*  10:    */   private String timestampReceipt;
/*  11:    */   private String binlogName;
/*  12:    */   private String position;
/*  13:    */   private String nextPostion;
/*  14:    */   private String serverId;
/*  15:    */   private String before;
/*  16:    */   private String after;
/*  17:    */   private String isDdl;
/*  18:    */   private String sql;
/*  19:    */   
/*  20:    */   public testbean(String eventId, String databaseName, String tableName, String eventType, String timestamp, String timestampReceipt, String binlogName, String position, String nextPostion, String serverId, String before, String after, String isDdl, String sql)
/*  21:    */   {
/*  22: 24 */     this.eventId = eventId;
/*  23: 25 */     this.databaseName = databaseName;
/*  24: 26 */     this.tableName = tableName;
/*  25: 27 */     this.eventType = eventType;
/*  26: 28 */     this.timestamp = timestamp;
/*  27: 29 */     this.timestampReceipt = timestampReceipt;
/*  28: 30 */     this.binlogName = binlogName;
/*  29: 31 */     this.position = position;
/*  30: 32 */     this.nextPostion = nextPostion;
/*  31: 33 */     this.serverId = serverId;
/*  32: 34 */     this.before = before;
/*  33: 35 */     this.after = after;
/*  34: 36 */     this.isDdl = isDdl;
/*  35: 37 */     this.sql = sql;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public String getEventId()
/*  39:    */   {
/*  40: 40 */     return this.eventId;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void setEventId(String eventId)
/*  44:    */   {
/*  45: 43 */     this.eventId = eventId;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public String getDatabaseName()
/*  49:    */   {
/*  50: 46 */     return this.databaseName;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setDatabaseName(String databaseName)
/*  54:    */   {
/*  55: 49 */     this.databaseName = databaseName;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public String getTableName()
/*  59:    */   {
/*  60: 52 */     return this.tableName;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setTableName(String tableName)
/*  64:    */   {
/*  65: 55 */     this.tableName = tableName;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public String getEventType()
/*  69:    */   {
/*  70: 58 */     return this.eventType;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void setEventType(String eventType)
/*  74:    */   {
/*  75: 61 */     this.eventType = eventType;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public String getTimestamp()
/*  79:    */   {
/*  80: 64 */     return this.timestamp;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setTimestamp(String timestamp)
/*  84:    */   {
/*  85: 67 */     this.timestamp = timestamp;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public String getTimestampReceipt()
/*  89:    */   {
/*  90: 70 */     return this.timestampReceipt;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setTimestampReceipt(String timestampReceipt)
/*  94:    */   {
/*  95: 73 */     this.timestampReceipt = timestampReceipt;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public String getBinlogName()
/*  99:    */   {
/* 100: 76 */     return this.binlogName;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setBinlogName(String binlogName)
/* 104:    */   {
/* 105: 79 */     this.binlogName = binlogName;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public String getPosition()
/* 109:    */   {
/* 110: 82 */     return this.position;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setPosition(String position)
/* 114:    */   {
/* 115: 85 */     this.position = position;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public String getNextPostion()
/* 119:    */   {
/* 120: 88 */     return this.nextPostion;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void setNextPostion(String nextPostion)
/* 124:    */   {
/* 125: 91 */     this.nextPostion = nextPostion;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public String getServerId()
/* 129:    */   {
/* 130: 94 */     return this.serverId;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setServerId(String serverId)
/* 134:    */   {
/* 135: 97 */     this.serverId = serverId;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public String getBefore()
/* 139:    */   {
/* 140:100 */     return this.before;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void setBefore(String before)
/* 144:    */   {
/* 145:103 */     this.before = before;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public String getAfter()
/* 149:    */   {
/* 150:106 */     return this.after;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void setAfter(String after)
/* 154:    */   {
/* 155:109 */     this.after = after;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public String getIsDdl()
/* 159:    */   {
/* 160:112 */     return this.isDdl;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setIsDdl(String isDdl)
/* 164:    */   {
/* 165:115 */     this.isDdl = isDdl;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public String getSql()
/* 169:    */   {
/* 170:118 */     return this.sql;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setSql(String sql)
/* 174:    */   {
/* 175:121 */     this.sql = sql;
/* 176:    */   }
/* 177:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.test.testbean
 * JD-Core Version:    0.7.0.1
 */