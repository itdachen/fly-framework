package com.github.itdachen.boot.oplog.ooxml.poi.exp.handler;

import com.github.itdachen.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.boot.autoconfigure.AppHelper;
import com.github.itdachen.boot.oplog.ooxml.poi.constants.OOXmlPoiConstants;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.MsgFileModel;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.boot.oplog.ooxml.poi.exp.ExpParamsSettings;
import com.github.itdachen.boot.oplog.ooxml.poi.exp.utils.ExcelExpUtils;
import com.github.itdachen.boot.oplog.ooxml.poi.msg.IOplogMsgClient;
import com.github.itdachen.boot.oplog.ooxml.poi.msg.OOXmlPoiMsgHandler;
import com.github.itdachen.framework.context.id.IdUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 创建表格默认实现方式
 *
 * @author 王大宸
 * @date 2025/7/11 23:44
 */
public class DefaultCreateWorkBook<T, Q> implements ICreateWorkBook<T, Q> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCreateWorkBook.class);


    /***
     * 创建表格
     *
     * @author 王大宸
     * @date 2025/7/22 15:18
     * @param settings settings
     * @param handler handler
     * @param bookNum  第几个表格
     * @param msgId    导出消息ID
     * @return void
     */
    @Override
    public void createWorkBook(ExpParamsSettings<Q> settings,
                               IWriteWorkBook<T, Q> handler,
                               int bookNum, String msgId,
                               IWorkBookExpFileUpload fileUploadHandler) {

        final String fileTitle = ExcelExpUtils.getExpTitle(settings.getTitle(), bookNum, settings.getFileFormat());


        String msgContent = "";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            /* 查询可导出的数据 */
            Integer limit = Integer.parseInt(String.valueOf(settings.getSheetRowNum()));
            List<T> dataList = handler.data(settings.getParams(), bookNum, limit);

            msgContent = "获取数据成功，该文件共 " + dataList.size() + " 条数据！";
            OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

            /* 创建 sheet */
            Sheet sheet = workbook.createSheet(settings.getTitle() + bookNum);
            sheet.setDefaultColumnWidth(30); // 设置默认宽度

            /* 表头设置 */
            //两个方法区别在有自定义表头标题
            ExcelExpUtils.setTableTitleCell(workbook, sheet, settings.getTitle(), settings.getFields(), settings.getAddIndexNum());

            if (!dataList.isEmpty()) {

                msgContent = "文件数据开始写入表格！";
                OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

                /* 数据写入表格, 根据调用方情况自定义 */
                handler.writeWorkBook(workbook, sheet, dataList, settings.getAddIndexNum());

                msgContent = "文件数据写入结束表格！";
                OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

            }

            dataList = null;

            toExpFileUpload(msgId, fileTitle, workbook, settings, fileUploadHandler);

            msgContent = "导出完成！";
            OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

            /* 关闭 */
            workbook.close();
        } catch (Exception e) {
            logger.error("数据 " + fileTitle + " 导出失败: {} ", e.getMessage(), e);
            msgContent = "导出失败：" + e.getMessage();
            OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }

                /* 计时结束 */
                stopWatch.stop();

                msgContent = "导出记录开始保存到数据库，本次导出共用时 " + stopWatch.getTotalTimeSeconds() + " 秒！";
                OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

                stopWatch = null;
            } catch (Exception e) {
                logger.error("数据导出, 关闭输出流失败: {} ", e.getMessage(), e);
            }

        }
    }


    /***
     * 文件上传至服务器
     *
     * @author 王大宸
     * @date 2025/7/28 9:35
     * @param msgId msgId
     * @param fileTitle fileTitle
     * @param workbook workbook
     * @param settings settings
     * @param fileUploadHandler fileUploadHandler
     * @return void
     */
    private void toExpFileUpload(String msgId, String fileTitle,
                                 XSSFWorkbook workbook,
                                 ExpParamsSettings<Q> settings,
                                 IWorkBookExpFileUpload fileUploadHandler) throws Exception {

        PoiUploadInfo uploadInfo = new PoiUploadInfo();
        /* 导出是否保存到服务器 */
        String msgContent = "导出结果开始上传到服务器！";
        OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);

        fileUploadHandler.toExpFileUpload(workbook, uploadInfo);

        msgContent = "导出结果上传到服务器结束！";
        OOXmlPoiMsgHandler.appendContent(msgId, fileTitle, msgContent);


        /* 下载文件消息入库: 能推送消息, 能上传文件 */
        MsgFileModel messageFile = MsgFileModel.builder()
                .id(IdUtils.getId())
                .msgId(msgId)
                .msgTitle(settings.getTitle() + OOXmlPoiConstants.POI_EXP_SUFFIX_TITLE)
                .appId(AppHelper.app().properties().getAppId())
                .fileFormat(settings.getFileFormat())
                .fileTitle(fileTitle)
                .fileUrl(uploadInfo.getFileUri())
                .fileSize(uploadInfo.getFileSize() + "")
                .hexMd5(fileTitle)
                .build();
        AppContextHelper.getBean(IOplogMsgClient.class).saveMsgFile(messageFile);

    }


}
