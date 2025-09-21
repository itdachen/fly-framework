package com.github.itdachen.framework.context.constants;

/**
 * 部门等级
 * 00-总部/10-省级/20-市州级/30-区县级/40-乡镇/街道级
 *
 * @author 王大宸
 * @date 2024/5/6 23:03
 */
public class DeptLevelConstants {

    /**
     * 总部
     */
    public static final String ROOT_LEVEL = "00";

    /**
     * 省级
     */
    public static final String PROV_LEVEL = "10";

    /**
     * 市/州级
     */
    public static final String CITY_LEVEL = "20";

    /**
     * 区/县级
     */
    public static final String COUNT_LEVEL = "30";

    /**
     * 乡镇/街道级
     */
    public static final String TOWN_LEVEL = "40";

    /**
     * 村/社区级
     */
    public static final String VILLAGE_LEVEL = "50";


    /***
     * 根据当前部门层级获取下一级部门层级
     *
     * @author 王大宸
     * @date 2024/12/27 23:22
     * @param level 部门层级
     * @return java.lang.String
     */
    public static String findNextLevel(String level) {
        if (ROOT_LEVEL.equals(level)) {
            return PROV_LEVEL;
        }
        if (PROV_LEVEL.equals(level)) {
            return CITY_LEVEL;
        }
        if (CITY_LEVEL.equals(level)) {
            return COUNT_LEVEL;
        }
        if (COUNT_LEVEL.equals(level)) {
            return TOWN_LEVEL;
        }
        return VILLAGE_LEVEL;
    }


}
