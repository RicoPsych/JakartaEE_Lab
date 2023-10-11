package lab.configuration.listeners;

import java.io.InputStream;
import java.util.UUID;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.user.entities.User;
import lab.user.service.UserService;
import lombok.SneakyThrows;

@WebListener
public class DataInitializator implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService service = (UserService) event.getServletContext().getAttribute("userService");
        service.create(User.builder()
            .id(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"))
            .name("Stefan")
            .avatar(getResourceAsByteArray("../avatar/zereni.png"))
            .build());

        service.create(User.builder()
            .id(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"))
            .name("Pawel")
            .avatar(getResourceAsByteArray("../avatar/eloise.png"))
            .build());

        service.create(User.builder()
            .id(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"))
            .name("Konrad")
            .avatar(getResourceAsByteArray("../avatar/uhlbrecht.png"))
            .build());

        service.create(User.builder()
            .id(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"))
            .name("Wnek")
            .avatar(getResourceAsByteArray("../avatar/calvian.png"))
            .build());

        service.updateAvatar(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"), getResourceAsByteArray("../avatar/calvian.png"));
        service.updateAvatar(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"), getResourceAsByteArray("../avatar/eloise.png"));
        service.updateAvatar(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"), getResourceAsByteArray("../avatar/uhlbrecht.png"));
        service.updateAvatar(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"), getResourceAsByteArray("../avatar/zereni.png"));
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }
}
