package Q8_03_Magic_Index;

public class Hector {
	
	public static int magicIndex(int[] array) {
		int start = 0;
		int end = array.length - 1;
		while (start <= end) {
			int middle = start + ((end - start) / 2);
			if (array[middle] > middle) {
				end = middle - 1;
			} else if (array[middle] < middle) {
				start = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	
	public static int magicIndexNotDistinct(int[] array) {
		return magicIndexNotDistinct(array, 0, array.length - 1);
	}
	
	private static int magicIndexNotDistinct(int[] array, int start, int end) {
		if (start > end) return -1;
		
		int middle = start + ((end - start) / 2);
		if (array[middle] == middle) {
			return middle;
		}
		
		int leftMagicIndex = magicIndexNotDistinct(array, start, Math.min(middle - 1, array[middle]));
		if (leftMagicIndex > -1) {
			return leftMagicIndex;
		}
		return magicIndexNotDistinct(array, Math.max(middle + 1, array[middle]), end);
	}
	
	public static void main(String[] args) {
		int[] array = {-1, 1, 6, 9, 10, 15, 20};
		System.out.println(magicIndexNotDistinct(array));
	}
}
