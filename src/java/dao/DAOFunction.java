/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Class;
import entity.Team;
import entity.Subject;
import entity.Function;
import entity.Feature;
import entity.User;
import entity.ClassSetting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.API;

public class DAOFunction extends dao.DataConnection {

    public static String getBaseFeatureJso(int uid, int roleId) {
        String condition = "1 = 1";

        // only active base
        condition += " and sj.status = 1 and cl.status = 1 and te.status = 1 and fe.status = 1";

        // student
        if (roleId == 4) {
            condition += " and fe.team_id in (select distinct team_id from class_user where user_id = ? and status = 1);";
        }

        // teacher
        if (roleId == 3) {
            condition += " and cl.trainer_id = ?";
        }

        if (roleId < 3) {
            return null;
        }

        String sql = "select cl.trainer_id, fe.feature_id, fe.feature_name, te.team_id, te.team_name, te.class_id, cl.class_code, cl.subject_id, sj.subject_code \n"
                + "	from feature fe\n"
                + "	inner join team te on te.team_id = fe.team_id\n"
                + "    inner join class cl on cl.class_id = te.class_id\n"
                + "    inner join subject sj on sj.subject_id = cl.subject_id\n"
                + "    where "
                + condition;

        try {
            JSONArray jArr = new JSONArray();
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                JSONObject jso = new JSONObject();
                jso.put("feature_id", rs.getInt("feature_id"));
                jso.put("feature_name", rs.getString("feature_name"));
                jso.put("team_id", rs.getInt("team_id"));
                jso.put("team_name", rs.getString("team_name"));
                jso.put("class_id", rs.getInt("class_id"));
                jso.put("class_code", rs.getString("class_code"));
                jso.put("subject_id", rs.getInt("subject_id"));
                jso.put("subject_code", rs.getString("subject_code"));
                jArr.put(jso);
            }
            return jArr.toString();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Function getFunctionById(int uid, int roleId, int funcId) {
        String condition = "func.function_id = " + funcId; // search all

        // only active base
        condition += " and cl.status = 1 and sj.status = 1 and te.status = 1 and fe.status = 1";

        // student
        if (roleId == 4) {
            condition += " and func.team_id in (select distinct team_id from class_user where user_id = ? and status = 1)";
        }

        // teacher
        if (roleId == 3) {
            condition += " and cl.trainer_id = ?";
        }

        if (roleId < 3) {
            return null;
        }

        String sql = "select cs.color, cl.class_id, sj.subject_code, cl.class_code, cl.subject_id, te.class_id, te.team_name, fe.feature_name, usr.full_name as owner_name, cs.type_title as status, func.* from \n"
                + "	`function` func\n"
                + "    inner join team te on te.team_id = func.team_id\n"
                + "    inner join feature fe on fe.feature_id = func.feature_id\n"
                + "    inner join class_user cu on cu.user_id = func.owner_id\n"
                + "		inner join user usr on usr.user_id = cu.user_id\n"
                + "	inner join class_setting cs on cs.class_setting_id = func.function_status\n"
                + "    inner join class cl on cl.class_id = te.class_id\n"
                + "    inner join subject sj on sj.subject_id = cl.subject_id where "
                + condition
                + " group by func.function_id order by func.function_id";

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Function e = new Function();
                e.setId(rs.getInt("function_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setFeatureId(rs.getInt("feature_id"));
                e.setOwnerId(rs.getInt("owner_id"));
                e.setStatusId(rs.getInt("function_status"));
                e.setComplexityId(rs.getInt("complexity_id"));
                e.setName(rs.getString("function_name"));
                e.setClassCode(rs.getString("class_code"));
                e.setSubjectCode(rs.getString("subject_code"));
                e.setTeamName(rs.getString("team_name"));
                e.setAccessRoles(rs.getString("access_roles"));
                e.setOwnerName(rs.getString("owner_name"));
                e.setStatusName(rs.getString("status"));
                e.setFeatureName(rs.getString("feature_name"));
                e.setPriority(rs.getString("priority"));
                e.setDescription(rs.getString("description"));
                e.setClassId(rs.getInt("class_id"));
                e.setStatusColor(rs.getString("color"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Function> getFunctions(int start, int total, int uid, int roleId, int funcStatus, int subjectCode, int classCode, int teamId, int feId, String funcName) {
        ArrayList<Function> dummies = new ArrayList<Function>();
        String condition = "1 = 1"; // search all

        // only active base
        condition += " and cl.status = 1 and sj.status = 1 and te.status = 1 and fe.status = 1";

        // student
        if (roleId == 4) {
            condition += " and func.team_id in (select distinct team_id from class_user where user_id = ? and status = 1)";
        }

        // teacher
        if (roleId == 3) {
            condition += " and cl.trainer_id = ?";
        }
        // author
        if (roleId == 2) {
            condition += " and sj.author_id = ?";
        }

        // filter
        if (funcStatus != -1) {
            condition += " and func.function_status = ?";
        }
        if (funcName != null) {
            condition += " and func.function_name like ?";
        }
        if (subjectCode != -1) {
            condition += " and sj.subject_id = ?";
        }
        if (classCode != -1) {
            condition += " and cl.class_id = ?";
        }
        if (teamId != -1) {
            condition += " and func.team_id = ?";
        }
        if (feId != -1) {
            condition += " and func.feature_id = ?";
        }

        String sql = "select cs.color, sj.subject_code, cl.class_code, cl.subject_id, te.class_id, te.team_name, fe.feature_name, usr.roll_number, usr.full_name as owner_name, cs.type_title as status, func.* from \n"
                + "	`function` func\n"
                + "    inner join team te on te.team_id = func.team_id\n"
                + "    inner join feature fe on fe.feature_id = func.feature_id\n"
                + "    inner join class_user cu on cu.user_id = func.owner_id\n"
                + "		inner join user usr on usr.user_id = cu.user_id\n"
                + "	inner join class_setting cs on cs.class_setting_id = func.function_status\n"
                + "    inner join class cl on cl.class_id = te.class_id\n"
                + "    inner join subject sj on sj.subject_id = cl.subject_id where "
                + condition
                + " group by func.function_id order by func.function_id"
                + " limit " + (start - 1) + ", " + total;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            int _curIndex = 1;

            if (condition.contains("r_id")) {
                pre.setInt(_curIndex, uid);
                _curIndex += 1;
            }

            if (condition.contains("function_status = ?")) {
                pre.setInt(_curIndex, funcStatus);
                _curIndex += 1;
            }

            if (condition.contains("function_name like")) {
                pre.setString(_curIndex, "%" + funcName.toLowerCase() + "%");
                _curIndex += 1;
            }

            if (condition.contains("subject_id = ?")) {
                pre.setInt(_curIndex, subjectCode);
                _curIndex += 1;
            }

            if (condition.contains("class_id = ?")) {
                pre.setInt(_curIndex, classCode);
                _curIndex += 1;
            }

            if (condition.contains("team_id = ?")) {
                pre.setInt(_curIndex, teamId);
                _curIndex += 1;
            }

            if (condition.contains("feature_id = ?")) {
                pre.setInt(_curIndex, feId);
                _curIndex += 1;
            }

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Function e = new Function();
                e.setId(rs.getInt("function_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setFeatureId(rs.getInt("feature_id"));
                e.setOwnerId(rs.getInt("owner_id"));
                e.setStatusId(rs.getInt("function_status"));
                e.setComplexityId(rs.getInt("complexity_id"));
                e.setName(rs.getString("function_name"));
                e.setClassCode(rs.getString("class_code"));
                e.setSubjectCode(rs.getString("subject_code"));
                e.setTeamName(rs.getString("team_name"));
                e.setAccessRoles(rs.getString("access_roles"));
                e.setOwnerName(rs.getString("roll_number") + " - " + rs.getString("owner_name"));
                e.setStatusName(rs.getString("status"));
                e.setFeatureName(rs.getString("feature_name"));
                e.setPriority(rs.getString("priority"));
                e.setDescription(rs.getString("description"));
                e.setStatusColor(rs.getString("color"));
                dummies.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dummies;
    }

    public static int countFunctions(int uid, int roleId, int funcStatus, int subjectCode, int classCode, int teamId, int feId, String funcName) {
        String condition = "1 = 1"; // search all

        // only active base
        condition += " and cl.status = 1 and sj.status = 1 and te.status = 1 and fe.status = 1";

        // student
        if (roleId == 4) {
            condition += " and func.team_id in (select distinct team_id from class_user where user_id = ? and status = 1)";
        }

        // teacher
        if (roleId == 3) {
            condition += " and cl.trainer_id = ?";
        }
        // author
        if (roleId == 2) {
            condition += " and sj.author_id = ?";
        }

        // filter
        if (funcStatus != -1) {
            condition += " and func.function_status = ?";
        }
        if (funcName != null) {
            condition += " and func.function_name like ?";
        }
        if (subjectCode != -1) {
            condition += " and sj.subject_id = ?";
        }
        if (classCode != -1) {
            condition += " and cl.class_id = ?";
        }
        if (teamId != -1) {
            condition += " and func.team_id = ?";
        }
        if (feId != -1) {
            condition += " and func.feature_id = ?";
        }

        String sql = "select sj.subject_code, cl.class_code, cl.subject_id, te.class_id, te.team_name, fe.feature_name, usr.full_name as owner_name, cs.type_title as status, func.* from \n"
                + "	`function` func\n"
                + "    inner join team te on te.team_id = func.team_id\n"
                + "    inner join feature fe on fe.feature_id = func.feature_id\n"
                + "    inner join class_user cu on cu.user_id = func.owner_id\n"
                + "		inner join user usr on usr.user_id = cu.user_id\n"
                + "	inner join class_setting cs on cs.class_setting_id = func.function_status\n"
                + "    inner join class cl on cl.class_id = te.class_id\n"
                + "    inner join subject sj on sj.subject_id = cl.subject_id where "
                + condition
                + " group by func.function_id order by func.function_id";

        // count 
        sql = "select count(*) from (" + sql + ") as alt";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            int _curIndex = 1;

            if (condition.contains("r_id")) {
                pre.setInt(_curIndex, uid);
                _curIndex += 1;
            }

            if (condition.contains("function_status = ?")) {
                pre.setInt(_curIndex, funcStatus);
                _curIndex += 1;
            }

            if (condition.contains("function_name like")) {
                pre.setString(_curIndex, "%" + funcName.toLowerCase() + "%");
                _curIndex += 1;
            }

            if (condition.contains("subject_id = ?")) {
                pre.setInt(_curIndex, subjectCode);
                _curIndex += 1;
            }

            if (condition.contains("class_id = ?")) {
                pre.setInt(_curIndex, classCode);
                _curIndex += 1;
            }

            if (condition.contains("team_id = ?")) {
                pre.setInt(_curIndex, teamId);
                _curIndex += 1;
            }

            if (condition.contains("feature_id = ?")) {
                pre.setInt(_curIndex, feId);
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

    public static ArrayList<User> getOwnerList(int classId, int teamId) {
        ArrayList<User> dummies = new ArrayList<>();

        String sql = "select usr.* from class_user cu \n"
                + "	inner join user usr on usr.user_id = cu.user_id\n"
                + "    where cu.class_id = ? and team_id = ? and cu.status = 1\n"
                + "    group by usr.user_id";

        if (classId > -1) {
            try {
                PreparedStatement pre = getConnection().prepareStatement(sql);
                pre.setInt(1, classId);
                pre.setInt(2, teamId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    User usr = new User();
                    usr.setUser_id(rs.getInt("user_id"));
                    usr.setRoll_number(rs.getString("roll_number"));
                    usr.setFull_name(rs.getString("full_name"));
                    dummies.add(usr);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dummies;
    }

    public static ArrayList<Team> getTeamByClassId(int uid, int roleId, int classId) {
        ArrayList<Team> dummies = new ArrayList<>();

        String condition = "";

        if (roleId == 4) {
            condition += " and cu.user_id = " + uid;
        }

        String sql = "select te.* from class_user cu \n"
                + "	inner join team te on te.team_id = cu.team_id\n"
                + "    inner join class cl on cl.class_id = cu.class_id\n"
                + "    where cu.class_id = ? and cu.status = 1 and cl.status = 1 and te.status = 1" + condition
                + "    group by te.team_id";

        if (classId > -1) {
            try {
                PreparedStatement pre = getConnection().prepareStatement(sql);
                pre.setInt(1, classId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    Team te = new Team();
                    te.setTeam_id(rs.getInt("team_id"));
                    te.setTeam_name(rs.getString("team_name"));
                    dummies.add(te);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dummies;
    }

    public static ArrayList<Feature> getFeatureByTeamId(int teamId) {
        ArrayList<Feature> dummies = new ArrayList<>();

        String sql = "select fe.* from feature fe\n"
                + "	inner join team te on te.team_id = fe.team_id\n"
                + "where fe.team_id = ? and te.status = 1 and fe.status = 1";

        if (teamId > -1) {
            try {
                PreparedStatement pre = getConnection().prepareStatement(sql);
                pre.setInt(1, teamId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    Feature fe = new Feature();
                    fe.setFeature_id(rs.getInt("feature_id"));
                    fe.setFeature_name(rs.getString("feature_name"));
                    dummies.add(fe);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dummies;
    }

    public static ArrayList<ClassSetting> getFunctionStatus() {
        ArrayList<ClassSetting> vector = new ArrayList<>();

        String sql = "select * from class_setting where type_id in (select setting_id from setting where status = 1 and setting_title like \"function_status\") ";

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setClass_setting_id(rs.getInt("class_setting_id"));
                cs.setType_title(rs.getString("type_title"));
                vector.add(cs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public static ArrayList<Subject> getSubject(int u_id, int role_id) {
        ArrayList<Subject> dummies = new ArrayList<>();

        String condition = " where 1 = 1";

        if (role_id == 4) {
            condition = " inner join class c on c.subject_id = s.subject_id inner join class_user cu on c.class_id = cu.class_id and cu.user_id = " + u_id;
        }
        if (role_id == 3) {
            condition = " , class c where s.subject_id = c.subject_id and trainer_id =" + u_id;
        }
        if (role_id == 2) {
            condition = " where s.author_id = " + u_id;
        }

        // only active
        condition += " and s.status = 1";

        String sql = "SELECT distinct s.* from subject s " + " " + condition;

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Subject sj = new Subject();
                sj.setSubject_id(rs.getInt("subject_id"));
                sj.setSubject_code(rs.getString("subject_code"));
                dummies.add(sj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dummies;
    }

    public static ArrayList<Class> getClass(int u_id, int role_id, int subId) {
        ArrayList<Class> dummies = new ArrayList<>();

        String condition = " where 1 = 1";

        if (role_id == 4) {
            condition = " , class_user cu where c.class_id = cu.class_id and cu.user_id =" + u_id;
        }
        if (role_id == 3) {
            condition = " where c.trainer_id = " + u_id;
        }
        if (role_id == 2) {
            condition = ", subject s where c.subject_id = s.subject_id and s.author_id = " + u_id;
        }

        // only active
        condition += " and c.status = 1";

        if (subId != -1) {
            condition += " and c.subject_id = " + subId;
        }

        String sql = "select distinct c.* from class c " + " " + condition;

        System.out.println(sql);

        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Class cl = new Class();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_code(rs.getString("class_code"));
                dummies.add(cl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dummies;
    }

    public static int insertNewFunction(Function fu) {
        int n = 0;
        String sql = "insert into `function` (team_id, function_name, feature_id, access_roles, description, complexity_id, owner_id, priority, function_status)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, fu.getTeamId());
            pre.setString(2, fu.getName());
            pre.setInt(3, fu.getFeatureId());
            pre.setString(4, fu.getAccessRoles());
            pre.setString(5, fu.getDescription());
            pre.setInt(6, fu.getComplexityId());
            pre.setInt(7, fu.getOwnerId());
            pre.setString(8, fu.getPriority());
            pre.setInt(9, fu.getStatusId());
            System.out.println(pre.toString());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;

    }

    public static int updateFunction(int id, Function fu) {
        int n = 0;
        String sql = "update `function` set team_id = ?, function_name = ?, feature_id = ?, access_roles = ?, description = ?, complexity_id = ?, owner_id = ?, priority = ?, function_status = ?"
                + " where function_id = " + id;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, fu.getTeamId());
            pre.setString(2, fu.getName());
            pre.setInt(3, fu.getFeatureId());
            pre.setString(4, fu.getAccessRoles());
            pre.setString(5, fu.getDescription());
            pre.setInt(6, fu.getComplexityId());
            pre.setInt(7, fu.getOwnerId());
            pre.setString(8, fu.getPriority());
            pre.setInt(9, fu.getStatusId());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;

    }

    private static boolean featureInTeam(int feId, int teId) {
        String sql = "select exists(select * from feature where feature_id = " + feId + " and team_id = " + teId + " and status = 1)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean funcInTeam(int funcId, int teId) {
        String sql = "select exists(select * from `function` where team_id = " + teId + " and function_id = " + funcId + ")";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean hasTeam(int uid, int roleId, int teamId, int ownerId) {
        String condition = "";
        if (roleId == 4) {
            condition += "and cu.user_id in (" + uid + ", " + ownerId + ")";
        }
        if (roleId == 3) {
            condition += "and cl.trainer_id = " + uid + " and cu.user_id in (" + ownerId + ")";
        }
        String sql = "select count(*) from (select distinct cu.user_id\n"
                + "	from class_user cu \n"
                + "    inner join class cl on cl.class_id = cu.class_id\n"
                + "    inner join team te on te.team_id = cu.team_id\n"
                + "    where 1 = 1\n"
                + "        and cu.status = 1 and cl.status = 1 and te.status = 1\n"
                + "        and cu.team_id = " + teamId + "\n"
                + condition
                + "        ) abc";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return (roleId == 4 && uid != ownerId) ? rs.getInt(1) == 2 : rs.getInt(1) == 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getOk(int uid, int roleId, int ownerId, int teamId, int featureId, int funcId) {
        if (!hasTeam(uid, roleId, teamId, ownerId)) {
            return "user&owner invalid";
        }
        if (!featureInTeam(featureId, teamId)) {
            return "feature not exists in team";
        }
        if (funcId != - 1 && !funcInTeam(funcId, teamId)) {
            return "function not exists in team";
        }
        return "success";
    }

    private static int getUserIdByRollNumber(ArrayList<User> ownerList, String roll_number) {
        int _id = -1;
        for (User owner : ownerList) {
            if (owner.getRoll_number().equalsIgnoreCase(roll_number)) {
                _id = owner.getUser_id();
            }
        }
        return _id;
    }

    private static int getStatusIdByName(ArrayList<ClassSetting> clList, String name) {
        int _id = -1;
        for (ClassSetting owner : clList) {
            if (owner.getType_title().equalsIgnoreCase(name)) {
                _id = owner.getClass_setting_id();
            }
        }
        return _id;
    }
    
    private static int getFeatureIdByName(ArrayList<Feature> clList, String name) {
        int _id = -1;
        for (Feature owner : clList) {
            if (owner.getFeature_name().equalsIgnoreCase(name)) {
                _id = owner.getFeature_id();
            }
        }
        return _id;
    }

    public static String UpdateFromList(int uid, int roleId, ArrayList<Function> clList) {
        int n = 0;
        // parse data
        Function tt = (Function) clList.get(0);
        ArrayList<User> ownerList = getOwnerList(tt.getClassId(), tt.getTeamId());
        ArrayList<ClassSetting> statusList = getFunctionStatus();
        ArrayList<Feature> feList = getFeatureByTeamId(tt.getTeamId());
        tt.setOwnerId(getUserIdByRollNumber(ownerList, tt.getOwnerName()));
        tt.setFeatureId(getFeatureIdByName(feList, tt.getFeatureName()));
        System.out.println(tt);
        String checkFirst = DAOFunction.getOk(uid, roleId, tt.getOwnerId(), tt.getTeamId(), tt.getFeatureId(), -1);
        if (!"success".equals(checkFirst)) {
            return "error: " + checkFirst;
        }
        // process data
        for (Function cl : clList) {
            cl.setOwnerId(getUserIdByRollNumber(ownerList, cl.getOwnerName()));
            cl.setStatusId(getStatusIdByName(statusList, cl.getStatusName()));
            cl.setFeatureId(getFeatureIdByName(feList, cl.getFeatureName()));
            System.out.println(cl);
            if (cl.getOwnerId() == -1 || cl.getStatusId() == -1 || cl.getFeatureId()== -1) {
                continue;
            }
            String getOk = DAOFunction.getOk(uid, roleId, cl.getOwnerId(), cl.getTeamId(), cl.getFeatureId(), cl.getId());
            if ("success".equals(getOk)) {
                if (cl.getId() == -1) {
                    // insert
                    n += insertNewFunction(cl);

                } else {
                    // update
                    n += updateFunction(cl.getId(), cl);
                }
            }

        }
        return n == 0 ? "error: No items have been changed!" : "success: " + n + " items have been changed|" + tt.getSubjectId() + "-" + tt.getClassId() + "-" + tt.getTeamId() + "-" + tt.getFeatureId();
    }

    public static Team getCurrentTeam(int team_id) {
        String sql = "select * from team "
                + " where team_id = " + team_id;
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Team cla = new Team();
                cla.setTeam_id(rs.getInt("team_id"));
                cla.setTeam_name(rs.getString("team_name"));
                return cla;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int syncFunction(int team_id) {
        
        String selectTeamInf = "select * from team where team_id = " + team_id;
        String project_id = "-1";
        String team_token = "";
        try {
            PreparedStatement pre = getConnection().prepareStatement(selectTeamInf);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                project_id = rs.getString("project_id");
                team_token = rs.getString("team_token");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        int n = 0;
        int count = 0;
        Vector<String> issues_name = new Vector<>();
        Vector<Integer> issues_id = new Vector<>();
        JSONArray jsons = API.getJsonArray("projects", project_id, "issues", team_token);
        if (jsons != null) {
            for (int i = 0; i < jsons.length(); i++) {
                JSONObject json = jsons.getJSONObject(i);
                issues_name.add(json.get("title").toString().trim());
                issues_id.add(json.getInt("iid"));
            }
        }

        String sql = "select * from `function` where team_id = " + team_id;

        Vector<String> keys = new Vector<>();
        
        keys.add("title");
        keys.add("description");

        Vector<Function> functions = new Vector<>();
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                
                count++;
                
                Vector<String> values = new Vector<>();
                values.add(rs.getString("function_name"));
                values.add(rs.getString("description"));
                
                int index = issues_name.indexOf(rs.getString("function_name"));
                if (index > -1) {
                    if (API.putApi("projects", project_id, "issues/" + issues_id.get(index), keys, values, team_token) == 200) {
                        n++;
                    }
                } else {
                    if (API.postApi("projects", project_id, "issues", keys, values, team_token) == 201) {
                        n++;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count == n ? 1 : 0;
    }
}
