package com.github.itdachen.framework.context.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lay-vue 树结构
 *
 * @author 王大宸
 * @date 2024-12-24 10:21
 */
public class LayTreeNode implements Serializable {
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
     * 是否展开
     */
    private Boolean spread = false;

    /**
     * 子项
     */
    private List<LayTreeNode> children = new ArrayList<>();

    public LayTreeNode(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public LayTreeNode(String id, String title, Boolean spread, List<LayTreeNode> children) {
        this.id = id;
        this.title = title;
        this.spread = spread;
        this.children = children;
    }

    public LayTreeNode(String id, String title, List<LayTreeNode> children) {
        this.id = id;
        this.title = title;
        this.spread = false;
        this.children = children;
    }

    public LayTreeNode(String id, String title, Boolean spread) {
        this.id = id;
        this.title = title;
        this.spread = spread;
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

    public List<LayTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<LayTreeNode> children) {
        this.children = children;
    }



}
