package lab.user.controler;

import java.io.InputStream;
import java.util.UUID;

import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PutUserRequest;

public interface UserController {
    GetUsersResponse getUsers();
    GetUserResponse getUser(UUID id);

    void putUser(UUID id,PutUserRequest request);
    void patchUser(UUID id,PatchUserRequest request);
    void deleteUser(UUID id);

    byte[] getUserAvatar(UUID id);
    void putUserAvatar(UUID id, InputStream avatar);
    void deleteUserAvatar(UUID id);
    void postUserAvatar(UUID id, InputStream avatar);
}
