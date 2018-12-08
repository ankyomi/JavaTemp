
public class Person{
<#list list as obj>
 private ${obj.DATA_TYPE}  ${obj.COLUMN_NAME};
</#list>
	
<#list list as obj>
public void set${obj.COLUMN_NAME?cap_first}(${obj.DATA_TYPE}  ${obj.COLUMN_NAME}){
	this.${obj.COLUMN_NAME} = ${obj.COLUMN_NAME};
}
</#list>

<#list list as obj>
public ${obj.DATA_TYPE} get${obj.COLUMN_NAME?cap_first}(){
	return ${obj.COLUMN_NAME};
}
</#list>
}
