package stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Medium
 * 
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,1,2,1,1].
 * 
 * Example 2: Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,4,6].
 *
 * Attempted: 1
 *
 * Percentile: 88.09%
 * 
 * Put iterator inside the stack. All the Collections have .iterator() method.
 */
interface NestedInteger {

	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a nested
	// list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {

	public Stack<Iterator<NestedInteger>> stack = new Stack<>();
	public Integer nextInt;

	public NestedIterator(List<NestedInteger> nestedList) {
		Iterator<NestedInteger> iter = nestedList.iterator();
		stack.push(iter);
		nextInt = null;
	}

	@Override
	public Integer next() {
		Integer cur = nextInt;
		nextInt = null;
		return cur;
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			Iterator<NestedInteger> iter = stack.peek();
			if (iter.hasNext()) {
				NestedInteger ni = iter.next();
				if (ni.isInteger()) {
					nextInt = ni.getInteger();
					return true;
				} else {
					stack.push(ni.getList().iterator());
				}
			} else {
				stack.pop();
			}
		}
		return false;
	}

}
