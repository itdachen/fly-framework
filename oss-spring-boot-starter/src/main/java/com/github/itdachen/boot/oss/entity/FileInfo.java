package com.github.itdachen.boot.oss.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description: 文件上传成功返回结果集
 * Created by 王大宸 on 2023/02/10 16:11
 * Created with IntelliJ IDEA.
 */
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 3802156750492028791L;

    /**
     * 文件访问路径
     */
    private String url;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件格式
     */
    private String format;

    /**
     * 文件md5
     */
    private String md5;

    public FileInfo() {
    }

    public FileInfo(String name, String format, String url, Long size, String md5) {
        this.url = url;
        this.name = name;
        this.size = size;
        this.format = format;
        this.md5 = md5;
    }

    public FileInfo(Builder builder) {
        this.url = builder.url;
        this.name = builder.name;
        this.size = builder.size;
        this.format = builder.format;
        this.md5 = builder.md5;
    }


    public static class Builder {
        private String url;
        private String name;
        private Long size;
        private String format;
        private String md5;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder size(Long size) {
            this.size = size;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder md5(String md5) {
            this.md5 = md5;
            return this;
        }

        public FileInfo build() {
            return new FileInfo(this);
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("url", getUrl())
                .append("name", getName())
                .append("size", getSize())
                .append("format", getFormat())
                .append("md5", getMd5())
                .toString();
    }

}
