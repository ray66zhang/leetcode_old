package tree;

/**
 * Medium
 * 
 * Given an integer array with no duplicates. A maximum tree building on this
 * array is defined as follow:
 * 
 * The root is the maximum number in the array. The left subtree is the maximum
 * tree constructed from left part subarray divided by the maximum number. The
 * right subtree is the maximum tree constructed from right part subarray
 * divided by the maximum number. Construct the maximum tree by the given array
 * and output the root node of this tree.
 * 
 * Example 1: Input: [3,2,1,6,0,5] Output: return the tree root node
 * representing the following tree:
 * 
 * 6 / \ 3 5 \ / 2 0 \ 1
 * 
 * Note: The size of the given array will be in the range [1,1000].
 *
 * Attempted: 1
 *
 * Percentile: 75.96%
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class MaximumBinaryTree {

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return null;
		}
		return construct(nums, 0, len - 1);
	}

	public TreeNode construct(int[] nums, int start, int end) {
		if (end < start) {
			return null;
		}
		int maxValue = nums[start];
		int maxIndex = start;
		for (int i = start; i <= end; i++) {
			int num = nums[i];
			if (num > maxValue) {
				maxValue = num;
				maxIndex = i;
			}
		}
		TreeNode node = new TreeNode(maxValue);
		node.left = construct(nums, start, maxIndex - 1);
		node.right = construct(nums, maxIndex + 1, end);
		return node;
	}

	public static void main(String[] args) {

	}

}
