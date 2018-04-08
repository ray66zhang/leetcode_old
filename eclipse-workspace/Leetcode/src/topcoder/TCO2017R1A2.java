package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You want to make a lot of sandwiches. However, you only have a limited amount
 * of cheese. The only piece of cheese you currently have is a rectangular block
 * with dimensions A, B, and C.
 * 
 * You can cut the block of cheese into smaller pieces. You are only allowed to
 * cut as follows: Each cut must divide one block of cheese into two smaller
 * blocks of cheese. Each cut must be parallel to one of the faces of the piece
 * you are cutting. Each of the two smaller blocks must have integer dimensions.
 * 
 * 
 * When placing a block of cheese onto a piece of bread, the cheese is always
 * rotated so that its shortest side is vertical. In other words, suppose you
 * have a block of cheese with dimensions (X,Y,Z) such that X ≥ Y ≥ Z. If you
 * place this block onto a piece of bread, its surface area will be X*Y and its
 * thickness will be Z.
 * 
 * A block of cheese is called a good slice if and only if its thickness is
 * greater than or equal to S. (Recall that the thickness of a block is the
 * length of its shortest side.)
 * 
 * You can cut your block of cheese into arbitrarily many pieces, as long as you
 * follow the rules given above. Your goal is to cut the block in such a way
 * that maximizes the total surface area of all good slices among the pieces.
 * Note that your way of cutting may also produce some pieces that are not good
 * slices, but those pieces won't contribute to the surface area. The number of
 * good slices does not matter, we only care about their surface. Different good
 * slices may have different dimensions.
 * 
 * You are given the ints A, B, C, and S. Return the maximum total surface area
 * of good slices you can get.
 * 
 * Definition
 * 
 * Class: CheeseSlicing Method: totalArea Parameters: int, int, int, int
 * Returns: int Method signature: int totalArea(int A, int B, int C, int S) (be
 * sure your method is public)
 * 
 * 
 * Constraints - A, B and C will be between 1 and 100, inclusive. - S will be
 * between 1 and 10, inclusive.
 *
 *
 * 5 5 5 2 Returns: 58 One possible sequence of cuts: 5x5x5 -> 2x5x5 + 3x5x5
 * 3x5x5 -> 3x2x5 + 3x3x5 3x3x5 -> 3x3x2 + 3x3x3 After these three cuts we have
 * four pieces: 2x5x5, 3x2x5, 3x3x2, and 3x3x3. Each of these is a good slice.
 * Their total surface area is 5*5 + 3*5 + 3*3 + 3*3.
 */
public class TCO2017R1A2 {

	public int totalArea(int A, int B, int C, int S) {
		List<Integer> input = new ArrayList<>();
		input.add(A);
		input.add(B);
		input.add(C);
		Collections.sort(input, Collections.reverseOrder());
		if (input.get(2) < S) {
			return 0;
		}
		int[][][] dp = new int[input.get(0) + 1][input.get(1) + 1][input.get(2) + 1];
		dp[S][S][S] = S * S;
		List<Integer> initList = new ArrayList<>();
		initList.add(S);
		initList.add(S);
		initList.add(S);
		Set<List<Integer>> curSet = new HashSet<>();
		curSet.add(initList);
		for (int i = S * 3 + 1; i <= A + B + C; i++) {
			Set<List<Integer>> nextSet = getComb(curSet, input);
			for (List<Integer> list : nextSet) {
				int max = list.get(0) * list.get(1);
				for (int j = 0; j < 3; j++) {
					int size = list.get(j);
					for (int k = S; k <= size - S; k++) {
						List<Integer> l1 = new ArrayList<>(list);
						List<Integer> l2 = new ArrayList<>(list);
						l1.set(j, k);
						l2.set(j, size - k);
						Collections.sort(l1, Collections.reverseOrder());
						Collections.sort(l2, Collections.reverseOrder());
						max = Math.max(max, dp[l1.get(0)][l1.get(1)][l1.get(2)] + dp[l2.get(0)][l2.get(1)][l2.get(2)]);
					}
				}
				dp[list.get(0)][list.get(1)][list.get(2)] = max;
			}
			curSet = nextSet;
		}
		return dp[input.get(0)][input.get(1)][input.get(2)];
	}

	public Set<List<Integer>> getComb(Set<List<Integer>> curSet, List<Integer> input) {
		Set<List<Integer>> nextSet = new HashSet<>();
		for (List<Integer> list : curSet) {
			for (int i = 0; i < 3; i++) {
				if (list.get(i) < input.get(i)) {
					List<Integer> newList = new ArrayList<>(list);
					newList.set(i, list.get(i) + 1);
					Collections.sort(newList, Collections.reverseOrder());
					nextSet.add(newList);
				}
			}
		}
		return nextSet;
	}

	public int totalArea2(int A, int B, int C, int S) {
		if (S > Math.min(Math.min(A, B), C)) {
			return 0;
		}
		int maxLen = Math.max(Math.max(A, B), C);
		int[][][] dp = new int[maxLen + 1][maxLen + 1][maxLen + 1];
		for (int i = 0; i <= maxLen; i++) {
			for (int j = 0; j <= maxLen; j++) {
				for (int k = 0; k <= maxLen; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		return search(A, B, C, S, dp);
	}

	public int search(int A, int B, int C, int S, int[][][] dp) {
		int[] arr = new int[3];
		arr[0] = A;
		arr[1] = B;
		arr[2] = C;
		Arrays.sort(arr);
		A = arr[0];
		B = arr[1];
		C = arr[2];
		if (S > A) {
			return 0;
		}
		if (dp[A][B][C] >= 0) {
			return dp[A][B][C];
		}
		int max = B * C;
		for (int i = S; i <= A - S; i++) {
			max = Math.max(max, search(i, B, C, S, dp) + search(A - i, B, C, S, dp));
		}
		for (int i = S; i <= B - S; i++) {
			max = Math.max(max, search(A, i, C, S, dp) + search(A, B - i, C, S, dp));
		}
		for (int i = S; i <= C - S; i++) {
			max = Math.max(max, search(A, B, i, S, dp) + search(A, B, C - i, S, dp));
		}
		dp[A][B][C] = max;
		return max;
	}

	public static void main(String[] args) {
		TCO2017R1A2 obj = new TCO2017R1A2();
		int A = 100;
		int B = 100;
		int C = 100;
		int S = 10;
		System.out.println(obj.totalArea2(A, B, C, S));
	}

}
