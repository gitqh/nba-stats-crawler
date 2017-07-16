package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.Schedule;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface ScheduleDao {

    @Insert("insert into schedule(`DATE`,`URL`,`SEASON`,`HOMETEAM`,`AWAYTEAM`,`HOMESCORE`,`AWAYSCORE`,`HOMEFIRST`,`AWAYFIRST`,"
            + "`HOMESECOND`,`AWAYSECOND`,`HOMETHIRD`,`AWAYTHIRD`,`HOMEFOURTH`,`AWAYFOURTH`,`ISDELETED`,`UPDATETIME`) "
            + "values(#{date},#{url},#{season},#{homeTeam},#{awayTeam},#{homeScore},#{awayScore},#{homeFirst},#{awayFirst},"
            + "#{homeSecond},#{awaySecond},#{homeThird},#{awayThird},#{homeFourth},#{awayFourth},#{isDeleted},#{updateTime})")
    int insertSchedule(Schedule schedule);

    @Update("update schedule set HOMEOT=#{homeOt},AWAYOT=#{awayOt},GAMETYPE=#{gameType} where URL=#{url}")
    int updateSchedule(Schedule schedule);
}
