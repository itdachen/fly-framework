package com.itdachen.framework.context.permission;

import java.io.Serializable;

/**
 * Description:
 * Created by 王大宸 on 2022-10-16 13:48
 * Created with IntelliJ IDEA.
 */
public class PermissionInfo implements Serializable {
    private static final long serialVersionUID = 5547194437832239204L;

    private String id;

    /**
     * 权限标识, 资源编码
     */
    private String permission;

    /**
     * 请求地址
     */
    private String uri;

    /**
     * 请求方法: POST, GET, PUT, DELETE
     */
    private String method;

    /**
     * 请求类型, 资源类型: button, url
     */
    private String type;

    /**
     * 操作名称(资源名称): 新增, 修改, 删除, 查询
     */
    private String name;

    /**
     * 菜单名称: 用户管理, 菜单管理等
     */
    private String menu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "PermissionInfo{" +
                "id='" + id + '\'' +
                ", permission='" + permission + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", menu='" + menu + '\'' +
                '}';
    }
}
