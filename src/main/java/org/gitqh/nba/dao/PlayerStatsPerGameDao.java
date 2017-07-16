package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.gitqh.nba.model.PlayerStatsPerGame;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface PlayerStatsPerGameDao {

    @Insert("insert into player_stats_per_game(`ID`,`PLAYERID`,`GAMEID`,`PTS`,`TRB`,`ORB`,`DRB`,`AST`,`STL`,`BLK`,`TOV`,`PF`,`MP`,"
            + "`FGA`,`FG`,`PA3`,`P3`,`FTA`,`FT`,`ISDELETED`,`UPDATETIME`) values(#{id},#{playerId},#{gameId},#{pts},#{trb},#{orb},"
            + "#{drb},#{ast},#{stl},#{blk},#{tov},#{pf},#{mp},#{fga},#{fg},#{pa3},#{p3},#{fta},"
            + "#{ft},#{isDeleted},#{updateTime})")
    void insertPlayerStatsPerGam(PlayerStatsPerGame playerStatsPerGame);


}
