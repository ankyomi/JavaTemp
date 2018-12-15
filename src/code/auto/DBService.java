package code.auto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import code.util.DBUtil;

public class DBService {

	public List<Map<String,Object>> getTableName(DBUtil db,String dbName) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = db.getAll("select TABLE_NAME from information_schema.TABLES where table_schema='"+dbName+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public List<Map<String,Object>> getTableInfo(DBUtil db,String dbName,String tableName) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = db.getAll("select COLUMN_NAME,DATA_TYPE from information_schema.COLUMNS where table_schema='"+dbName+"' and table_name='"+tableName+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
