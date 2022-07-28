package dao;

import entity.User;
import utils.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Vector;

public class DAOUser extends dao.DataConnection {

    public int addUser(User user) {
        int n = 0;
        String sql = "INSERT INTO `user`\n"
                + "(`roll_number`,\n"
                + "`full_name`,\n"
                + "`gender`,\n"
                + "`date_of_birth`,\n"
                + "`email`,\n"
                + "`mobile`,\n"
                + "`avatar_link`,\n"
                + "`facebook_link`,\n"
                + "`role_id`,\n"
                + "`status`,\n"
                + "`password`)\n"
                + "VALUES"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, user.getRoll_number());
            pre.setString(2, user.getFull_name());
            pre.setString(3, user.getGender());
            pre.setString(4, user.getDate_of_birth());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getMobile());
            pre.setString(7, user.getAvatar_link());
            pre.setString(8, user.getFacebook_link());
            pre.setInt(9, user.getRole_id());
            pre.setInt(10, user.getStatus());
            pre.setString(11, user.getPassword());

            //run sql            
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public void updatepassword(String email, String password) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "`password` = ?\n"
                    + "WHERE `email` = ?";
            int result = 0;
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean getUserEmail(String email) {
        try {
            String sql = "select * from user where email = ? ";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getUseridbyemail(String email) {
        String sql = "SELECT * FROM user where email = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public void updatepassword(int user_id, String pass_word) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "`password` = ?\n"
                    + "WHERE `user_id` = ?";
            int result = 0;
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, pass_word);
            ps.setInt(2, user_id);
            result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static User loginCheck(String email, String password) {
        String sql = "select * from user where email = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {
                    User e = new User();
                    e.setUser_id(rs.getInt("user_id"));
                    e.setRoll_number(rs.getString("roll_number"));
                    e.setFull_name(rs.getString("full_name"));
                    e.setGender(rs.getString("gender"));
                    e.setDate_of_birth(rs.getString("date_of_birth"));
                    e.setEmail(rs.getString("email"));
                    e.setMobile(rs.getString("mobile"));
                    e.setAvatar_link(rs.getString("avatar_link"));
                    e.setFacebook_link(rs.getString("facebook_link"));
                    e.setRole_id(rs.getInt("role_id"));
                    e.setStatus(rs.getInt("status"));
                    e.setToken_user(rs.getString("token_user"));
                    return e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateProfile(User up, String token) {
        String sql = "update user set full_name = ?, facebook_link = ?, email = ?, mobile = ?, date_of_birth = ?, gender = ?, token_user = ? where user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, up.getFull_name());
            pre.setString(2, up.getFacebook_link());
            pre.setString(3, up.getEmail());
            pre.setString(4, up.getMobile());
            pre.setString(5, up.getDate_of_birth());
            pre.setString(6, up.getGender());
            pre.setString(7, token);
            pre.setInt(8, up.getUser_id());
            return pre.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAvatarLink(int uid, String avt_link) {
        String sql = "update user set avatar_link = ? where user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, avt_link);
            pre.setInt(2, uid);
            return pre.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateStatus(int uid, int status) {
        String sql = "update user set status = ? where user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, uid);
            return pre.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUserById(User up) {
        String sql = "update user set full_name = ?, facebook_link = ?, email = ?, mobile = ?, date_of_birth = ?, gender = ?, role_id = ?, status = ? where user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, up.getFull_name());
            pre.setString(2, up.getFacebook_link());
            pre.setString(3, up.getEmail());
            pre.setString(4, up.getMobile());
            pre.setString(5, up.getDate_of_birth());
            pre.setString(6, up.getGender());
            pre.setInt(7, up.getRole_id());
            pre.setInt(8, up.getStatus());
            pre.setInt(9, up.getUser_id());
            return pre.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUserById(int uid) {
        String sql = "select * from user where user_id = ?";

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User e = new User();
                e.setUser_id(rs.getInt("user_id"));
                e.setRoll_number(rs.getString("roll_number"));
                e.setFull_name(rs.getString("full_name"));
                e.setGender(rs.getString("gender"));
                e.setDate_of_birth(rs.getString("date_of_birth"));
                e.setEmail(rs.getString("email"));
                e.setMobile(rs.getString("mobile"));
                e.setAvatar_link(rs.getString("avatar_link"));
                e.setFacebook_link(rs.getString("facebook_link"));
                e.setRole_id(rs.getInt("role_id"));
                e.setStatus(rs.getInt("status"));

                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<User> queryUsers(int start, int total, int role, int status, String name, String email) {
        ArrayList<User> dummies = new ArrayList<User>();
        String condition = "1 = 1"; // search all
        if (role != -1) {
            condition += " and role_id = ?";
        }
        if (status != -1) {
            condition += " and status = ?";
        }
        if (name != null) {
            condition += " and lower(full_name) like ?";
        }
        if (email != null) {
            condition += " and lower(email) like ?";
        }
        String sql = "select * from user where " + condition + " limit " + (start - 1) + ", " + total;

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            int _curIndex = 1;
            if (condition.contains("role_id")) {
                pre.setInt(_curIndex, role);
                _curIndex += 1;
            }
            if (condition.contains("status")) {
                pre.setInt(_curIndex, status);
                _curIndex += 1;
            }
            if (condition.contains("full_name")) {
                pre.setString(_curIndex, "%" + name.toLowerCase() + "%");
                _curIndex += 1;
            }
            if (condition.contains("email")) {
                pre.setString(_curIndex, "%" + email.toLowerCase() + "%");
                _curIndex += 1;
            }

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                User e = new User();
                e.setUser_id(rs.getInt("user_id"));
                e.setRoll_number(rs.getString("roll_number"));
                e.setFull_name(rs.getString("full_name"));
                e.setGender(rs.getString("gender"));
                e.setDate_of_birth(rs.getString("date_of_birth"));
                e.setEmail(rs.getString("email"));
                e.setMobile(rs.getString("mobile"));
                e.setAvatar_link(rs.getString("avatar_link"));
                e.setFacebook_link(rs.getString("facebook_link"));
                e.setRole_id(rs.getInt("role_id"));
                e.setStatus(rs.getInt("status"));

                dummies.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dummies;
    }

    public static int queryUsersCount(int role, int status, String name, String email) {
        String condition = "1 = 1"; // search all
        if (role != -1) {
            condition += " and role_id = ?";
        }
        if (status != -1) {
            condition += " and status = ?";
        }
        if (name != null) {
            condition += " and lower(full_name) like ?";
        }
        if (email != null) {
            condition += " and lower(email) like ?";
        }
        String sql = "select count(*) from user where " + condition;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Vector<User> viewAllUsers() {
        Vector<User> vector = new Vector();
        String sql = "select * from user";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                User e = new User();
                e.setUser_id(rs.getInt("user_id"));
                e.setRoll_number(rs.getString("roll_number"));
                e.setFull_name(rs.getString("full_name"));
                e.setGender(rs.getString("gender"));
                e.setDate_of_birth(rs.getString("date_of_birth"));
                e.setEmail(rs.getString("email"));
                e.setMobile(rs.getString("mobile"));
                e.setAvatar_link(rs.getString("avatar_link"));
                e.setFacebook_link(rs.getString("facebook_link"));
                e.setRole_id(rs.getInt("role_id"));
                e.setStatus(rs.getInt("status"));
                vector.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public String getToken(int id){
        String sql = "select token_user from user where user_id =?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        ArrayList<User> cc = DAOUser.queryUsers(1, 10, 1, -1, "long", null);
//        for (User i : cc) {
//            System.out.println(i);
//        }
//        System.out.println(DAOUser.getUserById(1));
//    }
}
