package two_pointers;

/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 * 
 * Date: 2018-02-28
 * 
 * Attempt:1
 *
 * Percentile: 24.20%
 */
public class Candy {

	public int candy(int[] ratings) {
		int len = ratings.length;
		if (len < 2) {
			return len;
		}
		int[] candy = new int[len];
		for (int i = 0; i < len; i++) {
			candy[i] = 1;
		}
		for (int i = 0; i < len - 1; i++) {
			if (ratings[i + 1] > ratings[i]) {
				candy[i + 1] = Math.max(candy[i] + 1, candy[i + 1]);
			}
		}
		for (int j = len - 1; j > 0; j--) {
			if (ratings[j - 1] > ratings[j]) {
				candy[j - 1] = Math.max(candy[j] + 1, candy[j - 1]);
			}
		}
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += candy[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		Candy obj = new Candy();
		int[] ratings = { 1, 2, 2, 3, 4, 5, 4, 1 };
		System.out.println(obj.candy(ratings));

	}

}
