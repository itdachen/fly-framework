package com.github.itdachen.framework.context.constants;

import com.github.itdachen.framework.context.BizContextHandler;

/**
 * 市州/区县/分部 等 部门格式
 * 例如: 52 01 02 03 04 23 00
 * 格式: 省(52) 市州(01) 区县(02) 乡镇(03) 街道办/村/序号(04) 职能(23) 备用(00)
 * <p>
 * 总部和省级部门格式:
 * 例如: 52 00 23 00 00 00 00
 * 格式: 省(52) 占位符(00) 职能(23)  占位符(00) 占位符(00) 占位符(00) 占位符(00)
 *
 * @author 王大宸
 * @date 2024/12/27 21:18
 */
public class DeptLevelFormatConstants {

    /**
     * 总部 00 00 00 00 00 00 00
     */
    public static final String ROOT_LEVEL_FORMAT = "1000%s00000000";


    /**
     * 省级
     */
    public static final String PROV_LEVEL_FORMAT = "%s00%s00000000";

    /**
     * 市/州级
     */
    public static final String CITY_LEVEL_FORMAT = "%s00000000%s00";

    /**
     * 区/县级
     */
    public static final String COUNT_LEVEL_FORMAT = "%s000000%s00";

    /**
     * 乡镇/街道级
     */
    public static final String STREET_LEVEL_FORMAT = "%s0000%s00";

    /**
     * 村/社区级
     */
    public static final String VILLAGE_LEVEL_FORMAT = "%s00%s00";

    /***
     * 生成部门代码
     *
     * @author 王大宸
     * @date 2024/12/27 21:55
     * @param areaCode 行政编码
     * @param level    部门层级
     * @param funcCode 职能编码
     * @return java.lang.String
     */
    public static String obtainDeptId(String areaCode, String level, String funcCode) {
        if (DeptLevelConstants.ROOT_LEVEL.equals(level)) {
            return String.format(ROOT_LEVEL_FORMAT, funcCode);
        }
        if (DeptLevelConstants.PROV_LEVEL.equals(level)) {
            return String.format(PROV_LEVEL_FORMAT, areaCode, funcCode);
        }
        if (DeptLevelConstants.CITY_LEVEL.equals(level)) {
            return String.format(CITY_LEVEL_FORMAT, areaCode, funcCode);
        }
        if (DeptLevelConstants.COUNT_LEVEL.equals(level)) {
            return String.format(COUNT_LEVEL_FORMAT, areaCode, funcCode);
        }
        if (DeptLevelConstants.STREET_LEVEL.equals(level)) {
            return String.format(STREET_LEVEL_FORMAT, areaCode, funcCode);
        }
        if (DeptLevelConstants.VILLAGE_LEVEL.equals(level)) {
            return String.format(VILLAGE_LEVEL_FORMAT, areaCode, funcCode);
        }
        return null;
    }


}
