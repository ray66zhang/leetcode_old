package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Medium
 * 
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,3], a solution is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 *
 * Attempted: 1
 *
 * Percentile: 32.01%
 */
public class Subsets {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		int len = nums.length;
		if (len == 0) {
			return res;
		}
		List<Integer> list = new ArrayList<>();
		dfs(res, list, nums, 0);
		return res;
	}

	public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
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
		Subsets obj = new Subsets();
		int[] nums = { 1, 2, 3 };
		System.out.println(obj.subsets(nums));

	}

}
