package Chap1_Fundamental.Section4_Algorithm_Analysis.Ex;

/**
 * 1.4.18 数组的局部最小元素。编写一个程序，给定一个含有N 个不同整数的数组，找到一个局部最
 * 小元素：满足a[i]<a[i － 1]，且a[i]<a[i+1] 的索引i。程序在最坏情况下所需的比较次数
 * 为～ 2lgN。
 * 答：检查数组的中间值a[N/2] 以及和它相邻的元素a[N/2-1] 和a[N/2+1]。如果a[N/2] 是一
 * 个局部最小值则算法终止；否则则在较小的相邻元素的半边中继续查找。
 */
public class _18_Local_Minimum {

    public static int localMinimum(int[] a) {
        int len = a.length;
        int lo = 0, hi = len - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (mid == 0 || mid == len - 1) // corner case
                break;

            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                return mid;
            } else if (a[mid - 1] < a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 1, 0, 1 };
        System.out.println(localMinimum(arr));
    }
}
