/*   1:    */ package cn.wizool.android.servlet.schoolpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.utils.JsonUtils;
/*   5:    */ import cn.wizool.android.utils.ResponseUtils;
/*   6:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.net.URLDecoder;
/*  10:    */ import java.sql.Connection;
/*  11:    */ import java.sql.PreparedStatement;
/*  12:    */ import java.sql.ResultSet;
/*  13:    */ import java.sql.SQLException;
/*  14:    */ import java.text.SimpleDateFormat;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import java.util.LinkedHashMap;
/*  17:    */ import java.util.List;
/*  18:    */ import java.util.Map;
/*  19:    */ import javax.servlet.ServletException;
/*  20:    */ import javax.servlet.http.HttpServlet;
/*  21:    */ import javax.servlet.http.HttpServletRequest;
/*  22:    */ import javax.servlet.http.HttpServletResponse;
/*  23:    */ 
/*  24:    */ public class SchoolColumnServletDemo
/*  25:    */   extends HttpServlet
/*  26:    */ {
/*  27:    */   private static final long serialVersionUID = 1L;
/*  28:    */   
/*  29:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  30:    */     throws ServletException, IOException
/*  31:    */   {
/*  32: 45 */     String placeid = request.getParameter("placeid");
/*  33: 46 */     System.out.println(placeid);
/*  34: 47 */     String date = request.getParameter("date");
/*  35: 48 */     List<Map> list = new ArrayList();
/*  36: 49 */     Connection conn = Mysql.getInstance().getConnection();
/*  37: 50 */     String str2 = request.getServerName();
/*  38: 51 */     int port = request.getServerPort();
/*  39: 52 */     String sss = request.getScheme();
/*  40: 53 */     String contextPath = request.getContextPath();
/*  41: 54 */     String sql = "";
/*  42: 55 */     if (date == null)
/*  43:    */     {
/*  44: 56 */       sql = 
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
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77: 89 */         "SELECT\r\n\ts.ITEM_CATEGORYID,\r\n\ts.ITEM_CLASSIFICATION,\r\n\ts.ITEM_TITLE,\r\n\ts.ITEM_DISPLAYS,\r\n\ts.ITEM_DISPLAYE,\r\n\ts.ITEM_DESCRIBE,\r\n\ts.ITEM_CLASSROOMNAME,\r\n\ts.ITEM_DISPLAYRANGE,\r\n\ts.ITEM_DISPLAYS,\r\n\ts.ITEM_ALL,\r\n\tx.ITEM_PICSWITCH,\r\n\tx.ITEM_TASKID,\r\n\tx.ITEM_SORT,\r\n\tx.ITEM_PICID,\r\n\tl.ITEM_COLUMNTYPE\r\nFROM\r\n`tlk_教室管理` AS js \r\nLEFT JOIN `tlk_图片任务管理` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tAND s.ITEM_ALL = '取消全选'\r\n)\r\nOR s.ITEM_ALL = '确定全选'\r\nLEFT JOIN `tlk_宣传推广任务详情维护` AS x ON s.ID = x.ITEM_TASKID\r\nLEFT JOIN `tlk_栏目管理` AS l ON l.ID = s.ITEM_CATEGORYID\r\nWHERE\r\n\tl.ITEM_COLUMNTYPE = '校级'\r\nand l.ITEM_AVAILABILITY = '是'\r\nAND js.ID= '" + placeid + "'\r\n" + "GROUP BY\r\n" + "\ts.ITEM_TITLE\r\n" + "ORDER BY\r\n" + "\ts.ITEM_DISPLAYS desc";
/*  78:    */     }
/*  79:    */     else
/*  80:    */     {
/*  81: 92 */       System.out.println("2222");
/*  82: 93 */       sql = "SELECT\r\n\ts.ITEM_CATEGORYID,\r\n\ts.ITEM_CLASSIFICATION,\r\n\ts.ITEM_TITLE,\r\n\ts.ITEM_DISPLAYS,\r\n\ts.ITEM_DISPLAYE,\r\n\ts.ITEM_DESCRIBE,\r\n\ts.ITEM_CLASSROOMNAME,\r\n\ts.ITEM_DISPLAYRANGE,\r\n\ts.ITEM_DISPLAYS,\r\n\ts.ITEM_ALL,\r\n\tx.ITEM_PICSWITCH,\r\n\tx.ITEM_TASKID,\r\n\tx.ITEM_SORT,\r\n\tx.ITEM_PICID,\r\n\tl.ITEM_COLUMNTYPE\r\nFROM\r\n`tlk_教室管理` AS js \r\nLEFT JOIN `tlk_图片任务管理` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tAND s.ITEM_ALL = '取消全选'\r\n)\r\nOR s.ITEM_ALL = '确定全选'\r\nLEFT JOIN `tlk_宣传推广任务详情维护` AS x ON s.ID = x.ITEM_TASKID\r\nLEFT JOIN `tlk_栏目管理` AS l ON l.ID = s.ITEM_CATEGORYID\r\nWHERE\r\n\tl.ITEM_COLUMNTYPE = '校级'\r\nand l.ITEM_AVAILABILITY = '是'\r\nAND js.ID= '" + 
/*  83:    */       
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:    */ 
/*  98:    */ 
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:    */ 
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:122 */         placeid + "'\r\n" + 
/* 112:123 */         "AND (\r\n" + 
/* 113:124 */         "\t\t\ts.ITEM_DISPLAYS <= '" + date + "'\r\n" + 
/* 114:125 */         "\t\tAND s.ITEM_DISPLAYE >= '" + date + "'\r\n" + 
/* 115:126 */         "\t)\r\n" + 
/* 116:127 */         "GROUP BY\r\n" + 
/* 117:128 */         "\ts.ITEM_TITLE\r\n" + 
/* 118:129 */         "ORDER BY\r\n" + 
/* 119:130 */         "\ts.ITEM_DISPLAYS desc";
/* 120:    */     }
/* 121:    */     try
/* 122:    */     {
/* 123:133 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/* 124:134 */       ResultSet resultSet = prepareStatement.executeQuery();
/* 125:135 */       System.out.println(resultSet.getRow());
/* 126:136 */       while (resultSet.next())
/* 127:    */       {
/* 128:137 */         Map map = new LinkedHashMap();
/* 129:138 */         Map<String, String> mapp = new LinkedHashMap();
/* 130:139 */         String imagename = null;
/* 131:140 */         String path = null;
/* 132:141 */         if ((resultSet.getString("ITEM_PICID") != null) && (resultSet.getString("ITEM_PICID").length() > 0))
/* 133:    */         {
/* 134:142 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_PICID").substring(1, resultSet.getString("ITEM_PICID").length() - 1), Map.class);
/* 135:143 */           imagename = (String)mapp.get("name");
/* 136:144 */           path = (String)mapp.get("path");
/* 137:145 */           path = URLDecoder.decode(path, "utf-8");
/* 138:146 */           path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/* 139:    */         }
/* 140:148 */         map.put("category_id", resultSet.getString("ITEM_CATEGORYID"));
/* 141:149 */         map.put("column_name", resultSet.getString("ITEM_CLASSIFICATION"));
/* 142:150 */         map.put("title", resultSet.getString("ITEM_TITLE"));
/* 143:151 */         map.put("start_time", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_DISPLAYS")));
/* 144:152 */         map.put("end_time", new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ITEM_DISPLAYE")));
/* 145:153 */         map.put("describe", resultSet.getString("ITEM_DESCRIBE"));
/* 146:154 */         map.put("place", resultSet.getString("ITEM_CLASSROOMNAME"));
/* 147:155 */         map.put("display_range", resultSet.getString("ITEM_DISPLAYRANGE"));
/* 148:156 */         map.put("all", resultSet.getString("ITEM_ALL"));
/* 149:157 */         map.put("picure_swich", resultSet.getString("ITEM_PICSWITCH"));
/* 150:158 */         map.put("task_id", resultSet.getString("ITEM_TASKID"));
/* 151:159 */         map.put("column_level", resultSet.getString("ITEM_COLUMNTYPE"));
/* 152:160 */         map.put("sort", Integer.valueOf(resultSet.getInt("ITEM_SORT")));
/* 153:161 */         map.put("image_name", imagename);
/* 154:162 */         map.put("image_path", path);
/* 155:163 */         list.add(map);
/* 156:    */       }
/* 157:165 */       resultSet.close();
/* 158:166 */       prepareStatement.close();
/* 159:167 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 160:    */     }
/* 161:    */     catch (SQLException e)
/* 162:    */     {
/* 163:171 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 164:172 */       e.printStackTrace();
/* 173:    */     }
/* 174:    */     finally
/* 175:    */     {
/* 176:    */       try
/* 177:    */       {
/* 178:175 */         conn.close();
/* 179:    */       }
/* 180:    */       catch (SQLException e)
/* 181:    */       {
/* 182:177 */         e.printStackTrace();
/* 183:    */       }
/* 184:    */     }
/* 185:    */   }
/* 186:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolColumnServletDemo
 * JD-Core Version:    0.7.0.1
 */