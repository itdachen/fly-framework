package com.github.itdachen.framework.boot.oplog.ooxml.poi.imp.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * 导出工具类
 *
 * @author 王大宸
 * @date 2025-07-25 10:29
 */
public class ExcelImpUtils {

    /***
     * 是否是 2003 的 excel，返回 true 是 2003
     *
     * @author 王大宸
     * @date 2025/7/25 10:30
     * @param filePath filePath
     * @return boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /***
     * 是否是 2007 的 excel，返回 true 是 2007
     *
     * @author 王大宸
     * @date 2025/7/25 10:30
     * @param filePath filePath
     * @return boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    /***
     * 验证 EXCEL 文件
     *
     * @author 王大宸
     * @date 2025/7/25 10:31
     * @param filePath filePath
     * @return boolean
     */
    public static boolean validateExcel(String filePath) {
        return filePath != null && (isExcel2003(filePath) || isExcel2007(filePath));
    }


    /***
     * 获取文件表格
     *
     * @author 王大宸
     * @date 2025/7/25 15:01
     * @param path path
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    public static Workbook getWorkbook(String path) {
        try {
            return WorkbookFactory.create(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("读取EXCEL文件出错", e);
        }
    }

    public static Workbook getWorkbook(InputStream inputStream) {
        try {
            return WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("读取EXCEL文件出错", e);
        }
    }

    public static Workbook getWorkbook(MultipartFile file) {
//        try {
//            return WorkbookFactory.create(file.getInputStream());
//        } catch (Exception e) {
//            throw new RuntimeException("读取EXCEL文件出错", e);
//        }
        try (InputStream inputStream = file.getInputStream()) {
            return WorkbookFactory.create(inputStream);
            // 你的处理逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading the Excel file", e);
        }

//        catch (InvalidFormatException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Invalid Excel format", e);
//        }

        catch (EncryptedDocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("The document is password protected", e);
        }
//        catch (OOXml4JXmlException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error parsing the Excel file", e);
//        }
        catch (OldExcelFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Old Excel format is not supported", e);
        } catch (NotOfficeXmlFileException e) {
            e.printStackTrace();
            throw new RuntimeException("The file is not a valid Office XML file", e);
        }

    }

    /***
     * 获取文件内有几个 Sheet
     *
     * @author 王大宸
     * @date 2025/7/25 15:01
     * @param workbook workbook
     * @return int
     */
    public static int getWorkbookSheetNumber(Workbook workbook) {
        return workbook.getNumberOfSheets();
    }


    /***
     * 获取 Sheet 内容
     *
     * @author 王大宸
     * @date 2025/7/25 15:02
     * @param wb wb
     * @param sheetIndex sheetIndex
     * @return org.apache.poi.ss.usermodel.Sheet
     */
    public static Sheet getSheet(Workbook wb, int sheetIndex) {
        if (wb == null) {
            throw new RuntimeException("工作簿对象为空");
        }
        int sheetSize = wb.getNumberOfSheets();
        if (sheetIndex < 0 || sheetIndex > sheetSize - 1) {
            throw new RuntimeException("工作表获取错误");
        }
        return wb.getSheetAt(sheetIndex);
    }


    public static Long getLongValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Long.parseLong(cell.getStringCellValue());
        }
        return null;
    }

    public static Integer getIntegerValue(Cell cell) {
        if (cell == null) return null;
        return Integer.parseInt(cell.getStringCellValue());
    }

    public static Double getDoubleValue(Cell cell) {
        if (cell == null) return null;
        return cell.getNumericCellValue();
    }

    public static String getStringValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

    public static BigDecimal getBigDecimalValue(Cell cell) {
        double numericCellValue = cell.getNumericCellValue();
        return BigDecimal.valueOf(numericCellValue);
    }

    public static Boolean getBooleanValue(Cell cell) {
        return cell.getBooleanCellValue();
    }


    public static void main(String[] args) {
        //   String path = "D:\\mock\\陈述抽签名单.xlsx";
        String path = "D:\\mock\\签名单.xls";

        boolean b = validateExcel(path);
        System.err.println("是否是 Excel: " + b);

        Workbook workbook = getWorkbook(path);
        Sheet sheet = getSheet(workbook, 0);

        String sheetName = sheet.getSheetName();
        System.err.println("sheetName: " + sheetName);

        int rows = sheet.getPhysicalNumberOfRows();//getPhysicalNumberOfRows 获取所有行数
        System.out.println("行数是: " + rows);

        int firstRowNum = sheet.getFirstRowNum();
        System.out.println("firstRowNum: " + firstRowNum);

        for (int j = 0; j < rows; j++) {

            // 判断是不是第一行标题 是不是表头？
            if (sheet.getFirstRowNum() == j) {
                //  第一行不要了
                continue;
            }
            Row row = sheet.getRow(j);
            int cells = row.getPhysicalNumberOfCells();//getPhysicalNumberOfCells 获取所有列数

            for (int i = 0; i < cells; i++) {
                switch (i) {
                    case 0://第1列
                        row.getCell(i).setCellType(CellType.STRING);//  设置单元格的类型字符串类型
                        String time = row.getCell(i).getStringCellValue();//转换为整数
                        System.err.println("第 " + j + " 行, 第 " + i + " 列: " + time);
                        break;
                    case 1://第2列
                        row.getCell(i).setCellType(CellType.STRING);
                        String index = row.getCell(i).getStringCellValue();
                        System.err.println("第 " + j + " 行, 第 " + i + " 列: " + index);
                        break;
                    case 2://第3列
                        row.getCell(i).setCellType(CellType.STRING);
                        String name = row.getCell(i).getStringCellValue();
                        System.err.println("第 " + j + " 行, 第 " + i + " 列: " + name);
                        break;
                    case 3://第4列
                        row.getCell(i).setCellType(CellType.STRING);
                        String dw = row.getCell(i).getStringCellValue();
                        System.err.println("第 " + j + " 行, 第 " + i + " 列: " + dw);
                        break;
                }
            }

        }


    }

}
