package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.AwardDao;
import org.gitqh.nba.model.Award;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("AwardPipeline")
public class AwardPipeline implements PageModelPipeline<Award> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }


    @Resource
    private AwardDao awardDao;

    @Override
    public void process(Award award, Task task) {
        if (mode == DbModeEnum.INSERT) {
            awardDao.insertAward(award);
        } else if (mode == DbModeEnum.UPDATE) {
            awardDao.updateAward(award);
        }
    }
}
