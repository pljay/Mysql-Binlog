package cn.wizool.android.servlet.classpackage;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wizool.android.JDBC.Mysql;
import cn.wizool.android.utils.HttpUtils;
import cn.wizool.android.utils.JsonUtils;
import cn.wizool.android.utils.ResponseUtils;
import cn.wizool.android.utils.TaotaoResultUtils;

/**
 * @author PLJay
 * @category 学生评比
 * Servlet implementation class StudentAppraisal
 */
public class StudentAppraisal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("placeid");
		String sql="SELECT\r\n" + 
				"	js.ID,\r\n" + 
				"	j.ITEM_CLASSNUMBER,\r\n" + 
				"	s.ITEM_STUDENTNAME,\r\n" + 
				"	b.ITEM_OPTIONNAME,\r\n" + 
				"	a.ITEM_STUDENTID,\r\n" + 
				"	a.ITEM_ADDORSUB,\r\n" + 
				"	a.ITEM_FRACTION,\r\n" + 
				"	ITEM_APPRAISETITLE,\r\n" + 
				"	a.ITEM_APPRAISEDETAIL,\r\n" + 
				"	a.ITEM_APPRAISEPICTURES,\r\n" + 
				"	b.ID AS type_id,\r\n" + 
				"	b.ITEM_SCHOOLID\r\n" + 
				"FROM\r\n" + 
				"	`tlk_班级基础信息` AS J\r\n" + 
				"LEFT JOIN `tlk_教室管理` AS js ON j.ITEM_CLASSROOMID = js.ITEM_NUMBER\r\n" + 
				"LEFT JOIN tlk_student AS s ON j.ID = s.ITEM_CLASSID\r\n" + 
				"LEFT JOIN `tlk_学生评比` AS a ON s.ID = a.ITEM_STUDENTID\r\n" + 
				"LEFT JOIN `tlk_学生评比设置` AS b ON a.ITEM_APPRAISETYPE = b.ID\r\n" + 
				"WHERE\r\n" + 
				"	js.ID = '"+id+"'"; 
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List< Map<String, String>> list=new ArrayList<>();
			while(resultSet.next()) {
				Map<String, String> map=new LinkedHashMap<>();
				map.put("id",resultSet.getString("id"));
				map.put("class_number",resultSet.getString("item_classnumber"));
				map.put("student_name",resultSet.getString("item_studentname"));
				map.put("option_name",resultSet.getString("item_optionname"));
				map.put("student_id",resultSet.getString("item_studentid"));
				map.put("addor_sub",resultSet.getString("item_addorsub"));
				map.put("fraction",resultSet.getString("item_fraction"));
				map.put("appraise_title",resultSet.getString("item_appraisetitle"));
				map.put("appraise_detail",resultSet.getString("item_appraisedetail"));
				Map<String, String> mapp = new HashMap<String, String>();
				String imagename = null;
				String path = null;
				if ((resultSet.getString("ITEM_APPRAISEPICTURES") != null) && (resultSet.getString("ITEM_APPRAISEPICTURES").length() > 5))
				{
					mapp = JsonUtils.jsonToPojo(resultSet.getString("ITEM_APPRAISEPICTURES").substring(1, resultSet.getString("ITEM_APPRAISEPICTURES").length() - 1), Map.class);
					imagename = (String)mapp.get("name");
					path = (String)mapp.get("path");
					path = URLDecoder.decode(path, "utf-8");
					path = HttpUtils.getServerPath(request)+ (String)mapp.get("path");
				}
				map.put("image_name", imagename);
				map.put("image_path", path);
				map.put("type_id",resultSet.getString("type_id"));
				map.put("SCHOOL_ID",resultSet.getString("ITEM_SCHOOLID"));
				list.add(map);
			}
			resultSet.close();
			prepareStatement.close();
			ResponseUtils.renderJson(response,JsonUtils.objectToJson(TaotaoResultUtils.ok(list)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ResponseUtils.renderJson(response, JsonUtils.objectToJson(TaotaoResultUtils.build(e.getErrorCode(), e.getMessage(), e.getStackTrace())));
			e.printStackTrace();
		}
	}

}
