package lab.user.controler;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;
import lab.user.service.UserService;

public class SimpleUserControler implements UserControler {

    private final UserService service;

    public SimpleUserControler(UserService service){
        this.service = service;
    }


    @Override
    public GetUsersResponse getUsers() {
        return GetUsersResponse.mapper(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID id) {
        return service.find(id)
                .map(user -> GetUserResponse.mapper(user))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        try {
            User user = PutUserRequest.mapper(request);
            user.setId(id);
            
            service.create(user);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
    //     service.find(id).ifPresentOrElse(
    //         entity -> service.update(factory.updateCharacter().apply(entity, request)), //todo inaczej 
    //         () -> {
    //             throw new NotFoundException();
    //         }
    // );
    }

    @Override
    public void deleteUser(UUID id) {
        service.find(id).ifPresentOrElse(
            entity -> service.delete(id),
            () -> {
                throw new NotFoundException();
            }
        );
    }

    @Override
    public byte[] getUserAvatar(UUID id) {
        return service.find(id)
            .map(User::getAvatar)
            .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
            entity -> service.updateAvatar(id, avatar),
            () -> {
                throw new NotFoundException();
            }
        );
    }
    
}
