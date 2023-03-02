package com.itdachen.framework.assets.tree;

import java.io.Serializable;

/**
 * Description: 树结构返回对象
 * Created by 王大宸 on 2021-11-22 21:55
 * Created with IntelliJ IDEA.
 */
public class ZTreeNode implements Serializable {
    private static final long serialVersionUID = 4830091403700336794L;
    public static final String ROOT_ID = "1";
    public static final String ROOT_PARENT_ID = "0";

    public static final String zTreeOpenIcon = "/assets/zTree/css/zTreeStyle/img/diy/1_open.png";
    public static final String zTreeCloseIcon = "/assets/zTree/css/zTreeStyle/img/diy/1_close.png";

    public static final String zTreeBuMenIcon = "/assets/zTree/css/zTreeStyle/img/bumen.png";
    public static final String zTreeJiGuoIcon = "/assets/zTree/css/zTreeStyle/img/jigou.png";

    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 是否展開
     */
    private Boolean open;

    /**
     * 是否选中
     */
    private Boolean checked;

    /**
     * 展开时图标
     */
    private String iconOpen;
    /**
     * 关闭时图标
     */
    private String iconClose;

    /**
     * 类型
     */
    private String type;

    /**
     * 级别
     */
    private String grade;

    /**
     * 备注
     */
    private String remarks;

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

    public ZTreeNode(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.parentId = builder.parentId;
        this.open = builder.open;
        this.checked = builder.checked;
        this.iconOpen = builder.iconOpen;
        this.iconClose = builder.iconClose;
        this.type = builder.type;
        this.grade = builder.grade;
        this.remarks = builder.remarks;
        this.attr1 = builder.attr1;
        this.attr2 = builder.attr2;
        this.attr3 = builder.attr3;
        this.attr4 = builder.attr4;
        this.attr5 = builder.attr5;
    }

    public ZTreeNode() {
    }

    public static class Builder {
        private String id;
        private String name;
        private String parentId;
        private Boolean open;
        private Boolean checked;
        private String iconOpen;
        private String iconClose;
        private String type;
        private String grade;
        private String remarks;
        private String attr1;
        private String attr2;
        private String attr3;
        private String attr4;
        private String attr5;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Builder open(Boolean open) {
            this.open = open;
            return this;
        }

        public Builder checked(Boolean checked) {
            this.checked = checked;
            return this;
        }

        public Builder openIcon(String iconOpen) {
            this.iconOpen = iconOpen;
            return this;
        }

        public Builder closeIcon(String iconClose) {
            this.iconClose = iconClose;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder attr1(String attr1) {
            this.attr1 = attr1;
            return this;
        }

        public Builder attr2(String attr2) {
            this.attr2 = attr2;
            return this;
        }

        public Builder attr3(String attr3) {
            this.attr3 = attr3;
            return this;
        }

        public Builder attr4(String attr4) {
            this.attr4 = attr4;
            return this;
        }

        public Builder attr5(String attr5) {
            this.attr5 = attr5;
            return this;
        }

        public ZTreeNode build() {
            return new ZTreeNode(this);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
