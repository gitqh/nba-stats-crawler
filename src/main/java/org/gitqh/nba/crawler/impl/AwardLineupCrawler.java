package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.AwardLineup;
import org.gitqh.nba.pipeline.AwardLineupPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/10.
 */
@Component("AwardLineupCrawler")
public class AwardLineupCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("AwardLineupPipeline")
    @Autowired
    private AwardLineupPipeline awardLineupPipeline;

    @Override
    public Spider getSpider() {
        awardLineupPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                awardLineupPipeline, AwardLineup.class).addUrl("http://www.stat-nba.com/award/item7.html").thread(CrawlerConfig.THREAD_NUM);
//        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
//                        .setRetryTimes(CrawlerConfig.RETRY_TIMES)
//                ,awardLineupPipeline,AwardLineup.class).addUrl("http://www.stat-nba.com/award/item0.html").thread(CrawlerConfig.THREAD_NUM);
    }
}
