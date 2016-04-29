How to extend your own graph for your needs from BaseGraph

1. Implement all abstract methods
2. Remember to add offset which comes from updateOffset method to all your drawings, which are moved while scrolling
3. Do not forget about isAnimatedFinished variable, which blocks clicks and scrolling if it set to false
4. Return appropriate graph types in getSupportedGraphTypes
5. And the last but not least. When setCallback method is called, then you should use callback to update itemWidth and leftBorder. You can see how it's done in the example below

Basic extending with no drawing and only zoom feature

	public class ExampleGraph extends BaseGraph {
	
	    public ExampleGraph(Context context,  AttributeSet attrs) {
	        this.isAnimationFinished = true;
	    }
	
	
	    @Override
	    public void setData(ModelDataGraph modelDataGraph) {
	
	    }
	
	    @Override
	    public void updateOffset(float v) {
	
	    }
	
	    @Override
	    public void click(int n) {
	
	    }
	
	    @Override
	    public void setCallback(CallbackDrawGraph callbackDrawGrapg) {
	        super.setCallback(callbackDrawGrapg);
	        measure();
	    }
	
	    @Override
	    public ViewType[] getSupportedGraphTypes() {
	        return new ViewType[]{ViewType.MESH_DAY_ITEM_DAY,
	                ViewType.MESH_WEEK_ITEM_WEEK,
	                ViewType.MESH_MONTH_ITEM_MONTH,
	                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
	                ViewType.MESH_MONTH_ITEM_WEEK};
	    }
	
	    @Override
	    public boolean getZoomPermission() {
	        return true;
	    }
	
	    @Override
	    public boolean getUsesBlockInfo() {
	        return false;
	    }
	
	    @Override
	    public void drawGraph(Canvas canvas) {
	
	    }
	
	    @Override
	    public void drawOnLeftPanel(Canvas canvas) {
	
	    }
	
	    @Override
	    public void measure() {
	        if (w != 0 && h != 0 && callbackToBack != null)
	            callbackToBack.updateDrawByQ(150, 50);
	    }
	
	    @Override
	    public void measureWithOffset() {
	
	    }
	}
