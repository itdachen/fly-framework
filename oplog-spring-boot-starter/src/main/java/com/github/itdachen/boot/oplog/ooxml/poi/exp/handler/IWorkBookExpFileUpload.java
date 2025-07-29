package com.github.itdachen.boot.oplog.ooxml.poi.exp.handler;

import com.github.itdachen.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 文件上传, 采用接口的形式, 方便后期扩展
 *
 * @author 王大宸
 * @date 2025-07-25 17:13
 */
public interface IWorkBookExpFileUpload {


    void toExpFileUpload(XSSFWorkbook workbook, PoiUploadInfo uploadInfo) throws Exception;


}
