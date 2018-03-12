package reservoir_sampling;

import java.util.Random;

/**
 * Medium
 * 
 * Given a singly linked list, return a random node's value from the linked
 * list. Each node must have the same probability of being chosen.
 * 
 * Follow up: What if the linked list is extremely large and its length is
 * unknown to you? Could you solve this efficiently without using extra space?
 * 
 * Example:
 * 
 * // Init a singly linked list [1,2,3]. ListNode head = new ListNode(1);
 * head.next = new ListNode(2); head.next.next = new ListNode(3); Solution
 * solution = new Solution(head);
 * 
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should
 * have equal probability of returning. solution.getRandom();
 * 
 * Attempted: 2
 * 
 * Percentile: 76.59%
 *
 * Don't forget to create a new node each time running get()
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class LinkedListRandomNode {

	ListNode head;
	Random rand = new Random();

	public LinkedListRandomNode(ListNode head) {
		this.head = head;
	}

	/** Returns a random node's value. */
	public int getRandom() {
		ListNode node = head;
		int count = 1;
		int res = node.val;
		while (node != null) {
			int p = rand.nextInt(count);
			if (p == 0) {
				res = node.val;
			}
			count++;
			node = node.next;
		}
		return res;
	}

	public static void main(String[] args) {

	}

}
