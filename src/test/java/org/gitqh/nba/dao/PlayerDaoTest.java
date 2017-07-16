package org.gitqh.nba.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalTime;

/**
 * Created by quhan on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlayerDaoTest {

    @Resource
    private PlayerDao playerDao;


    @Test
    public void test(){
        System.out.println(LocalTime.now());
    }

    @Test
    public void selectPlayerByNameTest() {
        Assert.assertEquals(playerDao.selectPlayerByName("lal"),"1");
    }

}