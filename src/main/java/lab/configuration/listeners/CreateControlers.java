package lab.configuration.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.user.controler.SimpleUserControler;
import lab.user.service.UserService;

@WebListener
public class CreateControlers implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        
        event.getServletContext().setAttribute("userControler", new SimpleUserControler(userService));
    }
}
