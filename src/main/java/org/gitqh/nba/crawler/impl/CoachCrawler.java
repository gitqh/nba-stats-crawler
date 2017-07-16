package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.Coach;
import org.gitqh.nba.pipeline.CoachPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/8.
 */
@Component("CoachCrawler")
public class CoachCrawler extends AbstractCrawler implements ICrawler {
    @Qualifier("CoachPipeline")
    @Autowired
    private CoachPipeline coachPipeline;


    @Override
    public Spider getSpider() {
        coachPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                coachPipeline, Coach.class).addUrl("http://www.stat-nba.com/playerList.php").thread(CrawlerConfig.THREAD_NUM);
    }
}
