package heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Medium
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element. For
 * example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * Attempted: 1
 *
 * Percentile: 70.66%
 */
public class KthLargestElementinanArray {

	public int findKthLargest(int[] nums, int k) {
		Queue<Integer> pq = new PriorityQueue<>();
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			if (pq.size() < k) {
				pq.offer(num);
			} else {
				if (pq.peek() < num) {
					pq.poll();
					pq.offer(num);
				}
			}
		}
		return pq.peek();
	}

	public static void main(String[] args) {
		KthLargestElementinanArray obj = new KthLargestElementinanArray();
		int[] nums = { 3, 2, 1, 5, 6, 4 };
		int k = 2;
		System.out.println(obj.findKthLargest(nums, k));
	}

}
