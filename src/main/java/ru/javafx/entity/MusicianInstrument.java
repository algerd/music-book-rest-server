
package ru.javafx.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name="musician_instrument",
    uniqueConstraints = {@UniqueConstraint(name = "uk_musician_instrument", columnNames = {"id_musician", "id_instrument"})}
)
public class MusicianInstrument implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_musician", foreignKey = @ForeignKey(name = "fk_musician_instrument_id_musician"))
    private Musician musician;
   
    @ManyToOne
    @JoinColumn(name = "id_instrument", foreignKey = @ForeignKey(name = "fk_musician_instrument_id_instrument"))
    private Instrument instrument;
    
    public MusicianInstrument() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.musician);
        hash = 53 * hash + Objects.hashCode(this.instrument);
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
        final MusicianInstrument other = (MusicianInstrument) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.musician, other.musician)) {
            return false;
        }
        if (!Objects.equals(this.instrument, other.instrument)) {
            return false;
        }
        return true;
    }

}
