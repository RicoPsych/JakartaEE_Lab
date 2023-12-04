package lab.song.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lab.album.entities.Album;
import lab.user.entities.User;
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
@Entity
@Table(name = "songs")
public class Song implements Serializable{
    @Id
    private UUID id;
    private String name;
    private float rating;
    private int duration;
    
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
//    private Artist author;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
//    private List<User> isFavourite;

    @Version
    private Long version;

    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @PreUpdate
    public void updateUpdateDateTime() {
        updateDateTime = LocalDateTime.now();
    }
    @PrePersist
    public void updateCreationDateTime() {
        creationDateTime = LocalDateTime.now();
    }
}
