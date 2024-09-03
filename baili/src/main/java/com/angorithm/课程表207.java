package com.angorithm;

import java.util.*;

public class 课程表207 {

    /**
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(canFinish(3, new int[][]{{1,0},{0,2},{2,3},{4,5},{5,6},{7,8},{2,8}}));
    }

    /**
     * 示例 1：
     *
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     *
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //需要选修的课程数量小于0，直接返回true
        if(numCourses <= 0){
            return true;
        }

        //需要选修的课程数量超过所有可能的课程数目，直接返回false
        if(numCourses > prerequisites.length * 2){
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        // 1,0  0,2  2,3  4,5  5,6  7,8  2,8
        //从课程表里去找能够直接学习的课程数量，（包括 1 -》 2， 2 -》 3 这种）
        for (int[] arr : prerequisites) {
            if(map.containsKey(arr[0])){
                map.get(arr[0]).add(arr[1]);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(arr[1]);
                map.put(arr[0], list);
            }

            if(!map.containsKey(arr[1])){
                map.put(arr[1], new ArrayList());
            }
        }

        //队列用于保存当前入度为0（即没有任何先修课程要求）的可以直接学习的课程
        Queue<Integer> queue = new LinkedList<>();
        for (int key : map.keySet()) {
            if(map.get(key).isEmpty()){
                queue.offer(key);
            }
        }

        //可以学习的课程数
        int studyCount = 0;

        while (!queue.isEmpty()){
            studyCount ++;
            if(studyCount >= numCourses){
                break;
            }
            Integer t = queue.poll();

            for (int key : map.keySet()) {
                if(map.get(key).contains(t)){
                    map.get(key).remove(t);
                    if(map.get(key).isEmpty()){
                        queue.offer(key);
                    }
                }
            }
        }

        return studyCount >= numCourses;
    }
}
