package lab.album.view;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab.album.entities.Album.Genre;
import lab.album.model.AlbumCreateModel;
import lab.album.service.AlbumService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import java.io.Serializable;
import java.util.UUID;

/**
 * View bean for rendering single character create form. Creating a character is divided into number of steps where each
 * step is separate JSF view. In order to use single bean, conversation scope is used.
 */
@ViewScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class AlbumCreate implements Serializable {

    /**
     * Service for managing characters.
     */
    private final AlbumService albumService;


    /**
     * Character exposed to the view.
     */
    @Getter
    private AlbumCreateModel album;

    public Genre[] getGenres(){
        return Genre.values();
    };



    @Inject
    public AlbumCreate(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        album = AlbumCreateModel.builder()
            .id(UUID.randomUUID())
            .build();
    }

  
    /**
     * Cancels character creation process.
     *
     * @return characters list navigation case
     */
    public String cancelAction() {
        return "/album/albums_list.xhtml?faces-redirect=true";
    }

 
    /**
     * Stores new character and ends conversation.
     *
     * @return characters list navigation case
     */
    public String saveAction() {
        albumService.create(album.saveEntity());
        return "/album/albums_list.xhtml?faces-redirect=true";
    }

}
