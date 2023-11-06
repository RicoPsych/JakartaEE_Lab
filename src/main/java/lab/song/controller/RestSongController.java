package lab.song.controller;

import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lab.album.service.AlbumService;
import lab.song.dto.GetSongResponse;
import lab.song.dto.GetSongsResponse;
import lab.song.dto.PutSongRequest;
import lab.song.entities.Song;
import lab.song.service.SongService;

@Path("")
public class RestSongController implements SongController {

    private final SongService service;
    private final AlbumService albumService;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Inject
    public RestSongController(SongService service, AlbumService albumService,
        @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        this.service = service;
        this.albumService = albumService;
        this.uriInfo = uriInfo;
    }

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }


    @Override
    public GetSongsResponse getSongs() {
        return GetSongsResponse.mapper(service.findAll());
    }

    @Override
    public GetSongsResponse getAlbumSongs(UUID album_id) {
        albumService.find(album_id).orElseThrow(NotFoundException::new);
        return GetSongsResponse.mapper(service.findByAlbum(album_id).get());
    }

    @Override
    public GetSongResponse getSong(UUID album_id, UUID id) {
        albumService.find(album_id).orElseThrow(NotFoundException::new);

        return service.find(id)
                .map(song -> GetSongResponse.mapper(song))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putSong(UUID album_id, UUID id, PutSongRequest request) {
        
        try {
            Song song = PutSongRequest.mapper(request,
            albumService.find(album_id).orElseThrow(NotFoundException::new),
            id);
            service.create(song);

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
            .path(SongController.class, "getSong")
            .build(album_id,id)
            .toString());
            throw new WebApplicationException(Response.Status.CREATED);

        } catch (IllegalArgumentException ex) {
           throw new BadRequestException(ex);
        }
    }

    // @Override
    // public void patchSong(UUID id, PatchSongRequest request) {
    // //     service.find(id).ifPresentOrElse(
    // //         entity -> service.update(factory.updateCharacter().apply(entity, request)), //todo inaczej 
    // //         () -> {
    // //             throw new NotFoundException();
    // //         }
    // // );
    // }

    @Override
    public void deleteSong(UUID album_id, UUID id) {
        albumService.find(album_id).orElseThrow(NotFoundException::new);
        service.find(id).ifPresentOrElse(
            entity -> service.delete(id),
            () -> {
                throw new NotFoundException();
            }
        );
    }

}
