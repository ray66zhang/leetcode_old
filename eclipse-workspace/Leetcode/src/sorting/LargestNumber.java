package sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * Attempted: 3
 * 
 * Percentile: 47.18%
 * 
 * Over-complicated thought. When comparing two numbers, we simply construct the
 * two concatenated strings and compare from there.
 *
 */
public class LargestNumber {

	public String largestNumber(int[] nums) {
		StringBuilder res = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>(10, new Comparator<Integer>() {
			public int compare(Integer n1, Integer n2) {
				String s1 = Integer.toString(n1);
				String s2 = Integer.toString(n2);
				String s1s2 = s1 + s2;
				String s2s1 = s2 + s1;
				long l1 = Long.parseLong(s1s2);
				long l2 = Long.parseLong(s2s1);
				return (int) (l2 - l1);
			}
		});
		for (int num : nums) {
			pq.offer(num);
		}
		while (!pq.isEmpty()) {
			res.append(pq.poll());
		}
		String num = res.toString();
		if (num.length() > 1 && num.charAt(0) == '0') {
			return "0";
		} else {
			return num;
		}
	}

	public static void main(String[] args) {
		LargestNumber obj = new LargestNumber();
		int[] nums = { 121, 12 };
		System.out.println(obj.largestNumber(nums));

	}

}
