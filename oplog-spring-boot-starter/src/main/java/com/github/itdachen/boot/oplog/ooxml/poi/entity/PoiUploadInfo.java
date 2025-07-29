package com.github.itdachen.boot.oplog.ooxml.poi.entity;

import java.io.Serializable;

/**
 * 上传的文件信息
 *
 * @author 王大宸
 * @date 2025-07-22 11:26
 */
public class PoiUploadInfo implements Serializable {
    private static final long serialVersionUID = 5892676357440997734L;

    /* 默认导出文件类型 */
    public static final String FILE_FORMAT = ".xlsx";
    /* 导出数据存放地址 */
    public static final String EXP_DISK_PREFIX = "/excel/exp/";
    /* 导入数据存放地址 */
    public static final String IMP_DISK_PREFIX = "/excel/imp/";


    /**
     * 导入导出标题
     */
    protected String title = "-";

    /**
     * 文件类型
     * 导出时, 默认为 xlsx 文件
     */
    protected String fileFormat = FILE_FORMAT;

    /**
     * 文件名称
     */
    protected String fileName = "-";

    /**
     * 文件存储地址
     */
    protected String diskPrefix = "-";

    /**
     * 文件访问地址, http 请求地址
     */
    protected String fileUri = "-";

    /**
     * 文件在磁盘上的详细的路径
     */
    protected String fileDiskUri = "-";

    /**
     * 文件大小
     */
    protected Long fileSize = 0L;

    /**
     * 文件数量
     */
    protected Long fileTotal = 0L;


    /**
     * 数据条数
     */
    protected Long dataTotal = 0L;

    /**
     * 导出或导入占用时间
     */
    protected String takeUpTime;

    public PoiUploadInfo() {
    }

    public PoiUploadInfo(String title) {
        this.title = title;
    }

    public PoiUploadInfo(String title, Long dataTotal, Long fileTotal) {
        this.title = title;
        this.dataTotal = dataTotal;
        this.fileTotal = fileTotal;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiskPrefix() {
        return diskPrefix;
    }

    public void setDiskPrefix(String diskPrefix) {
        this.diskPrefix = diskPrefix;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileDiskUri() {
        return fileDiskUri;
    }

    public void setFileDiskUri(String fileDiskUri) {
        this.fileDiskUri = fileDiskUri;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(Long dataTotal) {
        this.dataTotal = dataTotal;
    }

    public Long getFileTotal() {
        return fileTotal;
    }

    public void setFileTotal(Long fileTotal) {
        this.fileTotal = fileTotal;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTakeUpTime() {
        return takeUpTime;
    }

    public void setTakeUpTime(String takeUpTime) {
        this.takeUpTime = takeUpTime;
    }

}
