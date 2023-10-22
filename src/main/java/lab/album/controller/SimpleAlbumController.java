package lab.album.controller;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lab.exception.BadRequestException;
import lab.exception.NotFoundException;
import lab.album.dto.GetAlbumResponse;
import lab.album.dto.GetAlbumsResponse;

import lab.album.dto.PostAlbumRequest;
import lab.album.dto.PutAlbumRequest;
import lab.album.entities.Album;
import lab.album.service.AlbumService;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;
import lab.user.service.UserService;

@ApplicationScoped
public class SimpleAlbumController implements AlbumController {

    private final AlbumService service;

    @Inject
    public SimpleAlbumController(AlbumService service){
        this.service = service;
    }


    @Override
    public GetAlbumsResponse getAlbums() {
        return GetAlbumsResponse.mapper(service.findAll());
    }

    @Override
    public GetAlbumResponse getAlbum(UUID id) {
        return service.find(id)
                .map(album -> GetAlbumResponse.mapper(album))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putAlbum(UUID id, PutAlbumRequest request) {
        try {
            Album album = PutAlbumRequest.mapper(request);
            album.setId(id);
            
            service.create(album);
        } catch (IllegalArgumentException ex) {
           throw new BadRequestException(ex);
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

    @Override
    public void deleteAlbum(UUID id) {
        service.find(id).ifPresentOrElse(
            entity -> service.delete(id),
            () -> {
                //throw new NotFoundException();
            }
        );
    }


    @Override
    public Album postAlbum(PostAlbumRequest request) {
        return null;
        
    }

}
