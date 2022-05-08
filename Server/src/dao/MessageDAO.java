/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author holohoi
 */
import entity.Message;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javafx.util.Pair;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class MessageDAO {

    public static List<Message> getListMessage() {
        List<Message> listMessage = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Message";
        Query query = session.createQuery(hql);
        listMessage = query.list();
        session.getTransaction().commit();

        return listMessage;
    }

    public static List<Message> getMessagesByDeliver(User user) {
        if (user == null) {
            System.out.println("Not user");
            return null;
        } else {
            List<Message> listMessage = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Message where sendBy = :input";
            Query query = session.createQuery(hql);
            query.setParameter("inputId", user);

            listMessage = query.list();
            session.getTransaction().commit();

            return listMessage;
        }
    }

    public static List<Message> getMessagesByReceiver(User user) {
        if (user == null) {
            System.out.println("Not user");
            return null;
        } else {
            List<Message> listMessage = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Message where sendTo = :input";
            Query query = session.createQuery(hql);
            query.setParameter("input", user);

            listMessage = query.list();
            session.getTransaction().commit();

            return listMessage;
        }
    }

    public Pair getMessage(User user) {
        List<Message> deliveryMessages = getMessagesByDeliver(user);

        List<Message> receiveMessages = getMessagesByReceiver(user);

        return new Pair<>(deliveryMessages, receiveMessages);
    }

    public static boolean addMessage(Message msg) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(msg);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
    }
}
