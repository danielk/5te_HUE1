package crud;

import java.util.LinkedList;
import java.util.List;

import exceptions.ReisetypDbException;
import model.Reisetyp;

public class Test_DAO_Reisetyp {

    public static List<Reisetyp> reisetypList;

    {
        reisetypList = new LinkedList<Reisetyp>();
        reisetypList.add(new Reisetyp("St�dtereisen"));
        reisetypList.add(new Reisetyp("Italien"));
        reisetypList.add(new Reisetyp("Last Minute"));
        reisetypList.add(new Reisetyp("England"));
        reisetypList.add(new Reisetyp("Baltikum"));
        reisetypList.add(new Reisetyp("Asien"));

    }

    public static void main(String[] args) {
        new Test_DAO_Reisetyp();
    }

    public Test_DAO_Reisetyp() {
        try {
            create();
            read();
            update();
            delete();
            fetchall();
        } catch (ReisetypDbException e) {
            e.printStackTrace();
        }
    }

    public static void create() throws ReisetypDbException {

        int works = 0;

        reisetypList = new LinkedList<Reisetyp>();
        reisetypList.add(new Reisetyp("St�dtereisen"));
        reisetypList.add(new Reisetyp("Italien"));
        reisetypList.add(new Reisetyp("Last Minute"));
        reisetypList.add(new Reisetyp("England"));
        reisetypList.add(new Reisetyp("Baltikum"));
        reisetypList.add(new Reisetyp("Asien"));

        for (Reisetyp reis : reisetypList) {
            try {
                if (DAO_Reisetyp.createReisetyp(reis))
                    works++;
            } catch (ReisetypDbException e) {
            }
        }

        int loop = 1;
        for (Reisetyp reis : reisetypList) {
            if (loop == reis.getReisetypId()) {
                works++;
            }

            loop++;
        }

        if (works != 12) {
            throw new ReisetypDbException(
                    "FAIL - Create hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Create hat funktioniert");

    }

    public void read() throws ReisetypDbException {
        int works = 0;

        try {
            if (DAO_Reisetyp.readReisetyp(1).getReisetypBezeichnung()
                    .equals("St�dtereisen"))
                works++;
        } catch (ReisetypDbException e3) {
        }

        DAO_Reisetyp.createReisetyp(new Reisetyp("Neuer Reisetyp"));

        try {
            if (DAO_Reisetyp.readReisetyp(7).getReisetypBezeichnung()
                    .equals("Neuer Reisetyp"))
                works++;
            System.out.println(DAO_Reisetyp.readReisetyp(7)
                    .getReisetypBezeichnung());
        } catch (ReisetypDbException e3) {
        }

        if (works != 2) {
            throw new ReisetypDbException("FAIL - Read hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Read hat funktioniert");
    }

    public void update() throws ReisetypDbException {
        int works = 0;
        try {

            Reisetyp xx = DAO_Reisetyp.readReisetyp(1);
            xx.setReisetypBezeichnung("ErwinLand");

            DAO_Reisetyp.updateReisetyp(xx);
            xx = DAO_Reisetyp.readReisetyp(1);

            if (xx.getReisetypBezeichnung().equals("ErwinLand"))
                works++;

        } catch (ReisetypDbException e3) {
            e3.printStackTrace();
        }

        if (works != 1) {
            throw new ReisetypDbException(
                    "FAIL - Update hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Update hat funktioniert");
    }

    private void delete() throws ReisetypDbException {
        int works = 0;

        Reisetyp before = DAO_Reisetyp.readReisetyp(1);

        DAO_Reisetyp.deleteReisetyp(before);

        Reisetyp after = null;
        try {
            after = DAO_Reisetyp.readReisetyp(1);
        } catch (Exception e) {
        }

        if (after == null)
            works++;

        if (works != 1) {
            throw new ReisetypDbException(
                    "FAIL - Delete hat nicht funktioniert");
        } else
            System.out.println("SUCCESS - Delete hat funktioniert");

    }

    private void fetchall() throws ReisetypDbException {
        List<Reisetyp> ls = DAO_Reisetyp.fetchAllReisetyps();
        if (ls.size() != 6) throw new ReisetypDbException("FAIL - Fetchall hat nicht funktioniert");
        System.out.println("SUCCESS - Fetchall hat funktioniert");
    }
}
