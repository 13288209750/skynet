package com.hdg.util.excel;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/18.
 */
public interface ISetCellCallBack<T> {
    /**
     * 设置表格单元的值
     * @param t
     * @param objectList 不需要判断为空,直接调用
     */
    void setCellValue(T t,List<Object> objectList);

    /**
     * 设置表格的标题
     * @param headList 不需要判断为空,直接调用
     */
    void setHead(List<String> headList);

    String setDefaultValue();
}
