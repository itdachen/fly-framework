package com.github.itdachen.framework.core.enums;

/**
 * 通知类型
 *
 * @author 剑鸣秋朔
 * @date 2025-07-22 10:43
 */
public enum NoticeTypeEnum {

    /**
     * 消息
     */
    MSG {
        @Override
        public String value() {
            return "MSG";
        }

        @Override
        public String title() {
            return "消息";
        }
    },

    /**
     * 通知
     */
    NOTICE {
        @Override
        public String value() {
            return "NOTICE";
        }

        @Override
        public String title() {
            return "通知";
        }
    },

    /**
     * 代办
     */
    TODO {
        @Override
        public String value() {
            return "TODO";
        }

        @Override
        public String title() {
            return "代办";
        }
    },

    /**
     * 代办
     */
    OTHER {
        @Override
        public String value() {
            return "OTHER";
        }

        @Override
        public String title() {
            return "其他";
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
