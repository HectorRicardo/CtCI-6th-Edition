package Q8_01_Triple_Step;

public class Hector {

	public static int countWays2(int steps) {
		if (steps < 0) return 0;
		if (steps == 0) return 1;
		return countWays(steps - 1) + countWays(steps - 2) + countWays(steps - 3);
	}
	
	public static int countWays(int steps) {
		if (steps < 0) throw new RuntimeException("Steps cannot be negative");
		if (steps == 0) return 1;
		
		int prev3 = 0;
		int prev2 = 0;
		int prev1 = 1;
		
		int c = 0;
		for (int i = 1; i <= steps; i++) {
			c = prev3 + prev2 + prev1;
			prev3 = prev2;
			prev2 = prev1;
			prev1 = c;
		}
		
		return c;
	}
}
