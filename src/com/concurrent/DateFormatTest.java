package com.concurrent;

import com.iterator.ArrayList_;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年10月08日 16:26
 */
public class DateFormatTest {

    private static ThreadLocal<DateFormat> thl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static List<DateFormat> ar = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 11; i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    DateFormat dateFormat = thl.get();
                    ar.add(dateFormat);
                    System.out.println(dateFormat.parse("2021-12-" + finalI + " 22:22:22"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {
                    // ThreadLocal使用完毕记得remove避免内存泄漏
                    thl.remove();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("1111111111");
    }

}
