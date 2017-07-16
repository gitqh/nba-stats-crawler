package org.gitqh.nba.utils;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

/**
 * Created by quhan on 2017/7/7.
 */
public final class JsoupUtils {

    private JsoupUtils() {

    }

    public static Map<String, String> getInfo(String html) {
        Map<String, String> infoMap = Maps.newHashMap();
        Document document = Jsoup.parse(html);
        Elements elements = document.getAllElements();
        for (Element element : elements) {
            String text = element.getElementsByClass("row").text();
            String[] values = text.split(":");
            if (values.length >= 2) {
                infoMap.put(values[0], values[1]);
            }
        }
        return infoMap;
    }

    public static List<String> getGameInfo(String html) {
        List<String> gameInfoList = Lists.newArrayList();
        Elements elements = Jsoup.parse(html).select("tr");
        for (Element element : elements) {
            gameInfoList.add(element.getElementsByClass("number").text());
        }
        return gameInfoList;
    }
}
