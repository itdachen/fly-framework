package com.github.itdachen.framework.boot.oplog.ooxml.poi.exp.handler;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.fileupload.IOOXmlPoiFileUploadHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.utils.CommonsMultipartFile;
import com.github.itdachen.framework.context.models.FileInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * 导出数据上传至文件服务, 默认实现
 *
 * @author 剑鸣秋朔
 * @date 2025-07-25 17:14
 */
public class DefaultWorkBookExpFileUpload implements IWorkBookExpFileUpload {

    @Override
    public void toExpFileUpload(XSSFWorkbook workbook, PoiUploadInfo uploadInfo) throws Exception {
        // 需要将 workbook 通过流对象转为 MultipartFile，不然直接转为 workbook 的字节生成的文件无法打开
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem fileItem = factory.createItem("textField", "text/plain", true, uploadInfo.getTitle() + uploadInfo.getFileFormat());
        OutputStream os = fileItem.getOutputStream();
        workbook.write(os);
        os.close();
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

        FileInfo upload = AppContextHelper.getBean(IOOXmlPoiFileUploadHandler.class).toUpload(multipartFile, PoiUploadInfo.EXP_DISK_PREFIX);
        uploadInfo.setFileDiskUri(upload.getUrl());   // 文件磁盘地址
        uploadInfo.setFileUri(upload.getUrl());       // 文件访问地址
        uploadInfo.setFileSize(upload.getSize());     // 文件大小

    }


}
