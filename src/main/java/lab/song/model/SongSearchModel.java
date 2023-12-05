package lab.song.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lab.album.entities.Album;
import lab.song.entities.Song;
import lab.song.model.converter._AlbumModel;
import lab.user.entities.User;
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
public class SongSearchModel {
    private UUID id;
    private String name;
    private Float rating;
    private Integer duration;
   // private Album album;
   // private User owner;
    private Long version;
    private LocalDateTime updateDateTime;
    private LocalDateTime creationDateTime;

    
    public static SongSearchModel mapper(Song song){
        return SongSearchModel.builder()
            .name(song.getName())
            .duration(song.getDuration())
            .rating(song.getRating())
    //        .album(_AlbumModel.mapper(song.getAlbum()))
  //          .owner(song.getOwner().getName())
            .creationDateTime(song.getCreationDateTime())
            .updateDateTime(song.getUpdateDateTime())
            .id(song.getId())
            .version(song.getVersion())
            
            .build();
    }
}
