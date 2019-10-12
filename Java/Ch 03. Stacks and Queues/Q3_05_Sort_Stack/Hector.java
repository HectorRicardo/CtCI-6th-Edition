package Q3_05_Sort_Stack;

import java.util.Stack;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	public static void sort(Stack<Integer> s) {
		Stack<Integer> reverseSortedStack = new Stack<>();
		while (!s.isEmpty()) {
			int current = s.pop();
			while (!reverseSortedStack.isEmpty() && current < reverseSortedStack.peek()) {
				s.push(reverseSortedStack.pop()); // use s as temporary stack
			}
			reverseSortedStack.push(current);
		}
		
		// Now s is empty, reverseSortedStack has elements sorted
		while (!reverseSortedStack.isEmpty()) {
			s.push(reverseSortedStack.pop());
		}
	}

	public static void main(String [] args) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < 10; i++) {
			int r = AssortedMethods.randomIntInRange(0,  1000);
			s.push(r);
		}
		
		sort(s);
		
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}
}
