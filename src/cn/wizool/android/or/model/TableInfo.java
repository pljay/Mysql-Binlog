/*  1:   */ package cn.wizool.android.or.model;
/*  2:   */ 
/*  3:   */ public class TableInfo
/*  4:   */ {
/*  5:   */   private String databaseName;
/*  6:   */   private String tableName;
/*  7:   */   private String fullName;
/*  8:   */   
/*  9:   */   public boolean equals(Object o)
/* 10:   */   {
/* 11:13 */     if (this == o) {
/* 12:14 */       return true;
/* 13:   */     }
/* 14:15 */     if ((o == null) || (getClass() != o.getClass())) {
/* 15:16 */       return false;
/* 16:   */     }
/* 17:17 */     TableInfo tableInfo = (TableInfo)o;
/* 18:18 */     if (!this.databaseName.equals(tableInfo.getDatabaseName())) {
/* 19:19 */       return false;
/* 20:   */     }
/* 21:20 */     if (!this.tableName.equals(tableInfo.getTableName())) {
/* 22:21 */       return false;
/* 23:   */     }
/* 24:22 */     if (!this.fullName.equals(tableInfo.getFullName())) {
/* 25:23 */       return false;
/* 26:   */     }
/* 27:24 */     return true;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getDatabaseName()
/* 31:   */   {
/* 32:28 */     return this.databaseName;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setDatabaseName(String databaseName)
/* 36:   */   {
/* 37:32 */     this.databaseName = databaseName;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getTableName()
/* 41:   */   {
/* 42:36 */     return this.tableName;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setTableName(String tableName)
/* 46:   */   {
/* 47:40 */     this.tableName = tableName;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getFullName()
/* 51:   */   {
/* 52:44 */     return this.fullName;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void setFullName(String fullName)
/* 56:   */   {
/* 57:48 */     this.fullName = fullName;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int hashCode()
/* 61:   */   {
/* 62:53 */     int result = this.tableName.hashCode();
/* 63:54 */     result = 31 * result + this.databaseName.hashCode();
/* 64:55 */     result = 31 * result + this.fullName.hashCode();
/* 65:56 */     return result;
/* 66:   */   }
/* 67:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.model.TableInfo
 * JD-Core Version:    0.7.0.1
 */