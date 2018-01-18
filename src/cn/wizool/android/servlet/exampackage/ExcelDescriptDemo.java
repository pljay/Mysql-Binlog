package cn.wizool.android.servlet.exampackage;

import cn.wizool.android.JDBC.Mysql;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelDescriptDemo
extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException
	{
		String placeid = req.getParameter("placeid");
		System.out.println(placeid);
		String sql = "SELECT\r\n"
				+ "*  FROM\r\n"
				+ "`tlk_教室管理` AS a\r\n"
				+ "LEFT JOIN `tlk_班级基础信息` AS b ON a.ITEM_NUMBER = b.ITEM_CLASSROOMID\r\n"
				+ "LEFT JOIN `tlk_考试信息上传` AS c ON b.ID = c.ITEM_CLASSID\r\n"
				+ "WHERE\r\n\ta.ID = '" + placeid + "'\r\n" + 
				"ORDER BY a.created DESC";
		Connection conn = Mysql.getInstance().getConnection();
	}
}