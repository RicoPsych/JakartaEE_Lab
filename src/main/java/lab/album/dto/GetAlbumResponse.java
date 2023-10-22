package lab.album.dto;

import java.util.UUID;

import lab.album.entities.Album;
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
public class GetAlbumResponse {

    private String name;
    private UUID id;

    public static GetAlbumResponse mapper(Album album) {
        return GetAlbumResponse.builder()
        .name(album.getName())
        .id(album.getId())
        .build();
    }
    
}
