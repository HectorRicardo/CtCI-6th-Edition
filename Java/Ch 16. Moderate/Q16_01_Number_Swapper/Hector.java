package Q16_01_Number_Swapper;

public class Hector {
	
	public static class IntWrapper {
		int value;
		public IntWrapper(int value) {
			this.value = value;
		}
	}
	
	public static void swap2(IntWrapper a, IntWrapper b) {
		a.value = a.value - b.value;
		b.value = a.value + b.value;
		a.value = -a.value + b.value;
	}
	
	public static void swap(IntWrapper a, IntWrapper b) {
		a.value = a.value + b.value;
		b.value = a.value - b.value;
		a.value = a.value - b.value;
	}
	
	public static void main(String[] args) {
		IntWrapper a = new IntWrapper(-5);
		IntWrapper b = new IntWrapper(-8);
		swap(a, b);
		System.out.println("A: " + a.value + "    B: " + b.value);
	}

}
