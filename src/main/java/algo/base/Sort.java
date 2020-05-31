package algo.base;

import util.ArrayUtil;

/**
 * 常见排序算法实现
 */
public class Sort {

     // ============== n(log n)复杂度排序算法 ==============

    public static void merge(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * [l, r]归并排序
     */
    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid,  r);
    }

    /**
     * 以mid为界, 归并[l, mid] [mid + 1, r] 数组
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] merge = new int[r - l + 1];
        // 分别为左索引, 右索引, 与临时存储的索引
        int m = l, n = mid + 1, i = 0;
        for (; i < merge.length && m <= mid && n <= r; i++) {
            if (arr[m] > arr[n]) {
                merge[i] = arr[n];
                n++;
            } else {
                merge[i] = arr[m];
                m++;
            }
        }
        for (int j = m; j <= mid; j++) {
            merge[i++] = arr[j];
        }
        for (int j = n; j <= r; j++) {
            merge[i++] = arr[j];
        }
        for (int j = l, k = 0; j <= r; j++, k++) {
            arr[j] = merge[k];
        }
    }

    // ============== n^2复杂度排序算法 ==============

    /**
     * 待排序元素中, 每次取第一个元素A与后面元素B比较, A>B -> swap A, B 否则叫大数取B
     * A的索引始终是B-1
     */
    public static void bubble(int[] arr) {
        int sorted = 0;
        // 已排序元素个数
        while (sorted <= arr.length) {
            // 标定元素 index -> i - 1
            int p = arr[0];
            // 待排序元素(标定元素的下一个)  index -> i
            for (int i = 1; i < arr.length - sorted; i++) {
                if (arr[i] > p) {
                    p = arr[i];
                } else {
                    ArrayUtil.swap(arr, i, i - 1);
                }

            }
            sorted ++;
        }
    }

    /**
     * 每次从待排序元素中选择一个元素插入到合适位置:
     * 如何插入? 每次与前一个元素(已排序)比较, 小于则交换位置
     * 利用已排序元素的特性, 优化插入效率 => 希尔排序
     *
     * 待排序索引位: [i, length) 初始为[0, length)
     * 已排序索引位: [0, i) 初始为[0, i)
     */
    public static void insert(int[] arr) {
        // 待排序
        for (int i = 0; i < arr.length; i++) {
            // 已排序
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j])
                    ArrayUtil.swap(arr, i, j);
            }
        }
    }

    /**
     * 每次从待排序的元素中选择一个最小的索引, 与第一个待排序交换索引
     */
    public static void select(int[] arr) {
        int sorted = 0;
        while (sorted < arr.length) {
            int minIndex = findMinIndex(arr, sorted, arr.length - 1);
            ArrayUtil.swap(arr, sorted, minIndex);
            sorted ++;
        }
    }

    /**
     * [l, r]中最小元素index
     */
    private static int findMinIndex(int[] arr, int l, int r) {
        int index = l;
        int min = arr[l];
        for (int i = l + 1; i <= r; i++) {
            if (min > arr[i]) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.random(1000, 30);
        ArrayUtil.print(arr);
        // select(arr);
        // insert(arr);
        // bubble(arr);
        merge(arr);
        ArrayUtil.print(arr);
        System.out.println(ArrayUtil.isSorted(arr));
    }

}
