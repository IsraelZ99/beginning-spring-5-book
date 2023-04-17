package org.book.chapter4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.testng.Assert.*;

// CALLING METHODS AFTER CONSTRUCTION AND BEFORE DESTRUCTION
@Component
class SeventhObject extends HasData {
    static Object semaphore = null;

    /*
      Methods marked with @PostConstruct and @PreDestroy annotations will be called at the appropriate spots
      in the object's and context's lifecycle
     */

    @PostConstruct
    public void initialize() throws Exception {
        semaphore = new Object();
    }

    @PreDestroy
    public void dispose() throws Exception {
        semaphore = null;
    }
}

@ContextConfiguration("/annotated-06.xml")
public class TestLifeCycle06 extends AbstractTestNGSpringContextTests {

    @Test
    void testInitDestroyMethods() {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/annotated-06.xml");
        SeventhObject o1 = applicationContext.getBean(SeventhObject.class);
        assertNotNull(SeventhObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        applicationContext.close();
        assertNull(SeventhObject.semaphore);
    }

}
