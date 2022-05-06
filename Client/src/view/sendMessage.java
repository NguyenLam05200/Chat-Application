/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author holohoi
 */
public class sendMessage extends Thread {

    ObjectOutputStream outStream;
    Object objToSend;

    sendMessage(ObjectOutputStream _outStream, Object _objToSend) {
        outStream = _outStream;
        objToSend = _objToSend;
    }

    public void run() {
        try {
            // write on the output stream
            outStream.writeObject(objToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
