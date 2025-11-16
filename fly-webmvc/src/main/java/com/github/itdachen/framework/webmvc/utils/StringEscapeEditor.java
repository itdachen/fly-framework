package com.github.itdachen.framework.webmvc.utils;

import java.beans.PropertyEditorSupport;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2022-03-14 16:45
 * Created with IntelliJ IDEA.
 */
public class StringEscapeEditor extends PropertyEditorSupport {

    public StringEscapeEditor() {
        super();
    }

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String value = clearXss(text);
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

    /**
     * 处理字符转义
     *
     * @param value
     * @return
     */
    private String clearXss(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

}
