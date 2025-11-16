package com.github.itdachen.framework.boot.oplog.ooxml.poi.exp;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格设置
 *
 * @author 剑鸣秋朔
 * @date 2025/7/11 23:48
 */
public class ExpParamsSettings<Q> {


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
     * 标题
     */
    private String title = "导出文件";

    /**
     * 菜单功能ID, 对应菜单表
     */
    private String funcId = "-";

    /**
     * 菜单功能名称, 对应菜单表
     */
    private String funcTitle = "-";

    /**
     * 二级表头
     */
    private List<String> fields = new ArrayList<>();


    /**
     * 导出文件格式
     */
    private String fileFormat = ".xlsx";

    /**
     * 是否添加序号
     */
    private Boolean addIndexNum = true;

    /**
     * 查询参数
     */
    private Q params;


    /**
     * 单个表格数量, 默认三万条
     */
    protected Integer sheetRowNum = 30000;


    public ExpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails,
                             String title,
                             List<String> fields,
                             Q params) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
        this.title = title;
        this.fields = fields;
        this.params = params;
    }


    public ExpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails,
                             String title,
                             String funcId,
                             String funcTitle,
                             List<String> fields,
                             Q params) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
        this.title = title;
        this.funcId = funcId;
        this.funcTitle = funcTitle;
        this.fields = fields;
        this.params = params;
    }

    public ExpParamsSettings(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    public ExpParamsSettings(HttpServletRequest request,
                             HttpServletResponse response,
                             UserInfoDetails userDetails) {
        this.request = request;
        this.response = response;
        this.userDetails = userDetails;
    }

    public ExpParamsSettings<Q> title(String title) {
        this.title = title;
        return this;
    }

    public ExpParamsSettings<Q> fields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public ExpParamsSettings<Q> fileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public ExpParamsSettings<Q> addIndexNum(Boolean addIndexNum) {
        this.addIndexNum = addIndexNum;
        return this;
    }

    public ExpParamsSettings<Q> params(Q params) {
        this.params = params;
        return this;
    }

    public ExpParamsSettings<Q> sheetRowNum(Integer sheetRowNum) {
        this.sheetRowNum = sheetRowNum;
        return this;
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

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Boolean getAddIndexNum() {
        return addIndexNum;
    }

    public void setAddIndexNum(Boolean addIndexNum) {
        this.addIndexNum = addIndexNum;
    }

    public Q getParams() {
        return params;
    }

    public void setParams(Q params) {
        this.params = params;
    }

    public Integer getSheetRowNum() {
        return sheetRowNum;
    }

    public void setSheetRowNum(Integer sheetRowNum) {
        this.sheetRowNum = sheetRowNum;
    }


    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncTitle() {
        return funcTitle;
    }

    public void setFuncTitle(String funcTitle) {
        this.funcTitle = funcTitle;
    }
}
