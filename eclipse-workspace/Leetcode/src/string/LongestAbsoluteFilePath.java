package string;

import java.util.Stack;

/**
 * Medium
 * 
 * Suppose we abstract our file system by a string in the following manner:
 * 
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * 
 * dir subdir1 subdir2 file.ext The directory dir contains an empty
 * sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * 
 * The string
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * represents:
 * 
 * dir subdir1 file1.ext subsubdir1 subdir2 subsubdir2 file2.ext The directory
 * dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file
 * file1.ext and an empty second-level sub-directory subsubdir1. subdir2
 * contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 * 
 * We are interested in finding the longest (number of characters) absolute path
 * to a file within our file system. For example, in the second example above,
 * the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its
 * length is 32 (not including the double quotes).
 * 
 * Given a string representing the file system in the above format, return the
 * length of the longest absolute path to file in the abstracted file system. If
 * there is no file in the system, return 0.
 * 
 * Note: The name of a file contains at least a . and an extension. The name of
 * a directory or sub-directory will not contain a .. Time complexity required:
 * O(n) where n is the size of the input string.
 * 
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is
 * another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 *
 * Attempted: 1
 *
 * Percentile: 22.48%
 */

class FileNode {
	String parent = null;
	String token = null;
	int tab = 0;

	public FileNode(String parent, String token, int tab) {
		this.parent = parent;
		this.token = token;
		this.tab = tab;
	}

	public int getLength() {
		return getFull().length();
	}

	public String getFull() {
		if (tab > 0) {
			return parent + "/" + token;
		} else {
			return token;
		}
	}
}

public class LongestAbsoluteFilePath {

	public int lengthLongestPath(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		String[] tokens = input.split("\n");
		int max = 0;
		Stack<FileNode> stack = new Stack<>();
		FileNode root = new FileNode("", "", -1);
		stack.push(root);
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			String[] tabs = token.split("\t");
			int tab = tabs.length - 1;
			if (tab > stack.peek().tab) {
				FileNode node = new FileNode(stack.peek().getFull(), tabs[tabs.length - 1], tab);
				stack.push(node);
			} else if (tab == stack.peek().tab) {
				FileNode node = new FileNode(stack.peek().parent, tabs[tabs.length - 1], tab);
				stack.pop();
				stack.push(node);
			} else {
				while (tab <= stack.peek().tab) {
					stack.pop();
				}
				FileNode node = new FileNode(stack.peek().getFull(), tabs[tabs.length - 1], tab);
				stack.push(node);
			}
			if (stack.peek().token.contains(".")) {
				int length = stack.peek().getLength();
				if (length > max) {
					max = length;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		LongestAbsoluteFilePath obj = new LongestAbsoluteFilePath();
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.println(obj.lengthLongestPath(input));

	}

}
