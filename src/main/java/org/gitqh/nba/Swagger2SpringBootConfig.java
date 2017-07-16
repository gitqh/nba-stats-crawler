package org.gitqh.nba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * Created by quhan on 2017/7/10.
 */
@Configuration
@EnableSwagger2
@ComponentScan("org.gitqh.nba.web.controller")
public class Swagger2SpringBootConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .groupName("nba-stats-crawler-api")
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("nba爬虫接口" + "API", "nba爬虫接口 后台API文档",
                "http://localhost/", "", "Gitqh License",
                "Gitqh API License URL", null);
    }

    private Predicate<String> paths() {
        return ((Predicate<String>) PathSelectors.regex("/api.*")::apply);
    }


    @Bean
    UiConfiguration uiConfiguration() {
        return new UiConfiguration("validatorUrl");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }
}
