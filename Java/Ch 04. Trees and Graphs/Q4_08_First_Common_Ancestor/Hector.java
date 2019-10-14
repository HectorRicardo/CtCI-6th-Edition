package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

public class Hector {
	
	// Solution1
	private static class NodeInfo {
		public int depth;
		public TreeNode node;
		public NodeInfo parentInfo;
		
		public NodeInfo(TreeNode node, int depth, NodeInfo parentInfo) {
			this.node = node;
			this.depth = depth;
			this.parentInfo = parentInfo;
		}
	}
	
	static TreeNode commonAncestor1(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null) throw new RuntimeException("Tree cannot be null");
		
		NodeInfo node1Info = getNodeInfo(node1, root);
		NodeInfo node2Info = getNodeInfo(node2, root);
		
		if (node1Info == null || node2Info == null) throw new RuntimeException("Both nodes should exist in the tree");

		NodeInfo deeperNode, shallowerNode;
		if (node1Info.depth > node2Info.depth) {
			deeperNode = node1Info;
			shallowerNode = node2Info;
		} else {
			deeperNode = node2Info;
			shallowerNode = node1Info;
		}

		// got to deeper's node ancestor at same level than shallowerNode
		NodeInfo ancestorDeeperNode = deeperNode;
		while (ancestorDeeperNode.depth > shallowerNode.depth) {
			ancestorDeeperNode = ancestorDeeperNode.parentInfo;
		}
		
		NodeInfo ancestorShallowerNode = shallowerNode;
		while (ancestorDeeperNode.node != ancestorShallowerNode.node) {
			ancestorDeeperNode = ancestorDeeperNode.parentInfo;
			ancestorShallowerNode = ancestorShallowerNode.parentInfo;
		}
		
		// return either
		return ancestorShallowerNode.node;
	}
	
	private static NodeInfo getNodeInfo(TreeNode node, TreeNode root) {
		return getNodeInfo(node, root, 0, null);
	}
	
	private static NodeInfo getNodeInfo(TreeNode node, TreeNode root, int depth, NodeInfo parentInfo) {
		if (root == null) return null;
		
		NodeInfo rootInfo = new NodeInfo(root, depth, parentInfo);
		if (root == node) return rootInfo;
		
		int nextDepth = depth + 1;
		
		NodeInfo leftInfo = getNodeInfo(node, root.left, nextDepth, rootInfo);
		if (leftInfo != null) return leftInfo;
		
		NodeInfo rightInfo = getNodeInfo(node, root.right, nextDepth, rootInfo);
		if (rightInfo != null) return rightInfo;
		
		return null;
	}
	
	// Solution 2
	private static class HectorResult {
		public boolean node1Found;
		public boolean node2Found;
		public TreeNode commonAncestor;
		
		public HectorResult(boolean node1Found, boolean node2Found, TreeNode commonAncestor) {
			this.node1Found = node1Found;
			this.node2Found = node2Found;
			this.commonAncestor = commonAncestor;
		}
	}
	
	static TreeNode commonAncestor2(TreeNode root, TreeNode node1, TreeNode node2) {
		return commonAncestorAux2(root, node1, node2).commonAncestor;
	}
	
	static HectorResult commonAncestorAux2(TreeNode root, TreeNode node1, TreeNode node2) {
		
		if (root == null) return new HectorResult(false, false, null);
		
		if (root == node1) {
			if (node1 == node2) return new HectorResult(true, true, root);
			if (node2 != null) {
				HectorResult leftResult = commonAncestorAux2(root.left, null, node2);
				if (leftResult.node2Found) {
					return new HectorResult(true, true, root);
				}
				HectorResult rightResult = commonAncestorAux2(root.right, null, node2);
				if (rightResult.node2Found) {
					return new HectorResult(true, true, root);
				}
			}
			return new HectorResult(true, false, null);
		}
		if (root == node2) {
			if (node2 == node1) return new HectorResult(true, true, root);
			if (node1 != null) {
				HectorResult leftResult = commonAncestorAux2(root.left, node1, null);
				if (leftResult.node1Found) {
					return new HectorResult(true, true, root);
				}
				HectorResult rightResult = commonAncestorAux2(root.right, node1, null);
				if (rightResult.node1Found) {
					return new HectorResult(true, true, root);
				}
			}
			return new HectorResult(false, true, null);
		}
		
		// Root node is not either node 1 or node 2
		HectorResult leftResult = commonAncestorAux2(root.left, node1, node2);
		if (leftResult.commonAncestor != null) {
			return leftResult;
		} 
		TreeNode nodeToLook1 = leftResult.node1Found ? null : node1;
		TreeNode nodeToLook2 = leftResult.node2Found ? null : node2;
		
		HectorResult rightResult = commonAncestorAux2(root.right, nodeToLook1, nodeToLook2);
		if (rightResult.commonAncestor != null) {
			return rightResult;
		}
		
		boolean node1Found = leftResult.node1Found || rightResult.node1Found;
		boolean node2Found = leftResult.node2Found || rightResult.node2Found;
		
		return new HectorResult(node1Found, node2Found, node1Found && node2Found ? root : null);
	}
	
	// Solution 3
	static class Result {
		public TreeNode node;
		public boolean isAncestor;
		public Result(TreeNode node, boolean isAncestor) {
			this.node = node;
			this.isAncestor = isAncestor;
		}
	}
	
	static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p != null && q != null) {
			Result result = commonAncestorAux(root, p, q);
			if (result.isAncestor) {
				return result.node;
			} else {
				throw new RuntimeException("Both nodes should be in the three");
			}
		}
		throw new RuntimeException("Nodes p and q should be defined");
	}
	
	// P cannot be null, Q can be null
	static Result commonAncestorAux(TreeNode root, TreeNode p, TreeNode q) {
		
		if (root == null) return new Result(null, false); // Case: Finished searching tree and nodes were not found
		
		if (root == p || root == q) {
			if (p == q) {
				return new Result(root, true); // Same node, they exist on the tree, they are their own ancestor
			}
			
			if (q == null) {
				return new Result(root, false); // Done with the search, found the missing one
			}
			
			// One is still missing
			TreeNode missing = root == p ? q : p;
			Result resultLeft = commonAncestorAux(root.left, missing, null);
			if (resultLeft.node == missing) {
				return new Result(root, true);
			}
			
			Result resultRight = commonAncestorAux(root.right, missing, null);
			if (resultRight.node == missing) {
				return new Result(root, true);
			}
			
			// The missing node was not found
			return new Result(root, false);
		}
		
		// Node (or nodes) still not found
		Result resultLeft = commonAncestorAux(root.left, p, q);
		if (resultLeft.isAncestor) {
			return resultLeft;
		}
		if (resultLeft.node != null) {
			if (q == null) {
				return resultLeft; // Done with the search, we were looking only for one
			}
			
			// One is still missing
			TreeNode missing = resultLeft.node == p ? q : p;
			Result resultRight = commonAncestorAux(root.right, missing, null);
			if (resultRight.node == missing) {
				return new Result(root, true);
			}
			
			return resultLeft;
		}
		return commonAncestorAux(root.right, p, q);
	}
	
	public static void main(String[] args) {
		main0(args);
		mainE(args);
		mainF(args);
	}
	
	public static void main0(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(1);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(root, n3, n7);
		System.out.println(ancestor.data);
	}
	
//	public static void mainA(String[] args) {
//		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//		TreeNode root = TreeNode.createMinimalBST(array);
//		TreeNode n3 = root.find(8);
//		TreeNode n7 = root.find(8);
//		TreeNode ancestor = commonAncestor(n3, n7);
//		System.out.println(ancestor.data);
//	}
	
	public static void mainE(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(10);
		TreeNode n7 = root.find(6);
		TreeNode ancestor = commonAncestor(root, n3, n7);
		if (ancestor != null) {
			System.out.println(ancestor.data);
		} else {
			System.out.println("null");
		}
	}
	
	public static void mainF(String[] args) {
		int[] array = {5, 3, 6, 1, 9, 11};
		TreeNode root = new TreeNode(20);
		for (int a : array) {
			root.insertInOrder(a);
		}
		TreeNode n1 = root.find(1);
		TreeNode n9 = root.find(9);
		TreeNode ancestor = commonAncestor(root, n1, n9);
		System.out.println(ancestor.data);
	}
}
