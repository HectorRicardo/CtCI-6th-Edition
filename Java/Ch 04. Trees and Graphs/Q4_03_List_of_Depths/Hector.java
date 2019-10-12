package Q4_03_List_of_Depths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

public class Hector {
	
	static ArrayList<LinkedList<TreeNode>> getLinkedListsBFS(TreeNode root) {
		
		ArrayList<LinkedList<TreeNode>> nodesInDepth = new ArrayList<>();
		
		LinkedList<TreeNode> nodesInCurrentDepth = new LinkedList<>();
		if (root != null) nodesInCurrentDepth.add(root);
		
		while (!nodesInCurrentDepth.isEmpty()) {
			nodesInDepth.add(nodesInCurrentDepth);
			LinkedList<TreeNode> nodesInPreviousDepth = nodesInCurrentDepth;
			nodesInCurrentDepth = new LinkedList<>();
			for (TreeNode node : nodesInPreviousDepth) {
				if (node.left != null) nodesInCurrentDepth.add(node.left);
				if (node.right != null) nodesInCurrentDepth.add(node.right);
			}
		}
		
		return nodesInDepth;
	}
	
	/*
	static ArrayList<LinkedList<TreeNode>> getLinkedListsBFS(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> nodesInDepth = new ArrayList<>();
		if (root == null) return nodesInDepth;
		nodesInDepth.add(new LinkedList<TreeNode>());
		nodesInDepth.get(0).add(root);
		
		int currentDepth = 1;
		while (currentDepth - 1 < nodesInDepth.size()) {
			int previousDepth = currentDepth - 1;
			LinkedList<TreeNode> nodesInCurrentDepth = new LinkedList<>();
			for (TreeNode node : nodesInDepth.get(previousDepth)) {
				if (node.left != null) nodesInCurrentDepth.add(node.left);
				if (node.right != null) nodesInCurrentDepth.add(node.right);
			}
			if (!nodesInCurrentDepth.isEmpty()) {
				nodesInDepth.add(nodesInCurrentDepth);
			}
			currentDepth++;
		}
		
		return nodesInDepth;
	}
	*/
	static ArrayList<LinkedList<TreeNode>> getLinkedListsDFS(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
		populateResult(result, root, 0);
		return result;
	}
	
	private static void populateResult(ArrayList<LinkedList<TreeNode>> result, TreeNode root, int depth) {
		// Base case
		if (root == null) return;
		
		if (depth == result.size()) result.add(new LinkedList<TreeNode>());
		result.get(depth).add(root);
		
		int nextDepth = depth + 1;
		populateResult(result, root.left, nextDepth);
		populateResult(result, root.right, nextDepth);
	}
	
	static void printResult(ArrayList<LinkedList<TreeNode>> result){
		int depth = 0;
		for(LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while(i.hasNext()){
				System.out.print(" " + ((TreeNode)i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}
	

	public static void main(String[] args) {
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		ArrayList<LinkedList<TreeNode>> list = getLinkedListsBFS(root);
		printResult(list);
	}

}
