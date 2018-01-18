/*  1:   */ package cn.wizool.android.or.model;
/*  2:   */ 
/*  3:   */ public class ColumnInfo
/*  4:   */ {
/*  5:   */   private String name;
/*  6:   */   private String type;
/*  7:   */   
/*  8:   */   public String getName()
/*  9:   */   {
/* 10: 7 */     return this.name;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public void setName(String name)
/* 14:   */   {
/* 15:10 */     this.name = name;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getType()
/* 19:   */   {
/* 20:13 */     return this.type;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setType(String type)
/* 24:   */   {
/* 25:16 */     this.type = type;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public ColumnInfo(String name, String type)
/* 29:   */   {
/* 30:20 */     this.name = name;
/* 31:21 */     this.type = type;
/* 32:   */   }
/* 33:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.model.ColumnInfo
 * JD-Core Version:    0.7.0.1
 */