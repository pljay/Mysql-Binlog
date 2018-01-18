/*   1:    */ package cn.wizool.android.servlet.classpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.servletmode.ClassMaster;
/*   5:    */ import cn.wizool.android.utils.JsonUtils;
/*   6:    */ import cn.wizool.android.utils.ResponseUtils;
/*   7:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   8:    */ import java.io.IOException;
/*   9:    */ import java.net.URLDecoder;
/*  10:    */ import java.sql.Connection;
/*  11:    */ import java.sql.PreparedStatement;
/*  12:    */ import java.sql.ResultSet;
/*  13:    */ import java.sql.SQLException;
/*  14:    */ import java.util.HashMap;
/*  15:    */ import java.util.Map;
/*  16:    */ import javax.servlet.ServletException;
/*  17:    */ import javax.servlet.http.HttpServlet;
/*  18:    */ import javax.servlet.http.HttpServletRequest;
/*  19:    */ import javax.servlet.http.HttpServletResponse;
/*  20:    */ import org.apache.commons.logging.Log;
/*  21:    */ import org.apache.commons.logging.LogFactory;
/*  22:    */ 
/*  23:    */ public class ClassMasterDemo
/*  24:    */   extends HttpServlet
/*  25:    */ {
/*  26: 35 */   final Log logger = LogFactory.getLog(ClassMasterDemo.class);
/*  27:    */   
/*  28:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  29:    */     throws ServletException, IOException
/*  30:    */   {
/*  31: 39 */     response.setCharacterEncoding("utf-8");
/*  32: 40 */     String code = request.getParameter("placeid");
/*  33:    */     
/*  34: 42 */     Connection conn = Mysql.getInstance().getConnection();
/*  35: 43 */     String str2 = request.getServerName();
/*  36: 44 */     int port = request.getServerPort();
/*  37: 45 */     String sss = request.getScheme();
/*  38: 46 */     String contextPath = request.getContextPath();
/*  39: 47 */     ClassMaster classMaster = null;
/*  40: 48 */     String sql = "SELECT\r\n\ta.ID,\r\n\tb.ITEM_NAME,\r\n\tb.ITEM_SEX,\r\n\tb.ITEM_PHONE,\r\n\tb.ITEM_EMAIL,\r\n\tb.ITEM_TITLE,\r\n\tb.ITEM_ADDRESS,\r\n\tb.ITEM_ORGNAME,\r\n\tb.ITEM_TEACHERPATH,\r\n\tb.ITEM_STAFFNUMBER,\r\n\ta.ITEM_CLASSNUMBER,\r\n\tc.ITEM_BANXUN\r\nFROM\r\n\t`tlk_教室管理` AS f\r\n\tLEFT JOIN `tlk_班级基础信息` AS a ON f.ITEM_NUMBER =a.ITEM_CLASSROOMID\r\n\tLEFT JOIN `tlk_教师基础信息` AS b  ON a.ITEM_CLASSTEACHER=b.ID \r\n\tLEFT JOIN `tlk_班训` AS c ON a.ID=c.ITEM_CLASSID AND c.ITEM_DISPLAY='是'\r\nWHERE\r\n\tf.ID = '" + 
/*  41:    */     
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59: 67 */       code + "'";
/*  60:    */     try
/*  61:    */     {
/*  62: 69 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/*  63: 70 */       ResultSet resultSet = prepareStatement.executeQuery();
/*  64: 71 */       while (resultSet.next())
/*  65:    */       {
/*  66: 72 */         Map<String, String> mapp = new HashMap();
/*  67: 73 */         String imagename = null;
/*  68: 74 */         String path = null;
/*  69: 75 */         if ((resultSet.getString("ITEM_TEACHERPATH") != null) && (resultSet.getString("ITEM_TEACHERPATH").length() > 10))
/*  70:    */         {
/*  71: 76 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_TEACHERPATH").substring(1, resultSet.getString("ITEM_TEACHERPATH").length() - 1), Map.class);
/*  72: 77 */           imagename = (String)mapp.get("name");
/*  73: 78 */           path = (String)mapp.get("path");
/*  74: 79 */           path = URLDecoder.decode(path, "utf-8");
/*  75: 80 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/*  76:    */         }
/*  77: 82 */         classMaster = new ClassMaster(resultSet.getString("ID"), 
/*  78: 83 */           resultSet.getString("ITEM_NAME"), resultSet.getString("ITEM_CLASSNUMBER"), 
/*  79: 84 */           imagename, path, resultSet.getString("ITEM_SEX"), 
/*  80: 85 */           resultSet.getString("ITEM_PHONE"), resultSet.getString("ITEM_EMAIL"), 
/*  81: 86 */           resultSet.getString("ITEM_TITLE"), resultSet.getString("ITEM_ADDRESS"), 
/*  82: 87 */           resultSet.getString("ITEM_ORGNAME"), resultSet.getString("ITEM_STAFFNUMBER"), resultSet.getString("ITEM_BANXUN"));
/*  83:    */       }
/*  84: 89 */       resultSet.close();
/*  85: 90 */       prepareStatement.close();
/*  86: 91 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "访问成功", classMaster)));
/*  87:    */     }
/*  88:    */     catch (SQLException e)
/*  89:    */     {
/*  90: 94 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/*  91: 95 */       e.printStackTrace();
/* 100:    */     }
/* 101:    */     finally
/* 102:    */     {
/* 103:    */       try
/* 104:    */       {
/* 105: 98 */         conn.close();
/* 106:    */       }
/* 107:    */       catch (SQLException e)
/* 108:    */       {
/* 109:101 */         e.printStackTrace();
/* 110:    */       }
/* 111:    */     }
/* 112:    */   }
/* 113:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassMasterDemo
 * JD-Core Version:    0.7.0.1
 */