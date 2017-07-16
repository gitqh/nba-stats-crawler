package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.Award;
import org.gitqh.nba.pipeline.AwardPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("AwardCrawler")
public class AwardCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("AwardPipeline")
    @Autowired
    private AwardPipeline awardPipeline;

    @Override
    public Spider getSpider() {
        awardPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                awardPipeline, Award.class).addUrl("http://www.stat-nba.com/award/item0.html").thread(CrawlerConfig.THREAD_NUM);
//        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
//                        .setRetryTimes(CrawlerConfig.RETRY_TIMES)
//                ,awardPipeline,Award.class).addUrl("http://www.stat-nba.com/award/item0.html").thread(CrawlerConfig.THREAD_NUM);
    }
}
