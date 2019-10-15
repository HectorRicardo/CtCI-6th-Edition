package Q4_09_BST_Sequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import CtCILibrary.TreeNode;

public class Hector {
	
	public static ArrayList<LinkedList<Integer>> allSequences(TreeNode root) {
		Set<TreeNode> candidates = new HashSet<>();
		if (root != null) candidates.add(root);
		return allSequencesAux(candidates);
	}
	
	public static ArrayList<LinkedList<Integer>> allSequencesAux(Set<TreeNode> candidates) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
		if (candidates.isEmpty())  {
			result.add(new LinkedList<Integer>());
		} else {
			for (TreeNode next : candidates) {
				Set<TreeNode> newCandidates = new HashSet<>(candidates);
				newCandidates.remove(next);
				if (next.left != null) {
					newCandidates.add(next.left);
				}
				if (next.right != null) {
					newCandidates.add(next.right);
				}
				ArrayList<LinkedList<Integer>> arrays = allSequencesAux(newCandidates);
				for (LinkedList<Integer> list : arrays) {
					list.addFirst(next.data);
				}
				result.addAll(arrays);
			}
		}
		return result;
	}
	
	static ArrayList<LinkedList<Integer>> allSequences2(TreeNode root) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
		if (root == null) {
			result.add(new LinkedList<Integer>());
		} else {
			ArrayList<LinkedList<Integer>> leftArrays = allSequences2(root.left);
			ArrayList<LinkedList<Integer>> rightArrays = allSequences2(root.right);
			
			for (LinkedList<Integer> leftArray : leftArrays) {
				for (LinkedList<Integer> rightArray : rightArrays) {
					ArrayList<LinkedList<Integer>> possibleOrders = weave(leftArray, rightArray);
					for (LinkedList<Integer> possibleOrder : possibleOrders) {
						possibleOrder.addFirst(root.data);
						result.add(possibleOrder);
					}
				}
			}
		}
		return result;
	}
	
	static ArrayList<LinkedList<Integer>> weave(LinkedList<Integer> arr1, LinkedList<Integer> arr2) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
		if (arr1.isEmpty() && arr2.isEmpty()) {
			result.add(new LinkedList<>());
		} else {
			if (!arr1.isEmpty()) {
				int head = arr1.removeFirst();
				ArrayList<LinkedList<Integer>> subResult = weave(arr1, arr2);
				for (LinkedList<Integer> possibleOrder : subResult) {
					possibleOrder.addFirst(head);
					result.add(possibleOrder);
				}
				arr1.addFirst(head);
			}
			if (!arr2.isEmpty()) {
				int head = arr2.removeFirst();
				ArrayList<LinkedList<Integer>> subResult = weave(arr1, arr2);
				for (LinkedList<Integer> possibleOrder : subResult) {
					possibleOrder.addFirst(head);
					result.add(possibleOrder);
				}
				arr2.addFirst(head);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		TreeNode node = new TreeNode(100);
		int[] array = {100, 50, 20, 75, 150, 120, 170};
		//TreeNode node = new TreeNode(4);
		//int[] array = {2, 6, 1, 3, 5, 7};
		for (int a : array) {
			node.insertInOrder(a);
		}
		ArrayList<LinkedList<Integer>> allSeq = allSequences2(node);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
		System.out.println(allSeq.size());
	}

}
