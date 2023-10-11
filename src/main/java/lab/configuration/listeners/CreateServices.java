package lab.configuration.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.datastorage.DataStorage;
import lab.user.repository.UserRepository;
import lab.user.repository.memory.UserMemoryRepository;
import lab.user.service.UserService;

@WebListener
public class CreateServices implements ServletContextListener{
     @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStorage dataSource = (DataStorage) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserMemoryRepository(dataSource);


        event.getServletContext().setAttribute("userService", new UserService(userRepository));
    }

}
