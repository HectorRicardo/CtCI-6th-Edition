package Q5_03_Flip_Bit_to_Win;

public class Hector {
	
	static int longestSequence(int num) {
		
		if (num == -1) return Integer.BYTES * 8;
		
		int maxLengthWithFlip = 0;
		int currentLengthWithFlip = 0;
		int currentLengthWithoutFlip = 0;
		boolean bitFlipped = false;
		
		while (num != 0) {
			if ((num & 1) == 1) { // if current bit is equal to 1
				currentLengthWithFlip++;
				currentLengthWithoutFlip++;
			} else if (bitFlipped) {
				maxLengthWithFlip = Math.max(maxLengthWithFlip, currentLengthWithFlip);
				currentLengthWithFlip = currentLengthWithoutFlip + 1;
				currentLengthWithoutFlip = 0;
			} else {
				bitFlipped = true;
				currentLengthWithoutFlip = 0;
				currentLengthWithFlip++;
			}
			num >>>= 1;
		}
		
		maxLengthWithFlip = Math.max(maxLengthWithFlip, currentLengthWithFlip) + (bitFlipped ? 0 : 1);
		return maxLengthWithFlip;
	}
	
	public static void main(String[] args) {
		System.out.println(longestSequence(2147483647));
	}
}
