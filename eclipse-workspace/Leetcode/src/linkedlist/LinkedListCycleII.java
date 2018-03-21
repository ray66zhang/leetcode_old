package linkedlist;

/**
 * Medium
 * 
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * Note: Do not modify the linked list.
 * 
 * Follow up: Can you solve it without using extra space?
 *
 * Attempted: 2
 * 
 * Percentile :100%
 * 
 * Pay attention to the initial start point of the while loop
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class LinkedListCycleII {

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		boolean meet = false;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				meet = true;
				break;
			}
		}
		if (meet == false) {
			return null;
		} else {
			slow = head;
			while (fast != slow) {
				fast = fast.next;
				slow = slow.next;
			}
			return fast;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
