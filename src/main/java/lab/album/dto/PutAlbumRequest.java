package lab.album.dto;

import java.time.LocalDate;
import java.util.UUID;

import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
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
public class PutAlbumRequest {
    private String name;
    private LocalDate releaseDate;
    private Genre genre;


    public static Album mapper(PutAlbumRequest request, UUID id) {
        return Album.builder()
            .id(id)
            .name(request.getName())
            .genre(request.getGenre())
            .releaseDate(request.getReleaseDate())
        .build();
    }
    
}
