package com.baili.test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IpV6V4 {

    public static void main(String[] args) throws UnknownHostException, SocketException {
        System.out.println("所有的网络接口及IP：");
        getAllInterface();  
        System.out.println("最终筛选的无线网络IP为:" + getLocalHostIp());
    }
    
    public static String getLocalHostIp() {
        String ip = null;
        String localWiredIp = "192.168.192.26";
        for(String localIp : getAllLocalIp()) {
            if(!localWiredIp.equals(localIp)) {
                ip = localIp;
                break;
            }
        }
        return ip;
    }

    public static List<String> getAllLocalIp() {
        List<String> ips = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                if(ni.getName().contains("wlan")){  //获取无线网络的地址
                    List<String> ipV4 = new ArrayList<String>();
                    List<String> ipV6 = new ArrayList<String>();
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress ip = addresses.nextElement();
                        if (!ip.isLoopbackAddress() && !ip.isLinkLocalAddress()) {  //环回地址 或者 链路地址不算在内
                           if(ip instanceof Inet4Address) {
                               ipV4.add(ip.getHostAddress());
                           }else if (ip instanceof Inet6Address){
                               ipV6.add(ip.getHostAddress());
                           }
                        }
                    }
                    ips.addAll(ipV6);
                    ips.addAll(ipV4);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("无线网络IP为 :" + ips);
        return ips;
    }
    
    public static void getAllInterface(){
        try {
            //获取所有接口，并放进枚举集合中，然后使用Collections.list()将枚举集合转换为ArrayList集合
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            ArrayList<NetworkInterface> arr = Collections.list(netInterfaces);
            for(Iterator<NetworkInterface> it = arr.iterator();it.hasNext();) {
             NetworkInterface ni = it.next();
             String intName = ni.getName(); //获取接口名
             //获取每个接口中的所有ip网络接口集合，因为可能有子接口
             ArrayList<InetAddress> inets = Collections.list(ni.getInetAddresses());
             for(Iterator<InetAddress> it1 = inets.iterator();it1.hasNext();) {
              InetAddress inet = it1.next();
             //if(!inet.isLoopbackAddress() && !inet.isLinkLocalAddress()){
                  //只筛选ipv4地址，否则会同时得到Ipv6地址
                  String ip = inet.getHostAddress();
                  if(inet instanceof Inet4Address) {
                       System.out.printf("%-10s %-5s %-6s %-15s\n", "InetfaceName:",intName,"| IPv4:",ip);
                  } else if (inet instanceof Inet6Address)
                  {
                      System.out.printf("%-10s %-5s %-6s %-15s\n", "InetfaceName:",intName,"| IPv6:",ip);
                  }
             // }
             }
            }
           } catch (SocketException s) {
            s.printStackTrace();
           }
    }

}
