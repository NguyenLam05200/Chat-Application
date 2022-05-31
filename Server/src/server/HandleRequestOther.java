/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.GroupDAO;
import dao.GroupUserDAO;
import dao.MessageDAO;
import dao.MessageGroupDAO;
import dao.UserDAO;
import entity.Group;
import entity.Group_User;
import entity.Message;
import entity.Message_Group;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author holohoi
 */
public class HandleRequestOther {

    public static Object[][] getListMsgChat(Object[] req, String _dispatchMsg, User user) {
        // init response:
        Object[][] res;

        // handle request:
        boolean isUser = Boolean.parseBoolean(req[1].toString());
        int contactID = Integer.parseInt(req[2].toString());
        if (isUser) {
            User contact = UserDAO.findOneById(contactID);
            List<Message> listMsgChat = MessageDAO.getListMsgChat(user, contact);

            int size = listMsgChat.size();
            res = new Object[size + 1][];
            res[0] = new Object[]{_dispatchMsg, true};
            for (int i = 1; i < size + 1; i++) {
                res[i] = listMsgChat.get(i - 1).getObject(user);
            }
        } else {
            Group contact = GroupDAO.findOneById(contactID);
            boolean isMemberInGroup = GroupUserDAO.isMember(user, contact);
            if (isMemberInGroup) {
                List<Message_Group> listMsgChat = MessageGroupDAO.getListMsg(contact);

                int size = listMsgChat.size();
                res = new Object[size + 1][];
                res[0] = new Object[]{_dispatchMsg, false};
                for (int i = 1; i < size + 1; i++) {
                    res[i] = listMsgChat.get(i - 1).getObject(user);
                }
            } else {
                res = new Object[][]{{_dispatchMsg, false}};
            }

        }

        return res;
    }

    public static void sendMsg(Object[] req, String _dispatchMsg, User user) {
        // handle request:

        boolean isUser = Boolean.parseBoolean(req[1].toString());
        int contactID = Integer.parseInt(req[2].toString());

        String content = req[3].toString();
        if (isUser) {
            User contact = UserDAO.findOneById(contactID);
            Message newMsg = new Message(content, user, contact);
            boolean isAddSuccess = MessageDAO.addMessage(newMsg);
            if (isAddSuccess) {
                System.out.println("Send msg successfully!");

                Server.deliverMsg(isUser, newMsg.getObject(user), contact.getObject(), user.getId());
                Server.deliverMsg(isUser, newMsg.getObject(contact), user.getObject(), contact.getId());
            } else {
                System.out.println("Send msg failure!");
            }
        } else {
            Group contact = GroupDAO.findOneById(contactID);
            Message_Group newMsg = new Message_Group(content, user, contact);
            boolean isAddSuccess = MessageGroupDAO.addMessage(newMsg);
            if (isAddSuccess) {
                System.out.println("Send msg successfully!");
                Server.deliverMsg(isUser, newMsg.getObject(user), contact.getObject(), user.getId());
                List<Group_User> temp = GroupUserDAO.findAllOtherUserInGroup(contact, user);
                for (Group_User x : temp) {
                    Object[] msg = newMsg.getObject(user, true);
                    Server.deliverMsg(isUser, msg, contact.getObject(), x.getUserID().getId());
                }
            } else {
                System.out.println("Send msg failure!");
            }
        }

    }

    public static Object[][] searchContact(Object[] req, String _dispatchMsg, User user) {
        // init response:
        Object[][] res;

        // handle request:
        String usernameForSearch = req[1].toString();
        List<User> results = UserDAO.searchByName(usernameForSearch);
        List<Group> resultGroups = GroupDAO.searchByName(usernameForSearch);
        List<Group> temp = new ArrayList<>();
        for (Group x : resultGroups) {
            if (GroupUserDAO.isMember(user, x)) {
                temp.add(x);
            }
        }
        int size1 = temp.size();
        int size2 = results.size();

        int size = size1 + size2;
        res = new Object[size + 1][];
        res[0] = new Object[]{_dispatchMsg};
        for (int i = 0; i < size1; i++) {
            res[i + 1] = temp.get(i).getObject();
        }
        for (int i = size1; i < size; i++) {
            res[i + 1] = results.get(i - size1).getObject();
        }
        return res;
    }

    public static Object[][] searchUser(Object[] req, String _dispatchMsg) {
        // init response:
        Object[][] res;

        // handle request:
        String usernameForSearch = req[1].toString();
        List<User> results = UserDAO.searchByName(usernameForSearch);

        int size = results.size();
        res = new Object[size + 1][];
        res[0] = new Object[]{_dispatchMsg};

        for (int i = 1; i < size + 1; i++) {
            res[i] = results.get(i - 1).getObject();
        }
        return res;
    }

    public static Object[][] createNewGroup(Object[] req, String _dispatchMsg, User creator) {
        String name = req[1].toString();
        List<Integer> memberIDs = new ArrayList<>();
        for (int i = 2; i < req.length; i++) {
            memberIDs.add(Integer.parseInt(req[i].toString()));
        }

        Pair pair = GroupDAO.createNewGroup(creator.getId(), name, memberIDs);
        Group newGr = (Group) pair.getKey();
        Message_Group newMsg = (Message_Group) pair.getValue();

        Server.deliverMsg(false, newMsg.getObject(creator), newGr.getObject(), creator.getId());
        for (int x : memberIDs) {
            Object[] msg = newMsg.getObject(creator, true);
            Server.deliverMsg(false, msg, newGr.getObject(), x);
        }

        return new Object[][]{{_dispatchMsg}, newGr.getObject()};
    }

}
