package com.baili.software.check;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * java 读注册表获取电脑安装软件
 * @author www
 *
 */
public class A {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		process = runtime.exec(
				"cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
		in.readLine();
		in.readLine(); 

		String string = null;
		while ((string = in.readLine()) != null) {
			String name = string.replaceAll("DisplayName    REG_SZ    ", "");
			System.out.println(name.trim());
		} 
		
/*		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		// process = runtime.exec("cmd /c reg query
		// HKEY_LOCAL_MACHINE//SOFTWARE//Microsoft//Windows//CurrentVersion//Uninstall//");
		process = runtime.exec(
				"cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
		String string = null;

		String nameString = "";
		
		BufferedReader br = null;
		while ((string = in.readLine()) != null) {

			process = runtime.exec("cmd /c reg query " + string + " /v DisplayName");
			br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
			br.readLine();
			br.readLine();// 去掉前两行无用信息
			if ((nameString = br.readLine()) != null) {
				nameString = nameString.replaceAll("DisplayName    REG_SZ    ", ""); // 去掉无用信息
				
				System.out.println(nameString);//名称
				System.out.println(string);//注册表路径

			}

		}*/
	}
	
	
	

}
