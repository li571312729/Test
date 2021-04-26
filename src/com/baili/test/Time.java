package com.baili.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Time {

	public static byte[] intToByte4B(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n >> 24 & 0xff); //数据组起始位,存放内存起始位, 即:高字节在前
		b[1] = (byte) (n >> 16 & 0xff); //高字节在前是与java存放内存一样的, 与书写顺序一样
		b[2] = (byte) (n >> 8 & 0xff);
		b[3] = (byte) (n & 0xff);
		return b;
	}

	public static int byteBE2Int(byte[] b) {
		int s = 0;
		s = ((((b[0] & 0xff) << 24 | (b[1] & 0xff)) << 16) | (b[2] & 0xff)) << 8| (b[3] & 0xff);
		return s;
	}
	
	public static void main(String[] args) {
		byte[] bytes = intToByte4B(21);
		byteBE2Int(bytes);
		System.out.println(bytes);

//		System.out.println("aa".getBytes().length);
//		timerDelay();
//		timerAppointed();
//		timerDelayAndApporinted();
	}

	/**
	 * int整数转换为4字节的byte数组
	 *
	 * @param i  整数
	 * @return byte数组
	 */
	public static byte[] intToByte4(int i) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (i & 0xFF);
		targets[2] = (byte) (i >> 8 & 0xFF);
		targets[1] = (byte) (i >> 16 & 0xFF);
		targets[0] = (byte) (i >> 24 & 0xFF);
		return targets;
	}

	//schedule(TimerTask task, long delay, long period) 延迟 delay 执行并每隔period 执行一次
	private static void timerDelayAndApporinted() {
		 for (int i = 0; i < 10; ++i) {
	            new Timer("timer - " + 5).schedule(new TimerTask() {
	                @Override
	                public void run() {
	                	System.out.println(Thread.currentThread().getName() + " go ");
	                }
	            }, 2000 , 3000);
	        }		
	}

	//schedule(TimerTask task, Date time) 特定時間執行
	private static void timerAppointed() {
		for (int i = 0; i < 10; ++i) {
            new Timer("timer - " + i).schedule(new TimerTask() {
                @Override
                public void run() {
                	System.out.println(Thread.currentThread().getName() + " run ");
                }
            }, new Date(System.currentTimeMillis() + 2000));
        }		
	}

	//schedule(TimerTask task, long delay) 延迟 delay 毫秒 执行
	private static void timerDelay() {
		for (int i = 0; i < 10; ++i) {
            new Timer("timer - " + i).schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }, 1000);
        }		
	}

}
