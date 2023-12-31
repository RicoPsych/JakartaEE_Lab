package lab.album.controller;

import java.util.List;
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
import lab.album.dto.GetAlbumResponse;
import lab.album.dto.GetAlbumsResponse;

import lab.album.dto.PostAlbumRequest;
import lab.album.dto.PutAlbumRequest;
import lab.album.entities.Album;
import lab.album.service.AlbumService;
import lab.song.entities.Song;
import lab.song.service.SongService;
import lab.user.entities.UserRoles;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@Path("")
@Log
public class RestAlbumController implements AlbumController {

    private AlbumService service;
    private SongService songService;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Inject
    public RestAlbumController( //AlbumService service, SongService songService,
    @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        //this.service = service;
        this.uriInfo = uriInfo;
        //this.songService = songService;
    }

    @EJB
    public void setAlbumService(AlbumService albumService){
        this.service = albumService;
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
    public GetAlbumsResponse getAlbums() {
        return GetAlbumsResponse.mapper(service.findAll());
    }

    @Override
    public GetAlbumResponse getAlbum(UUID id) {
        return service.find(id)
                .map(album -> GetAlbumResponse.mapper(album,songService.findByAlbumForCallerPrincipal(id).get()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @SneakyThrows
    public void putAlbum(UUID id, PutAlbumRequest request) {
        try {
            Album album = PutAlbumRequest.mapper(request,id);
            //album.setId(id);
            if (service.find(album.getId()).isPresent()){
                service.update(album);    
            }
            else {
                service.create(album);
            }

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
            .path(AlbumController.class, "getAlbum")
            .build(id)
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
    // public void patchAlbum(UUID id, PatchAlbumRequest request) {
    // //     service.find(id).ifPresentOrElse(
    // //         entity -> service.update(factory.updateCharacter().apply(entity, request)), //todo inaczej 
    // //         () -> {
    // //             throw new NotFoundException();
    // //         }
    // // );
    // }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public void deleteAlbum(UUID id) {
        service.find(id).ifPresentOrElse(
            entity -> {
                // List<Song> list = songService.findByAlbum(id).get();
                // if(!list.isEmpty())
                //     list.stream().forEach(song->songService.delete(song.getId()));
                service.delete(id);
            },
            () -> {
                throw new NotFoundException();
            }
        );
    }



    @Override
    public Album postAlbum(PutAlbumRequest request) {
        try {
            Album album = PutAlbumRequest.mapper(request,UUID.randomUUID());
            service.create(album);

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
            .path(AlbumController.class, "getAlbum")
            .build(album.getId())
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

}
