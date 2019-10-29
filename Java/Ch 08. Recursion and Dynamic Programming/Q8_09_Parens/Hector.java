package Q8_09_Parens;

import java.util.ArrayList;
import java.util.List;

public class Hector {
	
	public static List<String> generateParensMoreOrLess(int n) {
		List<String> result = new ArrayList<>();
		generateParensMoreOrLess(n, 0, "", result);
		return result;
	}
	
	private static String appendChar(String s, char c, int times) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < times; i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	private static void generateParensMoreOrLess(int n, int pendingToClose, String prefix, List<String> result) {
		if (n == 0) {
			prefix = appendChar(prefix, ')', pendingToClose);
			result.add(prefix);
		} else {
			String copyPrefix = prefix;
			for (int toBeClosed = 0; toBeClosed <= pendingToClose; toBeClosed++) {
				prefix = appendChar(copyPrefix, ')', toBeClosed);
				generateParensMoreOrLess(n - 1, pendingToClose - toBeClosed + 1, prefix + '(', result);
			}
		}
	}
	
	public static List<String> generateParensBad(int n) {
		List<String> result = new ArrayList<>();
		if (n == 0) {
			result.add("");
		} else {
			List<String> previousResult = generateParensBad(n - 1);
			for (String combination : previousResult) {
				result.add("()" + combination); // at the beginning
				int pending = 0;
				String beginning = "(";
				for (int i = 0; i < combination.length(); i++) {
					char currentChar = combination.charAt(i);
					if (currentChar == ')') {
						pending--;
					} else {
						pending++;
					}
					beginning += combination.charAt(i);
					if (pending == 0) {
						String newCombination = beginning + ')' + combination.substring(i + 1);
						result.add(newCombination);
					}
				}
			}
		}
		return result;
	}
	
	public static List<String> generateParens(int count) {
		char[] prefix = new char[2 * count];
		List<String> result = new ArrayList<>();
		generateParens(result, count, count, prefix, 0);
		return result;
	}
	
	private static void generateParens(List<String> result, int leftRemaining, int rightRemaining, char[] prefix, int nextIdx) {
		
		if (leftRemaining == 0 && rightRemaining == 0) {
			result.add(String.valueOf(prefix));
			return;
		}
		
		if (leftRemaining > 0) {
			prefix[nextIdx] = '(';
			generateParens(result, leftRemaining - 1, rightRemaining, prefix, nextIdx + 1);
		}
		if (rightRemaining > leftRemaining) {
			prefix[nextIdx] = ')';
			generateParens(result, leftRemaining, rightRemaining - 1, prefix, nextIdx + 1);
		}
	}
	
	public static void main(String[] args) {
		List<String> parens = generateParens(3);
		System.out.println(parens);
		System.out.println(parens.size());
		
		//Set<String> tester = new HashSet<>(parens);
		//System.out.println(tester.size());
	}
}
