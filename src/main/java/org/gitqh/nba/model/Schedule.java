package org.gitqh.nba.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.utils.FilterUtils;
import org.gitqh.nba.utils.JsoupUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("Schedule")
@TargetUrl("http://www.stat-nba.com/game/[\\w]*.html")
//@HelpUrl("http://www.stat-nba.com/gameList_simple-2008-11.html")
@HelpUrl("http://www.stat-nba.com/gameList_simple-\\d{4}-\\d{2}.html")
@Data
public class Schedule implements AfterExtractor {
    private int id;
    @ExtractBy("//div[contains(@style,\"float: left;margin-top: 25px;margin-left: 10px;font-size: 16px;font-weight: bold;color: #009CFF\")]/text()")
    private String date;
    @ExtractBy("//div[@id='background']/div/div[@class='title']/text()")
    private String season;
    @ExtractByUrl
    private String url;
    @ExtractBy("//div[@class='basic']/div[1]/div/div/a[1]/text()")
    private String awayTeam;
    @ExtractBy("//div[@class='basic']/div[3]/div/div/a[1]/text()")
    private String homeTeam;
    @ExtractBy("//div[@class='scorebox']/div[1]/div[@class='score']/text()")
    private int awayScore;
    @ExtractBy("//div[@class='scorebox']/div[4]/div[@class='score']/text()")
    private int homeScore;
    @ExtractBy("//div[@class='scorebox']/div[2]/table/tbody/tr[1]/td[@class='number']/text()")
    private int awayFirst;
    @ExtractBy("//div[@class='scorebox']/div[3]/table/tbody/tr[1]/td[@class='number']/text()")
    private int homeFirst;
    @ExtractBy("//div[@class='scorebox']/div[2]/table/tbody/tr[2]/td[@class='number']/text()")
    private int awaySecond;
    @ExtractBy("//div[@class='scorebox']/div[3]/table/tbody/tr[2]/td[@class='number']/text()")
    private int homeSecond;
    @ExtractBy("//div[@class='scorebox']/div[2]/table/tbody/tr[3]/td[@class='number']/text()")
    private int awayThird;
    @ExtractBy("//div[@class='scorebox']/div[3]/table/tbody/tr[3]/td[@class='number']/text()")
    private int homeThird;
    @ExtractBy("//div[@class='scorebox']/div[2]/table/tbody/tr[4]/td[@class='number']/text()")
    private int awayFourth;
    @ExtractBy("//div[@class='scorebox']/div[3]/table/tbody/tr[4]/td[@class='number']/text()")
    private int homeFourth;
    @ExtractBy("//div[@class='scorebox']/div[3]/table")
    private String homeOt;
    @ExtractBy("//div[@class='scorebox']/div[2]/table")
    private String awayOt;
    private String gameType;
    private int isDeleted;
    private LocalDateTime updateTime;


    @Override
    public void afterProcess(Page page) {
//        insert();
        update();
    }

    private void update() {
        this.setUpdateTime(LocalDateTime.now());
        if (StringUtils.isNotBlank(season.trim())) {
            String[] values = FilterUtils.getSplitBySpace(season.trim());
            if (values.length >= 2) {
                this.setGameType(values[1]);
            } else {
                this.setGameType("");
            }
        }
        if (StringUtils.isNotBlank(awayOt)) {
            List<String> gameInfoList = JsoupUtils.getGameInfo(awayOt);
            if (gameInfoList != null && gameInfoList.size() > 4) {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (String element : gameInfoList) {
                    if (i >= 4) {
                        sb.append(element).append(",");
                    }
                    i++;
                }
                this.setAwayOt(sb.toString().substring(0, sb.toString().length() - 1));
            } else {
                this.setAwayOt("");
            }
        }
        if (StringUtils.isNotBlank(homeOt)) {
            List<String> gameInfoList = JsoupUtils.getGameInfo(homeOt);
            if (gameInfoList != null && gameInfoList.size() > 4) {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (String element : gameInfoList) {
                    if (i >= 4) {
                        sb.append(element).append(",");
                    }
                    i++;
                }
                this.setHomeOt(sb.toString().substring(0, sb.toString().length() - 1));
            } else {
                this.setHomeOt("");
            }
        }

    }

    private void insert() {
        if (StringUtils.isNotBlank(season.trim())) {
            String[] values = FilterUtils.getSplitBySpace(season.trim());
            if (values.length >= 2) {
                this.setSeason(values[0].substring(0, values[0].length() - 2));
            } else {
                this.setSeason("");
            }
        }
        if (StringUtils.isNotBlank(awayTeam)) {
            this.setAwayTeam(awayTeam);
        } else {
            this.setAwayTeam("");
        }

        if (StringUtils.isNotBlank(homeTeam)) {
            this.setHomeTeam(homeTeam);
        } else {
            this.setHomeTeam("");
        }

        if (StringUtils.isNotBlank(String.valueOf(homeScore))) {
            this.setHomeScore(homeScore);
        } else {
            this.setHomeScore(0);
        }

        if (StringUtils.isNotBlank(String.valueOf(awayFirst))) {
            this.setAwayFirst(awayFirst);
        } else {
            this.setAwayFirst(0);
        }
        if (StringUtils.isNotBlank(String.valueOf(homeFirst))) {
            this.setHomeFirst(homeFirst);
        } else {
            this.setHomeFirst(0);
        }

        if (StringUtils.isNotBlank(String.valueOf(awaySecond))) {
            this.setAwaySecond(awaySecond);
        } else {
            this.setAwaySecond(0);
        }
        if (StringUtils.isNotBlank(String.valueOf(homeSecond))) {
            this.setHomeSecond(homeSecond);
        } else {
            this.setHomeSecond(0);
        }

        if (StringUtils.isNotBlank(String.valueOf(awayThird))) {
            this.setAwayThird(awayThird);
        } else {
            this.setAwayThird(0);
        }
        if (StringUtils.isNotBlank(String.valueOf(homeThird))) {
            this.setHomeThird(homeThird);
        } else {
            this.setHomeThird(0);
        }

        if (StringUtils.isNotBlank(String.valueOf(awayFourth))) {
            this.setAwayFourth(awayFourth);
        } else {
            this.setAwayFourth(0);
        }
        if (StringUtils.isNotBlank(String.valueOf(homeFourth))) {
            this.setHomeFourth(homeFourth);
        } else {
            this.setHomeFourth(0);
        }

        this.setIsDeleted(0);
        this.setUpdateTime(LocalDateTime.now());
    }

}
