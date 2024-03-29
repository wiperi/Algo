package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.princeton.cs.algs4.BST;

public class Binary_Tree {
    public static class Node {
        public Integer val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(Integer val) {
            this.val = val;
        }

        public Node(Integer val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /************************************************************************
     * Helper Funtions
     ************************************************************************/
    public static int sizeOf(Node root) {
        List<Integer> list = levelOrder(root);
        return list.size();
    }

    public static int getHeight(Node root) {
        if (root == null) return 0;

        int leftMax = getHeight(root.left);
        int rightMax = getHeight(root.right);

        return 1 + Math.max(leftMax, rightMax);
    }

    public static Node buildTree(Integer[] a) {
        int len = a.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(a[i], null, null);
        }
        for (int i = 0; i < len; i++) {
            int leftKid = 2 * i + 1;
            int rightKid = 2 * i + 2;
            if (leftKid < len) nodes[i].left = nodes[leftKid];
            if (rightKid < len) nodes[i].right = nodes[rightKid];
        }
        return nodes[0];
    }

    /**
     * Show a linked list form binary tree
     * 
     * @param root
     */
    public static void show(Node root) {
        int arrSize = (int) Math.pow(2, (double) getHeight(root)) - 1;
        Integer[] arr = new Integer[arrSize];
        showAux(root, arr, 0);

        // print to screen
        int leftbound = 0;
        int rightbound = 0;
        int gapSize = arrSize; // the gap printed bewteem two elements that are in the same row
        while (true) {
            printGap(gapSize / 2);
            for (int i = leftbound; i <= rightbound; i++) {
                if (i >= arrSize) return;
                if (arr[i] == null) System.out.printf("%s", "n");
                if (arr[i] != null) System.out.print(arr[i]);
                printGap(gapSize);
            }
            System.out.println();

            gapSize /= 2;
            leftbound = 2 * leftbound + 1;
            rightbound = 2 * rightbound + 2;
        }
    }

    private static void showAux(Node root, Integer[] arr, int index) {
        if (root == null) {
            return;
        }
        arr[index] = root.val;
        showAux(root.left, arr, 2 * index + 1);
        showAux(root.right, arr, 2 * index + 2);
    }

    private static void printGap(int gapSize) {
        for (int i = 0; i < gapSize; i++) {
            System.out.printf("%s", " ");
        }
    }

    /************************************************************************
     * BFS
     ************************************************************************/
    // 层序遍历
    public static List<Integer> levelOrder(Node root) {
        // video: https://youtu.be/my9AYTFl8IM
        if (root == null) {
            return null;
        }

        Queue<Node> que = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();

        que.add(root);
        while (!que.isEmpty()) {
            Node temp = que.poll(); // 取出节点
            ret.add(temp.val); // 记录节点

            if (temp.left != null) // 左节点入队
                que.offer(temp.left);
            if (temp.right != null) // 右节点入队
                que.offer(temp.right);
        }
        return ret;
    }

    public static List<List<Integer>> levelOrderReturnLists(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        levelOrderReturnListsAux(root, 1, res);
        return res;
    }

    private static void levelOrderReturnListsAux(Node root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (level > res.size()) res.add(new LinkedList<Integer>());
        res.get(level - 1).add(root.val);

        levelOrderReturnListsAux(root.left, level + 1, res);
        levelOrderReturnListsAux(root.right, level + 1, res);
    }

    public static List<List<Integer>> levelOrderReturnListsIter(Node root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int levelSize = que.size();

            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                Node cur = que.poll();
                level.add(cur.val);
                if (cur.left != null) que.offer(cur.left);
                if (cur.right != null) que.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    /************************************************************************
     * DFS
     ************************************************************************/
    @SuppressWarnings("unused")
    private static void dfsFrame(Node root) {
        if (root == null) {
            return;
        }
        // 前序位置
        dfsFrame(root.left);
        // 中序位置
        dfsFrame(root.right);
        // 后序位置
    }

    private static List<Integer> retList = new ArrayList<>();

    /**
     * mode 1 = preorder, mode 2 = inorder, mode 3 = postorder
     * 
     * @param root
     * @param mode
     * @return
     */
    public static List<Integer> dfs(Node root, int mode) {
        retList.clear();
        return dfsAux(root, mode);
    }

    private static List<Integer> dfsAux(Node root, int mode) {
        if (mode < 1 || mode > 3) { // 检查参数合法性
            throw new IllegalArgumentException("mode must be 1,2 or 3");
        }

        if (root == null) { // base case
            return null;
        }

        if (mode == 1) retList.add(root.val); // 前序

        dfsAux(root.left, mode);

        if (mode == 2) retList.add(root.val); // 中序

        dfsAux(root.right, mode);

        if (mode == 3) retList.add(root.val); // 后序

        return retList;
    }

    public static List<Integer> preorderSub(Node root) {
        // 分解成问题：一个二叉树的前序遍历结果 = root的值 + 左子树的前序结果 + + 右子树的前序结果
        List<Integer> ret = new ArrayList<>();

        if (root == null) return ret;

        List<Integer> left = preorderSub(root.left);
        List<Integer> right = preorderSub(root.right);

        ret.add(root.val); // 中序和后序改变add命令的位置即可
        ret.addAll(left);
        ret.addAll(right);

        return ret;
    }

    public static List<Integer> preorderIter(Node root) { // 迭代先序
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        List<Integer> ret = new ArrayList<>();

        stack.push(root);
        while (!stack.empty()) {
            Node cur = stack.pop();
            ret.add(cur.val); // 记录节点

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        return ret;
    }

    public static List<Integer> preorderIter1(Node root) { // 迭代先序1
        Stack<Node> stack = new Stack<>();
        List<Integer> ret = new ArrayList<>();

        while (!stack.empty() || root != null) {
            if (root != null) { // 不断向左下走
                stack.push(root); // 每走到一个节点就将该节点入栈
                ret.add(root.val); // 记录节点
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }

        return ret;
    }

    public static List<Integer> inorderIter(Node root) { // 迭代中序
        // video: https://youtu.be/d4KBiARy_Tk
        Stack<Node> stack = new Stack<>();
        List<Integer> ret = new ArrayList<>();

        while (!stack.empty() || root != null) {
            if (root != null) { // 不断向左下走
                stack.push(root); // 每走到一个节点就将该节点入栈
                root = root.left; // 向左下走
            } else {
                root = stack.pop();
                ret.add(root.val); // 记录节点
                root = root.right;
            }
        }

        return ret;
    }

    public static List<Integer> postorderIter(Node root) { // 迭代后序
        if (root == null) {
            return null;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            // 先将左子树入stack1，再将右子树入stack1
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        List<Integer> ret = new ArrayList<>();
        while (!stack2.isEmpty()) {
            ret.add(stack2.pop().val);
        }
        return ret;
    }

    /************************************************************************
     * Test
     ************************************************************************/
    public static void testTraversal(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        Binary_Search_Tree b = new Binary_Search_Tree();
        for (Integer ints : new Integer[] { 6, 4, 8, 3, 5 }) {
            b.put(ints);
            bst.put(ints, ints);
        }

        System.out.println("levelorder:");
        System.out.println(bst.levelOrder());
        System.out.println(Binary_Tree.levelOrder(b.root));

        System.out.println();

        System.out.println("pre, in, post order by recursion:");
        System.out.println(Binary_Tree.dfs(b.root, 1));
        System.out.println(Binary_Tree.dfs(b.root, 2));
        System.out.println(Binary_Tree.dfs(b.root, 3));

        System.out.println();

        System.out.println("pre, pre1, in, post order by iter:");
        System.out.println(Binary_Tree.preorderIter(b.root));
        System.out.println(Binary_Tree.preorderIter1(b.root));
        System.out.println(Binary_Tree.preorderSub(b.root));
        System.out.println(Binary_Tree.inorderIter(b.root));
        System.out.println(Binary_Tree.postorderIter(b.root));
    }

    public static void testBuiltTree(String[] args) {
        Node root = Binary_Tree.buildTree(new Integer[] { null, 2, 3 });
        System.out.println(Binary_Tree.dfs(root, 1));
    }

    public static void testShow(String[] args) {
        Node root = Binary_Tree.buildTree(new Integer[] { 1, 2, 3, null, 4, 5, null, 6 });
        Binary_Tree.show(root);
    }

    public static void main(String[] args) {
        testShow(args);
    }
}
