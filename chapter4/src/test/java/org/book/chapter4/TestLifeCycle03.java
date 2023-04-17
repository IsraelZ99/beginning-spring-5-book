package org.book.chapter4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

// CALLING METHODS AFTER CONSTRUCTION AND BEFORE DESTRUCTION
class ThirdObject extends HasData {
    static Object semaphore = null;

    public void init() {
        semaphore = new Object();
    }

    public void dispose() {
        semaphore = null;
    }
}

@ContextConfiguration(locations = "/config-03.xml")
public class TestLifeCycle03 extends AbstractTestNGSpringContextTests {

    @Test
    void testInitDestroyMethods() {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/config-03.xml");
        ThirdObject o1 = applicationContext.getBean(ThirdObject.class);
        assertNotNull(ThirdObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        applicationContext.close();
        assertNull(ThirdObject.semaphore);
    }

}
