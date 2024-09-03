package com.baili.test;

public class xmlStringToMap {

	public static void main(String[] args) {
		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
				"<haspformat root=\"hasp_info\">" + 
				"    <feature>" + 
				"        <attribute name=\"id\" />" + 
				"        <attribute name=\"usable\" />" + 
				"        <attribute name=\"name\" />" + 
				"        <element name=\"license\" />" + 
				"        <hasp>" + 
				"          <attribute name=\"id\" />" + 
				"          <attribute name=\"type\" />" + 
				"        </hasp>" + 
				"    <product>" + 
				"        <attribute name=\"id\" />" +
				"        <attribute name=\"name\" />" +
				"        <attribute name=\"detachable\" />" +
				"    </product>" + 
				"    </feature>" + 
				"    <session>" + 
				"        <attribute name=\"ip\" />" +
				"        <attribute name=\"apiversion\" />" +
				"    </session>" + 
				"    <vendor>" + 
				"        <attribute name=\"id\" />" +
				"        <attribute name=\"name\" />" +
				"    </vendor>" + 
				"</haspformat>" + 
				"";	
		System.out.println(s);
	}
	
	public static void method(String str){
		
	}

}
