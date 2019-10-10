package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

public class HectorRecursive {
	
	static class Helper {
		LinkedListNode toBeCompared;
		boolean keepChecking;
		
		Helper(LinkedListNode toBeCompared, boolean keepChecking) {
			this.toBeCompared = toBeCompared;
			this.keepChecking = keepChecking;
		}
	}
	
	static int getLength(LinkedListNode listHead) {
		int length = 0;
		for (LinkedListNode n = listHead; n != null; n = n.next) {
			length++;
		}
		return length;
	}
	
	static boolean isPalindrome(LinkedListNode head) {
		int length = getLength(head);
		return checkPalindrome(head, length).keepChecking;
	}
	
	static Helper checkPalindrome(LinkedListNode current, int length) {
		
		// Base cases
		if (length == 0) {
			return new Helper(current, true);
		}
		if (length == 1) {
			return new Helper(current.next, true);
		}
		Helper responseSubProblem = checkPalindrome(current.next, length - 2);
		LinkedListNode nodeToBeCompared = responseSubProblem.toBeCompared;
		return new Helper(nodeToBeCompared.next, responseSubProblem.keepChecking && current.data == nodeToBeCompared.data);
	}
	
	/*
	static Helper checkPalindrome(LinkedListNode head, LinkedListNode current) {
		
		// Base case: We reached the end of the list
		if (current == null) { 
			return new Helper(head, true);
		}
		
		// Normal case
		Helper iterationData = checkPalindrome(head, current.next);
		// Everything is ok
		if (iterationData.keepChecking) {
			return new Helper(iterationData.toBeCompared.next, iterationData.toBeCompared.data == current.data);
		}
		return new Helper(null, false);
	}
	*/

}