package _50_Data_Structure.Graph.Simple_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;

public class _10S_Graph {

    /************************************************************************
     * memeber variables
     ************************************************************************/

    protected List<Integer>[] adj;
    protected final int V;
    protected int E;

    /************************************************************************
     * constructor
     ************************************************************************/

    @SuppressWarnings("unchecked")
    public _10S_Graph(int V) {
        this.V = V;
        E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    /**
     * Initializes a graph from the specified input stream. The format is the number
     * of vertices <em>V</em>, followed by the number of edges <em>E</em>, followed
     * by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in
     *                                  prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is
     *                                  negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    @SuppressWarnings("unchecked")
    public _10S_Graph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            // build vertices
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non negative");
            adj = (LinkedList<Integer>[]) new LinkedList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new LinkedList<Integer>();
            }
            // build edges
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a graph must be non negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input formate in Graph constructor");
        }
    }

    /**
     * Intialize a graph according to the formate of the input string. The formate
     * is
     * {@code "number of vertices / edgingVertex otherVertices...  / edgingVertex otherVertices..."}
     * </p>
     * for example, {@code 4 / 0 1 2 3 / 3 1 2} means this is a graph with 4 vertices. There is edges between
     * 0 1, 0 2, 0 3 and 3 1, 3 2.
     * 
     * @param s the input string
     */
    @SuppressWarnings("unchecked")
    public _10S_Graph(String s) {
        ArrayList<int[]> arr = new ArrayList<>();
        String[] parts = s.split("/");
        for (String part : parts) {
            // 去除首尾空格并按空格分割数字
            String[] numberStrings = part.trim().split("\\s+");

            // 转换字符串数组为整数数组
            int[] numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }
            arr.add(numbers);
        }
        // Initialize the adjacent list according to the first number readed
        this.V = arr.get(0)[0];
        if (this.V <= 0) throw new IllegalArgumentException("nubmer of vertices shouldn't lesser than 0");
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
        // add edges according to the number before '/'
        for (int i = 1; i < arr.size(); i++) {
            int[] subarr = arr.get(i);
            if (subarr.length <= 1)
                throw new IllegalArgumentException("the quantity of nubmers between '/' shouldn't lesser than 1");
            int v = subarr[0];
            for (int w = 1; w < subarr.length; w++) {
                validateVertex(v);
                validateVertex(subarr[w]);
                addEdge(v, subarr[w]);
            }
        }
    }

    /************************************************************************
     * non-static methods
     ************************************************************************/

    /**
     * 
     * @return the number of vertex in this graph
     */
    public int V() {
        return this.V;
    }

    /**
     * 
     * @return the number of edges in this graph
     */
    public int E() {
        return this.E;
    }

    /**
     * 
     * @param v
     * @return a list that contains all the neighbours of {@code v}
     */
    public Iterable<Integer> adj(int v) {
        if (v > V) throw new IllegalArgumentException("param of adj() exceed the maximum limit");
        return adj[v];
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        if (v != w) adj[w].add(v);
        E++;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < V; i++) {
            s = s + String.format("%d: %s\n", i, adj[i].toString());
        }
        return s;
    }

    protected void validateVertex(int v) {
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /************************************************************************
     * static methods
     ************************************************************************/

    /**
     * 
     * @param G the Graph
     * @param v the vertex
     * @return how many edges is incident to the vertex {@code v}
     */
    @SuppressWarnings("unused")
    public static int degree(_10S_Graph G, int v) {
        int degree = 0;
        for (int i : G.adj(v)) degree++;
        return degree;
    }

    /**
     * 
     * @param G the graph
     * @return the maximum number of edges incident to a vertex in the graph
     *         {@code G}
     */
    public static int maxDegree(_10S_Graph G) {
        int maxDegree = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > maxDegree) maxDegree = degree(G, v);
        }
        return maxDegree;
    }

    /**
     * 
     * @param G the graph
     * @return the total of edges / the total of vertices
     */
    public static double avergeDegree(_10S_Graph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(_10S_Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _10S_Graph g = new _10S_Graph(10);
        System.out.println(g);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 3); // self-loop

        System.out.println(g);
        System.out.println(_10S_Graph.numberOfSelfLoops(g));
    }
}
