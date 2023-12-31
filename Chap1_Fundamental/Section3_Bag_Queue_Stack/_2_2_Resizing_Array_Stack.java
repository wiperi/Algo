package Chap1_Fundamental.Section3_Bag_Queue_Stack;

import java.util.Iterator;

public class _2_2_Resizing_Array_Stack<Item> implements Iterable<Item> {
    @SuppressWarnings("unchecked")
    private Item[] a = (Item[]) new Object[1]; // 栈元素
    private int N = 0; // 元素数量

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @SuppressWarnings("unchecked")
    private void resize(int max) { // 将栈移动到一个大小为max 的新数组
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item) { // 将元素添加到栈顶
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() { // 从栈顶删除元素
        Item item = a[--N];
        a[N] = null; // 避免对象游离（请见1.3.2.4 节）
        if (N > 0 && N == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> { // 支持后进先出的迭代
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }
}