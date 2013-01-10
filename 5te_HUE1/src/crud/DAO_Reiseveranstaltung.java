package crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.HibernateUtil;
import model.Reiseveranstaltung;
import exceptions.ReiseveranstaltungDbException;

public class DAO_Reiseveranstaltung {

    public static boolean createReiseveranstaltung(Reiseveranstaltung reiseveranstaltung) throws ReiseveranstaltungDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Integer id = (Integer) session.save(reiseveranstaltung);
            reiseveranstaltung.setReiseveranstaltungId(id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReiseveranstaltungDbException(e);
        } finally {
            session.close();
        }

        System.out.println("Erfolgreich Reiseveranstaltung erstellt.");
        return true;
    }

    public static Reiseveranstaltung readReiseveranstaltung(Integer id) throws ReiseveranstaltungDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Reiseveranstaltung b = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Reiseveranstaltung where REISEVERANSTALTUNG_ID = :ID");
            q.setInteger("ID", id);
            b = ((Reiseveranstaltung) q.uniqueResult());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReiseveranstaltungDbException(e);
        } finally {
            session.close();
        }

        if (b == null) throw new ReiseveranstaltungDbException("Reiseveranstaltung nicht gefunden");
        System.out.println("Erfolgreich Reiseveranstaltung gelesen.");
        return b;
    }

    public static boolean updateReiseveranstaltung(Reiseveranstaltung rei) throws ReiseveranstaltungDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(rei);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReiseveranstaltungDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Reiseveranstaltung geupdated.");
        return true;
    }

    public static boolean deleteReiseveranstaltung(Reiseveranstaltung ben) throws ReiseveranstaltungDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ben);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReiseveranstaltungDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Reiseveranstaltung gel�scht.");
        return false;
    }

    @SuppressWarnings("unchecked")
    public static List<Reiseveranstaltung> fetchAllReiseveranstaltungs() throws ReiseveranstaltungDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Reiseveranstaltung> ben = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Reiseveranstaltung");
            ben = q.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReiseveranstaltungDbException(e);
        } finally {
            session.close();
        }

        if (ben == null) throw new ReiseveranstaltungDbException("Error beim holen von allen Reiseveranstaltungen");
        System.out.println("Erfolgreich alle Reiseveranstaltung gelesen.");
        return ben;
    }


}
