package lab.album.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lab.song.entities.Song;
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
public class Album implements Serializable{
    private UUID id;
    private String name;
    private List<Song> songs;
    private LocalDate releaseDate;
    private Genre genre;
    
@ViewScoped
@Named
    public enum Genre {
    Rock,
    Pop,
    Indie
    }
}

