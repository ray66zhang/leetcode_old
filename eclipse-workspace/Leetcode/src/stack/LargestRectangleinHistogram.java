package stack;

import java.util.Stack;

/**
 * Hard
 * 
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example, Given heights = [2,1,5,6,2,3], return 10.
 *
 * 
 * 
 */
public class LargestRectangleinHistogram {

	class Bar {
		int height;
		int position;

		public Bar(int height, int position) {
			this.height = height;
			this.position = position;
		}
	}

	public int largestRectangleArea(int[] heights) {
		int max = 0;
		Stack<Bar> stack = new Stack<>();
		for (int i = 0; i < heights.length; i++) {
			int height = heights[i];
			if (stack.isEmpty() || height > stack.peek().height) {
				Bar b = new Bar(height, i);
				stack.push(b);
			} else if (height < stack.peek().height) {
				while (!stack.isEmpty() && stack.peek().height > height) {
					stack.pop();
				}
				int area = height * (i - stack.peek().position);
				if (area > max) {
					max = area;
				}
			}
		}
		if (!stack.isEmpty()) {
			int area = heights.length * (stack.peek().height);
			if (area > max) {
				max = area;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		LargestRectangleinHistogram obj = new LargestRectangleinHistogram();
		int[] heights = { 2, 1, 5, 6, 2, 3 };
		System.out.println(obj.largestRectangleArea(heights));
	}

}
