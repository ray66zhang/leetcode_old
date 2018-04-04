package backtracking;

/**
 * Medium
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ] word = "ABCCED",
 * -> returns true, word = "SEE", -> returns true, word = "ABCB", -> returns
 * false.
 *
 * Attempted: 2
 *
 * Percentile: 75.61%
 * 
 * Inside DFS, always put found condition first before corner condition.
 */
public class WordSearch {

	public boolean exist(char[][] board, String word) {
		if (word.length() == 0) {
			return false;
		}
		boolean[][] visit = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (dfs(board, word, visit, 0, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs(char[][] board, String word, boolean[][] visit, int index, int row, int col) {
		if (index == word.length()) {
			return true;
		}
		if (index > word.length() || row < 0 || col < 0 || row >= board.length || col >= board[row].length
				|| visit[row][col]) {
			return false;
		}
		boolean found = false;
		if (board[row][col] == word.charAt(index)) {
			visit[row][col] = true;
			found = dfs(board, word, visit, index + 1, row + 1, col) || dfs(board, word, visit, index + 1, row - 1, col)
					|| dfs(board, word, visit, index + 1, row, col + 1)
					|| dfs(board, word, visit, index + 1, row, col - 1);
			visit[row][col] = false;
		}
		return found;
	}

	public static void main(String[] args) {
		WordSearch obj = new WordSearch();
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String word = "ABCB";
		System.out.println(obj.exist(board, word));
	}

}
