package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.gitqh.nba.model.Standing;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface StandingDao {

    @Insert("insert into standing(`ID`,`TEAMID`,`SEASON`,`DIVISION`,`CONFERENCE`,`WIN`,`LOSE`,`GB`,`HOME`,`AWAY`,`PSG`"
            + ",`PAG`,`WDIVISION`,`WCONFERENCE`,`LAST10`,`ISDELETED`,`UPDATETIME`) values(#{id},#{team},,#{isDeleted},#{updateTime})")
    int insertStanding(Standing standing);


}
