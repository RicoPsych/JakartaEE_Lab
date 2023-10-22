package lab.album.dto;

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
public class PutAlbumRequest {
    private String name;
    
    public static Album mapper(PutAlbumRequest request) {
        return null;
    }
    
}
