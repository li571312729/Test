package com.ip2region;

import com.chain.responsibility.Response;
import com.net.bio.talk.util.Utils;
import lombok.SneakyThrows;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiaoqiangli
 * @Date 2022-07-14
 */
public class FileReadtest {

    public static void main(String[] args) {
        // 1、创建 searcher 对象  dbPath = ip2region.xdb file path
        String dbPath = "ip2region.xdb";
        String path = "src/com/ip2region/" + dbPath;

        try {
            URL resource = SearcherTest.class.getResource(dbPath);
            BufferedInputStream content = (BufferedInputStream)resource.getContent();

            new Thread(() -> {
                try {
                    File file = new File(path);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileChannel channel = fileInputStream.getChannel();
                    while (true){
                        ByteBuffer data = ByteBuffer.allocate(1024);
                        int read = channel.read(data);
                        while (read > 0){
                            System.out.println(Thread.currentThread().getName() + "-read: " + new String(data.array()));
                            read = channel.read(data);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Src").start();


            new Thread(() -> {
                try {
                    InputStream inputStream = SearcherTest.class.getResourceAsStream(dbPath);
                    while (true){
                        byte[] b = new byte[1024];
                        while((inputStream.read(b, 0, b.length))!=-1){
                            System.out.println(Thread.currentThread().getName() + "-read: " + new String(b,"utf-8"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Classes").start();

        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }
    }

}

