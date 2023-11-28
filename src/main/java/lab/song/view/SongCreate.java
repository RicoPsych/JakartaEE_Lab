package lab.song.view;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab.album.entities.Album.Genre;
import lab.album.model.AlbumCreateModel;
import lab.album.service.AlbumService;
import lab.song.model.SongCreateModel;
import lab.song.model.converter._AlbumModel;
import lab.song.service.SongService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single character create form. Creating a character is divided into number of steps where each
 * step is separate JSF view. In order to use single bean, conversation scope is used.
 */
@ViewScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class SongCreate implements Serializable {

    /**
     * Service for managing characters.
     */
    private final AlbumService albumService;

    private final SongService songService;


    @Getter
    private List<_AlbumModel> albums;
    /**
     * Character exposed to the view.
     */
    @Getter
    private SongCreateModel song;


    @Inject
    public SongCreate(AlbumService albumService,SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        song = SongCreateModel.builder()
            .id(UUID.randomUUID())
            .build();
        albums = albumService.findAll().stream()
            .map(album->_AlbumModel.mapper(album))
        .collect(Collectors.toList());
    }

  
    /**
     * Cancels character creation process.
     *
     * @return characters list navigation case
     */
    public String cancelAction() {
        return "/album/album_view.xhtml?faces-redirect=true&id="+song.getAlbum().getId();
    }

 
    /**
     * Stores new character and ends conversation.
     *
     * @return characters list navigation case
     */
    public String saveAction() {
        
        songService.createForCallerPrincipal(song.saveEntity(albumService.find(song.getAlbum().getId()).orElseThrow()));
        return "/album/album_view.xhtml?faces-redirect=true&id="+song.getAlbum().getId();
    }

}
