package two_pointers;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 * 
 * Note: You may assume that nums1 has enough space (size that is greater or
 * equal to m + n) to hold additional elements from nums2. The number of
 * elements initialized in nums1 and nums2 are m and n respectively.
 *
 * Attempted: 2
 *
 * Percentile: 1.14%
 */
public class MergeSortedArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0) {
			return;
		}
		if (m == 0) {
			for (int i = 0; i < n; i++) {
				nums1[i] = nums2[i];
			}
			return;
		}
		int p1 = m - 1;
		int p2 = n - 1;
		int cur = m + n - 1;
		while (p1 >= 0 && p2 >= 0) {
			int n1 = nums1[p1];
			int n2 = nums2[p2];
			if (n1 >= n2) {
				nums1[cur--] = n1;
				if (p1 == 0) {
					for (int i = 0; i <= p2; i++) {
						nums1[i] = nums2[i];
					}
					break;
				} else {
					p1--;
				}
			} else {
				nums1[cur--] = n2;
				if (p2 == 0) {
					break;
				} else {
					p2--;
				}
			}
		}
	}

	public static void main(String[] args) {
		MergeSortedArray obj = new MergeSortedArray();
		int[] nums1 = { 2, 0 };
		int[] nums2 = { 1 };
		obj.merge(nums1, 1, nums2, 1);
		System.out.println(Arrays.toString(nums1));
	}

}
