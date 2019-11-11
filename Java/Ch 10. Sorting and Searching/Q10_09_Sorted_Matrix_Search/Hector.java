package Q10_09_Sorted_Matrix_Search;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	/*
	private static class Coordinate {
		public int row;
		public int col;
		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	*/
	
	public static boolean findElement(int[][] matrix, int elem) {
		return findElement(matrix, elem, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}
	
	private static boolean findElement(int[][] matrix, int elem, int startRow, int endRow, int startCol, int endCol) {
		
		if (startRow > endRow || startCol > endCol) {
			return false;
		}
		
		int rowI = startRow;
		int rowJ = endRow;
		int colI = startCol;
		int colJ = endCol;
		
		Integer midRow = null;
		Integer midCol = null;
		Boolean elemLessThanLastMiddle = null;
		while (rowI <= rowJ && colI <= colJ) {
			midRow = rowI + (rowJ - rowI) / 2;
			midCol = colI + (colJ - colI ) / 2;
			
			if (elem < matrix[midRow][midCol]) {
				rowJ = midRow - 1;
				colJ = midCol - 1;
				elemLessThanLastMiddle = true;
			} else if (elem > matrix[midRow][midCol]) {
				rowI = midRow + 1;
				colI = midCol + 1;
				elemLessThanLastMiddle = false;
			} else {
				return true;
			}
		}
		
		if (elemLessThanLastMiddle) {
			return findElement(matrix, elem, midRow, endRow, startCol, midCol - 1) || findElement(matrix, elem, startRow, midRow - 1, midCol, endCol);
		} else {
			return findElement(matrix, elem, midRow + 1, endRow, startCol, midCol) || findElement(matrix, elem, startRow, midRow, midCol + 1, endCol);
		}
	}
	
	public static void main(String[] args) {
		int M = 10;
		int N = 5;
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = 10 * i + j;
			}
		}
		
		AssortedMethods.printMatrix(matrix);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int v = 10 * i + j;
				System.out.println(v + ": " + findElement(matrix, v));
			}
		}
		
	}
}
