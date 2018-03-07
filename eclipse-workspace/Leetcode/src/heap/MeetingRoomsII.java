package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * Meeting Rooms II:
 * 
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
 *
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

public class MeetingRoomsII {

	public int minMeetingRooms(Interval[] intervals) {
		int len = intervals.length;
		if (len < 2) {
			return len;
		}
		PriorityQueue<Interval> pq1 = new PriorityQueue<>(len, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		for (Interval interval : intervals) {
			pq1.offer(interval);
		}
		PriorityQueue<Interval> pq2 = new PriorityQueue<>(len, new Comparator<Interval>() {
			public int compare(Interval interval1, Interval interval2) {
				return interval1.end - interval2.end;
			}
		});
		int max = 0;
		while (!pq1.isEmpty()) {
			Interval interval = pq1.poll();
			while (!pq2.isEmpty() && pq2.peek().end <= interval.start) {
				pq2.poll();
			}
			pq2.offer(interval);
			if (pq2.size() > max) {
				max = pq2.size();
			}
		}
		return max;
	}

	public static void main(String[] args) {
		MeetingRoomsII obj = new MeetingRoomsII();
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0, 30);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		System.out.println(obj.minMeetingRooms(intervals));

	}

}
