// Minxi Han
// NUID: 002954176

import java.util.Arrays;

public class LinkedList {
	public static void main(String[] args) {
		// 2. Add Two Numbers
		ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
		System.out.println(ListNode.addTwoNumbers(l1, l2).toString());

		// 138. Copy List with Random Pointer [[1,1],[2,1]]
		ListNodeWithRandomPointer dummy = new ListNodeWithRandomPointer(-1);
		ListNodeWithRandomPointer o1 = new ListNodeWithRandomPointer(1);
		ListNodeWithRandomPointer o2 = new ListNodeWithRandomPointer(2);
		ListNodeWithRandomPointer curr = dummy;
		curr.next = o1;
		curr.next.next = o2;
		curr.next.random = o2;
		curr.next.next.random = o2;
		curr.next.next.next = null;
		System.out.println(ListNodeWithRandomPointer.copyRandomList(dummy.next).toString());

		// 23. Merge k Sorted Lists
		ListNode k1 = new ListNode(1, new ListNode(4, new ListNode(5)));
		ListNode k2 = new ListNode(1, new ListNode(3, new ListNode(4)));
		ListNode k3 = new ListNode(2, new ListNode(6));
		ListNode[] lists = {k1, k2, k3};
		System.out.println(ListNode.mergeKLists(lists).toString());

		// 143. Reorder List (in-place)

		// 234. Palindrome Linked List
		ListNode ll = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
		System.out.println(ListNode.isPalindrome(ll));

		// 19. Remove Nth Node From End of List
		int n = 2;
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		System.out.println(ListNode.removeNthFromEnd(head, n).toString());

		// 328. Odd Even Linked List
		ListNode headOddEven = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		System.out.println(ListNode.oddEvenList(headOddEven).toString());

		// 708. Insert into a Sorted Circular Linked List
		int insertVal = 2;
		LinkedNodeWithCircular circularLinkedList = new LinkedNodeWithCircular(3, new LinkedNodeWithCircular(4, new LinkedNodeWithCircular(1, new LinkedNodeWithCircular(3))));
		System.out.println(LinkedNodeWithCircular.insert(circularLinkedList, insertVal).toString());

		// 1019. Next Greater Node In Linked List [2,1,5]
		ListNode linkedList = new ListNode(2, new ListNode(1, new ListNode(5, null)));
		System.out.println(Arrays.toString(ListNode.nextLargerNodes(linkedList)));

		// 82. Remove Duplicates from Sorted List II
		ListNode duplicates = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, null)))))));
		System.out.println(ListNode.deleteDuplicates(duplicates).toString());
	}
}