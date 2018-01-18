/*   1:    */ package cn.wizool.android.servlet.schoolpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.servletmode.PictureDescribeMode;
/*   5:    */ import cn.wizool.android.servletmode.PictureTaskManagerMode;
/*   6:    */ import cn.wizool.android.utils.JsonUtils;
/*   7:    */ import cn.wizool.android.utils.ResponseUtils;
/*   8:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   9:    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*  10:    */ import java.io.IOException;
/*  11:    */ import java.io.PrintStream;
/*  12:    */ import java.sql.Connection;
/*  13:    */ import java.sql.PreparedStatement;
/*  14:    */ import java.sql.ResultSet;
/*  15:    */ import java.sql.SQLException;
/*  16:    */ import java.text.SimpleDateFormat;
/*  17:    */ import java.util.ArrayList;
/*  18:    */ import java.util.List;
/*  19:    */ import javax.servlet.ServletException;
/*  20:    */ import javax.servlet.http.HttpServlet;
/*  21:    */ import javax.servlet.http.HttpServletRequest;
/*  22:    */ import javax.servlet.http.HttpServletResponse;
/*  23:    */ 
/*  24:    */ public class SchoolServletDemo
/*  25:    */   extends HttpServlet
/*  26:    */ {
/*  27:    */   private static final long serialVersionUID = 1L;
/*  28:    */   
/*  29:    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*  30:    */     throws ServletException, IOException
/*  31:    */   {
/*  32: 40 */     String name = req.getParameter("columnname");
/*  33: 41 */     String did = req.getParameter("id");
/*  34: 42 */     System.out.println(did);
/*  35: 43 */     String date = req.getParameter("date");
/*  36: 44 */     String message = "";
/*  37: 45 */     System.out.println("name====" + name);
/*  38:    */     
/*  39:    */ 
/*  40: 48 */     List<PictureTaskManagerMode> list = new ArrayList();
/*  41: 49 */     PictureTaskManagerMode ptm = null;
/*  42: 50 */     PictureDescribeMode pdm = null;
/*  43: 51 */     String str2 = req.getServerName();
/*  44: 52 */     int port = req.getServerPort();
/*  45: 53 */     String sss = req.getScheme();
/*  46: 54 */     String contextPath = req.getContextPath();
/*  47: 55 */     String sqll = "SELECT\r\n\ts.*\r\nFROM\r\n\t`tlk_班级基础信息` AS J\r\nLEFT JOIN `tlk_教室管理` AS js ON j.ITEM_CLASSROOMID = js.ITEM_NUMBER\r\nLEFT JOIN `tlk_图片任务管理` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tOR s.ITEM_ALL = '确定全选'\r\n)\r\nWHERE\r\n\tj.id = '" + 
/*  48:    */     
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57: 65 */       did + "'\r\n";
/*  58: 66 */     Connection conn = Mysql.getInstance().getConnection();
/*  59:    */     try
/*  60:    */     {
/*  61: 69 */       PreparedStatement pstmtt = conn.prepareStatement(sqll);
/*  62: 70 */       ResultSet resultSet = pstmtt.executeQuery();
/*  63: 71 */       if (!resultSet.next())
/*  64:    */       {
/*  65: 72 */         message = "查无此id";
/*  66: 73 */         ObjectMapper mapper = new ObjectMapper();
/*  67: 74 */         ResponseUtils.renderJson(resp, mapper.writeValueAsString(TaotaoResultUtils.build(Integer.valueOf(400), message)));
/*  68:    */       }
/*  69:    */       else
/*  70:    */       {
/*  71:    */         try
/*  72:    */         {
/*  73: 78 */           String sql2 = "SELECT\r\n\ts.*\r\nFROM\r\n\t`tlk_班级基础信息` AS J\r\nLEFT JOIN `tlk_教室管理` AS js ON j.ITEM_CLASSROOMID = js.ITEM_NUMBER\r\nLEFT JOIN `tlk_图片任务管理` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tOR s.ITEM_ALL = '确定全选'\r\n)\r\nWHERE\r\n\tj.id = '" + 
/*  74:    */           
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83: 88 */             did + "'\r\n" + 
/*  84: 89 */             "AND (\r\n" + 
/*  85: 90 */             "\ts.ITEM_DISPLAYS <= '" + date + "'\r\n" + 
/*  86: 91 */             "\tAND s.ITEM_DISPLAYE >= '" + date + "'\r\n" + 
/*  87: 92 */             ")\r\n" + 
/*  88: 93 */             "AND s.ITEM_CLASSIFICATION = '" + name + "'";
/*  89: 94 */           PreparedStatement pstmt = conn.prepareStatement(sql2);
/*  90: 95 */           ResultSet ret = pstmt.executeQuery();
/*  91: 96 */           while (ret.next())
/*  92:    */           {
/*  93: 97 */             List<PictureDescribeMode> list2 = new ArrayList();
/*  94: 98 */             ptm = new PictureTaskManagerMode();
/*  95: 99 */             ptm.setColumn_name(ret.getString("item_classification"));
/*  96:100 */             ptm.setDescription(ret.getString("item_describe"));
/*  97:101 */             ptm.setCategory_id(ret.getString("item_categoryid"));
/*  98:102 */             ptm.setDisplay_range(ret.getString("item_displayrange"));
/*  99:103 */             ptm.setDisplay_class(ret.getString("item_classroomname"));
/* 100:104 */             String str_all = ret.getString("item_all");
/* 101:105 */             ptm.setTitle(ret.getString("item_title"));
/* 102:    */             boolean ss;
/* 104:107 */             if ("确定全选".equals(str_all)) {
/* 105:108 */               ss = true;
/* 106:    */             } else {
/* 107:110 */               ss = false;
/* 108:    */             }
/* 109:112 */             ptm.setShow_all(ss);
/* 110:113 */             ptm.setStart_time(new SimpleDateFormat("yyyy-MM-dd").format(ret.getString("item_displays")));
/* 111:114 */             ptm.setEnd_time(new SimpleDateFormat("yyyy-MM-dd").format(ret.getString("item_displaye")));
/* 112:115 */             String id = ret.getString("id");
/* 113:116 */             String sql = "select * from tlk_宣传推广任务详情维护 where item_taskid='" + id + "'";
/* 114:117 */             PreparedStatement pstmt2 = conn.prepareStatement(sql);
/* 115:118 */             ResultSet ret2 = pstmt2.executeQuery();
/* 116:123 */             if (ret2.next())
/* 117:    */             {
/* 118:124 */               ret2.previous();
/* 119:125 */               while (ret2.next())
/* 120:    */               {
/* 121:126 */                 pdm = new PictureDescribeMode();
/* 122:127 */                 String picswitch = ret2.getString("item_picswitch");
/* 123:    */                 
/* 124:129 */                 pdm.setPic_switch(picswitch);
/* 125:130 */                 String taskid = ret2.getString("item_taskid");
/* 126:131 */                 ptm.setTask_id(taskid);
/* 127:    */                 
/* 128:133 */                 String picpath = ret2.getString("item_picpath");
/* 129:134 */                 String path = sss + "://" + str2 + ":" + port + contextPath + picpath;
/* 130:    */                 
/* 131:136 */                 pdm.setPic_path(path);
/* 132:137 */                 int sort = ret2.getInt("item_sort");
/* 133:138 */                 pdm.setPic_path2(picpath);
/* 134:139 */                 pdm.setSort(sort);
/* 135:140 */                 list2.add(pdm);
/* 136:    */               }
/* 137:    */             }
/* 138:    */             else
/* 139:    */             {
/* 140:143 */               pdm = new PictureDescribeMode();
/* 141:144 */               pdm.setPic_switch("null");
/* 142:145 */               ptm.setTask_id("null");
/* 143:146 */               pdm.setPic_path2("null");
/* 144:147 */               pdm.setPic_path("null");
/* 145:148 */               pdm.setSort(0);
/* 146:149 */               list2.add(pdm);
/* 147:    */             }
/* 148:152 */             ptm.setPic_describe(list2);
/* 149:153 */             list.add(ptm);
/* 150:154 */             ret2.close();
/* 151:155 */             pstmt2.close();
/* 152:    */           }
/* 153:158 */           ret.close();
/* 154:159 */           pstmt.close();
/* 155:160 */           resultSet.close();
/* 156:161 */           pstmtt.close();
/* 157:    */         }
/* 158:    */         catch (SQLException e)
/* 159:    */         {
/* 160:165 */           e.printStackTrace();
/* 169:    */         }
/* 170:    */         finally
/* 171:    */         {
/* 172:    */           try
/* 173:    */           {
/* 174:168 */             conn.close();
/* 175:    */           }
/* 176:    */           catch (SQLException e)
/* 177:    */           {
/* 178:171 */             e.printStackTrace();
/* 179:    */           }
/* 180:    */         }
/* 181:174 */         ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 182:    */       }
/* 183:    */     }
/* 184:    */     catch (SQLException e)
/* 185:    */     {
/* 186:179 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 187:180 */       e.printStackTrace();
/* 196:    */     }
/* 197:    */     finally
/* 198:    */     {
/* 199:    */       try
/* 200:    */       {
/* 201:183 */         conn.close();
/* 202:    */       }
/* 203:    */       catch (SQLException e)
/* 204:    */       {
/* 205:187 */         e.printStackTrace();
/* 206:    */       }
/* 207:    */     }
/* 208:    */   }
/* 209:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolServletDemo
 * JD-Core Version:    0.7.0.1
 */