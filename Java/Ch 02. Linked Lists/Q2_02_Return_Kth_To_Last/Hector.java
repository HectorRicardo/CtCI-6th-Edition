package Q2_02_Return_Kth_To_Last;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

public class Hector {
	
	static LinkedListNode kthToLast(LinkedListNode head, int k) {
		LinkedListNode kAhead = head;
		for (int i = 0; i < k; i++) {
			if (kAhead == null) {
				return null;
				//throw new IndexOutOfBoundsException("There are less than elements in linked list");
			}
			kAhead = kAhead.next;
		}
		LinkedListNode kToLast = head;
		while (kAhead != null) {
			kAhead = kAhead.next;
			kToLast = kToLast.next;
		}
		return kToLast;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode node = kthToLast(head, i);
			String nodeValue = node == null ? "null" : "" + node.data;
			System.out.println(i + ": " + nodeValue);
		}
	}
}
