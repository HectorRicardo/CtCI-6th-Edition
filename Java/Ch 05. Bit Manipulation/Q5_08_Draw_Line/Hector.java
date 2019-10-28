package Q5_08_Draw_Line;

public class Hector {
	
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int offset = y * (width / 8);
		int startingBytePos = offset + (x1 / 8);
		int endingBytePos = offset + (x2 / 8);
		
		int firstBitPos = 7 - (x1 % 8);
		int lastBitPos = 7 - (x2 % 8);
		
		if (startingBytePos == endingBytePos) {
			// set 1's from lastBitPos to firstBitPos in only byte
			int mask = ((1 << (firstBitPos - lastBitPos + 1)) - 1) << lastBitPos;
			screen[startingBytePos] |= (byte)mask;
			
		} else {
			// setting first byte
			// set 1's from 0 to firstBitPos (inclusive)
			//screen[startingBytePos] |= (1 << (firstBitPos + 1)) - 1;
			screen[startingBytePos] |= -1 >>> (7 - firstBitPos);
			
			// set middle bytes
			for (int i = startingBytePos + 1; i < endingBytePos; i++) {
				screen[i] = -128;
			}
			
			// setting last byte
			// set 1's from 7 to lastBitPos (inclusive)
			screen[endingBytePos] |= -1 << lastBitPos;
		}
	}
	
	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}
	
	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			char c = ((b >> i) & 1) == 1 ? '1' : '_';
			System.out.print(c);
		}
	}
	
	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c+=8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		int width = 8 * 1;
		int height = 1;
		for (int r = 0; r < height; r++) {
			for (int c1 = 0; c1 < width; c1++) {
				for (int c2 = c1; c2 < width; c2++) {
					byte[] screen = new byte[width * height / 8];

					System.out.println("row: " + r + ": " + c1 + " -> " + c2);
					drawLine(screen, width, c1, c2, r);
					printScreen(screen, width);
					System.out.println("\n\n");
				}
			}
		}

	}

}
