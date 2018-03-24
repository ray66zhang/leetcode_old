package linkedlist;

/**
 * Easy
 * 
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 *
 * Attempted: 1
 *
 * Percentile: 59.28%
 */
public class MergeTwoSortedLists {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		}
		ListNode pre;
		if (l1.val <= l2.val) {
			pre = new ListNode(l1.val);
			l1 = l1.next;
		} else {
			pre = new ListNode(l2.val);
			l2 = l2.next;
		}
		ListNode head = pre;
		while (l1 != null && l2 != null) {
			ListNode node;
			if (l1.val <= l2.val) {
				node = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				node = new ListNode(l2.val);
				l2 = l2.next;
			}
			pre.next = node;
			pre = node;
		}
		if (l1 == null) {
			pre.next = l2;
		} else {
			pre.next = l1;
		}
		return head;
	}

	public static void main(String[] args) {

	}

}
