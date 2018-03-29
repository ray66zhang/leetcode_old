package Contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contest77 {

	public int[] numberOfLines(int[] widths, String S) {
		int[] res = new int[2];
		if (S == null || S.length() == 0) {
			return res;
		}
		int len = S.length();
		int lines = 1;
		int curLine = 0;
		for (int i = 0; i < len; i++) {
			char c = S.charAt(i);
			int width = widths[c - 'a'];
			if (curLine + width <= 100) {
				curLine += width;
			} else {
				lines++;
				curLine = width;
			}
		}
		res[0] = lines;
		res[1] = curLine;
		return res;
	}

	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int x = grid.length;
		int y = grid[0].length;
		if (x == 0 || y == 0) {
			return 0;
		}
		int[][] update = new int[x][y];
		int[] sy = new int[y];
		int[] sx = new int[x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				sx[i] = Math.max(grid[i][j], sx[i]);
			}
		}
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				sy[j] = Math.max(grid[i][j], sy[j]);
			}
		}
		System.out.println(Arrays.toString(sx));
		System.out.println(Arrays.toString(sy));
		int sum = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				update[i][j] = Math.min(sy[j], sx[i]);
				sum += update[i][j] - grid[i][j];
			}
		}
		for (int[] arr : update) {
			System.out.println(Arrays.toString(arr));
		}
		return sum;
	}

	public boolean found = false;

	public boolean splitArraySameAverage(int[] A) {
		int len = A.length;
		if (len <= 1) {
			return false;
		}
		Arrays.sort(A);
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += A[i];
		}
		double avg = 1.0 * sum / A.length;
		for (int i = 1; i <= A.length / 2; i++) {
			double subsum = avg * i;
			if (subsum == (int) subsum) {
				System.out.println(subsum + " " + i);
				List<Integer> list = new ArrayList<>();
				dfs(list, i, (int) subsum, A, 0);
			}
		}
		return found;
	}

	public void dfs(List<Integer> list, int count, int target, int[] A, int index) {
		if (list.size() == count && target == 0) {
			System.out.println(list);
			found = true;
			return;
		}
		if (found || index > A.length || list.size() > count) {
			return;
		}
		for (int i = index; i < A.length; i++) {
			list.add(A[i]);
			dfs(list, count, target - A[i], A, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public int uniqueMorseRepresentations(String[] words) {
		String[] codes = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
				"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
		Set<String> set = new HashSet<>();
		for (String word : words) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				sb.append((codes[c - 'a']));
			}
			set.add(sb.toString());
		}
		return set.size();
	}

	public static void main(String[] args) {

	}

}
