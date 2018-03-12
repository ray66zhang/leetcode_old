package reservoir_sampling;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Medium
 * 
 * Given an array of integers with possible duplicates, randomly output the
 * index of a given target number. You can assume that the given target number
 * must exist in the array.
 * 
 * Note: The array size can be very large. Solution that uses too much extra
 * space will not pass the judge.
 * 
 * Example:
 * 
 * int[] nums = new int[] {1,2,3,3,3}; Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should
 * have equal probability of returning. solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 *
 * Attempted: 2
 *
 * Percentile: 27.82%
 *
 * Initialized once, and call pick() multiple times
 *
 */
public class RandomPickIndex {

	Random rand = new Random();
	int[] nums;

	public RandomPickIndex(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		int freq = 0;
		int res = -1;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (target == num) {
				freq++;
				int p = rand.nextInt(freq);
				if (p == 0) {
					res = i;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 3, 3 };
		int target = 3;
		RandomPickIndex obj = new RandomPickIndex(nums);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 1000000; i++) {
			int res = obj.pick(target);
			if (map.containsKey(res)) {
				map.put(res, map.get(res) + 1);
			} else {
				map.put(res, 1);
			}
		}
		System.out.println(map);
	}

}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */
