package lab.album.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lab.album.entities.Album;
import lab.album.model.AlbumModel;
import lab.album.model.AlbumsModel;
import lab.album.service.AlbumService;
import lab.song.service.SongService;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class AlbumView implements Serializable {


    private final AlbumService service;
    private final SongService songService;

    @Setter
    @Getter
    private UUID id;


    @Getter
    private AlbumModel album;


    /**
     * @param service service for managing characters
     */
    @Inject
    public AlbumView(AlbumService service,SongService songService) {
        this.service = service;
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Album> album = service.find(id);
        if (album.isPresent()) {
            this.album = AlbumModel.mapper(album.get(),songService.findByAlbumForCallerPrincipal(album.get().getId()).get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Album not found");
        }
    }

    public AlbumModel getAlbum() throws IOException{
        Optional<Album> album = service.find(id);
        if (album.isPresent()) {
            this.album = AlbumModel.mapper(album.get(),songService.findByAlbumForCallerPrincipal(album.get().getId()).get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Album not found");
        }
        return this.album;
    }

    /**
     * Action for clicking delete action.
     *
     * @param album character to be removed
     * @return navigation case to list_characters
     */
    public void deleteSongAction(AlbumModel._Song song) {
        songService.delete(song.getId());
        //album.setSongs(null);
        album = null;
        //return "album_view?faces-redirect=true&id="+id;
    }
}
