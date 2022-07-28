/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Function;
import entity.Milestone;
import entity.Tracking;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import entity.Class;
import entity.Team;

/**
 *
 * @author Luu Dat
 */
public class DAOTracking extends DataConnection {

    Connection conn = getConnection();

    public int countTracking(int u_id, int role, int team_id, int pageIndex, int pageSize, int status, String assignee, String note, String function, String milestone) {

        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and i.status = " + status;
        }
        if (note != null) {
            condition += " and t.tracking_note like '%" + note + "%'";
        }
        if (assignee != null) {
            condition += " and u1.full_name like '%" + assignee + "%'";
        }
        if (function != null) {
            condition += " and f.function_name like '" + function + "'";
        }
        if (milestone != null) {
            condition += " and m.milestone_name like '" + milestone + "'";
        }

        String sql = "SELECT count(*) FROM tracking tr\n"
                + "               inner join milestone m on m.milestone_id = tr.milestone_id\n"
                + "               inner join user u1 on u1.user_id = tr.assignee_id\n"
                + "			inner join user u2 on u2.user_id = tr.assigner_id\n"
                + "             inner join team t on t.team_id = tr.team_id\n"
                + "                left join `function` f on f.function_id = tr.function_id\n"
                + "                where tr.team_id = " + team_id
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

    public Vector<Tracking> getTracking(int u_id, int role, int team_id, int pageIndex, int pageSize, int status, String assignee, String note, String function, String milestone, String filter_col, String filter_type) {
        Vector<Tracking> vec = new Vector<>();

        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and i.status = " + status;
        }
        if (note != null) {
            condition += " and t.tracking_note like '%" + note + "%'";
        }
        if (assignee != null) {
            condition += " and u1.full_name like '%" + assignee + "%'";
        }
        if (function != null) {
            condition += " and f.function_name like '" + function + "'";
        }
        if (milestone != null) {
            condition += " and m.milestone_name like '" + milestone + "'";
        }

        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }

        String sql = "SELECT tr.*,  u1.full_name as assignee_name , u2.full_name as assigner_name , m.milestone_name,\n"
                + "                f.function_name,f.access_roles  FROM tracking tr\n"
                + "               inner join milestone m on m.milestone_id = tr.milestone_id\n"
                + "               inner join user u1 on u1.user_id = tr.assignee_id\n"
                + "			inner join user u2 on u2.user_id = tr.assigner_id\n"
                + "             inner join team t on t.team_id = tr.team_id\n"
                + "                left join `function` f on f.function_id = tr.function_id\n"
                + "                where tr.team_id = " + team_id
                + condition
                + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int tracking_id = rs.getInt("tracking_id");
                int _team_id = rs.getInt("team_id");
                int milestone_id = rs.getInt("milestone_id");
                int function_id = rs.getInt("function_id");
                int assigner_id = rs.getInt("assigner_id");
                int assignee_id = rs.getInt("assignee_id");
                String tracking_note = rs.getString("tracking_note");
                int _status = rs.getInt("status");
                String assignee_name = rs.getString("assignee_name");
                String assigner_name = rs.getString("assigner_name");
                String milestone_name = rs.getString("milestone_name");
                String function_name = rs.getString("function_name");
                String access_roles = rs.getString("access_roles");

                vec.add(new Tracking(tracking_id, _team_id, milestone_id, function_id, assigner_id, assignee_id, tracking_note, _status, assignee_name, assigner_name, milestone_name, function_name, access_roles));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<String> getAssignee(int u_id, int role_id, int team_id) {
        Vector<String> vector = new Vector<>();

        String sql = "select distinct  u.full_name from user u \n"
                + "  inner join tracking tr on u.user_id = tr.assignee_id\n"
                + "   inner join team t on tr.team_id = t.team_id"
                + " where t.team_id = " + " " + team_id;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String full_name = rs.getString(1);
                vector.add(String.valueOf(full_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<String> getFunction(int u_id, int role_id, int team_id) {
        Vector<String> vector = new Vector<>();

        String sql = "select distinct f.function_name from `function` f \n"
                + "where f.team_id =  " + " " + team_id;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String function_name = rs.getString(1);
                vector.add(String.valueOf(function_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<String> getMilestone(int u_id, int role_id, int team_id) {
        Vector<String> vector = new Vector<>();

        String sql = "SELECT distinct m.milestone_name FROM milestone m\n"
                + "inner join team t on t.class_id = m.class_id\n"
                + "and t.team_id = " + " " + team_id;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String milestone_name = rs.getString(1);
                vector.add(String.valueOf(milestone_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Tracking getTrackingByID(int u_id, int role, int tracking_id) {
        String sql = "select \n"
                + "                tracking.*, \n"
                + "\n"
                + "                u1.full_name as assignee_name , u2.full_name as assigner_name , milestone.milestone_name,\n"
                + "                f.function_name, f.access_roles\n"
                + "                from team\n"
                + "               inner join tracking on team.team_id = tracking.team_id \n"
                + "               inner join `function` f on f.function_id = tracking.function_id \n"
                + "               inner join milestone on milestone.milestone_id = tracking.milestone_id \n"
                + "               inner join user u1 on u1.user_id = tracking.assignee_id\n"
                + "			inner join user u2 on u2.user_id = tracking.assigner_id\n"
                + "             \n"
                + "              where tracking.tracking_id =  " + tracking_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Tracking tra = new Tracking();
                tra.setTracking_id(rs.getInt("tracking_id"));
                tra.setTeam_id(rs.getInt("team_id"));
                tra.setMilestone_id(rs.getInt("milestone_id"));
                tra.setFunction_id(rs.getInt("function_id"));
                tra.setAssigner_id(rs.getInt("assigner_id"));
                tra.setAssignee_id(rs.getInt("assignee_id"));
                tra.setTracking_note(rs.getString("tracking_note"));
                tra.setStatus(rs.getInt("status"));
                tra.setAssignee_name(rs.getString("assignee_name"));
                tra.setAssigner_name(rs.getString("assigner_name"));
                tra.setMilestone_name(rs.getString("milestone_name"));
                tra.setFunction_name(rs.getString("function_name"));
                tra.setAccess_roles(rs.getString("access_roles"));
                return tra;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<Milestone> getMilestoneByClass(int class_id) {
        Vector<Milestone> vec = new Vector<>();
        String sql = "select milestone.*\n"
                + "from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join team on team.class_id = class.class_id\n"
                + "inner join tracking on tracking.team_id = team.team_id\n"
                + "where tracking.tracking_id = " + class_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Milestone mil = new Milestone();
                mil.setMilestone_id(rs.getInt("milestone_id"));
                mil.setMilestone_name(rs.getString("milestone_name"));

                vec.add(mil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Function> getFunctionByClass(int class_id) {
        Vector<Function> vec = new Vector<>();
        String sql = "select f.* \n"
                + "                from `function` f\n"
                + "              inner join team on team.team_id = f.team_id\n"
                + "               inner join tracking on tracking.team_id = team.team_id\n"
                + "            where tracking.tracking_id =" + class_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Function fun = new Function();
                fun.setId(rs.getInt("function_id"));
                fun.setName(rs.getString("function_name"));

                vec.add(fun);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<User> getAssigneeByClass(int class_id) {
        Vector<User> vec = new Vector<>();
        String sql = "select *\n"
                + "           from tracking\n"
                + "           inner join team on team.team_id = tracking.team_id\n"
                + "                inner join class_user on class_user.team_id = team.team_id\n"
                + "                inner join user on class_user.user_id = user.user_id\n"
                + "                where tracking.tracking_id = " + class_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                User us = new User();
                us.setUser_id(rs.getInt("user_id"));
                us.setFull_name(rs.getString("full_name"));

                vec.add(us);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<User> getAssignerByClass(int class_id) {
        Vector<User> vec = new Vector<>();
        String sql = "select *\n"
                + "           from tracking\n"
                + "           inner join team on team.team_id = tracking.team_id\n"
                + "                inner join class_user on class_user.team_id = team.team_id\n"
                + "                inner join user on class_user.user_id = user.user_id\n"
                + "                where tracking.tracking_id = " + class_id + " and class_user.team_leader =1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                User us = new User();
                us.setUser_id(rs.getInt("user_id"));
                us.setFull_name(rs.getString("full_name"));

                vec.add(us);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int updateTracking(Tracking tra) {
        int n = 0;
        String sql = "update tracking set milestone_id = ?, function_id = ?, assigner_id = ?, \n"
                + "assignee_id = ?, tracking_note = ?, status = ? \n"
                + "where tracking_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);

            pre.setInt(1, tra.getMilestone_id());
            pre.setInt(2, tra.getFunction_id());
            pre.setInt(3, tra.getAssigner_id());
            pre.setInt(4, tra.getAssignee_id());
            pre.setString(5, tra.getTracking_note());
            pre.setInt(6, tra.getStatus());
            pre.setInt(7, tra.getTracking_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
//    public static void main(String[] args) {
//        DAOTracking dao = new DAOTracking();
//        int n;
//        n = dao.updateTracking(new Tracking(1, 1, 2, 2, 9, 16, "sdfhdg", 5));
//        System.out.println(n);
//    }

    public Team getCurTeam(int team_id) {
        String sql = "select team.*, class.class_code from team inner join class on team.class_id = class.class_id"
                + " where team_id = " + team_id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                Team team = new Team();
                team.setTeam_id(rs.getInt("team_id"));
                team.setTeam_name(rs.getString("team_name"));
                team.setClass_code(rs.getString("class_code"));
                return team;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int addTracking(Tracking tra) {
        int n = 0;
        String sql = "INSERT INTO tracking(team_id, milestone_id, function_id, assigner_id, assignee_id, tracking_note, status) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, tra.getTeam_id());
            pre.setInt(2, tra.getMilestone_id());
            pre.setInt(3, tra.getFunction_id());
            pre.setInt(4, tra.getAssigner_id());
            pre.setInt(5, tra.getAssignee_id());
            pre.setString(6, tra.getTracking_note());
            pre.setInt(7, tra.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Milestone> getMilestone(int team_id) {
        Vector<Milestone> vec = new Vector<>();
        String sql = "select *\n"
                + "             from milestone\n"
                + "             inner join class on class.class_id = milestone.class_id\n"
                + "              inner join team on class.class_id = team.class_id\n"
                + "             where team.team_id = " + team_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Milestone mil = new Milestone();
                mil.setMilestone_id(rs.getInt("milestone_id"));
                mil.setMilestone_name(rs.getString("milestone_name"));
                vec.add(mil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Function> getFunction(int team_id) {
        Vector<Function> vec = new Vector<>();
        String sql = "select *\n"
                + "             from `function` f\n"
                + "              inner join team on team.team_id = f.team_id\n"
                + "             where team.team_id =" + team_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Function fun = new Function();
                fun.setId(rs.getInt("function_id"));
                fun.setName(rs.getString("function_name"));
                vec.add(fun);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<User> getAssignee(int team_id) {
        Vector<User> vec = new Vector<>();
        String sql = "select *\n"
                + "             from class_user\n"
                + "              inner join team on team.team_id = class_user.team_id\n"
                + "              inner join user on class_user.user_id = user.user_id\n"
                + "             where team.team_id =" + team_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                User us = new User();
                us.setUser_id(rs.getInt("user_id"));
                us.setFull_name(rs.getString("full_name"));
                vec.add(us);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<User> getAssigner(int team_id) {
        Vector<User> vec = new Vector<>();
        String sql = "select *\n"
                + "             from class_user\n"
                + "              inner join team on team.team_id = class_user.team_id\n"
                + "              inner join user on class_user.user_id = user.user_id\n"
                + "             where team.team_id = " + team_id + " and class_user.team_leader =1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                User us = new User();
                us.setUser_id(rs.getInt("user_id"));
                us.setFull_name(rs.getString("full_name"));
                vec.add(us);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

}
