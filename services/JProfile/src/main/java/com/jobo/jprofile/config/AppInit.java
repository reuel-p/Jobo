package com.jobo.jprofile.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInit implements WebApplicationInitializer {

	public void onStartup(ServletContext p_container) throws ServletException {

		AnnotationConfigWebApplicationContext l_ctx = new AnnotationConfigWebApplicationContext();
		l_ctx.register(AppConfig.class);
		l_ctx.setServletContext(p_container);

		ServletRegistration.Dynamic l_servlet = p_container.addServlet("Appdispatcher", new DispatcherServlet(l_ctx));
		l_servlet.setLoadOnStartup(1);
		l_servlet.addMapping("/");
	}

}
