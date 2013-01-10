package crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.HibernateUtil;
import model.Reisetyp;
import exceptions.ReisetypDbException;

public class DAO_Reisetyp {

    public static boolean createReisetyp(Reisetyp reisetyp) throws ReisetypDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Integer id = (Integer) session.save(reisetyp);
            reisetyp.setReisetypId(id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReisetypDbException(e);
        } finally {
            session.close();
        }

        System.out.println("Erfolgreich Reisetyp erstellt.");
        return true;
    }

    public static Reisetyp readReisetyp(Integer id) throws ReisetypDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Reisetyp b = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Reisetyp where REISETYP_ID = :ID");
            q.setInteger("ID", id);
            b = ((Reisetyp) q.uniqueResult());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReisetypDbException(e);
        } finally {
            session.close();
        }

        if (b == null) throw new ReisetypDbException("Reisetyp nicht gefunden");
        System.out.println("Erfolgreich Reisetyp gelesen.");
        return b;
    }

    public static boolean updateReisetyp(Reisetyp rei) throws ReisetypDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(rei);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReisetypDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Reisetyp geupdated.");
        return true;
    }

    public static boolean deleteReisetyp(Reisetyp ben) throws ReisetypDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ben);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReisetypDbException(e);
        } finally {
            session.close();
        }
        System.out.println("Erfolgreich Reisetyp gel�scht.");
        return false;
    }

    @SuppressWarnings("unchecked")
    public static List<Reisetyp> fetchAllReisetyps() throws ReisetypDbException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Reisetyp> ben = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Reisetyp");
            ben = q.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ReisetypDbException(e);
        } finally {
            session.close();
        }

        if (ben == null) throw new ReisetypDbException("Error beim holen von allen Reisetypen");
        System.out.println("Erfolgreich alle Reisetyp gelesen.");
        return ben;
    }


}
