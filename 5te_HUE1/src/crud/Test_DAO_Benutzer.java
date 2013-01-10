package crud;

import java.util.LinkedList;
import java.util.List;


import exceptions.BenutzerDbException;
import model.Benutzer;

public class Test_DAO_Benutzer {

    public static List<Benutzer> benutzerList;

    {
        benutzerList = new LinkedList<Benutzer>();
        benutzerList.add(new Benutzer("kaiser@epsillo.net", "kaiserpw", false));
        benutzerList.add(new Benutzer("buder@epsillo.net", "buderpw", true));
        benutzerList.add(new Benutzer("enne@epsillo.net", "ennepw", false));
        benutzerList.add(new Benutzer("otto.reichel@htlstp.ac.at", "reiopw", true));
        benutzerList.add(new Benutzer("alexandra.buchner@htlstp.ac.at", "buchpw", false));
        benutzerList.add(new Benutzer("klaus.hasenzagl@htlstp.ac.at", "hasepw", false));
        benutzerList.add(new Benutzer(null, "nullpw", false));
    }

    public static void main(String[] args) {

        new Test_DAO_Benutzer();
    }

    public Test_DAO_Benutzer() {
        try {
            create();
            read();
            update();
            delete();
            fetchall();
        } catch (BenutzerDbException e) {
            e.printStackTrace();
        }
    }

    private void delete() throws BenutzerDbException {
        int works = 0;

        Benutzer before = DAO_Benutzer.readBenutzer("kaiser@epsillo.net");

        DAO_Benutzer.deleteBenutzer(before);

        Benutzer after = null;
        try {
            after = DAO_Benutzer.readBenutzer("kaiser@epsillo.net");
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (after == null) works++;

        DAO_Benutzer.createBenutzer(before);

        if (before.equals(DAO_Benutzer.readBenutzer("kaiser@epsillo.net"))) {
            works++;
        }

        before.setBenutzerPasswort("KaiserNewPassword");

        DAO_Benutzer.updateBenutzer(before);

        DAO_Benutzer.deleteBenutzer(DAO_Benutzer.readBenutzer("kaiser@epsillo.net"));


        after = null;
        try {
            after = DAO_Benutzer.readBenutzer("kaiser@epsillo.net");
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (after == null) works++;

        if (works != 3) {
            throw new BenutzerDbException("FAIL - Delete hat nicht funktioniert");
        } else System.out.println("SUCCESS - Delete hat funktioniert");

    }

    public void create() throws BenutzerDbException {

        int works = 0;

        for (Benutzer ben : benutzerList) {
            try {
                if (DAO_Benutzer.createBenutzer(ben)) works++;
            } catch (BenutzerDbException e) {
            }
        }

        if (works != 6) {
            throw new BenutzerDbException("FAIL - Create hat nicht funktioniert");
        } else System.out.println("SUCCESS - Create hat funktioniert");

    }

    public void read() throws BenutzerDbException {
        int works = 0;

        try {
            if (DAO_Benutzer.readBenutzer("kaiser@epsillo.net").equals(new Benutzer("kaiser@epsillo.net", "kaiserpw", false)))
                works++;
        } catch (BenutzerDbException e3) {
        }

        try {
            if (DAO_Benutzer.readBenutzer("buder@epsillo.net").equals(benutzerList.get(1))) works++;
        } catch (BenutzerDbException e2) {
        }


        try {
            if (DAO_Benutzer.readBenutzer("enne@epsillo.net").getBenutzerPasswort().equals("ennepw")) works++;
        } catch (BenutzerDbException e1) {
        }

        try {
            if (DAO_Benutzer.readBenutzer("unbekannteEMAIL@epsillo.net").getBenutzerPasswort().equals("ennepw"))
                works++;
        } catch (BenutzerDbException e) {
        }

        if (works != 3) {
            throw new BenutzerDbException("FAIL - Read hat nicht funktioniert");
        } else System.out.println("SUCCESS - Read hat funktioniert");
    }

    public void update() throws BenutzerDbException {
        int works = 0;

        try {
            Benutzer before = DAO_Benutzer.readBenutzer("kaiser@epsillo.net");
            before.setBenutzerPasswort("newKaiserPW");
            DAO_Benutzer.updateBenutzer(before);

            Benutzer after = DAO_Benutzer.readBenutzer("kaiser@epsillo.net");

            if (after.getBenutzerPasswort().equals("newKaiserPW")) {
                works++;
            }

        } catch (BenutzerDbException e3) {
            e3.printStackTrace();
        }


        if (works != 1) {
            throw new BenutzerDbException("FAIL - Update hat nicht funktioniert");
        } else System.out.println("SUCCESS - Update hat funktioniert");
    }

    private void fetchall() throws BenutzerDbException {
        List<Benutzer> ls = DAO_Benutzer.fetchAllBenutzers();
        System.out.println(ls.size());
    }


}
