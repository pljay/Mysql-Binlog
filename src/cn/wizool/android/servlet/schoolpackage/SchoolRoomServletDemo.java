/*  1:   */ package cn.wizool.android.servlet.schoolpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.servletmode.classroom;
/*  5:   */ import cn.wizool.android.utils.JsonUtils;
/*  6:   */ import cn.wizool.android.utils.ResponseUtils;
/*  7:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  8:   */ import java.io.IOException;
/*  9:   */ import java.io.PrintStream;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import java.sql.PreparedStatement;
/* 12:   */ import java.sql.ResultSet;
/* 13:   */ import java.sql.SQLException;
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.List;
/* 16:   */ import javax.servlet.ServletException;
/* 17:   */ import javax.servlet.http.HttpServlet;
/* 18:   */ import javax.servlet.http.HttpServletRequest;
/* 19:   */ import javax.servlet.http.HttpServletResponse;
/* 20:   */ 
/* 21:   */ public class SchoolRoomServletDemo
/* 22:   */   extends HttpServlet
/* 23:   */ {
/* 24:   */   private static final long serialVersionUID = 1L;
/* 25:   */   
/* 26:   */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/* 27:   */     throws ServletException, IOException
/* 28:   */   {
/* 29:39 */     String id = req.getParameter("schoolid");
/* 30:40 */     System.out.println("111111" + id);
/* 31:41 */     Connection conn = Mysql.getInstance().getConnection();
/* 32:42 */     List<classroom> classrooms = new ArrayList();
/* 33:   */     try
/* 34:   */     {
/* 35:45 */       String sql = "SELECT ID,ITEM_NAME,ITEM_SJBH FROM `tlk_教师管理树形` where item_schoolid='" + id + "'";
/* 36:   */       
/* 37:47 */       PreparedStatement pst = conn.prepareStatement(sql);
/* 38:48 */       ResultSet rs = pst.executeQuery();
/* 39:49 */       classroom c = null;
/* 40:50 */       while (rs.next())
/* 41:   */       {
/* 42:51 */         c = new classroom(rs.getString("ID"), rs.getString("ITEM_NAME"), rs.getString("ITEM_SJBH"), true);
/* 43:52 */         classrooms.add(c);
/* 44:   */       }
/* 45:54 */       sql = 
/* 46:   */       
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:64 */         "SELECT\r\n\tb.ID,\r\n\tb.ITEM_NUMBER,\r\n\tb.ITEM_NAME,\r\n\tb.ITEM_QUALITY,\r\n\tb.ITEM_TREEID\r\nFROM\r\n\t`tlk_教室管理` AS b\r\nLEFT JOIN `tlk_教师管理树形` AS a ON a.ID = b.ITEM_TREEID\r\nWHERE\r\nb.item_schoolid = '" + id + "'";
/* 56:65 */       System.out.println(id);
/* 57:66 */       pst = conn.prepareStatement(sql);
/* 58:67 */       rs = pst.executeQuery();
/* 59:68 */       c = null;
/* 60:69 */       while (rs.next())
/* 61:   */       {
/* 62:70 */         c = new classroom(rs.getString("ID"), rs.getString("ITEM_NAME"), rs.getString("ITEM_TREEID"), rs.getString("ITEM_NUMBER"), rs.getString("ITEM_QUALITY"), false);
/* 63:71 */         classrooms.add(c);
/* 64:   */       }
/* 65:73 */       rs.close();
/* 66:74 */       pst.close();
/* 67:75 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", classrooms)));
/* 68:   */     }
/* 69:   */     catch (SQLException e)
/* 70:   */     {
/* 71:78 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 72:79 */       e.printStackTrace();
/* 81:   */     }
/* 82:   */     finally
/* 83:   */     {
/* 84:   */       try
/* 85:   */       {
/* 86:82 */         conn.close();
/* 87:   */       }
/* 88:   */       catch (SQLException e)
/* 89:   */       {
/* 90:85 */         e.printStackTrace();
/* 91:   */       }
/* 92:   */     }
/* 93:   */   }
/* 94:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolRoomServletDemo
 * JD-Core Version:    0.7.0.1
 */