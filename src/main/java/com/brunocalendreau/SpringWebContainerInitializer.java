package com.brunocalendreau;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

@Order(1)
public class SpringWebContainerInitializer implements WebApplicationInitializer {

    private final static Logger logger = LoggerFactory.getLogger(SpringWebContainerInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) {
        logger.info("Initializing context");
        WebApplicationContext webContext = createWebAplicationContext(SpringConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(webContext));
        //Clear out reference to applicationContext.xml
        servletContext.setInitParameter("contextConfigLocation", "");
    }

    private WebApplicationContext createWebAplicationContext(Class... configClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // set to "prod" for production, "dev" for development
        context.register(configClasses);
        return context;
    }
}