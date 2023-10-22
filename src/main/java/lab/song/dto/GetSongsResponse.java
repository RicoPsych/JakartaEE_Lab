package lab.song.dto;

import java.util.List;
import java.util.UUID;

import lab.song.entities.Song;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSongsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class _Song {
        private UUID id;
        private String name;
        
    }

    @Singular
    private List<_Song> songs;

    public static GetSongsResponse mapper(List<Song> songs) {
        return GetSongsResponse.builder()
            .songs(songs.stream()
                .map(song-> _Song.builder()
                            .id(song.getId())
                            .name(song.getName())
                .build()).toList()).build();
    }
    
}
