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
import entity.User;
import entity.Group_User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class GroupUserDAO {

    public static boolean isMember(User user, Group group) {
        if (user == null || group == null) {
            System.out.println("Not group input");
            return false;
        } else {
            List<Group_User> listUserGroup = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group_User where groupID = :inputIdGroup and userID = :inputIdUser";
            Query query = session.createQuery(hql);
            query.setParameter("inputIdGroup", group);
            query.setParameter("inputIdUser", user);

            listUserGroup = query.list();
            session.getTransaction().commit();
            return !listUserGroup.isEmpty();
        }
    }

    public static List<Group_User> findAllUserByGroup(Group group) {
        if (group == null) {
            System.out.println("Not group input");
            return null;
        } else {
            List<Group_User> listUserGroup = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group_User where groupID = :inputId";
            Query query = session.createQuery(hql);
            query.setParameter("inputId", group);

            listUserGroup = query.list();
            session.getTransaction().commit();

            return listUserGroup;
        }
    }

    public static List<Group_User> findAllOtherUserInGroup(Group group, User user) {
        if (user == null || group == null) {
            System.out.println("Not group input");
            return null;
        } else {
            List<Group_User> listUserGroup = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group_User where groupID = :inputIdGroup and userID != :inputIdUser";
            Query query = session.createQuery(hql);
            query.setParameter("inputIdGroup", group);
            query.setParameter("inputIdUser", user);

            listUserGroup = query.list();
            session.getTransaction().commit();

            return listUserGroup;
        }
    }

    public static List<Group_User> findAllGroupByUser(User user) {
        if (user == null) {
            System.out.println("Not group input");
            return null;
        } else {
            List<Group_User> listUserGroup = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group_User where userID = :inputId";
            Query query = session.createQuery(hql);
            query.setParameter("inputId", user);

            listUserGroup = query.list();
            session.getTransaction().commit();

            return listUserGroup;
        }
    }

    public static Group_User findByUserGroup(User user, Group group) {
        if (group == null || user == null) {
            System.out.println("Not group or user input");
            return null;
        } else {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "from Group_User where groupID = :inputGroup and userID = :inputUser";
            Query query = session.createQuery(hql);
            query.setParameter("inputGroup", group);
            query.setParameter("inputUser", user);

            query.uniqueResult();

            boolean isNotResult = query.list().isEmpty();

            if (isNotResult) {
//            System.out.println("Not result");
                session.getTransaction().commit();
                return null;
            } else {
//            System.out.println("Have result");
                Group_User temp = (Group_User) query.list().get(0);
                session.getTransaction().commit();
                return temp;
            }
        }
    }

    public static String addUser(int userID, Group group, User creator) {
        User _user = UserDAO.findOneById(userID);
        if (_user != null) {
            Group_User user_group_check = findByUserGroup(_user, group);
            if (user_group_check == null) {
                Group_User temp = new Group_User(_user, group, creator);
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                try {
                    session.saveOrUpdate(temp);
                    session.getTransaction().commit();
                } catch (HibernateException e) {
                    System.out.println("Opps, " + e);
                    session.getTransaction().commit();
                    return "Fail to add or update!";
                }
                return "Add success!";
            } else {
                return "User is already a member in this group!";
            }
        } else {
            return "User is not exist!";
        }
    }

    public static void main(String[] args) {
        User x = UserDAO.findOneById(5);
        Group y = GroupDAO.findOneById(3);
        System.out.println(isMember(x, y));
    }
}
