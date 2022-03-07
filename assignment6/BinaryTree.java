import java.util.*;

// Minxi Han
// NUID: 002954176
public class BinaryTree<Pair> {

	// 437. Path Sum III
	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		return dfs(root, 0, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	public int dfs(TreeNode root, int curSum, int target) {
		if (root == null) {
			return 0;
		}
		curSum += root.val;
		int count = curSum == target ? 1 : 0;
		return count + dfs(root.left, curSum, target) + dfs(root.right, curSum, target);
	}


	// 236. Lowest Common Ancestor of a Binary Tree
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null)
			return root;
		if (left != null && right == null)
			return left;
		if (left == null && right != null)
			return right;
		return null;
	}


	// 687. Longest Univalue Path
	int ret = 0;

	public int longestUnivaluePath(TreeNode root) {
		findMaxLength(root);
		return ret;
	}

	public int findMaxLength(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left_length = findMaxLength(root.left);
		int right_length = findMaxLength(root.right);
		int left = 0;
		int right = 0;

		if (root.left != null && root.val == root.left.val) {
			left = left_length + 1;
		}
		if (root.right != null && root.val == root.right.val) {
			right = right_length + 1;
		}
		ret = Math.max(ret, left + right);
		return Math.max(left, right);
	}


	// 297. Serialize and Deserialize Binary Tree
	public String serialize(TreeNode root) {
		return serialize(root, "");
	}

	public String serialize(TreeNode root, String str) {
		if (root == null) {
			str += "None,";
		} else {
			str += str.valueOf(root.val) + ",";
			str = serialize(root.left, str);
			str = serialize(root.right, str);
		}
		return str;
	}

	public TreeNode deserialize(String data) {
		String[] dataArray = data.split(",");
		List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
		return deserialize(dataList);
	}

	public TreeNode deserialize(List<String> dataList) {
		if (dataList.get(0).equals("None")) {
			dataList.remove(0);
			return null;
		}

		TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
		dataList.remove(0);
		root.left = deserialize(dataList);
		root.right = deserialize(dataList);
		return root;
	}


	// 987. Vertical Order Traversal of a Binary Tree
	Map<Integer, List<Integer[]>> vertMap = new TreeMap<>();

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		preOrder(root, 0, 0);

		for (List<Integer[]> list : vertMap.values()) {
			list.sort((a1, a2) -> (a1[0] == a2[0]) ? (a1[1] - a2[1]) : (a1[0] - a2[0]));
			List<Integer> temp = new ArrayList<>();
			for (Integer[] nums : list) {
				temp.add(nums[1]);
			}
			ret.add(temp);
		}
		return ret;
	}

	private void preOrder(TreeNode root, int x, int y) {
		if (root == null) {
			return;
		}

		List<Integer[]> list = vertMap.getOrDefault(y, new ArrayList<>());
		list.add(new Integer[]{x, root.val});
		vertMap.put(y, list);

		preOrder(root.left, x + 1, y - 1);
		preOrder(root.right, x + 1, y + 1);
	}


	// 222. Count Complete Tree Nodes
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + countNodes(root.right) + countNodes(root.left);
	}


	// 129. Sum Root to Leaf Numbers
	int rootToLeaf = 0;

	public void preorder(TreeNode root, int currNumber) {
		if (root != null) {
			currNumber = currNumber * 10 + root.val;
			if (root.left == null && root.right == null) {
				rootToLeaf += currNumber;
			}
			preorder(root.left, currNumber);
			preorder(root.right, currNumber);
		}
	}

	public int sumNumbers(TreeNode root) {
		preorder(root, 0);
		return rootToLeaf;
	}

	// 1325. Delete Leaves With a Given Value
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (root == null) {
			return null;
		}

		root.left = removeLeafNodes(root.left, target);
		root.right = removeLeafNodes(root.right, target);

		if (root.left == null && root.right == null && root.val == target) {
			return null;
		}
		return root;
	}
}
