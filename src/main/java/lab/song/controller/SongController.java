package lab.song.controller;

import java.util.UUID;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lab.song.dto.GetSongResponse;
import lab.song.dto.GetSongsResponse;
import lab.song.dto.PatchSongRequest;
import lab.song.dto.PutSongRequest;

@Path("")
public interface SongController {

    @GET
    @Path("/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public GetSongsResponse getSongs();

    @GET
    @Path("/albums/{album_id}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public GetSongsResponse getAlbumSongs(@PathParam("album_id") UUID id); 

    @GET
    @Path("/users/{user_id}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public GetSongsResponse getUserSongs(@PathParam("user_id") UUID user_id);


    @GET
    @Path("/albums/{album_id}/songs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetSongResponse getSong(@PathParam("album_id") UUID album_id, @PathParam("id") UUID id);




    @POST
    @Path("/albums/{album_id}/songs/")
    @Consumes({MediaType.APPLICATION_JSON})
    public void postSong(@PathParam("album_id") UUID album_id,PutSongRequest request);

    @PUT
    @Path("/albums/{album_id}/songs/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void putSong(@PathParam("album_id") UUID album_id, @PathParam("id") UUID id,PutSongRequest request);

    //public void patchSong(UUID id,PatchSongRequest request);
    @DELETE
    @Path("/albums/{album_id}/songs/{id}")
    public void deleteSong(@PathParam("album_id") UUID album_id, @PathParam("id") UUID id);

}
