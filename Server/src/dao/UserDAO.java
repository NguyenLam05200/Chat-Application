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
        String[] tenNam = new String[]{"Anh", "Vy", "Ngọc", "Nhi", "Hân", "Thư", "Linh", "Như", "Ngân", "Phương", "Thảo", "My", "Trân", "Quỳnh", "Nghi", "Trang", "Trâm", "An", "Thy", "Châu", "Trúc", "Uyên", "Yến", "Ý", "Tiên", "Mai", "Hà", "Vân", "Nguyên", "Hương", "Quyên", "Duyên", "Kim", "Trinh", "Thanh", "Tuyền", "Hằng", "Dương", "Chi", "Giang", "Tâm", "Lam", "Tú", "Ánh", "Hiền", "Khánh", "Minh", "Huyền", "Thùy", "Vi", "Ly", "Dung", "Nhung", "Phúc", "Lan", "Phụng", "Ân", "Thi", "Khanh", "Kỳ", "Nga", "Tường", "Thúy", "Mỹ", "Hoa", "Tuyết", "Lâm", "Thủy", "Đan", "Hạnh", "Xuân", "Oanh", "Mẫn", "Khuê", "Diệp", "Thương", "Nhiên", "Băng", "Hồng", "Bình", "Loan", "Thơ", "Phượng", "Mi", "Nhã", "Nguyệt", "Bích", "Đào", "Diễm", "Kiều", "Hiếu", "Di", "Liên", "Trà", "Tuệ", "Thắm", "Diệu", "Quân", "Nhàn"
        };

        String[] tenNu = new String[]{"Anh", "Vy", "Ngọc", "Nhi", "Hân", "Thư", "Linh", "Như", "Ngân", "Phương", "Thảo", "My", "Trân", "Quỳnh", "Nghi", "Trang", "Trâm", "An", "Thy", "Châu", "Trúc", "Uyên", "Yến", "Ý", "Tiên", "Mai", "Hà", "Vân", "Nguyên", "Hương", "Quyên", "Duyên", "Kim", "Trinh", "Thanh", "Tuyền", "Hằng", "Dương", "Chi", "Giang", "Tâm", "Lam", "Tú", "Ánh", "Hiền", "Khánh", "Minh", "Huyền", "Thùy", "Vi", "Ly", "Dung", "Nhung", "Phúc", "Lan", "Phụng", "Ân", "Thi", "Khanh", "Kỳ", "Nga", "Tường", "Thúy", "Mỹ", "Hoa", "Tuyết", "Lâm", "Thủy", "Đan", "Hạnh", "Xuân", "Oanh", "Mẫn", "Khuê", "Diệp", "Thương", "Nhiên", "Băng", "Hồng", "Bình", "Loan", "Thơ", "Phượng", "Mi", "Nhã", "Nguyệt", "Bích", "Đào", "Diễm", "Kiều", "Hiếu", "Di", "Liên", "Trà", "Tuệ", "Thắm", "Diệu", "Quân", "Nhàn"
        };

        String[] lotNam = new String[]{"Minh", "Hoàng", "Gia", "Nguyễn", "Quốc", "Thanh", "Văn", "Thành", "Anh", "Ngọc", "Tấn", "Đức", "Lê", "Tuấn", "Quang", "Trần", "Hữu", "Nhật", "Duy", "Trọng", "Đình", "Đăng", "Huỳnh", "Trung", "Bảo", "Phúc", "Tiến", "Chí", "Thiên", "Công", "Xuân", "Phạm", "Vũ", "Thái", "Huy", "Võ", "Hải", "Thế", "Hồng", "Khánh", "Trí", "Phước", "Phú", "Nguyên", "Việt", "Mạnh", "Bá", "Trường", "Vĩnh", "Hoài", "Phan", "Cao", "Đặng", "Hồ", "Dương", "Thiện", "Lâm", "Kim", "Đỗ", "Trương", "Đại", "Viết", "Phi", "Phương", "Nam", "Đoàn", "Hà", "Kiến", "Ngô", "Nhựt", "Hiếu", "Bùi", "An", "Hùng", "Chấn", "Bình", "Khải", "Khắc", "Khôi", "Mai", "Châu", "Sỹ", "Vĩ", "Tùng", "Lý", "Long", "Hưng", "Hạo", "Phát", "Như", "Đinh", "Quý", "Đắc", "Vinh", "Nhất", "Đông", "Lương", "Kỳ", "Trịnh"};
        String[] lotNu = new String[]{"Thị", "Ngọc", "Nguyễn", "Hoàng", "Lê", "Trần", "Thanh", "Bảo", "Phương", "Huỳnh", "Gia", "Minh", "Kim", "Quỳnh", "Phạm", "Khánh", "Hồng", "Mỹ", "Hà", "Vũ", "Võ", "Mai", "Thùy", "Anh", "Như", "Thảo", "Thụy", "Phan", "Yến", "Đặng", "Xuân", "Hồ", "Thiên", "Đỗ", "Nhật", "Thái", "Tường", "Tuyết", "Nhã", "Thúy", "Dương", "Hải", "Thu", "Lâm", "Trúc", "Trương", "Hoài", "Đoàn", "Ngô", "Tú", "Cao", "Kiều", "Ánh", "Phúc", "Bích", "Châu", "Bùi", "Khả", "Vân", "Đình", "Tâm", "Thục", "Bội", "Ái", "Lý", "Hương", "Nguyên", "Uyên", "Thủy", "Trịnh", "Cẩm", "Đào", "Diệp", "Tuệ", "Diệu", "Huệ", "Diễm", "Lan", "Cát", "Huyền", "An", "Linh", "Lưu", "Quế", "Ngân", "Đinh", "Uyển", "Triệu", "Trà", "Song", "Bình", "Nguyệt", "Trang", "Mẫn", "Kỳ", "Trâm", "Hạnh", "Lương", "Vương"};
        String[] ho = new String[]{"Nguyễn", "Trần", "Lê", "Phạm", "Huỳnh", "Võ", "Phan", "Trương", "Bùi", "Đặng", "Đỗ", "Ngô", "Hồ", "Dương", "Đinh", "Đoàn", "Lâm", "Mai", "Trịnh", "Đào", "Cao", "Lý", "Hà", "Lưu", "Lương", "Thái", "Châu", "Tạ", "Phùng", "Tô", "Vương", "Văn", "Tăng", "Quách", "Lại", "Hứa", "Thạch", "Diệp", "Từ", "Chu", "La", "Đàm", "Tống", "Giang", "Chung", "Triệu", "Kiều", "Hồng", "Trang", "Đồng", "Danh", "Lư", "Lữ", "Thân", "Kim", "Mã", "Bạch", "Liêu", "Tiêu", "Dư", "Bành", "Âu", "Tôn", "Khưu", "Sơn", "Tất", "Nghiêm", "Lục", "Quan", "Phương", "Mạc", "Lai", "Vòng", "Mạch", "Thiều", "Trà", "Đậu", "Nhan", "Lã", "Trình", "Ninh", "Vi", "Biện", "Hàng", "Ôn", "Chế", "Nhâm", "Tôn Nữ", "Thi", "Doãn", "Khổng", "Phù", "Đường", "Ông", "Tôn Thất", "Ngụy", "Viên", "Tào", "Cù"};
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
        List<User> temp = searchByName("Thành");
        for (User x : temp) {
            System.out.println(x.getName());
        }
    }
}
