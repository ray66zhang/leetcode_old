package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Hard
 * 
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are given
 * the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings
 * collectively (Figure B).
 * 
 * Buildings Skyline Contour The geometric information of each building is
 * represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x
 * coordinates of the left and right edge of the ith building, respectively, and
 * Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤
 * INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles
 * grounded on an absolutely flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [
 * [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in
 * between any two adjacent buildings should be considered part of the skyline
 * contour.
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3
 * 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 * Notes:
 * 
 * The number of buildings in any input list is guaranteed to be in the range
 * [0, 10000]. The input list is already sorted in ascending order by the left x
 * position Li. The output list must be sorted by the x position. There must be
 * no consecutive horizontal lines of equal height in the output skyline. For
 * instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the
 * three lines of height 5 should be merged into one in the final output as
 * such: [...[2 3], [4 5], [12 7], ...]
 *
 * Attempted: 1
 *
 * Percentile for O(n^2) solution: 34.26%
 * 
 * Percentile for O(nlogn) solution: 79.6%
 *
 * Solution 1: O(n^2) two for loop, right edge is exclusive.
 * 
 * Solution 2: O(nlogn) 1 for loop and 1 heap, maintain a heap to keep track of
 * the current max height, the only issue is how to track which one is polled,
 * we don't need to remove it each time. Instead, we keep track it using a hash
 * table and update the heap every time we peek the heap.
 */

class Building {
	int left;
	int right;
	int height;

	public Building(int left, int right, int height) {
		this.left = left;
		this.right = right;
		this.height = height;
	}

}

public class TheSkylineProblem {

	public List<int[]> getSkylineSlow(int[][] buildings) {
		List<int[]> res = new ArrayList<>();
		if (buildings.length == 0) {
			return res;
		}
		Set<Integer> pointSet = new HashSet<>();
		for (int[] building : buildings) {
			pointSet.add(building[0]);
			pointSet.add(building[1]);
		}
		List<Integer> pointList = new ArrayList<>(pointSet);
		Collections.sort(pointList);
		int curHeight = 0;
		for (int point : pointList) {
			int height = 0;
			for (int[] building : buildings) {
				if (building[0] <= point && point < building[1]) {
					height = Math.max(height, building[2]);
				}
			}
			if (height != curHeight) {
				int[] corner = new int[2];
				corner[0] = point;
				corner[1] = height;
				res.add(corner);
				curHeight = height;
			}
		}
		return res;
	}

	public List<int[]> getSkylineFast(int[][] buildings) {
		List<int[]> res = new ArrayList<>();
		if (buildings.length == 0) {
			return res;
		}
		Map<Integer, List<Building>> mapLeft = new HashMap<>();
		Map<Integer, List<Building>> mapRight = new HashMap<>();
		Set<Integer> pointSet = new HashSet<>();
		for (int[] building : buildings) {
			int left = building[0];
			int right = building[1];
			pointSet.add(left);
			pointSet.add(right);
			Building b = new Building(building[0], building[1], building[2]);
			if (mapLeft.containsKey(left)) {
				mapLeft.get(left).add(b);
			} else {
				List<Building> list = new ArrayList<>();
				list.add(b);
				mapLeft.put(left, list);
			}
			if (mapRight.containsKey(right)) {
				mapRight.get(right).add(b);
			} else {
				List<Building> list = new ArrayList<>();
				list.add(b);
				mapRight.put(right, list);
			}
		}
		List<Integer> pointList = new ArrayList<>(pointSet);
		Collections.sort(pointList);
		PriorityQueue<Building> pq = new PriorityQueue<>(new Comparator<Building>() {
			public int compare(Building a, Building b) {
				return b.height - a.height;
			}
		});
		Set<Building> visited = new HashSet<>();
		int curHeight = 0;
		for (int point : pointList) {
			if (mapRight.containsKey(point)) {
				visited.addAll(mapRight.get(point));
			}
			if (mapLeft.containsKey(point)) {
				List<Building> list = mapLeft.get(point);
				for (Building b : list) {
					pq.offer(b);
				}
			}
			while (!pq.isEmpty() && visited.contains(pq.peek())) {
				pq.poll();
			}
			int height = 0;
			if (!pq.isEmpty()) {
				height = pq.peek().height;
			}
			if (height != curHeight) {
				int[] corner = new int[2];
				corner[0] = point;
				corner[1] = height;
				res.add(corner);
				curHeight = height;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		TheSkylineProblem obj = new TheSkylineProblem();
		int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
		List<int[]> res = obj.getSkylineFast(buildings);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(Arrays.toString(res.get(i)));
		}
	}

}
