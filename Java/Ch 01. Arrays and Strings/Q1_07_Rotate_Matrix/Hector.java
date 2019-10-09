package Q1_07_Rotate_Matrix;

import CtCILibrary.AssortedMethods;

public class Hector {
	
	static void rotate(int[][] image) {
		int N = image.length;
		
		// i = iterate diagonally into the center of the matrix
		for (int layer = 0; layer < N / 2; layer++) {
			// j = iterate column, from start of submatrix to end of submatrix - 1
			for (int j = layer; j < N - 1 - layer; j++) {
				int top = image[layer][j];
				image[layer][j] = image[N-1-j][layer];
				image[N-1-j][layer] = image[N-1-layer][N-1-j];
				image[N-1-layer][N-1-j] = image[j][N-1-layer];
				image[j][N-1-layer] = top;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}
}