package Chap2_Sort.Section1_Elementary_Sort;

@SuppressWarnings("rawtypes")
public class _3_Insertion extends Sort_Template {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sortV2(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];

            int j = i;
            for (; j > 0 && less(temp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 5, 6, 4, 3, 1, 7, 9, 0, 6, 2 };
        sortV2(arr);
        show(arr);
    }
}
