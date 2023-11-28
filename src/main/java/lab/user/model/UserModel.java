package lab.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import lab.user.entities.User;

/**
 * JSF view model class in order to not use entity classes. Represents single user to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UserModel {


    /**
     * User's id.
     */
    private UUID id;

    private String name;


    public static UserModel mapper (User user ){
        return UserModel.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
    }
}
