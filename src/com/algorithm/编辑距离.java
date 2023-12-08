package com.algorithm;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class 编辑距离 {

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        System.out.println(dynamicMinDistance("sea", "eat"));
        long t1 = System.currentTimeMillis();
        System.out.println(recursionMinDistance("sea", "eat"));
        long t2 = System.currentTimeMillis();

        System.out.println("动态规划耗时：" + (t1 - t));
        System.out.println("递归耗时：" + (t2 - t1));
    }

    /**
     * 动态规划解法 自下向上
     * @param word1
     * @param word2
     * @return
     */
    private static int dynamicMinDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        //如果两个字符串其中一个为空，直接返回另一个长度即可,word1为空相当于word1需要插入，否则就是需要删除到跟word2相同
        if(l1 == 0 || l2 == 0){
            return Math.max(l1, l2);
        }

        //dp[i][j]数组表示从word1的0到[i]位置 变成 word2的0到[j]位置需要的操作次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        //边界初始化，即word1或者word2长度为0时需要的操作次数其实就是另一个的长度
        for (int i = 0; i <= l1; i++){
            for (int j = 0; j <= l2; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = 0;
                }else if(i == 0){
                    //如果i是0表示第一个字符串是空的，那需要添加第二个字符串所有元素才可以
                    dp[i][j] = j;
                }else if(j == 0){
                    dp[i][j] = i;
                }
            }
        }

        //开始动态规划dp数组赋值
        for (int i = 1; i <= l1; i++){
            for (int j = 1; j <= l2; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j -1];
                }else{
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }

    /**
     * 递归求解  自顶向下
     * @param word1
     * @param word2
     * @return
     */
    private static int recursionMinDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0){
            return Math.max(word1.length(), word2.length());
        }

        //最后一个字符相同则直接看双方前面一个字符
        if(word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)){
            return recursionMinDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1));
        }

        //不相同则取三种操作中最小操作步骤的那个
        //插入
        int insert = recursionMinDistance(word1, word2.substring(0, word2.length() - 1));
        //删除
        int dele = recursionMinDistance(word1.substring(0, word1.length() - 1), word2);
        //替换
        int update = recursionMinDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1));
        return 1 + Math.min(Math.min(insert, dele), update);
    }


}
