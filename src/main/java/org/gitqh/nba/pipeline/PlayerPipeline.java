package org.gitqh.nba.pipeline;

import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.dao.PlayerDao;
import org.gitqh.nba.model.Player;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * @author gitqh88@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("PlayerPipeline")
public class PlayerPipeline implements PageModelPipeline<Player> {

    private DbModeEnum mode;

    public DbModeEnum getMode() {
        return mode;
    }

    public void setMode(DbModeEnum mode) {
        this.mode = mode;
    }


    @Resource
    private PlayerDao playerDao;

    @Override
    public void process(Player player, Task task) {
        if (mode == DbModeEnum.INSERT) {
            playerDao.insertPlayer(player);
        } else if (mode == DbModeEnum.UPDATE) {
            playerDao.updatePlayer(player);
        }
    }
}
