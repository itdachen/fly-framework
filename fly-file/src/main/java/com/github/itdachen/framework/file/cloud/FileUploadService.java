package com.github.itdachen.framework.file.cloud;

import com.github.itdachen.framework.file.entity.FileInfo;
import com.github.itdachen.framework.file.properties.LocalCloudStorageProperties;
import com.github.itdachen.framework.file.service.IVerifyFileHeaderService;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Description: 文件上传抽象类
 * Created by 王大宸 on 2023/02/10 16:59
 * Created with IntelliJ IDEA.
 */
public abstract class FileUploadService {
    protected LocalCloudStorageProperties properties;
    protected IVerifyFileHeaderService verifyFileHeaderService;

    /***
     * 文件上传统一接口
     *
     * @author 王大宸
     * @date 2023/2/10 17:00
     * @param file file
     * @return cn.edu.hubu.framework.file.entity.FileInfo
     */
    public abstract FileInfo upload(MultipartFile file) throws Exception;


    /**
     * 获取文件在服务器上的路径
     *
     * @return java.lang.String
     * @author 王大宸
     * @date 2022/8/19 15:39
     */
    protected String filePath() {
        return LocalDate.now().getYear() + "/" + LocalDate.now().getMonthValue() + "/";
    }

    /**
     * 获取 UUID
     *
     * @return java.lang.String
     * @author 王大宸
     * @date 2022/8/19 15:46
     */
    protected String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 上传时重新定义文件名, 防止名称重复
     *
     * @param fileName 原始文件名
     * @return java.lang.String
     * @author 王大宸
     * @date 2022/8/19 15:49
     */
    protected String redefineFileName(String fileName) {
        return getUUID() + fileName.substring(fileName.lastIndexOf("."));
    }


}