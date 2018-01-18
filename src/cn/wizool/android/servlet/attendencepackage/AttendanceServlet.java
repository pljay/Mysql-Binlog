/*   1:    */ package cn.wizool.android.servlet.attendencepackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.utils.JsonUtils;
/*   5:    */ import cn.wizool.android.utils.ResponseUtils;
/*   6:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.sql.Connection;
/*  10:    */ import java.sql.PreparedStatement;
/*  11:    */ import java.sql.ResultSet;
/*  12:    */ import java.sql.SQLException;
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.HashMap;
/*  15:    */ import java.util.LinkedHashMap;
/*  16:    */ import java.util.List;
/*  17:    */ import java.util.Map;
/*  18:    */ import javax.servlet.ServletException;
/*  19:    */ import javax.servlet.http.HttpServlet;
/*  20:    */ import javax.servlet.http.HttpServletRequest;
/*  21:    */ import javax.servlet.http.HttpServletResponse;
/*  22:    */ 
/*  23:    */ public class AttendanceServlet
/*  24:    */   extends HttpServlet
/*  25:    */ {
/*  26:    */   static int a;
/*  27:    */   
/*  28:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  29:    */     throws ServletException, IOException
/*  30:    */   {
/*  31: 38 */     System.out.print(++a + "\t");
/*  32: 39 */     String classid = request.getParameter("placeid");
/*  33: 40 */     System.out.println(classid);
/*  34: 41 */     String str2 = request.getServerName();
/*  35: 42 */     int port = request.getServerPort();
/*  36: 43 */     String sss = request.getScheme();
/*  37: 44 */     String contextPath = request.getContextPath();
/*  38: 45 */     String sql = "SELECT\r\n\tb.ITEM_CALSS_TIME,\r\n\tb.ITEM_CALSSROOM,\r\n\tb.ITEM_HOUR,\r\n\td.`start`,\r\n\td.`end`,\r\n\td.keshi,\r\n\tb.ITEM_SUB_NAME,\r\n\tb.ITEM_WEEKS,\r\n\tc.ITEM_NAME,\r\n\tb.ITEM_TYPE,\r\n\tc.ITEM_TEACHERPATH\r\nFROM\r\n\t`tlk_班级基础信息` AS a\r\nLEFT JOIN `tlk_教室管理` AS js ON a.ITEM_CLASSROOMID = js.ITEM_NUMBER \r\nLEFT JOIN `tlk_科目安排` AS b ON a.ITEM_CLASSROOMID = b.ITEM_CALSSROOM\r\nLEFT JOIN `t_课时安排` AS d ON (\r\n\tb.ITEM_HOUR = d.sort\r\n\tAND a.ITEM_GRADE = d.nianji\r\n)\r\nLEFT JOIN `tlk_教师基础信息` AS c ON b.ITEM_TEACHER = c.ITEM_STAFFNUMBER\r\nWHERE\r\njs.ID ='" + 
/*  39:    */     
/*  40:    */ 
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
/*  59:    */ 
/*  60: 67 */       classid + "'";
/*  61: 68 */     Connection connection = Mysql.getInstance().getConnection();
/*  62: 69 */     List<Map> list = new ArrayList();
/*  63: 70 */     List<Map> list1 = new ArrayList();
/*  64:    */     try
/*  65:    */     {
/*  66: 72 */       PreparedStatement prepareStatement = connection.prepareStatement(sql);
/*  67: 73 */       ResultSet resultSet = prepareStatement.executeQuery();
/*  68:    */       
/*  69: 75 */       String week = "";
/*  70: 76 */       while (resultSet.next())
/*  71:    */       {
/*  72: 77 */         Map map = new LinkedHashMap();
/*  73: 78 */         if ((week == "") || (week.equals(resultSet.getString("ITEM_CALSS_TIME"))))
/*  74:    */         {
/*  75: 79 */           Map map1 = new LinkedHashMap();
/*  76: 80 */           week = resultSet.getString("ITEM_CALSS_TIME");
/*  77: 81 */           Map<String, String> mapp = new HashMap();
/*  78: 82 */           String imagename = null;
/*  79: 83 */           String path = null;
/*  80: 84 */           System.out.println(resultSet.getString("ITEM_TEACHERPATH"));
/*  81: 85 */           if ((resultSet.getString("ITEM_TEACHERPATH") != null) && (resultSet.getString("ITEM_TEACHERPATH").length() > 10))
/*  82:    */           {
/*  83: 86 */             mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_TEACHERPATH").substring(1, resultSet.getString("ITEM_TEACHERPATH").length() - 1), Map.class);
/*  84: 87 */             imagename = (String)mapp.get("name");
/*  85: 88 */             path = (String)mapp.get("path");
/*  86: 89 */             path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/*  87:    */           }
/*  88: 91 */           map1.put("star", resultSet.getString("start"));
/*  89: 92 */           map1.put("end", resultSet.getString("end"));
/*  90: 93 */           map1.put("keshi", resultSet.getString("keshi"));
/*  91: 94 */           map1.put("subject", resultSet.getString("ITEM_SUB_NAME"));
/*  92: 95 */           map1.put("class_type", resultSet.getString("ITEM_TYPE"));
/*  93: 96 */           map1.put("weeks", resultSet.getString("ITEM_WEEKS"));
/*  94: 97 */           map1.put("teacher_name", resultSet.getString("ITEM_NAME"));
/*  95: 98 */           map1.put("image_name", imagename);
/*  96: 99 */           map1.put("image_path", path);
/*  97:100 */           String sql2 = "";
/*  98:101 */           if ("走".equals(resultSet.getString("ITEM_TYPE")))
/*  99:    */           {
/* 100:102 */             sql2 = 
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
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119:    */ 
/* 120:122 */               "SELECT\r\n\tb.ITEM_STUDENTNAME,\r\n\tb.ID,\r\n\tb.ITEM_CLASSID,\r\n\tb.ITEM_AGE,\r\n\tb.ITEM_SEX,\r\n\tb.ITEM_ADDRESS,\r\n\tb.ITEM_PARENTPHONE,\r\n\tb.ITEM_STUDENTPATH,\r\n\tb.ITEM_STUDENTID,\r\n\tc.cardid,\r\n\tc.type,\r\n\ta.ITEM_IDD AS pattern\r\nFROM\r\n\t`tlk_学生选课` AS a\r\nLEFT JOIN tlk_student AS b ON a.ITEM_STU_NUM = b.ITEM_STUDENTID\r\nLEFT JOIN card AS c ON c.teacherid = b.ID\r\nWHERE\r\n\ta.ITEM_CLASSROOM = '" + resultSet.getString("ITEM_CALSSROOM") + "'\r\n" + "AND a.ITEM_ATTENDTIME = '" + resultSet.getString("ITEM_CALSS_TIME") + "'\r\n" + "AND a.ITEM_SORT = '" + resultSet.getString("ITEM_HOUR") + "'";
/* 121:123 */             System.out.println(resultSet.getString("ITEM_CALSSROOM") + "$$$" + resultSet.getString("ITEM_CALSS_TIME") + "&&&&" + resultSet.getString("ITEM_HOUR"));
/* 122:    */           }
/* 123:    */           else
/* 124:    */           {
/* 125:129 */             sql2 = 
/* 126:    */             
/* 127:    */ 
/* 128:    */ 
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:    */ 
/* 133:    */ 
/* 134:    */ 
/* 135:    */ 
/* 136:    */ 
/* 137:    */ 
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:152 */               "SELECT\r\n\tb.ITEM_STUDENTNAME,\r\n\tb.ID,\r\n\tb.ITEM_CLASSID,\r\n\tb.ITEM_AGE,\r\n\tb.ITEM_SEX,\r\n\tb.ITEM_ADDRESS,\r\n\tb.ITEM_PARENTPHONE,\r\n\tb.ITEM_STUDENTPATH,\r\n\tb.ITEM_STUDENTID,\r\n\tc.cardid,\r\n\tc.type,\r\n\td.ID AS pattern\r\nFROM\r\n`tlk_人员考勤时间设置` AS e\r\nLEFT JOIN tlk_student AS b ON e.ITEM_PID = b.ITEM_CLASSID\r\nLEFT JOIN card AS c ON c.teacherid = b.ID \r\nLEFT JOIN `tlk_时间管理` AS d ON (\r\n\te.ITEM_TIMEID LIKE CONCAT('%', d.ID, '%')\r\n\tAND d.ITEM_START_ATTENDDATE <= '" + resultSet.getString("start") + "'\r\n" + "\tAND d.ITEM_END_OFF_ATTENDDATE >= '" + resultSet.getString("end") + "'\r\n" + ")" + "WHERE\r\n" + "b.ITEM_CLASSID='" + classid + "'\r\n";
/* 149:    */           }
/* 150:154 */           PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
/* 151:155 */           ResultSet resultSet2 = prepareStatement2.executeQuery();
/* 152:156 */           int count = 0;
/* 153:157 */           List<Map<String, String>> studentlist = new ArrayList();
/* 154:158 */           String pattern = null;
/* 155:159 */           while (resultSet2.next())
/* 156:    */           {
/* 157:160 */             count++;
/* 158:161 */             Map<String, String> map2 = new LinkedHashMap();
/* 159:162 */             map2.put("card_id", resultSet2.getString("cardid"));
/* 160:163 */             map2.put("name", resultSet2.getString("ITEM_STUDENTNAME"));
/* 161:164 */             map2.put("gender", resultSet2.getString("ITEM_SEX"));
/* 162:165 */             map2.put("age", resultSet2.getString("ITEM_AGE"));
/* 163:166 */             map2.put("id", resultSet2.getString("ITEM_STUDENTID"));
/* 164:167 */             map2.put("phone_number", resultSet2.getString("ITEM_PARENTPHONE"));
/* 165:168 */             map2.put("class_id", resultSet2.getString("id"));
/* 166:169 */             map2.put("type", resultSet2.getString("type"));
/* 167:170 */             map1.put("pattern", resultSet2.getString("pattern"));
/* 168:171 */             System.out.println(resultSet2.getString("type"));
/* 169:172 */             pattern = resultSet2.getString("pattern");
/* 170:173 */             String student_imagename = null;
/* 171:174 */             String student_path = null;
/* 172:175 */             if ((resultSet2.getString("ITEM_STUDENTPATH") != null) && (resultSet2.getString("ITEM_STUDENTPATH").length() > 10))
/* 173:    */             {
/* 174:176 */               mapp = (Map)JsonUtils.jsonToPojo(resultSet2.getString("ITEM_STUDENTPATH").substring(1, resultSet2.getString("ITEM_STUDENTPATH").length() - 1), Map.class);
/* 175:177 */               student_imagename = (String)mapp.get("name");
/* 176:178 */               student_path = (String)mapp.get("path");
/* 177:179 */               student_path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
/* 178:    */             }
/* 179:181 */             map2.put("student_imagename", student_imagename);
/* 180:182 */             map2.put("student_path", student_path);
/* 181:183 */             studentlist.add(map2);
/* 182:    */           }
/* 183:185 */           System.out.println("33333333333+++" + pattern);
/* 184:186 */           prepareStatement2.close();
/* 185:187 */           resultSet2.close();
/* 186:188 */           map1.put("class_among", Integer.valueOf(count));
/* 187:189 */           map1.put("class_personnel", studentlist);
/* 188:190 */           list1.add(map1);
/* 189:    */         }
/* 190:    */         else
/* 191:    */         {
/* 192:193 */           map.put("week", week);
/* 193:194 */           map.put("course", list1);
/* 194:195 */           list.add(map);
/* 195:196 */           list1 = new ArrayList();
/* 196:197 */           week = resultSet.getString("ITEM_CALSS_TIME");
/* 197:198 */           resultSet.previous();
/* 198:    */         }
/* 199:200 */         if (resultSet.isLast())
/* 200:    */         {
/* 201:201 */           map.put("week", week);
/* 202:202 */           map.put("course", list1);
/* 203:203 */           list.add(map);
/* 204:    */         }
/* 205:    */       }
/* 206:206 */       resultSet.close();
/* 207:207 */       prepareStatement.close();
/* 208:208 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
/* 209:    */     }
/* 210:    */     catch (SQLException e)
/* 211:    */     {
/* 212:211 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
/* 213:212 */       e.printStackTrace();

/* 222:    */     }
/* 223:    */     finally
/* 224:    */     {
/* 225:    */       try
/* 226:    */       {
/* 227:215 */         connection.close();
/* 228:    */       }
/* 229:    */       catch (SQLException e)
/* 230:    */       {
/* 231:218 */         e.printStackTrace();
/* 232:    */       }
/* 233:    */     }
/* 234:    */   }
/* 235:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.attendencepackage.AttendanceServlet
 * JD-Core Version:    0.7.0.1
 */