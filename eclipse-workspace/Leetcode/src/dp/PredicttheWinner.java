package dp;

import java.util.Arrays;

/**
 * Medium
 * 
 * Given an array of scores that are non-negative integers. Player 1 picks one
 * of the numbers from either end of the array followed by the player 2 and then
 * player 1 and so on. Each time a player picks a number, that number will not
 * be available for the next player. This continues until all the scores have
 * been chosen. The player with the maximum score wins.
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can
 * assume each player plays to maximize his score.
 * 
 * Example 1: Input: [1, 5, 2] Output: False Explanation: Initially, player 1
 * can choose between 1 and 2. If he chooses 2 (or 1), then player 2 can choose
 * from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1
 * (or 2). So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. Hence,
 * player 1 will never be the winner and you need to return False. Example 2:
 * Input: [1, 5, 233, 7] Output: True Explanation: Player 1 first chooses 1.
 * Then player 2 have to choose between 5 and 7. No matter which number player 2
 * choose, player 1 can choose 233. Finally, player 1 has more score (234) than
 * player 2 (12), so you need to return True representing player1 can win. Note:
 * 1 <= length of the array <= 20. Any scores in the given array are
 * non-negative integers and will not exceed 10,000,000. If the scores of both
 * players are equal, then player 1 is still the winner.
 *
 * Attempted: 1
 *
 * Percentile: 88.54%
 * 
 * DP problem. Define dp[i][j] as the max score a player can earn between i and
 * j. dp[i][j] will be the max between the current play chooses i or j. If he
 * chooses i, his score will be nums[i] + total_score[i+1][j] - dp[i+1][j].
 */
public class PredicttheWinner {

	public boolean PredictTheWinner(int[] nums) {
		int len = nums.length;
		if (len <= 1) {
			return true;
		}
		int[] sum = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = nums[i];
		}
		for (int l = 1; l < len; l++) {
			for (int i = 0; i < len - 1; i++) {
				int j = i + l;
				if (j < len) {
					dp[i][j] = Math.max(nums[i] + sum[j + 1] - sum[i + 1] - dp[i + 1][j],
							nums[j] + sum[j] - sum[i] - dp[i][j - 1]);
				}
			}
		}
		return dp[0][len - 1] * 2 >= sum[len] ? true : false;
	}

	public static void main(String[] args) {
		PredicttheWinner obj = new PredicttheWinner();
		int[] nums = { 1, 5, 233, 7 };
		System.out.println(obj.PredictTheWinner(nums));
	}

}
