package org.gitqh.nba.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by quhan on 2017/7/6.
 */
@Repository
public interface DatabaseDao {

    @Select("truncate ${tableName}")
    void truncateTable(@Param("tableName") String tableName);
}
