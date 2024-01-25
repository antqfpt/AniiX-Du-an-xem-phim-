package org.example.aniix.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(columnDefinition = "nvarchar(150)")
    private String name;
    @Column(columnDefinition = "nvarchar(4000)")
    private String thumbnail;
    @Column(columnDefinition = "nvarchar(4000)")
    private String description;
    private int releaseYear;
    private boolean type;
    private Timestamp uploadDate;
    private boolean status;


    @ManyToMany
    private List<Category> categories;
    @OneToMany(mappedBy = "flim")
    private List<Season> seasons;
    @ManyToMany
    private Set<Tag> tags;
    @ManyToMany(mappedBy = "flims")
    private Set<Users> users;
    @ManyToOne
    private Country country;
}
