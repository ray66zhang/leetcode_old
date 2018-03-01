package two_pointers;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container and n is at least 2.
 *
 * Date: 2018-02-28
 * 
 * Attempted: 1
 * 
 * Percentile: 95.47%
 */
public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		int len = height.length;
		if (len < 2) {
			return 0;
		}
		int start = 0;
		int end = len - 1;
		int max = Math.min(height[start], height[end]) * (end - start);
		while (start < end) {
			int nextStart = start;
			int nextEnd = end;
			if (height[start] >= height[end]) {
				while (start < nextEnd && height[nextEnd] <= height[end]) {
					nextEnd--;
				}
			} else {
				while (nextStart < end && height[nextStart] <= height[start]) {
					nextStart++;
				}
			}
			start = nextStart;
			end = nextEnd;
			int area = Math.min(height[start], height[end]) * (end - start);
			if (area > max) {
				max = area;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		ContainerWithMostWater obj = new ContainerWithMostWater();
		int[] height = { 1, 1 };
		System.out.println(obj.maxArea(height));
	}

}
