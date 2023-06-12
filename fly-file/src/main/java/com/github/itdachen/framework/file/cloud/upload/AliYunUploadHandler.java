package com.github.itdachen.framework.file.cloud.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.github.itdachen.framework.file.cloud.FileUploadService;
import com.github.itdachen.framework.file.entity.FileInfo;
import com.github.itdachen.framework.file.properties.AliYunOssProperties;
import com.github.itdachen.framework.file.properties.OssCloudProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * Description:
 * Created by 王大宸 on 2023-06-12 22:33
 * Created with IntelliJ IDEA.
 */
public class AliYunUploadHandler extends FileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(AliYunUploadHandler.class);

    public AliYunUploadHandler(OssCloudProperties properties) {
        this.properties = properties;
    }


    @Override
    public FileInfo upload(MultipartFile file) throws Exception {
        //  String bucketName = ossCloudProperties.getBucket();
        //  String endpoint = ossCloudProperties.getEndpoint();
        // String accessKeyId = ossCloudProperties.getAccessKeyId();
        // String accessKeySecret = ossCloudProperties.getAccessKeySecret();

        //oss客户端构建
        // OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSS ossClient = new OSSClientBuilder().build(properties.getAli().getEndpoint(),
                properties.getAli().getAccessKeyId(),
                properties.getAli().getAccessKeySecret());

        //获取文件原始名称 xxx.jpg
        String originalFilename = file.getOriginalFilename();

        //jdk8语法日期格式
//        LocalDateTime ldt = LocalDateTime.now();
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 2023/06/12/
        // String folder = pattern.format(ldt);
        String fileName = redefineFileName(Objects.requireNonNull(file.getOriginalFilename()));
        assert originalFilename != null;
        final String extendsion = originalFilename.substring(originalFilename.lastIndexOf("."));

        //在oss上的bucket创建文件夹
        String newFilename = filePath() + "/" + fileName + extendsion;

        try {
            long size = file.getSize();
            PutObjectResult putObjectResult = ossClient.putObject(properties.getAli().getBucket(), newFilename, file.getInputStream());
            //拼装返回路径
            if (putObjectResult != null) {
                String imgUrl = "https://" + properties.getAli().getBucket() + "." + properties.getAli().getEndpoint() + "/" + newFilename;
                return new FileInfo.Builder()
                        .url(imgUrl)
                        .name(fileName)
                        .size(size)
                        .format(fileName.substring(fileName.lastIndexOf(".")))
                        .build();
            }

        } catch (IOException e) {
            logger.error("阿里云文件上传失败: {}", e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;
    }
}
