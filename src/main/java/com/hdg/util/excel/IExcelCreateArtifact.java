package com.hdg.util.excel;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/18.
 */
public interface IExcelCreateArtifact<T> extends ISetCellCallBack<T> {
    XSSFCellStyle getHeadStyle();

    XSSFCellStyle getBodyStyle();

    public void createExcelFile(List<T> list, OutputStream fout);
}
