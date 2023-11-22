package lab.user.dto;

import lab.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostUserRequest {
    private String name;
    private String password;
    //private List<Song> favourites;

    public static User mapper(PostUserRequest request){
        return User.builder().name(request.getName()).password(request.getPassword()).build();
    }
}
