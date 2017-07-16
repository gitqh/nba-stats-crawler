package org.gitqh.nba.model;

import com.google.common.collect.Maps;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;


import java.util.Map;

/**
 * 页面解析测试类
 */
public class TestPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("http://www.stat-nba.com/player/526.html").all());
        page.putField("text", page.getHtml().xpath("//div[@class='detail']/div[@class='row'][1]/text()").toString());
        System.out.println(page.getResultItems().get("text").toString());
//        System.out.println(page.getResultItems().get("name").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new TestPageProcessor()).addUrl("http://www.stat-nba.com/player/526.html").thread(5).run();
        StringBuilder sb = new StringBuilder();
        sb.append("");

        Map<String, String> playerInfoMap = Maps.newHashMap();
        String className = "row";
        Document document = Jsoup.parse(sb.toString());
        XElements childDocument = Xsoup.compile("//div[@class='detail']/div[@class='row']").evaluate(document);
        Elements elements = childDocument.getElements();
        for (Element element : elements) {
            String text = element.getElementsByClass("row").text();
            String[] values = text.split(":");
            if (values.length >= 2) {
                playerInfoMap.put(values[0], values[1]);
            }
        }
        System.out.println(playerInfoMap);
    }
}
