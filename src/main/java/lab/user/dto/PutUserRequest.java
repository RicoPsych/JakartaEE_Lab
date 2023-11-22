package lab.user.dto;

import lab.user.entities.User;
import lombok.AllArgsConstructor;
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
public class PutUserRequest {
    private String name;
    
    private String password;
    //private List<Song> favourites;

    public static User mapper(PutUserRequest request){
        return User.builder().name(request.getName()).password(request.getPassword()).build();
    }
}
