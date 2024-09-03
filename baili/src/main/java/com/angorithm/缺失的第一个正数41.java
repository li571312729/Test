package com.angorithm;

/**
 * @author xiaoqiangli
 * @Date 2021-12-30
 */
public class 缺失的第一个正数41 {

    public static void main(String[] args) {
        int j = firstMissingPositive(new int[]{0, 1, 2});
        System.out.println(j);

    }

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        // 要找的值一定是在 1 ~ （N+1） 之间 这个稍微推一下就出来了
        // 所以初始值设置为 n + 1
        int min = nums.length + 1;



        // 2  1  3  -1  5

        // 如果存在负数(这里把0也算上，因为0不是我们的判断范围) 则设置为 N+1，如果元素<= n 则将元素对应的位置下标的元素设为负数(相当于模拟has存储小于等于n的元素,这样最后还是为正数的下标位置+1 就是缺少的正数)
        // 加1是因为，我们确定不需要0这个数，所以把0下标当1来用。

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= 0){
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 如果没有合适的。说明全部小于n，没有大于n的则，一定是n+1，，
        // 只有出现一个大于n的 才能空出来位,而空出来的位置一定没有人去占位置
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                min = i + 1;
                break;
            }
        }
        return min;
    }
}
