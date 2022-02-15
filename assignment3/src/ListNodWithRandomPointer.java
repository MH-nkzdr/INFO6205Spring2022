import java.util.HashMap;

public class ListNodWithRandomPointer {
	int val;
	ListNodWithRandomPointer next;
	ListNodWithRandomPointer random;

	public ListNodWithRandomPointer(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}

	// 138. Copy List with Random Pointer
	public static ListNodWithRandomPointer copyRandomList(ListNodWithRandomPointer head) {
		if (head == null) {
			return null;
		}
		HashMap<ListNodWithRandomPointer, ListNodWithRandomPointer> m = new HashMap<>();
		ListNodWithRandomPointer c = head;

		while (c != null) {
			ListNodWithRandomPointer newNode = new ListNodWithRandomPointer(c.val);
			m.put(c, newNode);
			c = c.next;
		}

		c = head;
		while (c != null) {
			ListNodWithRandomPointer newNode = m.get(c);
			if (c.next != null) {
				newNode.next = m.get(c.next);
			}
			if (c.random != null) {
				newNode.random = m.get(c.random);
			}
			c = c.next;
		}
		return m.get(head);
	}
}
