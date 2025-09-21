package com.github.itdachen.framework.context.constants;

/**
 * 市州/区县/分部 等 部门格式
 * 例如: 52 01 102 03 04 23 00
 * 格式: 省(52) 市州(01) 区县(02) 乡镇(03) 镇/村/街道办/序号(016) 职能(123) 备用(00, 例如: 1部,2部,3部等)
 * <p>
 * 总部和省级部门格式:
 * 例如: 52 00 102 00 00 00 00
 * 格式: 省(52) 占位符(00) 职能(123)  占位符(00) 占位符(00) 占位符(016) 占位符(00)
 * <p>
 * 以下备注, 以部门职能代码 102 为例
 *
 * @author 王大宸
 * @date 2024/12/27 21:18
 */
public class DeptLevelFormatConstants {

    /**
     * 总部 10 00 102 00 00 00 00
     */
    public static final String ROOT_LEVEL_FORMAT = "1000%s000000000";


    /**
     * 省级 52 00 102 00 000 00 00
     */
    public static final String PROV_LEVEL_FORMAT = "%s00%s000000000";

    /**
     * 市/州级 5201 00 00 000 102 00
     * 省(52) 市州(01) 区县(02) 乡镇(03) 镇/村/街道办/序号(04) 职能(123) 备用(00, 例如: 1部,2部,3部等)
     */
    public static final String CITY_LEVEL_FORMAT = "%s0000000%s00";

    /**
     * 区/县级 520102 00 000 102 00
     * 省(52) 市州(01) 区县(02) 乡镇(03) 镇/村/街道办/序号(04) 职能(123) 备用(00, 例如: 1部,2部,3部等)
     */
    public static final String COUNT_LEVEL_FORMAT = "%s00000%s00";

    /**
     * 乡镇/街道级 52010203 000 102 00
     * 省(52) 市州(01) 区县(02) 乡镇(03) 镇/村/街道办/序号(04) 职能(123) 备用(00, 例如: 1部,2部,3部等)
     */
    public static final String TOWN_LEVEL_FORMAT = "%s00%s00";

    /**
     * 村/社区级 52010203004 102 00
     * 省(52) 市州(01) 区县(02) 乡镇(03) 镇/村/街道办/序号(04) 职能(123) 备用(00, 例如: 1部,2部,3部等)
     */
    public static final String VILLAGE_LEVEL_FORMAT = "%s%s00";

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
        if (DeptLevelConstants.TOWN_LEVEL.equals(level)) {
            return String.format(TOWN_LEVEL_FORMAT, areaCode, funcCode);
        }
        if (DeptLevelConstants.VILLAGE_LEVEL.equals(level)) {
            return String.format(VILLAGE_LEVEL_FORMAT, areaCode, funcCode);
        }
        return null;
    }


}
