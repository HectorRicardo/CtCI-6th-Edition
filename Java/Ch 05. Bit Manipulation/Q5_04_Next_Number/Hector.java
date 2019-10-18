package Q5_04_Next_Number;

public class Hector {
	
	static int getPrev(int n) {
		if (n <= 0) {
			//throw new RuntimeException("n should be positive");
			return -1;
		}
		int copyN = n;
		int ones = 0;
		int i = 1; // we will check if bit i + bit i-1 is "10"
		while (copyN > 0) {
			int firstTwoDigits = copyN % 4;
			if (firstTwoDigits == 2) { // equal to 10
				break;
			}
			ones += copyN % 2;
			copyN /= 2;
			i++;
		}
		
		if (copyN == 0) {
			return -1;
			//throw new RuntimeException("Cannot find smaller number");
		}
		
		n &= ~(1 << i); // set bit i   to 0
		n |= 1 << (i - 1); // set bit i-1 to 1
		
		n &= -1 << (i - 1); // clear the first i - 1 bits
		n |= ((1 << ones) - 1) << (i - 1 - ones);
		
		return n;
		
	}
	
	static int getNext(int n) {
		if (n <= 0) {
			//throw new RuntimeException("n should be positive");
			return -1;
		}
		int copyN = n;
		int i = 1;
		int ones = 0;
		while (copyN > 0) {
			int firstTwoDigits = copyN % 4;
			if (firstTwoDigits == 1) {
				break;
			}
			ones += copyN % 2;
			i++;
			copyN /= 2;
		}
		
		if (copyN == 0) {
			return -1;
			//throw new RuntimeException("Cannot find larger number");
		}
		
		n &= ~(1 << (i - 1)); // set bit i-1 to 0
		n |= 1 << i; // set bit i to 1
		
		n &= -1 << (i - 1);
		n |= (1 << ones) - 1;
		
		return n;
		
	}
	
	public static void main(String[] args) {
		System.out.println(getPrev(9));
		//System.out.println(getNext(4));
	}
}
