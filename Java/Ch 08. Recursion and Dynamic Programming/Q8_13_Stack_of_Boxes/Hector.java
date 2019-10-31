package Q8_13_Stack_of_Boxes;

import java.util.List;

public class Hector {
	
	public static int createStack2(List<Box> boxes) {
		boxes.sort((box1, box2) -> box2.height - box1.height);
		int maxStackHeightWithBoxAtBottom[] = new int[boxes.size()];
		return getMaxHeight(boxes, null, 0, maxStackHeightWithBoxAtBottom);
	}
	
	private static int getMaxHeight(List<Box> boxes, Box previousBottom, int currentBottomIdx, int[] maxStackHeightWithBoxAtBottom) {
		
		// Base case: empty stack
		if (currentBottomIdx == boxes.size()) {
			return 0;
		}
		
		Box currentBottom = boxes.get(currentBottomIdx);
		
		// Max height with current box at bottom
		int maxHeightWithCurrentBottom = 0;
		if (previousBottom == null || currentBottom.canBeAbove(previousBottom)) {
			if (maxStackHeightWithBoxAtBottom[currentBottomIdx] == 0) {
				maxStackHeightWithBoxAtBottom[currentBottomIdx] = currentBottom.height + getMaxHeight(boxes, currentBottom, currentBottomIdx + 1, maxStackHeightWithBoxAtBottom);
			}
			maxHeightWithCurrentBottom = maxStackHeightWithBoxAtBottom[currentBottomIdx];
		}

		// Max Height without current box
		int maxHeightWithoutCurrentBottom = getMaxHeight(boxes, previousBottom, currentBottomIdx + 1, maxStackHeightWithBoxAtBottom);
		
		// Get max height
		return Math.max(maxHeightWithCurrentBottom, maxHeightWithoutCurrentBottom);
	}
	
	public static int createStack(List<Box> boxes) {
		boxes.sort((box1, box2) -> box1.height - box2.height);
		int maxStackHeightWithBoxAtBottom[] = new int[boxes.size()];
		int maxHeightWhicheverBottom = 0;
		for (int i = 0; i < boxes.size(); i++) {
			Box currentBottom = boxes.get(i);
			for (int j = i - 1; j >= 0; j--) {
				Box previousBottom = boxes.get(j);
				if (currentBottom.canBeUnder(previousBottom)) {
					maxStackHeightWithBoxAtBottom[i] = Math.max(maxStackHeightWithBoxAtBottom[i], maxStackHeightWithBoxAtBottom[j]);
				}
			}
			maxStackHeightWithBoxAtBottom[i] += currentBottom.height;
			maxHeightWhicheverBottom = Math.max(maxHeightWhicheverBottom, maxStackHeightWithBoxAtBottom[i]);
		}
 		return maxHeightWhicheverBottom;
	}
}
