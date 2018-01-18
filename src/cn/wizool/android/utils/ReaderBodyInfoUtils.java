/*  1:   */ package cn.wizool.android.utils;
/*  2:   */ 
/*  3:   */ import java.io.BufferedReader;
/*  4:   */ import java.io.IOException;
/*  5:   */ import java.io.InputStreamReader;
/*  6:   */ import javax.servlet.http.HttpServletRequest;
/*  7:   */ 
/*  8:   */ public class ReaderBodyInfoUtils
/*  9:   */ {
/* 10:   */   public static String ReadBody(HttpServletRequest request)
/* 11:   */   {
/* 12:10 */     StringBuffer sb = null;
/* 13:   */     try
/* 14:   */     {
/* 15:13 */       BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
/* 16:14 */       sb = new StringBuffer("");
/* 17:   */       String temp;
/* 18:16 */       while ((temp = br.readLine()) != null)
/* 19:   */       {
/* 21:17 */         sb.append(temp);
/* 22:   */       }
/* 23:19 */       br.close();
/* 24:   */     }
/* 25:   */     catch (IOException e)
/* 26:   */     {
/* 27:22 */       e.printStackTrace();
/* 28:   */     }
/* 29:24 */     return sb.toString();
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int hashCode()
/* 33:   */   {
/* 34:29 */     return super.hashCode();
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean equals(Object obj)
/* 38:   */   {
/* 39:35 */     return super.equals(obj);
/* 40:   */   }
/* 41:   */   
/* 42:   */   protected Object clone()
/* 43:   */     throws CloneNotSupportedException
/* 44:   */   {
/* 45:41 */     return super.clone();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String toString()
/* 49:   */   {
/* 50:47 */     return super.toString();
/* 51:   */   }
/* 52:   */   
/* 53:   */   protected void finalize()
/* 54:   */     throws Throwable
/* 55:   */   {
/* 56:53 */     super.finalize();
/* 57:   */   }
/* 58:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.ReaderBodyInfoUtils
 * JD-Core Version:    0.7.0.1
 */