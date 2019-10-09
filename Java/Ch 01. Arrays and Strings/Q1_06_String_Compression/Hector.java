package Q1_06_String_Compression;

public class Hector {
	
	static String compress2(String input) {
		StringBuilder outputBuilder = new StringBuilder();
		int i = 0;
		while (i < input.length()) {
			int j = 1;
			while (i + j < input.length() && input.charAt(i) == input.charAt(i + j)) {
				j++;
			}
			outputBuilder.append(input.charAt(i) + Integer.toString(j));
			i += j;
		}
		String compressedString = outputBuilder.toString();
		return compressedString.length() < input.length() ? compressedString : input;
	}
	
	static String compress(String input) {
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < input.length(); i++) {
			countConsecutive++;
			if (i + 1 >= input.length() || input.charAt(i + 1) != input.charAt(i)) {
				compressed.append(input.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.length() < input.length() ? compressed.toString() : input;
	}
	
	public static void main(String[] args) {
		System.out.println(compress("aabcccccaaa"));
	}
}