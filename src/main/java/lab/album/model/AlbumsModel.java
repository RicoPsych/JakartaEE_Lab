package lab.album.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Builder
@ToString
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumsModel implements Serializable {

    @Builder
    @ToString
    @Setter
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class _Album{
        private UUID id;
        private String name;
    }

    @Singular
    private List<_Album> albums; 

    public static AlbumsModel mapper(List<Album> entities)
    {
        return AlbumsModel.builder().albums(entities.stream()
            .map(album -> AlbumsModel._Album.builder()
                .id(album.getId())
                .name(album.getName())
                .build()
        ).toList()).build();
    }
}
