package lab.user.controller;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
// import lab.exception.BadRequestException;
// import lab.exception.NotFoundException;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PostUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;
import lab.user.service.UserService;

@ApplicationScoped
public class SimpleUserController implements UserController {

    private final UserService service;

    @Inject
    public SimpleUserController(UserService service){
        this.service = service;
    }


    @Override
    public GetUsersResponse getUsers() {
        return GetUsersResponse.mapper(service.findAll());
    }
    
    
    @Override
    public User postUser(PostUserRequest request) {
        try {
            User user = PostUserRequest.mapper(request);
            user.setId(UUID.randomUUID());
            service.create(user);
            return user;
        } catch (IllegalArgumentException ex) {
      //     throw new BadRequestException(ex);
            return null;
        }
    }
    
    @Override
    public GetUserResponse getUser(UUID id) {
        return service.find(id)
                .map(user -> GetUserResponse.mapper(user)).get();
//                .orElseThrow(Exception::new);//NotFoundException
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        try {
            User user = PutUserRequest.mapper(request);
            user.setId(id);
            
            service.create(user);
        } catch (IllegalArgumentException ex) {
        //    throw new BadRequestException(ex);
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
                //throw new NotFoundException();
            }
        );
    }

    @Override
    public byte[] getUserAvatar(UUID id) {
        return service.getAvatar(id).get();
            // .orElseThrow(Exception::new);//NotFoundException
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
            entity -> service.updateAvatar(id, avatar),
            () -> {
            // throw new NotFoundException();
            }
        );
    }
    @Override
    public void postUserAvatar(UUID id, InputStream avatar) {
       service.find(id).ifPresentOrElse(
            entity -> service.updateAvatar(id, avatar),
            () -> {
            //throw new NotFoundException();
            }
        );
    }

    @Override
    public void deleteUserAvatar(UUID id) {
       service.find(id).ifPresentOrElse(
        
            entity -> service.deleteAvatar(id),
            () -> {
                //throw new NotFoundException();
            }
        );
    }
}
