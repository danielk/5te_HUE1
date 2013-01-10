package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reiseveranstaltung", uniqueConstraints = {@UniqueConstraint(columnNames = "REISEVERANSTALTUNG_ID")})
public class Reiseveranstaltung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REISEVERANSTALTUNG_ID", unique = true, nullable = false)
    private Integer reiseveranstaltungId;

    @Column(name = "REISEVERANSTALTUNG_ZIELORT")
    private String reiseveranstaltungZielort;

    @Column(name = "REISEVERANSTALTUNG_BESCHREIBUNG")
    private String reiseveranstaltungBeschreibung;

    @Column(name = "REISEVERANSTALTUNG_BEGINN")
    private Date reiseveranstaltungBeginn;

    @Column(name = "REISEVERANSTALTUNG_ENDE")
    private Date reiseveranstaltungEnde;

    @Column(name = "REISEVERANSTALTUNG_PREIS")
    private Double reiseveranstaltungPreis;

    @Column(name = "REISEVERANSTALTUNG_HYPERLINK")
    private String reiseveranstaltungHyperlink;

    @ManyToOne
    @JoinColumn(name = "REISETYP_ID")
    private Reisetyp reiseveranstaltungReisetyp;

    public Integer getReiseveranstaltungId() {
        return reiseveranstaltungId;
    }

    public void setReiseveranstaltungId(Integer reiseveranstaltungId) {
        this.reiseveranstaltungId = reiseveranstaltungId;
    }

    public String getReiseveranstaltungZielort() {
        return reiseveranstaltungZielort;
    }

    public void setReiseveranstaltungZielort(String reiseveranstaltungZielort) {
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
    }

    public String getReiseveranstaltungBeschreibung() {
        return reiseveranstaltungBeschreibung;
    }

    public void setReiseveranstaltungBeschreibung(
            String reiseveranstaltungBeschreibung) {
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
    }

    public Date getReiseveranstaltungBeginn() {
        return reiseveranstaltungBeginn;
    }

    public void setReiseveranstaltungBeginn(Date reiseveranstaltungBeginn) {
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
    }

    public Date getReiseveranstaltungEnde() {
        return reiseveranstaltungEnde;
    }

    public void setReiseveranstaltungEnde(Date reiseveranstaltungEnde) {
        this.reiseveranstaltungEnde = reiseveranstaltungEnde;
    }

    public Double getReiseveranstaltungPreis() {
        return reiseveranstaltungPreis;
    }

    public void setReiseveranstaltungPreis(Double reiseveranstaltungPreis) {
        this.reiseveranstaltungPreis = reiseveranstaltungPreis;
    }

    public String getReiseveranstaltungHyperlink() {
        return reiseveranstaltungHyperlink;
    }

    public void setReiseveranstaltungHyperlink(String reiseveranstaltungHyperlink) {
        this.reiseveranstaltungHyperlink = reiseveranstaltungHyperlink;
    }

    public Reisetyp getReiseveranstaltungReisetyp() {
        return reiseveranstaltungReisetyp;
    }

    public void setReiseveranstaltungReisetyp(Reisetyp reiseveranstaltungReisetyp) {
        this.reiseveranstaltungReisetyp = reiseveranstaltungReisetyp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((reiseveranstaltungId == null) ? 0 : reiseveranstaltungId
                .hashCode());
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
        Reiseveranstaltung other = (Reiseveranstaltung) obj;
        if (reiseveranstaltungId == null) {
            if (other.reiseveranstaltungId != null)
                return false;
        } else if (!reiseveranstaltungId.equals(other.reiseveranstaltungId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reiseveranstaltung [reiseveranstaltungId="
                + reiseveranstaltungId + ", reiseveranstaltungZielort="
                + reiseveranstaltungZielort
                + ", reiseveranstaltungBeschreibung="
                + reiseveranstaltungBeschreibung
                + ", reiseveranstaltungBeginn=" + reiseveranstaltungBeginn
                + ", reiseveranstaltungEnde=" + reiseveranstaltungEnde
                + ", reiseveranstaltungPreis=" + reiseveranstaltungPreis
                + ", reiseveranstaltungHyperlink="
                + reiseveranstaltungHyperlink + ", reiseveranstaltungReisetyp="
                + reiseveranstaltungReisetyp + "]";
    }

    public Reiseveranstaltung(
            String reiseveranstaltungZielort,
            String reiseveranstaltungBeschreibung,
            Date reiseveranstaltungBeginn, Date reiseveranstaltungEnde,
            Double reiseveranstaltungPreis, String reiseveranstaltungHyperlink,
            Reisetyp reiseveranstaltungReisetyp) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
        this.reiseveranstaltungEnde = reiseveranstaltungEnde;
        this.reiseveranstaltungPreis = reiseveranstaltungPreis;
        this.reiseveranstaltungHyperlink = reiseveranstaltungHyperlink;
        this.reiseveranstaltungReisetyp = reiseveranstaltungReisetyp;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort,
                              String reiseveranstaltungBeschreibung,
                              Date reiseveranstaltungBeginn, Date reiseveranstaltungEnde,
                              Double reiseveranstaltungPreis, String reiseveranstaltungHyperlink) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
        this.reiseveranstaltungEnde = reiseveranstaltungEnde;
        this.reiseveranstaltungPreis = reiseveranstaltungPreis;
        this.reiseveranstaltungHyperlink = reiseveranstaltungHyperlink;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort,
                              String reiseveranstaltungBeschreibung,
                              Date reiseveranstaltungBeginn, Date reiseveranstaltungEnde,
                              Double reiseveranstaltungPreis) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
        this.reiseveranstaltungEnde = reiseveranstaltungEnde;
        this.reiseveranstaltungPreis = reiseveranstaltungPreis;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort,
                              String reiseveranstaltungBeschreibung,
                              Date reiseveranstaltungBeginn, Date reiseveranstaltungEnde) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
        this.reiseveranstaltungEnde = reiseveranstaltungEnde;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort,
                              String reiseveranstaltungBeschreibung, Date reiseveranstaltungBeginn) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
        this.reiseveranstaltungBeginn = reiseveranstaltungBeginn;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort,
                              String reiseveranstaltungBeschreibung) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
        this.reiseveranstaltungBeschreibung = reiseveranstaltungBeschreibung;
    }

    public Reiseveranstaltung(String reiseveranstaltungZielort) {
        super();
        this.reiseveranstaltungZielort = reiseveranstaltungZielort;
    }

    public Reiseveranstaltung() {
        super();
    }


}
