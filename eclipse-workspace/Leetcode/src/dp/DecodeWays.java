package dp;

/**
 * Medium
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 *
 * Attempted: 2
 *
 * Percentile: 70.87%
 * 
 * Pay attention to the initial dp[0] as 1 instead of 0.
 */
public class DecodeWays {

	public int numDecodings(String s) {
		int len = s.length();
		if (len == 0 || s.charAt(0) == '0') {
			return 0;
		}
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return 0;
			}
		}
		int[] dp = new int[len + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= len; i++) {
			if (ways(s, i, 1) > 0 && ways(s, i, 2) > 0) {
				dp[i] = dp[i - 1] + dp[i - 2];
			} else if (ways(s, i, 1) == 0 && ways(s, i, 2) > 0) {
				dp[i] = dp[i - 2];
			} else if (ways(s, i, 1) > 0 && ways(s, i, 2) == 0) {
				dp[i] = dp[i - 1];
			} else {
				return 0;
			}
		}
		return dp[len];
	}

	public int ways(String s, int index, int offset) {
		if (offset == 1) {
			char c = s.charAt(index - 1);
			if (c == '0') {
				return 0;
			} else {
				return 1;
			}
		} else if (offset == 2) {
			char a = s.charAt(index - 2);
			char b = s.charAt(index - 1);
			if (a == '1') {
				return 1;
			} else if (a == '2' && b >= '0' && b <= '6') {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		DecodeWays obj = new DecodeWays();
		String s = "100";
		System.out.println(obj.numDecodings(s));
	}

}
