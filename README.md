How to extend your own graph for your needs from BaseGraph

1. Implement all abstract methods
2. Remember to add offset which comes from updateOffset method to all your drawings, which are moved while scrolling
3. Do not forget about isAnimatedFinished variable, because it blocks clicks and scrolling if it set to false
4. Return appropriate view types in getSupportedViewTypes
5. If your graph supports scrolling you should assign realW variable to real width of your graph, because background class uses it in order to correctly draw arrows
6. And the trickiest part. When setCallback method is called, passed parameter is saved into callbackToBack and then you must use callback to update itemWidth and leftBorder. You can see how it's done in the example

	```java
	@Override
	public void setCallback(CallbackDrawGraph callbackDrawGrapg) {
	    super.setCallback(callbackDrawGrapg);
	    measure();
	}
	@Override
	public void measure() {
		if (w != 0 && h != 0 && callbackToBack != null) {
	        callbackToBack.updateDrawByQ(150, 50);
	        // Other measurments here
	    }
	}
	```
	
7. When you succesfully called updateDrawByQ on callback reference you may use various methods, which are provided by callback. For example, sendInvalidate to perform animations and scrollTo if you need to move left or right on your graph 
8. Finally, don't forget to assign a enum name to your graph in TypeGraph and initialize it in setDataGraph method in NewBackground 
```java
case YOUR_GRAPH_NAME:
    graph = new YourGraph(getContext(), attributeSet);
```


Basic extending with no drawing and only zoom feature
```java
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
    public ViewType[] getSupportedViewTypes() {
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
```

Using your brand new graph in main activity
```java
    graphViewGroup = (GraphViewGroup) findViewById(R.id.mygroop);

	ModelDataGraph data = new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_DAY_ITEM_DAY)
    graphViewGroup.setDataGraphAndInfo(data, TypeGraph.YOUR_GRAPH);
```
