import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BSTtestComplete {
	public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        String teste = null;
        for (int i = 0; !StdIn.isEmpty();i++) {
            String key = StdIn.readString();
            //if(!key.equals("-1"))
            	//break;
            teste = key;
            st.put(key, i);
            
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
