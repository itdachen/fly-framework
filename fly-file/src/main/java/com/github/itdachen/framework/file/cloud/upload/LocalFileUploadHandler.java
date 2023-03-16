package com.github.itdachen.framework.file.cloud.upload;

import com.github.itdachen.framework.file.cloud.FileUploadService;
import com.github.itdachen.framework.file.entity.FileInfo;
import com.github.itdachen.framework.file.properties.LocalCloudStorageProperties;
import com.github.itdachen.framework.file.service.IVerifyFileHeaderService;
import com.github.itdachen.framework.file.utils.MapPathUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Description: 本地文件上传
 * Created by 王大宸 on 2023/02/10 16:52
 * Created with IntelliJ IDEA.
 */
public class LocalFileUploadHandler extends FileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileUploadHandler.class);

    public LocalFileUploadHandler(LocalCloudStorageProperties properties,
                                  IVerifyFileHeaderService verifyFileHeaderService) {
        this.properties = properties;
        this.verifyFileHeaderService = verifyFileHeaderService;
    }


    @Override
    public FileInfo upload(MultipartFile file) throws Exception {
        try {
            if (StringUtils.isEmpty(file.getOriginalFilename())) {
                throw new Exception("请选择需要上传的文件");
            }
            /* 校验文件头 */
            if (properties.getVerifyFileHeader()) {
                verifyFileHeaderService.verifyFileHeader(file);
            }

            long size = file.getSize();
            String fileName = file.getOriginalFilename();

            // 文件上传
            String src = fileUpload(file, filePath());
            // 文件网络地址处理
            String url = MapPathUtils.mapPath(properties.getMapPath()) + src.replace(properties.getDiskFolder(), "");
            url = url.replaceAll("//", "/");
            if (properties.getLocalHttp().endsWith("/")) {
                url = properties.getLocalHttp().substring(0, properties.getLocalHttp().length() - 1) + url;
            } else {
                url = properties.getLocalHttp() + url;
            }
            return new FileInfo.Builder()
                    .url(url)
                    .name(fileName)
                    .size(size)
                    .format(fileName.substring(fileName.lastIndexOf(".")))
                    .build();
        } catch (Exception e) {
            logger.error("文件上传失败: ", e);
            throw new Exception("上传失败");
        }
    }

    /***
     * 本地文件上传方法
     *
     * @author 王大宸
     * @date 2023/2/10 17:05
     * @param file file
     * @param filePath filePath
     * @return java.lang.String
     */
    public String fileUpload(MultipartFile file, String filePath) throws IOException {
        filePath = properties.getDiskFolder() + filePath;
        //如果文件路径不存在 创建路径
        File fileDir = new File(filePath.replaceAll("//", "/"));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        //文件名称,防止名称重复
        String fileName = redefineFileName(Objects.requireNonNull(file.getOriginalFilename()));

        File uploadFile = new File(filePath, fileName);
        file.transferTo(uploadFile);

        return filePath + fileName;
    }


}
