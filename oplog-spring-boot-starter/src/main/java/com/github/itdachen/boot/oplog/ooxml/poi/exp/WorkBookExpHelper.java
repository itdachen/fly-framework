package com.github.itdachen.boot.oplog.ooxml.poi.exp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.boot.oplog.ooxml.poi.constants.OOXmlPoiConstants;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.boot.oplog.ooxml.poi.exp.handler.*;
import com.github.itdachen.boot.oplog.ooxml.poi.exp.utils.ExcelExpUtils;
import com.github.itdachen.boot.oplog.ooxml.poi.msg.OOXmlPoiMsgHandler;
import com.github.itdachen.framework.context.id.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * WorkBookExpHelper
 *
 * @author 王大宸
 * @date 2025/7/19 1:51
 */
public class WorkBookExpHelper<T, Q> {
    private static final Logger logger = LoggerFactory.getLogger(WorkBookExpHelper.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("work-book-exp-thread-" + ThreadLocalRandom.current().nextInt(1000));
            return thread;
        }
    });


    /**
     * 导出参数设置
     */
    private ExpParamsSettings<Q> settings;


    /**
     * 数据来源已经导入方法接口
     */
    private IWriteWorkBook<T, Q> writeWorkBook;


    /***
     * 创建表格方法
     */
    private ICreateWorkBook<T, Q> createWorkBook = new DefaultCreateWorkBook<>();

    private IWorkBookExpFileUpload fileUpload = new DefaultWorkBookExpFileUpload();

    public WorkBookExpHelper(ExpParamsSettings<Q> settings, IWriteWorkBook<T, Q> writeWorkBook) {
        this.settings = settings;
        this.writeWorkBook = writeWorkBook;
    }

    public WorkBookExpHelper(ExpParamsSettings<Q> settings, IWriteWorkBook<T, Q> writeWorkBook, ICreateWorkBook<T, Q> createWorkBook, IWorkBookExpFileUpload fileUpload) {
        this.settings = settings;
        this.writeWorkBook = writeWorkBook;
        this.createWorkBook = createWorkBook;
        this.fileUpload = fileUpload;
    }


    public void execute() throws IOException {
        if (null == settings.getRequest() || null == settings.getResponse()) {
            throw new RuntimeException("HttpServletRequest 和 HttpServletResponse 不能为空！");
        }


        Long rowTotal = writeWorkBook.total(settings.getParams());
        long bookTotalNum = rowTotal / settings.getSheetRowNum();
        if (0 < rowTotal % settings.getSheetRowNum()) {
            bookTotalNum++;
        }
        if (0 == rowTotal) {
            bookTotalNum = 1;
        }

        final String msgId = IdUtils.getId();

        /* 操作日志 */
        WorkBookExpLogHandler.saveLog(settings.getRequest(), settings.getUserDetails(), msgId,
                settings.getParams(),
                new PoiUploadInfo(settings.getTitle(), rowTotal, bookTotalNum));

        try {
            /* 添加导出消息日志 */
            final String content = "【" + settings.getTitle() + "】数据导出共 " + bookTotalNum + " 个文件，共计 " + rowTotal + " 条数据。数据导出较慢，若消息附件中没有 " + bookTotalNum + " 个文件，请 5 分钟后重新查看消息信息！";
            OOXmlPoiMsgHandler.saveExpMsgInfo(settings.getRequest(), msgId,
                    content,
                    "【" + settings.getTitle() + "】" + OOXmlPoiConstants.POI_EXP_SUFFIX_TITLE,
                    settings.getUserDetails());
        } catch (Exception e) {
            logger.error("导出消息入库失败: ", e);
        }


        final long finalBookNum = bookTotalNum;
        final IWriteWorkBook<T, Q> workBookHandler = writeWorkBook;
        final IWorkBookExpFileUpload fileUploadHandler = fileUpload;
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    workBookPoiExpHelper(settings, workBookHandler, finalBookNum, msgId, fileUploadHandler);
                } catch (Exception e) {
                    logger.error("数据导出日志入库失败 ", e);
                }
            }
        });

    }


    /***
     * 导出操作, 循环导出一个或者多个文件
     *
     * @author 王大宸
     * @date 2025/7/10 15:09
     * @param settings settings
     * @return void
     */
    private void workBookPoiExpHelper(ExpParamsSettings<Q> settings,
                                      IWriteWorkBook<T, Q> writeWorkBook,
                                      long bookTotalNum,
                                      String msgId,
                                      IWorkBookExpFileUpload fileUploadHandler) {

        String msgContent = "====== 开始导出 ======";
        OOXmlPoiMsgHandler.appendContent(msgId, "", msgContent);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String expTitle = "";

        int bookNum; // 记录第几个文件
        for (int i = 0; i < bookTotalNum; i++) {
            bookNum = i + 1;
            logger.info("====== 导出《" + expTitle + "》 数据文件 START ======");

            expTitle = ExcelExpUtils.getExpTitle(settings.getTitle(), bookNum, settings.getFileFormat());
            OOXmlPoiMsgHandler.appendContent(msgId, "", "====== 导出《" + expTitle + "》 数据文件 START ======");

            try {
                createWorkBook.createWorkBook(settings, writeWorkBook, bookNum, msgId, fileUploadHandler);
            } catch (Exception e) {

                logger.error("====== 导出《" + expTitle + "》 数据文件 END [FAIL] ======", e);
                String msg = "====== 导出《" + expTitle + "》 数据文件 END [FAIL]: " + e.getMessage() + "<br>";
                OOXmlPoiMsgHandler.appendContent(msgId, "", msg);
            }

            logger.info("====== 导出《" + expTitle + " 数据文件 END [SUCCESS] ======");
            OOXmlPoiMsgHandler.appendContent(msgId, "", "====== 导出《" + expTitle + "》 数据文件 END [SUCCESS] ====== <br>");
        }

        stopWatch.stop();

        msgContent = "====== 导出结束，本次导出共用时 " + stopWatch.getTotalTimeSeconds() + " 秒 ======";
        OOXmlPoiMsgHandler.appendContent(msgId, settings.getTitle(), msgContent);

    }


}
