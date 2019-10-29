package Q8_08_Permutations_With_Dups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			Set<Character> seenChars = new HashSet<>();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (!seenChars.contains(c)) {
					seenChars.add(c);
					getPerms(s.substring(0, i) + s.substring(i + 1), perms, prefix + c);
				}
			}
		}
	}
	
	private static Map<Character, Integer> getCharactersFrequencies(String s) {
		Map<Character, Integer> frequencies = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
		}
		return frequencies;
	}
	
	public static List<String> permutations(String s) {
		Map<Character, Integer> frequencies = getCharactersFrequencies(s);
		List<String> permutations = new ArrayList<>();
		permute(s.length(), "", frequencies, permutations);
		return permutations;
	}
	
	private static void permute(int remaining, String prefix, Map<Character, Integer> frequencies, List<String> result) {
		if (remaining == 0) {
			result.add(prefix);
		} else {
			for (Character c : frequencies.keySet()) {
				int count = frequencies.get(c);
				if (count > 0) {
					frequencies.put(c, count - 1);
					permute(remaining - 1, prefix + c, frequencies, result);
					frequencies.put(c, count);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> perms = permutations("aabb");
		perms.forEach(perm -> System.out.println(perm));
		System.out.println(perms.size());
	}

}