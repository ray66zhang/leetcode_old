package topcoder;

/**
 * You are given two int[]s L and R, each of length n.
 * 
 * Find the number of strictly increasing sequences of integers A[0] < A[1] <
 * ... < A[n-1] such that L[i] ≤ A[i] ≤ R[i] for every i. Return this number
 * modulo 998244353.
 *
 * {1, 3, 1, 4} {6, 5, 4, 6} Returns: 4 There are 4 strictly increasing
 * sequences satisfying the conditions: {1, 3, 4, 5}, {1, 3, 4, 6}, {2, 3, 4, 5}
 * and {2, 3, 4, 6}.
 * 
 */
public class SRM728Div2P2 {

	public int count(int[] L, int[] R) {
		int n = L.length;
		long[][] dp = new long[10001][n];
		long[][] dpsum = new long[10001][n];
		int sum = 0;
		for (int i = 0; i < 10001; i++) {
			if (L[0] <= i && i <= R[0]) {
				dp[i][0] = 1;
				sum++;
			}
			dpsum[i][0] = sum;
		}
		for (int j = 1; j < n; j++) {
			int l = L[j];
			int r = R[j];
			for (int i = 1; i < 10001; i++) {
				if (l <= i && i <= r) {
					dp[i][j] = dpsum[i - 1][j - 1];
				}
				dpsum[i][j] = dpsum[i - 1][j] + dp[i][j];
			}
		}
		return (int) (dpsum[10000][n - 1] % 998244353);
	}

	public static void main(String[] args) {
		SRM728Div2P2 obj = new SRM728Div2P2();
		int[] L = { 1, 1, 1 };
		int[] R = { 10000, 10000, 10000 };
		System.out.println(obj.count(L, R));
	}

}
