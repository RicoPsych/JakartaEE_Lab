package lab.song.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lab.song.entities.Song;
import lab.song.model.SongModel;
import lab.song.model.SongSearchModel;
import lab.song.service.SongService;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
public class SongSearch implements Serializable {
    private final SongService songService;


    @Getter
    @Setter
    private SongSearchModel search;

    @Getter
    private List<SongSearchModel> songs;

    /**
     * @param service service for managing characters
     */
    @Inject
    public SongSearch(SongService songService) {
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        this.search = SongSearchModel.builder().build();

        
        List<Song> songs = songService.findAll();
        this.songs = songs.stream().map(_song->SongSearchModel.mapper(_song)).toList();
    }

    public void Search(){
        List<Song> songs = songService.findByFilter(search.getId(),/*search.getOwner()*/null,search.getName() == "" ? null : search.getName(),
        search.getRating(),/*search.getAlbum()*/null,search.getDuration(),search.getVersion(),search.getUpdateDateTime(),search.getCreationDateTime());
        this.songs = songs.stream().map(_song->SongSearchModel.mapper(_song)).toList();
    }
}
