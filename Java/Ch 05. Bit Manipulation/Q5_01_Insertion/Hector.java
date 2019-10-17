package Q5_01_Insertion;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	public static int updateBits(int n, int m, int i, int j) {
		int submask1 = j == 31 ? 0 : -1 << (j + 1);       // something like 111111100000000000
		int submask2 = (1 << i) - 1;        // something like 000000000000011111
		int mask = submask1 | submask2;     // something like 111111100000011111
		int innerBitsClearedInN = n & mask; // clear inner beats in n
		//System.out.println(AssortedMethods.toFullBinaryString(submask2));
		int mBitsShifted = m << i;          // put m bits in the middle
		return innerBitsClearedInN | mBitsShifted;
	}
	
	public static void main(String[] args) {
		int a = ~23423;
		System.out.println(AssortedMethods.toFullBinaryString(a));
		int b = 5;
		System.out.println(AssortedMethods.toFullBinaryString(b));		
		int c = updateBits(a, b, 29, 31);
		System.out.println(AssortedMethods.toFullBinaryString(c));
	}
	
	public static void main1(String[] args) {
		int x = -75;
		int xDivided = x / 128;
		int xShifted = x >> 7;
		System.out.println(xDivided);
		System.out.println(xShifted);
	}

}
