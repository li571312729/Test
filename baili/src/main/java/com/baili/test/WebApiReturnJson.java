package com.baili.test;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoqiangli
 * @Date 2021-11-16
 */
public class WebApiReturnJson {

    public static void main(String[] args) throws Exception {

        //外接口URL路径拼接
        String urlStr = "http://webapi.soa.ctripcorp.com/api/17679/getNeedTimesOn2021TrainTravelTask?UserId=M2313222785";
        //链接URL
        URL url=new URL(urlStr);
        //返回结果集
        StringBuffer document = new StringBuffer();
        //创建链接
        URLConnection conn = url.openConnection();
        //读取返回结果集
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String line = null;
        while ((line = reader.readLine()) != null){
            document.append(line);
        }
        reader.close();

        JSONObject json =JSONObject.parseObject(document.toString());

        //获取json中某个对象

        String str =(String)json.get("requestParams");
        System.out.println(str);

        //由于requestParams包含全部str字符串数据,现将str转Map
        String[] strs = str.split("&");
        Map<String, String> m = new HashMap<String, String>();
        for(String s:strs){
            String[] ms = s.split("=");
            m.put(ms[0], ms[1]);
        }
        System.out.println(m.get("minlon"));

        //获取json串中具体值  后期可根据key随意调取存入数据库
        String obj = (String)json.get("requestTime");
        System.out.println(obj);

    }
}