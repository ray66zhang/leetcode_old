package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Medium
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 *
 * Attempted: 1
 *
 * Percentile: 92.56%
 */

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class MergeIntervals {

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals.size() == 0) {
			return res;
		}
		Queue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		for (Interval interval : intervals) {
			pq.offer(interval);
		}
		Interval cur = pq.poll();
		while (!pq.isEmpty()) {
			Interval interval = pq.poll();
			if (cur.end < interval.start) {
				res.add(cur);
				cur = interval;
			} else {
				cur.end = Math.max(cur.end, interval.end);
			}
		}
		res.add(cur);
		return res;
	}

	public static void main(String[] args) {

	}

}
