package com.baili.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public abstract class Test9 {

//        public static void main(String[] args) {
//            FileReader fr=null;
//            BufferedReader br=null;
//            try {
//                fr=new FileReader("D:\\test.txt");
//                br=new BufferedReader(fr);
//                String temp="";
//                while((temp=br.readLine())!=null){
//                    System.out.println(temp);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if(br!=null){
//                        br.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    if(fr!=null){
//                        fr.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
    public static void main(String[] args) throws IOException {
//        File file = new File("d:/test.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//
//        Properties properties = new Properties();
//        properties.load(reader);
//        Iterator<String> iterator = properties.stringPropertyNames().iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

      System.out.println(commonCharsss(new String[]{"agsdfg", "dfgg", "dghfsddg"}));
    }

    public static List<String> commonCharsss(String[] A) {
        String first = A[0];
        char[] chars = first.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            int cnt = 0;
            for (int j = 1; j < A.length; j++) {
                if (A[j].contains(aChar + "") && cnt <= 3) {
                    cnt++;
                    result.add(aChar + "");
                }
            }
        }
        return result;
    }

    public static List<String> commonChars(String[] s) {
        //定义并初始化两个数组arr和array
        int[] arr = new int[26];
        int[] array = new int[26];
        for(int i = 0;i<26;i++) {
            arr[i] = 100;
            array[i] = 0;
        }
        for(String str : s) {
            for(int i = 0;i<26;i++) {
                array[i] = 0;
            }
            //统计每个字符出现的次数
            for(int i = 0;i<str.length();i++) {
                ++array[str.charAt(i) - 'a'];
            }

            for(int i = 0;i<26;i++) {
                arr[i] = Math.min(arr[i], array[i]);
            }
        }
        List<String> list = new ArrayList<String>();
        for(int i = 0;i<26;i++) {
            for(int j = 0;j<arr[i];j++) {
                String ch = "";
                ch+=(char)('a'+i);
                list.add(ch);
            }
        }
        return list;
    }

}
