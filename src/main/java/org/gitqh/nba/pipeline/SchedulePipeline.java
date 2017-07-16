package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.ScheduleDao;
import org.gitqh.nba.model.Schedule;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/4.
 */
@Component("SchedulePipeline")
public class SchedulePipeline implements PageModelPipeline<Schedule> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }

    @Resource
    private ScheduleDao scheduleDao;

    @Override
    public void process(Schedule schedule, Task task) {
        if (mode == DbModeEnum.INSERT) {
            scheduleDao.insertSchedule(schedule);
        } else if (mode == DbModeEnum.UPDATE) {
            scheduleDao.updateSchedule(schedule);
        }
    }

}
