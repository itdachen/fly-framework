package com.github.itdachen.framework.context.enums;

/**
 * 页面类型
 *
 * @author 王大宸
 * @date 2025/9/8 21:59
 */
public enum PageTypeEnum {

    HOME("后台管理", "HOME"),
    DASHBOARD("仪表盘", "DASHBOARD"),
    VIS("可视化大屏", "VIS"),
    MONITOR("监控", "MONITOR"),

    INDEX("信息管理", "INDEX"),
    SAVE("新增", "SAVE"),
    UPDATE("编辑", "UPDATE"),
    VIEW("查看", "VIEW"),
    COPE_WITH("应对", "COPE_WITH"),
    OTHER("其他", "OTHER"),

    ;

    PageTypeEnum(String title, String code) {
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
