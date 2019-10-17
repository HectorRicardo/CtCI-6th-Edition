package Q5_02_Binary_to_String;

public class Hector {
	
	public static String printBinary(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
		
		StringBuffer answer = new StringBuffer(".");
		int digits = 0;
		
		while (num > 0 && digits < 32) {
			num = num * 2;
			int nextDigit = (int)num;
			answer.append(nextDigit);
			num = num - nextDigit;
			digits++;
		}
		
		if (num > 0) {
			return "ERROR";
		}
		
		return answer.toString();
	}
	
	public static String printBinary1(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
		
		StringBuffer answer = new StringBuffer();
		double factor = 1;
		int digits = 0;
		while (num > 0 && digits < 32) {
			factor /= 2;
			answer.append((int)(num / factor));
			num = num % factor;
			digits++;
		}
		if (num > 0) {
			return "ERROR";
		}
		
		return answer.toString();
	}
}
