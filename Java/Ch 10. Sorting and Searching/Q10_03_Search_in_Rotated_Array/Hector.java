package Q10_03_Search_in_Rotated_Array;

public class Hector {
	
	private static int findFirstElement(int[] array) {
		int startFirstHalf = 0;
		int endFirstHalf = array.length - 1;
		int startSecondHalf = 0;
		int endSecondHalf = array.length - 1;
		
		while (endFirstHalf != startSecondHalf && array[endFirstHalf] <= array[startSecondHalf]) {
			
			if (array[startFirstHalf] >= array[endFirstHalf]) {
				endSecondHalf = endFirstHalf;
				endFirstHalf = startFirstHalf + (endFirstHalf - startFirstHalf) / 2;
			} else {
				startFirstHalf = startSecondHalf;
				endFirstHalf = startSecondHalf + (endSecondHalf - startSecondHalf) / 2;
			}
			
			startSecondHalf = endFirstHalf + 1;
		}
		
		return startSecondHalf;
	}
	
	public static int search(int[] array, int elem) {
		int firstIdx = findFirstElement(array);
		
		int start = 0;
		int end = array.length - 1;
		
		while (start <= end) {
			int middleIdx = start + (end - start) / 2;
			int middleElement = getIthElem(array, middleIdx, firstIdx);
			if (elem < middleElement) {
				end = middleIdx - 1;
			} else if (elem > middleElement) {
				start = middleIdx + 1;
			} else {
				return (firstIdx + middleIdx) % array.length;
			}
		}
		
		return -1;
	}
	
	private static int getIthElem(int[] array, int i, int first) {
		return array[(first + i) % array.length];
	}
	
	
	public static void main(String[] args) {
		int[] a = { 2, 3, 1, 2, 2, 2, 2, 2 , 2 , 2 };
		
		System.out.println(search(a, 2));
		System.out.println(search(a, 3));
		System.out.println(search(a, 4));
		System.out.println(search(a, 1));
		System.out.println(search(a, 8));
		
		int[] b = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		System.out.println(search(b, 5));
	}

}
