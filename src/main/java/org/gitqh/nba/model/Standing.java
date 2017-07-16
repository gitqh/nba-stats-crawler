package org.gitqh.nba.model;


import lombok.Data;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.time.LocalTime;

/**
 * Created by quhan on 2017/6/30.
 */
@Component("Standing")
@TargetUrl("")
@HelpUrl("")
@Data
public class Standing implements AfterExtractor {

    private int id;
    private int teamId;
    private String season;
    private String division;
    private char conference;
    private int win;
    private int lose;
    private float gb;
    private String away;
    private String home;
    private float psg;
    private float pag;
    private String wdivision;
    private String wconference;
    private String last10;
    private int isDeleted;
    private LocalTime updateTime;

    @Override
    public void afterProcess(Page page) {

    }
}
