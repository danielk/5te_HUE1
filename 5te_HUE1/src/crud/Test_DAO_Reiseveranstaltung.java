package crud;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import exceptions.ReiseveranstaltungDbException;
import model.Reiseveranstaltung;

@SuppressWarnings("deprecation")
public class Test_DAO_Reiseveranstaltung {

    public static List<Reiseveranstaltung> reiseveranstaltungList;

    {
        reiseveranstaltungList = new LinkedList<Reiseveranstaltung>();
        reiseveranstaltungList.add(new Reiseveranstaltung("Paris", "3tagesreise nach Paris", new Date(2010, 10, 10), new Date(2010, 10, 13), 1300.12, "parislink.html"));
        reiseveranstaltungList.add(new Reiseveranstaltung("China", "Erlebnissurlaub in Asien", new Date(2011, 4, 10), new Date(2011, 7, 9), 429.27, "china.html"));
        reiseveranstaltungList.add(new Reiseveranstaltung("WeitWeitWeg", "DAS geschenk f�r Leute die sie nicht m�gen.", new Date(2011, 10, 10), new Date(2100, 10, 13), 99.0, "weitweg.html"));
        reiseveranstaltungList.add(new Reiseveranstaltung("Serbien", "Gehst du in da schenste Land", new Date(2012, 4, 13), new Date(2012, 9, 9), 1300.12, "Nix Internet in Serbien"));
        reiseveranstaltungList.add(new Reiseveranstaltung("Friedhof", "Die Letzte Reise die Sie machen", new Date(2012, 12, 21), new Date(2999, 9, 9), 0.50, "XXX.html"));

    }

    public static void main(String[] args) {
        new Test_DAO_Reiseveranstaltung();
    }

    public Test_DAO_Reiseveranstaltung() {
        try {
            create();
            read();
            update();
            delete();
            fetchall();
        } catch (ReiseveranstaltungDbException e) {
            e.printStackTrace();
        }
    }

    public void create() throws ReiseveranstaltungDbException {

        int works = 0;

        for (Reiseveranstaltung reis : reiseveranstaltungList) {
            try {
                if (DAO_Reiseveranstaltung.createReiseveranstaltung(reis))
                    works++;
            } catch (ReiseveranstaltungDbException e) {
            }
        }

        int loop = 1;
        for (Reiseveranstaltung reis : reiseveranstaltungList) {
            if (loop == reis.getReiseveranstaltungId()) {
                works++;
            }

            loop++;
        }

        if (works != 10) {
            throw new ReiseveranstaltungDbException(
                    "FAIL - Create hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Create hat funktioniert");

    }

    public void read() throws ReiseveranstaltungDbException {
        int works = 0;

        try {
            if (DAO_Reiseveranstaltung.readReiseveranstaltung(2).getReiseveranstaltungZielort()
                    .equals("China"))
                works++;
        } catch (ReiseveranstaltungDbException e3) {
            System.out.println(e3);
        }

        DAO_Reiseveranstaltung.createReiseveranstaltung(new Reiseveranstaltung("Neue Reiseveranstaltung"));

        try {
            if (DAO_Reiseveranstaltung.readReiseveranstaltung(6).getReiseveranstaltungZielort()
                    .equals("Neue Reiseveranstaltung"))
                works++;

        } catch (ReiseveranstaltungDbException e3) {
            System.out.println(e3);
        }

        if (works != 2) {
            throw new ReiseveranstaltungDbException("FAIL - Read hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Read hat funktioniert");
    }

    public void update() throws ReiseveranstaltungDbException {
        int works = 0;
        try {

            Reiseveranstaltung xx = DAO_Reiseveranstaltung.readReiseveranstaltung(1);
            xx.setReiseveranstaltungBeschreibung("ErwinLand");

            DAO_Reiseveranstaltung.updateReiseveranstaltung(xx);
            xx = DAO_Reiseveranstaltung.readReiseveranstaltung(1);

            if (xx.getReiseveranstaltungBeschreibung().equals("ErwinLand"))
                works++;

        } catch (ReiseveranstaltungDbException e3) {
            e3.printStackTrace();
        }

        if (works != 1) {
            throw new ReiseveranstaltungDbException(
                    "FAIL - Update hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Update hat funktioniert");
    }

    private void delete() throws ReiseveranstaltungDbException {
        int works = 0;

        Reiseveranstaltung before = DAO_Reiseveranstaltung.readReiseveranstaltung(1);

        DAO_Reiseveranstaltung.deleteReiseveranstaltung(before);

        Reiseveranstaltung after = null;
        try {
            after = DAO_Reiseveranstaltung.readReiseveranstaltung(1);
        } catch (Exception e) {
        }

        if (after == null)
            works++;

        if (works != 1) {
            throw new ReiseveranstaltungDbException(
                    "FAIL - Delete hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Delete hat funktioniert");

    }

    private void fetchall() throws ReiseveranstaltungDbException {
        List<Reiseveranstaltung> ls = DAO_Reiseveranstaltung.fetchAllReiseveranstaltungs();
        if (ls.size() != 5) throw new ReiseveranstaltungDbException("FAIL - Fetchall hat nicht funktioniert");
        System.out.println("SUCCESS - Fetchall hat funktioniert");
    }
}
