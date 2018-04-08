package dfs_bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Hard
 * 
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of non-empty words
 * from the dictionary, where words are sorted lexicographically by the rules of
 * this new language. Derive the order of letters in this language. Example 1:
 * Given the following words in dictionary, [ "wrt", "wrf", "er", "ett", "rftt"
 * ] The correct order is: "wertf". Example 2: Given the following words in
 * dictionary, [ "z", "x" ] The correct order is: "zx". Example 3: Given the
 * following words in dictionary, [ "z", "x", "z" ] The order is invalid, so
 * return "". Note: You may assume all letters are in lowercase. You may assume
 * that if a is a prefix of b, then a must appear before b in the given
 * dictionary. If the order is invalid, return an empty string. There may be
 * multiple valid order of letters, return any one of them is fine.
 *
 */
public class AlienDictionary {
	public boolean cycle = false;

	public String alienOrder(List<String> words) {
		int len = words.size();
		if (len == 0 || (len == 1 && words.get(0).length() > 1)) {
			return "";
		}
		Map<Character, Set<Character>> graph = new HashMap<>();
		for (int i = 0; i < len - 1; i++) {
			String a = words.get(i);
			String b = words.get(i + 1);
			if (a.equals(b) || b.startsWith(a)) {
				continue;
			}
			for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
				char ca = a.charAt(j);
				char cb = b.charAt(j);
				if (ca != cb) {
					Set<Character> set;
					if (graph.containsKey(ca)) {
						set = graph.get(ca);
					} else {
						set = new HashSet<>();
					}
					set.add(cb);
					graph.put(ca, set);
				}
			}
		}
		Stack<Character> stack = new Stack<>();
		Set<Character> visited = new HashSet<>();
		Set<Character> visiting = new HashSet<>();
		for (char c : graph.keySet()) {
			if (!visited.contains(c)) {
				dfs(graph, stack, visited, visiting, c);
			}
		}
		if (cycle) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			return sb.toString();
		}
	}

	public void dfs(Map<Character, Set<Character>> graph, Stack<Character> stack, Set<Character> visited,
			Set<Character> visiting, char src) {
		if (visiting.contains(src)) {
			cycle = true;
			return;
		}
		if (cycle || visited.contains(src)) {
			return;
		}
		visiting.add(src);
		if (graph.containsKey(src)) {
			Set<Character> set = graph.get(src);
			for (char dst : set) {
				dfs(graph, stack, visited, visiting, dst);
			}
		}
		visiting.remove(src);
		visited.add(src);
		stack.add(src);
	}

	public static void main(String[] args) {
		AlienDictionary obj = new AlienDictionary();
		List<String> words = new ArrayList<>();
		words.add("wrt");
		words.add("wrf");
		words.add("er");
		words.add("ett");
		words.add("rftt");
		System.out.println(obj.alienOrder(words));

	}

}
