package com.baili.test;

import com.sun.deploy.util.StringUtils;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Administrator
 */
public class Java8Test {

    /**设置格式化模板**/
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");

    /**设置日期时区常量**/
    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static void main(String[] args) {
        Java8Test test = new Java8Test();
        // stream
         test.StreamTest();

        // 新的时间类(LocalDate、LocalDateTime)
        //test.date();

    }

    /**
     * 新的时间类(LocalDate、LocalDateTime)
     */
    @SneakyThrows
    private void date() {
        // Date格式化为DateTime
        Date date = new Date();
        LocalDateTime dateTime = date.toInstant().atZone(ZONE_ID).toLocalDateTime();
        System.out.println(dateTime);

        // LocalDate/LocalDateTime转Date
        // LocalDate
        LocalDate now = LocalDate.now();
        Date from = Date.from(now.atStartOfDay(ZONE_ID).toInstant());
        System.out.println(from);

        // LocalDateTime
        LocalDateTime nowT = LocalDateTime.now();
        Date from1 = Date.from(nowT.atZone(ZONE_ID).toInstant());
        System.out.println(from1);

        // 日期格式化
        // 注意LocalDate不包含时分秒，格式化到时分秒会出错
        System.out.println(now.format(DATE_TIME_FORMATTER));
        System.out.println(nowT.format(DATE_TIME_FORMATTER_TIMER));

        // 日期加减
        now = now.plusDays(1);
        now = now.plusMonths(-1);
        now = now.plusYears(2);
        System.out.println(now.format(DATE_TIME_FORMATTER));

        // 日期时间间隔
        // LocalDate
        LocalDate startDate2 = LocalDate.of(2019,07,01);
        LocalDate endDate2 = LocalDate.of(2019,07,02);
        Long withDay =  endDate2.toEpochDay() - startDate2.toEpochDay();
        System.out.println(withDay);

        // LocalDateTime
        LocalDateTime startDate = LocalDateTime.of(2019,07,01,12,12,22);
        LocalDateTime endDate = LocalDateTime.of(2019,07,02,12,12,22);
        Long withSecond =  endDate.atZone(ZONE_ID).toEpochSecond() - startDate.atZone(ZONE_ID).toEpochSecond();
        System.out.println(withSecond/60/60/24);

        // 第一天and最后一天
        // 当月第一天
        LocalDateTime dateTime1 = LocalDateTime.of(2019,07,03,12,12,22);
        dateTime1 = dateTime1.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(dateTime1);
        // 当月最后一天
        dateTime1 = dateTime1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(dateTime1);

        //当月的第几天
        dateTime1 = LocalDateTime.now();
        int dayOfMonth = dateTime1.getDayOfMonth();
        System.out.println(dayOfMonth);
        // 当前周的第几天
        int dayOfWeek = dateTime1.getDayOfWeek().getValue();
        System.out.println(dayOfWeek);
    }

    /**
     * stream
     */
    @SneakyThrows
    public void StreamTest(){
        String[] strArr = new String[]{"A","B","C","D"};
//        Arrays.stream(strArr).forEach(System.out::println);
//        IntStream.range(0,10).forEach(System.out::println);
        List<String> a = new ArrayList<>(5);
        a.add("hello");
        a.add("hello1");
        a.add("hello2");
        a.add("hello3");
        a.add("hello4");
        String join = StringUtils.join(a, ",");
        System.out.println(join);
    }

}
