package com.concurrent;

import lombok.SneakyThrows;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年08月20日 15:44
 */
public class TimerTest {

    /**
     * 当一个Timer运行多个TimerTask时，只要其中一个TimerTask在执行中向run方法外抛出了异常，则其他任务也会自动终止。
     * @author lxq
     * @date 2021/8/20 15:44
     * @param args
     */
    public static void main(String[] args) {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {

                System.out.println(11111);

                TimeUnit.SECONDS.sleep(1);
                throw new Exception("22222");
            }
        }, 500);

        t.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {


                TimeUnit.SECONDS.sleep(1);
                System.out.println(22222222);

            }
        }, 1000);
    }


}
