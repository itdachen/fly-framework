package com.github.itdachen.framework.boot.oplog.ooxml.poi.fileupload;

import com.github.itdachen.framework.context.models.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口, 交给调用方实现
 *
 * @author 王大宸
 * @date 2025-07-29 17:36
 */
public interface IOOXmlPoiFileUploadHandler {


    FileInfo toUpload(MultipartFile multipartFile, String path);


}
