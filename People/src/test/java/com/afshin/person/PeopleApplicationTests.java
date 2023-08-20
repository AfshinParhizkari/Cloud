package com.afshin.person;

import com.afshin.person.application.PeopleRst;
import com.afshin.person.logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PeopleApplicationTests {

    public static final Logger logger  = LoggerFactory.getLogger(PeopleRst.class);
    @Test
    void sendDat2Logstash() {
        logger.info("test");
    }

}
