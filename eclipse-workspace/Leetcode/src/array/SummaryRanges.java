package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Medium
 * 
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 * 
 * Example 1: Input: [0,1,2,4,5,7] Output: ["0->2","4->5","7"] Example 2: Input:
 * [0,2,3,4,6,8,9] Output: ["0","2->4","6","8->9"]
 *
 * Attempted: 1
 *
 * Percentile: 55.91%
 */
public class SummaryRanges {

	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int start = num;
			while (i + 1 < nums.length && nums[i + 1] == num + 1) {
				i++;
				num++;
			}
			int end = nums[i];
			if (start < end) {
				res.add(start + "->" + end);
			} else {
				res.add(Integer.toString(start));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		SummaryRanges obj = new SummaryRanges();
		int[] nums = { 0, 2, 3, 4, 6, 8, 9 };
		System.out.println(obj.summaryRanges(nums));
	}

}
