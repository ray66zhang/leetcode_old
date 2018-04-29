package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We are given head, the head node of a linked list containing unique integer
 * values.
 * 
 * We are also given the list G, a subset of the values in the linked list.
 * 
 * Return the number of connected components in G, where two values are
 * connected if they appear consecutively in the linked list.
 * 
 * Example 1:
 * 
 * Input: head: 0->1->2->3 G = [0, 1, 3] Output: 2 Explanation: 0 and 1 are
 * connected, so [0, 1] and [3] are the two connected components. Example 2:
 * 
 * Input: head: 0->1->2->3->4 G = [0, 3, 1, 4] Output: 2 Explanation: 0 and 1
 * are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two
 * connected components. Note:
 * 
 * If N is the length of the linked list given by head, 1 <= N <= 10000. The
 * value of each node in the linked list will be in the range [0, N - 1]. 1 <=
 * G.length <= 10000. G is a subset of all values in the linked list.
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

class CarNode {
	int pos;
	int speed;
	int step;

	CarNode(int pos, int speed, int step) {
		this.pos = pos;
		this.speed = speed;
		this.step = step;
	}
}

public class Contest80 {

	public int racecar(int target) {
		Queue<CarNode> queue = new LinkedList<>();
		CarNode root = new CarNode(0, 1, 0);
		queue.offer(root);
		Set<String> visit = new HashSet<>();
		while (!queue.isEmpty()) {
			CarNode node = queue.poll();
			if (node.pos == target) {
				return node.step;
			}
			String key = node.pos + " " + node.speed;
			if (visit.contains(key) || node.pos > target * 2 || node.pos < 0) {
				continue;
			}
			visit.add(key);
			CarNode nodeF = new CarNode(node.pos + node.speed, node.speed * 2, node.step + 1);
			CarNode nodeR = new CarNode(node.pos, node.speed > 0 ? -1 : 1, node.step + 1);
			queue.offer(nodeF);
			queue.offer(nodeR);
		}
		return -1;
	}

    class CarInfo{
        int pos, speed;
        public CarInfo(int p, int s) {
            pos = p;
            speed = s;
        }
    }
    
    public int racecar2(int target) {
        Set<String> visited = new HashSet<>();
        String begin = 0 + "/" + 1;
        visited.add(begin);
        Queue<CarInfo> queue = new LinkedList<>();
        queue.add(new CarInfo(0,1));
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                CarInfo cur = queue.poll();
                if (cur.pos == target) return level;
                String s1 = (cur.pos + cur.speed) + "/" + (cur.speed * 2);
                String s2 = cur.pos + "/" + (cur.speed > 0 ? -1 : 1);
                if (Math.abs(cur.pos + cur.speed - target) < target && !visited.contains(s1)) {
                    visited.add(s1);
                    queue.add(new CarInfo(cur.pos + cur.speed, cur.speed * 2));
                }
                if (Math.abs(cur.pos - target) < target && !visited.contains(s2)) {
                    visited.add(s2);
                    queue.add(new CarInfo(cur.pos, cur.speed > 0 ? -1 : 1));
                }
            }
            
            level++;
        }
        return -1;
    }
    
	public int numComponents(ListNode head, int[] G) {
		Set<Integer> set = new HashSet<>();
		for (int inum : G) {
			set.add(inum);
		}
		int components = 0;
		while (head != null) {
			int val = head.val;
			if (!set.contains(val)) {
				head = head.next;
			} else {
				components++;
				while (head != null && set.contains(head.val)) {
					set.remove(head.val);
					head = head.next;
				}
			}
		}
		return components;
	}

	public List<String> ambiguousCoordinates(String S) {
		Set<String> res = new HashSet<>();
		String s = S.substring(1, S.length() - 1);
		int len = s.length();
		for (int i = 1; i < len; i++) {
			String sl = s.substring(0, i);
			String sr = s.substring(i);
			Set<String> setL = construct(sl);
			Set<String> setR = construct(sr);
			if (setL.size() > 0 && setR.size() > 0) {
				for (String s1 : setL) {
					for (String s2 : setR) {
						res.add("(" + s1 + ", " + s2 + ")");
					}
				}
			}
		}
		return new ArrayList<>(res);
	}

	public Set<String> construct(String s) {
		Set<String> set = new HashSet<>();
		if (s.length() == 0) {
			return set;
		}
		if (s.length() == 1) {
			set.add(s);
			return set;
		}
		if (s.charAt(0) != '0') {
			set.add(s);
		}
		if (s.charAt(s.length() - 1) == '0') {
			return set;
		}
		if (s.charAt(0) == '0') {
			set.add("0." + s.substring(1));
			return set;
		}
		for (int i = 1; i < s.length(); i++) {
			set.add(s.substring(0, i) + "." + s.substring(i));
		}
		return set;
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		String res = "";
		int len = paragraph.length();
		if (len == 0) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			char c = paragraph.charAt(i);
			if (c == '!' || c == '?' || c == '\'' || c == ',' || c == ';' || c == '.' || c == '/') {
				continue;
			} else {
				sb.append(c);
			}
		}
		Set<String> set = new HashSet<>();
		for (String s : banned) {
			set.add(s);
		}
		String[] strs = sb.toString().toLowerCase().split(" ");
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if (set.contains(str)) {
				continue;
			}
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		int max = 0;
		for (String s : map.keySet()) {
			int v = map.get(s);
			if (v > max) {
				max = v;
				res = s;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Contest80 obj = new Contest80();
		int target = 10000;
		System.out.println(obj.racecar(target));
	}

}
