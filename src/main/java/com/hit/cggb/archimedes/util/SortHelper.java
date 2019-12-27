package com.hit.cggb.archimedes.util;

import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;
import com.hit.cggb.archimedes.enumtype.OutlierDetectionTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/3,上午午10:11
 * @description 排序助手类
 */
@SuppressWarnings("all")
public class SortHelper<T> {

    private static Logger logger = LoggerFactory.getLogger(SortHelper.class);

    /**
     * 排序MAP，按MAP键值List的数量从大到小排序
     * <p>
     * Map<String, List<T>>, T 为实际类型
     *
     * @param aMapOfList 泛型类型
     */
    public List<String> sortMapByListSize(Map<String, List<T>> aMapOfList) {
        List<String> sortedList = new ArrayList<>();
        try {
            List<String> keyList = new ArrayList<>(aMapOfList.keySet());
            for (int i = keyList.size() - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (aMapOfList.get(keyList.get(j)).size() >= aMapOfList.get(keyList.get(j + 1)).size()) {
                        String tempKey = keyList.get(j + 1);
                        keyList.set(j + 1, keyList.get(j));
                        keyList.set(j, tempKey);
                    }
                }
                sortedList.add(keyList.get(i));
            }
        } catch (Throwable e) {
            logger.error("sort map by list size come across a error, message is " + e);
        }
        return sortedList;
    }

    /**
     * 排序List，按MAP键值日期的早晚从早到晚排序
     * <p>
     * List<Map<String, Object>>, Object 为时间类型
     *
     * @param aListOfMapDate 待排序列表
     */
    public List<Map<String, Object>> sortListByMapDate(List<Map<String, Object>> aListOfMapDate, OrderTypeEnum orderType) {
        List<Map<String, Object>> sortedList = new ArrayList<>();
        if (OrderTypeEnum.ASC.equals(orderType)) {
            for (int i = aListOfMapDate.size() - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    Date earlyDate = ThreadSafeDateUtil.parseDateTimeMillisecond((String) aListOfMapDate.get(j).get("date"));
                    Date lateDate = ThreadSafeDateUtil.parseDateTimeMillisecond((String) aListOfMapDate.get(j + 1).get("date"));
                    if (earlyDate.getTime() <= lateDate.getTime()) {
                        Map<String, Object> tempMap = aListOfMapDate.get(j + 1);
                        aListOfMapDate.set(j + 1, aListOfMapDate.get(j));
                        aListOfMapDate.set(j, tempMap);
                    }
                }
                sortedList.add(aListOfMapDate.get(i));
            }
        } else if (OrderTypeEnum.DESC.equals(orderType)) {
            for (int i = aListOfMapDate.size() - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    Date earlyDate = ThreadSafeDateUtil.parseDateTimeMillisecond((String) aListOfMapDate.get(j).get("date"));
                    Date lateDate = ThreadSafeDateUtil.parseDateTimeMillisecond((String) aListOfMapDate.get(j + 1).get("date"));
                    if (earlyDate.getTime() >= lateDate.getTime()) {
                        Map<String, Object> tempMap = aListOfMapDate.get(j + 1);
                        aListOfMapDate.set(j + 1, aListOfMapDate.get(j));
                        aListOfMapDate.set(j, tempMap);
                    }
                }
                sortedList.add(aListOfMapDate.get(i));
            }
        } else {
            throw new IllegalArgumentException("meet unknown order type of " + orderType);
        }
        return sortedList;
    }
}
