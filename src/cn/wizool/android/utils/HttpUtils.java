/*  1:   */ package cn.wizool.android.utils;
/*  2:   */ 
/*  3:   */ import javax.servlet.http.HttpServletRequest;
/*  4:   */ 
/*  5:   */ public class HttpUtils
/*  6:   */ {
/*  7:   */   public static String getServerPath(HttpServletRequest request)
/*  8:   */   {
/*  9: 8 */     String serverName = request.getServerName();
/* 10: 9 */     int serverPort = request.getServerPort();
/* 11:10 */     String scheme = request.getScheme();
/* 12:11 */     String contextPath = request.getContextPath();
/* 13:12 */     return scheme + "://" + serverName + ":" + serverPort + contextPath;
/* 14:   */   }
/* 15:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.HttpUtils
 * JD-Core Version:    0.7.0.1
 */