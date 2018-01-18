/*   1:    */ package cn.wizool.android.servlet.schoolpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.servletmode.PictureDescribeMode;
/*   5:    */ import cn.wizool.android.servletmode.PictureTaskManagerMode;
/*   6:    */ import cn.wizool.android.utils.JsonUtils;
/*   7:    */ import cn.wizool.android.utils.ResponseUtils;
/*   8:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.sql.Connection;
/*  12:    */ import java.sql.PreparedStatement;
/*  13:    */ import java.sql.ResultSet;
/*  14:    */ import java.sql.SQLException;
/*  15:    */ import java.text.SimpleDateFormat;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import java.util.List;
/*  18:    */ import javax.servlet.ServletException;
/*  19:    */ import javax.servlet.http.HttpServlet;
/*  20:    */ import javax.servlet.http.HttpServletRequest;
/*  21:    */ import javax.servlet.http.HttpServletResponse;
/*  22:    */ 
/*  23:    */ public class SchoolServletDemoFenPage
/*  24:    */   extends HttpServlet
/*  25:    */ {
/*  26:    */   private static final long serialVersionUID = 1L;
/*  27:    */   
/*  28:    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*  29:    */     throws ServletException, IOException
/*  30:    */   {
/*  31: 39 */     String name = req.getParameter("columnname");
/*  32: 40 */     String aa = req.getParameter("page");
/*  33: 41 */     String did = req.getParameter("id");
/*  34: 42 */     String date = req.getParameter("date");
/*  35: 43 */     int page = Integer.parseInt(aa);
/*  36: 44 */     System.out.println(did);
/*  37: 45 */     System.out.println("name====" + name);
/*  38: 46 */     Connection conn = Mysql.getInstance().getConnection();
/*  39:    */     
/*  40:    */ 
/*  41: 49 */     List<PictureTaskManagerMode> list = new ArrayList();
/*  42: 50 */     PictureTaskManagerMode ptm = null;
/*  43: 51 */     PictureDescribeMode pdm = null;
/*  44: 52 */     String str2 = req.getServerName();
/*  45: 53 */     int port = req.getServerPort();
/*  46: 54 */     String sss = req.getScheme();
/*  47: 55 */     String contextPath = req.getContextPath();
/*  48:    */     try
/*  49:    */     {
/*  50: 57 */       String sql2 = "SELECT\r\n\ts.*\r\nFROM\r\n\t`tlk_班级基础信息` AS J\r\nLEFT JOIN `tlk_教室管理` AS js ON j.ITEM_CLASSROOMID = js.ITEM_NUMBER\r\nLEFT JOIN `tlk_图片任务管理` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tOR s.ITEM_ALL = '确定全选'\r\n)\r\nWHERE\r\n\tj.id = '" + 
/*  51:    */       
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60: 67 */         did + "'\r\n" + 
/*  61: 68 */         "AND (\r\n" + 
/*  62: 69 */         "\ts.ITEM_DISPLAYS <= '" + date + "'\r\n" + 
/*  63: 70 */         "\tAND s.ITEM_DISPLAYE >= '" + date + "'\r\n" + 
/*  64: 71 */         ")\r\n" + 
/*  65: 72 */         "AND s.ITEM_CLASSIFICATION = '" + name + "'group by s.ID order by  s.ID limit " + (page - 1) * 4 + ",4 ";
/*  66: 73 */       PreparedStatement pstmt = conn.prepareStatement(sql2);
/*  67: 74 */       System.out.println("sql2====" + sql2);
/*  68: 75 */       ResultSet ret = pstmt.executeQuery();
/*  69: 76 */       while (ret.next())
/*  70:    */       {
/*  71: 77 */         List<PictureDescribeMode> list2 = new ArrayList();
/*  72: 78 */         ptm = new PictureTaskManagerMode();
/*  73: 79 */         ptm.setColumn_name(ret.getString("item_classification"));
/*  74: 80 */         ptm.setDescription(ret.getString("item_describe"));
/*  75: 81 */         ptm.setCategory_id(ret.getString("item_categoryid"));
/*  76: 82 */         ptm.setDisplay_range(ret.getString("item_displayrange"));
/*  77: 83 */         ptm.setDisplay_class(ret.getString("item_classroomname"));
/*  78: 84 */         ptm.setTitle(ret.getString("item_title"));
/*  79: 85 */         String str_all = ret.getString("item_all");
/*  80:    */         boolean ss;
/*  82: 87 */         if ("确定全选".equals(str_all)) {
/*  83: 88 */           ss = true;
/*  84:    */         } else {
/*  85: 90 */           ss = false;
/*  86:    */         }
/*  87: 92 */         ptm.setShow_all(ss);
/*  88: 93 */         ptm.setStart_time(new SimpleDateFormat("yyyy-MM-dd").format(ret.getString("item_displays")));
/*  89: 94 */         ptm.setEnd_time(new SimpleDateFormat("yyyy-MM-dd").format(ret.getString("item_displaye")));
/*  90: 95 */         String id = ret.getString("id");
/*  91: 96 */         String sql = "select * from tlk_宣传推广任务详情维护 where item_taskid='" + id + "'";
/*  92: 97 */         PreparedStatement pstmt2 = conn.prepareStatement(sql);
/*  93: 98 */         ResultSet ret2 = pstmt2.executeQuery();
/*  94:103 */         if (ret2.next())
/*  95:    */         {
/*  96:104 */           pdm = new PictureDescribeMode();
/*  97:105 */           String picswitch = ret2.getString("item_picswitch");
/*  98:    */           
/*  99:107 */           pdm.setPic_switch(picswitch);
/* 100:108 */           String taskid = ret2.getString("item_taskid");
/* 101:109 */           ptm.setTask_id(taskid);
/* 102:    */           
/* 103:111 */           String picpath = ret2.getString("item_picpath");
/* 104:112 */           String path = sss + "://" + str2 + ":" + port + contextPath + picpath;
/* 105:113 */           pdm.setPic_path2(picpath);
/* 106:114 */           pdm.setPic_path(path);
/* 107:115 */           int sort = ret2.getInt("item_sort");
/* 108:    */           
/* 109:117 */           pdm.setSort(sort);
/* 110:118 */           list2.add(pdm);
/* 111:    */         }
/* 112:    */         else
/* 113:    */         {
/* 114:121 */           pdm = new PictureDescribeMode();
/* 115:122 */           pdm.setPic_switch("null");
/* 116:123 */           ptm.setTask_id("null");
/* 117:124 */           pdm.setPic_path2("null");
/* 118:125 */           pdm.setPic_path("null");
/* 119:126 */           pdm.setSort(0);
/* 120:127 */           list2.add(pdm);
/* 121:    */         }
/* 122:129 */         ptm.setPic_describe(list2);
/* 123:130 */         list.add(ptm);
/* 124:131 */         ret2.close();
/* 125:132 */         pstmt2.close();
/* 126:    */       }
/* 127:134 */       ret.close();
/* 128:135 */       pstmt.close();
/* 129:136 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 130:    */     }
/* 131:    */     catch (SQLException e)
/* 132:    */     {
/* 133:139 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 134:140 */       e.printStackTrace();
/* 143:    */     }
/* 144:    */     finally
/* 145:    */     {
/* 146:    */       try
/* 147:    */       {
/* 148:143 */         conn.close();
/* 149:    */       }
/* 150:    */       catch (SQLException e)
/* 151:    */       {
/* 152:146 */         e.printStackTrace();
/* 153:    */       }
/* 154:    */     }
/* 155:    */   }
/* 156:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolServletDemoFenPage
 * JD-Core Version:    0.7.0.1
 */