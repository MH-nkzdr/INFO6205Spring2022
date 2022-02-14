import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	// 2. Add Two Numbers
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode pre = new ListNode(0);
		ListNode cur = pre;
		int remainder = 0;
		while (l1 != null || l2 != null) {
			int x = l1 == null ? 0 : l1.val;
			int y = l2 == null ? 0 : l2.val;
			int total = x + y + remainder;

			remainder = total / 10;
			cur.next = new ListNode(total % 10);
			cur = cur.next;
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		if (remainder == 1) {
			cur.next = new ListNode(remainder);
		}
		return pre.next;
	}

	// 23. Merge k Sorted Lists
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) return null;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
		for (ListNode x : lists) {
			while (x != null) {
				minHeap.add(x.val);
				x = x.next;
			}
		}

		ListNode dummy = new ListNode(-1);
		ListNode x = dummy;
		while (!minHeap.isEmpty()) {
			int y = minHeap.poll();
			x.next = new ListNode(y);
			x = x.next;
		}
		return dummy.next;
	}

	// 234. Palindrome Linked List
	public static boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode mid = slow;
		ListNode rightHead = mid.next;
		mid.next = null;

		ListNode curr = rightHead;
		ListNode pre = null;
		while (curr != null) {
			ListNode nxt;
			nxt = curr.next;
			curr.next = pre;
			pre = curr;
			curr = nxt;
		}

		ListNode reversed = pre;
		while (head != null && reversed != null) {
			if (head.val != reversed.val) return false;
			head = head.next;
			reversed = reversed.next;
		}
		return true;
	}

	// 19. Remove Nth Node From End of List
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode start = dummy;
		ListNode end = dummy;

		while (n != 0) {
			start = start.next;
			n--;
		}
		while (start.next != null) {
			start = start.next;
			end = end.next;
		}
		end.next = end.next.next;
		return dummy.next;
	}

	// 328. Odd Even Linked List
	public static ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode oddTail = head;
		ListNode evenTail = head.next;
		ListNode evenHead = evenTail;

		while (evenTail != null && evenTail.next != null) {
			oddTail.next = evenTail.next;
			oddTail = oddTail.next;
			evenTail.next = oddTail.next;
			evenTail = evenTail.next;
		}
		oddTail.next = evenHead;
		return head;
	}

	// 1019. Next Greater Node In Linked List
	public static int[] nextLargerNodes(ListNode head) {
		List<Integer> stored = new ArrayList<>();
		ListNode c = head;
		while (c != null) {
			stored.add(c.val);
			c = c.next;
		}

		Stack<Integer> stack = new Stack<>();
		int[] ret = new int[stored.size()];
		for (int i = 0; i < stored.size(); i++) {
			while (!stack.empty() && stored.get(stack.peek()) < stored.get(i)) {
				int index = stack.pop();
				ret[index] = stored.get(i);
			}
			stack.push(i);
		}
		return ret;
	}

	// 82. Remove Duplicates from Sorted List II
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		while (head != null) {
			if (head.next == null || head.val != head.next.val) {
				curr.next = head;
				curr = head;
			}
			while (head.next != null && head.val == head.next.val) head = head.next;
			head = head.next;
		}
		curr.next = null;
		return dummy.next;
	}

	// 143. Reorder List
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) return;
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode mid = slow;
		ListNode rightHead = mid.next;
		mid.next = null;

		ListNode pre = null;
		ListNode curr = rightHead;
		while (curr != null) {
			ListNode nxt;
			nxt = curr.next;
			curr.next = pre;
			pre = curr;
			curr = nxt;
		}

		ListNode reversedRightHead = pre;
		ListNode l = head;
		ListNode r = reversedRightHead;
		while (r != null) {
			ListNode lNext = l.next;
			ListNode rNext = r.next;
			r.next = l.next;
			l.next = r;
			l = lNext;
			r = rNext;
		}
	}

	@Override
	public String toString() {
		return String.format("Node(%s, next = %s)", val, next);
	}
}