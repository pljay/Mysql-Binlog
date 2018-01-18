/*  1:   */ package cn.wizool.android.servletmode;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ 
/*  5:   */ public class CurriculumSchedule
/*  6:   */ {
/*  7:   */   private String week;
/*  8:   */   private List<Kecheng> courses;
/*  9:   */   
/* 10:   */   public String getWeek()
/* 11:   */   {
/* 12:10 */     return this.week;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setWeek(String week)
/* 16:   */   {
/* 17:13 */     this.week = week;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public List<Kecheng> getCourses()
/* 21:   */   {
/* 22:17 */     return this.courses;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setCourses(List<Kecheng> courses)
/* 26:   */   {
/* 27:20 */     this.courses = courses;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public CurriculumSchedule(String week, List<Kecheng> courses)
/* 31:   */   {
/* 32:25 */     this.week = week;
/* 33:26 */     this.courses = courses;
/* 34:   */   }
/* 35:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servletmode.CurriculumSchedule
 * JD-Core Version:    0.7.0.1
 */