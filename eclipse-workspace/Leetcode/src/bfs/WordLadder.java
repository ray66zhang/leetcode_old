package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Medium
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * Only one letter can be changed at a time. Each transformed word must exist in
 * the word list. Note that beginWord is not a transformed word. For example,
 * 
 * Given: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log","cog"] As one shortest transformation is "hit"
 * -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 * Note: Return 0 if there is no such transformation sequence. All words have
 * the same length. All words contain only lowercase alphabetic characters. You
 * may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 *
 * Attempted: 2
 *
 * Percentile: 42.04%
 * 
 * The last word should also be in the word list.
 */
public class WordLadder {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (wordList.size() == 0) {
			return 0;
		}
		int distance = 0;
		Set<String> wordSet = new HashSet<>(wordList);
		int len = beginWord.length();
		Map<String, Integer> map = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		map.put(beginWord, 1);
		while (!queue.isEmpty()) {
			String word = queue.poll();
			distance = map.get(word);
			StringBuilder sb = new StringBuilder(word);
			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);
				for (char j = 'a'; j <= 'z'; j++) {
					if (j != c) {
						sb.setCharAt(i, j);
						String newWord = sb.toString();
						if (wordSet.contains(newWord)) {
							if (newWord.equals(endWord)) {
								return distance + 1;
							}
							if (!map.containsKey(newWord)) {
								queue.offer(newWord);
								map.put(newWord, distance + 1);
							}
						}
						sb.setCharAt(i, c);
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		WordLadder obj = new WordLadder();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		// wordList.add("cog");
		System.out.println(obj.ladderLength(beginWord, endWord, wordList));
	}

}
