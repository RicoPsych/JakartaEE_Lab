package lab.song.controller;

import java.util.UUID;

import lab.song.dto.GetSongResponse;
import lab.song.dto.GetSongsResponse;
import lab.song.dto.PatchSongRequest;
import lab.song.dto.PutSongRequest;

public interface SongController {
    GetSongsResponse getSongs();
    GetSongResponse getSong(UUID id);

    void putSong(UUID id,PutSongRequest request);
    void patchSong(UUID id,PatchSongRequest request);
    void deleteSong(UUID id);
}
