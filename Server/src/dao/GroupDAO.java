/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author holohoi
 */
import entity.Group;
import entity.Group_User;
import entity.Message_Group;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class GroupDAO {

    public static List<Group> searchByName(String nameForSearch) {
        List<Group> listUser = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Group where name like :nameInput";
        Query query = session.createQuery(hql);
        query.setParameter("nameInput", "%" + nameForSearch + "%");

        listUser = query.list();
        session.getTransaction().commit();
        return listUser;
    }

    public static List<Group> getListGroup() {
        List<Group> listGroup = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Group";
        Query query = session.createQuery(hql);
        listGroup = query.list();
        session.getTransaction().commit();
        return listGroup;
    }

    public static List<Group> getListGroup(User user) {
        List<Group_User> listGroupUser = GroupUserDAO.findAllGroupByUser(user);

        List<Group> listGroup = new ArrayList<>();
        for (Group_User x : listGroupUser) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", x.getGroupID().getId());
            query.uniqueResult();

            boolean isNotResult = query.list().isEmpty();

            if (isNotResult) {
                session.getTransaction().commit();
            } else {
                Group temp = (Group) query.list().get(0);
                session.getTransaction().commit();
                listGroup.add(temp);
            }
        }
        return listGroup;
    }

    public static boolean addGroup(Group course) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();
            return false;
        }
        return true;
    }

    public static Pair createNewGroup(int userID, String name, List<Integer> memberIDs) {
        User creator = UserDAO.findOneById(userID);
        Group newGr = null;
        Message_Group newMsgLast = null;

        if (creator != null) {
            newGr = new Group(name, creator);
            addGroup(newGr);

            GroupUserDAO.addUser(creator.getId(), newGr, creator);
            Message_Group newMsg = new Message_Group(creator.getName() + " create new group", creator, newGr);
            MessageGroupDAO.addMessage(newMsg);

            for (int memberID : memberIDs) {
                User member = UserDAO.findOneById(memberID);
                String[] memberName = member.getName().split(" ");

                GroupUserDAO.addUser(memberID, newGr, creator);
                newMsgLast = new Message_Group(creator.getName() + " added " + memberName[memberName.length - 1], creator, newGr);
                MessageGroupDAO.addMessage(newMsgLast);
            }
        }

        return new Pair<>(newGr, newMsgLast);
    }

    public static Group findOneById(int _id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Group where id = :id";
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
            Group temp = (Group) query.list().get(0);
            session.getTransaction().commit();
            return temp;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

    }
}
