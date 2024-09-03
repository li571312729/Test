package com.baili.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test12 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String ip = s.next();
        List<String> ips = new ArrayList<>();
        countIp(ip, 0, new ArrayList<>(), ips);
        System.out.println(ips);
    }
    
    public static void countIp(String ip, int pos, List<String> cur, List<String> ips){
        
        if(cur.size() >= 4){
            if(pos == ip.length()){
                ips.add(String.join(".", cur)); 
            }
            return;
        }
        
        for(int i = 1 ; i <= 3; i ++){
            if(pos + i > ip.length()){
                break;
            }
            
            String subStr = ip.substring(pos, pos +i);
            if(subStr.startsWith("0") && subStr.length() > 1 ||
                    (i == 3 && Integer.parseInt(subStr) > 255)){
                continue;
            }
            
            cur.add(subStr);
            countIp(ip, pos + i, cur, ips);
            cur.remove(cur.size() -1);
        }
    }
}
