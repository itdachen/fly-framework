package com.github.itdachen.framework.security.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: ok-admin 菜单
 * Created by 王大宸 on 2021-01-26 15:16
 * Created with IntelliJ IDEA.
 */
public class OkAdminMenu implements Serializable {
    private static final long serialVersionUID = -6794194573350682589L;

    private String id;

    /**
     * 上级id
     */
    private String parentId;


    /**
     * 菜单名称
     */
    private String title;


    /**
     * 跳转路径
     */
    private String href;


    /**
     * 字体
     */
    private String fontFamily;


    /**
     * 图标
     */
    private String icon;


    /**
     * 是否展开
     */
    private String spread;


    /**
     * 排序
     */
    private Integer orderNum;


    /**
     * 是否选中
     */
    private String isCheck;


    /**
     * 打开方式
     */
    private String target;


    /**
     * 菜单类型
     */
    private String menuType;


    /**
     * 菜单状态
     */
    private String visible;


    /**
     * 权限标识
     */
    private String perms;


    /**
     * 备注
     */
    private String remark;


    /**
     * 创建人
     */
    private String createUser;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 更新人
     */
    private String updateUser;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /***
     * 子菜单
     */
    private List<OkAdminMenu> children;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getSpread() {
        return spread;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getVisible() {
        return visible;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPerms() {
        return perms;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public List<OkAdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<OkAdminMenu> children) {
        this.children = children;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("title", getTitle())
                .append("href", getHref())
                .append("fontFamily", getFontFamily())
                .append("icon", getIcon())
                .append("spread", getSpread())
                .append("orderNum", getOrderNum())
                .append("isCheck", getIsCheck())
                .append("target", getTarget())
                .append("menuType", getMenuType())
                .append("visible", getVisible())
                .append("perms", getPerms())
                .append("remark", getRemark())
                .append("createUser", getCreateUser())
                .append("createTime", getCreateTime())
                .append("updateUser", getUpdateUser())
                .append("updateTime", getUpdateTime())
                .append("children", getChildren())
                .toString();
    }

}
