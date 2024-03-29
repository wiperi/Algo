package Graph.Graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class _60_Symbol_Graph {
    private ST<String, Integer> st; // String -> index
    private String[] keys;          // index -> String
    private Graph G;                // the graph

    public _60_Symbol_Graph(String stream, String separator) {
        st = new ST<String, Integer>();

        In in = new In(stream);                           // First pass
        while (in.hasNextLine())                          // builds the index
        {
            String[] a = in.readLine().split(separator);  // by reading strings
            for (int i = 0; i < a.length; i++)            // to associate each
                if (!st.contains(a[i]))                   // distinct string
                    st.put(a[i], st.size());              // with an index.
        }
        
        keys = new String[st.size()];                     // Built the Inverted index
        for (String name : st.keys())                     // to get string keys
            keys[st.get(name)] = name;                    // is an array.

        G = new Graph(st.size());
        in = new In(stream);                              // Second pass
        while (in.hasNextLine())                          // builds the graph
        {
            String[] a = in.readLine().split(separator);  // by connecting the
            int v = st.get(a[0]);                         // first vertex
            for (int i = 1; i < a.length; i++)            // on each line
                G.addEdge(v, st.get(a[i]));               // to all the others.
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * 
     * @param s
     * @return the index of vertex naming {@code s}
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * 
     * @param v
     * @return the name of vertex {@code v}
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * @return the inner Graph data strcture
     */
    public Graph G() {
        return G;
    }
}
