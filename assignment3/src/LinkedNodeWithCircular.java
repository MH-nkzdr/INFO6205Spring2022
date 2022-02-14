public class LinkedNodeWithCircular {
	public int val;
	public LinkedNodeWithCircular next;

	public LinkedNodeWithCircular() {
	}

	public LinkedNodeWithCircular(int _val) {
		val = _val;
	}

	public LinkedNodeWithCircular(int _val, LinkedNodeWithCircular _next) {
		val = _val;
		next = _next;
	}

	// 708. Insert into a Sorted Circular Linked List
	public static LinkedNodeWithCircular insert(LinkedNodeWithCircular head, int insertVal) {
		LinkedNodeWithCircular preHead = new LinkedNodeWithCircular(-1);
		preHead.next = head;

		LinkedNodeWithCircular newNode = new LinkedNodeWithCircular(insertVal);

		// empty list
		if (head == null) {
			preHead.next = newNode;
			newNode.next = newNode;
		}
		// only one node
		else if (head.next == head) {
			head.next = newNode;
			newNode.next = head;
		} else {
			LinkedNodeWithCircular c = head;
			LinkedNodeWithCircular n = c.next;

			while (true) {
				// in the middle of ascending
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
				// circled back to head, all nodes are equal
				if (n == head) {
					c.next = newNode;
					newNode.next = head;
					break;
				}
			}
		}
		return preHead.next;
	}

	@Override
	public String toString() {
		return String.format("Node(%s, next = %s)", val, next);
	}
}
