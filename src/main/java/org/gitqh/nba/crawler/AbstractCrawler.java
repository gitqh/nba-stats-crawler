package org.gitqh.nba.crawler;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.monitor.Monitor;
import org.gitqh.nba.utils.SpringContextUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;

/**
 * Created by quhan on 2017/7/4.
 */
public abstract class AbstractCrawler {

    protected DbModeEnum dbModeEnum;

    public DbModeEnum getDbModeEnum() {
        return dbModeEnum;
    }

    public void setDbModeEnum(DbModeEnum dbModeEnum) {
        this.dbModeEnum = dbModeEnum;
    }

    public void startCrawler(String beanId) throws JMException {
        Preconditions.checkArgument(StringUtils.isNotBlank(beanId));
        SpiderMonitor spiderMonitor = Monitor.getSpiderMonitor();
        ICrawler iCrawler = getCrawler(beanId);
        Spider spider = iCrawler.getSpider();
        spiderMonitor.register(spider);
        spider.start();
    }

    public void stopCrawler(String beanId) throws JMException {
        Preconditions.checkArgument(StringUtils.isNotBlank(beanId));
        ICrawler iCrawler = getCrawler(beanId);
        Spider spider = iCrawler.getSpider();
        spider.stop();
    }

    private ICrawler getCrawler(String beanId) {
        return (ICrawler) SpringContextUtils.getBean(beanId);
    }

    public void testCrawler(String beanId, String url) {
        Preconditions.checkArgument(StringUtils.isNotBlank(beanId));
        Preconditions.checkArgument(StringUtils.isNotBlank(url));
        ICrawler iCrawler = getCrawler(beanId);
        Spider spider = iCrawler.getSpider();
        spider.test(url);
    }
}
