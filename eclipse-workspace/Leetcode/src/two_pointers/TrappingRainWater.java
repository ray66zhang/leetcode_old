package two_pointers;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * Date: 2018-02-28
 * 
 * Attempted: 1
 *
 * Percentile: 37.35%
 */
public class TrappingRainWater {

	public int trap(int[] height) {
		int len = height.length;
		if (len < 2) {
			return 0;
		}
		int max = -1;
		int maxIndex = -1;
		for (int i = 0; i < len; i++) {
			if (height[i] > max) {
				max = height[i];
				maxIndex = i;
			}
		}
		int water = 0;
		int cur = height[0];
		for (int i = 1; i < maxIndex; i++) {
			if (height[i] > cur) {
				cur = height[i];
			} else {
				water += cur - height[i];
			}
		}
		cur = height[len - 1];
		for (int j = len - 2; j > maxIndex; j--) {
			if (height[j] > cur) {
				cur = height[j];
			} else {
				water += cur - height[j];
			}
		}
		return water;
	}

	public static void main(String[] args) {
		TrappingRainWater obj = new TrappingRainWater();
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(obj.trap(height));

	}

}
