package Q8_11_Coins;

import java.util.HashMap;
import java.util.Map;

public class Hector {
	
	
	public static int makeChange(int amount, int[] denoms) {
		Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
		return makeChange(amount, denoms, 0, memo);
	}
	
	public static int makeChange(int amount, int[] denoms, int startingIdx, Map<Integer, Map<Integer, Integer>> memo) {
		if (amount == 0) return 1;
		if (startingIdx == denoms.length) return 0;
		
		// Checking memo
		Map<Integer, Integer> memoForCurrentAmount = memo.get(amount);
		if (memoForCurrentAmount == null) {
			memoForCurrentAmount = new HashMap<Integer, Integer>();
			memo.put(amount, memoForCurrentAmount);
		}
		
		Integer totalWays = memoForCurrentAmount.get(startingIdx);
		if (totalWays != null) {
			return totalWays;
		} else {
			totalWays = 0;
		}
		
		int moneyUntilNow = 0;
		while (moneyUntilNow <= amount) {
			totalWays += makeChange(amount - moneyUntilNow, denoms, startingIdx + 1, memo);
			moneyUntilNow += denoms[startingIdx];
		}
		
		memoForCurrentAmount.put(startingIdx, totalWays);
		return totalWays;
	}
	
	
	public static void main(String[] args) {
		System.out.println(makeChange(5, new int[]{25, 10, 5, 1}));
	}
}
