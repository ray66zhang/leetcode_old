package string;

/**
 * Hard
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * For example:
 * 
 * Given "aacecaaa", return "aaacecaaa".
 * 
 * Given "abcd", return "dcbabcd".
 *
 * Attempted: 2
 *
 * Percentile: 35.08%
 * 
 * KMP can achieve O(n).
 */
public class ShortestPalindrome {

	public String shortestPalindrome(String s) {
		int len = s.length();
		if (len <= 1) {
			return s;
		}
		int mid = Math.max(len / 2, len / 2 - 1);
		String res = "";
		for (int i = mid; i >= 0; i--) {
			String str = getShort(s, i, true);
			if (str.length() > 0) {
				res = str;
				break;
			}
		}
		for (int i = mid; i >= 0; i--) {
			String str = getShort(s, i, false);
			if (str.length() > 0) {
				if (str.length() < res.length()) {
					res = str;
				}
				break;
			}
		}
		return res;
	}

	public String getShort(String s, int index, boolean flag) {
		int len = s.length();
		if (flag) {
			int start = index;
			int end = index;
			while (start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			if (start < 0) {
				return reverse(s.substring(end)) + s;
			} else {
				return "";
			}
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
				if (start < 0) {
					return reverse(s.substring(end)) + s;
				} else {
					return "";
				}
			}
		}
	}

	public String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ShortestPalindrome obj = new ShortestPalindrome();
		String s = "abb";
		System.out.println(obj.shortestPalindrome(s));

	}

}
