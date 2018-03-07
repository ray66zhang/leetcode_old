package treeset;

import java.util.HashSet;
import java.util.Set;

/**
 * Easy
 * 
 * Given an array of integers and an integer k, find out whether there are two
 * distinct indices i and j in the array such that nums[i] = nums[j] and the
 * absolute difference between i and j is at most k.
 *
 * Attempted: 1
 *
 * Percentile: 27.68%
 */
public class ContainsDuplicateII {

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		int len = nums.length;
		if (len < 2 || k < 1) {
			return false;
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			if (i > k) {
				set.remove(nums[i - k - 1]);
			}
			if (set.contains(num)) {
				return true;
			} else {
				set.add(num);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ContainsDuplicateII obj = new ContainsDuplicateII();
		int[] nums = { 1, 2, 3, 3 };
		int k = 1;
		System.out.println(obj.containsNearbyDuplicate(nums, k));

	}

}
