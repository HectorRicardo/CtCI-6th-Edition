package Q1_04_Palindrome_Permutation;

public class Hector {
	
	static boolean isPermutationOfPalindrome(String inputProblem) {
		String input = inputProblem.toLowerCase();
		int bitArray = 0;
		for (int i = 0; i < input.length(); i++) {
			
			// convert letter to number, i.e. a=0, b=1, c=2.... z=25
			int letterNumber = letterToNumber(input.charAt(i));
			
			if (letterNumber != -1) {
			
				// Get single bit vector for letterNumber
				// Will be  000...0000000 for a
				//			000...0000010 for b
				// 			000...0000100 for c
				//			.....
				//			100...0000000 for z
				int singleBitArray = 1 << letterNumber;
				
				// turn on and off switch in bit array
				bitArray ^= singleBitArray;
			}
			
			
		}
		boolean isPowerOf2 = (bitArray & (bitArray - 1)) == 0;
		boolean containsOnlyOneOne = isPowerOf2;
		return bitArray == 0 || containsOnlyOneOne;
	}
	
	// convert letter to number, i.e. a=0, b=1, c=2.... z=25
	static int letterToNumber(char letter) {
		int letterNumber = letter - 'a';
		if (0 > letterNumber || 25 < letterNumber) return -1;
		return letterNumber;
	}
}