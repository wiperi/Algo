package Chap1_Fundamental.Section4_Algorithm_Analysis;

import edu.princeton.cs.algs4.StdRandom;

public class _2_1_Three_Sum {
    public static int count(int[] a) { // 统计和为0的元组的数量
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniformInt(-100, 101);
        }
        count(arr);
    }
}