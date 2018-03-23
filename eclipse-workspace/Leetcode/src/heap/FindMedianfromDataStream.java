package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Hard
 * 
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure. double findMedian() - Return the median of all elements so far.
 * For example:
 * 
 * addNum(1) addNum(2) findMedian() -> 1.5 addNum(3) findMedian() -> 2
 *
 * Attempted: 1
 *
 * Percentile: 32.80%
 */
public class FindMedianfromDataStream {

	Queue<Integer> pqsmall;
	Queue<Integer> pqlarge;

	public FindMedianfromDataStream() {
		pqsmall = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});
		pqlarge = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
	}

	public void balance() {
		if (Math.abs(pqsmall.size() - pqlarge.size()) <= 1) {
			return;
		}
		if (pqsmall.size() > pqlarge.size()) {
			pqlarge.offer(pqsmall.poll());
		} else {
			pqsmall.offer(pqlarge.poll());
		}
	}

	public void addNum(int num) {
		if (pqsmall.isEmpty()) {
			pqsmall.offer(num);
		} else {
			if (num <= pqsmall.peek()) {
				pqsmall.offer(num);
			} else {
				pqlarge.offer(num);
			}
		}
		balance();
	}

	public double findMedian() {
		System.out.println(pqsmall);
		System.out.println(pqlarge);
		if (pqsmall.isEmpty() && pqlarge.isEmpty()) {
			return 0;
		} else if (pqsmall.isEmpty() || pqlarge.isEmpty()) {
			if (pqsmall.isEmpty()) {
				return pqlarge.peek();
			} else {
				return pqsmall.peek();
			}
		} else {
			if (pqsmall.size() == pqlarge.size()) {
				return 1.0 * (pqsmall.peek() + pqlarge.peek()) / 2;
			} else if (pqsmall.size() > pqlarge.size()) {
				return pqsmall.peek();
			} else {
				return pqlarge.peek();
			}
		}
	}

	public static void main(String[] args) {
		FindMedianfromDataStream obj = new FindMedianfromDataStream();
		obj.addNum(-1);
		System.out.println(obj.findMedian());
		obj.addNum(-2);
		System.out.println(obj.findMedian());
		obj.addNum(-3);
		System.out.println(obj.findMedian());
	}

}
