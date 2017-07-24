package com.hdg.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**单元测试基类
 * Created by BlueBuff on 2017/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath*:applicationContext-*.xml",
                "classpath:applicationContext.xml",
                "classpath:spring-mvc.xml"
        }
)
@WebAppConfiguration
public class BaseTest {
}
