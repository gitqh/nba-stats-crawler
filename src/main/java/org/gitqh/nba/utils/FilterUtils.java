package org.gitqh.nba.utils;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.gitqh.nba.constants.enums.DivisionEnum;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by quhan on 2017/7/3.
 */
public final class FilterUtils {

    private FilterUtils() {

    }
    /**
     * 返回球队名称的首字母简写
     *
     * @param name
     * @return
     */
    public static String getEnShortName(String name) {
        String res;
        String[] nameWords = name.split(" ");
        if (nameWords.length > 2) {
            StringBuilder sb = new StringBuilder();
            for (String nameWord : nameWords) {
                sb.append(nameWord.substring(0, 1));
            }
            res = sb.toString();
        } else {
            res = nameWords[0].substring(0, 3).toUpperCase();
        }
        return res;
    }

    /**
     * 根据分赛区返回大分区
     *
     * @param division
     * @return
     */
    public static String getConferenceWithDivision(String division) {
        return division.equals(DivisionEnum.ATLANTIC.getCnName())
                || division.equals(DivisionEnum.CENTRAL.getCnName())
                || division.equals(DivisionEnum.SOUTHEAST.getCnName()) ? "e" : "w";
    }

    public static String getFormatWeightOrHeight(String input, String mode) {
        String[] values = input.split("\\(");
        if (values.length >= 2) {
            if (mode.equals("h")) {
                return values[0].substring(0, values[0].length() - 1).replace(".", "");
            } else {
                return values[0].substring(0, values[0].length() - 2);
            }
        }
        return "0";
    }

    public static int getMultiple(int g, float avg) {
        float res = g * avg;
        return (int) res;
    }

    public static String getPickTeam(String input) {
        String[] values1 = input.split("被");
        if (values1.length >= 2) {
            String[] values2 = values1[1].split("选中");
            return values2[0];
        }
        return "";
    }

    public static String getNumber(String input) {
        String[] values = input.split(" ");
        if (values.length >= 2) {
            return values[0];
        }
        return "";
    }


    /**
     * 提取括号内的字符串
     *
     * @param input
     * @return
     */
    public static String extractBracketInnerStr(String input) {
        if (!input.contains("(") || !input.contains(")")) {
            return "";
        }
        List<String> resultList = Lists.newArrayList();
        int m = 0, n = 0, count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                if (count == 0) {
                    m = i;
                    count++;
                }
            }
            if (input.charAt(i) == ')') {
                count--;
                if (count == 0) {
                    n = i;
                    resultList.add(input.substring(m + 1, n).trim());
                }
            }
        }
        if (count != 0) {
            return "";
        }
        return StringUtils.join(" ", resultList).replace("赛季", "").replace("[", "").replace("]", "");
    }

    /**
     * 根据回车符切分字符串
     * 输入： 123\n125
     * 输出 String[]{123,125}
     *
     * @param input
     * @return
     */
    public static String[] getSplitBySpace(String input) {
        return input.split(" ");
    }


    public static String extraNumFromAwardUrl(String url) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static int formatString(String input) {
        if (StringUtils.isBlank(input)) {
            return  -1;
        } else {
            return Integer.parseInt(input);
        }
    }
}
