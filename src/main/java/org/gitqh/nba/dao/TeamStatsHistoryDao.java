package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.gitqh.nba.model.TeamStatsHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface TeamStatsHistoryDao {
    @Insert("insert into team_stats_history(`ID`,`TEAMID`,`SEASON`,`PTS`,`TRB`,`ORB`,`DRB`,`AST`,`STL`,`BLK`,`TOV`,`PF`,`MP`,"
            + "`FGA`,`FG`,`PA3`,`P3`,`FTA`,`FT`,`ISDELETED`,`UPDATETIME`) values(#{id},#{team},"
            + "#{season},#{pts},#{trb},#{orb},#{drb},#{ast},#{stl},#{blk},#{tov},#{pf},#{mp},#{fga},#{fg},#{pa3},#{p3},#{fta},"
            + "#{ft},#{isDeleted},#{updateTime})")
    int insertTeamStatsHistory(TeamStatsHistory teamStatsHistory);


}
