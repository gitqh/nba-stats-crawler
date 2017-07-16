package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.PlayerStatsCareer;
import org.gitqh.nba.pipeline.PlayerStatsCareerPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("PlayerStatsCareerCrawler")
public class PlayerStatsCareerCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("PlayerStatsCareerPipeline")
    @Autowired
    private PlayerStatsCareerPipeline playerStatsCareerPipeline;

    @Override
    public Spider getSpider() {
//        playerStatsCareerPipeline.setMode(super.dbModeEnum);
//        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
//                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
//                playerStatsCareerPipeline, PlayerStatsCareer.class).addUrl("http://www.stat-nba.com/playerList.php")
//                .thread(CrawlerConfig.THREAD_NUM);

        playerStatsCareerPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                playerStatsCareerPipeline, PlayerStatsCareer.class).addUrl("http://www.stat-nba.com/player/989.html")
                .thread(CrawlerConfig.THREAD_NUM);
    }
}
