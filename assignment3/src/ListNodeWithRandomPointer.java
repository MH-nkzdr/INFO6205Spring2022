import java.util.HashMap;

public class ListNodeWithRandomPointer {
	int val;
	ListNodeWithRandomPointer next;
	ListNodeWithRandomPointer random;

	public ListNodeWithRandomPointer(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}

	// 138. Copy List with Random Pointer
	public static ListNodeWithRandomPointer copyRandomList(ListNodeWithRandomPointer head) {
		if (head == null) {
			return null;
		}
		HashMap<ListNodeWithRandomPointer, ListNodeWithRandomPointer> m = new HashMap<>();
		ListNodeWithRandomPointer c = head;

		while (c != null) {
			ListNodeWithRandomPointer newNode = new ListNodeWithRandomPointer(c.val);
			m.put(c, newNode);
			c = c.next;
		}

		c = head;
		while (c != null) {
			ListNodeWithRandomPointer newNode = m.get(c);
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

	@Override
	public String toString() {
		return String.format("Node(%s, next = %s, random.val = %s)", val, next, random.val);
	}
}
