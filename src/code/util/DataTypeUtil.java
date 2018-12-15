package code.util;

public class DataTypeUtil {

	public static String fieldType(String type) {
		String rType = null;
		switch(type.toUpperCase()) {
		 case "INT":
			 rType = "int";
             break;
         case "VARCHAR":
        	 rType = "String";
             break;
         case "CHAR":
        	 rType = "String";
             break;
         case "DATE":
        	 rType = "Date";
             break;
         case "BIGINT":
        	 rType = "Long";
             break;
         default:
        	 rType = "String";
             break;
		}
		return rType;
	}
}
