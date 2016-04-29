## Гайд - как добавить свой график, отнаследовавшись от BaseGraph

1. Реализовать все абстрактные методы
2. Не забывать добавлять offset ко всем своим рисующимся элементам,которые должны скроллится
3. Помнить про переменную isAnimatedFinished, потому что она блокирует клики и скроллы юзера, если она установлена в false
4. Возвращать поддерживаемые типы отображения в getSupportedViewTypes
5. Если ваш график поддерживает скроллинг, то вам обязательно нужно присвоить значение перепенной realW, потому что класс NewBackground использует её для корректного отображение стрелок. Например, я это делаю в measure 
6. И самая сложная часть. Когда вызывается метод setCallback, то передаваемый параметр сохраняется в callBackToBack и вам обязательно нужно использовать метод этого коллбэка updateDrawByQ, чтобы сообщить ему itemWidth и leftBorder. Например, это можно сделать так 

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
>>>>>>> 5077ad2d0f73cb3a3a6f01f888772a4e9b1a4ab7
	    }
	}
	```
	
7. Когда вы успешно вызвали updateDrawByQ, вы сможете использовать методы, которые есть в этом коллбэке. Например, sendInvalidate выполняет invalidate вью для анимации, а scrollTo может скроллить ваш график прямо из кода 
8. В конце концов, не забудьте присвоить своему графику имя и добавить его в TypeGraph и вызвать конструктор своего графика в NewBackground 
```java
case YOUR_GRAPH_NAME:
    graph = new YourGraph(getContext(), attributeSet);
```

## Пример кода

####Обычный график, которые ничего не рисует и поддерживает лупу
```java
public class ExampleGraph extends BaseGraph {

    public ExampleGraph(Context context,  AttributeSet attrs) {
        this.isAnimationFinished = true;
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
		// Логика сохранения данных
    }

    @Override
    public void updateOffset(float v) {
		// Обновление offset'a
    }

    @Override
    public void click(int n) {
		// Обработка нажатия 
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
		// Основная часть рисования происходит здесь
    }

    @Override
    public void drawOnLeftPanel(Canvas canvas) {
		// Рисование на закрепленной слева панели
		// Здесь не нужно использовать offset
    }

    @Override
    public void measure() {
        if (w != 0 && h != 0 && callbackToBack != null) {
            callbackToBack.updateDrawByQ(150, 50);
        }
    }

    @Override
    public void measureWithOffset() {
		// Измерения, которые используют offset
		// Осторожно - этот метод вызывается в onDraw
    }
}
```

####Использование вашего нового графика в составе GraphViewGroup
```java
    graphViewGroup = (GraphViewGroup) findViewById(R.id.mygroop);

	ModelDataGraph data = new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_DAY_ITEM_DAY);
    graphViewGroup.setDataGraphAndInfo(data, TypeGraph.YOUR_GRAPH);
```
