/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Class;
import entity.Subject;
import entity.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ptuan
 */
public class DAOClass extends DataConnection {

    Connection conn = getConnection();

    public Vector<Class> queryClass(int pageIndex, int pageSize, String filter_colString, String filer_typeString, int user_id, int role_id, int trainer_id, int status1, String content) {
        Vector<Class> vector = new Vector();
        String filter_sort_condition = "";
        if (filter_colString != null) {
            filter_sort_condition += " order by " + filter_colString + " " + filer_typeString;
        }
        String filter_condition = "where 1 = 1";
        if (role_id == 3) {
            filter_condition += " and c.trainer_id = " + user_id;
        }
        if (trainer_id != -1) {
            filter_condition += " and c.trainer_id = " + trainer_id;
        }
        if (status1 != -1) {
            filter_condition += " and c.status = " + status1;
        }
        String search = "";
        if (content != null) {
            search = " and c.class_code like '%" + content + "%'";
        }
        String sql = "SELECT * FROM class c inner join subject s on c.subject_id = s.subject_id " + filter_condition + " " + search + " " + filter_sort_condition + " limit " + pageIndex + "," + pageSize;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("class_id");
                String code = rs.getString("class_code");
                int trainer = rs.getInt("trainer_id");
                int subject = rs.getInt("subject_id");
                int year = rs.getInt("class_year");
                String tern = rs.getString("class_term");
                int block = rs.getInt("block5_class");
                int status = rs.getInt("status");
                String gitlabId = rs.getString("gitlab_id");
                vector.add(new Class(id, code, trainer, subject, year, tern, block, status, gitlabId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Integer> viewAllTrainer() {
        Vector<Integer> vector = new Vector<Integer>();
        String sql = "SELECT distinct trainer_id FROM class";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                int trainer = rs.getInt(1);

                vector.add(trainer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int addClass(Class c) {
        int n = 0;
        String sql = "INSERT INTO `class`\n"
                + "(`class_code`,`trainer_id`,`subject_id`,`class_year`,`class_term`,`gitlab_id`,`block5_class`,`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, c.getClass_code());
            pre.setInt(2, c.getTrainer_id());
            pre.setInt(3, c.getSubject_id());
            pre.setInt(4, c.getClass_year());
            pre.setString(5, c.getClass_term());
            pre.setString(6,c.getGitlabID());
            pre.setInt(7, c.getBlock5_class());
            pre.setInt(8, c.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteClass(int id) {
        int n = 0;
        String sql = "";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int queryCountClass(int user_id, int role_id, int status, int trainer_id, String content) {
        int n = 0;
        String filter_condition = "where 1=1";
        if (role_id == 3) {
            filter_condition += " and c.trainer_id = " + user_id;
        }
        if (status != -1) {
            filter_condition += " and c.status = " + status;
        }
        if (trainer_id != -1) {
            filter_condition += " and c.trainer_id = " + trainer_id;
        }
        String search = "";
        if (content != null) {
            search = " and c.class_code like '%" + content + "%'";
        }
        String sql = "Select count(*) from class c inner join subject s on c.subject_id = s.subject_id " + filter_condition + " " + search;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Class getClassById(int id) {
        String sql = "Select * from class where class_id = " + id;
        Class c = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int id2 = rs.getInt("class_id");
                String code = rs.getString("class_code");
                int trainer = rs.getInt("trainer_id");
                int subject = rs.getInt("subject_id");
                int year = rs.getInt("class_year");
                String tern = rs.getString("class_term");
                int block = rs.getInt("block5_class");
                int status = rs.getInt("status");
                String gitlabId = rs.getString("gitlab_id");
                c = new Class(id2, code, trainer, subject, year, tern, block, status, gitlabId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int updateClass(Class c) {
        int n = 0;
        String sql = "UPDATE `class`\n"
                + "SET\n"
                + "`class_code` = ?,`trainer_id` = ?,`subject_id` = ?,`class_year` = ?,`class_term` = ?,`gitlab_id`=?,`block5_class` = ?,`status` = ?\n"
                + "WHERE `class_id` = ?;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, c.getClass_code());
            pre.setInt(2, c.getTrainer_id());
            pre.setInt(3, c.getSubject_id());
            pre.setInt(4, c.getClass_year());
            pre.setString(5, c.getClass_term());
            pre.setString(6,c.getGitlabID());
            pre.setInt(7, c.getBlock5_class());
            pre.setInt(8, c.getStatus());
            pre.setInt(9, c.getClass_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Class> viewallClass() {
        Vector<Class> vector = new Vector();

        String sql = "SELECT * FROM class";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("class_id");
                String code = rs.getString("class_code");
                int trainer = rs.getInt("trainer_id");
                int subject = rs.getInt("subject_id");
                int year = rs.getInt("class_year");
                String tern = rs.getString("class_term");
                int block = rs.getInt("block5_class");
                int status = rs.getInt("status");
                String gitlabId = rs.getString("gitlab_id");
                vector.add(new Class(id, code, trainer, subject, year, tern, block, status, gitlabId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Class> queryClassSideBar(int u_id, int role) {
        Vector<Class> vec = new Vector<>();
        String sql = "select class.* from class ";
        if (role == 3) {
            sql += " where trainer_id = " + u_id + " and class.status = 1 ";
        }
        if (role == 2) {
            sql = sql + " inner join subject on subject.subject_id = class.subject_id"
                    + " where subject.author_id = " + u_id + " and class.status = 1 ";
        }
        if (role == 1) {
            sql += " where class.status = 1 ";
        }
        if (role == 4) {
            sql += " inner join class_user on class.class_id = class_user.class_id\n"
                    + " where class.status = 1 and class_user.user_id = " + u_id;
        }
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Class cla = new Class();
                cla.setClass_id(rs.getInt("class_id"));
                cla.setClass_code(rs.getString("class_code"));
                vec.add(cla);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Team> queryTeamByClass(int class_id, int u_id, int role) {
        Vector<Team> vec = new Vector<>();
        String sql = "select team.* from team where status = 1 and class_id = " + class_id;

        if (role == 4) {
            sql = "select team.* from team\n"
                    + "inner join class_user on class_user.team_id = team.team_id\n"
                    + "where team.status = 1 and class_user.user_id = " + u_id + " and team.class_id = " + class_id;
        }

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Team team = new Team();
                team.setTeam_id(rs.getInt("team_id"));
                team.setTeam_name(rs.getString("team_name"));
                vec.add(team);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Subject> querySubject(int u_id, int role) {
        Vector<Subject> vec = new Vector<>();
        String sql = "select subject.* from subject where status = 1";

        if (role == 2) {
            sql += " and author_id = " + u_id;
        }

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Subject temp = new Subject();
                temp.setSubject_id(rs.getInt("subject_id"));
                temp.setSubject_code(rs.getString("subject_code"));
                vec.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
}
