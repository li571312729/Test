package com.baili.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class IpTest {

	public static void main(String[] args) {
	    System.out.println(new Date());
		String ip = getLocalHostIp();
		System.out.println(ip);
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
				Enumeration<InetAddress> addresses = ni.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = addresses.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
						ips.add(ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ips;
	}
}
