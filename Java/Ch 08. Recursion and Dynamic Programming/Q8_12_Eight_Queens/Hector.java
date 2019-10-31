package Q8_12_Eight_Queens;

import java.util.ArrayList;
import java.util.List;

public class Hector {
	
	public static List<int[]> placeQueens(int gridSize) {
		List<int[]> solution = new ArrayList<>();
		placeQueens(gridSize, new int[gridSize], 0, solution);
		return solution;
	}
	
	private static void placeQueens(int gridSize, int[] currentArrangement, int row, List<int[]> arrangements) {
		if (row == gridSize) {
			arrangements.add(currentArrangement.clone());
		} else {
			for (int col = 0; col < gridSize; col++) {
				if (newColumnIsValid(row, col, currentArrangement)) {
					currentArrangement[row] = col;
					placeQueens(gridSize, currentArrangement, row + 1, arrangements);
				}
			}
		}
	}
	
	private static boolean newColumnIsValid(int newRow, int newCol, int[] currentArrangement) {
		for (int otherRow = 0; otherRow < newRow; otherRow++) {
			int otherCol = currentArrangement[otherRow];
			if (otherCol == newCol || areOnSameDiagonal(otherRow, otherCol, newRow, newCol)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areOnSameDiagonal(int row1, int col1, int row2, int col2) {
		return Math.abs(row1 - row2) == Math.abs(col1 - col2);
	}
	
	public static void main(String[] args) {
		List<int[]> arrangements = placeQueens(8);
		System.out.println(arrangements.size());
	}
}
