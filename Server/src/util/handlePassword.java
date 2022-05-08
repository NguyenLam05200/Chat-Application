/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author holohoi
 */
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class handlePassword {

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        return toHexString(getSHA(password));
    }

    public static boolean comparePassword(String inputPassword, String dbPassword) throws NoSuchAlgorithmException {
        String inputPwd = hashPassword(inputPassword);
        return inputPwd.equals(dbPassword);
    }

    /* Driver code */
    public static void main(String args[]) {
        try {
            String string1 = "18120433";
            System.out.println("\n" + string1 + " : " + toHexString(getSHA(string1)));

            String string2 = "holohoi05200";
            System.out.println("\n" + string2 + " : " + toHexString(getSHA(string2)));

            System.out.println("\n" + string1 + " :" + hashPassword(string1));
            System.out.println("compare: " + comparePassword(string1, hashPassword(string2)));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }
}
