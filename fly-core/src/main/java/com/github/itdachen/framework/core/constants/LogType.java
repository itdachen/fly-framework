package com.github.itdachen.framework.core.constants;

/**
 * Description:
 * Created by 王大宸 on 2021-12-01 16:52
 * Created with IntelliJ IDEA.
 */
public class LogType {

    /**
     * 其他操作
     */
    public final static String OTHER = "0";
    public final static String OTHER_MSG = "其他操作";

    /**
     * 新增操作
     */
    public final static String SAVE = "1";
    public final static String SAVE_MSG = "新增";

    /**
     * 修改操作
     */
    public final static String UPDATE = "2";
    public final static String UPDATE_MSG = "修改";

    /**
     * 删除操作
     */
    public final static String REMOVE = "3";
    public final static String REMOVE_MSG = "删除";

    /**
     * 获取数据操作
     */
    public final static String GET_DATA = "4";
    public final static String GET_DATA_MSG = "查询";

    /**
     * 页面跳转
     */
    public final static String JUMP = "5";
    public final static String JUMP_MSG = "页面跳转";

    /**
     * 授权
     */
    public final static String GRANT = "6";
    public final static String GRANT_MSG = "授权";

    /**
     * 导入
     */
    public final static String IMPORT = "7";
    public final static String IMPORT_MSG = "导入";

    /**
     * 导出
     */
    public final static String EXPORT = "8";
    public final static String EXPORT_MSG = "导出";


    /**
     * 强退
     */
    public final static String FORCE_RETURN = "9";
    public final static String FORCE_RETURN_MSG = "强退";

    /**
     * 生成代码
     */
    public final static String GENERATOR = "10";
    public final static String GENERATOR_MSG = "生成代码";

    /**
     * 清空
     */
    public final static String CLEAN = "11";
    public final static String CLEAN_MSG = "清空";

    /**
     * 显示权限
     */
    public static final String VIEW_PERMISSION = "12";
    public final static String VIEW_PERMISSION_MSG = "显示权限";

    /**
     * 分页查询
     */
    public final static String GET_PAGE_DATA = "13";
    public final static String GET_PAGE_DATA_MSG = "分页查询";


    public static String getLogType(String value) {
        switch (value) {
            case GET_PAGE_DATA:
                return GET_PAGE_DATA_MSG;
            case OTHER:
                return OTHER_MSG;
            case SAVE:
                return SAVE_MSG;
            case UPDATE:
                return UPDATE_MSG;
            case REMOVE:
                return REMOVE_MSG;
            case GET_DATA:
                return GET_DATA_MSG;
            case JUMP:
                return JUMP_MSG;
            case GRANT:
                return GRANT_MSG;
            case IMPORT:
                return IMPORT_MSG;
            case FORCE_RETURN:
                return FORCE_RETURN_MSG;
            case GENERATOR:
                return GENERATOR_MSG;
            case CLEAN:
                return CLEAN_MSG;
            case EXPORT:
                return EXPORT_MSG;
            case VIEW_PERMISSION:
                return VIEW_PERMISSION_MSG;
            default:
                return "未知操作";
        }
    }

}
