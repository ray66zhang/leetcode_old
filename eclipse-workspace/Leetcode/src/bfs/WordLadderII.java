package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Hard
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time Each transformed word must exist in
 * the word list. Note that beginWord is not a transformed word. For example,
 * 
 * Given: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log","cog"] Return [
 * ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note:
 * Return an empty list if there is no such transformation sequence. All words
 * have the same length. All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 *
 * Attempted: 3
 *
 * Percentile: 4.96%
 */

class Node {
	String word;
	Node pre;
	List<Node> next;

	public Node(String word) {
		this.word = word;
		this.pre = null;
		this.next = new ArrayList<>();
	}
}

public class WordLadderII {

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		if (wordList.size() == 0) {
			return res;
		}
		int len = beginWord.length();
		Set<String> wordSet = new HashSet<>(wordList);
		Map<String, Integer> level = new HashMap<>();
		level.put(beginWord, 1);
		boolean found = false;
		int minLevel = -1;
		Node head = new Node(beginWord);
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int cur = level.get(node.word);
			System.out.println(node.word + " " + cur);
			if (found == true && cur > minLevel) {
				break;
			}
			char[] ch = node.word.toCharArray();
			for (int i = 0; i < len; i++) {
				char c = ch[i];
				for (char j = 'a'; j <= 'z'; j++) {
					if (j != c) {
						ch[i] = j;
						String newWord = new String(ch);
						if (wordSet.contains(newWord)
								&& (!level.containsKey(newWord) || level.get(newWord) == cur + 1)) {
							if (newWord.equals(endWord)) {
								Node newNode = new Node(newWord);
								node.next.add(newNode);
								newNode.pre = node;
								found = true;
								minLevel = cur;
								List<String> list = new ArrayList<>();
								Node n = newNode;
								while (n != null) {
									list.add(n.word);
									n = n.pre;
								}
								Collections.reverse(list);
								res.add(list);
							} else {
								Node newNode = new Node(newWord);
								node.next.add(newNode);
								newNode.pre = node;
								level.put(newWord, cur + 1);
								queue.offer(newNode);
							}
						}
						ch[i] = c;
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		WordLadderII obj = new WordLadderII();
		List<String> wordList = new ArrayList<>();
		String beginWord = "hit";
		String endWord = "cog";
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");

		// String beginWord = "red";
		// String endWord = "tax";
		// wordList.add("ted");
		// wordList.add("tex");
		// wordList.add("red");
		// wordList.add("tax");
		// wordList.add("tad");
		// wordList.add("den");
		// wordList.add("rex");
		// wordList.add("pee");

		System.out.println(obj.findLadders(beginWord, endWord, wordList));
	}

}
