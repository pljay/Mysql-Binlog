/*  1:   */ package cn.wizool.android.servlet.generalpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.utils.JsonUtils;
/*  5:   */ import cn.wizool.android.utils.ResponseUtils;
/*  6:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  7:   */ import com.fasterxml.jackson.databind.ObjectMapper;
/*  8:   */ import java.io.IOException;
/*  9:   */ import java.io.PrintStream;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import java.sql.PreparedStatement;
/* 12:   */ import java.sql.ResultSet;
/* 13:   */ import java.sql.SQLException;
/* 14:   */ import java.util.HashMap;
/* 15:   */ import java.util.Map;
/* 16:   */ import javax.servlet.ServletException;
/* 17:   */ import javax.servlet.http.HttpServlet;
/* 18:   */ import javax.servlet.http.HttpServletRequest;
/* 19:   */ import javax.servlet.http.HttpServletResponse;
/* 20:   */ 
/* 21:   */ public class UpdateAppServlet
/* 22:   */   extends HttpServlet
/* 23:   */ {
/* 24:   */   private static final long serialVersionUID = 1L;
/* 25:   */   
/* 26:   */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:42 */     String realPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
/* 30:43 */     ObjectMapper mapper = new ObjectMapper();
/* 31:44 */     Connection conn = Mysql.getInstance().getConnection();
/* 32:45 */     PreparedStatement pstmt = null;
/* 33:46 */     ResultSet resultSet = null;
/* 34:47 */     Map<String, String> map = null;
/* 35:48 */     Map<String, String> map2 = new HashMap();
/* 36:49 */     String sql = "select * from tlk_上传安卓系统版本  order by ITEM_EDITION desc";
/* 37:   */     try
/* 38:   */     {
/* 39:51 */       pstmt = conn.prepareStatement(sql);
/* 40:52 */       resultSet = pstmt.executeQuery();
/* 41:53 */       if (resultSet.next())
/* 42:   */       {
/* 43:54 */         map = new HashMap();
/* 44:55 */         if (!resultSet.getString("ITEM_UPDATEPATH").equals(""))
/* 45:   */         {
/* 46:56 */           map2 = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_UPDATEPATH").substring(1, resultSet.getString("ITEM_UPDATEPATH").length() - 1), Map.class);
/* 47:57 */           map.put("laster", resultSet.getString("ITEM_NAME"));
/* 48:58 */           map.put("url", realPath + (String)map2.get("path"));
/* 49:59 */           System.out.println(realPath + (String)map2.get("path"));
/* 50:   */         }
/* 51:61 */         response.addHeader("Content-Disposition", "inline;filename=" + (String)map2.get("path"));
/* 52:   */       }
/* 53:65 */       pstmt.close();
/* 54:66 */       resultSet.close();
/* 55:67 */       ResponseUtils.renderJson(response, "application/json", mapper.writeValueAsString(TaotaoResultUtils.build(Integer.valueOf(200), "ok", map)));
/* 56:   */     }
/* 57:   */     catch (SQLException e)
/* 58:   */     {
/* 59:71 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 60:72 */       e.printStackTrace();
/* 69:   */     }
/* 70:   */     finally
/* 71:   */     {
/* 72:   */       try
/* 73:   */       {
/* 74:75 */         conn.close();
/* 75:   */       }
/* 76:   */       catch (SQLException e)
/* 77:   */       {
/* 78:78 */         e.printStackTrace();
/* 79:   */       }
/* 80:   */     }
/* 81:   */   }
/* 82:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.generalpackage.UpdateAppServlet
 * JD-Core Version:    0.7.0.1
 */