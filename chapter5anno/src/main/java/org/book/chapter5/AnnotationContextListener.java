package org.book.chapter5;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AnnotationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context = buildAnnotationContext();
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildAnnotationContext() {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.scan("org.book.chapter3.mem03"); // <context:component-scan />
        context.refresh();
        return context;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
