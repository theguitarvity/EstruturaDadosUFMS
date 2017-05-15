import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinaryTreeDelete {
	public static void main(String[] args) {
		BinaryTreeSimple<String, Integer> st = new BinaryTreeSimple<String, Integer>();
		int i;
		String direction, key, childkey;

		key = StdIn.readString();
		st.putRoot(key, 0);

		for (i = 1; !StdIn.isEmpty(); i++) {
			direction = StdIn.readString();
			key = StdIn.readString();
			childkey = StdIn.readString();
			if (direction.equals("L"))
				st.putLeft(key, childkey, i);
			else if (direction.equals("R"))
				st.putRight(key, childkey, i);

			StdOut.println();

			st.preorderTraversal();
		}

		// ordem crescente de nivel
		for (String s : st.levelOrder())
			StdOut.println(s + " " + st.get(s));

		// removendo no

		StdOut.println("Digite o no a ser excluido");
		String e = StdIn.readString();

		st.delete(e);

		// ordem crescente de nivel
		for (String s : st.levelOrder())
			StdOut.println(s + " " + st.get(s));

		StdOut.println();

		st.preorderTraversal();
	}
}
