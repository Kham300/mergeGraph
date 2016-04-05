package com.lardis.ivan.testcustomview.View.ViewGraph.myEnum;

/**
 * enum тип графика

 * столбцы графика дни, задняя сетка недели - MESH_WEEK_ITEM_DAY
 * столбцы графика недели, задняя сетка недели - MESH_WEEK_ITEM_WEEK
 * столбцы графика дни, задняя сетка дни - MESH_DAY_ITEM_DAY
 *  столбцы графика месяцы, задняя сетка месяцы - MESH_MONTH_ITEM_MONTH
 * столбцы графика месяцы, задняя сетка недели - MESH_MONTH_ITEM_WEEK

 */

public enum enumTypeViewGraph {
    /**
     * столбцы графика дни, задняя сетка недели
     */
      MESH_WEEK_ITEM_DAY
    ,
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
}
