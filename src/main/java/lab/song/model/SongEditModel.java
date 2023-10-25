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
public class SongEditModel {
    private String name;
    private float rating;
    private int duration;
    private _AlbumModel album;


    public static SongEditModel mapper(Song song){
        return SongEditModel.builder()
        .name(song.getName())
        .duration(song.getDuration())
        .rating(song.getRating())
        .album(_AlbumModel.mapper(song.getAlbum()))
//          .owner(song.getOwner().getName())
        .build();
    }

    public Song saveEntity(Album album){
        return Song.builder()
        .name(this.getName())
        .rating(this.getRating())
        .duration(this.getDuration())
        .album(album)
        .build();
    }
}
