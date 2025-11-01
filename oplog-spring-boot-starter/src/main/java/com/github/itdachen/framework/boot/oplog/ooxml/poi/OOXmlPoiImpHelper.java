package com.github.itdachen.framework.boot.oplog.ooxml.poi;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.AppHelper;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.constants.OOXmlPoiConstants;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.MsgFileModel;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.IReadWorkBookHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.ImpParamsSettings;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.handler.*;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.utils.ExcelImpUtils;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.msg.IOplogMsgClient;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.msg.OOXmlPoiMsgHandler;
import com.github.itdachen.framework.boot.oplog.ooxml.poi.utils.ReplyResponseMsgUtils;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.context.models.FileInfo;
import com.github.itdachen.framework.core.utils.LocalDateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

/**
 * OOXmlPoiImpHelper
 *
 * @author 王大宸
 * @date 2025-07-25 15:58
 */
public class OOXmlPoiImpHelper<T> {
    private static final Logger logger = LoggerFactory.getLogger(OOXmlPoiImpHelper.class);

    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("ooxml-poi-imp-thread-" + ThreadLocalRandom.current().nextInt(1000));
            return thread;
        }
    });


    /**
     * 导入参数设置
     */
    private ImpParamsSettings settings;

    /**
     * 读取数据
     */
    private IReadWorkBookHandler<T> readWorkBook;

    /**
     * 解析表格
     */
    private IAnalysisOOXmlPoiHandler<T> analysisWorkBook = new DefaultAnalysisOOXmlPoiHandler<>();

    /**
     * 需要导入的文件
     */
    private MultipartFile workBookFile;

    /***
     * 文件上传接口, 可以自定义实现
     */
    private IOOXmlPoiImpFileUploadHandler fileUploadHandler = new DefaultOOXmlPoiImpFileUploadHandler();

    /**
     * 需要导入的文件
     */
    public OOXmlPoiImpHelper<T> workBookFile(MultipartFile workBookFile) {
        this.workBookFile = workBookFile;
        return this;
    }

    /**
     * 导入参数设置
     */
    public OOXmlPoiImpHelper<T> settings(ImpParamsSettings settings) {
        this.settings = settings;
        return this;
    }

    /***
     * 读取数据
     *
     * @author 王大宸
     * @date 2025/7/25 22:57
     * @param readWorkBook readWorkBook
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiImpHelper<T>
     */
    public OOXmlPoiImpHelper<T> readWorkBook(IReadWorkBookHandler<T> readWorkBook) {
        this.readWorkBook = readWorkBook;
        return this;
    }

    /**
     * 解析表格
     */
    public OOXmlPoiImpHelper<T> analysisWorkBook(IAnalysisOOXmlPoiHandler<T> analysisWorkBook) {
        this.analysisWorkBook = analysisWorkBook;
        return this;
    }


    /**
     * 文件上传接口, 可以自定义实现
     */
    public OOXmlPoiImpHelper<T> fileUploadHandler(IOOXmlPoiImpFileUploadHandler fileUploadHandler) {
        this.fileUploadHandler = fileUploadHandler;
        return this;
    }


    /***
     * 导入
     *
     * @author 王大宸
     * @date 2025/7/25 16:03
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiImpHelper<T>
     */
    public OOXmlPoiImpHelper<T> execute() throws Exception {
        final String msgId = IdUtils.getId();
        final String fileName = workBookFile.getOriginalFilename();
        final InputStream inputStream = workBookFile.getInputStream();

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();


                    /* 添加消息 */
                    OOXmlPoiMsgHandler.saveImpMsgInfo(
                            settings.getRequest(),
                            msgId,
                            "【" + LocalDateUtils.getLocalDateTimeMillis() + "】=========== 开始导入 ===========",
                            "【" + settings.getTitle() + "】" + OOXmlPoiConstants.POI_IMP_SUFFIX_TITLE,
                            settings.getUserDetails()
                    );


                    OOXmlPoiMsgHandler.appendContent(msgId, "正在校验文件校验格式是否正确，文件名为《" + fileName + "》!");
                    boolean validateExcel = ExcelImpUtils.validateExcel(fileName);
                    if (!validateExcel) {
                        OOXmlPoiMsgHandler.appendContent(msgId, "文件校验格式不正确，数据文件格式错误，不是标准的 xls 或 xlsx 文件，导入强制终止!");
                        throw new BizException("数据文件格式错误，不是标准的 xls 或 xlsx 文件！");
                    }

                    OOXmlPoiMsgHandler.appendContent(msgId, "文件格式校验完成，开始读取文件表格信息！");

                    Workbook workbook = ExcelImpUtils.getWorkbook(inputStream);
                    OOXmlPoiMsgHandler.appendContent(msgId, "文件信息读取完成，开始将文件数据上传是服务器！");

                    /* 文件上传 */
                    FileInfo upload = fileUploadHandler.toUpload(inputStream, fileName);
                    /* 文件信息入库 */
                    saveMsgFile(settings, msgId, upload);


                    OOXmlPoiMsgHandler.appendContent(msgId, "文件数据上传完成，开始解析表格数据！");

                    PoiUploadInfo poiUploadInfo = analysisWorkBook.analysisWorkBook(workbook, settings, readWorkBook, msgId);
                    poiUploadInfo.setFileSize(upload.getSize());
                    poiUploadInfo.setFileUri(upload.getUrl());
                    poiUploadInfo.setFileDiskUri(upload.getUrl());

                    /* 计时结束 */
                    stopWatch.stop();

                    poiUploadInfo.setTakeUpTime(stopWatch.getTotalTimeSeconds() + "");

                    OOXmlPoiMsgHandler.appendContent(msgId, "正在记录导入日志！");

                    /* 导入日志入库 */
                    OOXmlPoiImpLogHandler.saveLog(settings, msgId, poiUploadInfo);

                    /* 导入完成日志 */
                    OOXmlPoiMsgHandler.appendContent(msgId, "导入日志记录完成，本次导入共用时 " + stopWatch.getTotalTimeSeconds() + " 秒！");

                    OOXmlPoiMsgHandler.appendContent(msgId, "=========== 导入完成 ===========");
                } catch (Exception e) {
                    logger.error("{} 数据导入失败: ", settings.getTitle(), e);
                    OOXmlPoiMsgHandler.appendContent(msgId, "=========== 导入失败 ===========");
                    OOXmlPoiMsgHandler.appendContent(msgId, "失败原因：" + e.getMessage());
                }
            }
        });


        return this;
    }


    /***
     * 执行导入, 返回给前端消息
     *
     * @author 王大宸
     * @date 2025/7/25 22:52
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiImpHelper<T>
     */
    public OOXmlPoiImpHelper<T> reply() throws IOException {
        ReplyResponseMsgUtils.reply(settings.getResponse(), "数据正在导入，请刷新【消息中心】信息！");
        return this;
    }


    private void saveMsgFile(ImpParamsSettings settings, String msgId, FileInfo upload) {
        /* 下载文件消息入库: 能推送消息, 能上传文件 */
        MsgFileModel messageFile = MsgFileModel.builder()
                .id(IdUtils.getId())
                .msgId(msgId)
                .msgTitle(settings.getTitle() + OOXmlPoiConstants.POI_IMP_SUFFIX_TITLE)
                .appId(AppHelper.app().properties().getAppId())
                .fileFormat(upload.getFormat())
                .fileTitle(upload.getName())
                .fileUrl(upload.getUrl())
                .fileSize(upload.getSize() + "")
                .hexMd5(upload.getName())
                .build();
        AppContextHelper.getBean(IOplogMsgClient.class).saveMsgFile(messageFile);
    }


}
