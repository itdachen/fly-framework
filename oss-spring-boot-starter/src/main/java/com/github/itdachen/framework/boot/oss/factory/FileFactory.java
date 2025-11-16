package com.github.itdachen.framework.boot.oss.factory;

import com.github.itdachen.framework.boot.autoconfigure.oss.enums.OssTypeEnum;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssProperties;
import com.github.itdachen.framework.boot.oss.cloud.DownloadService;
import com.github.itdachen.framework.boot.oss.cloud.FileUploadService;
import com.github.itdachen.framework.boot.oss.cloud.download.InternetFileDownloadService;
import com.github.itdachen.framework.boot.oss.cloud.download.LocalFileDownloadService;
import com.github.itdachen.framework.boot.oss.cloud.upload.AliYunUploadHandler;
import com.github.itdachen.framework.boot.oss.cloud.upload.LocalFileUploadHandler;
import com.github.itdachen.framework.boot.oss.service.IVerifyFileHeaderService;

/**
 * Description: 文件上传/下载工厂
 * Created by 剑鸣秋朔 on 2023/02/10 16:50
 * Created with IntelliJ IDEA.
 */
public class FileFactory {
    private final OssProperties autoconfigureProperties;
    private final OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties;
    private final OssLocalAutoconfigureProperties localOssAutoconfigureProperties;
    private final IVerifyFileHeaderService verifyFileHeaderService;

    public FileFactory(OssProperties autoconfigureProperties,
                       OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties,
                       OssLocalAutoconfigureProperties localOssAutoconfigureProperties,
                       IVerifyFileHeaderService verifyFileHeaderService) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.aliYunOssAutoconfigureProperties=aliYunOssAutoconfigureProperties;
        this.localOssAutoconfigureProperties=localOssAutoconfigureProperties;
        this.verifyFileHeaderService = verifyFileHeaderService;
    }

    /***
     * 文件上传
     *
     * @author 剑鸣秋朔
     * @date 2023/4/11 21:15
     * @return com.github.itdachen.framework.file.cloud.FileUploadService
     */
    public FileUploadService build() {
        if (OssTypeEnum.ALI == autoconfigureProperties.getType()) {
            return new AliYunUploadHandler(aliYunOssAutoconfigureProperties);
        }
        return new LocalFileUploadHandler(localOssAutoconfigureProperties, verifyFileHeaderService);
    }


    /***
     * 文件下载
     *
     * @author 剑鸣秋朔
     * @date 2023/4/11 21:28
     * @param uri uri
     * @return com.github.itdachen.framework.file.cloud.DownloadService
     */
    public DownloadService build(String uri) {
        if (uri.startsWith(localOssAutoconfigureProperties.getLocalHttp())) {
            return new LocalFileDownloadService(localOssAutoconfigureProperties);
        }
        return new InternetFileDownloadService(localOssAutoconfigureProperties);
    }

}
