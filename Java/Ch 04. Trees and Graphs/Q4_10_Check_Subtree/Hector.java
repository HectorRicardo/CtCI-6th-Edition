package Q4_10_Check_Subtree;

import java.util.LinkedList;
import java.util.Queue;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

public class Hector {
	
	static int getHeight(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}

	static boolean treesAreEqual(TreeNode root1, TreeNode root2) {
		if (root1 == root2) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		
		return root1.data == root2.data && treesAreEqual(root1.left, root2.left) && treesAreEqual(root1.right, root2.right);
	}

	static boolean containsTree2(TreeNode T1, TreeNode T2) {
		if (T1 == T2 || T2 == null) {
			return true;
		}
		if (T1 == null) {
			return false;
		}
		
		int differenceHeights = getHeight(T1) - getHeight(T2);
		if (differenceHeights < 0) throw new RuntimeException("T1 must be bigger than T2");
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(T1);
		int nodesInQueue = 1;
		for (int i = 0; i < differenceHeights; i++) {
			int nodesAdded = 0;
			for (int n = 0; n < nodesInQueue; n++) {
				TreeNode next = queue.poll();
				if (next.left != null) {
					queue.add(next.left);
					nodesAdded++;
				}
				if (next.right != null) {
					queue.add(next.right);
					nodesAdded++;
				}
			}
			nodesInQueue = nodesAdded;
		}
		
		while (!queue.isEmpty()) {
			TreeNode candidate = queue.poll();
			if (treesAreEqual(candidate, T2)) {
				return true;
			}
		}
		
		return false;
	}
	
	static void preOrderWithNullsTraversal(TreeNode root, StringBuffer buffer) {
		if (root == null) {
			buffer.append((String)null);
		} else {
			buffer.append(root.data);
			preOrderWithNullsTraversal(root.left, buffer);
			preOrderWithNullsTraversal(root.right, buffer);
		}
	}
	
	static boolean containsTree(TreeNode T1, TreeNode T2) {
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		preOrderWithNullsTraversal(T1, buffer1);
		preOrderWithNullsTraversal(T2, buffer2);
		
		return buffer1.indexOf(buffer2.toString()) >= 0;
	}
	
	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};
		int[] array2 = {2, 3, 1};
		
		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);

		if (containsTree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

		// t4 is not a subtree of t3
		int[] array3 = {1, 2, 3};
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

		if (containsTree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}
}
