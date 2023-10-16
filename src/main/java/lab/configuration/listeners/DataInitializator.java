package lab.configuration.listeners;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.artist.entities.Artist;
import lab.artist.service.ArtistService;
import lab.song.entities.Song;
import lab.song.service.SongService;
import lab.user.entities.User;
import lab.user.service.UserService;
import lombok.SneakyThrows;

@ApplicationScoped
public class DataInitializator {
    private final UserService userService;
    private final SongService songService;
    private final ArtistService artistService;

    private final RequestContextController requestContextController;

    @Inject
    DataInitializator(UserService userService, RequestContextController requestContextController, SongService songService, ArtistService artistService){
        this.userService = userService;
        this.requestContextController = requestContextController;
        this.artistService = artistService;
        this.songService = songService;
    }

    
    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();

        artistService.create(Artist.builder().id(UUID.fromString("f98e668-364c-4766-a87d-a3a909800ebf")).name("Balthazar").retired(false).build());

        songService.create(Song.builder()
        .name("Linger on")
        .author(artistService.find(UUID.fromString("f98e668-364c-4766-a87d-a3a909800ebf")).get())
        .duration(300)
        .rating(4.3f)
        
        .build());

        userService.create(User.builder()
            .id(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"))
            .name("Stefan")
           // .avatar(getResourceAsByteArray("../avatar/zereni.png"))
            .build());

        userService.create(User.builder()
            .id(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"))
            .name("Pawel")
            //.avatar(getResourceAsByteArray("../avatar/eloise.png"))
            .build());

        userService.create(User.builder()
            .id(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"))
            .name("Konrad")
            //.avatar(getResourceAsByteArray("../avatar/uhlbrecht.png"))
            .build());

        userService.create(User.builder()
            .id(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"))
            .name("Wnek")
            //.avatar(getResourceAsByteArray("../avatar/calvian.png"))
            .build());

        userService.updateAvatar(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"), getResourceAsByteArray("../avatar/calvian.png"));
        userService.updateAvatar(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"), getResourceAsByteArray("../avatar/eloise.png"));
        userService.updateAvatar(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"), getResourceAsByteArray("../avatar/uhlbrecht.png"));
        userService.updateAvatar(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"), getResourceAsByteArray("../avatar/zereni.png"));
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
