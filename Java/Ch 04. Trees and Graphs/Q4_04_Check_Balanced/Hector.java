package Q4_04_Check_Balanced;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

public class Hector {
	
	static boolean isBalanced(TreeNode root) {
		return getHeightAndBalance(root) != -1;
	}
	
	static int getHeightAndBalance(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int leftHeight = getHeightAndBalance(root.left);
		int rightHeight = getHeightAndBalance(root.right);
		
		if (leftHeight == -1 || rightHeight == -1) {
			return -1;
		}
		
		return Math.abs(leftHeight - rightHeight) > 1 ? -1 : Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static void main(String[] args) {
		// Create balanced tree
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Is balanced? " + isBalanced(root));
		
		// Could be balanced, actually, but it's very unlikely...
		TreeNode unbalanced = new TreeNode(10);
		for (int i = 0; i < 10; i++) {
			unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100));
		}
		System.out.println("Root? " + unbalanced.data);
		System.out.println("Is balanced? " + isBalanced(unbalanced));
	}

}
