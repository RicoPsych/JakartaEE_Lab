package lab.song.dto;

import java.util.UUID;

import lab.album.entities.Album;
import lab.song.entities.Song;
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
public class PutSongRequest {
    private String name;
    private float rating;
    private int duration;

    public static Song mapper(PutSongRequest request,Album album, UUID id) {
        return Song.builder()
            .id(id)
            .name(request.getName())
            .rating(request.getRating())
            .duration(request.getDuration())
            .album(album)
        .build();
    }
    
}
