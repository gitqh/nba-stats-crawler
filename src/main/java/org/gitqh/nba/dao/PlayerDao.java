package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.Player;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/6/29.
 */
@Repository(value = "PlayerDao")
public interface PlayerDao {

    @Insert("insert into player(`TEAMID`,`CNNAME`,`ENNAME`,`ALLNAME`,`POSITION`,`HEIGHT`,`WEIGHT`,`BIRTHDAY`,`BIRTHPLACE`"
            + ",`COLLEGE`,`HIGHSCHOOL`,`DRAFTTEAM`,`DRAFTROUND`,`DRAFTPICK`,`DRAFTYEAR`,`G`,`GS`,`PTS`,`TRB`,`ORB`,`DRB`"
            + ",`AST`,`STL`,`BLK`,`TOV`,`PF`,`MP`,`FGA`,`FG`,`PA3`,`P3`,`FTA`,`FT`,`CLASS`,`WIN`,`LOSE`,`ISDELETED`"
            + ",`UPDATETIME`,`URL`,`NUMBER`) values(#{team},#{cnName},#{enName},#{allName},#{position},#{height}"
            + ",#{weight},#{birthday},#{birthplace},#{college},#{highSchool},#{draftTeam},#{draftRound},#{draftPick}"
            + ",#{draftYear},#{g},#{gs},#{pts},#{trb},#{orb},#{drb},#{ast},#{stl},#{blk},#{tov},#{pf},#{mp},#{fga},"
            + "#{fg},#{pa3},#{p3},#{fta},#{ft},#{className},#{win},#{lose},#{isDeleted},#{updateTime},#{url},#{number})")
    int insertPlayer(Player player);


    @Select("select CNNAME from player where CNNAME = #{cnName}")
    String selectPlayerByName(@Param("cnName") String cnName);

    @Update("update player set NUMBER=#{number} where ALLNAME= #{allName}")
    int updatePlayer(Player player);

}
