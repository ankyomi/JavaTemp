package code.auto;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.util.AutoCode;
import code.util.DBUtil;
import code.util.DataTypeUtil;

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
		DBService dbService = new DBService();
		db.getConnection();
		
		List<Map<String, Object>> tableName = dbService.getTableName(db, db.getDataName());
		if(tableName !=null && tableName.size()>0) {
			for(Map<String,Object> table : tableName) {
				String tName = (String) table.get("TABLE_NAME");
				System.out.println("tableName : " + tName);
				List<Map<String, Object>> list =  dbService.getTableInfo(db, db.getDataName(),tName);
				if(list !=null && list.size()>0) {
					List<Map<String, Object>> param = new ArrayList<Map<String,Object>>();
					for(Map<String,Object> m : list) {
						Map<String,Object> map = new HashMap<String,Object>();
						
						map.put("COLUMN_NAME",(String) m.get("COLUMN_NAME"));
						map.put("DATA_TYPE", DataTypeUtil.fieldType((String) m.get("DATA_TYPE")));
						param.add(map);
					}
					root.put("list", param);
					root.put("tableName", tName);
					ac.auto("02.ftl", root, PATCH+"/"+upperCase(tName)+ ".java");
				}
			}
		}
		//关闭数据库连接
		db.close();
	}
	//首字母大写
    public static String upperCase(String str) {
    	return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
