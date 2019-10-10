package Q2_08_Loop_Detection;

import CtCILibrary.LinkedListNode;

public class Hector {
	
	static LinkedListNode findLoopBeginning(LinkedListNode head) {
		
		// Floyd loop detection algorithm
		LinkedListNode slow = head;
		LinkedListNode fast = head;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}
		
		if (fast == null || fast.next == null) {
			return null; // No cycle
		}
		
		LinkedListNode outsideList = head;
		LinkedListNode insideList = slow; // or fast
		
		while (outsideList != insideList) {
			outsideList = outsideList.next;
			insideList = insideList.next;
		}
		
		return outsideList;
	}
	
	public static void main(String[] args) {
		int list_length = 100;
		int k = 10;
		
		// Create linked list
		LinkedListNode[] nodes = new LinkedListNode[list_length];
		for (int i = 0; i < list_length; i++) {
			nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
		}
		
		// Create loop;
		nodes[list_length - 1].next = nodes[list_length - k];
		
		LinkedListNode loop = findLoopBeginning(nodes[0]);
		if (loop == null) {
			System.out.println("No Cycle.");
		} else {
			System.out.println(loop.data);
		}
	}
	
}
