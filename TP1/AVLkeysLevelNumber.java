import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AVLkeysLevelNumber {
	public static void main(String[] args) {
        AVLTreeST<String, Integer> st = new AVLTreeST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
        //lendo level
        StdOut.println("Digite um nivel para leitura");
        int level = StdIn.readInt();
        StdOut.println(st.keysLevelNumber(level));
    }
}
