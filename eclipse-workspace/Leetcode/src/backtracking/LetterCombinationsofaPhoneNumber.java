package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Medium
 * 
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd",
 * "ce", "cf"].
 *
 * Attempted: 1
 *
 * Percentile: 67.99%
 */
public class LetterCombinationsofaPhoneNumber {
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return res;
		}
		for (int i = 0; i < digits.length(); i++) {
			char c = digits.charAt(i);
			if (c < '2' || c > '9') {
				return res;
			}
		}
		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		dfs(res, new StringBuilder(), digits, 0, map);
		return res;
	}

	public void dfs(List<String> res, StringBuilder sb, String digits, int index, Map<Character, String> map) {
		int len = digits.length();
		if (index == len) {
			res.add(sb.toString());
			return;
		}
		char c = digits.charAt(index);
		String chars = map.get(c);
		for (int i = 0; i < chars.length(); i++) {
			sb.append(chars.charAt(i));
			dfs(res, sb, digits, index + 1, map);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		LetterCombinationsofaPhoneNumber obj = new LetterCombinationsofaPhoneNumber();
		System.out.println(obj.letterCombinations("609"));
	}

}
