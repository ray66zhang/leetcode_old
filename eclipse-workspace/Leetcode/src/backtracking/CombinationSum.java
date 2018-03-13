package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Medium
 * 
 * Given a set of candidate numbers (C) (without duplicates) and a target number
 * (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note: All numbers (including target) will be positive integers. The solution
 * set must not contain duplicate combinations. For example, given candidate set
 * [2, 3, 6, 7] and target 7, A solution set is: [ [7], [2, 2, 3] ]
 *
 * Attempted: 4
 *
 * Percentile: 4.45%
 *
 * Figure out correct looping criteria inside the dfs method. Within FOR loop,
 * the recursion should always be based on i instead of index.
 */
public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Set<List<Integer>> res = new HashSet<>();
		if (candidates.length == 0) {
			return new ArrayList<>();
		}
		List<Integer> list = new ArrayList<>();
		Arrays.sort(candidates);
		dfs(res, list, candidates, target, 0, 0);
		return new ArrayList<>(res);
	}

	public void dfs(Set<List<Integer>> res, List<Integer> list, int[] candidates, int target, int index, int sum) {
		if (sum > target) {
			return;
		} else if (sum == target) {
			List<Integer> candidate = new ArrayList<>(list);
			Collections.sort(candidate);
			res.add(candidate);
			return;
		}
		int len = candidates.length;
		for (int i = index; i < len; i++) {
			int num = candidates[i];
			if (sum + num <= target && i <= len) {
				list.add(num);
				dfs(res, list, candidates, target, i, sum + num);
				list.remove(list.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		CombinationSum obj = new CombinationSum();
		int[] candidates = { 2, 3, 6, 7 };
		int target = 7;
		System.out.println(obj.combinationSum(candidates, target));
	}

}
