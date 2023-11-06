package lab.song.dto;

import java.util.UUID;

import lab.album.entities.Album;
import lab.song.entities.Song;
import lab.user.entities.User;
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
public class GetSongResponse {

    private UUID id;
    private String name;
    private float rating;
    private int duration;

        
    @ToString
    @Setter
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class _Album {
        private String name;
        private UUID id;
    }
    private _Album album;
    
        
    @ToString
    @Setter
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class _User {
        private String name;
        private UUID id;
    }
    
    //private _User owner;

    public static GetSongResponse mapper(Song song) {

        return GetSongResponse.builder()
            .id(song.getId())
            .name(song.getName())
            .rating(song.getRating())
            .duration(song.getDuration())
            .album(_Album.builder()
                .id(song.getAlbum().getId())
                .name(song.getAlbum().getName())
                .build())
            // .owner(_User.builder()
            //     .id(song.getOwner().getId())
            //     .name(song.getOwner().getName())
            //     .build())
            .build();
    }
    
}
