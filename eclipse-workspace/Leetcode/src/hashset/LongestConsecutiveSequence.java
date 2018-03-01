package hashset;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 *
 * Attempted: 1
 *
 * Percentile: 28.85%
 *
 */
public class LongestConsecutiveSequence {

	public int longestConsecutive(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return len;
		}
		int max = 0;
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			int start = num - 1;
			int end = num + 1;
			while (set.contains(start)) {
				start--;
			}
			start++;
			while (set.contains(end)) {
				end++;
			}
			end--;
			int length = end - start + 1;
			if (length > max) {
				max = length;
			}
			for (int j = start; j <= end; j++) {
				set.remove(j);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		System.out.println(obj.longestConsecutive(nums));

	}

}
