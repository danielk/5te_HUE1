package crud;

import java.sql.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import exceptions.BenutzerDbException;
import exceptions.DbException;
import exceptions.ReisetypDbException;
import exceptions.ReiseveranstaltungDbException;
import model.Benutzer;
import model.Reisetyp;
import model.Reiseveranstaltung;

public class Test_DAO_Random {


    private static Set<Reisetyp> RandomPersistedReisetypSet = null;

    public static Set<Reisetyp> getRandomPersistedReisetypSet() {

        Set<Reisetyp> randomSet = new HashSet<Reisetyp>();
        Random xx = new Random();
        Reisetyp r;

        try {
            for (Reisetyp reisetyp : DAO_Reisetyp.fetchAllReisetyps()) {
                if (xx.nextBoolean()) randomSet.add(reisetyp);
            }
        } catch (ReisetypDbException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return randomSet;
    }

    public static Set<Benutzer> getRandomPersistedBenutzerList() {

        Set<Benutzer> randomSet = new HashSet<Benutzer>();

        Random xx = new Random();
        Benutzer r;

        if (xx.nextBoolean()) {
            try {
                r = new Benutzer("kaiser@epsillo.net", "kaiserpw", false);
                DAO_Benutzer.createBenutzer(r);
                randomSet.add(r);
            } catch (BenutzerDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Benutzer("enne@epsillo.net", "ennepw", false);
                DAO_Benutzer.createBenutzer(r);
                randomSet.add(r);
            } catch (BenutzerDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Benutzer("buder@epsillo.net", "buderpw", true);
                DAO_Benutzer.createBenutzer(r);
                randomSet.add(r);
            } catch (BenutzerDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Benutzer("otto.reichel@htlstp.ac.at", "reiopw",
                        true);
                DAO_Benutzer.createBenutzer(r);
                randomSet.add(r);
            } catch (BenutzerDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return randomSet;
    }

    public static Set<Reiseveranstaltung> getRandomPersistedReiseveranstaltungList() {

        Set<Reiseveranstaltung> randomSet = new HashSet<Reiseveranstaltung>();

        Random xx = new Random();
        Reiseveranstaltung r;


        if (xx.nextBoolean()) {
            try {
                r = new Reiseveranstaltung("Paris", "3tagesreise nach Paris", new Date(2010, 10, 10), new Date(2010, 10, 13), 1300.12, "parislink.html");
                DAO_Reiseveranstaltung.createReiseveranstaltung(r);
                randomSet.add(r);
            } catch (ReiseveranstaltungDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Reiseveranstaltung("China", "Erlebnissurlaub in Asien", new Date(2011, 4, 10), new Date(2011, 7, 9), 429.27, "china.html");
                DAO_Reiseveranstaltung.createReiseveranstaltung(r);
                randomSet.add(r);
            } catch (ReiseveranstaltungDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Reiseveranstaltung("WeitWeitWeg", "DAS geschenk f�r Leute die sie nicht m�gen.", new Date(2011, 10, 10), new Date(2100, 10, 13), 99.0, "weitweg.html");
                DAO_Reiseveranstaltung.createReiseveranstaltung(r);
                randomSet.add(r);
            } catch (ReiseveranstaltungDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Reiseveranstaltung("Serbien", "Gehst du in da schenste Land", new Date(2012, 4, 13), new Date(2012, 9, 9), 1300.12, "Nix Internet in Serbien");
                DAO_Reiseveranstaltung.createReiseveranstaltung(r);
                randomSet.add(r);
            } catch (ReiseveranstaltungDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (xx.nextBoolean()) {
            try {
                r = new Reiseveranstaltung("Friedhof", "Die Letzte Reise die Sie machen", new Date(2012, 12, 21), new Date(2999, 9, 9), 0.50, "XXX.html");
                DAO_Reiseveranstaltung.createReiseveranstaltung(r);
                randomSet.add(r);
            } catch (ReiseveranstaltungDbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return randomSet;
    }

    public static void main(String[] args) {

        try {
            Reisetyp re = new Reisetyp("St�dtereisen", getRandomPersistedBenutzerList(), getRandomPersistedReiseveranstaltungList());
            System.out.println(re.getBenutzers());
            DAO_Reisetyp.createReisetyp(re);

        } catch (DbException e) {
            e.printStackTrace();
        }


        //try {
        //	DAO_Reisetyp.createReisetyp(new Reisetyp("Testbezeichnung",getRandomPersistedBenutzerList()));
        //} catch (ReisetypDbException e) {
        //	e.printStackTrace();
        //}

    }
}
