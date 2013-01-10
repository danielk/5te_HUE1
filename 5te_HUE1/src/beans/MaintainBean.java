package beans;

import crud.DAO_Benutzer;
import crud.DAO_Reisetyp;
import exceptions.BenutzerDbException;
import exceptions.ReisetypDbException;
import model.Benutzer;
import model.Reisetyp;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Das MaintainBean ist für die veränderung der Benutzerdaten zuständig.
 */
@ManagedBean(name = "maintainBean")
@ViewScoped
public class MaintainBean {

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

    public Map<String, Integer> getAvailableReisetyps() {
        Map<String, Integer> reisetypMap = new HashMap<String, Integer>();


        try {
            for (Reisetyp r : DAO_Reisetyp.fetchAllReisetyps()) {
                reisetypMap.put(r.getReisetypBezeichnung(), r.getReisetypId());
            }
        } catch (ReisetypDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler beim Speichern der Reisetypen : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return reisetypMap;
    }

    public List<String> getSelectedReisetyps() {
        List<String> xx = new LinkedList<String>();

        try {
            for (Reisetyp reisetyp : DAO_Benutzer.getReisetyps(benutzer)) {
                xx.add(reisetyp.getReisetypId() + "");
            }
        } catch (BenutzerDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler beim Abrufen der Reisetypen : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return xx;
    }

    public void setSelectedReisetyps(List<String> selectedReisetyps) {

        Set<Reisetyp> mp = new HashSet<Reisetyp>();

        for (String x : selectedReisetyps) {
            try {
                mp.add(DAO_Reisetyp.readReisetyp(new Integer(x)));
            } catch (ReisetypDbException e1) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler beim Speichern der Reisetypen : " + e1.getMessage(), null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

        benutzer.setReisetyps(mp);

        try {
            DAO_Benutzer.updateBenutzer(benutzer);
        } catch (BenutzerDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler beim Speichern der Reisetypen : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    public boolean isNewsletter() {
        return benutzer.getBenutzerNewsletter();
    }

    public void setNewsletter(boolean newsletter) {

        benutzer.setBenutzerNewsletter(newsletter);
        try {
            DAO_Benutzer.updateBenutzer(benutzer);
        } catch (BenutzerDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler beim Speichern der Reisetypen : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    public String save() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Es wurde erfolgreich gespeichert", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }
}
