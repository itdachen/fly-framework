package com.itdachen.framework.log.constants;

/**
 * Description:
 * Created by 王大宸 on 2023/01/17 16:38
 * Created with IntelliJ IDEA.
 */
public class ApiLogConstant {

    /**
     * 操作失败
     */
    public final static String IS_ERR = "0";
    public final static String IS_ERR_MSG = "操作失败";

    /**
     * 操作成功
     */
    public final static String IS_OK = "1";
    public final static String IS_OK_MSG = "操作成功";

    /**
     * 操作异常
     */
    public final static String IS_EX = "2";
    public final static String IS_EX_MSG = "操作异常";

    /**
     * 其他异常
     */
    public final static String IS_OTHER = "3";
    public final static String IS_OTHER_MSG = "其他异常";

    /**
     * 分页异常
     */
    public final static String IS_PAGE_EX = "4";
    public final static String IS_PAGE_EX_MSG = "分页异常";

    /***
     * 根据转入的值,获取明文
     *
     * @author 王大宸
     * @date 2021/12/1 16:56
     * @param value
     * @return java.lang.String
     */
    public static String getOperStatus(String value) {
        switch (value) {
            case IS_ERR:
                return IS_ERR_MSG;
            case IS_OK:
                return IS_OK_MSG;
            case IS_EX:
                return IS_EX_MSG;
            case IS_OTHER:
                return IS_OTHER_MSG;
            case IS_PAGE_EX:
                return IS_PAGE_EX_MSG;
            default:
                return "未知错误";
        }
    }

}
