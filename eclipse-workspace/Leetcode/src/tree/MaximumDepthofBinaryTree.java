package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Easy
 * 
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * 3 / \ 9 20 / \ 15 7 return its depth = 3.
 *
 */
public class MaximumDepthofBinaryTree {

	public int maxDepthDFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return dfs(root, 1);
	}

	public int dfs(TreeNode root, int depth) {
		if (root.left == null && root.right == null) {
			return depth;
		} else if (root.left == null && root.right != null) {
			return dfs(root.right, depth + 1);
		} else if (root.left != null && root.right == null) {
			return dfs(root.left, depth + 1);
		} else {
			return Math.max(dfs(root.left, depth + 1), dfs(root.right, depth + 1));
		}
	}

	public int maxDepthBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Map<TreeNode, Integer> map = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		map.put(root, 1);
		int depth = 0;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			depth = map.get(node);
			if (node.left != null) {
				queue.offer(node.left);
				map.put(node.left, depth + 1);
			}
			if (node.right != null) {
				queue.offer(node.right);
				map.put(node.right, depth + 1);
			}
		}
		return depth;
	}

}
