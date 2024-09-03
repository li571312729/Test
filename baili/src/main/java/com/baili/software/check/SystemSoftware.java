package com.baili.software.check;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemSoftware {  
	
	private static List<String[]> list = new ArrayList<>();
 
	public static void main(String[] args){
	 SystemSoftware systemSoftware = new SystemSoftware();
	    try
	    {
	      systemSoftware.check();
	      for (String[] result : list) {
	        System.out.println("软件名称:" + result[0].trim() + "，版本号:" + result[1].trim() + "，出版商:" + 
	          result[2].trim() + "，卸载路径:" + result[3].trim());
	      }
	      String software = systemSoftware.install();
	      System.out.println(software);
	    }catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println(0);
	    }
	}
	
	/**
	 * @param string 
	 * @return
	 */
	private boolean numberCheck(String string){
		String regex="^[+-]?\\d+(\\.\\d+)?$";	//判断字符串是否为数字
		return string.matches(regex);
	}
	
	private String install() throws Exception {
		String GoogleChrome = "";
		String VLC = "";
		String WebCom = "";
		String WebComkit = "";
		for(String[] result : list){
			if(result[0].trim().contains("Google Chrome")){
				String version = result[1].trim().split("\\.")[0];
				if(numberCheck(version)){
					if(Integer.parseInt(version) <= 44){
						GoogleChrome = "Google Chrome";
					}
				}
			}
			if(result[0].trim().contains("VLC")){
				VLC = "VLC";
			}
			if(result[0].trim().contains("Web Components") && !result[0].trim().contains("Kit")){
				WebCom = "Web Components";
			}
			if(result[0].trim().contains("Web Components Kit")){
				WebComkit = "Web Components Kit";
			}
		}
		String software = "";
	    if("".equals(GoogleChrome)){
	    	software += "Google ";
	    }
       if("".equals(VLC)){
	    	software += "VLC ";
       }
       if("".equals(WebCom)){
	    	software += "Components ";
       }
       if("".equals(WebComkit)){
	    	software += "Kit ";
       }   
       return software += ieVersioncheck();
	}

	/**
	 * @author Lixq 
	 * @param softwarename
	 * @return absolutePath of system
	 */
	@SuppressWarnings("unused")
	private String pathDel(String name){
		File file = new File("src/com/baili/software/check/" + name);  //a.txt
        System.out.println(file.getAbsolutePath());
        String relativepath = file.getAbsolutePath().replace("\\", "\\\\");
        String absolutePath = "\"" + relativepath + "\"";
		return absolutePath;
	}
	
	/**
	 * @author Lixq 
	 * @param 
	 * @return 
	 */
	private String ieVersioncheck() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime
                .exec("cmd /c reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Internet Explorer\"");
        BufferedReader in = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        String string = null;
        String svcVersion = null;		//ie 10以上是在该字段中，，，所以先找这个字段
        String svcUpdateVersion = null;	//高版本的更新版本，，只是10.xx.xx  ,,,前面的大版本和svcVersion一致
        String Version = null;			//当前面两个字段都没有value，再读取这个字段
        while ((string = in.readLine()) != null) {
            System.out.println(string);
        	if(string.contains("svcVersion    REG_SZ")){
        		svcVersion = string.replaceAll("svcVersion    REG_SZ    ", "");    //去掉无用信息      
            	if(svcVersion != null && svcVersion.trim().length() != 0){
            		break;
            	}
        	}
        	if(string.contains("Version    REG_SZ")){
        		Version = string.replaceAll("svcVersion    REG_SZ    ", "");    //去掉无用信息      
        	}
        	if(string.contains("svcUpdateVersion    REG_SZ")){
        		svcUpdateVersion = string.replaceAll("svcVersion    REG_SZ    ", "");    //去掉无用信息      
        	}
        }
        in.close();
        process.destroy();
        String realVersion = null;
        realVersion = (svcVersion == null || svcVersion.trim().length() == 0) ? ((svcUpdateVersion == null || svcUpdateVersion.trim().length() == 0) ? Version : svcUpdateVersion) : svcVersion;
        String result = "ie";
        if(realVersion != null && realVersion.trim().length() !=0){
        	realVersion = realVersion.trim().split("\\.")[0];
        	if(numberCheck(realVersion)){
				if(Integer.parseInt(realVersion) >= 11){	//ie版本限制为 11及以上
					result = result.replaceAll("ie", ""); 
				}
			}
        }
        return result;
    }
	
	private void check() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime
                .exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
        BufferedReader in = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        String string = null;
        while ((string = in.readLine()) != null) {
            String[] message = queryValue(string);
            if(message!=null) 
            	list.add(message);
        }
        in.close();
        process.destroy();
    }

    //具体查询每一个软件的详细信息
    private String[] queryValue(String string) throws IOException {
        String nameString = "";
        String versionString = "";
        
        String publisherString="";
        String uninstallPathString = "";
        
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        BufferedReader br = null;
        
        process = runtime.exec("cmd /c reg query \"" + string + "\"");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
    	System.out.println("<***************>start to search software:"+string+"<***************>");
    	
    	String line = null;
        while((line = br.readLine()) != null){
        	if(line.contains("DisplayName")){
            	System.out.println(line);
            	String tempNmae = line.replaceAll("DisplayName    REG_SZ    ", "");    //去掉无用信息      
            	if(checkExists(tempNmae))
            		nameString= tempNmae;
            	else
            		continue;
        	}
        	if(line.contains("DisplayVersion")){
            	System.out.println(line);
                versionString=line.replaceAll("DisplayVersion    REG_SZ    ", "");    //去掉无用信息
        	}
        	if(line.contains("Publisher")){
            	System.out.println(line);
                publisherString =line.replaceAll("Publisher    REG_SZ    ", "");    //去掉无用信息
        	}
        	if(line.contains("UninstallString")){
            	System.out.println(line);
                uninstallPathString=line.replaceAll("UninstallString    REG_SZ    ", "");    //去掉无用信息
        	}
        }
        br.close();
        process.destroy();
        
    	System.out.println("<***************>end to search software:"+string+"<***************>\n");

        String[] resultString = null;
        if(!"".equals(nameString)){
        	resultString = new String[4];
	        resultString[0]= nameString ;//== null ? null : new String(nameString.getBytes(),"GB-2312");
	        resultString[1]= versionString ;//== null ? null : new String(versionString.getBytes(),"GB-2312");
	        resultString[2]= publisherString ;//== null ? null : new String(publisherString.getBytes(),"GB-2312");
	        resultString[3]= uninstallPathString ;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312");
        }
        return resultString;
    }
    
    public boolean checkExists(String name){
    	if(name.contains("Google Chrome") || name.contains("VLC") || name.contains("Web Components") || name.contains("Web Components Kit"))
    		return true;
    	return false;
    }
}
