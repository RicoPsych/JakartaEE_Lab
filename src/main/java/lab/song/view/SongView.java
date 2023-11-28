package lab.song.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lab.song.entities.Song;
import lab.song.model.SongModel;
import lab.song.service.SongService;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class SongView implements Serializable {

    private final SongService songService;

    @Setter
    @Getter
    private UUID id;


    @Getter
    private SongModel song;


    /**
     * @param service service for managing characters
     */
    @Inject
    public SongView(SongService songService) {
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Song> song = songService.findForCallerPrincipal(id);
        if (song.isPresent()) {
            this.song = SongModel.mapper(song.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Song not found");
        }
    }


}
