package Q2_05_Sum_Lists;

import CtCILibrary.LinkedListNode;

public class HectorForward {
	
	static class NodeCarryPair {
		public LinkedListNode node;
		public int carry;
		
		public NodeCarryPair(LinkedListNode node, int carry) {
			this.node = node;
			this.carry = carry;
		}
	}
	
	static int getLength(LinkedListNode listHead) {
		int length = 0;
		for (LinkedListNode n = listHead; n != null; n = n.next) {
			length++;
		}
		return length;
	}
	
	static LinkedListNode insertBefore(LinkedListNode listHead, int data) {
		LinkedListNode newHead = new LinkedListNode(data);
		newHead.next = listHead;
		return newHead;
	}
	
	static LinkedListNode addZerosToBeginning(LinkedListNode listHead, int padding) {
		LinkedListNode newHead = listHead;
		for (int i = 0; i < padding; i++) {
			newHead = insertBefore(newHead, 0);
		}
		return newHead;
	}
	
	static LinkedListNode add(LinkedListNode num1, LinkedListNode num2) {
		
		int lengthNum1 = getLength(num1);
		int lengthNum2 = getLength(num2);
		
		LinkedListNode cleanedNum1 = null;
		LinkedListNode cleanedNum2 = null;
		
		if (lengthNum1 < lengthNum2) {
			cleanedNum1 = addZerosToBeginning(num1, lengthNum2 - lengthNum1);
			cleanedNum2 = num2;
		} else {
			cleanedNum1 = num1;
			cleanedNum2 = addZerosToBeginning(num2, lengthNum1 - lengthNum2);
		}		
		
		NodeCarryPair answer = addAux(cleanedNum1, cleanedNum2);
		if (answer.carry > 0) {
			answer.node = insertBefore(answer.node, answer.carry);
			answer.carry = 0;
		}
		return answer.node;
	}
	
	/* Same length num1 and num2 */
	static NodeCarryPair addAux(LinkedListNode num1, LinkedListNode num2) {
		if (num1 == null) {
			return new NodeCarryPair(null, 0);
		}
		NodeCarryPair sumRemaining = addAux(num1.next, num2.next);
		int sum = num1.data + num2.data + sumRemaining.carry;
		int nextDigit = sum % 10;
		int nextCarry = sum / 10;
		
		LinkedListNode newNode = insertBefore(sumRemaining.node, nextDigit);
		return new NodeCarryPair(newNode, nextCarry);
	}
}
