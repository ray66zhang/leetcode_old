package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Node {
	int val;
	Set<Node> next;
	Set<Node> pre;

	public Node(int val) {
		this.val = val;
		this.next = new HashSet<>();
		this.pre = new HashSet<>();
	}
}

public class Contest75 {

	public boolean rotateString(String A, String B) {
		int lenA = A.length();
		int lenB = B.length();
		if (lenA != lenB) {
			return false;
		}
		if (lenA == 0) {
			return true;
		}
		for (int i = 0; i < lenA; i++) {
			String prefix = A.substring(0, i);
			String suffix = A.substring(i);
			String newString = suffix + prefix;
			if (newString.equals(B)) {
				return true;
			}
		}
		return false;
	}

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Node> visit = new HashMap<>();
		int edges = 0;
		for (int i = 0; i < graph.length; i++) {
			Node cur;
			if (visit.containsKey(i)) {
				cur = visit.get(i);
			} else {
				cur = new Node(i);
				visit.put(i, cur);
			}
			for (int j = 0; j < graph[i].length; j++) {
				int val = graph[i][j];
				Node nextNode;
				if (visit.containsKey(val)) {
					nextNode = visit.get(val);
				} else {
					nextNode = new Node(val);
					visit.put(val, nextNode);
				}
				cur.next.add(nextNode);
				nextNode.pre.add(cur);
				edges++;
			}
		}
		if (edges == 0) {
			return res;
		}
		Node head = visit.get(0);
		while (head.pre.size() > 0) {
			for (Node n : head.pre) {
				head = n;
				break;
			}
		}
		List<Integer> path = new ArrayList<>();
		path.add(head.val);
		dfs(res, path, head);
		System.out.println(head.val);
		return res;
	}

	public void dfs(List<List<Integer>> res, List<Integer> path, Node node) {
		if (node.next.size() == 0) {
			List<Integer> list = new ArrayList<>(path);
			res.add(list);
			return;
		}
		Set<Node> nextNodes = node.next;
		for (Node nextNode : nextNodes) {
			path.add(nextNode.val);
			dfs(res, path, nextNode);
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args) {
		Contest75 obj = new Contest75();
		int[][] test = new int[][] { { 1, 2 }, { 3 }, { 3 }, {} };
		System.out.println(obj.allPathsSourceTarget(test));
	}

}
