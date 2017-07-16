package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.CoachDao;
import org.gitqh.nba.model.Coach;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/8.
 */
@Component("CoachPipeline")
public class CoachPipeline implements PageModelPipeline<Coach> {
    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }


    @Resource
    private CoachDao coachDao;

    @Override
    public void process(Coach coach, Task task) {
        if (mode == DbModeEnum.INSERT) {
            coachDao.insertCoach(coach);
        } else if (mode == DbModeEnum.UPDATE) {
            coachDao.updateCoach(coach);
        }
    }
}
