package com.hdg.util;

import com.hdg.entity.PageParam;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public class SqlUtil {

    private SqlUtil() {

    }

    /**
     * 在sql后面追加分页sql
     *
     * @param stringBuffer
     * @param pageParam
     */
    public static void appendPageSqlSupport(StringBuffer stringBuffer, PageParam pageParam) {
        if (stringBuffer != null && pageParam != null && (pageParam.getIgnore() == null || pageParam.getIgnore() == false)) {
            int pageNo = pageParam.getPageNo();
            int pageSize = pageParam.getPageSize();
            if (pageNo != 0 && pageSize != 0) {
                stringBuffer.append(" LIMIT ").append((pageNo - 1) * pageSize).append(',').append(pageSize);
            }
        }
    }
}
