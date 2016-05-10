package com.lardis.ivan.diagramslibrary.graphview.base;

/**
 * enum тип графика
 * столбцы графика дни, задняя сетка недели - MESH_WEEK_ITEM_DAY_PERIOD_MONTH
 * столбцы графика недели, задняя сетка недели - MESH_WEEK_ITEM_WEEK
 * столбцы графика дни, задняя сетка дни - MESH_DAY_ITEM_DAY
 * столбцы графика месяцы, задняя сетка месяцы - MESH_MONTH_ITEM_MONTH
 * столбцы графика месяцы, задняя сетка недели - MESH_MONTH_ITEM_WEEK
 */

public enum ViewType {
    /**
     * столбцы графика дни, задняя сетка недели
     */
    MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
    /**
     * столбцы графика недели, задняя сетка недели
     */
    MESH_WEEK_ITEM_WEEK,
    /**
     * столбцы графика дни, задняя сетка дни
     */
    MESH_DAY_ITEM_DAY,
    /**
     * столбцы графика месяцы, задняя сетка месяцы
     */
    MESH_MONTH_ITEM_MONTH,
    /**
     * столбцы графика месяцы, задняя сетка недели
     */
    MESH_MONTH_ITEM_WEEK;


    public static ViewType[] getAllTypes() {
        return new ViewType[]{ViewType.MESH_DAY_ITEM_DAY,
                ViewType.MESH_WEEK_ITEM_WEEK,
                ViewType.MESH_MONTH_ITEM_MONTH,
                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
                ViewType.MESH_MONTH_ITEM_WEEK};
    }

    public static ViewType[] getBasicTypes() {
        return new ViewType[]{ViewType.MESH_DAY_ITEM_DAY,
                ViewType.MESH_WEEK_ITEM_WEEK,
                ViewType.MESH_MONTH_ITEM_MONTH};
    }

    public static ViewType[] getAdvancedTypes() {
        return new ViewType[] {ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
            ViewType.MESH_MONTH_ITEM_WEEK};
    }

}