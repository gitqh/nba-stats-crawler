package org.gitqh.nba.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by quhan on 2017/6/29.
 */
@Component("player")
@TargetUrl("http://www.stat-nba.com/player/[\\w]*.html")
@HelpUrl("http://www.stat-nba.com/playerList.php\\?il=E&lil=0")
//@HelpUrl("http://www.stat-nba.com/playerList.php\\?il=[\\w]\\&lil=[\\w]*")
//@HelpUrl("http://www.stat-nba.com/playerList.php?il=A&lil=0")
@Data
public class Player implements AfterExtractor {

    private int teamId;
    @ExtractBy("//div[@class='name']/text()")
    private String cnName;
    private String enName;
    @ExtractBy("//div[@class='detail']")
    private String allName;
    private String position;
    private String height;
    private String weight;
    private String birthday;
    private String birthplace;
    private String college;
    private String highSchool;
    private String draftTeam;
    private int draftRound;
    private int draftPick;
    private String draftYear;
    private String number;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal g']/text()")
    private String g;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal gs']/text()")
    private String gs;
    private int isDeleted;
    private LocalDateTime updateTime;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal pts']/text()")
    private String pts;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal trb']/text()")
    private String trb;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal orb']/text()")
    private String orb;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal drb']/text()")
    private String drb;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal ast']/text()")
    private String ast;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal stl']/text()")
    private String stl;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal blk']/text()")
    private String blk;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal tov']/text()")
    private String tov;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal pf']/text()")
    private String pf;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal mp']/text()")
    private String mp;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal fga']/text()")
    private String fga;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal fg']/text()")
    private String fg;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal threepa']/text()")
    private String pa3;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal threep']/text()")
    private String p3;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal fta']/text()")
    private String fta;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal ft']/text()")
    private String ft;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal w']/text()")
    private String win;
    @ExtractBy("//table[@id='stat_box_tot']/tbody/tr/td[@class='normal l']/text()")
    private String lose;
    private String className;
    @ExtractByUrl
    private String url;

    private int isNow;

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
        if (StringUtils.isNotBlank(allName)) {
            Map<String, String> playerInfoMap = JsoupUtils.getInfo(allName);
            if (StringUtils.isNotBlank(playerInfoMap.get("全　　名"))) {
                this.setAllName(playerInfoMap.get("全　　名").trim());
            } else {
                this.setAllName("");
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("位　　置"))) {
                this.setPosition(playerInfoMap.get("位　　置").trim());
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("身　　高"))) {
                this.setHeight(FilterUtils.getFormatWeightOrHeight(playerInfoMap.get("身　　高").trim(), "h"));
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("体　　重"))) {
                this.setWeight(FilterUtils.getFormatWeightOrHeight(playerInfoMap.get("体　　重").trim(), "w"));
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("出生日期").trim())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    this.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(sdf.parse(playerInfoMap.get("出生日期").trim())));
                } catch (ParseException e) {
                    this.setBirthday("0000-00-00");
                }
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("出生城市"))) {
                this.setBirthplace(playerInfoMap.get("出生城市").trim());
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("大　　学"))) {
                this.setCollege(playerInfoMap.get("大　　学").trim());
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("高　　中"))) {
                this.setHighSchool(playerInfoMap.get("高　　中").trim());
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("球衣号码"))) {
                this.setNumber(FilterUtils.getNumber(playerInfoMap.get("球衣号码").trim()));
            }
            if (StringUtils.isNotBlank(playerInfoMap.get("选秀情况"))) {
                List<String> valueList = Lists.newArrayList();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(playerInfoMap.get("选秀情况").trim());
                while (matcher.find()) {
                    valueList.add(matcher.group());
                }
                if (valueList.size() >= 3) {
                    this.setDraftYear(valueList.get(0));
                    this.setDraftRound(Integer.parseInt(valueList.get(1)));
                    this.setDraftPick(Integer.parseInt(valueList.get(2)));
                    this.setDraftTeam(FilterUtils.getPickTeam(playerInfoMap.get("选秀情况").trim()));
                }
            }
        }
        if (StringUtils.isNotBlank(win)) {
            this.setWin(win.trim());
        } else {
            this.setWin("0");
        }
        if (StringUtils.isNotBlank(lose)) {
            this.setLose(lose.trim());
        } else {
            this.setLose("0");
        }
        if (StringUtils.isNotBlank(g)) {
            this.setG(g.trim());
        } else {
            this.setG("0");
        }
        if (StringUtils.isNotBlank(gs)) {
            this.setGs(gs.trim());
        } else {
            this.setGs("0");
        }
        if (StringUtils.isNotBlank(pts)) {
            this.setPts(pts.trim());
        } else {
            this.setPts("0");
        }
        if (StringUtils.isNotBlank(trb)) {
            this.setTrb(trb.trim());
        } else {
            this.setTrb("0");
        }
        if (StringUtils.isNotBlank(orb)) {
            this.setOrb(orb.trim());
        } else {
            this.setOrb("0");
        }
        if (StringUtils.isNotBlank(drb)) {
            this.setDrb(drb.trim());
        } else {
            this.setDrb("0");
        }
        if (StringUtils.isNotBlank(ast)) {
            this.setAst(ast.trim());
        } else {
            this.setAst("0");
        }
        if (StringUtils.isNotBlank(stl)) {
            this.setStl(stl.trim());
        } else {
            this.setStl("0");
        }
        if (StringUtils.isNotBlank(blk)) {
            this.setBlk(blk.trim());
        } else {
            this.setBlk("0");
        }
        if (StringUtils.isNotBlank(tov)) {
            this.setTov(tov.trim());
        } else {
            this.setTov("0");
        }
        if (StringUtils.isNotBlank(pf)) {
            this.setPf(pf.trim());
        } else {
            this.setPf("0");
        }
        if (StringUtils.isNotBlank(mp)) {
            this.setMp(mp.trim());
        } else {
            this.setMp("0");
        }
        if (StringUtils.isNotBlank(fga)) {
            this.setFga(fga.trim());
        } else {
            this.setFga("0");
        }
        if (StringUtils.isNotBlank(fg)) {
            this.setFg(fg.trim());
        } else {
            this.setFg("0");
        }
        if (StringUtils.isNotBlank(pa3)) {
            this.setPa3(pa3.trim());
        } else {
            this.setPa3("0");
        }
        if (StringUtils.isNotBlank(p3)) {
            this.setP3(p3.trim());
        } else {
            this.setP3("0");
        }
        if (StringUtils.isNotBlank(fta)) {
            this.setFta(fta.trim());
        } else {
            this.setFta("0");
        }
        if (StringUtils.isNotBlank(ft)) {
            this.setFt(ft.trim());
        } else {
            this.setFt("0");
        }
        this.setIsDeleted(0);
        this.setUpdateTime(LocalDateTime.now());
    }

}
