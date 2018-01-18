/*   1:    */ package cn.wizool.android.or;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.com.google.code.or.binlog.BinlogEventV4;
/*   4:    */ import cn.wizool.android.com.google.code.or.binlog.BinlogEventV4Header;
/*   5:    */ import cn.wizool.android.com.google.code.or.binlog.impl.event.AbstractBinlogEventV4;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.Date;
/*   9:    */ import java.util.Map;
/*  10:    */ import java.util.concurrent.atomic.AtomicLong;
/*  11:    */ 
/*  12:    */ public class CDCEvent
/*  13:    */ {
/*  14: 16 */   private long eventId = 0L;
/*  15: 17 */   private String databaseName = null;
/*  16: 18 */   private String tableName = null;
/*  17: 19 */   private int eventType = 0;
/*  18:    */   private String timestamp;
/*  19:    */   private String timestampReceipt;
/*  20: 22 */   private String binlogName = null;
/*  21: 23 */   private long position = 0L;
/*  22: 24 */   private long nextPostion = 0L;
/*  23: 25 */   private long serverId = 0L;
/*  24: 26 */   private Map<String, String> before = null;
/*  25: 27 */   private Map<String, String> after = null;
/*  26: 28 */   private Boolean isDdl = null;
/*  27: 29 */   private String sql = null;
/*  28: 31 */   private static AtomicLong uuid = new AtomicLong(0L);
/*  29:    */   
/*  30:    */   public CDCEvent() {}
/*  31:    */   
/*  32:    */   public CDCEvent(long eventId, String databaseName, String tableName, int eventType, String timestamp, String timestampReceipt, String binlogName, long position, long nextPostion, long serverId, Map<String, String> before, Map<String, String> after, Boolean isDdl, String sql)
/*  33:    */   {
/*  34: 38 */     this.eventId = eventId;
/*  35: 39 */     this.databaseName = databaseName;
/*  36: 40 */     this.tableName = tableName;
/*  37: 41 */     this.eventType = eventType;
/*  38: 42 */     this.timestamp = timestamp;
/*  39: 43 */     this.timestampReceipt = timestampReceipt;
/*  40: 44 */     this.binlogName = binlogName;
/*  41: 45 */     this.position = position;
/*  42: 46 */     this.nextPostion = nextPostion;
/*  43: 47 */     this.serverId = serverId;
/*  44: 48 */     this.before = before;
/*  45: 49 */     this.after = after;
/*  46: 50 */     this.isDdl = isDdl;
/*  47: 51 */     this.sql = sql;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public CDCEvent(AbstractBinlogEventV4 are, String databaseName, String tableName)
/*  51:    */   {
/*  52: 55 */     init(are);
/*  53: 56 */     this.databaseName = databaseName;
/*  54: 57 */     this.tableName = tableName;
/*  55:    */   }
/*  56:    */   
/*  57:    */   private void init(BinlogEventV4 be)
/*  58:    */   {
/*  59: 61 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  60: 62 */     this.eventId = uuid.getAndAdd(1L);
/*  61: 63 */     BinlogEventV4Header header = be.getHeader();
/*  62: 64 */     this.timestamp = simpleDateFormat.format(new Date(header.getTimestamp()));
/*  63: 65 */     this.eventType = header.getEventType();
/*  64: 66 */     this.serverId = header.getServerId();
/*  65: 67 */     this.timestampReceipt = simpleDateFormat.format(Long.valueOf(header.getTimestampOfReceipt()));
/*  66: 68 */     System.out.println(header.getTimestampOfReceipt());
/*  67: 69 */     this.position = header.getPosition();
/*  68: 70 */     this.nextPostion = header.getNextPosition();
/*  69: 71 */     this.binlogName = header.getBinlogFileName();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public String toString()
/*  73:    */   {
/*  74: 76 */     StringBuilder builder = new StringBuilder();
/*  75: 77 */     builder.append("{ eventId:").append(this.eventId);
/*  76: 78 */     builder.append(",databaseName:").append(this.databaseName);
/*  77: 79 */     builder.append(",tableName:").append(this.tableName);
/*  78: 80 */     builder.append(",eventType:").append(this.eventType);
/*  79: 81 */     builder.append(",timestamp:").append(this.timestamp);
/*  80: 82 */     builder.append(",timestampReceipt:").append(this.timestampReceipt);
/*  81: 83 */     builder.append(",binlogName:").append(this.binlogName);
/*  82: 84 */     builder.append(",position:").append(this.position);
/*  83: 85 */     builder.append(",nextPostion:").append(this.nextPostion);
/*  84: 86 */     builder.append(",serverId:").append(this.serverId);
/*  85: 87 */     builder.append(",isDdl:").append(this.isDdl);
/*  86: 88 */     builder.append(",sql:").append(this.sql);
/*  87: 89 */     builder.append(",before:").append(this.before);
/*  88: 90 */     builder.append(",after:").append(this.after).append("}");
/*  89: 91 */     return builder.toString();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public long getEventId()
/*  93:    */   {
/*  94: 95 */     return this.eventId;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void setEventId(long eventId)
/*  98:    */   {
/*  99: 99 */     this.eventId = eventId;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public String getDatabaseName()
/* 103:    */   {
/* 104:103 */     return this.databaseName;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setDatabaseName(String databaseName)
/* 108:    */   {
/* 109:107 */     this.databaseName = databaseName;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public String getTableName()
/* 113:    */   {
/* 114:111 */     return this.tableName;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setTableName(String tableName)
/* 118:    */   {
/* 119:115 */     this.tableName = tableName;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public int getEventType()
/* 123:    */   {
/* 124:119 */     return this.eventType;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setEventType(int eventType)
/* 128:    */   {
/* 129:123 */     this.eventType = eventType;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public String getTimestamp()
/* 133:    */   {
/* 134:127 */     return this.timestamp;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void setTimestamp(String timestamp)
/* 138:    */   {
/* 139:131 */     this.timestamp = timestamp;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public String getTimestampReceipt()
/* 143:    */   {
/* 144:135 */     return this.timestampReceipt;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public void setTimestampReceipt(String timestampReceipt)
/* 148:    */   {
/* 149:139 */     this.timestampReceipt = timestampReceipt;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String getBinlogName()
/* 153:    */   {
/* 154:143 */     return this.binlogName;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void setBinlogName(String binlogName)
/* 158:    */   {
/* 159:147 */     this.binlogName = binlogName;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public long getPosition()
/* 163:    */   {
/* 164:151 */     return this.position;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public void setPosition(long position)
/* 168:    */   {
/* 169:155 */     this.position = position;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public long getNextPostion()
/* 173:    */   {
/* 174:159 */     return this.nextPostion;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public void setNextPostion(long nextPostion)
/* 178:    */   {
/* 179:163 */     this.nextPostion = nextPostion;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public long getServerId()
/* 183:    */   {
/* 184:167 */     return this.serverId;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setServerId(long serverId)
/* 188:    */   {
/* 189:171 */     this.serverId = serverId;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public Map<String, String> getBefore()
/* 193:    */   {
/* 194:175 */     return this.before;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void setBefore(Map<String, String> before)
/* 198:    */   {
/* 199:179 */     this.before = before;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public Map<String, String> getAfter()
/* 203:    */   {
/* 204:183 */     return this.after;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void setAfter(Map<String, String> after)
/* 208:    */   {
/* 209:187 */     this.after = after;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public Boolean getIsDdl()
/* 213:    */   {
/* 214:191 */     return this.isDdl;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void setIsDdl(Boolean isDdl)
/* 218:    */   {
/* 219:195 */     this.isDdl = isDdl;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public String getSql()
/* 223:    */   {
/* 224:199 */     return this.sql;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void setSql(String sql)
/* 228:    */   {
/* 229:203 */     this.sql = sql;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public static AtomicLong getUuid()
/* 233:    */   {
/* 234:207 */     return uuid;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public static void setUuid(AtomicLong uuid)
/* 238:    */   {
/* 239:211 */     uuid = uuid;
/* 240:    */   }
/* 241:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.CDCEvent
 * JD-Core Version:    0.7.0.1
 */