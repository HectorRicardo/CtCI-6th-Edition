package Q8_07_Permutations_Without_Dups;

import java.util.ArrayList;
import java.util.List;

public class Hector {
	
	public static List<String> getPerms(String s) {
		List<String> perms = new ArrayList<>();
		getPerms(s, perms, "");
		return perms;
	}
	
	private static void getPerms(String s, List<String> perms, String prefix) {
		if (s.length() == 0) {
			perms.add(prefix);
		} else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				getPerms(s.substring(0, i) + s.substring(i + 1), perms, prefix + c);
			}
		}
	}
	
	public static List<String> getPerms2(String s) {
		List<String> result = new ArrayList<String>();
		if (s.length() == 0) {
			result.add("");
		} else {
			List<String> perms = getPerms(s.substring(1));
			char firstChar = s.charAt(0);
			
			for (String perm : perms) {
				for (int i = 0; i <= perm.length(); i++) {
					result.add(perm.substring(0, i) + firstChar + perm.substring(i));
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<String> perms = getPerms("abcd");
		perms.forEach(perm -> System.out.println(perm));
		System.out.println(perms.size());
	}

}
