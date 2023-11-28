package lab.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lab.album.model.AlbumsModel;
import lab.user.entities.User;

/**
 * JSF view model class in order to not use entity classes. Represents list of users to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UsersModel implements Serializable {

    /**
     * Represents single user in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class _User {

    
        private UUID id;    
        private String name;

    }

    /**
     * List of users.
     */
    @Singular
    private List<_User> users;


    public static UsersModel mapper(List<User> entities){
        return UsersModel.builder().users(entities.stream()
            .map(album -> UsersModel._User.builder()
                .id(album.getId())
                .name(album.getName())
                .build()
        ).toList()).build(); 
    }
}
