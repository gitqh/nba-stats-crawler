package org.gitqh.nba.pipeline;

import org.gitqh.nba.dao.StandingDao;
import org.gitqh.nba.model.Standing;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("StandingPipeline")
public class StandingPipeline implements PageModelPipeline<Standing> {

    @Resource
    private StandingDao standingDao;

    @Override
    public void process(Standing standing, Task task) {
        standingDao.insertStanding(standing);
    }
}
