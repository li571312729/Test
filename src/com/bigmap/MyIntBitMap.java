package com.bigmap;

/**
 * @author xiaoqiangli
 * @Date 2021-11-30
 */
public class MyIntBitMap {

    private int[] arr;


    /**
     * 创建实际数组
     *
     * @param length 数据集的大小
     */
    public MyIntBitMap(long length) {
        this.arr = new int[getIndex(length) + 1];
    }

    /**
     * 返回实际存储的元素数量，而不是map的大小
     * @return
     */
    public long size(){
        long result = 0;
        for (int i : arr) {
            result += Integer.bitCount(i);
        }
        return result;
    }

    /**
     * 返回map的大小，而不是实际存储的元素数量，
     * @return
     */
    public int length(){
        return arr.length;
    }

    /**
     * 只需要判断放置的位置是否为1即可，为1则已存在
     * 这里把1移到该元素位置然后与操作即可
     * 32 + 16 2   0 1 0 0 1 1
     * @param num
     * @return
     */
    public boolean contains(long num) {
        return (arr[getIndex(num)] & (1 << getPosition(num))) != 0;
    }

    /**
     * 清空该元素在map中的存放记录
     * 这里把1移到该元素位置然后与操作即可
     *
     * @param num
     * @return
     */
    public void clear(long num) {
        // 这里一定要先移过去再非，如果直接非0，则直接全部变成1
        arr[getIndex(num)] &= ~(1 << getPosition(num));
    }

    /**
     * 判 断指定数字num是否存在
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     *
     * @param num
     */
    public void put(long num) {
        arr[getIndex(num)] |= 1 << getPosition(num);
    }

    /**
     * num % 8得到在int[index]的位置
     *
     * @param num
     * @return
     */
    public int getPosition(long num) {
        return (int) (num & 0x1F);
    }

    /**
     * num/32 得到int[]的index
     *
     * @param num
     * @return
     */
    public int getIndex(long num) {
        // num / 32 = num >> 5
        return (int) (num >> 5);
    }

    /**
     * 展示num所在int的当前二进制表示
     * @param num
     */
    public void showBit(long num) {
        StringBuffer s = new StringBuffer(Integer.toBinaryString(arr[getIndex(num)]));
        int count = 32 - s.length();
        if(count > 0){
            s.reverse();
            for (int i = 0; i < count; i++) {
                s.append(0);
            }
        }
        System.out.println(s.reverse());
    }
}
