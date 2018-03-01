package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 *
 * Attempted: 1
 *
 * Percentile: 31.46%
 * 
 * Time complexity: O(nlogn)
 * 
 * Better solution: use count sort idea to create an array with index as
 * frequency and value and the number, put the hashmap data into the array and
 * find the top K from the largest to the smallest.
 */
class Node {
	int val;
	int freq;

	public Node(int val, int freq) {
		this.val = val;
		this.freq = freq;
	}
}

public class TopKFrequentElements {

	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		PriorityQueue<Node> pq = new PriorityQueue<>(k, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n2.freq - n1.freq;
			}
		});
		for (int key : map.keySet()) {
			int value = map.get(key);
			Node node = new Node(key, value);
			pq.add(node);
		}
		for (int i = 0; i < k; i++) {
			res.add(pq.poll().val);
		}
		return res;
	}

	public static void main(String[] args) {
		TopKFrequentElements obj = new TopKFrequentElements();
		int[] nums = { 1, 1, 1, 2, 2, 3 };
		int k = 2;
		System.out.println(obj.topKFrequent(nums, k));
	}

}
