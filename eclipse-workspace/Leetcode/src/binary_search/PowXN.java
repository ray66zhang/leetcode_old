package binary_search;

/**
 * Medium
 * 
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * 
 * Example 1:
 * 
 * Input: 2.00000, 10 Output: 1024.00000 Example 2:
 * 
 * Input: 2.10000, 3 Output: 9.26100 Example 3:
 * 
 * Input: 2.00000, -2 Output: 0.25000 Explanation: 2-2 = 1/22 = 1/4 = 0.25 Note:
 * 
 * -100.0 < x < 100.0 n is a 32-bit signed integer, within the range [−231, 231
 * − 1]
 *
 * Attempted: 2
 *
 * Percentile: 85.40%
 * 
 * DO NOT directly return dfs() recursive function.
 */
public class PowXN {

	public double myPow(double x, int n) {
		if (n == 0) {
			return 1.0;
		} else if (n < 0) {
			return 1.0 / dfs(x, -n);
		} else {
			return dfs(x, n);
		}
	}

	public double dfs(double x, int n) {
		if (n == 0) {
			return 1.0;
		} else if (n == 1) {
			return x;
		}
		double val = dfs(x, n / 2);
		if (n % 2 == 0) {
			return val * val;
		} else {
			return val * val * x;
		}
	}

	public static void main(String[] args) {
		PowXN obj = new PowXN();
		double x = 0.00001;
		int n = 2147483647;
		System.out.println(obj.myPow(x, n));

	}

}
