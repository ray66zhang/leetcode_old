package dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and
 * the 6 LEDs on the bottom represent the minutes (0-59).
 * 
 * Each LED represents a zero or one, with the least significant bit on the
 * right.
 * 
 * 
 * For example, the above binary watch reads "3:25".
 * 
 * Given a non-negative integer n which represents the number of LEDs that are
 * currently on, return all possible times the watch could represent.
 * 
 * Example:
 * 
 * Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04",
 * "0:08", "0:16", "0:32"] Note: The order of output does not matter. The hour
 * must not contain a leading zero, for example "01:00" is not valid, it should
 * be "1:00". The minute must be consist of two digits and may contain a leading
 * zero, for example "10:2" is not valid, it should be "10:02".
 * 
 * Attempted: 1
 * 
 * Percentile: 0%
 * 
 * Better solution: enumerate all possible hour + min cominations, and check how
 * many digits are there. If the digits match the target, print the time.
 */
public class BinaryWatch {

	public List<String> readBinaryWatch(int num) {
		Set<String> res = new HashSet<>();
		int[] arr = new int[10];
		dfs(arr, num, res);
		return new ArrayList<>(res);
	}

	public void dfs(int[] arr, int left, Set<String> res) {
		if (left == 0) {
			System.out.println(Arrays.toString(arr));
			toTime(arr, res);
		}
		if (left < 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				arr[i] = 1;
				dfs(arr, left - 1, res);
				arr[i] = 0;
			}
		}
	}

	public void toTime(int[] arr, Set<String> res) {
		int hour = arr[3];
		if (arr[0] == 1) {
			hour += 8;
		}
		if (arr[1] == 1) {
			hour += 4;
		}
		if (arr[2] == 1) {
			hour += 2;
		}
		if (hour > 11) {
			return;
		}
		int min = arr[9];
		if (arr[4] == 1) {
			min += 32;
		}
		if (arr[5] == 1) {
			min += 16;
		}
		if (arr[6] == 1) {
			min += 8;
		}
		if (arr[7] == 1) {
			min += 4;
		}
		if (arr[8] == 1) {
			min += 2;
		}
		if (min > 59) {
			return;
		}
		if (min < 10) {
			res.add(Integer.toString(hour) + ":0" + Integer.toString(min));
		} else {
			res.add(Integer.toString(hour) + ":" + Integer.toString(min));
		}
	}

	public static void main(String[] args) {
		BinaryWatch obj = new BinaryWatch();
		System.out.println(obj.readBinaryWatch(1));

	}

}
