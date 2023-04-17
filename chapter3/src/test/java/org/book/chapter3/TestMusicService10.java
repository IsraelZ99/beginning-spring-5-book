package org.book.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Consumer;

@ContextConfiguration(classes = {TestConfiguration.class})
public class TestMusicService10 extends AbstractTestNGSpringContextTests {

    // This use the bean musicServiceTest that is declared in TestConfiguration classl as a context
    @Autowired
    MusicServiceTest tests;

    @DataProvider
    Object[][] configurations() {
        return new Object[][]{
                {"/config-01.xml"},
                {"/config-02.xml"},
                {"/config-03.xml"},
                {"/config-04.xml"},
                {"/config-05.xml"},
                {"/config-06.xml"},
                {Configuration7.class},
                {Configuration8.class},
                {Configuration9.class},
                {Configuration10.class},
        };
    }

    private void runMethod(Object config, Consumer<MusicService> method) {
        ApplicationContext context = null;
        if (config instanceof String) {
            context = new ClassPathXmlApplicationContext(config.toString());
        } else {
            if (config instanceof Class<?>) {
                context = new AnnotationConfigApplicationContext((Class<?>) config);
            } else {
                throw new RuntimeException("Invalid configuration argument: " + config);
            }
        }
        MusicService service = context.getBean(MusicService.class);
        method.accept(service);
    }

    @Test(expectedExceptions = RuntimeException.class)
    void testRunMethod() {
        runMethod(Boolean.TRUE, tests::testSongVoting);
    }

    @Test(dataProvider = "configurations")
    void testSongVoting(Object config) {
        runMethod(config, tests::testSongVoting);
    }

    @Test(dataProvider = "configurations")
    void testGetMatchingArtistNames(Object config) {
        runMethod(config, tests::testMatchingArtistNames);
    }

    @Test(dataProvider = "configurations")
    void testGetSongsForArtist(Object config) {
        runMethod(config, tests::testSongsForArtist);
    }

    @Test(dataProvider = "configurations")
    void testMatchingSongNamesForArtist(Object config) {
        runMethod(config, tests::testMatchingSongNamesForArtist);
    }

}
