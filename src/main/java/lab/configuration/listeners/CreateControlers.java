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
        //tODO NIE DIZALA AAAAAAAAAA
        UserController controler = new SimpleUserController(userService);

        event.getServletContext().setAttribute("userControler", controler);
    }
}
