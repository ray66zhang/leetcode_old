package treeset;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Medium
 * 
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the absolute difference between nums[i] and
 * nums[j] is at most t and the absolute difference between i and j is at most
 * k.
 *
 * Attempted: 4
 *
 * Percentile: 20.41%
 *
 * TreeSet has a method subset() that returns the subset of the original set
 * with boundaries.
 */
public class ContainsDuplicateIII {

	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		int len = nums.length;
		if (len < 2 || k < 1 || t < 0) {
			return false;
		}
		SortedSet<Long> set = new TreeSet<>();
		for (int i = 0; i < len; i++) {
			long num = nums[i];
			if (i > k) {
				set.remove((long) nums[i - k - 1]);
			}
			if (set.isEmpty()) {
				set.add(num);
			} else {
				long minBound = num - t;
				long maxBound = num + t + 1;
				Set<Long> subset = set.subSet(minBound, maxBound);
				if (subset.size() > 0) {
					return true;
				} else {
					set.add(num);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ContainsDuplicateIII obj = new ContainsDuplicateIII();
		int[] nums = { -10, -8 };
		int k = 100;
		int t = 1;
		System.out.println(obj.containsNearbyAlmostDuplicate(nums, k, t));

	}

}
