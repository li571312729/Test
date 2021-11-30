package com.bigmap;


/**
 * java 实现bigmap
 *
 * @author xiaoqiangli
 * @Date 2021-11-30
 */
public class MyBitMap {

    public byte[] bits;

    /**
     * 创建bitmap数组
     */
    public MyBitMap(int n){
        bits = new byte[getIndex(n) + 1];
    }

    /**
     * 标记指定数字（num）在bitmap中的值，标记其已经出现过<br/>
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了
     * @param num
     */
    public void put(int num){
        bits[getIndex(num)] |= 1 << getPosition(num);
    }

    /**
     * 判断指定数字num是否存在<br/>
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     * @param num
     * @return
     */
    public boolean contains(int num){
        return (bits[getIndex(num)] & 1 << getPosition(num)) != 0;
    }

    /**
     * num/8得到byte[]的index
     * @param num
     * @return
     */
    public int getIndex(int num){
        return num >> 3;
    }

    /**
     * num%8得到在byte[index]的位置
     * @param num
     * @return
     */
    public int getPosition(int num){
        return num & 0x07;
    }

    /**
     * 重置某一数字对应在bitmap中的值<br/>
     * 对1进行左移，然后取反，最后与byte[index]作与操作。
     * @param num
     */
    public void clear(int num){
        bits[getIndex(num)] &= ~(1 << getPosition(num));
    }

    /**
     * 打印byte类型的变量<br/>
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */

    public void showByte(byte b){
        byte[] array = new byte[8];
        for(int i = 7; i >= 0; i--){
            array[i] = (byte)(b & 1);
            b = (byte)(b >> 1);
        }

        for (byte b1 : array) {
            System.out.print(b1);
            System.out.print(" ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        MyBitMap map = new MyBitMap(100);
        map.put(12);
        map.put(13);
        map.put(14);
        map.put(15);

        if(map.contains(15)){
            System.out.println("15 exist");
        }

        for (byte bit : map.bits) {
            map.showByte(bit);
        }
    }

}
