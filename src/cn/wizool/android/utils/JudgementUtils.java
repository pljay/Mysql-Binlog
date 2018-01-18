/*  1:   */ package cn.wizool.android.utils;
/*  2:   */ 
/*  3:   */ import java.util.Calendar;
/*  4:   */ import java.util.regex.Matcher;
/*  5:   */ import java.util.regex.Pattern;
/*  6:   */ 
/*  7:   */ public class JudgementUtils
/*  8:   */ {
/*  9:   */   public static boolean valiDateTimeWithLongFormat(String timeStr)
/* 10:   */   {
/* 11:11 */     String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
/* 12:12 */     Pattern pattern = Pattern.compile(format);
/* 13:13 */     Matcher matcher = pattern.matcher(timeStr);
/* 14:14 */     if (matcher.matches())
/* 15:   */     {
/* 16:15 */       pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
/* 17:16 */       matcher = pattern.matcher(timeStr);
/* 18:17 */       if (matcher.matches())
/* 19:   */       {
/* 20:18 */         int y = Integer.valueOf(matcher.group(1)).intValue();
/* 21:19 */         int m = Integer.valueOf(matcher.group(2)).intValue();
/* 22:20 */         int d = Integer.valueOf(matcher.group(3)).intValue();
/* 23:21 */         if (d > 28)
/* 24:   */         {
/* 25:22 */           Calendar c = Calendar.getInstance();
/* 26:23 */           c.set(y, m - 1, 1);
/* 27:24 */           int lastDay = c.getActualMaximum(5);
/* 28:25 */           return lastDay >= d;
/* 29:   */         }
/* 30:   */       }
/* 31:28 */       return true;
/* 32:   */     }
/* 33:30 */     return false;
/* 34:   */   }
/* 35:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.JudgementUtils
 * JD-Core Version:    0.7.0.1
 */