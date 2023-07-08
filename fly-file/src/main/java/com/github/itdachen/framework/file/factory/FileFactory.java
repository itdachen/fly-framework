package com.github.itdachen.framework.file.factory;


import com.github.itdachen.framework.autoconfigure.properties.FlyAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.enums.OssTypeEnum;
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
    private final FlyAutoconfigureProperties autoconfigureProperties;
    private final IVerifyFileHeaderService verifyFileHeaderService;

    public FileFactory(FlyAutoconfigureProperties autoconfigureProperties,
                       IVerifyFileHeaderService verifyFileHeaderService) {
        this.autoconfigureProperties = autoconfigureProperties;
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
        if (OssTypeEnum.ALI == autoconfigureProperties.getOss().getType()) {
            return new AliYunUploadHandler(autoconfigureProperties);
        }
        return new LocalFileUploadHandler(autoconfigureProperties, verifyFileHeaderService);
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
        if (uri.startsWith(autoconfigureProperties.getOss().getLocal().getLocalHttp())) {
            return new LocalFileDownloadService(autoconfigureProperties);
        }
        return new InternetFileDownloadService(autoconfigureProperties);
    }

}
