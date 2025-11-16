package com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.handler;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.IReadWorkBookHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.ImpParamsSettings;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 解析表格
 *
 * @author 剑鸣秋朔
 * @date 2025/7/25 22:05
 */
public interface IAnalysisOOXmlPoiHandler<T> {

    /***
     * 解析表格
     *
     * @author 剑鸣秋朔
     * @date 2025/7/29 16:42
     * @param workbook 表格数据
     * @param settings 设置参数
     * @param readWorkBook 读取表格接口
     * @param msgId 消息ID
     * @return com.github.itdachen.ooxml.poi.entity.PoiUploadInfo
     */
    PoiUploadInfo analysisWorkBook(Workbook workbook,
                                   ImpParamsSettings settings,
                                   IReadWorkBookHandler<T> readWorkBook,
                                   String msgId) throws Exception;


}
