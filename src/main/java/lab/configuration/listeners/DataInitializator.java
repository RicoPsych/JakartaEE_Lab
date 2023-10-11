package lab.configuration.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DataInitializator implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
    
    }
}
