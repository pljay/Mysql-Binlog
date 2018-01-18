/*  1:   */ package cn.wizool.android.or.model;
/*  2:   */ 
/*  3:   */ public class BinlogInfo
/*  4:   */ {
/*  5:   */   private String binlogName;
/*  6:   */   private Long fileSize;
/*  7:   */   
/*  8:   */   public String getBinlogName()
/*  9:   */   {
/* 10: 7 */     return this.binlogName;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public void setBinlogName(String binlogName)
/* 14:   */   {
/* 15:10 */     this.binlogName = binlogName;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public Long getFileSize()
/* 19:   */   {
/* 20:13 */     return this.fileSize;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setFileSize(Long fileSize)
/* 24:   */   {
/* 25:16 */     this.fileSize = fileSize;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public BinlogInfo(String binlogName, Long fileSize)
/* 29:   */   {
/* 30:20 */     this.binlogName = binlogName;
/* 31:21 */     this.fileSize = fileSize;
/* 32:   */   }
/* 33:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.or.model.BinlogInfo
 * JD-Core Version:    0.7.0.1
 */