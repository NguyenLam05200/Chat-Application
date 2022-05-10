/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author holohoi
 */
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class UserDAO {

    public static List<User> getListUser() {
        List<User> listUser = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User";
        Query query = session.createQuery(hql);
        listUser = query.list();
        session.getTransaction().commit();
        return listUser;
    }

//    public static List<User> getListStudent() {
//        List<User> listUser = null;
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        String hql = "from User where role = :role";
//        Query query = session.createQuery(hql);
//        query.setParameter("role", 1);
//
//        listUser = query.list();
//        session.getTransaction().commit();
//        return listUser;
//    }
    public static User findOneById(int _id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", _id);
        query.uniqueResult();

        boolean isNotResult = query.list().isEmpty();

        if (isNotResult) {
//            System.out.println("Not result");
            session.getTransaction().commit();
            return null;
        } else {
//            System.out.println("Have result");
            User temp = (User) query.list().get(0);
            session.getTransaction().commit();
            return temp;
        }
    }

    public static User findOneByUsername(String _username) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", _username);
        query.uniqueResult();

        boolean isNotResult = query.list().isEmpty();

        if (isNotResult) {
//            System.out.println("Not result");
            session.getTransaction().commit();
            return null;
        } else {
//            System.out.println("Have result");
            User temp = (User) query.list().get(0);
            session.getTransaction().commit();
            return temp;
        }
    }

    public static boolean changePassword(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();

            return false;
        }
        return true;
    }

    public static boolean addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<User> temp = getListUser();
        for (User x : temp) {
            System.out.println(x.getName());
        }
    }
}
