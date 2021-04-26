package com.baili.test;

import java.text.ParseException;

import Aladdin.Hasp;
import Aladdin.HaspStatus;
import Aladdin.HaspTime;
public class ProtectedDogTest {

	private static Hasp hasp;
	
	public static void main(String[] args) throws Exception {
		loginCheck(); //登录验证 判断有没有插入锁，以及该锁的加密是否失效  loginCheck对于登录次数来说会减少一次
		//readTimieFromDog();	  //获取锁内的时间返回的是TUC的long型时间
		//readSessionInfo();		//读取会话相关的信息
		//readInfo();	          //获取当前用户锁的各种信息
		writeHaspContent(5);
		readHaspContent(0, 10);
	    logOut();           //登 出。。加密锁登出。。  但是可以继续登录。。
	}
	
	public static void readHaspContent(Integer offset,Integer length) throws Exception {
        byte[] data = new byte[length];
        hasp.read(Hasp.HASP_FILEID_RW, offset, data);
        int status = hasp.getLastError();
        if (HaspStatus.HASP_STATUS_OK != status){
            throw new Exception();
        }            
        
        System.out.println(new String(data));
        String dump = dump(data);
        System.out.println(dump);
   }
	
	public static void writeHaspContent(Integer offset) throws Exception {
	    byte[] hexToByteArray = new byte[]{0x00,0x00,0x00,0x00,0x00};
	    
	    //byte[] hexToByteArray = "hello world!".getBytes();
	    hasp.write(Hasp.HASP_FILEID_RW, offset, hexToByteArray);
	    int status = hasp.getLastError();
	    if (HaspStatus.HASP_STATUS_OK != status){
            throw new Exception();
        }
   }
	
	/**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            // 奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            // 偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
	
	private static void readInfo() {
		String scope = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
				"<haspscope/>";

				String format = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
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

				String vendorCode = 
				"oVHwQHlg3chFAb5ay8SujGpmrrhiZBAgZaQvSsMhmbe+YkbkTiX12EpoYekqZY3Xf/I20kulz3OEBrDx" + 
				"6MyW2xBi3oD8D3BdjFEi6bRxLAzaBVWAWoTFWDJC9QSC1P8sYnx/OrzVaKBlEV/J6Z4/xjOO47M2wN3m" + 
				"mtKla0N6TCY5cS2ndgIIj7Mzgex0kTlUCuvip16F8Tohnrnnht5wxapJAZZ0YoClEk79IIr8LQTQiLp5" + 
				"mod0A128cnRI8MpPMdKrMQ1P9/rV8yniBtythX+ZvAo+cqNIjy70XrfQ7iMxdvZwKk5WQejAwGAtJtM0" + 
				"t5MXYm0+7iMdFx6nCKwvTVtWAHqZdHIojRNKyQ9WT64ZAgbpk4IOGcX3Ss/nuCgNZ2ZDBoRXPxvhOMaV" + 
				"vOXgR3e42PtCfHJcJ+3jyMJd87m4glOhXSvXqOgvhSRZ1/MUz+6IxrT3Jhb0ZKUzx6MSxPxfrkLRoOm9" + 
				"3SbrOPeozFZnz9/WYRiheopR6Qb5vvjuMJQgHHwqOgaBUmyJiPkpam4DwkKo2qHnt4lDsMSEA6eTw+H2" + 
				"k+FaDnkq6p+UORIAj5W5u9cjiyImU5vTndro4Jk8C9rRo1XgWY6B/zQvVzJj9CITHiF6G86J0jl2XadB" + 
				"wCKNvbycr+Ld9vyJSCx6mGFkx/8+3Jka3oldV5xTrBspe7f3nxIAA7OD6BEA5AFnfVpxCkDmfE4EwGp6" + 
				"mrQll8MW3WgG+pxQGk6HyMLKrytPHe3y19S8517/7Z0Tg7ZS/luyFjnSHomsEJe4mPKZl3l6zUwueSFf" + 
				"katHBCb/RjjgZ5cwAZ+b5pTsZghet9HJv5PpLYuFEROICA4QaieAH7ekt7JObdVwX9S6FEKDaZuwZej8" + 
				"7GWJtmVWFNN3tsBoYK0H8ntKpCbsnHYWeskOJk2W+X+lcNFjmZWGfYbTsvfR+nVba5tFYPIwUHM11yHP" + 
				"rCud90xEWvPjKbawouivTA==";

				String info;

				info = hasp.getInfo(scope, format, vendorCode);

				int status = hasp.getLastError();

				if (HaspStatus.HASP_STATUS_OK != status)
					System.out.println("读取失败");
				else
					System.out.println(info);	
				
	}

	private static void readSessionInfo() {
		String info;

		info = hasp.getSessionInfo(Hasp.HASP_SESSIONINFO);

		int status = hasp.getLastError();

		if (HaspStatus.HASP_STATUS_OK != status)
			System.out.println("读取失败");
		else
			System.out.println(info);

	}

	private static void readTimieFromDog() throws ParseException {
		HaspTime datetime = hasp.getRealTimeClock();

		int status = hasp.getLastError();

		if (HaspStatus.HASP_STATUS_OK != status)
			System.out.println("查询失败");
		else{
			System.out.println(datetime.getHaspTime());  //返回的是法国   的TUC时间
			System.out.println(datetime.getYear()+"/"+datetime.getMonth()+"/"+datetime.getDay()+" "+datetime.getHour()+
					":"+datetime.getMinute()+":"+datetime.getSecond());
			}
	}

	public static void loginCheck(){
		long feature = 3;	//这个配置的是功能ID
		String vendorCode = 
		"oVHwQHlg3chFAb5ay8SujGpmrrhiZBAgZaQvSsMhmbe+YkbkTiX12EpoYekqZY3Xf/I20kulz3OEBrDx" + 
		"6MyW2xBi3oD8D3BdjFEi6bRxLAzaBVWAWoTFWDJC9QSC1P8sYnx/OrzVaKBlEV/J6Z4/xjOO47M2wN3m" + 
		"mtKla0N6TCY5cS2ndgIIj7Mzgex0kTlUCuvip16F8Tohnrnnht5wxapJAZZ0YoClEk79IIr8LQTQiLp5" + 
		"mod0A128cnRI8MpPMdKrMQ1P9/rV8yniBtythX+ZvAo+cqNIjy70XrfQ7iMxdvZwKk5WQejAwGAtJtM0" + 
		"t5MXYm0+7iMdFx6nCKwvTVtWAHqZdHIojRNKyQ9WT64ZAgbpk4IOGcX3Ss/nuCgNZ2ZDBoRXPxvhOMaV" + 
		"vOXgR3e42PtCfHJcJ+3jyMJd87m4glOhXSvXqOgvhSRZ1/MUz+6IxrT3Jhb0ZKUzx6MSxPxfrkLRoOm9" + 
		"3SbrOPeozFZnz9/WYRiheopR6Qb5vvjuMJQgHHwqOgaBUmyJiPkpam4DwkKo2qHnt4lDsMSEA6eTw+H2" + 
		"k+FaDnkq6p+UORIAj5W5u9cjiyImU5vTndro4Jk8C9rRo1XgWY6B/zQvVzJj9CITHiF6G86J0jl2XadB" + 
		"wCKNvbycr+Ld9vyJSCx6mGFkx/8+3Jka3oldV5xTrBspe7f3nxIAA7OD6BEA5AFnfVpxCkDmfE4EwGp6" + 
		"mrQll8MW3WgG+pxQGk6HyMLKrytPHe3y19S8517/7Z0Tg7ZS/luyFjnSHomsEJe4mPKZl3l6zUwueSFf" + 
		"katHBCb/RjjgZ5cwAZ+b5pTsZghet9HJv5PpLYuFEROICA4QaieAH7ekt7JObdVwX9S6FEKDaZuwZej8" + 
		"7GWJtmVWFNN3tsBoYK0H8ntKpCbsnHYWeskOJk2W+X+lcNFjmZWGfYbTsvfR+nVba5tFYPIwUHM11yHP" + 
		"rCud90xEWvPjKbawouivTA==";
		hasp = new Hasp(feature);
		hasp.login(vendorCode);
		int status = hasp.getLastError(); 
		if (HaspStatus.HASP_STATUS_OK != status)
			System.out.println("登录失败");
		else
			System.out.println("登录成功");	
	}
	
	public static void logOut(){
		hasp.logout();
		int status = hasp.getLastError();
		if (HaspStatus.HASP_STATUS_OK != status)
			System.out.println("登出失败");
		else
			System.out.println("登出成功");

	}
	
	
	public static String dump(byte[] paramArrayOfByte)
    {
      byte[] arrayOfByte1 = new byte[16];
      byte[] arrayOfByte2 = { 0 };
      if (paramArrayOfByte.length == 0) {
        return null;
      }
      arrayOfByte1[0] = 0;
      int j = 0;
      String str2 = null;
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        byte k = paramArrayOfByte[i];
        if ((k < 32) || (k > 127)) {
          arrayOfByte1[j] = 46;
        } else {
          arrayOfByte1[j] = k;
        }
        if (j < 15) {
          arrayOfByte1[(j + 1)] = 0;
        }
        arrayOfByte2[0] = k;
        String str1 = toHexString(arrayOfByte2);
        //System.out.print(str1 + " "); //存入锁时，加密锁存入的内容
        j++;
        if (((j & 0x3) == 0) && (j < 15)) {
          //System.out.print("| ");
        }
        str2 = new String(arrayOfByte1);
        if (j > 15)
        {
          //System.out.println("[" + str2 + "]");
          j = 0;
          arrayOfByte1[0] = 0;
        }
      }
      if (j != 0)
      {
        str2 = new String(arrayOfByte1);
        //System.out.println(" [" + str2 + "]");
      }
      return str2;
    }
  
  private static String toHexString(byte[] paramArrayOfByte)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      
      int i = paramArrayOfByte.length;
      for (int j = 0; j < i; j++)
      {
        byte2hex(paramArrayOfByte[j], localStringBuffer);
        if (j < i - 1) {
          localStringBuffer.append(":");
        }
      }
      return localStringBuffer.toString();
    }
  
   private static void byte2hex(byte paramByte, StringBuffer paramStringBuffer)
    {
      char[] arrayOfChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
      
      int i = (paramByte & 0xF0) >> 4;
      int j = paramByte & 0xF;
      paramStringBuffer.append(arrayOfChar[i]);
      paramStringBuffer.append(arrayOfChar[j]);
    }
}
