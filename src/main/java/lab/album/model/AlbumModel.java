package lab.album.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lab.song.entities.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@ToString
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumModel {

    private String name;
    private LocalDate releaseDate;
    private Genre genre;

    @ToString
    @Setter
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class _Song {
        private UUID id;
        private String name;
        private Long version;
        private LocalDateTime creationDateTime;
        private LocalDateTime updateDateTime;
    }
    
    
    @Singular
    private List<_Song> songs;

    public static AlbumModel mapper(Album album,List<Song> songs)
    {
        return AlbumModel.builder()
            .name(album.getName())
            .releaseDate(album.getReleaseDate())
            .genre(album.getGenre())
            .songs(songs.stream()
                .map(song -> AlbumModel._Song.builder()
                    .id(song.getId())
                    .name(song.getName())
                    .creationDateTime(song.getCreationDateTime())
                    .updateDateTime(song.getUpdateDateTime())
                    .version(song.getVersion())
                .build())
                .toList())
            .build();
    }
}
