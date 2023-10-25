package lab.song.model;

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
public class SongModel {
    private String name;
    private float rating;
    private int duration;
    private _AlbumModel album;
//    private Artist author;
   private String owner;

    public static SongModel mapper(Song song){
        return SongModel.builder()
            .name(song.getName())
            .duration(song.getDuration())
            .rating(song.getRating())
            .album(_AlbumModel.mapper(song.getAlbum()))
  //          .owner(song.getOwner().getName())
            .build();
    }
}
