package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Meeting Rooms:
 * 
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all
 * meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 *
 */

public class MeetingRooms {

	public boolean canAttendMeetings(Interval[] intervals) {
		if (intervals.length == 0) {
			return true;
		}
		PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		for (Interval i : intervals) {
			if (i.end > i.start) {
				pq.offer(i);
			}
		}
		int cur = pq.poll().end;
		while (!pq.isEmpty()) {
			Interval i = pq.poll();
			if (i.start < cur) {
				return false;
			}
			cur = i.end;
		}
		return true;
	}

	public static void main(String[] args) {
		// [[0, 30],[5, 10],[15, 20]]
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0, 30);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		MeetingRooms obj = new MeetingRooms();
		System.out.println(obj.canAttendMeetings(intervals));
	}

}
