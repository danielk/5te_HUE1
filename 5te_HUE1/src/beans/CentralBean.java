package beans;

import crud.DAO_Benutzer;
import db.PopulateDb;
import exceptions.BenutzerDbException;
import model.Benutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Das CentralBean verwaltet den eingeloggten Benutzer und schreibt alle Daten bei seiner initialisierung in die Datenbank.
 * Exceptions werden nicht behandelt da diese praktisch nur auftreten können wenn die Environment nicht funktioniert.
 */
@ManagedBean(name = "centralBean")
@SessionScoped
public class CentralBean {


    {
        System.out.println("Central Bean Instantated");
        new PopulateDb();
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Der Eingeloggte Benutzer steht in dieser Variable.
     * Steht sie auf "null" ist noch kein Benutzer eingeloggt
     * und der Aufrufer jeder X-Beliebigen Seite wird auf die
     * Loginseite weitergeleitet.
     */
    private Benutzer benutzer;

    public Benutzer getBenutzer() {
        if (benutzer == null) logout();
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }


    /**
     * Wird verwendet für das Timeout oder ggf. für eine Logout funktion.
     */
    public String logout() {

        benutzer = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Wird von der Loginfunktion aufgerufen und setzt den eingelogogten benutzer.
     *
     * @param b
     * @return
     */
    public String login(Benutzer b) {

        benutzer = b;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
