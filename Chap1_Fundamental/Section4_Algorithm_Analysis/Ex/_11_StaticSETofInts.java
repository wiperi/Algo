package Chap1_Fundamental.Section4_Algorithm_Analysis.Ex;

import java.util.Arrays;

public class _11_StaticSETofInts {
    private int[] a;

    public _11_StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // 保护性复制
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) { // 二分查找
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) { // 键要么存在于a[lo..hi] 中，要么不存在
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    private int rank(int key, int lo, int hi) { // 二分查找的递归版本
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
            rank(key, lo, mid - 1);
        } else if (key > a[mid]) {
            rank(key, mid + 1, hi);
        } else {
            return mid;
        }
        return -1;
    }

    public int howMany(int key) {
        int keyIndex = 0;
        if ((keyIndex = rank(key)) == -1) { // 边界条件：key不存在
            return 0;
        }

        int left = rank(key - 1); // 定位key左侧第一个元素
        int right = rank(key + 1); // 定位key右侧第一个元素
        if (left == -1) {
            left = -1;
        }
        if (right == -1) {
            right = a.length;
        }
        int leftKey;
        int rightKey;
        int newLeft = left;
        int newRight = right;
        while (a[newLeft + 1] != key) {
            leftKey = a[left];
            left = rank(leftKey, left + 1, keyIndex);
            newLeft = left;
        }
        while (a[right - 1] != key) {
            right = rank(a[right], right - 1, keyIndex);
        }


        return right - left - 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 3, 4, 5 };
        int[] arr1 = { 0, 0, 0, 0, 1, 1 };

        _11_StaticSETofInts set = new _11_StaticSETofInts(arr);
        System.out.println(set.howMany(3));

        _11_StaticSETofInts set1 = new _11_StaticSETofInts(arr1);
        System.out.println(set1.howMany(1));
    }
}