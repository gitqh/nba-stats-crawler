package org.gitqh.nba;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class NbaStatsCrawlerApplicationTests {

	@Test
	public void contextLoads() {
		log.trace("trace test");
		log.debug("debug test");
		log.warn("warn test");
		log.error("error test");
	}

}
