/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.utils.HttpUtils;
/*  4:   */ import cn.wizool.android.utils.ResponseUtils;
/*  5:   */ import java.io.IOException;
/*  6:   */ import javax.servlet.ServletConfig;
/*  7:   */ import javax.servlet.ServletException;
/*  8:   */ import javax.servlet.annotation.WebServlet;
/*  9:   */ import javax.servlet.http.HttpServlet;
/* 10:   */ import javax.servlet.http.HttpServletRequest;
/* 11:   */ import javax.servlet.http.HttpServletResponse;
/* 12:   */ 
/* 13:   */ @WebServlet({"/facerecognition"})
/* 14:   */ public class FaceRecognitionServlet
/* 15:   */   extends HttpServlet
/* 16:   */ {
/* 17:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 18:   */     throws ServletException, IOException
/* 19:   */   {
/* 20:21 */     String serverpath = HttpUtils.getServerPath(request);
/* 21:22 */     serverpath = serverpath + "/uploads/feature/11e7-9f36-fd4cf9c9-befc-cfffeac8f141.txt";
/* 22:23 */     ResponseUtils.renderJson(response, serverpath);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void init(ServletConfig config)
/* 26:   */     throws ServletException
/* 27:   */   {
/* 28:29 */     super.init(config);
/* 29:   */   }
/* 30:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.FaceRecognitionServlet
 * JD-Core Version:    0.7.0.1
 */