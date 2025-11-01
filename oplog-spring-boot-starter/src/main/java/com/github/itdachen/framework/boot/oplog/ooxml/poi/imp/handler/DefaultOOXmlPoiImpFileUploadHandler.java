package com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.handler;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.fileupload.IOOXmlPoiFileUploadHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.utils.CommonsMultipartFile;
import com.github.itdachen.framework.context.models.FileInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传默认实现
 *
 * @author 王大宸
 * @date 2025-07-25 17:21
 */
public class DefaultOOXmlPoiImpFileUploadHandler implements IOOXmlPoiImpFileUploadHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOOXmlPoiImpFileUploadHandler.class);


    @Override
    public FileInfo toUpload(InputStream inputStream, String fileName) throws Exception {
        MultipartFile fileItem = getMultipartFile(inputStream, fileName);
        return AppContextHelper.getBean(IOOXmlPoiFileUploadHandler.class).toUpload(fileItem, PoiUploadInfo.IMP_DISK_PREFIX);
    }


    public MultipartFile getMultipartFile(InputStream inputStream, String fileName) {
        FileItem fileItem = createFileItem(inputStream, fileName);
        //CommonsMultipartFile 是 feign 对 multipartFile 的封装，但是要 FileItem 类对象
        return new CommonsMultipartFile(fileItem);
    }


    /**
     * FileItem类对象创建
     *
     * @param inputStream inputStream
     * @param fileName    fileName
     * @return FileItem
     */
    public FileItem createFileItem(InputStream inputStream, String fileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        OutputStream os = null;
        //使用输出流输出输入流的字节
        try {
            os = item.getOutputStream();
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        } catch (IOException e) {
            logger.error("Stream copy exception", e);
            throw new IllegalArgumentException("文件上传失败");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("Stream close exception", e);

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Stream close exception", e);
                }
            }
        }

        return item;
    }

}
