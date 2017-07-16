package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.PlayerStatsCareer;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface PlayerStatsCareerDao {

    @Insert("insert into player_stats_career(`ID`,`PLAYERID`,`TEAMID`,`G`,`GS`,`PTS`,`TRB`,`ORB`,`DRB`,`AST`,`STL`,`BLK`,`TOV`,`PF`,`MP`,"
            + "`FGA`,`FG`,`PA3`,`P3`,`FTA`,`FT`,`TYPE`,`SEASON`,`LEAGUE`,`ISNOW`,`ISDELETED`,`UPDATETIME`) values(#{id},#{playerId},#{team},"
            + "#{g},#{gs},#{pts},#{trb},#{orb},#{drb},#{ast},#{stl},#{blk},#{tov},#{pf},#{mp},#{fga},#{fg},#{pa3},#{p3},#{fta},"
            + "#{ft},#{type},#{season}, #{league},#{isNow},#{isDeleted},#{updateTime})")
    int insertPlayerStatsCareer(PlayerStatsCareer playerStatsCareer);

    @Update("")
    int updatePlayerStatsCareer(PlayerStatsCareer playerStatsCareer);


}
