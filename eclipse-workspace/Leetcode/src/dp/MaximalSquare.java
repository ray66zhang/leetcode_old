package dp;

/**
 * Medium
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0 Return 4.
 *
 * Attempted: 3
 * 
 * Percentile: 63.55%
 * 
 * Build dp[][] as the maximum square from dp[i][j] as the bottom right corner.
 * dp[i][j] will be the minimum of dp[i-1][j], dp[i][j-1], and dp[i-1][j-1],
 * plus 1.
 * 
 */
public class MaximalSquare {

	public int maximalSquareSlow(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] sum = new int[row + 1][col + 1];
		int max = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (matrix[i - 1][j - 1] - '0');
				if (matrix[i - 1][j - 1] == '1') {
					int d = 1;
					while (i - d >= 0 && j - d >= 0) {
						int s = sum[i][j] - sum[i - d][j] - sum[i][j - d] + sum[i - d][j - d];
						if (s == d * d) {
							if (s > max) {
								max = s;
							}
						} else {
							break;
						}
						d++;
					}
				}
			}
		}
		return max;
	}

	public int maximalSquareFast(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int max = 0;
		int[][] dp = new int[row + 1][col + 1];
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
					if (dp[i][j] > max) {
						max = dp[i][j];
					}
				}
			}
		}
		return max * max;
	}

	public static void main(String[] args) {
		MaximalSquare obj = new MaximalSquare();
		char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		System.out.println(obj.maximalSquareFast(matrix));
	}

}
