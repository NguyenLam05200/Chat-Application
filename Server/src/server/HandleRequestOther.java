/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.MessageDAO;
import dao.UserDAO;
import entity.Message;
import entity.User;
import java.util.List;

/**
 *
 * @author holohoi
 */
public class HandleRequestOther {

    public static Object[][] getListMsgChat(Object[] req, String _dispatchMsg, User user) {
        // init response:
        Object[][] res;

        // handle request:
        String contactID = req[1].toString();
        System.out.println("req: " + contactID);
        User contact = UserDAO.findOneById(Integer.parseInt(contactID));
        List<Message> listMsgChat = MessageDAO.getListMsgChat(user, contact);

        int size = listMsgChat.size();
        res = new Object[size + 1][];
        res[0] = new Object[]{_dispatchMsg};
        for (int i = 1; i < size + 1; i++) {
            res[i] = listMsgChat.get(i - 1).getObject();
        }
        return res;
    }

}
