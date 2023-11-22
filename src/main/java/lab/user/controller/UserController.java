package lab.user.controller;

import java.io.InputStream;
import java.util.UUID;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lab.user.dto.GetUserResponse;
import lab.user.dto.GetUsersResponse;
import lab.user.dto.PatchUserRequest;
import lab.user.dto.PostUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;

@Path("")
public interface UserController {
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public GetUsersResponse getUsers();

    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetUserResponse getUser(@PathParam("id") UUID id);
    
    @POST
    @Path("/users")
    @Consumes({MediaType.APPLICATION_JSON})
    public void postUser(PostUserRequest request);
    
    @PUT
    @Path("/users/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void putUser(@PathParam("id") UUID id,PutUserRequest request);
    
    //public void patchUser(UUID id,PatchUserRequest request);
    @DELETE
    @Path("/users/{id}")
    public void deleteUser(@PathParam("id") UUID id);
    
    @GET
    @Path("/users/{id}/avatar")
    @Produces("image/png")
    public byte[] getUserAvatar(@PathParam("id") UUID id);

    @PUT
    @Path("/users/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void putUserAvatar(@PathParam("id") UUID id, 
     @SuppressWarnings("RestParamTypeInspection") @FormParam("avatar") InputStream avatar);

    @DELETE
    @Path("/users/{id}/avatar")
    public void deleteUserAvatar(@PathParam("id") UUID id);


    //public void postUserAvatar(UUID id, InputStream avatar);
}
