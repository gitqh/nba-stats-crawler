package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.AwardLineupDao;
import org.gitqh.nba.model.AwardLineup;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * @description:
 * @author: quhan
 * @create: 2017-07-12-17:23
 */
@Component("AwardLineupPipeline")
public class AwardLineupPipeline implements PageModelPipeline<AwardLineup> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }


    @Resource
    private AwardLineupDao awardLineupDao;

    @Override
    public void process(AwardLineup awardLineup, Task task) {
        if (mode == DbModeEnum.INSERT) {
            awardLineupDao.insertAwardLineup(awardLineup);
        } else if (mode == DbModeEnum.UPDATE) {
            awardLineupDao.updateAwardLineup(awardLineup);
        }
    }
}
