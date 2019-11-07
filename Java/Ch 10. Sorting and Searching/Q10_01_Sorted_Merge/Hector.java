package Q10_01_Sorted_Merge;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	private static void merge(int[] a, int[] b, int aLength, int bLength) {
		int i = aLength - 1;
		int j = bLength - 1;
		
		int current = a.length - 1;
		
		while (j >= 0) {
			if (i < 0 || a[i] < b[j]) {
				a[current] = b[j];
				j--;
			} else {
				a[current] = a[i];
				i--;
			}
			current--;
		}
	}
	
	public static void main(String[] args) {
		int[] a; // = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
		int[] b; // = {1, 4, 7, 6, 7, 7};
		a = new int[]{9, 9, 9, 9, 9, 9, 10, 100, 0, 0, 0, 0, 0, 0};
		b = new int[]{1, 4, 7, 6, 7, 7};
		merge(a, b, 8, 6);
		System.out.println(AssortedMethods.arrayToString(a));
	}
}
