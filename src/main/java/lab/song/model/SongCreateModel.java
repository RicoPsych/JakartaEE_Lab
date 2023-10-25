package lab.song.model;

import java.util.UUID;

import lab.album.entities.Album;
import lab.song.entities.Song;
import lab.song.model.converter._AlbumModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SongCreateModel {
    private UUID id;
    private String name;
    private float rating;
    private int duration;
    private _AlbumModel album;
//    private Artist author;
 //   private String owner;

    // public static SongCreateModel mapper(Song song){
    //     return SongCreateModel.builder()
    //         .name(song.getName())
    //         .duration(song.getDuration())
    //         .rating(song.getRating())
    //         .album(song.getAlbum().getName())
    //         //.owner(song.getOwner().getName())
    //         .build();
    // }

    public Song saveEntity(Album album){
        return Song.builder()
        .id(this.getId())
        .name(this.getName())
        .rating(this.getRating())
        .duration(this.getDuration())
        .album(album)
        .build();
    }
}
