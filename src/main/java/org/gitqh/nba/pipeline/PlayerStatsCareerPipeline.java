package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.PlayerStatsCareerDao;
import org.gitqh.nba.model.PlayerStatsCareer;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("PlayerStatsCareerPipeline")
public class PlayerStatsCareerPipeline implements PageModelPipeline<PlayerStatsCareer> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }

    @Resource
    private PlayerStatsCareerDao playerStatsCareerDao;

    @Override
    public void process(PlayerStatsCareer playerStatsCareer, Task task) {
        if (mode == DbModeEnum.INSERT) {
            playerStatsCareerDao.insertPlayerStatsCareer(playerStatsCareer);
        } else if (mode == DbModeEnum.UPDATE) {
            playerStatsCareerDao.updatePlayerStatsCareer(playerStatsCareer);
        }

    }
}
