package cn.wizool.android.JDBC;  

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public final class Mysql {  
	//log日志

	//使用单利模式创建数据库连接池  
	private static Mysql instance;  
	private static ComboPooledDataSource dataSource;  
	public Connection conn = null;  
	public PreparedStatement pst = null;
	public static String url=""; 
	public static String ip=""; 
	public static int port; 
	public static String user="";
	public static String pass="";
	public static String CORPID="";
	public static String SECRET="";
	/*public static void main(String[] args) {
		new XMLReaderTest("D:\\Program Files\\apache-tomcat-7.0.53\\webapps\\obpm\\WEB-INF\\classes");
	}*/
	private Mysql() throws SQLException, PropertyVetoException {
		File f = new File(this.getClass().getResource("/").getPath());
		String url=f+"";
		//String url="D:\\Program Files\\apache-tomcat-7.0.53\\webapps\\obpm\\WEB-INF\\classes";
		new XMLReaderTest(url);
		dataSource = new ComboPooledDataSource();  
		dataSource.setUser(Mysql.user);     //用户名  
		dataSource.setPassword(Mysql.pass); //密码  
		dataSource.setJdbcUrl(Mysql.url);//数据库地址  
		//dataSource.setUser("root");     //用户名  
		//dataSource.setPassword("poiuyt"); //密码  
		//dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xiaoxuan?useUnicode=true&characterEncoding=utf8");//数据库地址  

		dataSource.setDriverClass("com.mysql.jdbc.Driver");  
		dataSource.setInitialPoolSize(10); //初始化连接数  
		dataSource.setMinPoolSize(1);//最小连接数  
		dataSource.setMaxPoolSize(15);//最大连接数  
		dataSource.setMaxStatements(0);//最长等待时间  
		dataSource.setMaxStatementsPerConnection(0);
		dataSource.setMaxIdleTime(60);//最大空闲时间，单位毫秒  
		dataSource.setIdleConnectionTestPeriod(2000);

	}  

	public static final Mysql getInstance() {  
		if (instance == null) {  
			try {    
				instance = new Mysql();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		}  
		return instance;  
	}  

	public synchronized final Connection getConnection() {  
		Connection conn = null;  
		try {  
			conn = dataSource.getConnection();
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
		return conn;  
	}
	public Mysql(String sql) {
		try {
			conn = Mysql.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
		}catch (Exception e) {  
			e.printStackTrace();  
		}
	}
	public void close() {  
		try {  
			this.pst.close();  
			this.conn.close();
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
	}

class XMLReaderTest {
	public XMLReaderTest(String url){
		url=url+"\\Mysql.xml";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println(url);
		Element element = null;
		// 可以使用绝对路劲
		File f = new File(url);
		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();
			// 得到一个DOM并返回给document对象
			Document dt = db.parse(f);
			// 得到一个elment根元素
			element = dt.getDocumentElement();
			// 获得根节点
			System.out.println("根元素：" + element.getNodeName());
			// 获得根元素下的子节点
			NodeList childNodes = element.getChildNodes();
			// 遍历这些子节点
			for (int i = 0; i < childNodes.getLength(); i++) {
				// 获得每个对应位置i的结点
				Node node1 = childNodes.item(i);

				if ("Mysql".equals(node1.getNodeName())) {
					Mysql.url=node1.getAttributes().getNamedItem("url").getNodeValue()+"?Unicode=true&characterEncoding=UTF-8";
					System.out.println(Mysql.url);
					Mysql.user=node1.getAttributes().getNamedItem("user").getNodeValue();
					Mysql.pass=node1.getAttributes().getNamedItem("pass").getNodeValue();
					Mysql.ip=node1.getAttributes().getNamedItem("ip").getNodeValue();
					Mysql.port=Integer.parseInt(node1.getAttributes().getNamedItem("port").getNodeValue());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
}

/*String className = Thread.currentThread().getStackTrace()[2].getClassName();  
String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();  
int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();  
System.out.println(className);  
System.out.println(methodName);  
System.out.println(lineNumber); */
