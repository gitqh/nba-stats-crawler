package org.gitqh.nba.model;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.constants.BasketBallTerms;
import org.gitqh.nba.utils.FilterUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.time.LocalDateTime;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("PlayerStatsCareer")
@TargetUrl("http://www.stat-nba.com/player/989.html")
//@HelpUrl("http://www.stat-nba.com/player/\\d*.html")
//@HelpUrl("http://www.stat-nba.com/player/989.html")
@Data
@ExtractBy(value = "//table[@id='stat_box_tot']/tbody/tr", multi = true)
public class PlayerStatsCareer implements AfterExtractor {
    private int id;
    private int playerId;
    @ExtractBy("//body/a[2]/text()")
    private String team;
    private int g;
    private int gs;
    private String type;
    @ExtractBy("//body/a[1]/text()")
    private String season;
    private String league;
    private int isNow;
    private int isDeleted;
    private LocalDateTime updateTime;
    private int pts;
    private int trb;
    private int orb;
    private int drb;
    private int ast;
    private int stl;
    private int blk;
    private int tov;
    private int pf;
    private int mp;
    private int fga;
    private int fg;
    private int pa3;
    private int p3;
    private int fta;
    private int ft;
    @ExtractBy("//body/text()")
    private String text;

    @Override
    public void afterProcess(Page page) {
        System.out.println(text);
        if (StringUtils.isNotBlank(team)) {
            if (team.contains(BasketBallTerms.ABA)) {
                this.setType(BasketBallTerms.ABA);
            } else {
                this.setType(BasketBallTerms.NBA);
            }
        }
        if (StringUtils.isNotBlank(text)) {
            System.out.println(text.trim().replace("   ", " -1 "));
            String[] values = text.trim().replace("   ", " -1 ").split(" ");
            this.setG(FilterUtils.formatString(values[0]));
            this.setGs(FilterUtils.formatString(values[1]));
            this.setPts(FilterUtils.formatString(values[20]));
            this.setTrb(FilterUtils.formatString(values[12]));
            this.setOrb(FilterUtils.formatString(values[13]));
            this.setDrb(FilterUtils.formatString(values[14]));
            this.setAst(FilterUtils.formatString(values[15]));
            this.setStl(FilterUtils.formatString(values[16]));
            this.setBlk(FilterUtils.formatString(values[17]));
            this.setTov(FilterUtils.formatString(values[18]));
            this.setPf(FilterUtils.formatString(values[19]));
            this.setMp(FilterUtils.formatString(values[2]));
            this.setFga(FilterUtils.formatString(values[5]));
            this.setFg(FilterUtils.formatString(values[4]));
            this.setPa3(FilterUtils.formatString(values[8]));
            this.setP3(FilterUtils.formatString(values[7]));
            this.setFta(FilterUtils.formatString(values[11]));
            this.setFt(FilterUtils.formatString(values[10]));
        }
        this.setIsDeleted(0);
        this.setUpdateTime(LocalDateTime.now());
    }
}
