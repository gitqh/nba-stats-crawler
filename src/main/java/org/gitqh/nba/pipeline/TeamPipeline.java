package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.TeamDao;
import org.gitqh.nba.model.Team;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("TeamPipeline")
public class TeamPipeline implements PageModelPipeline<Team> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }

    @Resource
    private TeamDao teamDao;

    @Override
    public void process(Team team, Task task) {
        if (mode == DbModeEnum.INSERT) {
            teamDao.insertTeam(team);
        } else if (mode == DbModeEnum.UPDATE) {
            teamDao.updateTeam(team);
        }
    }
}
