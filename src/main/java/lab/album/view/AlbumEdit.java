package lab.album.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lab.album.model.AlbumEditModel;
import lab.album.service.AlbumService;
import lab.song.entities.Song;
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
public class AlbumEdit implements Serializable {

    private final AlbumService service;
    private final SongService songService;


    @Setter
    @Getter
    private UUID id;

    @Getter
    private AlbumEditModel album;



    public Genre[] getGenres(){
        return Genre.values();
    };


    @Inject
    public AlbumEdit(AlbumService service,SongService songService) {
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
            this.album = AlbumEditModel.mapper(album.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Album not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        List<Song> songs = songService.findByAlbum(id).orElseThrow();
        service.update(album.saveEntity(service.find(id).orElseThrow()));
        for (Song song : songs) {
            song.setAlbum(service.find(id).orElseThrow());
            songService.updateForCallerPrincipal(song);
        }

//        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
//        return viewId + "?faces-redirect=true&includeViewParams=true";
        return "/album/album_view.xhtml?faces-redirect=true&id="+id;
    }

}
