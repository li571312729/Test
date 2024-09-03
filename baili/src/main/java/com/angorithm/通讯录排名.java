package com.angorithm;

import java.util.*;

/**
 * @author Administrator
 */
public class 通讯录排名 {

    /**
     * 逐个字母比较排序
     *
     * @param disorder
     * @return orderly
     */
    public static String[] nopTop(String[] disorder) {
        int index = 0;
        int end = disorder.length;
        String[] orderly = new String[end]; // 新建排序后的数组
        TreeMap<Character, List<String>> treeMap = new TreeMap<>(); // 排序工具类，key是按照大小排序的
        List<String> list = null;

        // 收集字符串首字母信息
        for (String str : disorder) {
            char cTag = str.charAt(0);

            list = treeMap.get(cTag);
            if (list == null) {
                list = new ArrayList<>(1);
            }
            list.add(str);
            treeMap.put(cTag, list);
        }

        // 通过收集的首字母信息给字符串排序
        Set<Character> keySet = treeMap.keySet();
        for (char cTag : keySet) {
            list = treeMap.get(cTag);
            if (list != null && list.size() > 0) {

                if (list.size() < 2) { // 如果只有一个字符串，不需要递归
                    for (String string : list) {
                        orderly[index] = string;
                        index++;
                    }
                } else { // 如果有两个以上字符串，开头字母相同，可能需要递归排序
                    ArrayList<String> arrayList = new ArrayList<>(0);
                    for (String string : list) {
                        if (string.length() > 1) { // 字符串长度大于2的，需要添加到list,准备下一次递归排序用到
                            arrayList.add(string.substring(1));
                        } else {
                            orderly[index] = string;
                            index++;
                        }
                    }
                    if (arrayList.size() > 1) {
                        String[] array = new String[arrayList.size()];
                        int i = 0;
                        for (String string : arrayList) {
                            array[i] = string;
                            i++;
                        }
                        array = nopTop(array); // 还需要递归排序 back background -> ack ackground
                        for (String string : array) {
                            orderly[index] = cTag + string;
                            index++;
                        }
                    } else if (arrayList.size() == 1) {
                        orderly[index] = cTag + arrayList.get(0);
                        index++;
                    }
                }
            }

        }
        treeMap.clear();
        return orderly; // 排序后的字符串数组
    }

    public static void main(String[] args) {
        String[] noP = { "#dsg", "apple", "car", "a", "family", "sky", "application", "app", "baby", "back", "background",
                "bad", "bbbbb", "bee", "cafe", "cake", "care" , "#a", "*gd"};
        String[] nopTop = nopTop(noP);
        List<String> strings = new ArrayList<>(Arrays.asList(nopTop));
        List<String> endName = new ArrayList<>();

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(!next.startsWith("#")){
                break;
            }

            endName.add(next);
            iterator.remove();
        }
        strings.addAll(endName);
        System.out.println(strings);
    }



}
