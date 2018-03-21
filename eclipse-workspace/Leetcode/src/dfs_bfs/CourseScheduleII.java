package dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Medium
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If
 * it is impossible to finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take
 * course 3 you should have finished both courses 1 and 2. Both courses 1 and 2
 * should be taken after you finished course 0. So one correct course order is
 * [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented. You may
 * assume that there are no duplicate edges in the input prerequisites.
 *
 * Attempted: 1
 * 
 * Percentile: 45.88%
 */
public class CourseScheduleII {

	public boolean cycle = false;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0) {
			return new int[0];
		}
		Map<Integer, List<Integer>> edges = new HashMap<>();
		for (int i = 0; i < prerequisites.length; i++) {
			int[] edge = prerequisites[i];
			if (edges.containsKey(edge[1])) {
				edges.get(edge[1]).add(edge[0]);
			} else {
				List<Integer> neighbor = new ArrayList<>();
				neighbor.add(edge[0]);
				edges.put(edge[1], neighbor);
			}
		}
		Set<Integer> visiting = new HashSet<>();
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < numCourses; i++) {
			dfs(edges, visiting, visited, stack, i);
		}
		if (cycle) {
			return new int[0];
		} else {
			int[] res = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				res[i] = stack.pop();
			}
			return res;
		}
	}

	public void dfs(Map<Integer, List<Integer>> edges, Set<Integer> visiting, Set<Integer> visited,
			Stack<Integer> stack, int node) {
		if (cycle || visited.contains(node)) {
			return;
		}
		if (visiting.contains(node)) {
			cycle = true;
			return;
		}
		visiting.add(node);
		if (edges.containsKey(node)) {
			List<Integer> neighbors = edges.get(node);
			for (int ne : neighbors) {
				dfs(edges, visiting, visited, stack, ne);
			}
		}
		visiting.remove(node);
		visited.add(node);
		stack.push(node);
	}

	public static void main(String[] args) {
		CourseScheduleII obj = new CourseScheduleII();
		int numCourses = 4;
		int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		int[] res = obj.findOrder(numCourses, prerequisites);
		System.out.println(Arrays.toString(res));
	}

}
