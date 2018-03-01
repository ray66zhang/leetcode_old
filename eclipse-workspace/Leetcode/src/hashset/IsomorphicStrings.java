package hashset;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character but a character may map to itself.
 * 
 * For example, Given "egg", "add", return true.
 * 
 * Given "foo", "bar", return false.
 * 
 * Given "paper", "title", return true.
 * 
 * Note: You may assume both s and t have the same length.
 *
 * Attempted: 2
 *
 * Percentile: 60.11%
 */
public class IsomorphicStrings {

	public boolean isIsomorphic(String s, String t) {
		if (s == null || t == null) {
			return false;
		}
		if (s.length() <= 1) {
			return true;
		}
		Map<Character, Character> map = new HashMap<>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char cs = s.charAt(i);
			char ct = t.charAt(i);
			if (map.containsKey(cs)) {
				if (map.get(cs) != ct) {
					return false;
				}
			} else {
				if (map.containsValue(ct)) {
					return false;
				}
				map.put(cs, ct);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		IsomorphicStrings obj = new IsomorphicStrings();
		String s = "ab";
		String t = "aa";
		System.out.println(obj.isIsomorphic(s, t));

	}

}
