package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Hard
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Attempted: 1
 * 
 * Percentile: 55.83%
 *
 */
public class MergekSortedLists {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		} else if (lists.length == 1) {
			return lists[0];
		}
		ListNode pre = null;
		ListNode head = null;
		Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		});
		for (int i = 0; i < lists.length; i++) {
			ListNode node = lists[i];
			pq.offer(node);
		}
		while (!pq.isEmpty()) {
			ListNode node = pq.poll();
			if (pre == null) {
				pre = node;
				head = node;
			} else {
				pre.next = node;
			}
			System.out.println(pre.val);
			if (node.next != null) {
				node = node.next;
				pq.offer(node);
			}
		}
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
