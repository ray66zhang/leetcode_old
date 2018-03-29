package dp;

/**
 * Medium
 * 
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * Note: Each of the array element will not exceed 100. The array size will not
 * exceed 200. Example 1:
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11]. Example 2:
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Attempted: 1
 *
 * Percentile: 91.29%
 *
 */
public class PartitionEqualSubsetSum {

	public boolean canPartition(int[] nums) {
		int len = nums.length;
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (len <= 1 || sum % 2 == 1) {
			return false;
		}
		int target = sum / 2;
		boolean[] dp = new boolean[target + 1];
		dp[0] = true;
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			for (int j = target - num; j >= 0; j--) {
				if (dp[j]) {
					dp[j + num] = true;
				}
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
		int[] nums = { 1, 5, 11, 7 };
		System.out.println(obj.canPartition(nums));
	}

}
