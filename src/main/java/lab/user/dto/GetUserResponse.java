package lab.user.dto;

import java.util.List;
import java.util.UUID;

import lab.user.dto.GetUsersResponse._User;
import lab.user.entities.User;
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
public class GetUserResponse { 
    private UUID id;
    private String name;
    
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
    private List<_Song> favourites;
    
    // @ToString.Exclude
    // @EqualsAndHashCode.Exclude
    // public byte[] avatar;

    public static GetUserResponse mapper(User user){
    return GetUserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .favourites(user.getSongs().stream()
            .map(song->_Song.builder()
                .id(song.getId())
                .name(song.getName())
            .build())
        .toList())
    .build();
    }
}
