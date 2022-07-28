/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Feature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author buitr
 */
public class DAOFeature extends DataConnection {

    Connection conn = getConnection();

    public Vector<Feature> listFeature() {
        Vector<Feature> vector = new Vector<>();
        String sql = "SELECT * FROM Feature;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feature_id = rs.getInt(1);
                int team_id = rs.getInt(2);
                String feature_name = rs.getString(3);
                int status = rs.getInt(4);
                String description = rs.getString(5);
                vector.add(new Feature(feature_id, team_id, feature_name, status, description));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }



    public Vector<Feature> getFeatures(int u_id, int role_id,int team_id, int pageIndex, int pageSize, int status, String feature_name, String subject, String filter_col, String filter_type) {
        Vector<Feature> vec = new Vector<>();

        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and feature.status = " + status;
        }
        if (feature_name != null) {
            condition += " and feature.feature_name like '%" + feature_name + "%'";
        }

        if (role_id == 4) {
            condition += " and class_user.user_id = " + u_id;
        }
        if (role_id == 3) {

            condition += " and class.trainer_id = " + u_id;
        }
        if (role_id == 2) {

            condition += " and subject.author_id = " + u_id;
        }

        if (subject != null) {
            condition += " and subject.subject_code like '%" + subject + "%'";
        }
        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }

        String sql = "SELECT user1.user_id,feature.description, feature.feature_id, feature.feature_name, feature.status, \n"
                + "team.topic_code,team.topic_name, class.class_code, user2.full_name as trainer, feature.team_id,\n"
                + "subject.subject_code, subject.subject_name\n"
                + "from class_user\n"
                + "inner join team on team.team_id = class_user.team_id\n"
                + "inner join user user1 on user1.user_id = class_user.user_id\n"
                + "inner join class class on class.class_id = class_user.class_id\n"
                + "inner join subject on subject.subject_id = class.subject_id\n"
                + "inner join user user2 on user2.user_id = class.trainer_id\n"
                + "inner join feature on feature.team_id = team.team_id where feature.team_id = " + team_id
                + condition 
                + " group by feature_id, team_id"
                + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int _feature_id = rs.getInt("feature_id");
                String _feature_name = rs.getString("feature_name");
                int _status = rs.getInt("status");
                String _topic_code = rs.getString("topic_code");
                String _class_code = rs.getString("class_code");
                String _trainer = rs.getString("trainer");
                int _team_id = rs.getInt("team_id");
                String _subject_code = rs.getString("subject_code");
                String _subject_name = rs.getString("subject_name");
                String description = rs.getString("description");
                String _topic_name = rs.getString("topic_name");

                vec.add(new Feature(_feature_id, _team_id, _feature_name, _status, _topic_code, _class_code, _trainer, _subject_code, _subject_name,description,_topic_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countFeatures(int u_id, int role_id,int team_id, int status, String feature_name, String subject) {
        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and feature.status = " + status;
        }
        if (feature_name != null) {
            condition += " and feature.feature_name like '%" + feature_name + "%'";
        }

        if (role_id == 4) {
            condition += " and class_user.user_id = " + u_id;
        }
        if (role_id == 3) {

            condition += " and class.trainer_id = " + u_id;
        }
        if (role_id == 2) {

            condition += " and subject.author_id = " + u_id;
        }

        if (subject != null) {
            condition += " and subject.subject_code like '%" + subject + "%'";
        }
        String sql = "SELECT count(*) from (select distinct feature_id from class_user\n"
                + "inner join team on team.team_id = class_user.team_id\n"
                + "                inner join user user1 on user1.user_id = class_user.user_id\n"
                + "                inner join class class on class.class_id = class_user.class_id\n"
                + "                inner join subject on subject.subject_id = class.subject_id\n"
                + "                inner join user user2 on user2.user_id = class.trainer_id\n"
                + "                inner join feature on feature.team_id = team.team_id  where feature.team_id = " + team_id
                + condition 
                + "                group by team.team_id, feature.feature_id ) as table1";
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



    public Vector<String> getSubject(int u_id, int role_id) {
        Vector<String> vector = new Vector<>();

        String condition = " where 1 = 1 ";

        if (role_id == 4) {
            condition = " inner join class c on c.subject_id = s.subject_id inner join class_user cu on c.class_id = cu.class_id and cu.user_id = " + u_id;
        }
        if (role_id == 3) {
            condition = " , class c where s.subject_id = c.subject_id and trainer_id =" + u_id;
        }
        if (role_id == 2) {
            condition = " where s.author_id = " + u_id;
        }

        String sql = "SELECT distinct s.subject_code from subject s " + " " + condition;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String subject_code = rs.getString(1);
                vector.add(String.valueOf(subject_code));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<String> getClass(int u_id, int role_id) {
        Vector<String> vector = new Vector<>();

        String condition = " where 1 = 1 ";

        if (role_id == 4) {
            condition = " , class_user cu where c.class_id = cu.class_id and cu.user_id =" + u_id;
        }
        if (role_id == 3) {
            condition = " where c.trainer_id = " + u_id;
        }
        if (role_id == 2) {
            condition = ", subject s where c.subject_id = s.subject_id and s.author_id = " + u_id;
        }

        String sql = "select distinct c.class_code from class c " + " " + condition;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String class_code = rs.getString(1);
                vector.add(String.valueOf(class_code));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }


    public Vector<String> listTeamID(int u_id) {
        Vector<String> vector = new Vector<>();
        String sql = "SELECT distinct team_id FROM class_user where user_id = " + u_id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                vector.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }



    public int addFeature(Feature f) {
        int n = 0;
        String sql = "INSERT INTO `Feature`( `team_id`,`feature_name`,`description`,`status`) VALUES(?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, f.getTeam_id());
            pre.setString(2, f.getFeature_name());
            pre.setString(3, f.getDescription());
            pre.setInt(4, f.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Feature listAllByID(int feature_id, int u_id) {
        Vector<Feature> vec = new Vector<>();

        String condition = " where feature.feature_id = " + feature_id ;
        String sql = "SELECT user1.user_id, feature.feature_id, feature.feature_name, feature.status, \n"
                + "team.topic_code,team.topic_name, class.class_code, user2.full_name as trainer, feature.team_id,\n"
                + "subject.subject_code, subject.subject_name, feature.description\n"
                + "from class_user\n"
                + "inner join team on team.team_id = class_user.team_id\n"
                + "inner join user user1 on user1.user_id = class_user.user_id\n"
                + "inner join class class on class.class_id = class_user.class_id\n"
                + "inner join subject on subject.subject_id = class.subject_id\n"
                + "inner join user user2 on user2.user_id = class.trainer_id\n"
                + "inner join feature on feature.team_id = team.team_id"
                + condition;
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                               int _feature_id = rs.getInt("feature_id");
                String _feature_name = rs.getString("feature_name");
                int _status = rs.getInt("status");
                String _topic_code = rs.getString("topic_code");
                String _class_code = rs.getString("class_code");
                String _trainer = rs.getString("trainer");
                int _team_id = rs.getInt("team_id");
                String _subject_code = rs.getString("subject_code");
                String _subject_name = rs.getString("subject_name");
                String description = rs.getString("description");
                String _topic_name = rs.getString("topic_name");

                return new Feature(_feature_id, _team_id, _feature_name, _status, _topic_code, _class_code, _trainer, _subject_code, _subject_name, description,_topic_name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int updateFeature(int feature_id, int status) {
        int n = 0;
        try {
            String sql = "UPDATE `Feature`\n"
                    + "SET\n"
                    + "`status` = " + status + "\n"
                    + "WHERE `feature_id` = " + feature_id + ";";
            PreparedStatement ps = conn.prepareStatement(sql);

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public int updateFeature(Feature f) {
        int n = 0;
        try {
            String sql = "UPDATE `Feature`\n"
                    + "SET\n"
                    + "`team_id` = ?,\n"
                    + "`feature_name` = ?,\n"
                    + "`status` = ?,\n"
                    + "`description` = ?\n"
                    + "WHERE `feature_id` = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getTeam_id());
            ps.setString(2, f.getFeature_name());
            ps.setInt(3, f.getStatus());
            ps.setInt(5, f.getFeature_id());
            ps.setString(4, f.getDescription());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

}
