package com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.handler;

import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.IReadWorkBookHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.ImpParamsSettings;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.utils.ExcelImpUtils;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.msg.OOXmlPoiMsgHandler;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 解析数据, 默认实现
 *
 * @author 剑鸣秋朔
 * @date 2025/7/25 22:23
 */
public class DefaultAnalysisOOXmlPoiHandler<T> implements IAnalysisOOXmlPoiHandler<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAnalysisOOXmlPoiHandler.class);


    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("ooxml-poi-imp-thread-" + ThreadLocalRandom.current().nextInt(1000));
            return thread;
        }
    });


    /***
     * 文件解析
     *
     * @author 剑鸣秋朔
     * @date 2025/7/25 23:18
     * @param settings     settings
     * @param readWorkBook readWorkBook
     * @return void
     */
    @Override
    public PoiUploadInfo analysisWorkBook(Workbook workbook,
                                          ImpParamsSettings settings,
                                          IReadWorkBookHandler<T> readWorkBook,
                                          String msgId) throws Exception {

        /* 获取该表格有几个 Sheet */
        int workbookSheetNumber = ExcelImpUtils.getWorkbookSheetNumber(workbook);

        OOXmlPoiMsgHandler.appendContent(msgId, "已从文件中获取 " + workbookSheetNumber + " 个 sheet 表格数据！<br>");

        Sheet sheet;
        int rows;
        Row row;
        List<T> list = new ArrayList<>();
        long dataTotal = 0L;
        for (int i = 0; i < workbookSheetNumber; i++) {
            list = new ArrayList<>(); // 存放数据
            sheet = ExcelImpUtils.getSheet(workbook, i);

            OOXmlPoiMsgHandler.appendContent(msgId, "正在读取第 " + (i + 1) + " 个 sheet 表格数据，sheet 名称为《" + sheet.getSheetName() + "》！");

            /* 获取表格行数 */
            rows = sheet.getPhysicalNumberOfRows();
            /* 解析行 */
            for (int i1 = settings.getFirstRowNum(); i1 < rows; i1++) {
                row = sheet.getRow(i1);

                /* 解析列, 交给调用方自动实现  cells = row.getPhysicalNumberOfCells(); */
                readWorkBook.readRows(settings.getUserDetails(), list, row, i, sheet.getSheetName());

            }
            OOXmlPoiMsgHandler.appendContent(msgId, "读取第 " + (i + 1) + " 个 sheet 表格数据完成，该 sheet 共计 " + list.size() + " 条数据，正在对数据进行入库处理！");

            /* 数据处理, 交给调用方处理, 数据入库 */
            readWorkBook.handler(list);

            OOXmlPoiMsgHandler.appendContent(msgId, "第 " + (i + 1) + " 个 sheet 表格数据入库处理完成！<br>");

            dataTotal += list.size();
        }

        OOXmlPoiMsgHandler.appendContent(msgId, "数据读取完成，本次导入共计 " + dataTotal + " 条数据！");


        return new PoiUploadInfo(settings.getTitle(), dataTotal, 1L);


    }


}
