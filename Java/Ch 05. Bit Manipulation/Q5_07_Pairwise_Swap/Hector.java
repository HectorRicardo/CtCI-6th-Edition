package Q5_07_Pairwise_Swap;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	public static int swapOddEvenBits(int x) {
		int oddBits = x & 0xAAAAAAAA;
		int evenBits = x & 0x55555555;
		return  (oddBits >>> 1) | (evenBits << 1); 
	}
	
	public static void main(String[] args) {
		int a = 234321;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		int b = swapOddEvenBits(a);
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
	}

}
