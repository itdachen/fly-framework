package com.github.itdachen.framework.context.models;

import java.io.Serializable;
import java.util.List;

/**
 * DictLabel
 *
 * @author 剑鸣秋朔
 * @date 2024-10-15 9:16
 */
public class LabelNode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 显示
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 是否选择
     */
    private String checked;

    /**
     * 备用属性1
     */
    private String attr1;


    /**
     * 备用属性2
     */
    private String attr2;


    /**
     * 备用属性3
     */
    private String attr3;


    /**
     * 备用属性4
     */
    private String attr4;


    /**
     * 备用属性5
     */
    private String attr5;

    private List<LabelNode> children;


    public LabelNode() {
    }

    public LabelNode(String label, String value, String checked) {
        this.label = label;
        this.value = value;
        this.checked = checked;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public List<LabelNode> getChildren() {
        return children;
    }

    public void setChildren(List<LabelNode> children) {
        this.children = children;
    }
}
