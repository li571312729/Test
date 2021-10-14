package com.solution;

/**
 * @author lxq
 * @date 2021年10月14日 16:22
 */
public class NC28最小覆盖子串 {

    public static void main(String[] args) {
        String s = minWindow("XDOYEZODEYXNZ", "XYZ");
        System.out.println(s);
    }


    /**
     * 给出两个字符串 s 和 t，要求在 s 中找出最短的包含 t 中所有字符的连续子串。
     * 数据范围：0 > |S|,|T| <10000，保证s和t字符串中仅包含大小写英文字母
     * 要求：进阶：空间复杂度 O(n) ， 时间复杂度 O(n)
     * 例如：
     * S ="XDOYEZODEYXNZ"
     * T ="XYZ"
     * 找出的最短子串为"YXNZ"
     * 注意：
     * 如果 s 中没有包含 t 中所有字符的子串，返回空字符串 “”；
     * 满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
     * @author lxq
     * @date 2021/10/14 16:24
     * @param S
     * @param T 
     * @return java.lang.String
     */
    public static String minWindow(String S, String T) {
        int[] srcHash = new int[255];
        // 记录目标字符串每个字母出现次数
        for(int i = 0; i < T.length(); i++){
            srcHash[T.charAt(i)]++;
        }

        int start, i;
        // 用于记录窗口内每个字母出现次数
        int[] destHash = new int[255];
        int found = 0;
        int begin = -1, end = S.length(), minLength = S.length();
        for(start = i = 0; i < S.length(); i++){
            // 每来一个字符给它的出现次数加1
            destHash[S.charAt(i)]++;

            // 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
            if(destHash[S.charAt(i)] <= srcHash[S.charAt(i)]) found++;

            // 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串
            if(found == T.length()){
                // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
                while(start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]){
                    destHash[S.charAt(start)]--;
                    start++;
                }
                // 这时候start指向该子串开头的字母，判断该子串长度
                if(i - start < minLength){
                    minLength = i - start;
                    begin = start;
                    end = i;
                }
                // 把开头的这个匹配字符跳过，并将匹配字符数减1
                destHash[S.charAt(start)]--;
                found--;
                // 子串起始位置加1，我们开始看下一个子串了
                start++;
            }
        }
        // 如果begin没有修改过，返回空
        return begin == -1 ? "" : S.substring(begin,end + 1);
    }

}
