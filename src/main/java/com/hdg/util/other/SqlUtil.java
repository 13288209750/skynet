package com.hdg.util.other;

import com.hdg.entity.PageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public class SqlUtil {

    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

    private SqlUtil() {

    }

    /**
     * 在sql后面追加分页sql
     *
     * @param stringBuffer
     * @param pageParam
     */
    public static void appendPageSqlSupport(StringBuffer stringBuffer, PageParam pageParam) {
        if (logger.isInfoEnabled()) {
            logger.info("pageParam{}", pageParam);
        }
        if (stringBuffer != null && pageParam != null && (pageParam.getIgnore() == null || pageParam.getIgnore() == false)) {
            int pageNo = pageParam.getPageNo() == null ? 0 : pageParam.getPageNo();
            int pageSize = pageParam.getPageSize() == null ? 0 : pageParam.getPageSize();
            if (pageNo != 0 && pageSize != 0) {
                stringBuffer.append(" LIMIT ").append((pageNo - 1) * pageSize).append(',').append(pageSize);
            }
        }
    }

    public static Map<String, Integer> getPageLimt(PageParam pageParam) {
        Map<String, Integer> map = new HashMap<>();
        if (pageParam != null) {
            int pageNo = pageParam.getPageNo() == null ? 1 : pageParam.getPageNo();
            int pageSize = pageParam.getPageSize() == null ? 0 : pageParam.getPageSize();
            map.put("pageIndex", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
        }else {
            map.put("pageIndex", 0);
            map.put("pageSize", 0);
        }
        return map;
    }

    public static Object[] appendUpdateSqlSupport(StringBuffer stringBuffer, Map<String, Object> setMap, boolean isSet) {
        if (stringBuffer == null || setMap == null || setMap.isEmpty()) {
            return new Object[]{};
        }
        if (isSet) {
            //外面没有提供set
            stringBuffer.append(" set ");
        } else {
            //外面已经提供了set
            stringBuffer.append(" , ");
        }
        Set<Map.Entry<String, Object>> set = setMap.entrySet();
        Object[] obj = new Object[setMap.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : set) {
            String keyName = entry.getKey();
            Object objVal = entry.getValue();
            stringBuffer.append(keyName);
            stringBuffer.append(" = ");
            stringBuffer.append(" ? ");
            obj[i] = objVal;
            if (i++ != setMap.size() - 1) {
                stringBuffer.append(" , ");
            }
        }
        return obj;
    }

    public static Object[] appendQueryParamSqlSupport(StringBuffer stringBuffer, Map<String, Object> paramMap, boolean isWhere) {
        if (paramMap == null || stringBuffer == null || paramMap.isEmpty()) {
            return new Object[]{};
        }
        Set<Map.Entry<String, Object>> set = paramMap.entrySet();
        Object[] obj = new Object[paramMap.size()];
        int index = 0;
        if (isWhere) {
            stringBuffer.append(" where ");
        }
        for (Map.Entry<String, Object> entry : set) {
            String paramName = entry.getKey();
            if (index != 0) {
                stringBuffer.append(" and ");
            }
            stringBuffer.append(paramName);
            stringBuffer.append(" = ");
            stringBuffer.append(" ? ");
            Object value = entry.getValue();
            obj[index++] = value;
        }
        return obj;
    }
}
