/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Class;
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
 * @author Luu Dat
 */
public class DAOTeam extends DataConnection {

    Connection conn = getConnection();

    public Vector<Class> queryClassTeam(int trainer_id) {
        Vector<Class> vec = new Vector<>();
        String sql = "select distinct class.class_id, class.class_code "
                + "from class "
                + "inner join team on class.class_id = team.class_id "
                + "where class.trainer_id = " + trainer_id;
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                entity.Class cla = new entity.Class();
                cla.setClass_id(rs.getInt("class_id"));
                cla.setClass_code(rs.getString("class_code"));
                vec.add(cla);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int updateTeamStatus(int team_id, int status) {
        int n = 0;
        String sql = "update team set status = ? where team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, team_id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Team> queryTeam(int pageIndex, int pageSize, int class_id, int status, String team_name, String topic_code, String class_code, String filter_col, String filter_type) {
        Vector<Team> vec = new Vector<>();
        String sql = "select team.*, class.class_code\n"
                + " from team \n"
                + " inner join class on class.class_id = team.class_id where team.class_id =  " + class_id;

        if (topic_code != null) {
            sql += " and team.topic_code like '%" + topic_code + "%'";
        }
        if (status != -1) {
            sql += " and team.status = " + status;
        }
        if (class_code != null) {
            sql += " and class.class_code like '%" + class_code + "%'";
        }
        if (team_name != null) {
            sql += " and team.team_name like '%" + team_name + "%'";
        }
        sql += " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Team te = new Team();
                te.setTeam_id(rs.getInt("team_id"));
                te.setClass_id(rs.getInt("class_id"));
                te.setTeam_name(rs.getString("team_name"));
                te.setTopic_code(rs.getString("topic_code"));
                te.setTopic_name(rs.getString("topic_name"));
                te.setGitlab_url(rs.getString("gitlab_url"));
                te.setStatus(rs.getInt("status"));
                te.setClass_code(rs.getString("class_code"));
                vec.add(te);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vec;
    }

    public int countTeam(int class_id, int status, String team_name, String topic_code, String class_code) {

        String sql = "select count(*)\n"
                + " from team \n"
                + " inner join class on class.class_id = team.class_id where team.class_id =  " + class_id;

        if (topic_code != null) {
            sql += " and team.topic_code like '%" + topic_code + "%'";
        }
        if (status != -1) {
            sql += " and team.status = " + status;
        }
        if (class_code != null) {
            sql += " and class.class_code like '%" + class_code + "%'";
        }
        if (team_name != null) {
            sql += " and team.team_name like '%" + team_name + "%'";
        }
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getProject_id(int team_id) {
        String sql = "SELECT project_id FROM team where team_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, team_id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getTeam_tokent(int team_id) {
        String sql = "SELECT team_token FROM team where team_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, team_id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Trang
    ////////////////////////////////////////////////////////////
    public Vector<String> listClassId(int u_id) {
        Vector<String> vector = new Vector<>();
        String sql = "select distinct t.class_id from team t, class c where t.class_id = c.class_id and trainer_id = " + u_id;
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

    public int addTeam(Team t) {
        int n = 0;
        String sql = "INSERT INTO `Team`( `class_id`,`topic_code`,`topic_name`,`gitlab_url`,`status`, `project_id`, `team_token`, `team_name`) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, t.getClass_id());
            pre.setString(2, t.getTopic_code());
            pre.setString(3, t.getTopic_name());
            pre.setString(4, t.getGitlab_url());
            pre.setInt(5, t.getStatus());
            pre.setString(6, t.getProject_id());
            pre.setString(7, t.getTeam_token());
            pre.setString(8, t.getTeam_name());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int updateTeam(Team t) {
        int n = 0;
        try {
            String sql = "UPDATE `Team`\n"
                    + "SET\n"
                    + "`class_id` = ?,\n"
                    + "`topic_code` = ?,\n"
                    + "`topic_name` = ?,\n"
                    + "`gitlab_url` = ?,\n"
                    + "`status` = ?,\n"
                    + "`project_id` = ?,\n"
                    + "`team_token` = ?,\n"
                    + "`team_name` = ?\n"
                    + "WHERE `team_id` = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getClass_id());
            ps.setString(2, t.getTopic_code());
            ps.setString(3, t.getTopic_name());
            ps.setString(4, t.getGitlab_url());
            ps.setInt(5, t.getStatus());
            ps.setString(6, t.getProject_id());
            ps.setString(7, t.getTeam_token());
            ps.setString(8, t.getTeam_name());
            ps.setInt(9, t.getTeam_id());
            System.out.println(ps.toString());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public Team listAllByID(int team_id, int u_id) {
        Vector<Team> vec = new Vector<>();
        String sql = "select t.*, c.class_code, c.class_year, c.block5_class "
                + "from team t, class c "
                + "where t.class_id = c.class_id "
                + "and trainer_id = " + u_id + " and t.team_id = " + team_id;

        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                Team team = new Team();
                team.setTeam_id(rs.getInt("team_id"));
                team.setClass_id(rs.getInt("class_id"));
                team.setTopic_code(rs.getString("topic_code"));
                team.setTopic_name(rs.getString("topic_name"));
                team.setGitlab_url(rs.getString("gitlab_url"));
                team.setClass_code(rs.getString("class_code"));
                team.setClass_year(rs.getInt("class_year"));
                team.setBlock5_class(rs.getInt("block5_class"));
                team.setStatus(rs.getInt("status"));
                team.setProject_id(rs.getString("project_id"));
                team.setTeam_name(rs.getString("team_name"));
                team.setTeam_token(rs.getString("team_token"));
                return team;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
