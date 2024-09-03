package com.angorithm;

public class PATest {

    public static void main(String[] args){
        String data = minNumber("1334239", 3);
        System.out.println(data);
    }


    /**
     * 这里使用的是笨方法，一次删除一个 循环k次，保证剩余的是最小的
     *
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     * 示例 1 ：
     * 输入：num = "1334239", k = 3  433
     * 输出："1219"
     *
     * 输入：num = "10200", k = 1
     * 输出："200"
     * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 ：
     *
     * 输入：num = "10", k = 2
     * 输出："0"
     * 解释：从原数字移除所有的数字，剩余为空就是 0 。
     *
     * 提示：
     * 1 <= k <= num.length <= 105
     * num 仅由若干位数字（0 - 9）组成
     * 除了 0 本身之外，num 不含任何前导零
     */
    private static String minNumber(String s, int k) {
        if(s == null || "".equals(s)){
            return "";
        }

        for (int i = 1; i <= k; i++){
            char[] chars = s.toCharArray();
            for (int j = 0; j <= chars.length - 2; j++) {
                if(chars[j] <= chars[j + 1]){
                    if(j == chars.length - 2){
                        s = s.substring(0, s.length() - 1);
                        break;
                    }
                    continue;
                }

                if(j == 0|| chars[j + 1] == '0'){
                    s =  s.substring(2);
                }else {
                    // 移除该位置字符
                    s = s.substring(0, j) + s.substring(j + 1);
                }
                break;
            }
        }
        return "".equals(s) ? "0" : s;
    }
}
