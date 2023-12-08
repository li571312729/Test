package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        List<Integer> param = new ArrayList();
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            param.add(Integer.parseInt(split[i]));
        }
        System.out.println(badbad(param, count));
    }

    /**
     * 直接滑动窗口即可
     * @param ar
     * @param count
     * @return
     */
    private static String badbad(List<Integer> ar, int count) {
        int st = 0, end = count - 1;
        StringBuilder result = new StringBuilder();

        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i = 0; i <= end; i++) {
            queue.add(ar.get(i));
        }
        result.append(queue.peek()).append(",");

        while (true){
            queue.remove(ar.get(st));
            end ++;
            st ++;

            if(end >= ar.size()){
                break;
            }
            queue.add(ar.get(end));
            result.append(queue.peek()).append(",");
        }

        return result.deleteCharAt(result.lastIndexOf(",")).toString();
    }


}
