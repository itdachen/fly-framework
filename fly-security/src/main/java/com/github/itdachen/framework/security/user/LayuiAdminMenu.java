package com.github.itdachen.framework.security.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: LayuiAdmin UI 菜单
 * Created by 王大宸 on 2022-10-22 0:00
 * Created with IntelliJ IDEA.
 */
public class LayuiAdminMenu implements Serializable {
    private static final long serialVersionUID = -6794194573350682589L;

    /**
     * 菜单ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 跳转路径
     */
    private String jump;

    private Integer orderNum;

    /**
     * 子菜单
     */
    private List<LayuiAdminMenu> list = new ArrayList<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<LayuiAdminMenu> getList() {
        return list;
    }

    public void setList(List<LayuiAdminMenu> list) {
        this.list = list;
    }
}
