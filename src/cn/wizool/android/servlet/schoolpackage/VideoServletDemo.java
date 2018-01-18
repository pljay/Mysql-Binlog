/*   1:    */ package cn.wizool.android.servlet.schoolpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.function.ZipJieYa;
/*   5:    */ import cn.wizool.android.utils.JsonUtils;
/*   6:    */ import cn.wizool.android.utils.ResponseUtils;
/*   7:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   8:    */ import java.io.File;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.sql.Connection;
/*  12:    */ import java.sql.PreparedStatement;
/*  13:    */ import java.sql.ResultSet;
/*  14:    */ import java.sql.SQLException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import java.util.LinkedHashMap;
/*  17:    */ import java.util.List;
/*  18:    */ import java.util.Map;
/*  19:    */ import javax.servlet.ServletContext;
/*  20:    */ import javax.servlet.ServletException;
/*  21:    */ import javax.servlet.http.HttpServlet;
/*  22:    */ import javax.servlet.http.HttpServletRequest;
/*  23:    */ import javax.servlet.http.HttpServletResponse;
/*  24:    */ import javax.servlet.http.HttpSession;
/*  25:    */ 
/*  26:    */ public class VideoServletDemo
/*  27:    */   extends HttpServlet
/*  28:    */ {
/*  29:    */   private static final long serialVersionUID = 1L;
/*  30:    */   
/*  31:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  32:    */     throws ServletException, IOException
/*  33:    */   {
/*  34: 42 */     response.setCharacterEncoding("utf-8");
/*  35: 43 */     String placeid = request.getParameter("placeid");
/*  36: 44 */     System.out.println(placeid);
/*  37: 45 */     String date = request.getParameter("date");
/*  38: 46 */     String sql = null;
/*  39: 47 */     List<Map> list = new ArrayList();
/*  40: 48 */     Connection conn = Mysql.getInstance().getConnection();
/*  41: 49 */     String str2 = request.getServerName();
/*  42: 50 */     int port = request.getServerPort();
/*  43: 51 */     String sss = request.getScheme();
/*  44: 52 */     String contextPath = request.getContextPath();
/*  45: 53 */     if (date == null) {
/*  46: 54 */       sql = 
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
/*  62: 70 */         "SELECT\r\n\ts.*,sp.*\r\nFROM\r\n\t`tlk_班级基础信息` AS J\r\nLEFT JOIN `tlk_教室管理` AS js ON j.ITEM_CLASSROOMID = js.ITEM_NUMBER\r\nLEFT JOIN `tlk_宣传推广任务视频维护` AS s ON ((\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tand s.ITEM_ALL = '取消全选') \r\n\tOR s.ITEM_ALL = '确定全选') \r\nLEFT JOIN `tlk_宣传推广任务视频` AS sp ON sp.ITEM_TASKID=s.ID\r\nWHERE\r\n\tj.id = '" + placeid + "'\r\n" + "ORDER BY sp.ITEM_SORT ASC";
/*  63:    */     } else {
/*  64: 96 */       sql = 
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
/*  77:    */ 
/*  78:110 */         "SELECT\r\n\ts.*,sp.*\r\nFROM\r\n`tlk_教室管理` AS js \r\nLEFT JOIN `tlk_宣传推广任务视频维护` AS s ON (\r\n\ts.ITEM_DISPLAYRANGE LIKE concat('%', js.ID, '%')\r\n\tOR s.ITEM_ALL = '确定全选' \r\n)LEFT JOIN `tlk_宣传推广任务视频` AS sp ON sp.ITEM_TASKID=s.ID\r\nWHERE\r\n\tjs.id = '" + placeid + "'\r\n" + "AND (\r\n" + "\ts.ITEM_DISPLAYS <= '" + date + "'\r\n" + "\tAND s.ITEM_DISPLAYE >= '" + date + "'\r\n" + ")\r\n" + "ORDER BY sp.ITEM_SORT ASC";
/*  79:    */     }
/*  80:    */     try
/*  81:    */     {
/*  82:114 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
/*  83:115 */       ResultSet resultSet = prepareStatement.executeQuery();
/*  84:116 */       System.out.println(resultSet.getRow());
/*  85:117 */       while (resultSet.next())
/*  86:    */       {
/*  87:118 */         Map map = new LinkedHashMap();
/*  88:119 */         Map<String, String> mapp = new LinkedHashMap();
/*  89:120 */         String videoname = null;
/*  90:121 */         String path = null;
/*  91:122 */         String videopath = null;
/*  92:    */         
/*  93:124 */         System.out.println(resultSet.getString("ITEM_VIDEO"));
/*  94:125 */         if ((resultSet.getString("ITEM_VIDEO") != null) && (resultSet.getString("ITEM_VIDEO").length() > 1))
/*  95:    */         {
/*  96:126 */           mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_VIDEO").substring(1, resultSet.getString("ITEM_VIDEO").length() - 1), Map.class);
/*  97:127 */           videoname = (String)mapp.get("name");
/*  98:128 */           String path1 = (String)mapp.get("path");
/*  99:129 */           String contextpath = request.getSession().getServletContext().getRealPath("/");
/* 100:130 */           System.out.println("(contextpath+path1.substring(1).replace(\"/\",\"\\\\\"))" + contextpath + path1.substring(1).replace("/", "\\"));
/* 101:131 */           if (new File(contextpath + path1.substring(1).replace("/", "\\")).exists())
/* 102:    */           {
/* 103:133 */             String item_path = resultSet.getString("item_path");
/* 104:    */             
/* 105:135 */             String filename = item_path.split("/")[(item_path.split("/").length - 1)];
/* 106:136 */             System.out.println("filename" + filename);
/* 107:    */             
/* 108:138 */             String filepath = path1.substring(1, path1.length() - path1.split("/")[(path1.split("/").length - 1)].length()) + filename;
/* 109:139 */             System.out.println(filepath);
/* 110:    */             
/* 111:    */ 
/* 112:142 */             contextpath = contextpath + filepath.replace("/", "\\");
/* 113:143 */             System.out.println(contextpath);
/* 114:144 */             System.out.println("122534564848" + new File(contextpath).exists());
/* 115:145 */             if (new File(contextpath).exists())
/* 116:    */             {
/* 117:146 */               System.out.println("没解压走起");
/* 118:147 */               videopath = contextpath.replace("\\", "/");
/* 119:    */             }
/* 120:    */             else
/* 121:    */             {
/* 122:151 */               System.out.println("解压了");
/* 123:152 */               ZipJieYa zy = new ZipJieYa();
/* 124:153 */               String path2 = contextpath.replace("\\", "/");
/* 125:154 */               File file = zy.decompression(path2.substring(0, path2.length() - path2.split("/")[(path2.split("/").length - 1)].length()).replace("/", "\\") + path1.split("/")[(path1.split("/").length - 1)], path2.substring(0, path2.length() - (path2.split("/")[(path2.split("/").length - 1)].length() + 1)).replace("/", "\\"));
/* 126:155 */               String absolutePath = file.getAbsolutePath();
/* 127:156 */               System.out.println(absolutePath);
/* 128:157 */               videopath = absolutePath.replace("\\", "/");
/* 129:    */             }
/* 130:159 */             videopath = sss + "://" + str2 + ":" + port + contextPath + videopath.substring(videopath.length() - videopath.split("obpm")[(videopath.split("obpm").length - 1)].length(), videopath.length());
/* 131:    */           }
/* 132:    */         }
/* 133:162 */         map.put("start_time", resultSet.getDate("ITEM_DISPLAYS"));
/* 134:163 */         map.put("end_time", resultSet.getDate("ITEM_DISPLAYE"));
/* 135:164 */         map.put("describe", resultSet.getString("ITEM_DESCRIBE"));
/* 136:165 */         map.put("place", resultSet.getString("ITEM_CLASSNAME"));
/* 137:166 */         map.put("display_range", resultSet.getString("ITEM_DISPLAYRANGE"));
/* 138:167 */         map.put("all", resultSet.getString("ITEM_ALL"));
/* 139:168 */         map.put("task_id", resultSet.getString("ITEM_TASKID"));
/* 140:169 */         map.put("sort", Integer.valueOf(resultSet.getInt("ITEM_SORT")));
/* 141:170 */         map.put("video_name", videoname);
/* 142:171 */         System.out.println("videopath" + videopath);
/* 143:172 */         System.out.println("videoname" + videoname);
/* 144:173 */         map.put("video_path", videopath);
/* 145:174 */         list.add(map);
/* 146:    */       }
/* 147:176 */       resultSet.close();
/* 148:177 */       prepareStatement.close();
/* 149:178 */       response.setCharacterEncoding("utf-8");
/* 150:179 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
/* 151:    */     }
/* 152:    */     catch (SQLException e)
/* 153:    */     {
/* 154:183 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 155:184 */       e.printStackTrace();
/* 164:    */     }
/* 165:    */     finally
/* 166:    */     {
/* 167:    */       try
/* 168:    */       {
/* 169:187 */         conn.close();
/* 170:    */       }
/* 171:    */       catch (SQLException e)
/* 172:    */       {
/* 173:190 */         e.printStackTrace();
/* 174:    */       }
/* 175:    */     }
/* 176:    */   }
/* 177:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.VideoServletDemo
 * JD-Core Version:    0.7.0.1
 */