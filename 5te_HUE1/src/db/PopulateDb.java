package db;

import crud.*;
import exceptions.BenutzerDbException;
import exceptions.ReisetypDbException;
import exceptions.ReiseveranstaltungDbException;
import model.Benutzer;
import model.Reisetyp;
import model.Reiseveranstaltung;

import java.sql.Date;
import java.util.LinkedList;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: danielkaiser
 * Date: 21/12/2012
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class PopulateDb {

    public static boolean called = false;

    private void addBenutzers() {
        LinkedList<Benutzer> benutzerList = new LinkedList<Benutzer>();
        benutzerList.add(new Benutzer("kaiser@epsillo.net", "kaiserpw", false, Test_DAO_Random.getRandomPersistedReisetypSet()));
        benutzerList.add(new Benutzer("buder@epsillo.net", "buderpw", true, Test_DAO_Random.getRandomPersistedReisetypSet()));
        benutzerList.add(new Benutzer("enne@epsillo.net", "ennepw", false, Test_DAO_Random.getRandomPersistedReisetypSet()));
        benutzerList.add(new Benutzer("otto.reichel@htlstp.ac.at", "reiopw", true, Test_DAO_Random.getRandomPersistedReisetypSet()));
        benutzerList.add(new Benutzer("alexandra.buchner@htlstp.ac.at", "buchpw", false, Test_DAO_Random.getRandomPersistedReisetypSet()));
        benutzerList.add(new Benutzer("klaus.hasenzagl@htlstp.ac.at", "hasepw", false, Test_DAO_Random.getRandomPersistedReisetypSet()));

        for (Benutzer ben : benutzerList) {
            try {
                DAO_Benutzer.createBenutzer(ben);
            } catch (BenutzerDbException e) {
            }
        }
    }

    private void addReiseveranstaltungs() {
        LinkedList<Reiseveranstaltung> reiseveranstaltungsList = new LinkedList<Reiseveranstaltung>();
        try {
            reiseveranstaltungsList.add(new Reiseveranstaltung("Paris", "3tagesreise nach Paris", new Date(210, 10, 10), new Date(210, 10, 13), 1300.12, "parislink.html", DAO_Reisetyp.readReisetyp(1)));
            reiseveranstaltungsList.add(new Reiseveranstaltung("London", "4tagesreise nach London", new Date(210, 10, 10), new Date(210, 10, 13), 1300.12, "londonlink.html", DAO_Reisetyp.readReisetyp(1)));
            reiseveranstaltungsList.add(new Reiseveranstaltung("Moskau", "Städtetrip nach Moskau", new Date(210, 10, 10), new Date(210, 10, 13), 1300.12, "moskaulink.html", DAO_Reisetyp.readReisetyp(1)));
            reiseveranstaltungsList.add(new Reiseveranstaltung("China", "Erlebnissurlaub in Asien", new Date(211, 4, 10), new Date(211, 7, 9), 429.27, "china.html", DAO_Reisetyp.readReisetyp(2)));
            reiseveranstaltungsList.add(new Reiseveranstaltung("WeitWeitWeg", "DAS geschenk f�r Leute die sie nicht m�gen.", new Date(211, 10, 10), new Date(210, 10, 13), 99.0, "weitweg.html", DAO_Reisetyp.readReisetyp(3)));
            reiseveranstaltungsList.add(new Reiseveranstaltung("Serbien", "Gehst du in da schenste Land", new Date(212, 4, 13), new Date(212, 9, 9), 1300.12, "Kein Internet in Serbien", DAO_Reisetyp.readReisetyp(4)));

        } catch (ReisetypDbException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (Reiseveranstaltung reis : reiseveranstaltungsList) {
            try {
                DAO_Reiseveranstaltung.createReiseveranstaltung(reis);
            } catch (ReiseveranstaltungDbException e) {
            }
        }
    }


    public PopulateDb() {

        if (called == false) {
            try {
                Test_DAO_Reisetyp.create();
            } catch (ReisetypDbException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            addBenutzers();
            addReiseveranstaltungs();
        }
        called = true;
    }
}
