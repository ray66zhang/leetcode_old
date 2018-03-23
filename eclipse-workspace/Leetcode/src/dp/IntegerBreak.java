package dp;

import java.util.Arrays;

/**
 * Medium
 * 
 * Given a positive integer n, break it into the sum of at least two positive
 * integers and maximize the product of those integers. Return the maximum
 * product you can get.
 * 
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 =
 * 3 + 3 + 4).
 * 
 * Note: You may assume that n is not less than 2 and not larger than 58.
 *
 * Attempted: 1
 *
 * Percentile: 49.47%
 *
 * dp[i] = max(max(j, dp[j]) * max(i-j, dp[i-j]))
 */
public class IntegerBreak {
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			int max = 0;
			for (int j = 1; j < i; j++) {
				int prod = Math.max(dp[j], j) * Math.max(dp[i - j], i - j);
				if (prod > max) {
					max = prod;
				}
			}
			dp[i] = max;
		}
		System.out.println(Arrays.toString(dp));
		return dp[n];
	}

	public static void main(String[] args) {
		IntegerBreak obj = new IntegerBreak();
		System.out.println(obj.integerBreak(2));
	}
}
