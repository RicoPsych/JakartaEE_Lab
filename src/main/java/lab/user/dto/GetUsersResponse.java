package lab.user.dto;

import java.util.List;
import java.util.UUID;

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
public class GetUsersResponse {
    
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class _User{
        private UUID id;
        private String name;
    }

    @Singular
    private List<_User> users;

    public static GetUsersResponse mapper(List<User> users){
        return GetUsersResponse.builder().users(users.stream()
            .map(user-> _User.builder()
                .id(user.getId())
                .name(user.getName())
                .build())
            .toList())
        .build();
    }
}
