package Q10_02_Group_Anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	private static String sortStringCharacters(String s) {
		char[] characters = s.toCharArray();
		Arrays.sort(characters);
		return new String(characters);
	}
	
	private static void sort(String[] array) {
		Map<String, List<String>> anagramsToWords = new HashMap<>();
		for (String word : array) {
			String anagram = sortStringCharacters(word);
			if (!anagramsToWords.containsKey(anagram)) {
				anagramsToWords.put(anagram, new ArrayList<>());
			}
			anagramsToWords.get(anagram).add(word);
		}
		
		int i = 0;
		for (Map.Entry<String, List<String>> entry : anagramsToWords.entrySet()) {
			for (String word : entry.getValue()) {
				array[i] = word;
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		sort(array);
		System.out.println(AssortedMethods.stringArrayToString(array));
	}
}
