package org.gitqh.nba.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by quhan on 2017/7/4.
 */
public final class SpringContextUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    private SpringContextUtils() {
    }
}
