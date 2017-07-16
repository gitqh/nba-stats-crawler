package org.gitqh.nba.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.crawler.AbstractCrawler;
import org.gitqh.nba.service.CrawlerService;
import org.gitqh.nba.utils.SpringContextUtils;
import org.springframework.stereotype.Service;

import javax.management.JMException;

/**
 * Created by quhan on 2017/7/4.
 */
@Slf4j
@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Override
    public boolean startCrawler(String crawlerName, DbModeEnum mode) {
        AbstractCrawler crawler = (AbstractCrawler) SpringContextUtils.getBean(crawlerName);
        crawler.setDbModeEnum(mode);
        try {
            crawler.startCrawler(crawlerName);
            return true;
        } catch (JMException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean stopCrawler(String crawlerName) {
        AbstractCrawler crawler = (AbstractCrawler) SpringContextUtils.getBean(crawlerName);
        try {
            crawler.stopCrawler(crawlerName);
            return true;
        } catch (JMException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean testCrawler(String crawlerName, String url) {
        AbstractCrawler crawler = (AbstractCrawler) SpringContextUtils.getBean(crawlerName);
        crawler.testCrawler(crawlerName, url);
        return true;
    }
}
