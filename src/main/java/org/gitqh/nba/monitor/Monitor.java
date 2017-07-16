package org.gitqh.nba.monitor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * Created by quhan on 2017/7/4.
 */
public final class Monitor {

    private Monitor() {

    }

    private static SpiderMonitor spiderMonitor = new SpiderMonitor() {
        @Override
        protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
            return new CustomSpiderStatus(spider, monitorSpiderListener);
        }
    };


    public static SpiderMonitor getSpiderMonitor() {
        if (spiderMonitor == null) {
            spiderMonitor = new SpiderMonitor() {
                @Override
                protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
                    return new CustomSpiderStatus(spider, monitorSpiderListener);
                }
            };
        }
        return spiderMonitor;
    }
}
