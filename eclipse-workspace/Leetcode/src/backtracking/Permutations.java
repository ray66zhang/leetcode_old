package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Medium
 * 
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * For example, [1,2,3] have the following permutations: [ [1,2,3], [1,3,2],
 * [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 * 
 * Attempted: 2
 * 
 * Figure out the correct state of returning DFS
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums.length == 0) {
			return res;
		}
		dfs(res, nums, 0);
		return res;
	}

	public void dfs(List<List<Integer>> res, int[] nums, int index) {
		if (index >= nums.length) {
			List<Integer> list = new ArrayList<>();
			for (int num : nums) {
				list.add(num);
			}
			res.add(list);
			return;
		}
		for (int i = index; i < nums.length; i++) {
			swap(nums, index, i);
			dfs(res, nums, index + 1);
			swap(nums, index, i);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		Permutations obj = new Permutations();
		int[] nums = { 1, 2, 3};
		System.out.println(obj.permute(nums));

	}

}
