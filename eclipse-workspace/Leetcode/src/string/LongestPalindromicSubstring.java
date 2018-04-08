package string;

/**
 * Medium
 * 
 * Given a string s, find the longest palindromic substring in s. You may assume
 * that the maximum length of s is 1000.
 * 
 * Example:
 * 
 * Input: "babad"
 * 
 * Output: "bab"
 * 
 * Note: "aba" is also a valid answer.
 * 
 * 
 * Example:
 * 
 * Input: "cbbd"
 * 
 * Output: "bb"
 *
 * Attempted: 1
 *
 * Percentile: 55.52%
 */
public class LongestPalindromicSubstring {

	public String longestPalindrome(String s) {
		int len = s.length();
		if (len <= 1) {
			return s;
		}
		int maxLen = 0;
		String maxStr = "";
		for (int i = 0; i < len; i++) {
			String str1 = getLength(s, i, true);
			String str2 = getLength(s, i, false);
			if (str1.length() > maxLen) {
				maxLen = str1.length();
				maxStr = str1;
			}
			if (str2.length() > maxLen) {
				maxLen = str2.length();
				maxStr = str2;
			}
		}
		return maxStr;
	}

	public String getLength(String s, int index, boolean flag) {
		int len = s.length();
		if (flag) {
			int start = index;
			int end = index;
			while (start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			return s.substring(start + 1, end);
		} else {
			int start = index;
			int end = index + 1;
			while (start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			if (start + 1 == end) {
				return "";
			} else {
				return s.substring(start + 1, end);
			}
		}
	}

	public static void main(String[] args) {
		LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
		String s = "abbad";
		System.out.println(obj.longestPalindrome(s));
	}

}
