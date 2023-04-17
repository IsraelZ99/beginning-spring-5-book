package org.book.chapter2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import static org.testng.Assert.assertEquals;

public class GreeterTest {

    @Test
    void testHelloWorld(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Greeter greeter = context.getBean("helloGreater", Greeter.class);
        ByteArrayOutputStream baos = context.getBean("baos", ByteArrayOutputStream.class);
        greeter.greet();
        String data = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        assertEquals(data, "Hello, World!");

//        Greeter greeter = new HelloWorldGreeter();
//        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try(PrintStream ps = new PrintStream(baos, true, "UTF-8")){
//            greeter.setPrintStream(ps);
//            greeter.greet();
//        } catch (UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        String data = new String(baos.toByteArray(), StandardCharsets.UTF_8);
//        assertEquals(data, "Hello, World!");
    }

}
