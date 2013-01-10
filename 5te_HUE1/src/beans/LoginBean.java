package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crud.DAO_Benutzer;
import exceptions.NfBenutzerDbException;
import exceptions.BenutzerDbException;
import model.Benutzer;

/**
 * Wie der Name vermuten lässt behandelt das LoginBean die Loginseite.
 * Bei einem erfolgreichen Login setzt sie den Sessionbenutzer via der CentralBean.login funktion.
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

    private String email;
    private String password;

    @ManagedProperty(value = "#{centralBean}")
    private CentralBean centralBean;

    public void setCentralBean(CentralBean centralBean) {
        this.centralBean = centralBean;
    }


    public String login() {

        Benutzer ben = null;
        try {
            ben = DAO_Benutzer.readBenutzer(email);
            if (ben == null) throw new BenutzerDbException("DB-Error");
        } catch (BenutzerDbException e) {

            if (e instanceof NfBenutzerDbException) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "E-Mail adresse exisitiert nicht. \n" +
                        " Bitte zuerst registrieren!", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return null;
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler : " + e.getMessage(), null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return null;
            }

        }


        if (ben.getBenutzerPasswort().equals(password)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreicher Login !", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

            try {
                centralBean.login(DAO_Benutzer.readBenutzer(ben.getBenutzerEmail()));
            } catch (BenutzerDbException e) {
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unerwarteter Datenbankfehler : " + e.getMessage(), null);
                FacesContext.getCurrentInstance().addMessage(null, message2);
                return null;
            }
            return "success";
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falsches Passwort !", null);
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "fail";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}  
  