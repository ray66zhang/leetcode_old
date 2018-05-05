package stack;

import java.util.Stack;

/**
 * Medium
 * 
 * Given a nested list of integers represented as a string, implement a parser
 * to deserialize it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Note: You may assume that the string is well-formed:
 * 
 * String is non-empty. String does not contain white spaces. String contains
 * only digits 0-9, [, - ,, ]. Example 1:
 * 
 * Given s = "324",
 * 
 * You should return a NestedInteger object which contains a single integer 324.
 * Example 2:
 * 
 * Given s = "[123,[456,[789]]]",
 * 
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123. 2. A nested list containing two elements:
 * i. An integer containing value 456. ii. A nested list with one element: a. An
 * integer containing value 789.
 *
 * Attempted: 1
 *
 * Percentile: 49.56%
 */
public class MiniParser {

	public NestedInteger deserialize(String s) {
		int len = s.length();
		if (!s.startsWith("[")) {
			NestedInteger ni = new NestedInteger(Integer.parseInt(s));
			return ni;
		}
		NestedInteger root = new NestedInteger();
		Stack<NestedInteger> stack = new Stack<>();
		stack.push(root);
		for (int i = 1; i < len - 1; i++) {
			char c = s.charAt(i);
			if (c == '[') {
				NestedInteger ni = new NestedInteger();
				stack.peek().add(ni);
				stack.push(ni);
			} else if (c == ',') {
				continue;
			} else if (c == ']') {
				stack.pop();
			} else {
				int start = i;
				while (i < len - 1 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
					i++;
				}
				int end = i;
				stack.peek().add(new NestedInteger(Integer.parseInt(s.substring(start, end + 1))));
			}
		}
		return stack.pop();
	}

}
