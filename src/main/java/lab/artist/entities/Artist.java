package lab.artist.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lab.song.entities.Song;
import lombok.AllArgsConstructor;
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
public class Artist implements Serializable{
    private UUID id;
    private String name;

    private boolean retired;

    private List<Song> songs;
}
