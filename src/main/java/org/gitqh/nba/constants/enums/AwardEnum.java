package org.gitqh.nba.constants.enums;

/**
 * Created by quhan on 2017/7/9.
 */
public enum AwardEnum {
    MVP(0, "MVP"), ROY(1, "ROY"), DPOY(2, "DPOY"), SMOY(3, "SMOY"),
    MIP(4, "MIP"), FMVP(5, "FMVP"), AMVP(6, "AMVP"), POM(7, "POM"),
    FM(81, "FM"), SM(82, "SM"), TM(83, "TM"), FDM(101, "FDM"),
    SDM(102, "SDM"), FRM(91, "FRM"), SRM(92, "SRM"), ASW(121, "ASW"),
    ASE(122, "ASE"), HOF(16, "HOF"), COY(17, "COY"), POW(18, "POW");

    private int value;

    private String text;

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    AwardEnum(int value, String text) {
        this.text = text;
        this.value = value;
    }

    public static AwardEnum getAwardEnum(int value) {
        AwardEnum awardEnum = null;
        AwardEnum[] awardEnums = AwardEnum.values();
        for (AwardEnum awardEnumElem : awardEnums) {
            if (awardEnumElem.value == value) {
                awardEnum = awardEnumElem;
                break;
            }
        }
        return awardEnum;
    }

    public static String getAwardEnumText(int value) {
        return getAwardEnum(value).text;
    }
}
