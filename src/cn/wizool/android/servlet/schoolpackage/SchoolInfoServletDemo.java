/*   1:    */ package cn.wizool.android.servlet.schoolpackage;
/*   2:    */ 
/*   3:    */ import cn.wizool.android.JDBC.Mysql;
/*   4:    */ import cn.wizool.android.servletmode.PictureMode;
/*   5:    */ import cn.wizool.android.servletmode.SchoolInfoMode;
/*   6:    */ import cn.wizool.android.utils.JsonUtils;
/*   7:    */ import cn.wizool.android.utils.ResponseUtils;
/*   8:    */ import cn.wizool.android.utils.TaotaoResultUtils;
/*   9:    */ import com.google.gson.Gson;
/*  10:    */ import com.google.gson.reflect.TypeToken;
/*  11:    */ import java.io.IOException;
/*  12:    */ import java.io.PrintStream;
/*  13:    */ import java.lang.reflect.Type;
/*  14:    */ import java.sql.Connection;
/*  15:    */ import java.sql.PreparedStatement;
/*  16:    */ import java.sql.ResultSet;
/*  17:    */ import java.sql.SQLException;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.Iterator;
/*  20:    */ import java.util.List;
/*  21:    */ import javax.servlet.ServletException;
/*  22:    */ import javax.servlet.http.HttpServlet;
/*  23:    */ import javax.servlet.http.HttpServletRequest;
/*  24:    */ import javax.servlet.http.HttpServletResponse;
/*  25:    */ 
/*  26:    */ public class SchoolInfoServletDemo
/*  27:    */   extends HttpServlet
/*  28:    */ {
	/*  29:    */   private static final long serialVersionUID = 1L;
	/*  30:    */   
	/*  31:    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			/*  32:    */     throws ServletException, IOException
	/*  33:    */   {
		/*  34: 50 */     String schoolname = req.getParameter("schoolname");
		/*  35: 51 */     System.out.println("111111" + schoolname);
		/*  36:    */     
		/*  37: 53 */     String sql = "select * from tlk_校园信息  where item_campusname ='" + schoolname + "'";
		/*  38:    */     
		/*  39: 55 */     Connection conn = Mysql.getInstance().getConnection();
		/*  40: 56 */     PreparedStatement pstmt = null;
		/*  41: 57 */     ResultSet ret = null;
		/*  42: 58 */     SchoolInfoMode sim = null;
		/*  43: 59 */     List<SchoolInfoMode> list = new ArrayList();
		/*  44: 60 */     String str2 = req.getServerName();
		/*  45: 61 */     int port = req.getServerPort();
		/*  46: 62 */     String sss = req.getScheme();
		/*  47: 63 */     String contextPath = req.getContextPath();
		/*  48:    */     try
		/*  49:    */     {
			/*  50: 65 */       pstmt = conn.prepareStatement(sql);
			/*  51: 66 */       ret = pstmt.executeQuery();
			/*  52: 68 */       while (ret.next())
			/*  53:    */       {
				/*  54: 69 */         sim = new SchoolInfoMode();
				/*  55: 70 */         String id = ret.getString("id");
				/*  56: 71 */         sim.setId(id);
				/*  57: 72 */         System.out.println(ret.getString("ITEM_CAMPUSNAME"));
				/*  58: 73 */         sim.setSchool_name(ret.getString("ITEM_CAMPUSNAME"));
				/*  59: 74 */         sim.setSchool_motto(ret.getString("ITEM_CAMPUSTRA"));
				/*  60: 75 */         System.out.println(id);
				/*  61: 76 */         String sql2 = "select * from tlk_logo where item_schoolid='" + id + "'";
				/*  62: 77 */         PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				/*  63: 78 */         ResultSet ret2 = pstmt2.executeQuery();
				/*  64: 79 */         List<PictureMode> list2 = new ArrayList();
				/*  65:    */         Iterator localIterator;
				/*  66: 80 */         for (; ret2.next(); localIterator.hasNext())
				/*  67:    */         {
					/*  68: 81 */           String str = ret2.getString("item_logo");
					/*  69: 82 */           Type type = new TypeToken() {}.getType();
					/*  70: 84 */           Gson gson = new Gson();
					/*  71: 85 */           List<PictureMode> list1 = (List)gson.fromJson(str, type);
					/*  72: 86 */           PictureMode nd = null;
					/*  73: 87 */           localIterator = list1.iterator();
											PictureMode student = (PictureMode)localIterator.next();
					/*  74: 88 */           nd = new PictureMode();
					/*  75: 89 */           String name = student.getName().toString();
					/*  76: 90 */           nd.setName(name);
					/*  77: 91 */           String picpath = student.getPath().toString();
					/*  78: 92 */           String path = sss + "://" + str2 + ":" + port + contextPath + picpath;
					/*  79: 93 */           nd.setPath(path);
					/*  80: 94 */           list2.add(nd);
											continue;	
				/*  81:    */         }
				/*  82: 97 */         ret2.close();
				/*  83: 98 */         pstmt2.close();
				/*  84: 99 */         sim.setSchool_picture(list2);
				/*  85:100 */         list.add(sim);
			/*  86:    */       }
			/*  87:102 */       ret.close();
			/*  88:103 */       pstmt.close();
		/*  89:    */     }
		/*  90:    */     catch (SQLException e)
		/*  91:    */     {
			/*  92:107 */       ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(e.getErrorCode()), e.getMessage(), e)));
			/*  93:108 */       e.printStackTrace();
		/* 102:    */     }
		/* 103:    */     finally
		/* 104:    */     {
			/* 105:    */       try
			/* 106:    */       {
				/* 107:111 */         conn.close();
			/* 108:    */       }
			/* 109:    */       catch (SQLException e)
			/* 110:    */       {
				/* 111:114 */         e.printStackTrace();
			/* 112:    */       }
		/* 113:    */     }
		/* 114:117 */     ResponseUtils.renderJson(resp, "application/json", JsonUtils.objectToJson(TaotaoResultUtils.build(Integer.valueOf(200), "ok", list)));
	/* 115:    */   }
/* 116:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.servlet.schoolpackage.SchoolInfoServletDemo
 * JD-Core Version:    0.7.0.1
 */