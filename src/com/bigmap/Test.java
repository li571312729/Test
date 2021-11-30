package com.bigmap;

import java.io.*;

/**
 * @author xiaoqiangli
 * @Date 2021-11-30
 */
public class Test {

    public static void main(String[] args) {
        File f = new File("D:\\Users\\xiaoqiangli\\work\\qq.txt");
        if(!f.exists()){
            return;
        }

        MyBitMap map = new MyBitMap(500000000);

        try(
            BufferedReader reader = new BufferedReader(new FileReader(f));
        ) {
            String data = null;
            while ((data = reader.readLine()) != null){
                System.out.println("当前读取到的是：" + data);
                int number = Integer.parseInt(data);
                if(map.contains(number)){
                    System.out.println(data + "  已经存在");
                    map.showByte(map.bits[number >> 3]);
                    continue;
                }
                map.put(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
