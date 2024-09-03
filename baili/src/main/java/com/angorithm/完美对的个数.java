package com.angorithm;

/**
 * @author lxq
 * @date 2021年06月02日 16:12
 */
public class 完美对的个数 {

    public static void main(String[] args) {
        int count = count(new int[][]{{2, 11, 21}, {19, 10, 1}, {20, 11, 1}, {6, 15, 24}, {18, 27, 36}});
        System.out.println(count);
    }

    /**
     * 2 11 21
     * 19 10 1
     * 20 11 1
     * 6 15 24
     * 18 27 36
     *
     * @author lxq
     * @date 2021/6/2 16:13
     * @param param
     * @return int
     */
    public static int count(int[][] param){
        int count = 0;
        for (int i = 0; i < param.length; i++) {
            for(int j = i + 1; j < param.length; j ++){
                int temp = param[i][0] + param[j][0];
                boolean flag = true;
                for (int i1 = 1; i1 < param[i].length; i1++) {
                    if (param[i][i1] + param[j][i1] != temp) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    count ++;
                }
            }
        }
        return count;
    }
}

