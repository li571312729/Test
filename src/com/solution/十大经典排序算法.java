package com.solution;

import com.baili.software.check.A;
import com.sun.javafx.binding.SelectBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class 十大经典排序算法 {
    
    private Integer aInteger = 10;
    
    static Random random = new Random();
    
    static DecimalFormat decimalFormat = new DecimalFormat("######.##");
    
    public static void main(String[] args) {
        double[] array = new double[9999];
        int[] ar = new int[]{1,3,1,43,3,5,34,23,21,16,41,29,37,30,11};
        // 填充数据
        //fillArray(array);

        // 冒泡排序
        //bubblingSort(array);

        // 快速排序
        //quickSort(array);

        // 选择排序
        //selectionSort(ar);

        // 插入排序
        //insertionSort(ar);

        // 希尔排序
        //shellSort(ar);

        // 归并排序
        //int[] ints = mergeSort(ar);
        //System.out.println(1111);

        // 堆排序
        //heapSort(ar);

        // 计数排序
        //countingSort(ar);

        // 桶排序
        // bucketSort(ar, 10);

        // 基数排序
        radixSort(ar);
    }

    /**
     * 冒泡排序
     * @author lxq
     * @date 2021/6/15 14:05
     * @param array
     */
    private static void bubblingSort(double[] array) {
        double temp = 0d;
        for(int i = 0; i < array.length; i ++){
            for(int j = 0; j < array.length - 1 - i; j ++){
                if(array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("冒泡,第" + i + "遍遍历。");
        }
    }

    /**
     * 疏随机填充数据
     * @author lxq
     * @date 2021/6/15 14:03
     * @return null
     */
    public static void fillArray(Object object){
        if(object instanceof double[]){
            for(int i = 0; i < ((double[])object).length ; i ++){
                ((double[])object)[i] = Double.parseDouble(decimalFormat.format(random.nextDouble()*999999));
            }
        }else{
            System.out.println("数组类型必须是double[]");
        }
    }

    /**
     * 选择排序
     * @author lxq
     * @date 2021/6/15 14:06
     * @param array
     */
    private static void selectionSort(int[] array) {
        int min;
        for(int i = 0; i < array.length; i ++){
            min = i;
            for (int j = i + 1; j < array.length; j++){
                if(array[j] < array[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    /**
     * 插入排序
     * @author lxq
     * @date 2021/6/15 14:34
     * @param array
     */
    private static void insertionSort(int[] array){
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j --){
                if (array[j] < array[j - 1]){
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }else {
                    break;
                }
            }

        }
    }

    /**
     * 希尔排序
     * @author lxq
     * @date 2021/6/15 16:12
     * @param arr 
     */
    private static void shellSort(int[] arr) {
        int temp, j;
        for (int step = arr.length / 2; step >= 1 ; step = step / 2) {
            for (int i = step ; i < arr.length; i ++){
                temp = arr[i];
                j = i - step;
                while (j >= 0 && temp < arr[j]){
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

    /**
     * 归并排序
     * @author lxq
     * @date 2021/6/15 17:13
     */
    private static int[] mergeSort(int[] array){
        if(array.length < 2){
            return array;
        }

        int middle = array.length / 2;
        int[] left = new int[middle];
        int[] right = new int[array.length - middle];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, middle, right, 0, right.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 合并左右子序列
     * @author lxq
     * @date 2021/6/15 17:59
     * @param left
     * @param right 
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int i = 0, j = 0, n = 0;
        while (i < left.length && j < right.length){
            if(left[i] < right[j]){
                result[n++] = left[i++];
            }else {
                result[n++] = right[j++];
            }
        }

        while (i < left.length){
            result[n++] = left[i++];
        }

        while (j < right.length){
            result[n++] = right[j++];
        }

        return result;
    }

    /**
     * 快速排序检查
     * @author lxq
     * @date 2021/6/15 14:05
     * @param array
     */
    private static void quickSort(double[] array) {
        if(array.length > 0) {
            // 查看数组是否为空
            unckSort(array, 0, array.length-1);
        }
    }

    /**
     * 快速排序
     * @author lxq
     * @date 2021/6/15 14:05
     * @param null
     * @return null
     */
    static int a = 0;
    public static void unckSort(double[] array,int low,int high) {
        if(low < high) {
            int middle = getMiddle(array,low,high);    // 将list数组一分为二
            System.out.println("递归排序,第" + (++a) + "次递归。");
            unckSort(array,low,middle-1);    // 对低字表进行递归排序
            unckSort(array,middle+1,high);    // 对高字表进行递归排序
        }
    }

    /**
     * 获取中轴位置
     * 即中轴一边都比另一边
     * @author lxq
     * @date 2021/6/16 10:47
     * @param list
     * @param low
     * @param high
     * @return int
     */
    public static int getMiddle(double[] list, int low, int high) {
        double tmp = list[low]; // 数组的第一个值作为中轴（分界点或关键数据）
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            list[low] = list[high]; // 比中轴小的记录移到低端
            while (low < high && list[low] < tmp) {
                low++;
            }
            list[high] = list[low]; // 比中轴大的记录移到高端
        }
        list[low] = tmp; // 中轴记录到尾
        return low; // 返回中轴的位置
    }

    /**
     * 堆排序（大根堆）
     * @author lxq
     * @date 2021/6/16 11:32
     * @param arr
     */
    private static void heapSort(int[] arr){
        // 第一步 构造一个大根堆
        heapBuild(arr);
        int size = arr.length - 1;
        while (size > 0){
            // 将堆顶最大值固定
            swap(arr, 0, size);

            // 调整剩余的元素结构为大顶堆
            heapiFy(arr, 0, --size);
        }
    }

    /**
     * 构造一个大根堆
     * @author lxq
     * @date 2021/6/16 11:35
     * @param arr
     */
    private static void heapBuild(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 当前插入的节点索引
            int current = i;

            // 当前节点的父节点索引
            int father = (i - 1) / 2;

            // 这里判断新插入的当前节点是否大于父节点，如果是则进行交换，并且与交换后的节点的父节点再进行比较
            // 直到小于父节点或者来到顶端
            while (arr[current] > arr[father]){
                swap(arr, current, father);
                current = father;
                father = (father - 1) / 2;
            }
        }
    }

    /**
     * 调整指定区间的元素结构为大顶堆
     * @author lxq
     * @date 2021/6/16 11:48
     * @param arr
     * @param start
     * @param end
     */
    private static void heapiFy(int[] arr, int start, int end) {
        // 左孩子位置
        int leftChild = 2 * start + 1;
        // 右孩子位置
        int rightChild = 2 * start + 2;

        // 每次选择一个较大的孩子节点交换后，父节点已经是最大的了，剩下的只要沿着刚才交换的这个分支继续迭代下去即可，
        // 并不需要照顾到所有节点
        while (leftChild <= end){
            // 较大的孩子节点索引
            int largeIndex;
            // 因为上面whil已经判断了左节点范围，这里如果右子节点超过限制，则较大节点就是左子节点
            if(rightChild > end){
                largeIndex = leftChild;
            }else {
                largeIndex = arr[leftChild] >= arr[rightChild] ? leftChild : rightChild;
            }

            // 如果该节点值已然大于所有子节点值，则说明大顶堆已经构造好
            if(arr[start] < arr[largeIndex]){
                swap(arr, start, largeIndex);
            }else {
                break;
            }

            // 迭代计算交换后的节点和其孩子节点
            start = largeIndex;
            leftChild = 2 * start + 1;
            rightChild = 2 * start + 2;
        }
    }

    /**
     * 交换数组中指定两个索引的值
     * @author lxq
     * @date 2021/6/16 11:38
     * @param arr
     * @param current
     * @param father
     */
    static void swap(int[] arr, int current, int father) {
        arr[current] ^= arr[father];
        arr[father] ^= arr[current];
        arr[current] ^= arr[father];
    }

    /**
     * 计数排序
     * @author lxq
     * @date 2021/6/17 11:12
     * @param arr
     */
    private static int[] countingSort(int[] arr){
        int max = getMaxvalue(arr);

        int[] result = new int[max + 1];
        // 反向扫描保证稳定性
        for (int i = arr.length - 1; i >= 0; i--) {
            result[arr[i]] ++;
        }

        for (int i = 0, j = 0; i < result.length; i++) {
            while(result[i] > 0){
                arr[j++] = i;
                result[i] --;
            }
        }
        return arr;
    }

    /**
     * 获取数组中最大值
     * @author lxq
     * @date 2021/6/17 11:31
     * @param arr
     * @return int
     */
    private static int getMaxvalue(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if(max < i){
                max = i;
            }
        }
        return max;
    }

    /**
     * 计数排序
     * 求出最大最小值，避免空间浪费，保证稳定性
     * @author lxq
     * @date 2021/6/17 11:12
     * @param arr
     */
    public static int[] countingSort2(int[] arr) {
        // 找出数组A中的最大值、最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // 初始化计数数组count
        // 长度为最大值减最小值加1，再加1
        int[] count = new int[(max -min + 1) + 1];

        // 对计数数组各元素赋值，count[0]永远为0
        for (int num : arr) {
            // A中的元素要减去最小值再加上1，再作为新索引
            count[num - min + 1] ++;
        }

        // 计数数组变形，新元素的值是前面元素累加之和的值
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }
        // 创建结果数组
        int[] result = new int[arr.length];
        // 遍历A中的元素，填充到结果数组中去，从前往后遍历
        for (int j = 0; j < arr.length; j++) {

            // 如果后面遇到相同的元素，在前面元素的基础上往后排
            // 如此就保证了原始数组中相同元素的原始排序
            result[count[arr[j] - min]] = arr[j];
            count[arr[j]-min]++;

        }
        return result;
    }

    /**
     * 排序数组，桶的数量
     * @author lxq
     * @date 2021/6/17 16:27
     * @param arr
     * @param bucketSize
     * @return int[]
     */
    private static int[] bucketSort(int[] arr, int bucketSize){
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int min = arr[0];
        int max = arr[0];
        for (int i : arr) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        // 获取桶的间隔(向上取整)
        int bucketInterval = (max - min + 1) / bucketSize +((max - min + 1) % bucketSize == 0 ? 0 : 1);
        int[][] buket = new int[bucketSize][0];

        for (int i : arr) {
            int bucketIndex = (i - min) / bucketInterval;
            buket[bucketIndex] = appendAble(buket[bucketIndex], i);
        }

        int arrIndex = 0;
        for (int[] ints : buket) {
            if (ints.length <= 0) {
                continue;
            }

            insertionSort(ints);
            for (int anInt : ints) {
                arr[arrIndex ++ ] = anInt;
            }
        }
        return arr;
    }

    /**
     * 将数据放入某一桶内，
     * @author lxq
     * @date 2021/6/17 17:15
     * @param ints
     * @param i
     */
    private static int[] appendAble(int[] ints, int i) {
        ints = Arrays.copyOf(ints, ints.length + 1);
        ints[ints.length - 1] = i;
        return ints;
    }

    /**
     * 基数排序
     * @author lxq
     * @date 2021/6/18 10:22
     * @param arr
     * @return int[]
     */
    private static int[] radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        // 首先求出数组中最大值
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(max, i);
        }

        // 再求出最大值的位数长度
        int n = 0;
        for (int temp = max; temp != 0; temp /= 10) {
            n++;
        }

        int mod = 10;
        int dev = 1;


        for (int i = 1; i <= n; i++, dev *= 10, mod *= 10) {
            // 每个位数都要一次重新排序
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] bucket = new int[10 * 2][0];
            for (int a : arr) {
                int bucketIndex = ((a % mod) / dev) + 10;
                bucket[bucketIndex] = appendAble(bucket[bucketIndex], a);
            }

            int index = 0;
            for (int[] ints : bucket) {
                for (int anInt : ints) {
                    arr[index ++] = anInt;
                }
            }
        }
        return arr;
    }

}
