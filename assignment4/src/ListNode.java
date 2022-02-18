// Minxi Han
// NUID: 002954176

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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

	// 1669. Merge In Between Linked Lists
	public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		ListNode leftNode = new ListNode(0);
		ListNode rightNode = new ListNode(0);
		ListNode node = list1;
		int i = 0;
		while (node != null) {
			if (i == a - 1) {
				leftNode = node;
			}
			if (i == b + 1) {
				rightNode = node;
				break;
			}
			i++;
			node = node.next;
		}

		leftNode.next = list2;
		node = list2;
		while (node != null) {
			if (node.next == null) {
				node.next = rightNode;
				break;
			}
			node = node.next;
		}
		return list1;
	}

	// 86. Partition List
	public ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null) return head;

		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		List<Integer> smaller = new ArrayList<>();
		List<Integer> larger = new ArrayList<>();
		while (head != null) {
			if (head.val < x) {
				smaller.add(head.val);
			} else {
				larger.add(head.val);
			}
			head = head.next;
		}
		for (int sm : smaller) {
			curr.next = new ListNode(sm);
			curr = curr.next;
		}
		for (int la : larger) {
			curr.next = new ListNode(la);
			curr = curr.next;
		}
		return dummy.next;
	}

	// 2074. Reverse Nodes in Even Length Groups
	public ListNode reverseEvenLengthGroups(ListNode head) {
		if (head == null || head.next == null) return head;

		List<Integer> group = new ArrayList<>();
		while (head != null) {
			group.add(head.val);
			head = head.next;
		}

		ListNode dummyNode = new ListNode(0);
		ListNode currNode = dummyNode;
		List<Integer> currGroup;
		int i = 0;
		int n = group.size();
		int currLength = 1;
		while (i < n) {
			currGroup = group.subList(i, Math.min(currLength + i, n));
			if (currGroup.size() % 2 == 0) {
				Collections.reverse(currGroup);
			}
			for (int ch : currGroup) {
				currNode.next = new ListNode(ch);
				currNode = currNode.next;
			}
			i += currLength;
			currLength++;
		}
		return dummyNode.next;
	}

	// 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
	public int[] nodesBetweenCriticalPoints(ListNode head) {
		List<Integer> nums = new ArrayList<>();
		while (head != null) {
			nums.add(head.val);
			head = head.next;
		}

		int n = nums.size();
		List<Integer> picked = new ArrayList<>();
		for (int i = 1; i < n - 1; i++) {
			if (nums.get(i - 1) > nums.get(i) && nums.get(i) < nums.get(i + 1)) {
				picked.add(i);
			} else if (nums.get(i - 1) < nums.get(i) && nums.get(i) > nums.get(i + 1)) {
				picked.add(i);
			}
		}

		if (picked.size() < 2) {
			return new int[]{-1, -1};
		}
		int maxSpot = picked.get(picked.size() - 1) - picked.get(0);
		int minSpot = Integer.MAX_VALUE;
		for (int i = 1; i < picked.size(); i++) {
			minSpot = Math.min(minSpot, picked.get(i) - picked.get(i - 1));
		}
		return new int[]{minSpot, maxSpot};
	}

	// 148. Sort List
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		return merge(head);
	}

	public ListNode merge(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode mid = slow.next;
		slow.next = null;
		ListNode ll = merge(head);
		ListNode rr = merge(mid);
		return mergeSort(ll, rr);
	}

	public ListNode mergeSort(ListNode ll, ListNode rr) {
		ListNode dummyNode = new ListNode(0);
		ListNode pre = dummyNode;
		while (ll != null && rr != null) {
			if (ll.val < rr.val) {
				pre.next = ll;
				ll = ll.next;
			} else {
				pre.next = rr;
				rr = rr.next;
			}
			pre = pre.next;
		}
		if (ll != null) pre.next = ll;
		if (rr != null) pre.next = rr;

		return dummyNode.next;
	}

	// 382. Linked List Random Node
	class Solution {
		List<Integer> list = new ArrayList<>();

		public Solution(ListNode head) {
			while (head != null) {
				list.add(head.val);
				head = head.next;
			}
		}

		public int getRandom() {
			return list.get((int) (Math.random() * list.size()));
		}
	}

	// 92. Reverse Linked List II
	public ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode pre = dummyHead;
		for (int i = 0; i < left - 1; i++) {
			pre = pre.next;
		}

		ListNode leftNode = pre.next;
		ListNode curr = pre;
		for (int i = 0; i < right - left + 1; i++) {
			curr = curr.next;
		}

		ListNode rightNode = curr.next;
		pre.next = null;
		curr.next = null;
		reverseLinkedList(leftNode);
		pre.next = curr;
		leftNode.next = rightNode;

		return dummyHead.next;
	}

	public void reverseLinkedList(ListNode head) {
		ListNode pre = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode next;
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
	}

	// 725. Split Linked List in Parts
	public ListNode[] splitListToParts(ListNode head, int k) {
		ListNode p = head;
		int cnt = 0;
		while (p != null) {
			p = p.next;
			cnt++;
		}

		int basic = (int) Math.floor(cnt / k);
		int bonus = cnt % k;
		int index = 0;
		ListNode[] ret = new ListNode[k];
		ListNode curr = head;
		while (curr != null) {
			ListNode last;
			ret[index] = curr;
			last = null;

			int currLength = index < bonus ? basic + 1 : basic;
			for (int i = 0; i < currLength; i++) {
				last = curr;
				curr = curr.next;
			}

			last.next = null;
			index++;
		}
		return ret;
	}

	// 817. Linked List Components
	public int numComponents(ListNode head, int[] nums) {
		HashSet nodeSet = new HashSet();
		for (int num : nums) nodeSet.add(num);

		ListNode curr = head;
		int ret = 0;

		while (curr != null) {
			if (nodeSet.contains(curr.val) && (curr.next == null || !nodeSet.contains(curr.next.val)))
				ret++;
			curr = curr.next;
		}
		return ret;
	}

	// 2130. Maximum Twin Sum of a Linked List
	public int pairSum(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode pre = null;
		ListNode curr = slow.next;
		while (curr != null) {
			ListNode next;
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}

		ListNode reversed = pre;
		int maxPair = Integer.MIN_VALUE;
		while (reversed != null) {
			maxPair = Math.max(maxPair, head.val + reversed.val);
			head = head.next;
			reversed = reversed.next;
		}
		return maxPair;
	}
}
