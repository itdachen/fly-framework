package com.github.itdachen.framework.boot.oplog.ooxml.poi.imp;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 导入参数设置
 *
 * @author 王大宸
 * @date 2025/7/25 22:18
 */
public class ImpParamsSettings {

    /**
     * 请求信息
     */
    private HttpServletRequest request;

    /**
     * 相应信息
     */
    private HttpServletResponse response;

    /**
     * 用户信息
     */
    private UserInfoDetails userDetails = BizContextHandler.getUserDetails();

    /**
     * 标题功能
     */
    private String title = "导入文件";

    /**
     * 菜单功能ID, 对应菜单表
     */
    private String funcId = "-";

    /**
     * 菜单功能名称, 对应菜单表
     */
    private String funcTitle = "-";

    /**
     * 从第几行还是读取
     */
    private int firstRowNum = 2;


    /***
     * 导入参数
     */
    private Object params;


    public ImpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails,
                             String title) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
        this.title = title;
    }

    public ImpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails,
                             String title,
                             int firstRowNum) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
        this.title = title;
        this.firstRowNum = firstRowNum;
    }


    public ImpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails,
                             String title,
                             String funcId,
                             String funcTitle,
                             int firstRowNum) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
        this.title = title;
        this.firstRowNum = firstRowNum;
        this.funcId = funcId;
        this.funcTitle = funcTitle;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public UserInfoDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserInfoDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncTitle() {
        if (null == this.funcTitle) {
            return title;
        }
        return funcTitle;
    }

    public void setFuncTitle(String funcTitle) {
        this.funcTitle = funcTitle;
    }

    public int getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(int firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
