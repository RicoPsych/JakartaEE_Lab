package lab.album.dto;

import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lab.song.dto.GetSongResponse._Album;
import lab.song.dto.GetSongsResponse;
import lab.user.dto.GetUserResponse;
import lombok.AccessLevel;
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
public class GetAlbumsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class _Album {
        private UUID id;
        private String name;
        
    }

    @Singular
    private List<_Album> albums;
    

    public static GetAlbumsResponse mapper(List<Album> albums) {
        return GetAlbumsResponse.builder()
            .albums(albums.stream()
                .map(album-> _Album.builder()
                            .id(album.getId())
                            .name(album.getName())
                .build()).toList()).build();
    }
    
}
