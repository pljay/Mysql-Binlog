/*  1:   */ package cn.wizool.android.servlet.schoolpackage;
/*  2:   */ 
/*  3:   */ import cn.wizool.android.JDBC.Mysql;
/*  4:   */ import cn.wizool.android.servletmode.WeatherMode;
/*  5:   */ import cn.wizool.android.utils.JsonUtils;
/*  6:   */ import cn.wizool.android.utils.ResponseUtils;
/*  7:   */ import cn.wizool.android.utils.TaotaoResultUtils;
/*  8:   */ import java.io.IOException;
/*  9:   */ import java.sql.Connection;
/* 10:   */ import java.sql.PreparedStatement;
/* 11:   */ import java.sql.ResultSet;
/* 12:   */ import java.sql.SQLException;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.List;
/* 15:   */ import javax.servlet.ServletException;
/* 16:   */ import javax.servlet.http.HttpServlet;
/* 17:   */ import javax.servlet.http.HttpServletRequest;
/* 18:   */ import javax.servlet.http.HttpServletResponse;
/* 19:   */ 
/* 20:   */ public class SchoolWeatherServletDemo
/* 21:   */   extends HttpServlet
/* 22:   */ {
/* 23:   */   private static final long serialVersionUID = 1L;
/* 24:   */   
/* 25:   */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/* 26:   */     throws ServletException, IOException
/* 27:   */   {
/* 28:37 */     String sql = "select * from tlk_天气维护";
/* 29:38 */     Connection conn = Mysql.getInstance().getConnection();
/* 30:39 */     PreparedStatement pstmt = null;
/* 31:   */     
/* 32:41 */     ResultSet ret = null;
/* 33:42 */     WeatherMode wm = null;
/* 34:43 */     List<WeatherMode> list = new ArrayList();
/* 35:   */     try
/* 36:   */     {
/* 37:45 */       pstmt = conn.prepareStatement(sql);
/* 38:46 */       ret = pstmt.executeQuery();
/* 39:47 */       while (ret.next())
/* 40:   */       {
/* 41:48 */         wm = new WeatherMode();
/* 42:49 */         wm.setCity(ret.getString("item_cname"));
/* 43:50 */         wm.setWeather(ret.getString("item_weather"));
/* 44:51 */         wm.setLow_temp(ret.getString("item_temperature"));
/* 45:52 */         wm.setWind_direction(ret.getString("item_direction"));
/* 46:53 */         wm.setWind_scale(ret.getString("item_wind"));
/* 47:54 */         wm.setHigh_temp(ret.getString("item_maxtemperature"));
/* 48:55 */         wm.setProvince(ret.getString("item_sheng"));
/* 49:56 */         wm.setDate(ret.getDate("item_starttime"));
/* 50:   */         
/* 51:58 */         list.add(wm);
/* 52:   */       }
/* 53:60 */       ret.close();
/* 54:61 */       pstmt.close();
/* 55:62 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 56:   */     }
/* 57:   */     catch (SQLException e)
/* 58:   */     {
/* 59:65 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 60:66 */       e.printStackTrace();
/* 69:   */     }
/* 70:   */     finally
/* 71:   */     {
/* 72:   */       try
/* 73:   */       {
/* 74:69 */         conn.close();
/* 75:   */       }
/* 76:   */       catch (SQLException e)
/* 77:   */       {
/* 78:72 */         e.printStackTrace();
/* 79:   */       }
/* 80:   */     }
/* 81:   */   }
/* 82:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolWeatherServletDemo
 * JD-Core Version:    0.7.0.1
 */