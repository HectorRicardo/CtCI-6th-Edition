package Q4_02_Minimal_Tree;

import CtCILibrary.TreeNode;

public class Hector {
	/*
	public class TreeNode {
		public int data;
		public TreeNode left;
		public TreeNode right;
	}
	*/
	
	static TreeNode createMinimalBST(int[] array) {
		return createMinimalBSTAux(array, 0, array.length);
	}
	
	/* Start is inclusive, end is exclusive */
	static TreeNode createMinimalBSTAux(int[] array, int start, int end) {
		int length = end - start;
		if (length == 0) {
			return null;
		}
		int midpoint = start + (length / 2);
		TreeNode root = new TreeNode(array[midpoint]);
		root.left = createMinimalBSTAux(array, start, midpoint);
		root.right = createMinimalBSTAux(array, midpoint + 1, end);
		return root;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		//int[] array = {1, 2, 3, 4};
		
		// We needed this code for other files, so check out the code in the library
		TreeNode root = createMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Created BST? " + root.isBST());
		System.out.println("Height: " + root.height());
	}
}
