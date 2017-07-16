package org.gitqh.nba.model;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.constants.BasketBallTerms;
import org.gitqh.nba.constants.enums.AwardEnum;
import org.gitqh.nba.utils.FilterUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.time.LocalDateTime;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("Award")
//@TargetUrl("http://www.stat-nba.com/award/item1.html")
@TargetUrl("http://www.stat-nba.com/award/item(((?![7-9])\\d)|(\\d((?![0-5|8-9])\\d))).html")
//@HelpUrl("http://www.stat-nba.com/award/item1.html")
@ExtractBy(value = "//div[@class='stat_box']/table/tbody/tr", multi = true)
@Data
public class Award implements AfterExtractor {
    @ExtractBy("//")
    private String test;
    private int id;
    @ExtractBy("//body/a[1]/text()")
    private String cnName;
    @ExtractBy("//body/text(2)")
    private String season;
    private String timePeriod;
    private String award;
    @ExtractBy("//body/text(5)")
    private String league;
    private int isDeleted;
    private LocalDateTime updateTime;
    @ExtractByUrl
    private String url;

    @Override
    public void afterProcess(Page page) {
        insert();
    }

    private void insert() {
        String num = FilterUtils.extraNumFromAwardUrl(url);
        if (StringUtils.isNotBlank(num)) {
            this.setAward(AwardEnum.getAwardEnumText(Integer.parseInt(num)));
        }
        if (StringUtils.isNumeric(league) | StringUtils.isBlank(league)) {
            if (StringUtils.isNumeric(league) | StringUtils.isBlank(league)) {
                this.setLeague(BasketBallTerms.NBA);
            }
            this.setIsDeleted(0);
            this.setUpdateTime(LocalDateTime.now());
        }
    }
}
