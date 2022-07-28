package dao;

import static dao.DataConnection.getConnection;
import entity.ClassUser;
import entity.Class;
import entity.Team;
import entity.User;
import utils.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class DAOClassUser extends dao.DataConnection {

    public static ArrayList<ClassUser> queryClassUsers(int uid, int roleId, int start, int total, int class_id, int team_id, int status, String name) {
        ArrayList<ClassUser> dummies = new ArrayList<ClassUser>();
        String condition = "c.status = 1 and t.status = 1"; // search all
        if (roleId == 4) condition += " and cu.class_id in (select distinct class_id from class_user where user_id = " + uid + ")";
        if (roleId == 3) condition += " and cu.class_id in (select distinct class_id from class where trainer_id = " + uid + ")";
        if (roleId == 2) condition += " and cu.class_id in (select distinct class_id from class c inner join subject s on c.subject_id = s.subject_id where s.author_id = " + uid + ")";
        
        if (team_id != -1) {
            condition += " and cu.team_id = ?";
        }
        if (status != -1) {
            condition += " and cu.status = ?";
        }
        if (name != null) {
            condition += " and (lower(u.full_name) like ? or lower(u.email) like ?)";
        }

        String sql = "SELECT t.team_name, c.class_id, cu.team_id, u.user_id, c.class_code, cu.team_leader, cu.dropout_date, u.roll_number, u.full_name, u.gender, u.date_of_birth, u.email, cu.user_notes, cu.ongoing_eval, cu.final_pres_eval, cu.final_topic_eval, cu.status, u.avatar_link FROM \n"
                + "	class_user cu \n"
                + "    inner join user u on cu.user_id = u.user_id \n"
                + "    inner join team t on cu.team_id = t.team_id\n"
                + "    inner join class c on cu.class_id = c.class_id\n"
                + "    where c.class_id = ? and " + condition + " limit " + (start - 1) + ", " + total;;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);;
            pre.setInt(1, class_id);
            int _curIndex = 2;
            if (condition.contains("and cu.team_id")) {
                pre.setInt(_curIndex, team_id);
                _curIndex += 1;
            }
            if (condition.contains("and cu.status")) {
                pre.setInt(_curIndex, status);
                _curIndex += 1;
            }
            if (condition.contains("lower(u.full_name)")) {
                pre.setString(_curIndex, "%" + name.toLowerCase() + "%");
                _curIndex += 1;
                pre.setString(_curIndex, "%" + name.toLowerCase() + "%");
                _curIndex += 1;
            }

            //System.out.println(pre.toString());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                ClassUser e = new ClassUser();
                e.setClassId(rs.getInt("class_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setUserId(rs.getInt("user_id"));
                e.setTeamLeader(rs.getInt("team_leader"));
                e.setDropOutDate(rs.getDate("dropout_date"));
                e.setUserNotes(rs.getString("user_notes"));
                e.setOnGoingEval(rs.getString("ongoing_eval"));
                e.setFinalPresEval(rs.getString("final_pres_eval"));
                e.setFinalTopicEval(rs.getString("final_topic_eval"));
                e.setStatus(rs.getInt("status"));

                e.setClassName(rs.getString("class_code"));
                e.setRollNumber(rs.getString("roll_number"));
                e.setFullName(rs.getString("full_name"));
                e.setGender(rs.getString("gender"));
                e.setDateOfBirth(rs.getString("date_of_birth"));
                e.setEmail(rs.getString("email"));
                e.setTeamName(rs.getString("team_name"));

                e.setAvatarLink(rs.getString("avatar_link"));

                dummies.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dummies;
    }

    public static ClassUser getClassUser(int class_id, int team_id, int user_id) {
        String sql = "SELECT c.class_id, cu.team_id, u.user_id, c.class_code, cu.team_leader, cu.dropout_date, u.roll_number, u.full_name, u.gender, u.date_of_birth, u.email, cu.user_notes, cu.ongoing_eval, cu.final_pres_eval, cu.final_topic_eval, cu.status, u.avatar_link FROM \n"
                + "	class_user cu \n"
                + "    inner join user u on cu.user_id = u.user_id \n"
                + "    inner join team t on cu.team_id = t.team_id\n"
                + "    inner join class c on cu.class_id = c.class_id\n"
                + "    where u.user_id = ? and cu.class_id = ? and cu.team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, user_id);
            pre.setInt(2, class_id);
            pre.setInt(3, team_id);
            //System.out.println(pre.toString());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                ClassUser e = new ClassUser();
                e.setClassId(rs.getInt("class_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setUserId(rs.getInt("user_id"));
                e.setTeamLeader(rs.getInt("team_leader"));
                e.setDropOutDate(rs.getDate("dropout_date"));
                e.setUserNotes(rs.getString("user_notes"));
                e.setOnGoingEval(rs.getString("ongoing_eval"));
                e.setFinalPresEval(rs.getString("final_pres_eval"));
                e.setFinalTopicEval(rs.getString("final_topic_eval"));
                e.setStatus(rs.getInt("status"));

                e.setClassName(rs.getString("class_code"));
                e.setRollNumber(rs.getString("roll_number"));
                e.setFullName(rs.getString("full_name"));
                e.setGender(rs.getString("gender"));
                e.setDateOfBirth(rs.getString("date_of_birth"));
                e.setEmail(rs.getString("email"));

                e.setAvatarLink(rs.getString("avatar_link"));

                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class getClassById(int class_id) {
        String sql = "select * from class where class_id = ? and status = 1";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, class_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Class e = new Class();
                e.setClass_id(rs.getInt("class_id"));
                e.setClass_code(rs.getString("class_code"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Team> queryAllTeamByClassId(int class_id) {
        ArrayList<Team> dummies = new ArrayList<Team>();

        String sql = "select * from team where class_id = ? and status = 1";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, class_id);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Team e = new Team();
                e.setTeam_id(rs.getInt("team_id"));
                e.setClass_id(rs.getInt("class_id"));
                e.setTopic_code(rs.getString("topic_code"));
                e.setTopic_name(rs.getString("topic_name"));
                e.setGitlab_url(rs.getString("gitlab_url"));
                e.setStatus(rs.getInt("status"));
                e.setTeam_name(rs.getString("team_name"));
                dummies.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dummies;
    }

    public static int queryClassUsersCount(int uid, int roleId, int class_id, int team_id, int status, String name) {
        String condition = "c.status = 1 and t.status = 1"; // search all
        if (roleId == 4) condition += " and cu.class_id in (select distinct class_id from class_user where user_id = " + uid + ")";
        if (roleId == 3) condition += " and cu.class_id in (select distinct class_id from class where trainer_id = " + uid + ")";
        if (roleId == 2) condition += " and cu.class_id in (select distinct class_id from class c inner join subject s on c.subject_id = s.subject_id where s.author_id = " + uid + ")";
        if (team_id != -1) {
            condition += " and cu.team_id = ?";
        }
        if (status != -1) {
            condition += " and cu.status = ?";
        }
        if (name != null) {
            condition += " and (lower(u.full_name) like ? or lower(u.email) like ?)";
        }

        String sql = "SELECT count(*) FROM \n"
                + "	class_user cu \n"
                + "    inner join user u on cu.user_id = u.user_id \n"
                + "    inner join team t on cu.team_id = t.team_id\n"
                + "    inner join class c on cu.class_id = c.class_id\n"
                + "    where c.class_id = ? and " + condition;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, class_id);
            int _curIndex = 2;
            if (condition.contains("and cu.team_id")) {
                pre.setInt(_curIndex, team_id);
                _curIndex += 1;
            }
            if (condition.contains("and cu.status")) {
                pre.setInt(_curIndex, status);
                _curIndex += 1;
            }
            if (condition.contains("lower(u.full_name)")) {
                pre.setString(_curIndex, "%" + name.toLowerCase() + "%");
                _curIndex += 1;
                pre.setString(_curIndex, "%" + name.toLowerCase() + "%");
                _curIndex += 1;
            }

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int insertOrUpdateFromList(int class_id, ArrayList<ClassUser> clList) {
        int n = 0;
        // parse data to list
        for (ClassUser cl : clList) {
            System.out.println(cl);
            cl.setUserId(DAOClassUser.getUserByEmail(cl.getEmail()).getUser_id());
            cl.setClassId(class_id);
        }

        // process data
        for (ClassUser cl : clList) {

            if (cl.getUserId() == -1 || !teamExists(cl.getTeamId(), cl.getClassId())) {
                System.out.println("Not exists uid or team!");
                continue;
            }
            // exists - update
            if (cuExists(cl.getUserId(), cl.getClassId())) {
                // update
                n += updateClassUser(cl);
            } else {
                // insert
                n += insertNewClassUser(cl);
            }
        }
        return n;
    }

    public static int updateClassUser(ClassUser cl) {
        int n = 0;
        String sql = "update class_user set ongoing_eval = ?, final_pres_eval = ?, final_topic_eval = ?, user_notes = ?, team_leader = ?, dropout_date = ?, status = ?, team_id = ?"
                + " where user_id = ? and class_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, cl.getOnGoingEval());
            pre.setString(2, cl.getFinalPresEval());
            pre.setString(3, cl.getFinalTopicEval());
            pre.setString(4, cl.getUserNotes());
            pre.setInt(5, cl.getTeamLeader());
            pre.setDate(6, cl.getDropOutDate());
            pre.setInt(7, cl.getStatus());
            pre.setInt(8, cl.getTeamId());
            pre.setInt(9, cl.getUserId());
            pre.setInt(10, cl.getClassId());
//            pre.setInt(10, cl.getTeamId());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static int removeTeamLeader(int class_id, int team_id) {
        int n = 0;
        String sql = "update class_user set team_leader = ? where class_id = ? and team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setInt(2, class_id);
            pre.setInt(3, team_id);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static int setTeamLeader(int class_id, int team_id, int userId) {
        int n = 0;
        String sql = "update class_user set team_leader = ? where class_id = ? and team_id = ? and user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, 1);
            pre.setInt(2, class_id);
            pre.setInt(3, team_id);
            pre.setInt(4, userId);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static int updateClassUser(int class_id, int userId, ClassUser cu) {
        int n = 0;
        String sql = "update class_user set team_id = ?, status= ?, dropout_date = ?, user_notes = ?, ongoing_eval = ?, final_pres_eval = ?, final_topic_eval = ? where class_id = ? and user_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, cu.getTeamId());
            pre.setInt(2, cu.getStatus());
            pre.setDate(3, cu.getDropOutDate());
            pre.setString(4, cu.getUserNotes());
            pre.setString(5, cu.getOnGoingEval());
            pre.setString(6, cu.getFinalPresEval());
            pre.setString(7, cu.getFinalTopicEval());
            pre.setInt(8, class_id);
            pre.setInt(9, userId);
//            pre.setInt(10, userId);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static int insertNewClassUser(ClassUser cl) {
        int n = 0;
        String sql = "insert into class_user (class_id, team_id, user_id, team_leader, dropout_date, user_notes, ongoing_eval, final_pres_eval, final_topic_eval, status)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, cl.getClassId());
            pre.setInt(2, cl.getTeamId());
            pre.setInt(3, cl.getUserId());
            pre.setInt(4, cl.getTeamLeader());
            pre.setDate(5, cl.getDropOutDate());
            pre.setString(6, cl.getUserNotes());
            pre.setString(7, cl.getOnGoingEval());
            pre.setString(8, cl.getFinalPresEval());
            pre.setString(9, cl.getFinalTopicEval());
            pre.setInt(10, cl.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;

    }

    public static int deleteClassUser(int uid, int cid, int tid) {
        int n = 0;
        String sql = "delete from class_user where user_id = ? and class_id = ? and team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);

            pre.setInt(1, uid);
            pre.setInt(2, cid);
            pre.setInt(3, tid);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static User getUserByEmail(String email) {
        String sql = "select * from user where email = ? and role_id = ? and status = 1";

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setInt(2, 4);
            //System.out.println(pre.toString());
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

    public static boolean teamExists(int team_id, int class_id) {
        String sql = "select count(*) from team where team_id = ? and class_id = ? and status = 1";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, team_id);
            pre.setInt(2, class_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean cuExists(int user_id, int class_id) {
        String sql = "select count(*) from class_user where user_id = ? and class_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, user_id);
//            pre.setInt(2, team_id);
            pre.setInt(2, class_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean trainerCanAccess(int trainer_id, int class_id) {
        String sql = "select count(*) from class where class_id = ? and trainer_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, class_id);
            pre.setInt(2, trainer_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateStatus(int uid, int class_id, int team_id, int status) {
        String sql = "update class_user set status = ? where user_id = ? and class_id = ? and team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, uid);
            pre.setInt(3, class_id);
            pre.setInt(4, team_id);
            return pre.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getClassId(int team_id) {
        int n = 0;
        String sql = "select distinct class_id from class_user where team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, team_id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
//        ArrayList<ClassUser> cc = DAOClassUser.queryClassUsers(1, 5, 60, 1, -1, -1, null);
//        for (ClassUser i : cc) {
//            System.out.println(i);
//        }
//        ArrayList<Team> cc1 = DAOClassUser.queryAllTeamByClassId(1);
//        for (Team i : cc1) {
//            System.out.println(i);
//        }
//
//        System.out.println(DAOClassUser.getClassUser(1, 1, 9));
//        
//        DAOClassUser.removeTeamLeader(1, 3);
//        DAOClassUser.setTeamLeader(1, 3, 23);
        int n = DAOClassUser.getClassId(2);
        System.out.println(n);
    }
}
