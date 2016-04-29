How to extend your own graph for your needs from BaseGraph:

	1. Implement all abstract methods
	2. Remember to add offset which comes from updateOffset method to all your drawings, which are moved while scrolling
	3. Do not forget about isAnimatedFinished variable, which blocks clicks and scrolling if it set to false
	4. Return appropriate graph types in getSupportedGraphTypes
	5. And the last but not least. When setCallback method is called, then you should use callback to update itemWidth and leftBorder. You can see how it's done in the example below

	
