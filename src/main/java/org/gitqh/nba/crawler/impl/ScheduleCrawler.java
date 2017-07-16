package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.Schedule;
import org.gitqh.nba.pipeline.SchedulePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("ScheduleCrawler")
public class ScheduleCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("SchedulePipeline")
    @Autowired
    private SchedulePipeline schedulePipeline;

    @Override
    public Spider getSpider() {
        schedulePipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setRetryTimes(CrawlerConfig.RETRY_TIMES).setTimeOut(CrawlerConfig.TIME_OUT)
                .setCharset(CrawlerConfig.CHARSET)
                .setUserAgent(CrawlerConfig.USER_AGENT), schedulePipeline, Schedule.class)
                .addUrl("http://www.stat-nba.com/gameList_simple.html").thread(CrawlerConfig.THREAD_NUM);
//        return OOSpider.create(Site.me().setRetryTimes(CrawlerConfig.RETRY_TIMES).setTimeOut(CrawlerConfig.TIME_OUT)
//              .setCharset(CrawlerConfig.CHARSET)
//              .setUserAgent(CrawlerConfig.USER_AGENT).setSleepTime(CrawlerConfig.SLEEP_TIME),
//              schedulePipeline, Schedule.class).addUrl("http://www.stat-nba.com/gameList_simple-2008-11.html").thread(CrawlerConfig.THREAD_NUM);
    }
}
