package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.pipeline.StandingPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("StandingCrawler")
public class StandingCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("StandingPipeline")
    @Autowired
    private StandingPipeline standingPipeline;

    @Override
    public Spider getSpider() {
        return null;
    }
}
