/*  1:   */ package cn.wizool.android.utils;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.PrintWriter;
/*  5:   */ import javax.servlet.http.HttpServletResponse;
/*  6:   */ 
/*  7:   */ public class ResponseUtils
/*  8:   */ {
/*  9:   */   public static void renderJson(HttpServletResponse response, String text)
/* 10:   */   {
/* 11:11 */     render(response, "text/plain;charset=utf-8", text);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public static void renderJson(HttpServletResponse response, String content, String text)
/* 15:   */   {
/* 16:17 */     render(response, content, text);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static void render(HttpServletResponse response, String content, String text)
/* 20:   */   {
/* 21:23 */     response.setContentType(content);
/* 22:24 */     response.setCharacterEncoding("UTF-8");
/* 23:   */     
/* 24:26 */     response.setHeader("Pragma", "No-cache");
/* 25:27 */     response.setHeader("Cache-Control", "no-cache");
/* 26:   */     
/* 27:29 */     response.setDateHeader("Expires", 0L);
/* 28:   */     try
/* 29:   */     {
/* 30:32 */       response.getWriter().write(text);
/* 31:   */     }
/* 32:   */     catch (IOException e)
/* 33:   */     {
/* 34:35 */       e.printStackTrace();
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.ResponseUtils
 * JD-Core Version:    0.7.0.1
 */