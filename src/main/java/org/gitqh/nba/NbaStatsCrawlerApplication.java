package org.gitqh.nba;

import org.gitqh.nba.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 爬虫启动类
 */
@SpringBootApplication
@MapperScan("org.gitqh.nba.dao")
@EnableWebMvc
public class NbaStatsCrawlerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext1 = SpringApplication.run(NbaStatsCrawlerApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext1);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
            container.addErrorPages(error401Page, error404Page, error500Page);
        });

    }
}
