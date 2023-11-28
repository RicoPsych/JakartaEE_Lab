package lab.song.controller;

import java.util.UUID;
import java.util.logging.Level;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
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
import lab.user.entities.UserRoles;
import lombok.extern.java.Log;

@Path("")
@Log
@RolesAllowed(UserRoles.USER)
public class RestSongController implements SongController {

    private  SongService songService;
    private  AlbumService albumService;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Inject
    public RestSongController(
        //SongService service, AlbumService albumService,
        @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        //this.service = service;
        //this.albumService = albumService;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setAlbumService(AlbumService albumService){
        this.albumService = albumService;
    }
    @EJB
    public void setSongService(SongService songService){
        this.songService = songService;
    }

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }


    @Override
    public GetSongsResponse getSongs() {
        return GetSongsResponse.mapper(songService.findAllForCallerPrincipal());
    }

    @Override
    public GetSongsResponse getAlbumSongs(UUID album_id) {
        albumService.find(album_id).orElseThrow(NotFoundException::new);
        return GetSongsResponse.mapper(songService.findByAlbumForCallerPrincipal(album_id).get());
    }
    @Override
    public GetSongsResponse getUserSongs(UUID user_id) {
       // userService.find(user_id).orElseThrow(NotFoundException::new);
        return GetSongsResponse.mapper(songService.findAllByUser(user_id).get());
    }


    @Override
    public GetSongResponse getSong(UUID album_id, UUID id) {
        albumService.find(album_id).orElseThrow(NotFoundException::new);

        return songService.find(id)
                .map(song -> GetSongResponse.mapper(song))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putSong(UUID album_id, UUID id, PutSongRequest request) {
        
        try {
            Song song = PutSongRequest.mapper(request,
            albumService.find(album_id).orElseThrow(NotFoundException::new),
            id);
            if(songService.find(song.getId()).isPresent()){
                songService.updateForCallerPrincipal(song);
            }
            else{
                songService.createForCallerPrincipal(song);
            }
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
            .path(SongController.class, "getSong")
            .build(album_id,id)
            .toString());
            throw new WebApplicationException(Response.Status.CREATED);

        } catch (EJBException ex) {
            //Any unchecked exception is packed into EJBException. Business exception can be itroduced here.
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void postSong(UUID album_id, PutSongRequest request) {
        
        try {
            Song song = PutSongRequest.mapper(request,
            albumService.find(album_id).orElseThrow(NotFoundException::new),
            UUID.randomUUID());
            songService.createForCallerPrincipal(song);

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
            .path(SongController.class, "getSong")
            .build(album_id,song.getId())
            .toString());
            throw new WebApplicationException(Response.Status.CREATED);

        } catch (EJBException ex) {
            //Any unchecked exception is packed into EJBException. Business exception can be itroduced here.
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
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
        songService.find(id).ifPresentOrElse(
            entity -> songService.delete(id),
            () -> {
                throw new NotFoundException();
            }
        );
    }

}
