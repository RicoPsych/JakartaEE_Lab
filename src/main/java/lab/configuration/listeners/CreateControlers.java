package lab.configuration.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.user.controler.SimpleUserController;
import lab.user.controler.UserController;
import lab.user.service.UserService;

@WebListener
public class CreateControlers implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");


        UserController controller = new SimpleUserController(userService);

        event.getServletContext().setAttribute("userController", controller);

        System.out.println("Controllers Created");
    }
}
