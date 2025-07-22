package com.github.itdachen.framework.core.enums;

/**
 * 消息分类
 *
 * @author 王大宸
 * @date 2025-07-22 10:45
 */
public enum MsgClazzEnum {

    /**
     * 数据导出
     */
    EXCEL_EXP {
        @Override
        public String value() {
            return "EXCEL_EXP";
        }

        @Override
        public String title() {
            return "数据导出";
        }
    },

    /**
     * 数据导入
     */
    EXCEL_IMP {
        @Override
        public String value() {
            return "EXCEL_IMP";
        }

        @Override
        public String title() {
            return "数据导入";
        }
    },


    ;


    /**
     * 获取值
     */
    public abstract String value();

    /**
     * 获取文本
     */
    public abstract String title();


}
