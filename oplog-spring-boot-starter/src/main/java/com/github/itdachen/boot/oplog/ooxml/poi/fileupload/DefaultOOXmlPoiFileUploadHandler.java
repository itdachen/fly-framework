package com.github.itdachen.boot.oplog.ooxml.poi.fileupload;

import com.github.itdachen.framework.context.models.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口的默认实现
 *
 * @author 王大宸
 * @date 2025-07-29 17:43
 */
public class DefaultOOXmlPoiFileUploadHandler implements IOOXmlPoiFileUploadHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOOXmlPoiFileUploadHandler.class);

    @Override
    public FileInfo toUpload(MultipartFile multipartFile, String path) {
        logger.error("导入导出文件上传接口未实现, 请实现 IOOXmlPoiFileUploadHandler 接口！！！");
        return null;
    }

}
