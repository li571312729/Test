package com.angorithm;

public class 时间差 {

    public static void main(String[] args) {
        System.out.println(daysBetweenDates("2019-06-12", "2019-06-30"));
        System.out.println(dayOfTheWeek(9, 10, 2020));
    }


    public static boolean runnian(int year){
        if(( year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }

    public static int number(String s){
        char[] chars = s.toCharArray();

        int[] cnt = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year=(chars[0] - '0') * 1000 + (chars[1] - '0') * 100 + (chars[2] - '0') * 10 + (chars[3] - '0') * 1;
        int month=(chars[5] - '0')* 10 + (chars[6] - '0') * 1;
        int days=(chars[8] - '0') * 10 + (chars[9] - '0') * 1;
        
        if(runnian(year)==true) {
            cnt[1]=29;
        } else {
            cnt[1]=28;
        }

        for(int i=1;i<month;i++){
            days+=cnt[i-1];
        }

        for(int i=1971;i<year;i++){
            if(runnian(i)==true) {
                days+=366;
            } else {
                days+=365;
            }
        }
        return days;
    }

    static int daysBetweenDates(String date1, String date2) {
        return Math.abs(number(date1) - number(date2));
    }


    static String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            year -= 1;
            month += 12;
        }
        return weeks[(day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400 + 1) % 7];
    }

}
