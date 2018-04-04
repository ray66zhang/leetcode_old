package heap;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Hard
 * 
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position Max --------------- ----- [1 3 -1] -3 5 3 6 7 3 1 [3 -1 -3] 5
 * 3 6 7 3 1 3 [-1 -3 5] 3 6 7 5 1 3 -1 [-3 5 3] 6 7 5 1 3 -1 -3 [5 3 6] 7 6 1 3
 * -1 -3 5 [3 6 7] 7 Therefore, return the max sliding window as [3,3,5,5,6,7].
 * 
 * Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for
 * non-empty array.
 *
 * Attempted: 1
 *
 * Percentile: 80.33%
 */

class QueueNode {
	int val;
	int index;

	public QueueNode(int val, int index) {
		this.val = val;
		this.index = index;
	}
}

public class SlidingWindowMaximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
		int len = nums.length;
		if (len == 0) {
			return new int[0];
		}
		int[] res = new int[len - k + 1];
		Deque<QueueNode> queue = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			QueueNode node = new QueueNode(num, i);
			if (queue.isEmpty()) {
				queue.offer(node);
			} else {
				if (i - queue.peekLast().index >= k) {
					queue.pollLast();
				}
				while (!queue.isEmpty()) {
					if (queue.peekFirst().val <= num) {
						queue.pollFirst();
					} else {
						break;
					}
				}
				queue.offerFirst(node);
			}
			if (i >= k - 1) {
				res[i - k + 1] = queue.peekLast().val;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		SlidingWindowMaximum obj = new SlidingWindowMaximum();
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		System.out.println(Arrays.toString(obj.maxSlidingWindow(nums, k)));
	}

}
