package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.Award;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface AwardDao {

    @Insert("insert into award(`CNNAME`,`LEAGUE`,`SEASON`,`TIMEPERIOD`,`AWARD`,`ISDELETED`,`UPDATETIME`)"
            + "#{cnName},#{league},#{season},#{timePeriod},#{award},#{isDeleted},#{updateTime})")
    int insertAward(Award award);

    @Update("")
    int updateAward(Award award);
}
