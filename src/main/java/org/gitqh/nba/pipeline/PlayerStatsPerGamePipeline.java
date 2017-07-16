package org.gitqh.nba.pipeline;

import org.gitqh.nba.dao.PlayerStatsPerGameDao;
import org.gitqh.nba.model.PlayerStatsPerGame;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("PlayerStatsPerGamePipeline")
public class PlayerStatsPerGamePipeline implements PageModelPipeline<PlayerStatsPerGame> {

    @Resource
    private PlayerStatsPerGameDao playerStatsPerGameDao;

    @Override
    public void process(PlayerStatsPerGame playerStatsPerGame, Task task) {
        playerStatsPerGameDao.insertPlayerStatsPerGam(playerStatsPerGame);
    }
}
