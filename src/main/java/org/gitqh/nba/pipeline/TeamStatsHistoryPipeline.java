package org.gitqh.nba.pipeline;

import org.gitqh.nba.dao.TeamStatsHistoryDao;
import org.gitqh.nba.model.TeamStatsHistory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("TeamStatsHistoryPipeline")
public class TeamStatsHistoryPipeline implements PageModelPipeline<TeamStatsHistory> {

    @Resource
    private TeamStatsHistoryDao teamStatsHistoryDao;

    @Override
    public void process(TeamStatsHistory teamStatsHistory, Task task) {
        teamStatsHistoryDao.insertTeamStatsHistory(teamStatsHistory);
    }
}
