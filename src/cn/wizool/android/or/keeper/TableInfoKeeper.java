/*  1:   */ package cn.wizool.android.or.keeper;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.com.google.code.or.binlog.impl.event.TableMapEvent;
/*  4:   */ import cn.wizool.android.com.google.code.or.common.glossary.column.StringColumn;
/*  5:   */ import cn.wizool.android.or.MysqlConnection;
/*  6:   */ import cn.wizool.android.or.model.ColumnInfo;
/*  7:   */ import cn.wizool.android.or.model.TableInfo;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Map;
/* 10:   */ import java.util.concurrent.ConcurrentHashMap;
/* 11:   */ import org.slf4j.Logger;
/* 12:   */ import org.slf4j.LoggerFactory;
/* 13:   */ 
/* 14:   */ public class TableInfoKeeper
/* 15:   */ {
/* 16:20 */   private static final Logger logger = LoggerFactory.getLogger(TableInfoKeeper.class);
/* 17:22 */   private static Map<Long, TableInfo> tabledIdMap = new ConcurrentHashMap();
/* 18:26 */   private static Map<String, List<ColumnInfo>> columnsMap = MysqlConnection.getColumns();
/* 19:   */   
/* 20:   */   public static void saveTableIdMap(TableMapEvent tme)
/* 21:   */   {
/* 22:30 */     long tableId = tme.getTableId();
/* 23:31 */     tabledIdMap.remove(Long.valueOf(tableId));
/* 24:   */     
/* 25:33 */     TableInfo table = new TableInfo();
/* 26:34 */     table.setDatabaseName(tme.getDatabaseName().toString());
/* 27:35 */     table.setTableName(tme.getTableName().toString());
/* 28:36 */     table.setFullName(tme.getDatabaseName() + "." + tme.getTableName());
/* 29:   */     
/* 30:38 */     tabledIdMap.put(Long.valueOf(tableId), table);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static synchronized void refreshColumnsMap()
/* 34:   */   {
/* 35:42 */     Map<String, List<ColumnInfo>> map = MysqlConnection.getColumns();
/* 36:43 */     if (map.size() > 0) {
/* 37:45 */       columnsMap = map;
/* 38:   */     } else {
/* 39:50 */       logger.error("refresh columnsMap error.");
/* 40:   */     }
/* 41:   */   }
/* 42:   */   
/* 43:   */   public static TableInfo getTableInfo(long tableId)
/* 44:   */   {
/* 45:55 */     return (TableInfo)tabledIdMap.get(Long.valueOf(tableId));
/* 46:   */   }
/* 47:   */   
/* 48:   */   public static List<ColumnInfo> getColumns(String fullName)
/* 49:   */   {
/* 50:59 */     return (List)columnsMap.get(fullName);
/* 51:   */   }
/* 52:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.keeper.TableInfoKeeper
 * JD-Core Version:    0.7.0.1
 */