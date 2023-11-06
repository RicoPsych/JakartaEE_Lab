package lab.album.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
public class PostAlbumRequest {
    private String name;

    //private List<_Song> songs;
    private LocalDate releaseDate;
    private Genre genre;

} 
