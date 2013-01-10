package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "benutzer", uniqueConstraints = {@UniqueConstraint(columnNames = "BENUTZER_EMAIL")})
public class Benutzer {


    @Id
    @Column(name = "BENUTZER_EMAIL", unique = true, nullable = false)
    private String benutzerEmail;

    @Column(name = "BENUTZER_PASSWORT")
    private String benutzerPasswort;

    @Column(name = "BENUTZER_NEWSLETTER")
    private Boolean benutzerNewsletter;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "benutzer_reisetyp",
            joinColumns = {@JoinColumn(name = "BENUTZER_EMAIL")},
            inverseJoinColumns = {@JoinColumn(name = "REISETYP_ID")})
    private Set<Reisetyp> reisetyps = new HashSet<Reisetyp>(0);


    public Benutzer() {
        // TODO Auto-generated constructor stub
    }

    public Benutzer(String benutzerEmail, String benutzerPasswort,
                    Boolean benutzerNewsletter, Set<Reisetyp> rst) {
        super();
        this.benutzerEmail = benutzerEmail;
        this.benutzerPasswort = benutzerPasswort;
        this.benutzerNewsletter = benutzerNewsletter;
        this.reisetyps = rst;
    }

    public Benutzer(String benutzerEmail, String benutzerPasswort,
                    Boolean benutzerNewsletter) {
        super();
        this.benutzerEmail = benutzerEmail;
        this.benutzerPasswort = benutzerPasswort;
        this.benutzerNewsletter = benutzerNewsletter;
    }

    public String getBenutzerEmail() {
        return benutzerEmail;
    }

    public void setBenutzerEmail(String benutzerEmail) {
        this.benutzerEmail = benutzerEmail;
    }

    public String getBenutzerPasswort() {
        return benutzerPasswort;
    }

    public void setBenutzerPasswort(String benutzerPasswort) {
        this.benutzerPasswort = benutzerPasswort;
    }

    public Boolean getBenutzerNewsletter() {
        return benutzerNewsletter;
    }

    public void setBenutzerNewsletter(Boolean benutzerNewsletter) {
        this.benutzerNewsletter = benutzerNewsletter;
    }

    public Set<Reisetyp> getReisetyps() {
        return reisetyps;
    }

    public void setReisetyps(Set<Reisetyp> reisetyps) {
        this.reisetyps = reisetyps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((benutzerEmail == null) ? 0 : benutzerEmail.hashCode());
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
        Benutzer other = (Benutzer) obj;
        if (benutzerEmail == null) {
            if (other.benutzerEmail != null)
                return false;
        } else if (!benutzerEmail.equals(other.benutzerEmail))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Benutzer [benutzerEmail=" + benutzerEmail
                + ", benutzerPasswort=" + benutzerPasswort + "]";
    }


}
