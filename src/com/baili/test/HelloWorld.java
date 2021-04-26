package com.baili.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HelloWorld {

	public static void main(String[] args) {
		String a = "\\sds\\sds\\s\\ds";
		System.out.println(a);

		System.out.println(a.replaceAll("\\\\", "/"));
		
		List<String> aa = new ArrayList<String>();
		aa.add("aaaaa");
		Map<String, List> map = new LinkedHashMap<String, List>();
		map.put("aa", aa);
		System.out.println(map);
		aa.add("bbbb");
		System.out.println(map);
		
		String s = "你好";
		byte[] n = s.getBytes();
		for(byte bb : n)
			System.out.print(bb+":");
	}
	
	@Override
	public void finalize() {
        System.err.println("Hello World被回收了!");
    }

}
