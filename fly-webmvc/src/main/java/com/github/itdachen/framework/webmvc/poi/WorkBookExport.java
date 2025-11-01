package com.github.itdachen.framework.webmvc.poi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jxl.write.WriteException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 导出
 *
 * @author 王大宸
 * @date 2024-12-04 9:42
 */
public class WorkBookExport extends WorkBookBase<WorkBookExport> {
    private static final Logger logger = LoggerFactory.getLogger(WorkBookExport.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 查询参数
     */
    protected Object params = null;

    /**
     * 数据条数
     */
    private long resSize = 0;

    /**
     * 耗时
     */
    private double exeTime = 0.0;
    /**
     * 文件访问地址, http 请求地址
     */
    protected String fileUri = "";

    /**
     * 文件在磁盘上的详细的路径
     */
    protected String fileDiskUri = "";

    /**
     * 文件大小
     */
    protected Long fileSize = 0L;


    protected final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("work-book-base-thread-" + ThreadLocalRandom.current().nextInt(1000));
            return thread;
        }
    });


    public WorkBookExport() {
    }

    public WorkBookExport(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest request() {
        return this.request;
    }

    public WorkBookExport request(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletResponse response() {
        return this.response;
    }

    public WorkBookExport response(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public Object params() {
        return this.params;
    }

    public WorkBookExport params(Object params) {
        this.params = params;
        return this;
    }

    @Override
    public WorkBookExport execute() {
        if (null == request || null == response) {
            throw new RuntimeException("HttpServletRequest 和 HttpServletResponse 不能为空！");
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        XSSFWorkbook workbook = null;
        OutputStream os = null;
        try {
            workbook = new XSSFWorkbook();
            os = response.getOutputStream();
            response.reset();// 清空输出流
            // excel 文件的 MIME 类型
            response.setContentType("application/msexcel");
            //在导出前对名称根据浏览器做下处理
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/vnd.ms-excel");
            //***************很重要
            //文件名中文乱码
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("content-disposition", "attachment;filename=" + new String(this.title.getBytes(), "ISO8859-1") + this.fileFormat);
            } else {
                String codedFileName = java.net.URLEncoder.encode(this.title, "UTF-8");
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName + this.fileFormat);
            }

            /* 创建 sheet */
            Sheet sheet = workbook.createSheet(this.title);
            sheet.setDefaultColumnWidth(30); // 设置默认宽度

            /* 表头设置 */
            //两个方法区别在有自定义表头标题
            setTitleCell(workbook, sheet);

            if (!data.isEmpty()) {
                int index = 1; // 序号
                int rowNum = 3; // 行号
                LinkedHashMap<String, String> map;
                String value;
                int dateColumn = 0; // 列号
                Row row;
                Cell cell;

                /* 内容文本字体 */
                XSSFCellStyle textCellStyle = workbook.createCellStyle();
                textCellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
                textCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
                XSSFFont textXssfFont = workbook.createFont();
                textXssfFont.setFontHeightInPoints((short) 12);
                textXssfFont.setBold(false); //粗体显示
                textCellStyle.setFont(textXssfFont);
                // textCellStyle.setWrapText(true); //设置自动换行

                for (Iterator<LinkedHashMap<String, String>> var = data.iterator(); var.hasNext(); ++index) {
                    row = sheet.createRow(rowNum);
                    row.setHeight(Short.parseShort("500"));
                    map = var.next();
                    dateColumn = 0;

                    if (this.rowNum) {
                        dateColumn = 1;
                        cell = row.createCell(0);
                        cell.setCellValue(String.valueOf(index)); // 添加序号
                        cell.setCellStyle(textCellStyle);
                    }

                    for (Iterator<String> varMap = map.values().iterator(); varMap.hasNext(); ++dateColumn) {
                        value = String.valueOf(varMap.next());
                        if (value == null || "null".equals(value) || StringUtils.isEmpty(value)) {
                            value = "";
                        }
                        cell = row.createCell(dateColumn);
                        cell.setCellValue(value);
                        cell.setCellStyle(textCellStyle);
                    }
                    rowNum++;
                }
            }

            /* 通过流写入到前端 */
            workbook.write(os);

            if (this.upload) {
                setUploadToFolder(workbook);
            }
            return new WorkBookExport();
        } catch (Exception e) {
            logger.error("数据导出失败: {} ", e.getMessage(), e);
            return new WorkBookExport();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (os != null) {
                    os.close();
                }

                stopWatch.stop();
                setExeTime(stopWatch.getTotalTimeSeconds());

                /* 保存到数据库 */
                if (this.save) {
                    saveHelper();
                }

            } catch (Exception e) {
                logger.error("数据导出, 关闭输出流失败: {} ", e.getMessage(), e);
            }
        }
    }

    /***
     * 设置表头
     *
     * @author 王大宸
     * @date 2024/5/28 10:22
     * @param workbook workbook
     * @param sheet sheet
     * @return void
     */
    private void setTitleCell(XSSFWorkbook workbook, Sheet sheet) throws WriteException {
        /* 标题 */
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中

        XSSFFont headerXssfFont = workbook.createFont();
        headerXssfFont.setBold(true); //粗体显示
        headerXssfFont.setFontName("仿宋_GB2312");
        headerXssfFont.setFontHeightInPoints((short) 24);
        headerCellStyle.setFont(headerXssfFont);


        int endColumnNum = fields.size() - 1;
        if (this.rowNum) {
            endColumnNum = fields.size();
        }

        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, endColumnNum);
        sheet.addMergedRegion(cellRangeAddress);

        Row headerRow = sheet.createRow(0);
        headerRow.setHeight(Short.parseShort("800"));
        Cell cell = headerRow.createCell(0);
        cell.setCellValue(this.title);
        cell.setCellStyle(headerCellStyle);


        /* 二级表头 */
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true); //粗体显示
        xssfFont.setFontName("仿宋_GB2312");
        xssfFont.setFontHeightInPoints((short) 16);
        cellStyle.setFont(xssfFont);

        Row row = sheet.createRow(1);
        row.setHeight(Short.parseShort("700"));


        int columnNum = 0;
        if (this.rowNum) {
            cell = row.createCell(columnNum);
            cell.setCellValue("序号");
            cell.setCellStyle(cellStyle);
            columnNum = 1;
        }

        for (String field : fields) {
            cell = row.createCell(columnNum);
            cell.setCellValue(field);
            cell.setCellStyle(cellStyle);
            columnNum++;
        }
    }


    /***
     * 将文件保持至服务器
     *
     * @author 王大宸
     * @date 2024/5/28 11:20
     * @param workbook workbook
     * @return void
     */
    private void setUploadToFolder(XSSFWorkbook workbook) {
        OssLocalAutoconfigureProperties ossProperties = AppContextHelper.getBean(OssLocalAutoconfigureProperties.class);
        String diskUri = ossProperties.getDiskFolder();
        if (!diskUri.endsWith(DISK_PREFIX)) {
            diskUri += DISK_PREFIX;
        }
        diskUri = diskUri.replaceAll("//", "/");

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        diskUri += pattern.format(ldt) + "/";

        String localDateTime = getLocalDateTime();
        localDateTime = localDateTime.replaceAll("-", "")
                .replaceAll(" ", "")
                .replaceAll(":", "");
        diskUri += localDateTime + getUUid() + this.fileFormat;



        /* 如果文件目录不存在, 则创建文件目录 */
        File file = new File(diskUri);
        File parentDir = file.getParentFile();
        long fileSize = file.length();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // 创建所有不存在的父目录
        }

        // 保存Excel到服务器
        try (FileOutputStream outputStream = new FileOutputStream(diskUri)) {
            workbook.write(outputStream);


            String fileUri = loadHttpUri(
                    ossProperties.getMapPath(),
                    diskUri,
                    ossProperties.getDiskFolder(),
                    ossProperties.getLocalHttp()
            );


            setFileDiskUri(diskUri); // 文件磁盘地址
            setFileUri(fileUri);     // 文件访问地址
            setFileSize(fileSize);   // 文件大小

        } catch (IOException e) {
            logger.error("文件保存到服务器失败: {}", e.getMessage());
        }
    }

    /***
     * 文件网络地址处理
     *
     * @author 王大宸
     * @date 2024/5/28 14:24
     * @param mapPath    映射路径, 例如: upload
     * @param diskUri    文件存放的磁盘详细地址, 例如: D:/upload/excel/exp/2024/05/28/xxxxx.xlsx
     * @param diskFolder 服务器存储位置, 例如: D:/upload/
     * @param bizHttp    应用访问地址, 例如: http://128.0.0.1:8080/app
     * @return java.lang.String 最终访问地址: http://128.0.0.1:8080/app/upload/excel/exp/2024/05/28/xxxxx.xlsx
     */
    private String loadHttpUri(String mapPath, String diskUri, String diskFolder, String bizHttp) {
        String url = mapPath(mapPath) + diskUri.replace(diskFolder, "");
        url = url.replaceAll("//", "/");
        if (bizHttp.endsWith("/")) {
            return bizHttp.substring(0, bizHttp.length() - 1) + url;
        }
        return bizHttp + url;
    }


    private void saveHelper() throws JsonProcessingException {

//        AppInfoProperties appClientProperties = AppContextHelper.getBean(AppInfoProperties.class);
//        PlatformInfoProperties platformClientProperties = AppContextHelper.getBean(PlatformInfoProperties.class);
//
//        LocalDateTime now = LocalDateTime.now();
//        String jsonString = objectMapper.writeValueAsString(this.params);

//        OplogDataExportModel oplogData = OplogDataExportModel.builder()
//                .id(EntityUtils.getId())
//                .appId(appClientProperties.getAppId())
//                .appName(appClientProperties.getAppName())
//                .serverVersion(appClientProperties.getVersion())
//                .contextPath(appClientProperties.getContextPath())
//                .platformId(platformClientProperties.getId())
//                .platformName(platformClientProperties.getTitle())
//
//                /* 人员信息 */
//                .signMethod(BizContextHandler.getSignMethod())
//                .loginAccount(BizContextHandler.getAccount())
//                .userDm(BizContextHandler.getRyDm())
//                .nickName(BizContextHandler.getRyName())
//                .roleDm(BizContextHandler.getSwRySfDm())
//                .roleName(BizContextHandler.getSwRySfMc())
//                .roleMain(BizContextHandler.getZsfBz())
//
//                /* 部门 */
//                .deptLevel(BizContextHandler.getDeptLevel())
//                .deptTitle(BizContextHandler.getDeptTitle())
//                .deptDm(BizContextHandler.getDeptId())
//                .parentDeptDm(BizContextHandler.getDeptParentId())
//
//                /* 行政区规划 */
//                .provDm(StringUtils.isEmpty(BizContextHandler.getProvCode()) ? "" : BizContextHandler.getProvCode())
//                .provTitle(StringUtils.isEmpty(BizContextHandler.getProvName()) ? "" : BizContextHandler.getProvName())
//                .cityDm(StringUtils.isEmpty(BizContextHandler.getCityCode()) ? "" : BizContextHandler.getCityCode())
//                .cityTitle(StringUtils.isEmpty(BizContextHandler.getCityName()) ? "" : BizContextHandler.getCityName())
//                .countyDm(StringUtils.isEmpty(BizContextHandler.getCountyCode()) ? "" : BizContextHandler.getCountyCode())
//                .countyTitle(StringUtils.isEmpty(BizContextHandler.getCountyName()) ? "" : BizContextHandler.getCountyName())
//                .fjDm(StringUtils.isEmpty(BizContextHandler.getStreetCode()) ? "" : BizContextHandler.getStreetCode())
//                .fjTitle(StringUtils.isEmpty(BizContextHandler.getStreetName()) ? "" : BizContextHandler.getStreetName())
//
//                /* 操作主机 */
//                .hostIp(ServletUtils.getIPAddress(this.request))
//                .hostAddress("")
//                .userAgent(request.getHeader("User-Agent"))
//
//                /* 日志信息 */
//                .oplogTitle(this.title)
//                // .clazzName(className)
//
//                /* 请求信息 */
//                .reqId(null == request.getRequestId() ? "" : request.getRequestId())
//                .reqUri(request.getRequestURI())
//                .reqMethod("GET")
//                .reqParams(jsonString)
//                //  .reqTime(now)
//
//                /* 执行信息 */
//                // .executeSql(executeSql)
//                .exeTime(getExeTime() + " ms")
//
//                /* 导出文件信息 */
//                .fileFormat(this.fileFormat)
//                .fileName(this.title + this.fileFormat)
//                .fileUrl(this.fileUri)
//                .fileDiskUrl(this.fileDiskUri)
//                .fileSize(String.valueOf(this.fileSize))
//                .resSize(String.valueOf(this.resSize))
//
//                /* 年度, 月度 */
//                .delFlag(YesOrNotConstant.N)
//                .yearly(String.valueOf(now.getYear()))
//                .monthly(String.valueOf(now.getMonthValue()))
//
//                .build();


//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    AppContextHelper.getBean(IOplogClient.class).saveDataExport(oplogData);
//                } catch (Exception e) {
//                    logger.error("数据导出日志入库失败 ", e);
//                }
//            }
//        });

    }


    /***
     * 映射路径处理
     *
     * @author 王大宸
     * @date 2023/2/13 9:32
     * @param path path
     * @return java.lang.String
     */
    private String mapPath(String path) {
        path = !path.startsWith("/") ? "/" + path : path;
        path = !path.endsWith("/") ? path + "/" : path;
        return path;
    }


    private String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String getUUid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public long getResSize() {
        return resSize;
    }

    public void setResSize(long resSize) {
        this.resSize = resSize;
    }

    public double getExeTime() {
        return exeTime;
    }

    public void setExeTime(double exeTime) {
        this.exeTime = exeTime;
    }


    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileDiskUri() {
        return fileDiskUri;
    }

    public void setFileDiskUri(String fileDiskUri) {
        this.fileDiskUri = fileDiskUri;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
