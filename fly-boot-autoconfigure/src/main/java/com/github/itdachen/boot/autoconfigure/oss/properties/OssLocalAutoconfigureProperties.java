package com.github.itdachen.boot.autoconfigure.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 文件上传配置(本地服务器)
 * Created by 王大宸 on 2023/02/10 15:26
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.oss.local")
public class OssLocalAutoconfigureProperties {

    /**
     * 访问地址,线上配置域名,测试环境配置 ip
     */
    private String localHttp = "http://127.0.0.1:8080";

    /**
     * 服务器存储位置
     */
    private String diskFolder = "/home/oss/";

    /**
     * 映射路径
     */
    private String mapPath = "upload";

    /**
     * 是否校验文件头
     */
    private Boolean verifyFileHeader = false;


    public String getLocalHttp() {
        return localHttp;
    }

    public void setLocalHttp(String localHttp) {
        this.localHttp = localHttp;
    }

    public String getDiskFolder() {
        return diskFolder;
    }

    public void setDiskFolder(String diskFolder) {
        this.diskFolder = diskFolder;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public Boolean getVerifyFileHeader() {
        return verifyFileHeader;
    }

    public void setVerifyFileHeader(Boolean verifyFileHeader) {
        this.verifyFileHeader = verifyFileHeader;
    }

}
