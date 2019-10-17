package Q4_12_Paths_with_Sum;

import java.util.HashMap;
import java.util.Map;

import CtCILibrary.TreeNode;

public class Hector {
	
	static int countPathsWithSumTouching(TreeNode root, int targetSum) {
		if (root == null) return 0;
		int onlyCount = root.data == targetSum ? 1 : 0;
		return onlyCount + countPathsWithSumTouching(root.left, targetSum - root.data) + countPathsWithSumTouching(root.right, targetSum - root.data);
	}
	
	static int countPathsWithSumPrel(TreeNode root, int targetSum) {
		if (root == null) return 0; 
		int touchingCount = countPathsWithSumTouching(root, targetSum);
		int leftCount = countPathsWithSumPrel(root.left, targetSum);
		int rightCount = countPathsWithSumPrel(root.right, targetSum);
		
		return leftCount + rightCount + touchingCount;
	}
	
	static int countPathsWithSum(TreeNode root, int targetSum) {
		Map<Integer, Integer> accumSumOfPathHash = new HashMap<>();
		accumSumOfPathHash.put(0, 1);
		return countPathsWithSum(root, targetSum, 0, accumSumOfPathHash);
	}
	
	static int countPathsWithSum(TreeNode root, int targetSum, int accum, Map<Integer, Integer> accumSumOfPathHash) {
		if (root == null) return 0;
		
		int newSumAccum = accum + root.data;
		int pathsUpToThisNode = accumSumOfPathHash.getOrDefault(newSumAccum - targetSum, 0);
		
		accumSumOfPathHash.put(newSumAccum, 1 + accumSumOfPathHash.getOrDefault(newSumAccum, 0));
		
		int leftPaths = countPathsWithSum(root.left, targetSum, newSumAccum, accumSumOfPathHash);
		int rightPaths = countPathsWithSum(root.right, targetSum, newSumAccum, accumSumOfPathHash);
		
		accumSumOfPathHash.put(newSumAccum, accumSumOfPathHash.get(newSumAccum) - 1);
		
		return pathsUpToThisNode + leftPaths + rightPaths;
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(2);
		
		root.left = left;
		root.right = right;
		
		left.left = left1;
		right.right = right1;
		System.out.println(countPathsWithSum(root, 4));
		mainA(args);
		mainB(args);
	}
	
	public static void mainA(String [] args) {
		/*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);		
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(8);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(6);	
		System.out.println(countPathsWithSum(root, 0));*/
		
		/*TreeNode root = new TreeNode(-7);
		root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(countPathsWithSum(root, -14));*/
		
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.left.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		System.out.println(countPathsWithSum(root, 4));
	}
	
	public static void mainB(String [] args) {
		/*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);		
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(8);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(6);	
		root.right.left.left = new TreeNode(0);	
		System.out.println(countPathsWithSum(root, 0));
		*/
		
		/*TreeNode root = new TreeNode(-7);
		root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(countPathsWithSum(root, 0));*/
		
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.left.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		System.out.println(countPathsWithSum(root, 4));
	}
}
