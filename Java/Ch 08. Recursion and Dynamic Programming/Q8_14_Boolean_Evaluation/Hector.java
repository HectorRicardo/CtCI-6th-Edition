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
	
	private static boolean charToBoolean(char operandChar) {
		return operandChar == '1';
	}
	
	private static boolean stringToBoolean(String s) {
		return s.charAt(0) == '1';
	}
	
	public static int countEvalInefficient(String expr, boolean targetResult) {
		if (expr.length() == 1) {
			return stringToBoolean(expr) == targetResult ? 1 : 0;
		}
		
		int totalWays = 0;
		// Loop through operators
		for (int i = 1; i < expr.length(); i += 2) {
			char operator = expr.charAt(i); // assume this will be the last operator of the subexpression
			String leftSubExpr = expr.substring(0, i);
			String rightSubExpr = expr.substring(i + 1);
			
			if (operator == '&') {
				int waysLeftTrue = countEvalInefficient(leftSubExpr, true);
				int waysRightTrue = countEvalInefficient(rightSubExpr, true);
				if (targetResult == true) {
					totalWays += waysLeftTrue * waysRightTrue;
				} else {
					int waysLeftFalse = countEvalInefficient(leftSubExpr, false);
					int waysRightFalse = countEvalInefficient(rightSubExpr, false);
					int waysRightWhatever = waysRightTrue + waysRightFalse;
					totalWays += (waysLeftFalse * waysRightWhatever) + (waysLeftTrue * waysRightFalse);
				}
			} else if (operator == '|') {
				int waysLeftFalse = countEvalInefficient(leftSubExpr, false);
				int waysRightFalse = countEvalInefficient(rightSubExpr, false);
				if (targetResult == true) {
					int waysLeftTrue = countEvalInefficient(leftSubExpr, true);
					int waysRightTrue = countEvalInefficient(rightSubExpr, true);
					int waysRightWhatever = waysRightTrue + waysRightFalse;
					totalWays += (waysLeftTrue * waysRightWhatever) + (waysLeftFalse * waysRightTrue);
				} else {
					totalWays += waysLeftFalse * waysRightFalse;
				}
			} else {
				int waysLeftTrue = countEvalInefficient(leftSubExpr, true);
				int waysLeftFalse = countEvalInefficient(leftSubExpr, false);
				int waysRightTrue = countEvalInefficient(rightSubExpr, true);
				int waysRightFalse = countEvalInefficient(rightSubExpr, false);
				if (targetResult == true) {
					totalWays += (waysLeftTrue * waysRightFalse) + (waysLeftFalse * waysRightTrue);
				} else {
					totalWays += (waysLeftTrue * waysRightTrue) + (waysLeftFalse * waysRightFalse);
				}
				
			}
		}
		
		return totalWays;
		
	}
	
	public static void main(String[] args) {
		System.out.println(countEvalInefficient("0&0&0&1^1|0", true));
	}
}
