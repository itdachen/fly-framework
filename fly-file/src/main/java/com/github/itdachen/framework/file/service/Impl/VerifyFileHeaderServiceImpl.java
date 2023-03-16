package com.github.itdachen.framework.file.service.Impl;

import com.github.itdachen.framework.file.service.IVerifyFileHeaderService;
import com.github.itdachen.framework.file.utils.FileHeaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * Created by 王大宸 on 2023/02/17 9:07
 * Created with IntelliJ IDEA.
 */
public class VerifyFileHeaderServiceImpl implements IVerifyFileHeaderService {
    private static final Logger logger = LoggerFactory.getLogger(VerifyFileHeaderServiceImpl.class);

    @Override
    public void verifyFileHeader(MultipartFile file) throws Exception {
        String fileHeaderCode = FileHeaderUtils.getFileHeader(file).toUpperCase();
        if (null == fileHeaderCode || "".equals(fileHeaderCode)) {
            logger.error("文件上传, 文件头解析为 null");
            throw new Exception("文件解析错误, 文件不能为空!!!");
        }
        String fileHeaderHex = FileHeaderUtils.getFileTypeByFileCode(fileHeaderCode);
        if (null == fileHeaderCode || "".equals(fileHeaderHex)) {
            logger.error("文件上传, 文件头解析: {}, 文件格式: {}; 该文件格式不支持上传", fileHeaderCode, file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            throw new Exception("该文件格式不支持, 建议打成压缩包(rar)格式上传!!!");
        }
    }

}
