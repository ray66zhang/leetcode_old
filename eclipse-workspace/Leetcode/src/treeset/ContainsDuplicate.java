package treeset;

import java.util.HashSet;
import java.util.Set;

/**
 * Easy
 * 
 * Given an array of integers, find if the array contains any duplicates. Your
 * function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 *
 * Attempted : 1
 * 
 * Percentile: 16.21%
 */
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return false;
		}
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	public static void main(String[] args) {
		ContainsDuplicate obj = new ContainsDuplicate();
		int[] nums = { 1, 2, 1 };
		System.out.print(obj.containsDuplicate(nums));

	}

}
