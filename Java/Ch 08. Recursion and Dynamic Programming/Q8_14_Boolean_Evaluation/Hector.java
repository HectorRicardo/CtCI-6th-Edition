package Q8_14_Boolean_Evaluation;

public class Hector {
	
	public static int countEval(String expr, boolean result) {
		if (expr.isEmpty()) {
			throw new RuntimeException("Expression cannot be empty");
		}
		
		int operands = operandsLength(expr);
		
		// trueMatrix[i, j] represents all the ways in which subexpression from operands i to j (both inclusive) can form true
		int[][] trueMatrix = new int[operands][operands];
		
		// falseMatrix[i, j] represents all the ways in which subexpression from operands i to j (both inclusive) can form false
		int[][] falseMatrix = new int[operands][operands];
		
		// Base case: subexpression of one operand
		for (int i = 0; i < operands; i++) {
			boolean currentOperand = getOperand(expr, i);
			int currentOperandInt = booleanToInt(currentOperand);
			trueMatrix[i][i] = currentOperandInt;
			falseMatrix[i][i] = 1 - currentOperandInt;
		}
		
		// General Case: solution for all subexpression of size numOperands
		for (int numOperands = 2; numOperands <= operands; numOperands++) {
			for (int startingOperandIdx = 0; startingOperandIdx <= operands - numOperands; startingOperandIdx++) {
				int endingOperandIdx = startingOperandIdx + numOperands - 1;
				
				// loop through operators inside the subexpression
				for (int operatorIdx = startingOperandIdx; operatorIdx < endingOperandIdx; operatorIdx++) {
					char operator = getOperator(expr, operatorIdx);
					
					// Left sub-sub-expression beginning and ending indices (both inclusive)
					int firstLeftSideOperandIdx = startingOperandIdx;
					int lastLeftSideOperandIdx = operatorIdx;
					
					// Right sub-sub-expression beginning and ending indices (both inclusive)
					int firstRightSideOperandIdx = operatorIdx + 1;
					int lastRightSideOperandIdx = endingOperandIdx;
					
					// ways
					int waysLeftIsTrue = trueMatrix[firstLeftSideOperandIdx][lastLeftSideOperandIdx];
					int waysLeftIsFalse = falseMatrix[firstLeftSideOperandIdx][lastLeftSideOperandIdx];
					//int waysLeftIsWhatever = waysLeftIsTrue + waysLeftIsFalse;
					
					int waysRightIsTrue = trueMatrix[firstRightSideOperandIdx][lastRightSideOperandIdx];
					int waysRightIsFalse = falseMatrix[firstRightSideOperandIdx][lastRightSideOperandIdx];
					int waysRightIsWhatever = waysRightIsTrue + waysRightIsFalse;
					
					if (operator == '&') {
						trueMatrix[startingOperandIdx][endingOperandIdx] += waysLeftIsTrue * waysRightIsTrue;
						falseMatrix[startingOperandIdx][endingOperandIdx] += (waysLeftIsFalse * waysRightIsWhatever) + (waysLeftIsTrue * waysRightIsFalse); 
					} else if (operator == '|') {
						trueMatrix[startingOperandIdx][endingOperandIdx] += (waysLeftIsTrue * waysRightIsWhatever) + (waysLeftIsFalse * waysRightIsTrue);
						falseMatrix[startingOperandIdx][endingOperandIdx] += waysLeftIsFalse * waysRightIsFalse; 
					} else {
						trueMatrix[startingOperandIdx][endingOperandIdx] += (waysLeftIsTrue * waysRightIsFalse) + (waysLeftIsFalse * waysRightIsTrue);
						falseMatrix[startingOperandIdx][endingOperandIdx] += (waysLeftIsFalse * waysRightIsFalse) + (waysLeftIsTrue * waysRightIsTrue); 
					}
				}
			}
		}
		
		int[][] matrix = result == true ? trueMatrix : falseMatrix;
		return matrix[0][operands - 1];
	}
	
	private static int operandsLength(String expr) {
		return expr.length() / 2 + 1;
	}
	
	private static boolean getOperand(String expr, int idx) {
		char operandChar = expr.charAt(2 * idx);
		return charToBoolean(operandChar);
	}
	
	private static char getOperator(String expr, int idx) {
		return expr.charAt(2 * idx + 1);
	}
	
	private static int booleanToInt(boolean value) {
		return value ? 1 : 0;
	}
	
	private static boolean charToBoolean(int operandChar) {
		return operandChar == '1';
	}
	
	public static void main(String[] args) {
		System.out.println(countEval("0&0&0&1^1|0", true));
	}
}
