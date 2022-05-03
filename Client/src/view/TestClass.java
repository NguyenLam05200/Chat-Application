/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author holohoi
 */
public class TestClass {

    public static void main(String[] args) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        System.out.println("test: " + imageIcon.toString());
    }
}
