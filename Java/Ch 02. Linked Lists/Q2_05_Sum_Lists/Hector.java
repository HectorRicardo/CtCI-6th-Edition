package Q2_05_Sum_Lists;

import CtCILibrary.LinkedListNode;

public class Hector {
	
	static LinkedListNode addLists(LinkedListNode num1, LinkedListNode num2) {
		return addListsAux(num1, num2, 0);
	}
	
	static LinkedListNode addListsAux(LinkedListNode num1, LinkedListNode num2, int carry) {
		if (num1 == null && num2 == null & carry == 0) {
			return null;
		}
		int digit1 = num1 == null ? 0 : num1.data;
		int digit2 = num2 == null ? 0 : num2.data;
		int sum = digit1 + digit2 + carry;
		
		LinkedListNode result = new LinkedListNode(sum % 10);
		result.next = addListsAux(num1 == null ? null : num1.next, num2 == null ? null : num2.next, sum / 10);
		return result;
	}
	
	static LinkedListNode addLists2(LinkedListNode num1, LinkedListNode num2) {
		
		// Setup
		LinkedListNode answerHead = null;
		int carry = 0;

		LinkedListNode p1 = num1;
		LinkedListNode p2 = num2;
		LinkedListNode answerTail = null;
		
		while (p1 != null || p2 != null) {
			int digit1 = p1 == null ? 0 : p1.data;
			int digit2 = p2 == null ? 0 : p2.data;
			
			int sum = digit1 + digit2 + carry;
			LinkedListNode newNode = new LinkedListNode(sum % 10);
			carry = sum / 10;
			
			if (answerHead == null) {
				answerHead = newNode;
			} else {
				answerTail.next = newNode;
			}
			
			answerTail = newNode;
			p1 = p1 == null ? null : p1.next;
			p2 = p2 == null ? null : p2.next;
		}
		
		if (carry > 0) {
			LinkedListNode newNode = new LinkedListNode(carry);
			answerTail.next = newNode;
			answerTail = newNode;
		}
		
		return answerHead;
	}
	
	public static void main(String[] args) {
		LinkedListNode lA1 = new LinkedListNode(9, null, null);
		LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
		LinkedListNode lA3 = new LinkedListNode(9, null, lA2);
		
		LinkedListNode lB1 = new LinkedListNode(1, null, null);
		LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
		LinkedListNode lB3 = new LinkedListNode(0, null, lB2);	
		
		LinkedListNode list3 = addLists(lA1, lB1);
		
		System.out.println("  " + lA1.printForward());		
		System.out.println("+ " + lB1.printForward());			
		System.out.println("= " + list3.printForward());	
		
		int l1 = linkedListToInt(lA1);
		int l2 = linkedListToInt(lB1);
		int l3 = linkedListToInt(list3);
		
		System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
		System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));		
	}
	
	public static int linkedListToInt(LinkedListNode node) {
		int value = 0;
		if (node.next != null) {
			value = 10 * linkedListToInt(node.next);
		}
		return value + node.data;
	}
	
	/*
	static LinkedListNode sum(LinkedListNode num1, LinkedListNode num2) {
		
		// Setup
		LinkedListNode answerHead = null;
		int carry = 0;

		LinkedListNode p1 = num1;
		LinkedListNode p2 = num2;
		LinkedListNode answerTail = null;
		
		while (p1 != null && p2 != null) {
			NodeCarryPair nodeCarryPair = sum(p1.data, p2.data, carry);
			carry = nodeCarryPair.carry;
			
			if (answerHead == null) {
				answerHead = nodeCarryPair.node;
				answerTail = nodeCarryPair.node;
			} else {
				answerTail.next = nodeCarryPair.node;
				answerTail = answerTail.next;
			}
			
			p1 = p1.next;
			p2 = p2.next;
		}
		while (p1 != null) {
			NodeCarryPair nodeCarryPair = sum(p1.data, 0, carry);
			carry = nodeCarryPair.carry;
			
			if (answerHead == null) {
				answerHead = nodeCarryPair.node;
				answerTail = nodeCarryPair.node;
			} else {
				answerTail.next = nodeCarryPair.node;
				answerTail = answerTail.next;
			}
			p1 = p1.next;
		}
		while (p2 != null) {
			NodeCarryPair nodeCarryPair = sum(p1.data, 0, carry);
			carry = nodeCarryPair.carry;
			
			if (answerHead == null) {
				answerHead = nodeCarryPair.node;
				answerTail = nodeCarryPair.node;
			} else {
				answerTail.next = nodeCarryPair.node;
				answerTail = answerTail.next;
			}
			p1 = p1.next;
		}
		
		return answerHead;
	}
	static NodeCarryPair sum(int a, int b, int carry) {
		int sum = a + b + carry;
		LinkedListNode node = new LinkedListNode();
		node.data = sum % 10;
		int newCarry = sum / 10;
		return new NodeCarryPair(node, newCarry);
	}
	*/

}
