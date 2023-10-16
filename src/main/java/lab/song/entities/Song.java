package lab.song.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lab.artist.entities.Artist;
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
public class Song implements Serializable{
    private UUID id;
    private String name;
    private float rating;
    private int duration;
    private Album album;
//    private Artist author;
    private User owner;
//    private List<User> isFavourite;
}
