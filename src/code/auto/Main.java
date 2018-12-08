package code.auto;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.util.DBUtil;

public class Main {

	private static final String PATCH = "E:/JavaTemp/test";

	public static void main(String[] args) {

		File file = new File(PATCH);

		if (!file.exists()) {
			file.mkdirs();
		}

		AutoCode ac = new AutoCode();
		HashMap<String, Object> root = new HashMap<String, Object>();
		// root.put("username", "123");
		// ac.fprint("01.ftl", root, PATCH + "/01.html");
		DBUtil db = new DBUtil();
		db.getConnection();
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = db.getAll("select COLUMN_NAME,DATA_TYPE from information_schema.COLUMNS where table_schema='demo' and table_name='person'");
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Map<String, Object>> param = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> m : list) {
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("COLUMN_NAME",(String) m.get("COLUMN_NAME"));
			if("VARCHAR".equalsIgnoreCase((String) m.get("DATA_TYPE"))) {
				map.put("DATA_TYPE", "String");
			}else {
				map.put("DATA_TYPE", (String) m.get("DATA_TYPE"));
			}
			
			param.add(map);
		}
		root.put("list", param);
		ac.auto("02.ftl", root, PATCH + "/01.java");

	}

}
