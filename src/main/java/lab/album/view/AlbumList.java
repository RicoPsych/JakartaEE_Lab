package lab.album.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab.album.model.AlbumsModel;
import lab.album.service.AlbumService;
import lab.song.service.SongService;

/**
 * View bean for rendering list of characters.
 */
@RequestScoped
@Named
public class AlbumList {

    /**
     * Service for managing characters.
     */
    private final AlbumService service;
    private final SongService songService;
    /**
     * Characters list exposed to the view.
     */
    private AlbumsModel albums;

    /**
     * Factory producing functions for conversion between models and entities.
     */

    /**
     * @param service character service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public AlbumList(AlbumService service,SongService songService) {
        this.service = service;
        this.songService = songService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all characters
     */
    public AlbumsModel getAlbums() {
        if (albums == null) {
            albums = AlbumsModel.mapper(service.findAll());
        }
        return albums;
    }

    /**
     * Action for clicking delete action.
     *
     * @param album character to be removed
     * @return navigation case to list_characters
     */
    public String deleteAction(AlbumsModel._Album album) {
        service.delete(album.getId());
        songService.findByAlbum(album.getId())
            .ifPresent(songs->songs.forEach(song->songService.delete(song.getId())));
        return "albums_list?faces-redirect=true";
    }

}
