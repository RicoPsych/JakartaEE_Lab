package lab.user.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
public class User implements Serializable {
    private UUID id;
    private String name;
    @Singular
    private List<Song> favourites;
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public byte[] avatar;



}
