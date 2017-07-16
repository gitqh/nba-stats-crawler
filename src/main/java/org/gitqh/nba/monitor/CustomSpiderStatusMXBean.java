package org.gitqh.nba.monitor;

import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @author code4crafer@gmail.com
 */
public interface CustomSpiderStatusMXBean extends SpiderStatusMXBean {

    String getSchedulerName();
}
