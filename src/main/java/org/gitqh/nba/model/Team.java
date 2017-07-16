package org.gitqh.nba.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.utils.FilterUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;


import java.time.LocalDateTime;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("team")
@TargetUrl("https://nba.hupu.com/teams/[\\w]*")
@HelpUrl("https://nba.hupu.com/teams")
@Data
public class Team implements AfterExtractor {
    private String enName;
    @ExtractBy(value = "//p[@class='bread-crumbs']/b/text()")
    private String cnName;
    private String shortName;
    @ExtractBy(value = "//div[@class='font']/p[1]/text()")
    private String date;
    private String conference;
    @ExtractBy(value = "//div[@class='font']/p[2]/text()")
    private String division;
    private int isDeleted = 0;
    private LocalDateTime updateTime;

    @Override
    public void afterProcess(Page page) {
        insert();
    }

    private void insert() {
        if (StringUtils.isNotBlank(cnName)) {
            String[] values = cnName.split("\\(");
            if (values.length >= 2) {
                this.setCnName(values[0].trim());
                String enAllName = values[1].substring(0, values[1].length() - 1);
                this.setEnName(enAllName);
                this.setShortName(FilterUtils.getEnShortName(enAllName));
            }
        }
        if (StringUtils.isNotBlank(date)) {
            this.setDate(date.substring(date.length() - 5, date.length() - 1));
        }
        if (StringUtils.isNotBlank(division)) {
            String divisionValue = (division.split("分区："))[1];
            this.setDivision(divisionValue);
            this.setConference(FilterUtils.getConferenceWithDivision(divisionValue));
        }
        this.setIsDeleted(0);
        this.setUpdateTime(LocalDateTime.now());
    }

}
