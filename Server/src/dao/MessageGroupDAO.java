/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author holohoi
 */
import static dao.MessageDAO.getListMsgChat;
import entity.Group;
import entity.Message;
import entity.Message_Group;
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

public class MessageGroupDAO {

    public static List<Message_Group> getListMsg(Group group) {
        if (group == null) {
            System.out.println("Not group");
            return null;
        } else {
            List<Message_Group> temp = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = " FROM Message_Group where sendTo = :input";
            Query query = session.createQuery(hql);
            query.setParameter("input", group);

            temp = query.list();
//            boolean isNotResult = temp.isEmpty();
            session.getTransaction().commit();
            return temp;
        }
    }

    public static Message_Group getMsgRelateSoon(Group group) {
        if (group == null) {
            System.out.println("Not group");
            return null;
        } else {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = " FROM Message_Group where sendTo = :input ORDER BY sendAt DESC LIMIT 1";
            Query query = session.createQuery(hql);
            query.setParameter("input", group);

            boolean isNotResult = query.list().isEmpty();

            if (isNotResult) {
//            System.out.println("Not result");
                session.getTransaction().commit();
                return null;
            } else {
//            System.out.println("Have result");
                Message_Group temp = (Message_Group) query.list().get(0);
                session.getTransaction().commit();
                return temp;
            }
        }
    }

    public static List<Message_Group> getListMsgContact(List<Group> listGroup) {
        List<Message_Group> listMsg = new ArrayList<>();
        for (Group x : listGroup) {
            if (x != null) {
                listMsg.add(getMsgRelateSoon(x));
            }
        }
        return listMsg;
    }

    public static boolean addMessage(Message_Group msg) {
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
