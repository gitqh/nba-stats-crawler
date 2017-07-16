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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by quhan on 2017/7/8.
 */
@Component("Coach")
//@TargetUrl("http://www.stat-nba.com/coach/55.html")
//@HelpUrl("http://www.stat-nba.com/playerList.php\\?il=D\\&lil=C")
@TargetUrl("http://www.stat-nba.com/coach/[\\w]*.html")
@HelpUrl("http://www.stat-nba.com/playerList.php\\?il=[\\w]\\&lil=[\\w]*")
//@HelpUrl("http://www.stat-nba.com/playerList.php\\?il=0&lil=0")
@Data
public class Coach implements AfterExtractor {

    private int coachId;
    @ExtractBy("//div[@class='name']/text()")
    private String cnName;
    private String enName;

    private String birthday;
    @ExtractBy("//div[@class='detail']")
    private String birthplace;
    private String college;
    private String highSchool;
    private int teachYear;
    private int teachNum;
    private int teachPlayoff;
    private int isDeleted;
    private LocalDateTime updateTime;
    private int win;
    private int lose;
    private int playoffWin;
    private int playoffLose;
    private String coy;
    private int finalNum;
    private int champion;
    @ExtractByUrl
    private String url;


    @Override
    public void afterProcess(Page page) {
        insert();
    }

    private void insert() {
        if (StringUtils.isNotBlank(cnName)) {
            String[] values = cnName.split("/");
            if (values.length >= 2) {
                this.setCnName(values[0].trim());
                this.setEnName(values[1].trim());
            } else {
                this.setEnName(values[0].trim());
            }
        }
        if (StringUtils.isNotBlank(birthplace)) {
            Map<String, String> coachInfo = JsoupUtils.getInfo(birthplace);
            if (StringUtils.isNotBlank(coachInfo.get("出生日期").trim())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    this.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(sdf.parse(coachInfo.get("出生日期").trim())));
                } catch (ParseException e) {
                    this.setBirthday("0000-00-00");
                }
            }
            if (StringUtils.isNotBlank(coachInfo.get("出生城市"))) {
                this.setBirthplace(coachInfo.get("出生城市").trim());
            } else {
                this.setBirthplace("");
            }
            if (StringUtils.isNotBlank(coachInfo.get("大　　学"))) {
                this.setCollege(coachInfo.get("大　　学").trim());
            }
            if (StringUtils.isNotBlank(coachInfo.get("高　　中"))) {
                this.setHighSchool(coachInfo.get("高　　中").trim());
            }
            if (StringUtils.isNotBlank(coachInfo.get("执教生涯"))) {
                String[] values = coachInfo.get("执教生涯").trim().split(" ");
                if (values.length >= 3) {
                    this.setTeachYear(Integer.parseInt(values[0].replace("年", "")));
                    this.setTeachNum(Integer.parseInt(values[1].replace("支球队", "")));
                    this.setTeachPlayoff(Integer.parseInt(values[2].replace("次进入季后赛", "")));
                }
            }
            if (StringUtils.isNotBlank(coachInfo.get("常  规  赛"))) {
                String[] values = coachInfo.get("常  规  赛").trim().split(" ");
                if (values.length >= 2) {
                    String[] values2 = values[1].split("胜");
                    if (values2.length >= 2) {
                        this.setWin(Integer.parseInt(values2[0].trim()));
                        this.setLose(Integer.parseInt(values2[1].replace("负", "").trim()));
                    }
                }
            }

            if (StringUtils.isNotBlank(coachInfo.get("季  后  赛"))) {
                String[] values = coachInfo.get("季  后  赛").trim().split(" ");
                if (values.length >= 2) {
                    String[] values2 = values[1].split("胜");
                    if (values2.length >= 2) {
                        this.setPlayoffWin(Integer.parseInt(values2[0].trim()));
                        this.setPlayoffLose(Integer.parseInt(values2[1].replace("负", "").trim()));
                    }
                }
            }
            if (StringUtils.isNotBlank(coachInfo.get("总  决  赛"))) {
                String[] values = coachInfo.get("总  决  赛").trim().split("次");
                if (values.length >= 1) {
                    this.setFinalNum(Integer.parseInt(values[0]));
                }
            }
            if (StringUtils.isNotBlank(coachInfo.get("总  冠  军"))) {
                String[] values = coachInfo.get("总  冠  军").trim().split("个");
                if (values.length >= 1) {
                    this.setChampion(Integer.parseInt(values[0]));
                }
            }
            if (StringUtils.isNotBlank(coachInfo.get("最佳教练"))) {
                this.setCoy(FilterUtils.extractBracketInnerStr(coachInfo.get("最佳教练").trim()));
            }
            this.setIsDeleted(0);
            this.setUpdateTime(LocalDateTime.now());
        }
    }
}
