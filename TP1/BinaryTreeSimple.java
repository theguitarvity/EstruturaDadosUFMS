import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;





/* This implementation is based on BST of Robert Sedgewick and Kevin Wayne */

public class BinaryTreeSimple<Key extends Comparable<Key>, Value> {
	private Node root; // root of binary tree

	private Node found;

	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right; // left and right subtrees
		private int size; // number of nodes in subtree

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	/**
	 * Initializes an empty binary tree.
	 */
	public BinaryTreeSimple() {
	}

	/**
	 * Returns true if this binary tree is empty.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of key-value pairs in this binary tree.
	 */
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in binary tree rooted at x
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	/**
	 * Does this binary tree contain the given key?
	 */
	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key.
	 */
	public Value get(Key key) {
		found = null;
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			found = x;
			return x.val;
		}

		if (found == null)
			getNode(x.left, key);

		if (found == null)
			getNode(x.right, key);

		if (found != null)
			return found.val;

		return null;
	}

	/**
	 * Returns the node associated with the given key.
	 */
	public Node getNode(Key key) {
		if (root == null)
			return null;

		found = null;
		return getNode(root, key);
	}

	private Node getNode(Node x, Key key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			found = x;
			return x;
		}

		if (found == null)
			getNode(x.left, key);

		if (found == null)
			getNode(x.right, key);

		return found;
	}

	public void preorderTraversal() {
		preorder(root);
	}

	public void preorder(Node x) {
		visit(x);
		if (x.left != null)
			preorder(x.left);
		if (x.right != null)
			preorder(x.right);
	}

	private void visit(Node x) {
		StdOut.println(x.key + " " + x.val);
	}

	/**
	 * Inserts the specified key-value pair as a root into the binary tree.
	 *
	 */

	public Node putRoot(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to putRoot() is null");

		root = new Node(key, val, 1);
		return root;
	}

	/**
	 * Inserts the specified key-value pair as a left node of node with key x
	 * into the binary tree. The main idea is to find the node with key x and
	 * then create a new node and insert it at left of node with key x
	 *
	 */
	
	public Node putLeft(Key x, Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to putLeft() is null");
		Node node = getNode(x);
		if (node == null)
			return null;

		Node leftNode = node.left;

		Node newNode = new Node(key, val, 1);

		if (leftNode != null)
			newNode.size = leftNode.size + 1;

		newNode.left = leftNode;

		newNode.right = null;

		newNode.key = key;

		newNode.val = val;

		node.left = newNode;

		node.size = node.size + 1;

		return newNode;
	}

	/**
	 * Inserts the specified key-value pair as a right node of node with key x
	 * into the binary tree. The main idea is to find the node with key x and
	 * then create a new node and insert it at right of node with key x
	 *
	 */

	public Node putRight(Key x, Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to putRight() is null");
		Node node = getNode(x);

		if (node == null)
			return null;

		Node rightNode = node.right;

		Node newNode = new Node(key, val, 1);

		if (rightNode != null)
			newNode.size = rightNode.size + 1;

		newNode.left = null;

		newNode.right = rightNode;

		newNode.key = key;

		newNode.val = val;

		node.right = newNode;

		node.size = node.size + 1;

		return newNode;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	// metodo responsavel por deletar
	public void deleteMin() {
		root = deleteMin(root);

	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		return x;

	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;

			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
			

		}
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
	public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

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

		StdOut.println();

		st.preorderTraversal();
	}
}
