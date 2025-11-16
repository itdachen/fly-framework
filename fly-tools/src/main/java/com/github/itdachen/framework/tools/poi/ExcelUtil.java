package com.github.itdachen.framework.tools.poi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.*;
import java.util.*;

/**
 * Excel 工具类
 *
 * @author wtc
 * @date 2019/09/24 18:13
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String OFFICE_EXCEL_XLS = "xls";
    private static final String OFFICE_EXCEL_XLSX = "xlsx";

    /***
     *获取 Excel 文件类容
     *
     * @author 剑鸣秋朔
     * @date 2022/10/17 9:23
     * @param filePath 文件路径
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     */
    public static List<Map<String, Object>> read(String filePath) throws IOException {
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        InputStream stream = new FileInputStream(filePath);
        Workbook wb = null;
        if (fileType.equals(OFFICE_EXCEL_XLS)) {
            wb = new HSSFWorkbook(stream);
        } else if (fileType.equals(OFFICE_EXCEL_XLSX)) {
            wb = new XSSFWorkbook(stream);
        } else {
            logger.error("Excel 文件格式不对,当前文件格式为 : " + fileType);
        }
        Sheet sheet1 = wb.getSheetAt(0);
        List<Map<String, Object>> listStr = new ArrayList<>();
        for (Row row : sheet1) {
            Map<String, Object> map = new HashMap<>(row.getRowNum());
            int i = 0;
            for (Cell cell : row) {
                map.put(String.valueOf(i), cell.getStringCellValue());
                i++;
            }
            listStr.add(map);
        }
        return listStr;
    }

    /***
     * 写入文件内容
     *
     * @author 剑鸣秋朔
     * @date 2022/10/17 9:23
     * @param outPath outPath
     * @return boolean
     */
    public static boolean write(String outPath) throws Exception {
        String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
        System.out.println(fileType);
        // 创建工作文档对象
        Workbook wb = null;
        if (fileType.equals(OFFICE_EXCEL_XLS)) {
            wb = new HSSFWorkbook();
        } else if (fileType.equals(OFFICE_EXCEL_XLSX)) {
            wb = new XSSFWorkbook();
        } else {
            logger.error("Excel 文件格式不对,当前文件格式为 : " + fileType);
            return false;
        }
        // 创建sheet对象
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 循环写入行数据
        for (int i = 0; i < 5; i++) {
            Row row = (Row) sheet1.createRow(i);
            // 循环写入列数据
            for (int j = 0; j < 8; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("测试" + j);
            }
        }
        // 创建文件流
        OutputStream stream = new FileOutputStream(outPath);
        // 写入数据
        wb.write(stream);
        // 关闭文件流
        stream.close();
        return true;
    }

    /***
     * 导出通用方法
     *
     * @author 剑鸣秋朔
     * @date 2022/10/17 9:23
     * @param fields        表头字段名称
     * @param exportDate    导出数据
     * @param fileName      文件名
     * @return void
     */
    public static void exportExcel(List<String> fields,
                                   List<LinkedHashMap<String, Object>> exportDate,
                                   String fileName) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpServletRequest request = requestAttributes.getRequest();

        String filename = fileName + ".xls";

        OutputStream out = null;
        InputStream is = null;
        try {
            // 写到服务器上
            String path = request.getSession().getServletContext().getRealPath("") + "/" + filename;
            File name = new File(path);
            // 创建写工作簿对象
            WritableWorkbook workbook = jxl.Workbook.createWorkbook(name);
            // 工作表
            WritableSheet sheet = workbook.createSheet(fileName, 0);
            // 设置字体;
            WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

            WritableCellFormat cellFormat = new WritableCellFormat(font);
            // 设置背景颜色;
            cellFormat.setBackground(Colour.YELLOW);
            // 设置边框  所有边框/边框线/边框颜色
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREY_25_PERCENT);
            // 设置文字居中对齐方式;
            cellFormat.setAlignment(Alignment.CENTRE);
            // 设置垂直居中;
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 分别给1列设置不同的宽度;
//            sheet.setColumnView(0, 15);
            // 给sheet电子版中所有的列设置默认的列的宽度;
            sheet.getSettings().setDefaultColumnWidth(25);
            // 给sheet电子版中所有的行设置默认的高度，高度的单位是1/20个像素点,但设置这个貌似就不能自动换行了
            // sheet.getSettings().setDefaultRowHeight(30);
            // 设置自动换行;
            cellFormat.setWrap(true);

            // 表头
            int column = 0;
            for (String field : fields) {
                sheet.addCell(new Label(column, 0, field, cellFormat));
                column++;
            }
            //设置行高
            sheet.setRowView(0, 800, false);

            if (exportDate != null && !exportDate.isEmpty()) {
                WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
                WritableCellFormat bodyCellFormat = new WritableCellFormat(bodyFont);
                bodyCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREY_25_PERCENT);
                bodyCellFormat.setAlignment(Alignment.CENTRE);
                bodyCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                sheet.getSettings().setDefaultColumnWidth(20);
                bodyCellFormat.setWrap(true);

                // 从第二行开始写入数据
                int index = 1;
                for (Map<String, Object> map : exportDate) {
                    // 从第一列开始
                    int dateColumn = 0;
                    for (Object value : map.values()) {
                        if (value == null || "null".equals(value.toString()) || StringUtils.isEmpty(value.toString())) {
                            value = "";
                        }
                        sheet.addCell(new Label(dateColumn, index, String.valueOf(value), bodyCellFormat));
                        dateColumn++;
                    }
                    index++;
                }
            }

            //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();
            // pageContext.getOut().clear();

            // 第六步，下载excel
            response.addHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename, "utf-8"));
            out = response.getOutputStream();
            String path3 = request.getSession().getServletContext().getRealPath("") + "/" + filename;

            // inputStream：读文件，前提是这个文件必须存在，要不就会报错
            is = new FileInputStream(path3);

            byte[] b = new byte[4096];
            int size = is.read(b);
            while (size > 0) {
                out.write(b, 0, size);
                size = is.read(b);
            }
            out.close();
            is.close();
        } catch (Exception e) {
            logger.error("数据导出失败: " + e.toString());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                logger.error("数据导出流关闭失败: " + e.toString());
            }
        }
    }

//    public static void main(String[] args) {
//        try {
//            write("D:001.xls");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            List<Map<String, Object>> read = read("D:002.xls");
//            for (Map s : read) {
//                System.err.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /***
     * 获取 excel 中的数据
     *
     * @author 剑鸣秋朔
     * @date 2022/10/17 9:23
     * @param in in
     * @param fileName fileName
     * @return java.util.List<java.util.List < java.lang.Object>>
     */
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (work == null) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }

}
