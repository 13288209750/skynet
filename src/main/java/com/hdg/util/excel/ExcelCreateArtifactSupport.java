package com.hdg.util.excel;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/18.
 */
public abstract class ExcelCreateArtifactSupport<T> implements IExcelCreateArtifact<T> {

    private XSSFWorkbook xwb;

    public ExcelCreateArtifactSupport() {
        xwb=new XSSFWorkbook();
    }

    public ExcelCreateArtifactSupport(XSSFWorkbook xwb) {
        this.xwb = xwb;
    }

    private static final Logger logger = LoggerFactory.getLogger(ExcelCreateArtifactSupport.class);


    @Override
    public void createExcelFile(List<T> list, OutputStream fout) {
        if (list == null || fout == null) {
            //两者之一为空，跳过
            if (logger.isErrorEnabled()) {
                logger.error("使用Excel工具类时必须提供有效的参数!!!");
            }
            return;
        }
        XSSFSheet sheet = xwb.createSheet();
        //添加style
        XSSFCellStyle headStyle = this.getHeadStyle();
        XSSFCellStyle bodyStyle = this.getBodyStyle();
        //构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        List<String> heads = new ArrayList<String>();
        this.setHead(heads);
        if (heads == null) {
            if (logger.isErrorEnabled()) {
                logger.error("请重写设置标题回调方法!!!");
            }
            return;
        }
        for (int i = 0, headsize = heads.size(); i < headsize; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(heads.get(i));
        }
        //构建表体
        int size = list.size();
        int tem = -1;
        String temName = "";
        if (list != null && size > 0) {
            T t = null;
            for (int j = 0; j < size; j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                t = list.get(j);
                List<Object> values = new ArrayList<Object>();
                this.setCellValue(t, values);
                if (values.isEmpty()) {
                    if (logger.isErrorEnabled()) {
                        logger.error("请重写setCellValue回调方法!!!");
                    }
                    return;
                } else {
                    int len = 0;
                    if (heads.size() != values.size()) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("标题长度和字段长度不相等!!!标题长度:[{}]\t字段长度:[{}]", heads.size(), values.size());
                        }
                        if (heads.size() > values.size()) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("由于您的标题长度大于实际数据字段长度，会导致部分单元为默认值!!!");
                            }
                            //实际最大偏移长度以标题的长度为准
                            len = heads.size();
                        } else {
                            if (logger.isWarnEnabled()) {
                                logger.warn("由于您的标题长度小于实际数据字段长度，会导致部分单元为默认值!!!");
                            }
                            //实际最大偏移长度以字段长度为准
                            len = values.size();
                        }
                    } else {
                        //相等，随便哪个都行
                        len = heads.size();
                    }
                    for (int z = 0; z < len; z++) {
                        cell = bodyRow.createCell(z);
                        cell.setCellStyle(bodyStyle);
                        Object obj = null;
                        //只有当且仅当z<values.size()的时候才能从values中取值，否则报错;
                        if (z < values.size()) {
                            obj = values.get(z);
                        }
                        cell.setCellValue(getDefaultValue(obj,setDefaultValue()));
                    }
                }
            }
        }
        try {
            xwb.write(fout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public XSSFCellStyle getHeadStyle() {
        // TODO Auto-generated method stub
        XSSFCellStyle cellStyle = xwb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        XSSFFont font = xwb.createFont();
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    @Override
    public XSSFCellStyle getBodyStyle() {
        // TODO Auto-generated method stub
        // 创建单元格样式
        XSSFCellStyle cellStyle = xwb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = xwb.createFont();
        // 设置字体加粗
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    @Override
    public String setDefaultValue() {
        return "-";
    }

    public String getDefaultValue(Object str,String defaultValue) {
        if (str == null || str.equals("")) {
            return defaultValue;
        } else {
            return str.toString();
        }
    }

    public XSSFWorkbook getXwb() {

        return xwb;
    }

    public void setXwb(XSSFWorkbook xwb) {

        this.xwb = xwb;
    }
}
