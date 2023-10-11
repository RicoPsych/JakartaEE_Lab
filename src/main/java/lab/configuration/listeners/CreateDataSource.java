package lab.configuration.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.datastorage.DataStorage;

@WebListener
public class CreateDataSource  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource", new DataStorage());
        System.out.println("Datasource created");
    }
}
