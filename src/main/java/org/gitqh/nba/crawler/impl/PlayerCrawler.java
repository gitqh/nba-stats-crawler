package org.gitqh.nba.crawler.impl;

import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.crawler.CrawlerConfig;
import org.gitqh.nba.crawler.ICrawler;
import org.gitqh.nba.model.Player;
import org.gitqh.nba.pipeline.PlayerPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("PlayerCrawler")
public class PlayerCrawler extends AbstractCrawler implements ICrawler {

    @Qualifier("PlayerPipeline")
    @Autowired
    private PlayerPipeline playerPipeline;


    @Override
    public Spider getSpider() {
        playerPipeline.setMode(super.dbModeEnum);
        return OOSpider.create(Site.me().setUserAgent(CrawlerConfig.USER_AGENT).setCharset(CrawlerConfig.CHARSET).setTimeOut(CrawlerConfig.TIME_OUT)
                        .setRetryTimes(CrawlerConfig.RETRY_TIMES).setSleepTime(CrawlerConfig.SLEEP_TIME),
                playerPipeline, Player.class).addUrl("http://www.stat-nba.com/playerList.php").thread(CrawlerConfig.THREAD_NUM);
    }
}
