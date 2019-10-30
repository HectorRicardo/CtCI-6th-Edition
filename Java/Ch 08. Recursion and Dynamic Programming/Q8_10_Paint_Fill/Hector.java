package Q8_10_Paint_Fill;

public class Hector {
	
	private static class Delta {
		boolean nw;
		boolean n;
		boolean ne;
		boolean e;
		boolean se;
		boolean s;
		boolean sw;
		boolean w;
	}
	
	public static void paintFill(int[][] screen, int r, int c, int newColor) {
		
		Delta d = new Delta();
		d.nw = true;
		d.n = true;
		d.ne = true;
		d.e = true;
		d.se = true;
		d.s = true;
		d.sw = true;
		d.w = true;
		paintFill(screen, r, c, newColor, screen[r][c], d);
	}
	
	private static void paintFill(int[][] screen, int r, int c, int newColor, int oldColor, Delta delta) {
		
		if (isOutOfBounds(screen, r, c) || screen[r][c] != oldColor) return;
		
		screen[r][c] = newColor;
		
		if (delta.nw) {
			Delta newDelta = new Delta();
			newDelta.w = true;
			newDelta.nw = true;
			newDelta.n = true;
			paintFill(screen, r-1, c-1, newColor, oldColor, newDelta);
		}
		
		if (delta.n) {
			Delta newDelta = new Delta();
			newDelta.n = true;
			paintFill(screen, r-1, c, newColor, oldColor, newDelta);
		}
		
		if (delta.ne) {
			Delta newDelta = new Delta();
			newDelta.n = true;
			newDelta.ne = true;
			newDelta.e = true;
			paintFill(screen, r-1, c+1, newColor, oldColor, newDelta);
		}
		
		if (delta.e) {
			Delta newDelta = new Delta();
			newDelta.e = true;
			paintFill(screen, r, c+1, newColor, oldColor, newDelta);
		}
		
		if (delta.se) {
			Delta newDelta = new Delta();
			newDelta.e = true;
			newDelta.se = true;
			newDelta.s = true;
			paintFill(screen, r+1, c+1, newColor, oldColor, newDelta);
		}
		
		if (delta.s) {
			Delta newDelta = new Delta();
			newDelta.s = true;
			paintFill(screen, r+1, c, newColor, oldColor, newDelta);
		}
		
		if (delta.sw) {
			Delta newDelta = new Delta();
			newDelta.s = true;
			newDelta.sw = true;
			newDelta.w = true;
			paintFill(screen, r+1, c-1, newColor, oldColor, newDelta);
		}
		
		if (delta.w) {
			Delta newDelta = new Delta();
			newDelta.w = true;
			paintFill(screen, r, c-1, newColor, oldColor, newDelta);
		}
	}
	
	private static boolean isOutOfBounds(int[][] screen, int r, int c) {
		return r < 0 || r >= screen.length || c < 0 || c >= screen[r].length;
	}
	
	public static void main(String[] args) {
		int rows = 10;
		int columns = 10;
		int[][] matrix = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = 0;
			}
		}
		
		paintFill(matrix, 5, 5, 2);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
