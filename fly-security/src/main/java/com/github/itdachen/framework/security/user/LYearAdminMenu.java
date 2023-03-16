package com.github.itdachen.framework.security.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 光年后台模块菜单返回结果集
 * Created by 王大宸 on 2023/02/02 17:23
 * Created with IntelliJ IDEA.
 */
public class LYearAdminMenu implements Serializable {
    private static final long serialVersionUID = 866436189338719527L;

    private String id;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 权限编码
     */
    private String perms;

    /**
     * 跳转地址
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 打开方式: _parent/覆盖原来的路径, _blank/打开新的浏览器选项卡,
     */
    private String target;

    /**
     * 子菜单
     */
    private List<LYearAdminMenu> children = new ArrayList<>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<LYearAdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<LYearAdminMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "LYearAdminMenu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", perms='" + perms + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", children=" + children +
                '}';
    }
}
