package com.github.itdachen.framework.context.tree.lay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TreeNode
 *
 * @author 王大宸
 * @date 2024-12-24 14:47
 */
public class TreeNode implements Serializable {
    private static final long serialVersionUID = 415337031177257051L;


    /**
     * ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 图标
     */
    private String icon;


    /**
     * 是否展开
     */
    private Boolean spread = false;

    /**
     * 子项
     */
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public TreeNode(String id, String title, Boolean spread, List<TreeNode> children) {
        this.id = id;
        this.title = title;
        this.spread = spread;
        this.children = children;
    }

    public TreeNode(String id, String title, List<TreeNode> children) {
        this.id = id;
        this.title = title;
        this.spread = false;
        this.children = children;
    }

    public TreeNode(String id, String title, Boolean spread) {
        this.id = id;
        this.title = title;
        this.spread = spread;
    }


    /* ID */
    public TreeNode id(String id) {
        this.id = id;
        return this;
    }

    /*  标题 */
    public TreeNode title(String title) {
        this.title = title;
        return this;
    }

    /* 是否展开 */
    public TreeNode spread(Boolean spread) {
        this.spread = spread;
        return this;
    }

    /*  子项  */
    public TreeNode children(List<TreeNode> children) {
        this.children = children;
        return this;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
