package binary_search;

/**
 * Medium
 * 
 * There is a list of sorted integers from 1 to n. Starting from left to right,
 * remove the first number and every other number afterward until you reach the
 * end of the list.
 * 
 * Repeat the previous step again, but this time from right to left, remove the
 * right most number and every other number from the remaining numbers.
 * 
 * We keep repeating the steps again, alternating left to right and right to
 * left, until a single number remains.
 * 
 * Find the last number that remains starting with a list of length n.
 * 
 * Example:
 * 
 * Input: n = 9, 1 2 3 4 5 6 7 8 9 2 4 6 8 2 6 6
 * 
 * Output: 6
 *
 * Attempted: 1
 *
 * Percentile: 30.5%
 */
public class EliminationGame {

	public int lastRemaining(int n) {
		int head = 1;
		int step = 1;
		boolean left = true;
		int remaining = n;
		while (remaining > 1) {
			if (left || remaining % 2 == 1) {
				head += step;
			}
			left = !left;
			step *= 2;
			remaining /= 2;
		}
		return head;
	}

	public static void main(String[] args) {
		EliminationGame obj = new EliminationGame();
		int n = 3;
		System.out.println(obj.lastRemaining(n));

	}

}
