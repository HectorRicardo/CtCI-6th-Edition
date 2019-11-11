package Q10_11_Peaks_and_Valleys;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	public static void sortValleyPeak(int[] array) {
		boolean shouldBePeak = false;
		for (int i = 1; i < array.length; i++) {
			if ((shouldBePeak && array[i] < array[i - 1]) || !shouldBePeak && array[i] > array[i - 1]) {
				swap(array, i, i - 1);
			}
			shouldBePeak = !shouldBePeak;
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] array = {5, 3, 1, 2, 3};
		System.out.println(AssortedMethods.arrayToString(array));
		sortValleyPeak(array);
		System.out.println(AssortedMethods.arrayToString(array));
		System.out.println(Tester.confirmValleyPeak(array));
	}
	
	public static void main2(String[] args) {
		int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
		System.out.println(AssortedMethods.arrayToString(array));
		sortValleyPeak(array);
		System.out.println(AssortedMethods.arrayToString(array));
		System.out.println(Tester.confirmValleyPeak(array));
	}
}
