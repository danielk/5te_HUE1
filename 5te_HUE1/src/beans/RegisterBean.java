package beans;

import crud.DAO_Benutzer;
import crud.DAO_Reisetyp;
import exceptions.NfBenutzerDbException;
import exceptions.BenutzerDbException;
import exceptions.ReisetypDbException;
import model.Benutzer;
import model.Reisetyp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Das RegisterBean ist zuständig für die Registration.
 * Es erstellt dann eine Entity in der Datenbank.
 */
@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean {

    private List<String> selectedReisetyps;

    private String email_r;
    private String password_r;

    private boolean newsletter;

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public String register() {

        try {

            DAO_Benutzer.readBenutzer(email_r);

        } catch (BenutzerDbException e) {

            if (e instanceof NfBenutzerDbException) {

                Set<Reisetyp> mp = new HashSet<Reisetyp>();

                for (String x : selectedReisetyps) {
                    try {
                        mp.add(DAO_Reisetyp.readReisetyp(new Integer(x)));
                    } catch (ReisetypDbException e1) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler: " + e1.getMessage(), null);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }

                try {
                    DAO_Benutzer.createBenutzer(new Benutzer(email_r, password_r, newsletter, mp));
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sie haben sich erfolgreich registriert.", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "";
                } catch (BenutzerDbException e1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler : " + e1.getMessage(), null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return null;
                }


            }


        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die Emailadresse ist schon registriert.", null);
        FacesContext.getCurrentInstance().addMessage(null, message);

        return null;

    }

    public List<String> getSelectedReisetyps() {
        return selectedReisetyps;
    }

    public void setSelectedReisetyps(List<String> selectedReisetyps) {
        this.selectedReisetyps = selectedReisetyps;
    }

    public Map<String, Integer> getAvailableReisetyps() {

        Map<String, Integer> reisetypMap = new HashMap<String, Integer>();

        try {
            for (Reisetyp r : DAO_Reisetyp.fetchAllReisetyps()) {
                reisetypMap.put(r.getReisetypBezeichnung(), r.getReisetypId());
            }
        } catch (ReisetypDbException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler : " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return reisetypMap;
    }

    public String getEmail_r() {
        return email_r;
    }

    public void setEmail_r(String email_r) {
        this.email_r = email_r;
    }

    public String getPassword_r() {
        return password_r;
    }

    public void setPassword_r(String password_r) {
        this.password_r = password_r;
    }
}
