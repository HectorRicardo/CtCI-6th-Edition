package Q1_05_One_Away;

public class Hector {
	
	static boolean oneEditAway(String s1, String s2) {
		
		boolean editted = false;
		
		int i = 0; // for s1
		int j = 0; // for s2
		while (i < s1.length() && j < s2.length()) {
			if (s1.charAt(i) == s2.charAt(j)) {
				i++;
				j++;
			} else if (s1.charAt(i) != s2.charAt(j)) {
				if (editted) return false;
				if (s1.length() > s2.length()) {
					i++;
				} else if (s1.length() < s2.length()) {
					j++;
				} else {
					i++;
					j++;
				}
				editted = true;
			}
		}
		return true;	
	}
	
static boolean oneEditAway2(String s1, String s2) {
		
		// Setup
		String shorter, longer;
		if (s1.length() < s2.length()) {
			shorter = s1;
			longer = s2;
		} else {
			shorter = s2;
			longer = s1;
		}
		
		if (longer.length() > shorter.length() + 1) return false;
		
		boolean editted = false;
		
		int i = 0; // iterator for shorter string
		for (int j = 0; j < longer.length(); j++) {
			if (shorter.charAt(i) != longer.charAt(j)) {
				if (editted) return false;
				if (shorter.length() == longer.length()) {
					i++;
				}
				editted = true;
			} else {
				i++;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(oneEditAway("pale", "palesssss"));
	}

}