package com.github.itdachen.framework.webmvc.poi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * WorkBookBase
 *
 * @author 王大宸
 * @date 2024-12-04 9:17
 */
public abstract class WorkBookBase<T> {

    public static final String FILE_FORMAT = ".xlsx";
    public static final String DISK_PREFIX = "/excel/exp/";

    /**
     * 标题
     */
    protected String title = "导出标题";

    /**
     * 二级表头
     */
    protected List<String> fields = new ArrayList<>();

    /**
     * 导出数据
     */
    protected List<LinkedHashMap<String, String>> data = new ArrayList<>();

    /**
     * 导出文件格式
     */
    protected String fileFormat = ".xlsx";

    /**
     * 文件存储地址
     */
    protected String diskPrefix = "/excel/exp/";

    /**
     * 是否存服务器
     */
    protected Boolean upload = false;

    /**
     * 是否添加序号
     */
    protected Boolean rowNum = false;

    /**
     * 是否保存到数据库
     */
    protected Boolean save = false;

    /**
     * 执行方法
     *
     * @return
     */
    protected abstract T execute();


    public String title() {
        return this.title;
    }

    public T title(String title) {
        this.title = title;
        return (T) this;
    }

    public List<String> fields() {
        return this.fields;
    }

    public T fields(List<String> fields) {
        this.fields = fields;
        return (T) this;
    }

    public List<LinkedHashMap<String, String>> data() {
        return this.data;
    }

    public T data(List<LinkedHashMap<String, String>> data) {
        this.data = data;
        return (T) this;
    }

    public String fileFormat() {
        return this.fileFormat;
    }

    public T fileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return (T) this;
    }


    public String diskPrefix() {
        return this.diskPrefix;
    }

    public T diskPrefix(String diskPrefix) {
        this.diskPrefix = diskPrefix;
        return (T) this;
    }

    public Boolean upload() {
        return this.upload;
    }

    public T upload(Boolean upload) {
        this.upload = upload;
        return (T) this;
    }


    public Boolean rowNum() {
        return this.rowNum;
    }

    public T rowNum(Boolean rowNum) {
        this.rowNum = rowNum;
        return (T) this;
    }




    public Boolean save() {
        return this.save;
    }

    public T save(Boolean save) {
        this.save = save;
        return (T) this;
    }


}
