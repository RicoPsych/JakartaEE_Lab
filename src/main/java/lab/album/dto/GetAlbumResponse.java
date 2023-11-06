package lab.album.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lab.song.entities.Song;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAlbumResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class _Song {
        private UUID id;
        private String name;
    }

    private String name;
    private UUID id;

    private List<_Song> songs;
    private LocalDate releaseDate;
    private Genre genre;


    public static GetAlbumResponse mapper(Album album,List<Song> songs) {
        return GetAlbumResponse.builder()
        .name(album.getName())
        .id(album.getId())
        .genre(album.getGenre())
        .releaseDate(album.getReleaseDate())
        .songs(songs.stream()
            .map(song->_Song.builder()
                .id(song.getId())
                .name(song.getName())
            .build())
        .toList())
        .build();
    }
    
}
