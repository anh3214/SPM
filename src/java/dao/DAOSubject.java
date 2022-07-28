/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author buitr
 */
public class DAOSubject extends DataConnection {

    Connection conn = getConnection();

    public Vector<Subject> viewAll() {
        Vector<Subject> vector = new Vector<>();
        String sql = "SELECT * FROM subject;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int subject_id = rs.getInt(1);
                String subject_code = rs.getString(2);
                String subject_name = rs.getString(3);
                int author_id = rs.getInt(4);
                int status = rs.getInt(5);
                vector.add(new Subject(subject_id, subject_code, subject_name, author_id, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Subject> getSubject(int u_id, int role_id, int pageIndex, int pageSize, int status, int author_id, int trainer_id, String subject_search, String filter_col, String filter_type) {
        Vector<Subject> vec = new Vector<>();

        String condition = " where 1 = 1 ";
        if (status != -1) {
            condition += " and s.status = " + status;
        }
        if (subject_search != null) {
            condition += " and s.subject_code like '%" + subject_search + "%'";
        }

        if (author_id != -1) {
            condition += " and s.author_id =" + author_id;
        }
        if (trainer_id != -1) {
            condition += " and c.trainer_id =" + trainer_id;
        }

        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }

        String sql = "SELECT distinct  s.*, u.user_id, u.roll_number,u.full_name,u.gender, u.date_of_birth,u.email,u.mobile,u.facebook_link, c.class_code\n"
                + "FROM subject s\n"
                + "inner join user u on u.user_id = s.author_id \n"
                + "inner join class c on c.subject_id = s.subject_id\n"
                + "inner join class_user cu on cu.class_id = c.class_id "
                + condition
                + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int subject_id = rs.getInt("subject_id");
                String subject_code = rs.getString("subject_code");
                String subject_name = rs.getString("subject_name");
                int _author_id = rs.getInt("author_id");
                int _status = rs.getInt("status");
                int user_id = rs.getInt("user_id");
                String roll_number = rs.getString("roll_number");
                String full_name = rs.getString("full_name");
                String gender = rs.getString("gender");
                String date_of_birth = rs.getString("date_of_birth");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String facebook_link = rs.getString("facebook_link");
                String class_code = rs.getString("class_code");

                vec.add(new Subject(subject_id, subject_code, subject_name, _author_id, _status, user_id, roll_number, full_name, gender, date_of_birth, email, mobile, facebook_link, class_code));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countSubject(int u_id, int role_id, int status, int author_id, int trainer_id, String subject_search) {

        String condition = " where 1 = 1 ";
        if (status != -1) {
            condition += " and s.status = " + status;
        }
        if (subject_search != null) {
            condition += " and s.subject_code like '%" + subject_search + "%'";
        }

        if (author_id != -1) {
            condition += " and s.author_id =" + author_id;
        }
        if (trainer_id != -1) {
            condition += " and c.trainer_id =" + trainer_id;
        }
        String sql = "SELECT distinct  s.*, u.user_id, u.roll_number,u.full_name,u.gender, u.date_of_birth,u.email,u.mobile,u.facebook_link, c.class_code\n"
                + "FROM subject s\n"
                + "inner join user u on u.user_id = s.author_id \n"
                + "inner join class c on c.subject_id = s.subject_id\n"
                + "inner join class_user cu on cu.class_id = c.class_id"
                + condition;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Vector<Subject> getAuthor() {
        Vector<Subject> vector = new Vector<>();

        String sql = "select distinct s.author_id, u.full_name from subject s inner join user u where s.author_id = u.user_id";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("author_id");
                String name = rs.getString("full_name");
                vector.add(new Subject(id, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Subject> getAuthor_() {
        Vector<Subject> vector = new Vector<>();

        String sql = "SELECT user_id, full_name FROM user where role_id = 2";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                vector.add(new Subject(id, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Subject> getTrainer() {
        Vector<Subject> vector = new Vector<>();

        String sql = "select distinct c.trainer_id, u.full_name  from class c\n"
                + "inner join user u where c.trainer_id = u.user_id ";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int trainer_id = rs.getInt("trainer_id");
                String full_name = rs.getString("full_name");
                vector.add(new Subject(trainer_id, full_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int addSubject(Subject f) {
        int n = 0;
        String sql = "INSERT INTO `subject`( `subject_code`,`subject_name`,`author_id`,`status`) VALUES(?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, f.getSubject_code());
            pre.setString(2, f.getSubject_name());
            pre.setInt(3, f.getAuthor_id());
            pre.setInt(4, f.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Subject listAllByID(int subject_id, int u_id) {
        Vector<Subject> vec = new Vector<>();

        String sql = "SELECT distinct  s.*,  u.user_id, u.roll_number,u.full_name,u.gender, u.date_of_birth,u.email,u.mobile,u.facebook_link, c.class_code\n"
                + "FROM subject s\n"
                + "inner join user u on u.user_id = s.author_id \n"
                + "inner join class c on c.subject_id = s.subject_id\n"
                + "inner join class_user cu on cu.class_id = c.class_id \n"
                + "where s.subject_id = " + subject_id;
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                String subject_code = rs.getString("subject_code");
                String subject_name = rs.getString("subject_name");
                int author_id = rs.getInt("author_id");
                int _status = rs.getInt("status");
                int user_id = rs.getInt("user_id");
                String roll_number = rs.getString("roll_number");
                String full_name = rs.getString("full_name");
                String gender = rs.getString("gender");
                String date_of_birth = rs.getString("date_of_birth");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String facebook_link = rs.getString("facebook_link");
                String class_code = rs.getString("class_code");

                return new Subject(subject_id, subject_code, subject_name, author_id, _status, user_id, roll_number, full_name, gender, date_of_birth, email, mobile, facebook_link, class_code);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int updateSubject(int subject_id, int status) {
        int n = 0;
        try {
            String sql = "UPDATE `subject`\n"
                    + "SET\n"
                    + "`status` = " + status + "\n"
                    + "WHERE `subject_id` = " + subject_id + ";";
            PreparedStatement ps = conn.prepareStatement(sql);

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public int updateSubject(Subject f) {
        int n = 0;
        try {
            String sql = "UPDATE `Subject`\n"
                    + "SET\n"
                    + "`subject_code` = ?,\n"
                    + "`subject_name` = ?,\n"
                    + "`author_id` = ?,\n"
                    + "`status` = ?\n"
                    + "WHERE `subject_id` = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, f.getSubject_code());
            ps.setString(2, f.getSubject_name());
            ps.setInt(3, f.getAuthor_id());
            ps.setInt(4, f.getStatus());
            ps.setInt(5, f.getSubject_id());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public static void main(String[] args) {
        DAOSubject dao = new DAOSubject();
        for (int i = 0; i < dao.getAuthor().size(); i++) {
            System.out.print(dao.getAuthor().get(i).getId());
        }

    }
}
