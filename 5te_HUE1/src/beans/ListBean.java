package beans;

import crud.DAO_Benutzer;
import exceptions.BenutzerDbException;
import exceptions.DbException;
import model.Benutzer;
import model.Reisetyp;
import model.Reiseveranstaltung;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.LinkedList;
import java.util.List;

/**
 * Das ListBean wird verwendet für die Ausgabe der Reiseveranstaltungstabelle.
 */
@ManagedBean(name = "listBean")
@ViewScoped
public class ListBean {

    Benutzer benutzer = null;

    @ManagedProperty(value = "#{centralBean}")
    private CentralBean centralBean;

    public void setCentralBean(CentralBean centralBean) {
        this.centralBean = centralBean;
    }

    @PostConstruct
    public void init() {
        benutzer = centralBean.getBenutzer();
    }

    private Reiseveranstaltung selectedReiseveranstaltung;

    public List<Reiseveranstaltung> getReiseveranstaltungList() {

        List<Reiseveranstaltung> list = null;

        try {
            list = DAO_Benutzer.getReiseveranstaltungs(benutzer);
        } catch (BenutzerDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return list;
    }

    public Reiseveranstaltung getSelectedReiseveranstaltung() {
        return selectedReiseveranstaltung;
    }

    public void setSelectedReiseveranstaltung(Reiseveranstaltung selectedReiseveranstaltung) {
        this.selectedReiseveranstaltung = selectedReiseveranstaltung;
    }
}