package code.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 
 * @author marnai  数据库工具
 *
 */
public class DBUtil {

	private  String DBUSER;
	private  String DBPWD;
	private  String URL;
	
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	public DBUtil() {
		try {
			Properties prop = new Properties();     
			InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(in);
			Class.forName(prop.getProperty("DRIVER"));
			DBUSER = prop.getProperty("USER");
			DBPWD = prop.getProperty("PWD");
			URL = prop.getProperty("URL");
			System.out.println("进行注册");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("注册失败");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, DBUSER, DBPWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Map<String,Object>> getAll(String sql) throws SQLException {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		pstmt = connection.prepareStatement(sql);
		
		resultSet  = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData(); // 获得列的结果  
		while(resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();  
            int cols_len = metaData.getColumnCount(); // 获取总的列数  
            for (int i = 0; i < cols_len; i++) {  
                String col_name = metaData.getColumnName(i + 1); // 获取第 i列的字段名称  
                                                                    // ,列计算从1开始  
                Object col_value = resultSet.getObject(col_name); // 获取第i列的内容值  
                if (col_value == null) {  
                    col_value = "";  
                }  
//                System.out.println(col_name + " : " + col_value);
                map.put(col_name, col_value);  
            }  
            list.add(map);  
		}
		return list;
	}
	
	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		db.getConnection();
		
		try {
//			db.getAll("select * from person");
//			db.getAll("select * from information_schema.TABLES where table_schema='demo'");
			List<Map<String,Object>> list = db.getAll("select COLUMN_NAME,DATA_TYPE from information_schema.COLUMNS where table_schema='demo' and table_name='person'");
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
