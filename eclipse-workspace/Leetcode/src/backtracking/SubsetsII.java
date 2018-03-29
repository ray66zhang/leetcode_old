package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Medium
 * 
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,2], a solution is:
 * 
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 *
 * Attempted: 2
 * 
 * Percentile: 10.33%
 * 
 * Sort the array first
 */
public class SubsetsII {

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return new ArrayList<>();
		}
		Set<List<Integer>> res = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		dfs(res, list, nums, 0);
		return new ArrayList<>(res);
	}

	public void dfs(Set<List<Integer>> res, List<Integer> list, int[] nums, int index) {
		if (index > nums.length) {
			return;
		}
		res.add(new ArrayList<>(list));
		for (int i = index; i < nums.length; i++) {
			list.add(nums[i]);
			dfs(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		SubsetsII obj = new SubsetsII();
		int[] nums = { 1, 2, 2 };
		System.out.println(obj.subsetsWithDup(nums));
	}

}
