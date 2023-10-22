package lab.song.controller;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lab.exception.BadRequestException;
import lab.exception.NotFoundException;
import lab.song.dto.GetSongResponse;
import lab.song.dto.GetSongsResponse;
import lab.song.dto.PatchSongRequest;
import lab.song.dto.PutSongRequest;
import lab.song.entities.Song;
import lab.song.service.SongService;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;
import lab.user.service.UserService;

@ApplicationScoped
public class SimpleSongController implements SongController {

    private final SongService service;

    @Inject
    public SimpleSongController(SongService service){
        this.service = service;
    }


    @Override
    public GetSongsResponse getSongs() {
        return GetSongsResponse.mapper(service.findAll());
    }

    @Override
    public GetSongResponse getSong(UUID id) {
        return service.find(id)
                .map(song -> GetSongResponse.mapper(song))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putSong(UUID id, PutSongRequest request) {
        try {
            Song song = PutSongRequest.mapper(request);
            song.setId(id);
            
            service.create(song);
        } catch (IllegalArgumentException ex) {
           throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchSong(UUID id, PatchSongRequest request) {
    //     service.find(id).ifPresentOrElse(
    //         entity -> service.update(factory.updateCharacter().apply(entity, request)), //todo inaczej 
    //         () -> {
    //             throw new NotFoundException();
    //         }
    // );
    }

    @Override
    public void deleteSong(UUID id) {
        service.find(id).ifPresentOrElse(
            entity -> service.delete(id),
            () -> {
                //throw new NotFoundException();
            }
        );
    }

}
