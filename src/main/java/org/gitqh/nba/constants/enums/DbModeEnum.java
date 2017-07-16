package org.gitqh.nba.constants.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by quhan on 2017/7/8.
 */
public enum DbModeEnum {
    INSERT, UPDATE;

    private static Map<String, DbModeEnum> stringToEnum = Maps.newHashMap();

    static {
        for (DbModeEnum dbModeEnum : values()) {
            stringToEnum.put(dbModeEnum.toString(), dbModeEnum);
        }
    }

    public static DbModeEnum fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
