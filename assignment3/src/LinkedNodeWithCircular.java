public class LinkedNodeWithCircular {
	public int val;
	public ListNodWithRandomPointer next;

	public LinkedNodeWithCircular() {
	}

	public LinkedNodeWithCircular(int _val) {
		val = _val;
	}

	public LinkedNodeWithCircular(int _val, ListNodWithRandomPointer _next) {
		val = _val;
		next = _next;
	}

	// 708. Insert into a Sorted Circular Linked List
	public static ListNodWithRandomPointer insert(ListNodWithRandomPointer head, int insertVal) {
		ListNodWithRandomPointer preHead = new ListNodWithRandomPointer(-1);
		preHead.next = head;

		ListNodWithRandomPointer newNode = new ListNodWithRandomPointer(insertVal);

		if (head == null) {
			preHead.next = newNode;
			newNode.next = newNode;
		} else if (head.next == head) {
			head.next = newNode;
			newNode.next = head;
		} else {
			ListNodWithRandomPointer c = head;
			ListNodWithRandomPointer n = c.next;
			while (true) {
				if (c.val <= n.val) {
					if (c.val <= insertVal && insertVal <= n.val) {
						c.next = newNode;
						newNode.next = n;
						break;
					}
				}
				// at the cliff
				else if (c.val > n.val) {
					if (c.val <= insertVal || n.val >= insertVal) {
						c.next = newNode;
						newNode.next = n;
						break;
					}
				}
				c = c.next;
				n = n.next;
				if (n == head) {
					c.next = newNode;
					newNode.next = head;
					break;
				}
			}
		}
		return preHead.next;
	}
}
