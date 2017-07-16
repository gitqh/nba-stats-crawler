package org.gitqh.nba.service;

import org.gitqh.nba.constants.enums.DbModeEnum;

/**
 * Created by quhan on 2017/7/4.
 */
public interface CrawlerService {

    boolean startCrawler(String crawlerName, DbModeEnum mode);

    boolean stopCrawler(String crawlerName);

    boolean testCrawler(String crawlerName, String url);
}
