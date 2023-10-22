package lab.album.controller;

import java.util.UUID;

import lab.album.dto.GetAlbumResponse;
import lab.album.dto.GetAlbumsResponse;
import lab.album.dto.PostAlbumRequest;
import lab.album.dto.PutAlbumRequest;
import lab.album.entities.Album;


public interface AlbumController {
    GetAlbumsResponse getAlbums();
    GetAlbumResponse getAlbum(UUID id);

    Album postAlbum(PostAlbumRequest request);
    void putAlbum(UUID id,PutAlbumRequest request);
    //void patchAlbum(UUID id,PatchAlbumRequest request);
    void deleteAlbum(UUID id);
}
