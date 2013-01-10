package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reisetyp", uniqueConstraints = {@UniqueConstraint(columnNames = "REISETYP_ID")})
public class Reisetyp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REISETYP_ID", unique = true, nullable = false)
    private Integer reisetypId;

    @Column(name = "REISETYP_BEZEICHNUNG")
    private String reisetypBezeichnung;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "reisetyps")
    private Set<Benutzer> benutzers = new HashSet<Benutzer>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reiseveranstaltungReisetyp")
    private Set<Reiseveranstaltung> reisetypReiseveranstaltungs;

    public Integer getReisetypId() {
        return reisetypId;
    }

    public void setReisetypId(Integer reisetypId) {
        this.reisetypId = reisetypId;
    }

    public String getReisetypBezeichnung() {
        return reisetypBezeichnung;
    }

    public void setReisetypBezeichnung(String reisetypBezeichnung) {
        this.reisetypBezeichnung = reisetypBezeichnung;
    }

    public Set<Benutzer> getBenutzers() {
        return benutzers;
    }

    public void setBenutzers(Set<Benutzer> benutzers) {
        this.benutzers = benutzers;
    }

    public Set<Reiseveranstaltung> getReisetypReiseveranstaltungs() {
        return reisetypReiseveranstaltungs;
    }

    public void setReisetypReiseveranstaltungs(
            Set<Reiseveranstaltung> reisetypReiseveranstaltungs) {
        this.reisetypReiseveranstaltungs = reisetypReiseveranstaltungs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((reisetypId == null) ? 0 : reisetypId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reisetyp other = (Reisetyp) obj;
        if (reisetypId == null) {
            if (other.reisetypId != null)
                return false;
        } else if (!reisetypId.equals(other.reisetypId))
            return false;
        return true;
    }

    public Reisetyp() {
    }

    public Reisetyp(String reisetypBezeichnung, Set<Benutzer> benutzers, Set<Reiseveranstaltung> reisetypReiseveranstaltungs) {
        super();
        this.reisetypReiseveranstaltungs = reisetypReiseveranstaltungs;
        this.reisetypBezeichnung = reisetypBezeichnung;
        this.benutzers = benutzers;
    }

    public Reisetyp(String reisetypBezeichnung, Set<Benutzer> benutzers) {
        super();
        this.reisetypBezeichnung = reisetypBezeichnung;
        this.benutzers = benutzers;
    }

    public Reisetyp(String reisetypBezeichnung) {
        super();
        this.reisetypBezeichnung = reisetypBezeichnung;
    }


}
