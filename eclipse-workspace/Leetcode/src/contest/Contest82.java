package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Contest82 {

	public String toGoatLatin(String S) {
		String[] strs = S.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			String c = str.substring(0, 1).toLowerCase();
			if (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u")) {
				sb.append(str);
				sb.append("ma");
			} else {
				sb.append(str.substring(1));
				sb.append(str.substring(0, 1));
				sb.append("ma");
			}
			for (int j = 0; j <= i; j++) {
				sb.append("a");
			}
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	public int largestIsland(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int max = dfs(getGrid(grid, 0, 0, false));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int cell = grid[i][j];
				if (cell == 0) {
					int[][] newGrid = getGrid(grid, i, j, true);
					int area = dfs(newGrid);
					if (area > max) {
						max = area;
					}
				}
			}
		}
		return max;
	}

	public int dfs(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int max = 0;
		int cur = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int cell = grid[i][j];
				if (cell == 1) {
					cur = dfsHelper(grid, i, j);
					if (cur > max) {
						max = cur;
					}
				}
			}
		}
		return max;
	}

	public int dfsHelper(int[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
			return 0;
		}
		if (grid[r][c] == 1) {
			grid[r][c] = 0;
			return dfsHelper(grid, r + 1, c) + dfsHelper(grid, r - 1, c) + dfsHelper(grid, r, c + 1)
					+ dfsHelper(grid, r, c - 1) + 1;
		} else {
			return 0;
		}
	}

	public int[][] getGrid(int[][] grid, int r, int c, boolean replace) {
		int[][] newGrid = new int[grid.length][grid[0].length];
		for (int i = 0; i < newGrid.length; i++) {
			for (int j = 0; j < newGrid[0].length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		if (replace) {
			newGrid[r][c] = 1;
		}
		return newGrid;
	}

	class Job {
		int difficulty;
		int profit;

		public Job(int difficulty, int profit) {
			this.difficulty = difficulty;
			this.profit = profit;
		}
	}

	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < difficulty.length; i++) {
			int d = difficulty[i];
			int p = profit[i];
			if (map.containsKey(d)) {
				map.put(d, Math.max(p, map.get(d)));
			} else {
				map.put(d, p);
			}
		}
		List<Job> jobs = new ArrayList<>();
		for (int d : map.keySet()) {
			jobs.add(new Job(d, map.get(d)));
		}
		int max = 0;
		for (int work : worker) {
			if (work > max) {
				max = work;
			}
		}
		int curIndex = 0;
		int curProfit = 0;
		int[] profits = new int[max + 1];
		for (int i = 1; i <= max; i++) {
			if (curIndex < jobs.size() && i == jobs.get(curIndex).difficulty) {
				curProfit = Math.max(jobs.get(curIndex).profit, curProfit);
				curIndex++;
			}
			profits[i] = curProfit;
		}
		int total = 0;
		for (int work : worker) {
			total += profits[work];
		}
		return total;
	}

	public int numFriendRequests(int[] ages) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int age : ages) {
			if (map.containsKey(age)) {
				map.put(age, map.get(age) + 1);
			} else {
				map.put(age, 1);
			}
		}
		int total = 0;
		for (int a : ages) {
			for (int b = 1; b <= 120; b++) {
				if (!((b <= 0.5 * a + 7) || (b > a) || (b > 100 && a < 100))) {
					if (map.containsKey(b)) {
						total += map.get(b);
						if (a == b) {
							total -= 1;
						}
					}
				}
			}
		}
		return total;
	}

	public static void main(String[] args) {
		Contest82 obj = new Contest82();
		String S = "I speak Goat Latin";
		System.out.println(obj.toGoatLatin(S));
	}

}
