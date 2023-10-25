package lab.song.model.converter;

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
public class _AlbumModel {
    private UUID id;
    private String name;

    public static _AlbumModel mapper(Album album){
        return _AlbumModel.builder()
            .id(album.getId())
            .name(album.getName())
        .build();
    }
}

