package lab.song.view;

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
import lab.song.model.SongEditModel;
import lab.song.model.converter._AlbumModel;
import lab.song.service.SongService;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single character edit form.
 */
@ViewScoped
@Named
public class SongEdit implements Serializable {

    private final AlbumService albumService;
    private final SongService songService;

    /**
     * Character id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Character exposed to the view.
     */
    @Getter
    private SongEditModel song;

    @Getter
    private List<_AlbumModel> albums;


    @Inject
    public SongEdit(AlbumService albumService,SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Song> song = songService.find(id);
        if (song.isPresent()) {
            this.song = SongEditModel.mapper(song.get());
            albums = albumService.findAll().stream()
                .map(album->_AlbumModel.mapper(album))
                .collect(Collectors.toList());

        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Song not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        Song newSong = song.saveEntity(albumService.find(song.getAlbum().getId()).orElseThrow());
        newSong.setId(id);
        songService.update(newSong);

        // String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        // return viewId + "?faces-redirect=true&includeViewParams=true";
        return "/album/album_view.xhtml?faces-redirect=true&id="+song.getAlbum().getId();
    }

}
