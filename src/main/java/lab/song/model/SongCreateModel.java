package lab.song.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lab.album.entities.Album;
import lab.song.domain.Rating;
import lab.song.entities.Song;
import lab.song.model.converter._AlbumModel;
import lab.song.validation.binding.ValidSong;
import lab.song.validation.group.SongModelGroup;
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
@ValidSong(groups = SongModelGroup.class)
@Named
@ViewScoped
public class SongCreateModel implements Serializable,Rating {

    private UUID id;

    @NotBlank
    private String name;
    
    
    private float rating;
    
    @Min(1)
    @NotNull
    private int duration;

    @NotNull
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

