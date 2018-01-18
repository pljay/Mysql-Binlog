/*   1:    */ package cn.wizool.android.servlet.classpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.servletmode.CurriculumSchedule;
/*   5:    */ import cn.wizool.android.servletmode.Kecheng;
/*   6:    */ import cn.wizool.android.utils.JsonUtils;
/*   7:    */ import cn.wizool.android.utils.ResponseUtils;
/*   8:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.sql.Connection;
/*  12:    */ import java.sql.PreparedStatement;
/*  13:    */ import java.sql.ResultSet;
/*  14:    */ import java.sql.SQLException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import java.util.HashMap;
/*  17:    */ import java.util.List;
/*  18:    */ import java.util.Map;
/*  19:    */ import javax.servlet.ServletException;
/*  20:    */ import javax.servlet.http.HttpServlet;
/*  21:    */ import javax.servlet.http.HttpServletRequest;
/*  22:    */ import javax.servlet.http.HttpServletResponse;
/*  23:    */ 
/*  24:    */ public class ClassCurriculumScheduleDemo
/*  25:    */   extends HttpServlet
/*  26:    */ {
	/*  27:    */   private static final long serialVersionUID = 1L;
	/*  28:    */   
	/*  29:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
			/*  30:    */     throws ServletException, IOException
	/*  31:    */   {
		/*  32: 41 */     String id = request.getParameter("classid");
		/*  33:    */     
		/*  34: 43 */     System.out.println(id);
		/*  35: 44 */     Connection conn = Mysql.getInstance().getConnection();
		/*  36: 45 */     String str2 = request.getServerName();
		/*  37: 46 */     int port = request.getServerPort();
		/*  38: 47 */     String sss = request.getScheme();
		/*  39: 48 */     String contextPath = request.getContextPath();
		/*  40: 49 */     List<Object> list = new ArrayList();
		/*  41: 50 */     List<Kecheng> list3 = null;
		/*  42:    */     try
		/*  43:    */     {
			/*  44: 52 */       String sql = "SELECT\r\n\tb.ITEM_CALSS_TIME,\r\n\td.`start`,\r\n\td.`end`,\r\n\td.keshi,\r\n\tb.ITEM_SUB_NAME,\r\n\tb.ITEM_WEEKS,\r\n\tc.ITEM_NAME,\r\n\tc.ITEM_TEACHERPATH\r\nFROM\r\n\t`tlk_班级基础信息` AS a\r\nLEFT JOIN `tlk_科目安排` AS b ON a.ITEM_CLASSROOMID = b.ITEM_CALSSROOM\r\nLEFT JOIN `t_课时安排` AS d ON (\r\n\tb.ITEM_HOUR = d.sort\r\n\tAND a.ITEM_GRADE = d.nianji\r\n)\r\nLEFT JOIN `tlk_教师基础信息` AS c ON b.ITEM_TEACHER = c.ITEM_STAFFNUMBER\r\nWHERE\r\n\ta.ID = '" + 
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
					/*  62: 70 */         id + "'";
			/*  63: 71 */       PreparedStatement prepareStatement = conn.prepareStatement(sql);
			/*  64: 72 */       ResultSet resultSet = prepareStatement.executeQuery();
			/*  65: 73 */       list3 = new ArrayList();
			/*  66: 74 */       String week = "";
			/*  67: 76 */       while (resultSet.next())
			/*  68:    */       {
				/*  69: 77 */         if ((week == "") || (week.equals(resultSet.getString("ITEM_CALSS_TIME"))))
				/*  70:    */         {
					/*  71: 78 */           week = resultSet.getString("ITEM_CALSS_TIME");
					/*  72: 79 */           Map<String, String> mapp = new HashMap();
					/*  73: 80 */           String imagename = null;
					/*  74: 81 */           String path = null;
					/*  75: 82 */           if ((resultSet.getString("ITEM_TEACHERPATH") != null) && (resultSet.getString("ITEM_TEACHERPATH").length() > 10))
					/*  76:    */           {
						/*  77: 83 */             mapp = (Map)JsonUtils.jsonToPojo(resultSet.getString("ITEM_TEACHERPATH").substring(1, resultSet.getString("ITEM_TEACHERPATH").length() - 1), Map.class);
						/*  78: 84 */             imagename = (String)mapp.get("name");
						/*  79: 85 */             path = (String)mapp.get("path");
						/*  80: 86 */             path = sss + "://" + str2 + ":" + port + contextPath + (String)mapp.get("path");
					/*  81:    */           }
					/*  82: 88 */           list3.add(new Kecheng(resultSet.getString("start"), resultSet.getString("end"), 
							/*  83: 89 */             resultSet.getString("keshi"), resultSet.getString("ITEM_SUB_NAME"), 
							/*  84: 90 */             resultSet.getString("ITEM_WEEKS"), resultSet.getString("ITEM_NAME"), imagename, path));
				/*  85:    */         }
				/*  86:    */         else
				/*  87:    */         {
					/*  88: 93 */           CurriculumSchedule keshi = new CurriculumSchedule(week, list3);
					/*  89: 94 */           list.add(keshi);
					/*  90: 95 */           list3 = new ArrayList();
					/*  91: 96 */           week = resultSet.getString("ITEM_CALSS_TIME");
					/*  92: 97 */           resultSet.previous();
				/*  93:    */         }
				/*  94: 99 */         if (resultSet.isLast())
				/*  95:    */         {
					/*  96:100 */           CurriculumSchedule keshi = new CurriculumSchedule(week, list3);
					/*  97:101 */           list.add(keshi);
				/*  98:    */         }
			/*  99:    */       }
			/* 100:104 */       resultSet.close();
			/* 101:105 */       prepareStatement.close();
			/* 102:106 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "访问成功", list)));
		/* 103:    */     }
		/* 104:    */     catch (SQLException e)
		/* 105:    */     {
			/* 106:109 */       ResponseUtils.renderJson(response, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/* 107:110 */       e.printStackTrace();
		/* 116:    */     }
		/* 117:    */     finally
		/* 118:    */     {
			/* 119:    */       try
			/* 120:    */       {
				/* 121:113 */         conn.close();
			/* 122:    */       }
			/* 123:    */       catch (SQLException e)
			/* 124:    */       {
				/* 125:116 */         e.printStackTrace();
			/* 126:    */       }
		/* 127:    */     }
	/* 128:    */   }
/* 129:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.classpackage.ClassCurriculumScheduleDemo
 * JD-Core Version:    0.7.0.1
 */