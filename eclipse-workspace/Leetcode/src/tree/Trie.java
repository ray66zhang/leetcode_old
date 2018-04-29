package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Medium
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Attempted: 1
 *
 * Percentile: 41.13%
 */
public class Trie {

	class TrieNode {
		boolean hasWord;
		String word;
		Map<Character, TrieNode> children;

		public TrieNode() {
			this.hasWord = false;
			this.word = null;
			this.children = new HashMap<>();
		}
	}

	public TrieNode root;

	/** Initialize your data structure here. */
	public Trie() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		insertDfs(word, 0, root);
	}

	public void insertDfs(String word, int index, TrieNode node) {
		if (index >= word.length()) {
			node.hasWord = true;
			node.word = word;
			return;
		}
		char c = word.charAt(index);
		Map<Character, TrieNode> children = node.children;
		TrieNode nextNode;
		if (children.containsKey(c)) {
			nextNode = children.get(c);
		} else {
			nextNode = new TrieNode();
		}
		insertDfs(word, index + 1, nextNode);
		children.put(c, nextNode);
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		return searchDfs(word, 0, root, false);
	}

	public boolean searchDfs(String word, int index, TrieNode node, boolean prefix) {
		if (index >= word.length()) {
			if (prefix || (node.hasWord && node.word.equals(word))) {
				return true;
			} else {
				return false;
			}
		}
		char c = word.charAt(index);
		Map<Character, TrieNode> children = node.children;
		if (children.containsKey(c)) {
			TrieNode nextNode = children.get(c);
			return searchDfs(word, index + 1, nextNode, prefix);
		} else {
			return false;
		}
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		return searchDfs(prefix, 0, root, true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
