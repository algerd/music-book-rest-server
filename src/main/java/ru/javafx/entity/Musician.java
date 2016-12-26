
package ru.javafx.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="musician")
public class Musician implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @NotNull(message = "error.musician.name.empty")
    @Size(max = 255, message = "error.musician.name.size")
    @Column(name="name")
    private String name;
    
    @Column(name="date_of_birth")
    private String dateOfBirth;
    
    @Column(name="date_of_death")
    private String dateOfDeath;
    
    @Column(name="country")
    private String country;
    
    @Column(name="rating")
    private Integer rating = 0;
    
    @Size(max = 10000, message = "error.musician.description.size")
    @Column(name="description")
    private String description;
    
    @Size(max = 255)
    @Column(name="image_link")
    private String imageLink;
    
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicianGenre> musicianGenres = new ArrayList<>();
    
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicianGroup> musicianGroups = new ArrayList<>();
    
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicianAlbum> musicianAlbums = new ArrayList<>();
    
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicianInstrument> musicianInstruments = new ArrayList<>();
    
    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicianSong> musicianSongs = new ArrayList<>();
    
    public Musician() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public List<MusicianGenre> getMusicianGenres() {
        return musicianGenres;
    }

    public List<MusicianGroup> getMusicianGroups() {
        return musicianGroups;
    }

    public List<MusicianAlbum> getMusicianAlbums() {
        return musicianAlbums;
    }  

    public List<MusicianInstrument> getMusicianInstruments() {
        return musicianInstruments;
    }

    public List<MusicianSong> getMusicianSongs() {
        return musicianSongs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Musician other = (Musician) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
