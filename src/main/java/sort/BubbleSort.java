package sort;

import java.util.Arrays;

/**
 * 冒泡排序只会操作相邻的两个数据。
 * 每次冒泡操作都会对相邻的两个元素进行比较,看是否满足大小关系要求。如果不满足就让它俩互换。
 * 一轮冒泡会让至少一个元素移动到它应该在的位置,重复 n 次,就完成了 n 个数据的排序工作。
 **/
public class BubbleSort {

    // 1、一个想要测试的方法a
    public static void bubbleSort(int[] array) {
        // 数组不符合条件时，直接退出，不比较
        if (array.length < 2 || (null == array)) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {// 排好序的数字放在末尾，不再参与排序，所以长度递减
                if (array[j] > array[j + 1]) {// 触发交换
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }// 一轮冒泡结束，排好一个数
            if (!flag) break;
        }
    }

    public static void main(String[] args) {
        //testTime是测试次数
        int testTime = 5000000;
        int size = 100; // 数组的长度
        int value = 100;// 数组中每个元素的大小范围
        boolean succeed = true;

        // 5、把方法a和方法b比对很多次来验证a是否正确
        for(int i = 0;i < testTime;i++) {
            int[] arr1 = generateRandomArray(size, value);
            //拷贝数组，数组new出来的就是在栈中不同的空间中存放，内容相同
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            int[] arr3 = Arrays.copyOf(arr1, arr1.length);
            bubbleSort(arr1);
            rightMethod(arr2);
            if(!isEqual(arr1, arr2)) {
                succeed = false;
                // arr1和arr3是一模一样的数组，如果有错的话，打印的arr3的数组就是arr1排序失败的数组
                // 如果有一个样本使得对比出错，打印样本分析是哪个方法出错
                System.out.println(Arrays.toString(arr3));
                break;
            }
        }
        // 打印要测的部分是否正确
        // 当样本数量很多时比对测试仍然正确，可以确定方法a已经正确
        System.out.println(succeed ? "succeed!" : "false..");
    }

    // 3、实现一个随机样本产生器
    public static int[] generateRandomArray(int size, int value) {
        //产生的数组长度是[0, size]
        int[] arr = new int[(int) ((size + 1) * Math.random())];

        //产生的数组中的数的范围是-value ~ value
        for(int i = 0;i < arr.length;i++) {
            arr[i] = (int) ((value + 1) * Math.random()
                    - (int) (value * Math.random()));
        }
        return arr;
    }

    // 2、一个绝对正确但是复杂度不好的方法b
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    // 4、实现比对的方法
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
            return false;
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1.length != arr2.length)
            return false;
        for(int i = 0;i < arr1.length;i++) {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

}
