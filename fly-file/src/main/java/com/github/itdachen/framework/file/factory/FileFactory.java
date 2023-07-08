package com.github.itdachen.framework.file.factory;

import com.github.itdachen.framework.autoconfigure.properties.oss.FlyOssAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.enums.OssTypeEnum;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.AliYunOssAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.FlyLocalOssAutoconfigureProperties;
import com.github.itdachen.framework.file.cloud.download.InternetFileDownloadService;
import com.github.itdachen.framework.file.cloud.upload.AliYunUploadHandler;
import com.github.itdachen.framework.file.cloud.upload.LocalFileUploadHandler;
import com.github.itdachen.framework.file.cloud.DownloadService;
import com.github.itdachen.framework.file.cloud.FileUploadService;
import com.github.itdachen.framework.file.cloud.download.LocalFileDownloadService;
import com.github.itdachen.framework.file.service.IVerifyFileHeaderService;
import org.springframework.stereotype.Component;

/**
 * Description: 文件上传/下载工厂
 * Created by 王大宸 on 2023/02/10 16:50
 * Created with IntelliJ IDEA.
 */
@Component
public class FileFactory {
    private final FlyOssAutoconfigureProperties autoconfigureProperties;
    private final AliYunOssAutoconfigureProperties aliYunOssAutoconfigureProperties;
    private final FlyLocalOssAutoconfigureProperties localOssAutoconfigureProperties;
    private final IVerifyFileHeaderService verifyFileHeaderService;

    public FileFactory(FlyOssAutoconfigureProperties autoconfigureProperties,
                       AliYunOssAutoconfigureProperties aliYunOssAutoconfigureProperties,
                       FlyLocalOssAutoconfigureProperties localOssAutoconfigureProperties,
                       IVerifyFileHeaderService verifyFileHeaderService) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.aliYunOssAutoconfigureProperties=aliYunOssAutoconfigureProperties;
        this.localOssAutoconfigureProperties=localOssAutoconfigureProperties;
        this.verifyFileHeaderService = verifyFileHeaderService;
    }

    /***
     * 文件上传
     *
     * @author 王大宸
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
     * @author 王大宸
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
