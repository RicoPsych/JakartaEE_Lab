package lab.configuration.listeners;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lab.album.service.AlbumService;
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
    private final AlbumService albumService;

    private final RequestContextController requestContextController;

    @Inject
    DataInitializator(UserService userService, RequestContextController requestContextController
    , SongService songService, ArtistService artistService, AlbumService albumService){
        this.userService = userService;
        this.requestContextController = requestContextController;
        this.artistService = artistService;
        this.songService = songService;
        this.albumService = albumService;
    }

    
    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();

        if(albumService.findAll().isEmpty()){
//        artistService.create(Artist.builder().id(UUID.fromString("f98e668-364c-4766-a87d-a3a909800ebf")).name("Balthazar").retired(false).build());

        Album a1 = Album.builder().id(UUID.fromString("8d2e3678-1678-4550-8aeb-1b9223802356"))
            .name("Rats")
            .genre(Genre.Indie)
            .releaseDate(LocalDate.of(2012, 10,15))
            //.songs(List.of(s1))
        .build();
        Album a2 = Album.builder().id(UUID.fromString("6c3f7514-d5a2-4975-8bda-99dd7b243792"))
            .name("Applause")
            .genre(Genre.Indie)
            .releaseDate(LocalDate.of(2011, 9,13))
           // .songs(List.of(s2))
        .build();

        

        User u1 = User.builder()
            .id(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"))
            .name("Stefan")
            //.favourites(List.of(s1,s2))
           // .avatar(getResourceAsByteArray("../avatar/zereni.png"))
            .build();
        User u2 = User.builder()
            .id(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"))
            .name("Pawel")
            //.avatar(getResourceAsByteArray("../avatar/eloise.png"))
            .build();

        User u3 = User.builder()
            .id(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"))
            .name("Konrad")
            //.avatar(getResourceAsByteArray("../avatar/uhlbrecht.png"))
            .build();

        User u4 = User.builder()
            .id(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"))
            .name("Wnek")
            //.avatar(getResourceAsByteArray("../avatar/calvian.png"))
            .build();




        Song s1 = Song.builder()
        .id(UUID.fromString("008d02dd-e112-4c6f-b44d-7d4c9fc04a08"))
        .name("Sinking Ship")
        .album(a1)
        .owner(u1)
        //.author(artistService.find(UUID.fromString("3cddb94c-d134-4c36-9645-003d058f84cb")).get())
        .duration(300)
        .rating(4.3f)
        .build();

        Song s2 = Song.builder()
        .id(UUID.fromString("68f62a60-710a-4521-997c-56c842d39339"))
        .name("The Boatman")
        .album(a2)
        .owner(u2)
        //.author(artistService.find(UUID.fromString("56df0e3e-3458-476b-bc1b-01d42213430e")).get())
        .duration(250)
        .rating(4.3f)
        .build();

        Song s3 = Song.builder()
        .id(UUID.fromString("138e3455-ecfe-4cff-bc95-18894a88f015"))
        .name("Do Not Claim Them Anymore")
        .album(a1)
        .owner(u3)
        //.author(artistService.find(UUID.fromString("3cddb94c-d134-4c36-9645-003d058f84cb")).get())
        .duration(253)
        .rating(3.4f)
        .build();

        Song s4 = Song.builder()
        .id(UUID.fromString("e476b78d-ef36-41aa-bb55-6db478b68afb"))
        .name("Fifteen Floors")
        .album(a2)
        .owner(u4)
        //.author(artistService.find(UUID.fromString("3cddb94c-d134-4c36-9645-003d058f84cb")).get())
        .duration(280)
        .rating(4.8f)
        .build();


        userService.create(u1);
        userService.create(u2);
        userService.create(u3);
        userService.create(u4);


        albumService.create(a1);
        albumService.create(a2);



        songService.create(s1);
        songService.create(s2);
        
        songService.create(s3);
        songService.create(s4);

        // userService.updateAvatar(UUID.fromString("33d1dc17-b337-4bdc-812b-0417918c1524"), getResourceAsByteArray("../avatar/calvian.png"));
        // userService.updateAvatar(UUID.fromString("77900169-116c-4800-a222-af01556a7f0d"), getResourceAsByteArray("../avatar/eloise.png"));
        // userService.updateAvatar(UUID.fromString("8213240b-0943-4719-acb2-5a41d1ec3c1e"), getResourceAsByteArray("../avatar/uhlbrecht.png"));
        // userService.updateAvatar(UUID.fromString("f901e668-364c-4f66-ad9d-a3a905f80ebf"), getResourceAsByteArray("../avatar/zereni.png"));
        }
        
        requestContextController.deactivate();
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
