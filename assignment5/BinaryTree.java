import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Minxi Han
// NUID: 002954176
public class BinaryTree {

	// 107. Binary Tree Level Order Traversal II
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null) return null;

		List<List<Integer>> levelOrder = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> ll = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				ll.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			levelOrder.add(0, ll);
		}
		return levelOrder;
	}


	// 366. Find Leaves of Binary Tree
	List<List<Integer>> ret = new ArrayList<>();

	public List<List<Integer>> findLeaves(TreeNode root) {
		while (root != null) {
			List<Integer> leafs = new ArrayList<>();
			root = removeLeafs(root, leafs);
			ret.add(leafs);
		}
		return ret;
	}

	private TreeNode removeLeafs(TreeNode root, List<Integer> leafs) {
		if (root == null) return null;

		if (root.left == null && root.right == null) {
			leafs.add(root.val);
			return null;
		}
		root.left = removeLeafs(root.left, leafs);
		root.right = removeLeafs(root.right, leafs);
		return root;
	}


	// 662. Maximum Width of Binary Tree
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;

		Queue<TreeNode> queue = new LinkedList<>();
		LinkedList<Integer> ll = new LinkedList<>();
		queue.offer(root);
		ll.add(1);
		int maxWidth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = size; i > 0; i--) {
				TreeNode node = queue.poll();
				Integer index = ll.removeFirst();
				if (node.left != null) {
					queue.offer(node.left);
					ll.add(index * 2);
				}
				if (node.right != null) {
					queue.offer(node.right);
					ll.add(index * 2 + 1);
				}
			}
			if (ll.size() >= 2) {
				maxWidth = Math.max(maxWidth, ll.getLast() - ll.getFirst() + 1);
			}
		}
		return maxWidth;
	}


	// 515. Find Largest Value in Each Tree Row
	public List<Integer> largestValues(TreeNode root) {
		if (root == null) return new ArrayList<>();

		List<Integer> ret = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int max = Integer.MIN_VALUE;
			while (size > 0) {
				TreeNode node = queue.poll();
				max = Math.max(max, node.val);
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
				size--;
			}
			ret.add(max);
		}
		return ret;
	}
}