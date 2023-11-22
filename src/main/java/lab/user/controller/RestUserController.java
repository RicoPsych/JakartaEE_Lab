package lab.user.controller;

import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PostUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;
import lab.user.service.UserService;
import lombok.extern.java.Log;

@Path("")
@Log
public class RestUserController implements UserController {

    private UserService service;

    // @Inject
    // public RestUserController(UserService service){
    //     this.service = service;
    // }
    @EJB
    public void setUserService(UserService service){
        this.service = service;
    }

    @Override
    public GetUsersResponse getUsers() {
        return GetUsersResponse.mapper(service.findAll());
    }
    
    
    @Override
    public void postUser(PostUserRequest request) {
        try {
            User user = PostUserRequest.mapper(request);
            user.setId(UUID.randomUUID());
            service.create(user);
            //return user;
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
    public GetUserResponse getUser(UUID id) {
        return service.find(id)
                .map(user -> GetUserResponse.mapper(user))
                .orElseThrow(NotFoundException::new);//NotFoundException
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        try {
            User user = PutUserRequest.mapper(request);
            user.setId(id);
            
            service.create(user);
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
    // public void patchUser(UUID id, PatchUserRequest request) {
    // //     service.find(id).ifPresentOrElse(
    // //         entity -> service.update(factory.updateCharacter().apply(entity, request)), //todo inaczej 
    // //         () -> {
    // //             throw new NotFoundException();
    // //         }
    // // );
    // }

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
        return service.getAvatar(id)
            .orElseThrow(() -> new NotFoundException());//NotFoundException
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
    // @Override
    // public void postUserAvatar(UUID id, InputStream avatar) {
    //    service.find(id).ifPresentOrElse(
    //         entity -> service.updateAvatar(id, avatar),
    //         () -> {
    //         //throw new NotFoundException();
    //         }
    //     );
    // }

    @Override
    public void deleteUserAvatar(UUID id) {
       service.find(id).ifPresentOrElse(
        
            entity -> service.deleteAvatar(id),
            () -> {
                throw new NotFoundException();
            }
        );
    }
}
