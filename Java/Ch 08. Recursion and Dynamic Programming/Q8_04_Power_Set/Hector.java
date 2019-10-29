package Q8_04_Power_Set;

import java.util.ArrayList;
import java.util.List;

public class Hector {
	
	public static List<List<Integer>> getSubsets(List<Integer> set) {
		List<List<Integer>> subsets = new ArrayList<>();
		getSubsets(set, subsets);
		return subsets;
	}
	
	private static void getSubsets(List<Integer> set, List<List<Integer>> subsets) {
		if (set.isEmpty()) {
			subsets.add(new ArrayList<Integer>());
		} else {
			int last = set.remove(set.size() - 1);
			getSubsets(set, subsets);
			int sizeBeforeIncrease = subsets.size();
			for (int i = 0; i < sizeBeforeIncrease; i++) {
				List<Integer> ithSubset = subsets.get(i);
				List<Integer> newSubset = new ArrayList<>(ithSubset);
				newSubset.add(last);
				subsets.add(newSubset);
			}
		}
	}
	
	public static List<List<Integer>> subsetsIteratively(List<Integer> set) {
		List<List<Integer>> subsets = new ArrayList<>();
		int numOfSubsets = 1 << set.size();
		for (int i = 0; i < numOfSubsets; i++) {
			subsets.add(getKthSubset(set, i));
		}
		return subsets;
	}
	
	private static List<Integer> getKthSubset(List<Integer> set, int k) {
		List<Integer> kthSubset = new ArrayList<>();
		int idx = 0;
		for (int i = k; i > 0; i >>= 1) {
			if ((i & 1) == 1) {
				kthSubset.add(set.get(idx));
			}
			idx++;
		}
		return kthSubset;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		
		List<List<Integer>> subsets2 = subsetsIteratively(list);
		System.out.println(subsets2.toString());		
	}
}
