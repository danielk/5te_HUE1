package crud;

import java.util.LinkedList;
import java.util.List;

import exceptions.NfBenutzerDbException;
import model.Reisetyp;
import model.Reiseveranstaltung;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.HibernateUtil;

import exceptions.BenutzerDbException;

import model.Benutzer;

public class DAO_Benutzer {

    public static boolean createBenutzer(Benutzer ben) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(ben);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }

        System.out.println("Erfolgreich Benutzer erstellt.");
        return true;

    }

    public static List<Reiseveranstaltung> getReiseveranstaltungs(Benutzer ben) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Benutzer b;
        List<Reiseveranstaltung> reiseveranstaltungList = new LinkedList<Reiseveranstaltung>();

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Benutzer where BENUTZER_EMAIL = :EMAIL");
            q.setString("EMAIL", ben.getBenutzerEmail());
            b = ((Benutzer) q.uniqueResult());
            for (Reisetyp reisetyp : b.getReisetyps()) {
                reiseveranstaltungList.addAll(reisetyp.getReisetypReiseveranstaltungs());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }

        return reiseveranstaltungList;

    }

    public static List<Reisetyp> getReisetyps(Benutzer ben) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Benutzer b;

        List<Reisetyp> reisetypsList = new LinkedList<Reisetyp>();

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Benutzer where BENUTZER_EMAIL = :EMAIL");
            q.setString("EMAIL", ben.getBenutzerEmail());
            b = ((Benutzer) q.uniqueResult());
            reisetypsList.addAll(b.getReisetyps());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }

        return reisetypsList;

    }

    public static Benutzer readBenutzer(String email) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Benutzer b = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Benutzer where BENUTZER_EMAIL = :EMAIL");
            q.setString("EMAIL", email);
            b = ((Benutzer) q.uniqueResult());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }

        if (b == null) throw new NfBenutzerDbException();
        System.out.println("Erfolgreich Benutzer gelesen.");
        return b;

    }

    public static boolean updateBenutzer(Benutzer ben) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(ben);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Benutzer geupdated.");
        return true;
    }

    public static boolean deleteBenutzer(Benutzer ben) throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ben);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Benutzer gel�scht.");
        return false;
    }

    @SuppressWarnings("unchecked")
    public static List<Benutzer> fetchAllBenutzers() throws BenutzerDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Benutzer> ben = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Benutzer");
            ben = q.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new BenutzerDbException(e);
        } finally {
            session.close();
        }

        if (ben == null) throw new BenutzerDbException("Error beim holen von allen Benutzern");
        System.out.println("Erfolgreich alle Benutzer gelesen.");
        return ben;
    }


}
