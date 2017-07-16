package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.gitqh.nba.model.Coach;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/8.
 */
@Repository("CoachDao")
public interface CoachDao {

    @Insert("insert into coach(`CNNAME`,`ENNAME`,`BIRTHDAY`,`BIRTHPLACE`,`COLLEGE`,`HIGHSCHOOL`,`TEACHYEAR`,`TEACHNUM`,"
            + "`TEACHPLAYOFF`,`LOSE`,`WIN`,`COY`,`CHAMPION`,`FINALNUM`,`PLAYOFFLOSE`,`PLAYOFFWIN`,`ISDELETED`,"
            + "`UPDATETIME`,`URL`) values(#{cnName},#{enName},#{birthday},#{birthplace},#{college},#{highSchool},#{teachYear},#{teachNum},"
            + "#{teachPlayoff},#{lose},#{win},#{coy},#{champion},#{finalNum},#{playoffLose},#{playoffWin},#{isDeleted},#{updateTime},#{url})")
    int insertCoach(Coach coach);

    @Update("")
    int updateCoach(Coach coach);
}
