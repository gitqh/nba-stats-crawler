package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.Team;
import org.gitqh.nba.pipeline.TeamPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("TeamCrawler")
public class TeamCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("TeamPipeline")
    @Autowired
    private TeamPipeline teamPipeline;


    @Override
    public Spider getSpider() {
        teamPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                teamPipeline, Team.class).addUrl("https://nba.hupu.com/teams").thread(CrawlerConfig.THREAD_NUM);
    }
}
