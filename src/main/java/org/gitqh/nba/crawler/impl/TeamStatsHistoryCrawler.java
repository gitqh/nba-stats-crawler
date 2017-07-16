package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.pipeline.TeamStatsHistoryPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("TeamStatsHistoryCrawler")
public class TeamStatsHistoryCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("TeamStatsHistoryPipeline")
    @Autowired
    private TeamStatsHistoryPipeline teamStatsHistoryPipeline;

    @Override
    public Spider getSpider() {
        return null;
    }
}
