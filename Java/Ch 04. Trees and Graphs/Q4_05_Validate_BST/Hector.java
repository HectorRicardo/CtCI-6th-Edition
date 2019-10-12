package Q4_05_Validate_BST;

import CtCILibrary.TreeNode;

public class Hector {
	
	private static class TreeInfo {
		public int lowest;
		public int highest;
		public boolean isBST;
		
		public TreeInfo(int lowest, int highest, boolean isBST) {
			this.lowest = lowest;
			this.highest = highest;
			this.isBST = isBST;
		}
	}
	
	static boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	// Parameter won't ever be null
	static boolean isBST(TreeNode root, int lowerBound, int upperBound) {
		if (root == null) return true;
		return (lowerBound < root.data && root.data < upperBound) && isBST(root.left, lowerBound, root.data) && isBST(root.right, root.data, upperBound);
	}
	
	static TreeInfo getTreeInfo(TreeNode root) {
		if (root == null) return new TreeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
		TreeInfo left = getTreeInfo(root.left);
		TreeInfo right = getTreeInfo(root.right);
		return new TreeInfo(Math.min(left.lowest, root.data), Math.max(right.highest, root.data),  left.isBST && right.isBST && (left.highest < root.data && root.data < right.lowest));
	}
}
