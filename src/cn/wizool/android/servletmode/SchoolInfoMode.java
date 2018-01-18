/*  1:   */ package cn.wizool.android.servletmode;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ 
/*  5:   */ public class SchoolInfoMode
/*  6:   */ {
/*  7:   */   private String id;
/*  8:   */   private String school_name;
/*  9:   */   private String school_motto;
/* 10:   */   private List<?> school_picture;
/* 11:   */   
/* 12:   */   public List<?> getSchool_picture()
/* 13:   */   {
/* 14:16 */     return this.school_picture;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void setSchool_picture(List<?> school_picture)
/* 18:   */   {
/* 19:20 */     this.school_picture = school_picture;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public String getId()
/* 23:   */   {
/* 24:24 */     return this.id;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setId(String id)
/* 28:   */   {
/* 29:27 */     this.id = id;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getSchool_name()
/* 33:   */   {
/* 34:30 */     return this.school_name;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setSchool_name(String school_name)
/* 38:   */   {
/* 39:33 */     this.school_name = school_name;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getSchool_motto()
/* 43:   */   {
/* 44:36 */     return this.school_motto;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setSchool_motto(String school_motto)
/* 48:   */   {
/* 49:39 */     this.school_motto = school_motto;
/* 50:   */   }
/* 51:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servletmode.SchoolInfoMode
 * JD-Core Version:    0.7.0.1
 */