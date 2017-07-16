package org.gitqh.nba.constants.enums;

import org.gitqh.nba.utils.Constants;

/**
 * Created by quhan on 2017/7/4.
 */
public enum DivisionEnum {
    ATLANTIC(1, "ATLANTIC", "大西洋赛区") {
        @Override
        public String getConference() {
            return Constants.EAST;
        }
    }, CENTRAL(2, "CENTRAL", "中部赛区") {
        @Override
        public String getConference() {
            return Constants.EAST;
        }
    }, SOUTHEAST(3, "SOUTHEAST", "东北赛区") {
        @Override
        public String getConference() {
            return Constants.EAST;
        }
    }, NORTHWEST(4, "NORTHWEST", "西南赛区"),
    PACIFIC(5, "PACIFIC", "太平洋赛区"), SOUTHWEST(6, "SOUTHWEST", "西北赛区");


    DivisionEnum(int value, String enName, String cnName) {
        this.enName = enName;
        this.value = value;
        this.cnName = cnName;
    }

    private String enName;

    private String cnName;

    private int value;

    public int getValue() {
        return value;
    }

    public String getEnName() {
        return enName;
    }

    public String getCnName() {
        return cnName;
    }

    public String getConference() {
        return Constants.WEST;
    }

    public DivisionEnum getDivisionEnum(int value) {
        DivisionEnum divisionEnum = null;
        DivisionEnum[] divisionEnums = DivisionEnum.values();
        for (DivisionEnum divisionEnumElem : divisionEnums) {
            if (divisionEnumElem.value == value) {
                divisionEnum = divisionEnumElem;
                break;
            }
        }
        return divisionEnum;
    }

}
