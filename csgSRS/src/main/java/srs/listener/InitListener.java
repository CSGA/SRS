package srs.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;


public class InitListener implements ServletContextListener {
	@Autowired
	@Override
	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
