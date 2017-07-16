package org.gitqh.nba.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("Stats")
@TargetUrl("")
@HelpUrl("")
@Data
public class Stats implements AfterExtractor {

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

    @Override
    public void afterProcess(Page page) {

    }
}
