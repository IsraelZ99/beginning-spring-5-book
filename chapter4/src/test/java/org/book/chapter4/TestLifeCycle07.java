package org.book.chapter4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
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
class EighthObject extends HasData implements InitializingBean, DisposableBean {

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

@ContextConfiguration("/annotated-07.xml")
public class TestLifeCycle07 extends AbstractTestNGSpringContextTests {

    @Test
    void testLifecycleMethods() {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/annotated-07.xml");
        EighthObject o1 = applicationContext.getBean(EighthObject.class);
        assertNotNull(EighthObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        applicationContext.close();
        assertNull(EighthObject.semaphore);
    }

}
