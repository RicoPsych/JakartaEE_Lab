package lab.album.controller;

import java.util.UUID;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lab.album.dto.GetAlbumResponse;
import lab.album.dto.GetAlbumsResponse;
import lab.album.dto.PostAlbumRequest;
import lab.album.dto.PutAlbumRequest;
import lab.album.entities.Album;

@Path("")
public interface AlbumController {

    @GET
    @Path("/albums")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAlbumsResponse getAlbums();

    @GET
    @Path("/albums/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAlbumResponse getAlbum(@PathParam("id") UUID id);

    @POST
    @Path("/albums")
    @Consumes({MediaType.APPLICATION_JSON})
    public Album postAlbum(PostAlbumRequest request);

    @PUT
    @Path("/albums/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void putAlbum(@PathParam("id") UUID id, PutAlbumRequest request);
    //void patchAlbum(UUID id,PatchAlbumRequest request);

    @DELETE
    @Path("/albums/{id}")
    public void deleteAlbum(@PathParam("id") UUID id);
}
