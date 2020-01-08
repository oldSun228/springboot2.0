package com.xch.study.biz.excelmanage.utils;

import com.xch.study.biz.excelmanage.entity.ExcelDataEntity;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author fgs
 * @Date 2020/1/6 18:17
 * @Version 1.0
 * @Description
 **/
public class ExcelReader {
    private static Logger logger = Logger.getLogger(ExcelReader.class.getName()); // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<ExcelDataEntity> readExcel(String fileName) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                logger.warning("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<ExcelDataEntity> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<ExcelDataEntity> parseExcel(Workbook workbook) {
        List<ExcelDataEntity> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warning("解析Excel失败，在第一行没有读取到任何数据！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 2;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                ExcelDataEntity resultData = convertRowToData(row);
                if (null == resultData) {
                    logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC: //数字 日期
                DecimalFormat df = new DecimalFormat("#");
                // like12 add,20180622,支持日期格式
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
                    returnValue = df2.format(d);
                } else {// 数字
                    returnValue = df.format(cell.getNumericCellValue()).toString();
                }
                break;
            case STRING: //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN: //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK: // 空值
                break;
            case FORMULA: // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR: // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static ExcelDataEntity convertRowToData(Row row) {
        ExcelDataEntity resultData = new ExcelDataEntity();
        Cell cell;
        int cellNum = 0;

        cell = row.getCell(cellNum++);
        String li1 = convertCellValueToString(cell);
        resultData.setLi1(li1);

        cell = row.getCell(cellNum++);
        String li2 = convertCellValueToString(cell);
        resultData.setLi2(li2);

        cell = row.getCell(cellNum++);
        String li3 = convertCellValueToString(cell);
        resultData.setLi3(li3);

        cell = row.getCell(cellNum++);
        String li4 = convertCellValueToString(cell);
        resultData.setLi4(li4);

        cell = row.getCell(cellNum++);
        String li5 = convertCellValueToString(cell);
        resultData.setLi5(li5);

        cell = row.getCell(cellNum++);
        String li6 = convertCellValueToString(cell);
        resultData.setLi6(li6);

        cell = row.getCell(cellNum++);
        String li7 = convertCellValueToString(cell);
        resultData.setLi7(li7);

        cell = row.getCell(cellNum++);
        String li8 = convertCellValueToString(cell);
        resultData.setLi8(li8);

        cell = row.getCell(cellNum++);
        String li9 = convertCellValueToString(cell);
        resultData.setLi9(li9);

        cell = row.getCell(cellNum++);
        String li10 = convertCellValueToString(cell);
        resultData.setLi10(li10);

        cell = row.getCell(cellNum++);
        String li11 = convertCellValueToString(cell);
        resultData.setLi11(li11);

        cell = row.getCell(cellNum++);
        String li12 = convertCellValueToString(cell);
        resultData.setLi12(li12);

        cell = row.getCell(cellNum++);
        String li13 = convertCellValueToString(cell);
        resultData.setLi13(li13);

        cell = row.getCell(cellNum++);
        String li14 = convertCellValueToString(cell);
        resultData.setLi14(li14);

        cell = row.getCell(cellNum++);
        String li15 = convertCellValueToString(cell);
        resultData.setLi15(li15);

        cell = row.getCell(cellNum++);
        String li16 = convertCellValueToString(cell);
        resultData.setLi16(li16);

        cell = row.getCell(cellNum++);
        String li17 = convertCellValueToString(cell);
        resultData.setLi17(li17);

        cell = row.getCell(cellNum++);
        String li18 = convertCellValueToString(cell);
        resultData.setLi18(li18);

        cell = row.getCell(cellNum++);
        String li19 = convertCellValueToString(cell);
        resultData.setLi19(li19);

        cell = row.getCell(cellNum++);
        String li20 = convertCellValueToString(cell);
        resultData.setLi20(li20);

        cell = row.getCell(cellNum++);
        String li21 = convertCellValueToString(cell);
        resultData.setLi21(li21);

        cell = row.getCell(cellNum++);
        String li22 = convertCellValueToString(cell);
        resultData.setLi22(li22);

        cell = row.getCell(cellNum++);
        String li23 = convertCellValueToString(cell);
        resultData.setLi23(li23);

        cell = row.getCell(cellNum++);
        String li24 = convertCellValueToString(cell);
        resultData.setLi24(li24);

        cell = row.getCell(cellNum++);
        String li25 = convertCellValueToString(cell);
        resultData.setLi25(li25);

        cell = row.getCell(cellNum++);
        String li26 = convertCellValueToString(cell);
        resultData.setLi26(li26);

        cell = row.getCell(cellNum++);
        String li27 = convertCellValueToString(cell);
        resultData.setLi27(li27);

        cell = row.getCell(cellNum++);
        String li28 = convertCellValueToString(cell);
        resultData.setLi28(li28);

        cell = row.getCell(cellNum++);
        String li29 = convertCellValueToString(cell);
        resultData.setLi29(li29);

        cell = row.getCell(cellNum++);
        String li30 = convertCellValueToString(cell);
        resultData.setLi30(li30);


        cell = row.getCell(cellNum++);
        String li31 = convertCellValueToString(cell);
        resultData.setLi31(li31);

        cell = row.getCell(cellNum++);
        String li32 = convertCellValueToString(cell);
        resultData.setLi32(li32);

        cell = row.getCell(cellNum++);
        String li33 = convertCellValueToString(cell);
        resultData.setLi33(li33);

        cell = row.getCell(cellNum++);
        String li34 = convertCellValueToString(cell);
        resultData.setLi34(li34);

        cell = row.getCell(cellNum++);
        String li35 = convertCellValueToString(cell);
        resultData.setLi35(li35);

        cell = row.getCell(cellNum++);
        String li36 = convertCellValueToString(cell);
        resultData.setLi36(li36);

        cell = row.getCell(cellNum++);
        String li37 = convertCellValueToString(cell);
        resultData.setLi37(li37);

        cell = row.getCell(cellNum++);
        String li38 = convertCellValueToString(cell);
        resultData.setLi38(li38);

        cell = row.getCell(cellNum++);
        String li39 = convertCellValueToString(cell);
        resultData.setLi39(li39);

        cell = row.getCell(cellNum++);
        String li40 = convertCellValueToString(cell);
        resultData.setLi40(li40);

        cell = row.getCell(cellNum++);
        String li41 = convertCellValueToString(cell);
        resultData.setLi41(li41);

        cell = row.getCell(cellNum++);
        String li42 = convertCellValueToString(cell);
        resultData.setLi42(li42);

        cell = row.getCell(cellNum++);
        String li43 = convertCellValueToString(cell);
        resultData.setLi43(li43);

        cell = row.getCell(cellNum++);
        String li44 = convertCellValueToString(cell);
        resultData.setLi44(li44);

        cell = row.getCell(cellNum++);
        String li45 = convertCellValueToString(cell);
        resultData.setLi45(li45);

        cell = row.getCell(cellNum++);
        String li46 = convertCellValueToString(cell);
        resultData.setLi46(li46);

        cell = row.getCell(cellNum++);
        String li47 = convertCellValueToString(cell);
        resultData.setLi47(li47);

        cell = row.getCell(cellNum++);
        String li48 = convertCellValueToString(cell);
        resultData.setLi48(li48);

        cell = row.getCell(cellNum++);
        String li49 = convertCellValueToString(cell);
        resultData.setLi49(li49);

        cell = row.getCell(cellNum++);
        String li50 = convertCellValueToString(cell);
        resultData.setLi50(li50);

        cell = row.getCell(cellNum++);
        String li51 = convertCellValueToString(cell);
        resultData.setLi51(li51);

        cell = row.getCell(cellNum++);
        String li52 = convertCellValueToString(cell);
        resultData.setLi52(li52);

        cell = row.getCell(cellNum++);
        String li53 = convertCellValueToString(cell);
        resultData.setLi53(li53);

        cell = row.getCell(cellNum++);
        String li54 = convertCellValueToString(cell);
        resultData.setLi54(li54);

        cell = row.getCell(cellNum++);
        String li55 = convertCellValueToString(cell);
        resultData.setLi55(li55);

        cell = row.getCell(cellNum++);
        String li56 = convertCellValueToString(cell);
        resultData.setLi56(li56);

        return resultData;
    }


}
