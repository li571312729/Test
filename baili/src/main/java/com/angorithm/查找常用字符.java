package com.angorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * @author Administrator
 */
public class 查找常用字符 {

    public static void main(String[] args) {
        查找常用字符 a = new 查找常用字符();
        a.commonChars1(new String[]{"aasdsdfewa", "sadfsgaadweb", "asdsdvasd", "vxcasdasdxd"}).forEach(o -> {
            System.out.print(o + "   ");
        });
        // a a s s d d
    }

    public List<String> commonChars(String[] A) {
        String s = A[0];
        List<String> result = new ArrayList<>(s.length());
        while(!"".equals(s)){
            char c = s.charAt(0);
            int count = getCount(s, c);
            s = s.replace(c + "", "");
            for (String s1 : A) {
                int count1 = getCount(s1, c);
                if(count > count1){
                    count = count1;
                }
            }

            if(count > 0){
                for (int i = 0; i < count; i++) {
                    result.add(c + "");
                }
            }
        }
        return result;
    }

    public int getCount(String s, char t){
        int count = 0;
        for (char c : s.toCharArray()) {
            if(c == t){
                count ++;
            }
        }
        return count;
    }

    /**********************************************************************************************/
    /**
     * 优化解法，用一个数组存储第一个字符串中每个字符出现的次数
     * 然后跟后面每个字符串临时数组进行比对，保留小的
     * 最后输出
     * @param A
     * @return
     */
    public List<String> commonChars1(String[] A) {
        // 存放26个小写字母每一个出现的次数，存放下标为 X - 97
        int[] a = new int[26];
        String s = A[0];
        for (char c : s.toCharArray()) {
            a[c - 97] ++;
        }

        for (int i = 1; i < A.length; i++) {
            // 从第二个字符串开始与第一个进行对比的临时数组
            int[] temp = new int[26];
            for (char c : A[i].toCharArray()) {
                temp[c - 97] ++;
            }

            for (int i1 = 0; i1 < a.length; i1++) {
                if(temp[i1] < a[i1]){
                    a[i1] = temp[i1];
                }
            }

        }

        List<String> result = new ArrayList<>(s.length());
        for (int i = 0; i < a.length; i++) {
            if(a[i] > 0){
                String aa = (char)(i + 97) + "";
                for (int i1 = 0; i1 < a[i]; i1++) {
                    result.add(aa);
                }
            }
        }
        return result;
    }

}
