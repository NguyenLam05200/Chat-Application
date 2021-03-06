/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author holohoi
 */
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import server.HandleRequestAuth;
import util.HibernateUtil;
import util.handlePassword;

public class UserDAO {

    public static List<User> getListUser() {
        List<User> listUser = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User";
        Query query = session.createQuery(hql);
        listUser = query.list();
        session.getTransaction().commit();
        return listUser;
    }

    public static List<User> searchByName(String nameForSearch) {
        List<User> listUser = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User where name like :nameInput";
        Query query = session.createQuery(hql);
        query.setParameter("nameInput", "%" + nameForSearch + "%");

        listUser = query.list();
        session.getTransaction().commit();
        return listUser;
    }

    public static User findOneById(int _id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User where id = :id";
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
            User temp = (User) query.list().get(0);
            session.getTransaction().commit();
            return temp;
        }
    }

    public static User findOneByUsername(String _username) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from User where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", _username);
        query.uniqueResult();

        boolean isNotResult = query.list().isEmpty();

        if (isNotResult) {
//            System.out.println("Not result");
            session.getTransaction().commit();
            return null;
        } else {
//            System.out.println("Have result");
            User temp = (User) query.list().get(0);
            session.getTransaction().commit();
            return temp;
        }
    }

    public static boolean changePassword(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();

            return false;
        }
        return true;
    }

    public static boolean updateLastSeen(User user) {
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        user.setLastSeen(curTime);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();

            return false;
        }
        return true;
    }

    public static boolean addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Opps, " + e);
            session.getTransaction().commit();
            return false;
        }
        return true;
    }

    private static void generateUser() {
        String[] tenNam = new String[]{"Anh", "Vy", "Ng???c", "Nhi", "H??n", "Th??", "Linh", "Nh??", "Ng??n", "Ph????ng", "Th???o", "My", "Tr??n", "Qu???nh", "Nghi", "Trang", "Tr??m", "An", "Thy", "Ch??u", "Tr??c", "Uy??n", "Y???n", "??", "Ti??n", "Mai", "H??", "V??n", "Nguy??n", "H????ng", "Quy??n", "Duy??n", "Kim", "Trinh", "Thanh", "Tuy???n", "H???ng", "D????ng", "Chi", "Giang", "T??m", "Lam", "T??", "??nh", "Hi???n", "Kh??nh", "Minh", "Huy???n", "Th??y", "Vi", "Ly", "Dung", "Nhung", "Ph??c", "Lan", "Ph???ng", "??n", "Thi", "Khanh", "K???", "Nga", "T?????ng", "Th??y", "M???", "Hoa", "Tuy???t", "L??m", "Th???y", "??an", "H???nh", "Xu??n", "Oanh", "M???n", "Khu??", "Di???p", "Th????ng", "Nhi??n", "B??ng", "H???ng", "B??nh", "Loan", "Th??", "Ph?????ng", "Mi", "Nh??", "Nguy???t", "B??ch", "????o", "Di???m", "Ki???u", "Hi???u", "Di", "Li??n", "Tr??", "Tu???", "Th???m", "Di???u", "Qu??n", "Nh??n"
        };

        String[] tenNu = new String[]{"Anh", "Vy", "Ng???c", "Nhi", "H??n", "Th??", "Linh", "Nh??", "Ng??n", "Ph????ng", "Th???o", "My", "Tr??n", "Qu???nh", "Nghi", "Trang", "Tr??m", "An", "Thy", "Ch??u", "Tr??c", "Uy??n", "Y???n", "??", "Ti??n", "Mai", "H??", "V??n", "Nguy??n", "H????ng", "Quy??n", "Duy??n", "Kim", "Trinh", "Thanh", "Tuy???n", "H???ng", "D????ng", "Chi", "Giang", "T??m", "Lam", "T??", "??nh", "Hi???n", "Kh??nh", "Minh", "Huy???n", "Th??y", "Vi", "Ly", "Dung", "Nhung", "Ph??c", "Lan", "Ph???ng", "??n", "Thi", "Khanh", "K???", "Nga", "T?????ng", "Th??y", "M???", "Hoa", "Tuy???t", "L??m", "Th???y", "??an", "H???nh", "Xu??n", "Oanh", "M???n", "Khu??", "Di???p", "Th????ng", "Nhi??n", "B??ng", "H???ng", "B??nh", "Loan", "Th??", "Ph?????ng", "Mi", "Nh??", "Nguy???t", "B??ch", "????o", "Di???m", "Ki???u", "Hi???u", "Di", "Li??n", "Tr??", "Tu???", "Th???m", "Di???u", "Qu??n", "Nh??n"
        };

        String[] lotNam = new String[]{"Minh", "Ho??ng", "Gia", "Nguy???n", "Qu???c", "Thanh", "V??n", "Th??nh", "Anh", "Ng???c", "T???n", "?????c", "L??", "Tu???n", "Quang", "Tr???n", "H???u", "Nh???t", "Duy", "Tr???ng", "????nh", "????ng", "Hu???nh", "Trung", "B???o", "Ph??c", "Ti???n", "Ch??", "Thi??n", "C??ng", "Xu??n", "Ph???m", "V??", "Th??i", "Huy", "V??", "H???i", "Th???", "H???ng", "Kh??nh", "Tr??", "Ph?????c", "Ph??", "Nguy??n", "Vi???t", "M???nh", "B??", "Tr?????ng", "V??nh", "Ho??i", "Phan", "Cao", "?????ng", "H???", "D????ng", "Thi???n", "L??m", "Kim", "?????", "Tr????ng", "?????i", "Vi???t", "Phi", "Ph????ng", "Nam", "??o??n", "H??", "Ki???n", "Ng??", "Nh???t", "Hi???u", "B??i", "An", "H??ng", "Ch???n", "B??nh", "Kh???i", "Kh???c", "Kh??i", "Mai", "Ch??u", "S???", "V??", "T??ng", "L??", "Long", "H??ng", "H???o", "Ph??t", "Nh??", "??inh", "Qu??", "?????c", "Vinh", "Nh???t", "????ng", "L????ng", "K???", "Tr???nh"};
        String[] lotNu = new String[]{"Th???", "Ng???c", "Nguy???n", "Ho??ng", "L??", "Tr???n", "Thanh", "B???o", "Ph????ng", "Hu???nh", "Gia", "Minh", "Kim", "Qu???nh", "Ph???m", "Kh??nh", "H???ng", "M???", "H??", "V??", "V??", "Mai", "Th??y", "Anh", "Nh??", "Th???o", "Th???y", "Phan", "Y???n", "?????ng", "Xu??n", "H???", "Thi??n", "?????", "Nh???t", "Th??i", "T?????ng", "Tuy???t", "Nh??", "Th??y", "D????ng", "H???i", "Thu", "L??m", "Tr??c", "Tr????ng", "Ho??i", "??o??n", "Ng??", "T??", "Cao", "Ki???u", "??nh", "Ph??c", "B??ch", "Ch??u", "B??i", "Kh???", "V??n", "????nh", "T??m", "Th???c", "B???i", "??i", "L??", "H????ng", "Nguy??n", "Uy??n", "Th???y", "Tr???nh", "C???m", "????o", "Di???p", "Tu???", "Di???u", "Hu???", "Di???m", "Lan", "C??t", "Huy???n", "An", "Linh", "L??u", "Qu???", "Ng??n", "??inh", "Uy???n", "Tri???u", "Tr??", "Song", "B??nh", "Nguy???t", "Trang", "M???n", "K???", "Tr??m", "H???nh", "L????ng", "V????ng"};
        String[] ho = new String[]{"Nguy???n", "Tr???n", "L??", "Ph???m", "Hu???nh", "V??", "Phan", "Tr????ng", "B??i", "?????ng", "?????", "Ng??", "H???", "D????ng", "??inh", "??o??n", "L??m", "Mai", "Tr???nh", "????o", "Cao", "L??", "H??", "L??u", "L????ng", "Th??i", "Ch??u", "T???", "Ph??ng", "T??", "V????ng", "V??n", "T??ng", "Qu??ch", "L???i", "H???a", "Th???ch", "Di???p", "T???", "Chu", "La", "????m", "T???ng", "Giang", "Chung", "Tri???u", "Ki???u", "H???ng", "Trang", "?????ng", "Danh", "L??", "L???", "Th??n", "Kim", "M??", "B???ch", "Li??u", "Ti??u", "D??", "B??nh", "??u", "T??n", "Kh??u", "S??n", "T???t", "Nghi??m", "L???c", "Quan", "Ph????ng", "M???c", "Lai", "V??ng", "M???ch", "Thi???u", "Tr??", "?????u", "Nhan", "L??", "Tr??nh", "Ninh", "Vi", "Bi???n", "H??ng", "??n", "Ch???", "Nh??m", "T??n N???", "Thi", "Do??n", "Kh???ng", "Ph??", "???????ng", "??ng", "T??n Th???t", "Ng???y", "Vi??n", "T??o", "C??"};
        System.out.println("size ten nam " + tenNam.length);
        System.out.println("size ten nu " + tenNu.length);
        System.out.println("size dem nam " + lotNam.length);
        System.out.println("size dem nu " + lotNu.length);
        System.out.println("size ho " + ho.length);

        int min_val = 0;
        int max_val = 98;
        Random rand = new Random();

        for (int i = 10; i < 2000; i++) {
            String name = "";
            String email = "";
            boolean gender = true;

            int randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
            name += ho[randomNum];
            email += ho[randomNum];

            if (i % 3 == 0) {
                gender = true;
                randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
                name += " " + lotNam[randomNum];

                randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
                name += " " + tenNam[randomNum];
                email += tenNam[randomNum] + i + "" + randomNum;

            } else {
                gender = false;
                randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
                name += " " + lotNu[randomNum];

                randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
                name += " " + tenNu[randomNum];
                email += tenNu[randomNum] + i + "" + randomNum;
            }
            email += "@gmail.com";
            email = removeAccent(email);

            String password = i + "";
            try {
                password = handlePassword.hashPassword(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HandleRequestAuth.class.getName()).log(Level.SEVERE, null, ex);
            }

            User newUs = new User(i + "", password, name, email, gender);
            addUser(newUs);
        }

    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<User> temp = searchByName("Th??nh");
        for (User x : temp) {
            System.out.println(x.getName());
        }
    }
}
