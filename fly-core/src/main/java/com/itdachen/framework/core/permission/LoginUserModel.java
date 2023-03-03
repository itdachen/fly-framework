package com.itdachen.framework.core.permission;

import java.io.Serializable;

/**
 * Description: 登录查询对象
 * Created by 王大宸 on 2023/03/03 15:42
 * Created with IntelliJ IDEA.
 */
public class LoginUserModel  implements Serializable {
    private static final long serialVersionUID = 2989313658535606417L;

    private String id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录账户
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 生产商家id,服务提供商
     */
    private String providerId;
    /**
     * 生产商家id,服务提供商
     */
    private String openId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 部门ID
     */
    private String departId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 账号等级
     */
    private String grade;

    /**
     * 客户端id
     */
    private String clientId;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartId() {
        return departId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
