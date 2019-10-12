package Q4_06_Successor;

import CtCILibrary.TreeNode;

public class Hector {
	
	// n is not null
	static TreeNode inorderSucc(TreeNode node) {
		assert(node != null);
		if (node.right != null) {
			return getLeftMostNode(node.right);
		} else {
			return getNextParentToTheRight(node);
		}
	}
	
	private static TreeNode getNextParentToTheRight(TreeNode node) {
		TreeNode parent = node.parent;
		while (parent != null && parent.data < node.data) {
			parent = parent.parent;
		}
		return parent;
	}
	
	// Node is not null
	private static TreeNode getLeftMostNode(TreeNode root) {
		TreeNode leftMostChild = root;
		while (leftMostChild.left != null) {
			leftMostChild = leftMostChild.left;
		}
		return leftMostChild;
	}
}
