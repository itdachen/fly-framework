package com.github.itdachen.framework.code.entity;

import cn.smallbun.screw.core.engine.EngineFileType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 数据库文档
 * Created by 王大宸 on 2023-06-27 16:39
 * Created with IntelliJ IDEA.
 */
public class TableDocsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName = "数据库文档";

    /**
     * 生成文件类型
     */
    private EngineFileType fileType = EngineFileType.HTML;

    /**
     * 生成之后是否打开目录
     */
    private Boolean openOutputDir = false;

    /**
     * 生成之后存放目录
     */
    private String fileOutputDir = "C:\\Downloads";

    /**
     * 版本
     */
    private String version = "V1.0";

    /**
     * 描述
     */
    private String description;

    /**
     * 忽略的表
     */
    private List<String> ignoreTables = new ArrayList<>();

    /**
     * 忽略表前缀
     */
    private List<String> ignorePrefixTables = new ArrayList<>();

    /**
     * 忽略表后缀
     */
    private List<String> ignoreSuffixTables = new ArrayList<>();


    public TableDocsInfo() {}

    public TableDocsInfo(String fileName,
                         EngineFileType fileType,
                         Boolean openOutputDir,
                         String fileOutputDir,
                         String version,
                         String description,
                         List<String> ignoreTables,
                         List<String> ignorePrefixTables,
                         List<String> ignoreSuffixTables) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.openOutputDir = openOutputDir;
        this.fileOutputDir = fileOutputDir;
        this.version = version;
        this.description = description;
        this.ignoreTables = ignoreTables;
        this.ignorePrefixTables = ignorePrefixTables;
        this.ignoreSuffixTables = ignoreSuffixTables;
    }

    public static TableDocsInfoBuilder builder() {
        return new TableDocsInfoBuilder();
    }

    public static class TableDocsInfoBuilder {
        private String fileName = "数据库文档";
        private EngineFileType fileType = EngineFileType.HTML;
        private Boolean openOutputDir = false;
        private String fileOutputDir = "C:\\Downloads";
        private String version = "V1.0";
        private String description = "";
        private List<String> ignoreTables = new ArrayList<>();
        private List<String> ignorePrefixTables = new ArrayList<>();
        private List<String> ignoreSuffixTables = new ArrayList<>();

        public TableDocsInfoBuilder() {
        }
        /* 文件名 */
        public TableDocsInfoBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
        /* 文件类型 */
        public TableDocsInfoBuilder fileType(EngineFileType fileType) {
            this.fileType = fileType;
            return this;
        }
        /* 是否打开文件夹 */
        public TableDocsInfoBuilder openOutputDir(Boolean openOutputDir) {
            this.openOutputDir = openOutputDir;
            return this;
        }
        /* 文件输出目录 */
        public TableDocsInfoBuilder fileOutputDir(String fileOutputDir) {
            this.fileOutputDir = fileOutputDir;
            return this;
        }
        /* 版本 */
        public TableDocsInfoBuilder version(String version) {
            this.version = version;
            return this;
        }
        /* 描述 */
        public TableDocsInfoBuilder description(String description) {
            this.description = description;
            return this;
        }
        /* 忽略的表 */
        public TableDocsInfoBuilder ignoreTables(List<String> ignoreTables) {
            this.ignoreTables = ignoreTables;
            return this;
        }
        /* 忽略表前缀 */
        public TableDocsInfoBuilder ignorePrefixTables(List<String> ignorePrefixTables) {
            this.ignorePrefixTables = ignorePrefixTables;
            return this;
        }
        /*  忽略表后缀 */
        public TableDocsInfoBuilder ignoreSuffixTables(List<String> ignoreSuffixTables) {
            this.ignoreSuffixTables = ignoreSuffixTables;
            return this;
        }

        public TableDocsInfo build() {
            return new TableDocsInfo(
                    fileName,
                    fileType,
                    openOutputDir,
                    fileOutputDir,
                    version,
                    description,
                    ignoreTables,
                    ignorePrefixTables,
                    ignoreSuffixTables
            );
        }

    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public EngineFileType getFileType() {
        return fileType;
    }

    public void setFileType(EngineFileType fileType) {
        this.fileType = fileType;
    }

    public Boolean getOpenOutputDir() {
        return openOutputDir;
    }

    public void setOpenOutputDir(Boolean openOutputDir) {
        this.openOutputDir = openOutputDir;
    }

    public String getFileOutputDir() {
        return fileOutputDir;
    }

    public void setFileOutputDir(String fileOutputDir) {
        this.fileOutputDir = fileOutputDir;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIgnoreTables() {
        return ignoreTables;
    }

    public void setIgnoreTables(List<String> ignoreTables) {
        this.ignoreTables = ignoreTables;
    }

    public List<String> getIgnorePrefixTables() {
        return ignorePrefixTables;
    }

    public void setIgnorePrefixTables(List<String> ignorePrefixTables) {
        this.ignorePrefixTables = ignorePrefixTables;
    }

    public List<String> getIgnoreSuffixTables() {
        return ignoreSuffixTables;
    }

    public void setIgnoreSuffixTables(List<String> ignoreSuffixTables) {
        this.ignoreSuffixTables = ignoreSuffixTables;
    }
}
