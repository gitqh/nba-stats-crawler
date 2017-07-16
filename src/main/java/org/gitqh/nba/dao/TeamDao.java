package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.Team;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/1.
 */
@Repository
public interface TeamDao {

    @Insert("insert into team(`ENNAME`,`CNNAME`,`SHORTNAME`,`DATE`,`CONFERENCE`,`DIVISION`,`ISDELETED`,`UPDATETIME`) "
            + "values(#{enName},#{cnName},#{shortName},#{date},#{conference},#{division},#{isDeleted},#{updateTime})")
    int insertTeam(Team team);


    @Update("update team set CNNAME=#{cnName} where isDeleted=1")
    int updateTeam(Team team);
}
