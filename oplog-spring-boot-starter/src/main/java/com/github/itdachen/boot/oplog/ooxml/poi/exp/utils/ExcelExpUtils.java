package com.github.itdachen.boot.oplog.ooxml.poi.exp.utils;

import com.github.itdachen.framework.core.utils.LocalDateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * 导出接触类
 *
 * @author 王大宸
 * @date 2025-07-08 16:27
 */
public class ExcelExpUtils {

    /* 字体 */
    public static final String FONT_NAME_FS_GB2312 = "仿宋_GB2312";

    /* 一级表头高度 */
    public static final String HEADER_ROW_HEIGHT = "800";

    /* 二级标题高度 */
    public static final String FIELD_HEADER_ROW_HEIGHT = "700";

    /* 文本行高 */
    public static final String TEXT_ROW_HEIGHT = "700";



    /***
     * 设置表头
     *
     * @author 王大宸
     * @date 2025/7/8 17:06
     * @param workbook workbook
     * @param sheet sheet
     * @param title  一级标题
     * @param fields 二级表头(展示字段)
     * @param rowNum 是否添加序号
     * @return void
     */
    public static void setTableTitleCell(XSSFWorkbook workbook,
                                         Sheet sheet,
                                         String title,
                                         List<String> fields,
                                         boolean rowNum) {
        /* 标题 */
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中

        XSSFFont headerXssfFont = workbook.createFont();
        headerXssfFont.setBold(true); //粗体显示
        headerXssfFont.setFontName(FONT_NAME_FS_GB2312);
        headerXssfFont.setFontHeightInPoints((short) 24);
        headerCellStyle.setFont(headerXssfFont);


        int endColumnNum = fields.size() - 1;
        if (rowNum) {
            endColumnNum = fields.size();
        }

        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, endColumnNum);
        sheet.addMergedRegion(cellRangeAddress);

        Row headerRow = sheet.createRow(0);
        headerRow.setHeight(Short.parseShort(HEADER_ROW_HEIGHT));
        Cell cell = headerRow.createCell(0);
        cell.setCellValue(title);
        cell.setCellStyle(headerCellStyle);


        /* 二级表头 */
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true); //粗体显示
        xssfFont.setFontName(FONT_NAME_FS_GB2312);
        xssfFont.setFontHeightInPoints((short) 16);
        cellStyle.setFont(xssfFont);

        Row row = sheet.createRow(1);
        row.setHeight(Short.parseShort(FIELD_HEADER_ROW_HEIGHT));

        int columnNum = 0;
        if (rowNum) {
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
     * 设置表格样式/内容文本字体
     *
     * @author 王大宸
     * @date 2025/7/8 17:10
     * @param workbook workbook
     * @return void
     */
    public static XSSFCellStyle setTextXSSFCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle textCellStyle = workbook.createCellStyle();
        textCellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        textCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
        XSSFFont textXssfFont = workbook.createFont();
        textXssfFont.setFontHeightInPoints((short) 12);
        textXssfFont.setBold(false); //粗体显示
        textCellStyle.setFont(textXssfFont);
        // textCellStyle.setWrapText(true); //设置自动换行

        return textCellStyle;
    }


    /**
     * 添加表格值
     *
     * @param row           行
     * @param cellNum       列号
     * @param cellText      添加的值
     * @param textCellStyle 表格样式
     */
    public static void setCellValue(Row row, int cellNum, String cellText, XSSFCellStyle textCellStyle) {
        Cell cell = row.createCell(cellNum);
        cell.setCellValue(cellText);
        cell.setCellStyle(textCellStyle);
    }

    /***
     * 默认导出处理
     *
     * @author 王大宸
     * @date 2025/7/9 9:48
     * @param workbook workbook
     * @param sheet sheet
     * @param list 导出数据
     * @param hasRowNum 导出表格是否有序号
     * @return void
     */
    public static void writeToWorkBook(XSSFWorkbook workbook, Sheet sheet, List<LinkedHashMap<String, String>> list, boolean hasRowNum) {
        int index = 1, rowNum = 2, dateColumn = 0; //  序号, 行号, 列号
        LinkedHashMap<String, String> map;
        String value; // 需要插入的数据信息
        Row row; // 表格:行
        Cell cell;  // 单表格: 元格
        final XSSFCellStyle textCellStyle = setTextXSSFCellStyle(workbook); // 表格样式
        for (Iterator<LinkedHashMap<String, String>> var = list.iterator(); var.hasNext(); ++index) {
            row = sheet.createRow(rowNum);
            row.setHeight(Short.parseShort(TEXT_ROW_HEIGHT));
            map = var.next();
            dateColumn = 0;

            if (hasRowNum) {
                dateColumn = 1;
                cell = row.createCell(0);
                cell.setCellValue(String.valueOf(index)); // 添加序号
                cell.setCellStyle(textCellStyle);
            }

            for (Iterator<String> varMap = map.values().iterator(); varMap.hasNext(); ++dateColumn) {
                value = String.valueOf(varMap.next());
                if (value == null || "null".equals(value) || value.isEmpty()) {
                    value = "";
                }
                cell = row.createCell(dateColumn);
                cell.setCellValue(value);
                cell.setCellStyle(textCellStyle);
            }
            rowNum++;
        }
    }


    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /***
     * 获取导出文件名称
     *
     * @author 王大宸
     * @date 2025/7/24 9:40
     * @param title       导出标题
     * @param bookNum     第几个文件
     * @param fileFormat  文件格式
     * @return java.lang.String
     */
    public static String getExpTitle(String title, int bookNum, String fileFormat) {
        final String localDate = LocalDateUtils.getLocalDate().replaceAll("-", "");
        return title + "_" + localDate + "_" + bookNum + fileFormat;
    }

}
