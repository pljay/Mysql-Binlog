/*  1:   */ package cn.wizool.android.servletmode;
/*  2:   */ 
/*  3:   */ public class classroom
/*  4:   */ {
/*  5:   */   private String ID;
/*  6:   */   private String ITEM_NAME;
/*  7:   */   private String ITEM_parentID;
/*  8:   */   private String ITEM_NUMBER;
/*  9:   */   private String ITEM_QUALITY;
/* 10:   */   private boolean isparent;
/* 11:   */   
/* 12:   */   public classroom() {}
/* 13:   */   
/* 14:   */   public boolean isIsparent()
/* 15:   */   {
/* 16:16 */     return this.isparent;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setIsparent(boolean isparent)
/* 20:   */   {
/* 21:19 */     this.isparent = isparent;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getID()
/* 25:   */   {
/* 26:22 */     return this.ID;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setID(String iD)
/* 30:   */   {
/* 31:25 */     this.ID = iD;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getITEM_NAME()
/* 35:   */   {
/* 36:28 */     return this.ITEM_NAME;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setITEM_NAME(String iTEM_NAME)
/* 40:   */   {
/* 41:31 */     this.ITEM_NAME = iTEM_NAME;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public String getITEM_parentID()
/* 45:   */   {
/* 46:34 */     return this.ITEM_parentID;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public String getITEM_NUMBER()
/* 50:   */   {
/* 51:37 */     return this.ITEM_NUMBER;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void setITEM_NUMBER(String iTEM_NUMBER)
/* 55:   */   {
/* 56:40 */     this.ITEM_NUMBER = iTEM_NUMBER;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public String getITEM_QUALITY()
/* 60:   */   {
/* 61:43 */     return this.ITEM_QUALITY;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void setITEM_QUALITY(String iTEM_QUALITY)
/* 65:   */   {
/* 66:46 */     this.ITEM_QUALITY = iTEM_QUALITY;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public classroom(String iD, String iTEM_NAME, String iTEM_parentID, String iTEM_NUMBER, String iTEM_QUALITY, boolean isparent)
/* 70:   */   {
/* 71:51 */     this.ID = iD;
/* 72:52 */     this.ITEM_NAME = iTEM_NAME;
/* 73:53 */     this.ITEM_parentID = iTEM_parentID;
/* 74:54 */     this.ITEM_NUMBER = iTEM_NUMBER;
/* 75:55 */     this.ITEM_QUALITY = iTEM_QUALITY;
/* 76:56 */     this.isparent = isparent;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public classroom(String iD, String iTEM_NAME, String iTEM_parentID, boolean isparent)
/* 80:   */   {
/* 81:60 */     this.ID = iD;
/* 82:61 */     this.ITEM_NAME = iTEM_NAME;
/* 83:62 */     this.ITEM_parentID = iTEM_parentID;
/* 84:63 */     this.isparent = isparent;
/* 85:   */   }
/* 86:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servletmode.classroom
 * JD-Core Version:    0.7.0.1
 */