package com.github.itdachen.framework.context.enums;

/**
 * 操作日志类型
 *
 * @author 王大宸
 * @date 2025/9/8 21:43
 */
public enum LogTypeEnum {

    SAVE("新增", "SAVE"),
    UPDATE("编辑", "UPDATE"),
    REMOVE("删除", "REMOVE"),
    VIEW("查看", "VIEW"),
    PAGE("分页查询", "PAGE"),
    GET("查询", "GET"),
    JUMP("跳转", "JUMP"),
    GRANT_AUTH("授权", "GRANT_AUTH"),
    IMPORT("导入", "IMPORT"),
    EXPORT("导出", "EXPORT"),
    FORCE_RETURN("强退", "FORCE_RETURN"),
    GENERATOR_CODE("生成代码", "GENERATOR_CODE"),
    CLEAR("清空", "CLEAR"),
    VIEW_PERMISSION("显示权限", "VIEW_PERMISSION"),
    COPE_WITH("应对", "COPE_WITH"),
    MONITOR("监控", "MONITOR"),
    VALID_FLAG("状态变更", "VALID_FLAG"),
    OTHER("其他", "OTHER"),

    ;


    LogTypeEnum(String title, String code) {
        this.title = title;
        this.code = code;
    }

    // 日志名称
    private final String title;
    // 日志代码
    private final String code;

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

}
