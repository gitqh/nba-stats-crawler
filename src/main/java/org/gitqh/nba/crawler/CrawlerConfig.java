package org.gitqh.nba.crawler;

/**
 * Created by quhan on 2017/7/4.
 */
public final class CrawlerConfig {
    public static final String CHARSET = "utf-8";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like "
            + "Gecko) Chrome/55.0.2883.87 Safari/537.36";
    public static final int TIME_OUT = 3000;
    public static final int RETRY_TIMES = 3;
    public static final int THREAD_NUM = 5;
    public static final int SLEEP_TIME = 100;

    private CrawlerConfig() {
    }
}
