package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Medium
 * 
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [ [1,1,2],
 * [1,2,1], [2,1,1] ]
 * 
 * Attempted: 1
 * 
 * Percentile: 4.71%
 *
 */
public class PermutationsII {

	public List<List<Integer>> permuteUnique(int[] nums) {
		Set<List<Integer>> res = new HashSet<>();
		if (nums.length == 0) {
			return new ArrayList<>();
		}
		dfs(res, nums, 0);
		return new ArrayList<>(res);
	}

	public void dfs(Set<List<Integer>> res, int[] nums, int index) {
		if (index >= nums.length) {
			List<Integer> list = new ArrayList<>();
			for (int num : nums) {
				list.add(num);
			}
			res.add(list);
			return;
		}
		for (int i = index; i < nums.length; i++) {
			swap(nums, i, index);
			dfs(res, nums, index + 1);
			swap(nums, i, index);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		PermutationsII obj = new PermutationsII();
		int[] nums = { 1, 2, 1 };
		System.out.println(obj.permuteUnique(nums));

	}

}
