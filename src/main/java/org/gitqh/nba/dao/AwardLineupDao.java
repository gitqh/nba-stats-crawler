package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.AwardLineup;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: quhan
 * @create: 2017-07-12-17:24
 */
@Repository
public interface AwardLineupDao {

    @Insert("insert into award(`CNNAME`,`LEAGUE`,`SEASON`,`TIMEPERIOD`,`AWARD`,`ISDELETED`,`UPDATETIME`)"
            + "#{cnName},#{league},#{season},#{timePeriod},#{award},#{isDeleted},#{updateTime})")
    int insertAwardLineup(AwardLineup awardLineup);

    @Update("")
    int updateAwardLineup(AwardLineup awardLineup);
}

