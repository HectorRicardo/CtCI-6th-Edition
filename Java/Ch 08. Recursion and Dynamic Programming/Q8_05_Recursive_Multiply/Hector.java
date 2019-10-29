package Q8_05_Recursive_Multiply;

public class Hector {
	
	public static int minProduct(int a, int b) {
		int smaller, greater;
		if (a < b) {
			smaller = a;
			greater = b;
		} else {
			smaller = b;
			greater = a;
		}
		return multiply(smaller, greater);
	}
	
	private static int multiply(int smaller, int greater) {
		if (smaller == 0) return 0;
		if (smaller == 1) return greater;
		
		int half = multiply(smaller >> 1, greater);
		if (smaller % 2 == 0) {
			return half + half;
		} else {
			return half + half + greater;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(minProduct(9, 7));
	}
}
