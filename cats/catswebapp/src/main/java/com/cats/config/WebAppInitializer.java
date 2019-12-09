package com.cats.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        XmlWebApplicationContext mainWebAppContext = new XmlWebApplicationContext();
        mainWebAppContext.setConfigLocation("/WEB-INF/applicationContext.xml");

        ServletRegistration.Dynamic main = servletContext.addServlet("MVC dispatcher", new DispatcherServlet(mainWebAppContext));
        main.setLoadOnStartup(1);
        main.addMapping("/");


        ServletRegistration.Dynamic cxf = servletContext.addServlet("CXF dispatcher", CXFServlet.class);
        cxf.setLoadOnStartup(2);
        cxf.addMapping("/soap/*");
    }
}