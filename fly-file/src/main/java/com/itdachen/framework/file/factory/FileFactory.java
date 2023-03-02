package com.itdachen.framework.file.factory;


import com.itdachen.framework.file.cloud.DownloadService;
import com.itdachen.framework.file.cloud.FileUploadService;
import com.itdachen.framework.file.cloud.download.InternetFileDownloadService;
import com.itdachen.framework.file.cloud.download.LocalFileDownloadService;
import com.itdachen.framework.file.cloud.upload.LocalFileUploadHandler;
import com.itdachen.framework.file.properties.LocalCloudStorageProperties;
import com.itdachen.framework.file.service.IVerifyFileHeaderService;
import org.springframework.stereotype.Component;

/**
 * Description: 文件上传/下载工厂
 * Created by 王大宸 on 2023/02/10 16:50
 * Created with IntelliJ IDEA.
 */
@Component
public class FileFactory {
    private final LocalCloudStorageProperties properties;
    private final IVerifyFileHeaderService verifyFileHeaderService;

    public FileFactory(LocalCloudStorageProperties properties,
                       IVerifyFileHeaderService verifyFileHeaderService) {
        this.properties = properties;
        this.verifyFileHeaderService = verifyFileHeaderService;
    }

    /***
     * 文件上传
     *
     * @author 王大宸
     * @date 2023/2/10 17:07
     * @return cn.edu.hubu.framework.file.cloud.FileUploadService
     */
    public FileUploadService build() {
        return new LocalFileUploadHandler(properties, verifyFileHeaderService);
    }


    /***
     * 文件下载
     *
     * @author 王大宸
     * @date 2023/2/10 16:56
     * @param uri uri
     * @return cn.edu.hubu.framework.file.factory.DownloadService
     */
    public DownloadService build(String uri) {
        if (uri.startsWith(properties.getLocalHttp())) {
            return new LocalFileDownloadService(properties);
        }
        return new InternetFileDownloadService(properties);
    }

}
