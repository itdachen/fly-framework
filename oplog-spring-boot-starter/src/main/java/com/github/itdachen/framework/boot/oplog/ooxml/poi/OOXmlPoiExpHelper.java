package com.github.itdachen.framework.boot.oplog.ooxml.poi;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.exp.ExpParamsSettings;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.exp.WorkBookExpHelper;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.exp.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * 导出
 *
 * @author 剑鸣秋朔
 * @date 2025/7/19 1:43
 */
public class OOXmlPoiExpHelper<T, Q> {
    private static final Logger logger = LoggerFactory.getLogger(OOXmlPoiExpHelper.class);

    /**
     * 导出参数设置
     */
    private ExpParamsSettings<Q> settings;


    /**
     * 数据来源已经导入方法接口
     */
    private IWriteWorkBook<T, Q> writeWorkBook;


    /***
     * 创建表格方法
     */
    private ICreateWorkBook<T, Q> createWorkBook = new DefaultCreateWorkBook<>();

    /***
     * 文件上传方法
     */
    private IWorkBookExpFileUpload fileUpload = new DefaultWorkBookExpFileUpload();


    public OOXmlPoiExpHelper<T, Q> settings(ExpParamsSettings<Q> settings) {
        this.settings = settings;
        return this;
    }

    /**
     * 数据来源
     */
    public OOXmlPoiExpHelper<T, Q> writeWorkBook(IWriteWorkBook<T, Q> writeWorkBook) {
        this.writeWorkBook = writeWorkBook;
        return this;
    }

    /**
     * 创建表格, 提供一个接口, 让调用方可以实现自定义
     */
    public OOXmlPoiExpHelper<T, Q> createWorkBook(ICreateWorkBook<T, Q> createWorkBook) {
        this.createWorkBook = createWorkBook;
        return this;
    }

    public OOXmlPoiExpHelper<T, Q> fileUpload(IWorkBookExpFileUpload fileUpload) {
        this.fileUpload = fileUpload;
        return this;
    }


    /***
     * 导出
     *
     * @author 剑鸣秋朔
     * @date 2025/7/19 2:04
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiExpHelper<T, Q>
     */
    public OOXmlPoiExpHelper<T, Q> execute() throws Exception {
        WorkBookExpHelper<T, Q> tqWorkBookExpHelper = new WorkBookExpHelper<>(this.settings, this.writeWorkBook, this.createWorkBook, this.fileUpload);
        tqWorkBookExpHelper.execute();
        return this;
    }

    /***
     * 执行导出, 返回给前端消息
     *
     * @author 剑鸣秋朔
     * @date 2025/7/19 2:21
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiExpHelper<T, Q>
     */
    public OOXmlPoiExpHelper<T, Q> reply() throws IOException {
        this.settings.getResponse().setStatus(HttpStatus.OK.value());
        this.settings.getResponse().setContentType("application/json;charset=UTF-8");
        this.settings.getResponse().getWriter().write(okMsg("数据正在导出，请刷新【消息中心】信息！"));
        return this;
    }

    /***
     * 成功消息
     */
    private static String okMsg(String msg) {
        return "{\"success\":\"true\",\"status\":\"" + 200 + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }


}
