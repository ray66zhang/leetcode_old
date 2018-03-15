package topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SRM630Div2P2 {

	public double findExp(int n, int x) {
		return 0;
	}

	public Map<Integer, Integer> count(int[] nums, int x) {
		Map<Integer, Integer> map = new HashMap<>();
		Set<List<Integer>> res = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		dfs(res, list, nums, x, 0);
		for (List<Integer> l : res) {
			int num = l.get(0);
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		return map;
	}

	public void dfs(Set<List<Integer>> res, List<Integer> list, int[] nums, int x, int index) {
		if (list.size() == x) {
			res.add(new ArrayList<>(list));
			return;
		}
		if (index == nums.length) {
			return;
		}
		for (int i = index; i < nums.length; i++) {
			list.add(nums[i]);
			dfs(res, list, nums, x, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		SRM630Div2P2 obj = new SRM630Div2P2();
		int[] nums = { 1, 2, 3, 4,5 };
		int x = 3;
		System.out.println(obj.count(nums, x));
	}

}
