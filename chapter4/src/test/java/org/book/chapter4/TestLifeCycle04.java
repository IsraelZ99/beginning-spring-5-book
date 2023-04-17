package org.book.chapter4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

// LIFECYCLES LISTENERS
class FourthObject extends HasData implements InitializingBean, DisposableBean {

    static Object semaphore = null;

    @Override
    public void destroy() throws Exception {
        semaphore = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        semaphore = new Object();
    }
}

@ContextConfiguration(locations = "/config-04.xml")
public class TestLifeCycle04 extends AbstractTestNGSpringContextTests {

    @Test
    void testLifeCycleMethod() {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/config-04.xml");
        FourthObject o1 = applicationContext.getBean(FourthObject.class);
        assertNotNull(FourthObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        applicationContext.close();
        assertNull(FourthObject.semaphore);
    }

}
