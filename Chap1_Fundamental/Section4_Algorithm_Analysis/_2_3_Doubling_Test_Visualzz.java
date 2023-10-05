package Chap1_Fundamental.Section4_Algorithm_Analysis;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class _2_3_Doubling_Test_Visualzz {
    public static double timeTrial(int N) { // 为处理N 个随机的六位整数的ThreeSum.count() 计时
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniformInt(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) { // 打印运行时间的表格
        initializeCanva();
        
        for (int N = 250; true; N += N) { // 打印问题规模为N 时程序的用时
            double time = timeTrial(N);
            rescalseIfneccessary();
            drawpoints();
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }
}
