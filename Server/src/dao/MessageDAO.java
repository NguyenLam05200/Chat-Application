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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

    public static List<Message> getListMsgChat(User user1, User user2) {
        if (user1 == null || user2 == null) {
            System.out.println("Not user");
            return null;
        } else {
            List<Message> listMessage = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Message where isAvailable = true and (sendTo = :user1 or sendTo= :user2) and (sendBy = :user1 or sendBy= :user2)";
            Query query = session.createQuery(hql);
            query.setParameter("user1", user1);
            query.setParameter("user2", user2);

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

    public static List<Message> getMsgRelate(User user) {
        if (user == null) {
            System.out.println("Not user");
            return null;
        } else {
            List<Message> listMessage = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Message where isAvailable = true and (sendTo = :input or sendBy = :input)";
            Query query = session.createQuery(hql);
            query.setParameter("input", user);

            listMessage = query.list();
            session.getTransaction().commit();

            return listMessage;
        }
    }

    public static List<Message> getMsgRelateSoon(User user) {
        if (user == null) {
            System.out.println("Not user");
            return null;
        } else {
            List<Message> listMessage = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "  FROM Message\n"
                    + "  WHERE sendAt in ( SELECT MAX(sendAt)\n"
                    + "                      FROM Message\n"
                    + "                      WHERE sendBy = :input or sendTo = :input\n"
                    + "                      GROUP BY sendBy, sendTo)";
            Query query = session.createQuery(hql);
            query.setParameter("input", user);

            listMessage = query.list();
            session.getTransaction().commit();

            return listMessage;
        }
    }

    public static Pair getListMsgContact(User user) {
        List<Message> listMsg = getMsgRelateSoon(user);

        Set<Integer> set = new LinkedHashSet<>();

        for (Message msg : listMsg) {
            if (msg.getSendBy().getId() != user.getId()) {
                set.add(msg.getSendBy().getId());
            } else {
                set.add(msg.getSendTo().getId());
            }
        }

        List<User> contacts = new ArrayList<>();
        for (int id : set) {
            User u = UserDAO.findOneById(id);
            contacts.add(0, u);
        }

        List<Message> temp = new ArrayList<>();
        int size = listMsg.size();
        for (int i = size - 1; i >= 0; i--) {
            Message x = listMsg.get(i);
            int sendBy = x.getSendBy().getId();
            int sendTo = x.getSendTo().getId();
            if (sendBy != user.getId() && set.contains(sendBy)) {
                temp.add(x);
                set.remove(sendBy);
            } else if (set.contains(sendTo)) {
                temp.add(x);
                set.remove(sendTo);
            }
        }

        return new Pair<>(temp, contacts);
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

//        User user = UserDAO.findOneByUsername("a");
//
//        Pair pair = getListMsgContact(user);
//        List<Message> listMsg = (List<Message>) pair.getKey();
//        List<User> listContacts = (List<User>) pair.getValue();
//        System.out.println("stop");
////        List<Message> listMsg = getMsgRelateSoon(user);
//
//        for (Message x : listMsg) {
//            System.out.println(x.getSendBy().getId() + " -> " + x.getSendTo().getId());
//        }
//        for (User x : listContacts) {
//            System.out.println("user: " + x.getId());
//        }
        User user1 = UserDAO.findOneByUsername("a");
        User user2 = UserDAO.findOneByUsername("s");
        List<Message> listMsg = getListMsgChat(user1, user2);
        for (Message x : listMsg) {
            System.out.println(x.getContent());
        }

    }
}
