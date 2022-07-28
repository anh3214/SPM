package dao;

import entity.Milestone;
import entity.Class;
import entity.Iteration;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.API;

public class DAOMilestone extends DataConnection {

    public int updateMilestone(Milestone mil) {
        int n = 0;
        String sql = "update milestone set milestone_name = ?, iteration_id = ?, class_id = ?, from_date = ?, to_date = ?, description = ?, status = ? where milestone_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, mil.getMilestone_name());
            pre.setInt(2, mil.getIteration_id());
            pre.setInt(3, mil.getClass_id());
            pre.setString(4, mil.getFrom_date());
            pre.setString(5, mil.getTo_date());
            pre.setString(6, mil.getDescription());
            pre.setInt(7, mil.getStatus());
            pre.setInt(8, mil.getMilestone_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addMilestone(Milestone mil) {
        int n = 0;
        String sql = "INSERT INTO milestone(milestone_name, iteration_id, class_id, from_date, to_date, description, status) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, mil.getMilestone_name());
            pre.setInt(2, mil.getIteration_id());
            pre.setInt(3, mil.getClass_id());
            pre.setString(4, mil.getFrom_date());
            pre.setString(5, mil.getTo_date());
            pre.setString(6, mil.getDescription());
            pre.setInt(7, mil.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateMilestoneStatus(int milestone_id, int status) {
        int n = 0;
        String sql = "update milestone set status = ? where milestone_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, milestone_id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Class> getClassBySubject(int role, int u_id, int subject_id) {
        Vector<Class> vec = new Vector<>();
        String sql = "select class.*\n"
                + "from class\n"
                + "inner join subject on subject.subject_id = class.subject_id\n";        
        if (role == 3) {
            sql += " where class.trainer_id = " + u_id + " and class.status = 1 ";
        }
        if (role == 2) {
            sql = sql + " where subject.author_id = " + u_id + " and class.status = 1 ";
        }
        if (role == 1) {
            sql += " where class.status = 1 ";
        }
        if (role == 4) {
            sql += " inner join class_user on class.class_id = class_user.class_id\n"
                    + " where class.status = 1 and class_user.user_id = " + u_id;
        }
        sql += " and class.subject_id = " + subject_id;

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

    public Vector<Iteration> getIterationByClass(int class_id) {
        Vector<Iteration> vec = new Vector<>();
        String sql = "select *\n"
                + "from iteration\n"
                + "inner join subject on subject.subject_id = iteration.subject_id\n"
                + "inner join class on class.subject_id = subject.subject_id\n"
                + "where iteration.status = 1 and class.class_id = " + class_id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Iteration iter = new Iteration();
                iter.setIteration_id(rs.getInt("iteration_id"));
                iter.setIteration_name(rs.getString("iteration_name"));
                iter.setDuration(rs.getInt("duration"));
                vec.add(iter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Class> queryClass(int u_id, int role) {
        Vector<Class> vec = new Vector<>();
        String sql = "select * from class ";
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

    public int getDurationIteration(int iteration_id) {
        String sql = "select duration from iteration where iteration_id = " + iteration_id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt("duration");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Milestone getMilestoneByID(int u_id, int role, int milestone_id) {
        String sql = "select "
                + "milestone.*, "
                + "class.class_id, class.class_code, "
                + "iteration.iteration_id, iteration.iteration_name, "
                + "subject.subject_id, subject.subject_code, subject.subject_name, iteration.duration, "
                + "user.full_name "
                + "from class "
                + "inner join milestone on class.class_id = milestone.class_id "
                + "inner join iteration on iteration.iteration_id = milestone.iteration_id "
                + "inner join subject on subject.subject_id = class.subject_id "
                + "inner join user on user.user_id = class.trainer_id "
                + " where milestone.milestone_id = " + milestone_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Milestone mil = new Milestone();
                mil.setMilestone_id(rs.getInt("milestone_id"));
                mil.setMilestone_name(rs.getString("milestone_name"));
                mil.setIteration_id(rs.getInt("iteration_id"));
                mil.setClass_id(rs.getInt("class_id"));
                mil.setFrom_date(rs.getString("from_date"));
                mil.setTo_date(rs.getString("to_date"));
                mil.setDescription(rs.getString("description"));
                mil.setStatus(rs.getInt("status"));
                mil.setClass_id(rs.getInt("class_id"));
                mil.setClass_code(rs.getString("class_code"));
                mil.setIteration_name(rs.getString("iteration_name"));
                mil.setSubject_id(rs.getInt("subject_id"));
                mil.setSubject_code(rs.getString("subject_code"));
                mil.setSubject_name(rs.getString("subject_name"));
                mil.setDuration(rs.getInt("duration"));
                mil.setTrainer_name(rs.getString("full_name"));
                return mil;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<Milestone> queryMilestone(int user_id, int role_id, int pageIndex, int pageSize, int class_id, int iteration_id, int status, String search,
            String filter_col, String filter_type) {
        Vector<Milestone> vec = new Vector<>();

        //condition for filter
        String filter_condition = " where 1 = 1";
        if (class_id != -1) {
            filter_condition += " and class.class_id = " + class_id;
        }

        if (role_id == 3) {
            filter_condition += " and class.trainer_id = " + user_id;
        }
        if (role_id == 2) {
            filter_condition += " and subject.author_id = " + user_id;
        }

        if (status != -1) {
            filter_condition += " and milestone.status = " + status;
        }

        if (iteration_id != -1) {
            filter_condition += " and milestone.iteration_id = " + iteration_id;
        }
        
        //condition for searching
        String search_condition = " ";
        if (search != null) {
            search_condition += " and milestone.milestone_name like '%" + search + "%'";
        }

        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }

        String sql = "select "
                + "milestone.*, "
                + "class.class_id, class.class_code, "
                + "iteration.iteration_id, iteration.iteration_name, "
                + "subject.subject_id, subject.subject_code, subject.subject_name, iteration.duration, "
                + "user.full_name "
                + "from class "
                + "inner join milestone on class.class_id = milestone.class_id "
                + "inner join iteration on iteration.iteration_id = milestone.iteration_id "
                + "inner join subject on subject.subject_id = class.subject_id "
                + "inner join user on user.user_id = class.trainer_id "
                + filter_condition + search_condition + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Milestone mil = new Milestone();
                mil.setMilestone_id(rs.getInt("milestone_id"));
                mil.setMilestone_name(rs.getString("milestone_name"));
                mil.setIteration_id(rs.getInt("iteration_id"));
                mil.setClass_id(rs.getInt("class_id"));
                mil.setFrom_date(rs.getString("from_date"));
                mil.setTo_date(rs.getString("to_date"));
                mil.setDescription(rs.getString("description"));
                mil.setStatus(rs.getInt("status"));
                mil.setClass_id(rs.getInt("class_id"));
                mil.setClass_code(rs.getString("class_code"));
                mil.setIteration_name(rs.getString("iteration_name"));
                mil.setSubject_id(rs.getInt("subject_id"));
                mil.setSubject_code(rs.getString("subject_code"));
                mil.setSubject_name(rs.getString("subject_name"));
                mil.setDuration(rs.getInt("duration"));
                mil.setTrainer_name(rs.getString("full_name"));
                vec.add(mil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countMilestone(int user_id, int role_id, int class_id, int iteration_id, int status, String search) {
        //condition for filter
        String filter_condition = " where 1 = 1";

        if (class_id != -1) {
            filter_condition += " and class.class_id = " + class_id;
        }

        if (role_id == 3) {
            filter_condition += " and class.trainer_id = " + user_id;
        }
        if (role_id == 2) {
            filter_condition += " and subject.author_id = " + user_id;
        }

        if (status != -1) {
            filter_condition += " and milestone.status = " + status;
        }
        
        if (iteration_id != -1) {
            filter_condition += " and milestone.iteration_id = " + iteration_id;
        }

        //condition for searching
        String search_condition = " ";
        if (search != null) {
            search_condition += " and milestone.milestone_name like '%" + search + "%'";
        }

        String sql = "select count(*) "
                + "from class "
                + "inner join milestone on class.class_id = milestone.class_id "
                + "inner join iteration on iteration.iteration_id = milestone.iteration_id "
                + "inner join subject on subject.subject_id = class.subject_id "
                + "inner join user on user.user_id = class.trainer_id "
                + filter_condition + search_condition;
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

    public Vector<Subject> querySubjectMilestone(int user_id, int role_id) {
        Vector<Subject> vec = new Vector<>();
        String sql = "select distinct subject.subject_id, subject.subject_code, subject.subject_name "
                + "from class "
                + "inner join milestone on class.class_id = milestone.class_id "
                + "inner join iteration on iteration.iteration_id = milestone.iteration_id "
                + "inner join subject on subject.subject_id = class.subject_id";
        if (role_id == 3) {
            sql += " where class.trainer_id = " + user_id;
        }
        if (role_id == 2) {
            sql += " where subject.author_id = " + user_id;
        }
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setSubject_id(rs.getInt("subject_id"));
                sub.setSubject_code(rs.getString("subject_code"));
                sub.setSubject_name(rs.getString("subject_name"));
                vec.add(sub);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int syncMilestoneGit(String group_id, String token, int class_id) {
        int n = 0;

        String sql = "select * from milestone where class_id = " + class_id;
        Vector<Milestone> milestones = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Milestone mil = new Milestone();
                mil.setMilestone_id(rs.getInt("milestone_id"));
                mil.setMilestone_name(rs.getString("milestone_name").trim());
                mil.setFrom_date(rs.getString("from_date"));
                mil.setTo_date(rs.getString("to_date"));
                mil.setDescription(rs.getString("description"));
                mil.setStatus(rs.getInt("status"));
                milestones.add(mil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (milestones.size() == 0) {
            return 0;
        }

        Vector<String> keys = new Vector<>();
        keys.add("title");
        keys.add("state_event");
        keys.add("start_date");
        keys.add("due_date");
        keys.add("description");

        for (int i = 0; i < milestones.size(); i++) {
            Vector<String> values = new Vector<>();
            values.add(milestones.get(i).getMilestone_name());
            values.add(milestones.get(i).getStatus() == 1 ? "activate" : "close");
            values.add(milestones.get(i).getFrom_date());
            values.add(milestones.get(i).getTo_date());
            values.add(milestones.get(i).getDescription());
            API.postApi("groups", group_id, "milestones", keys, values, token);
        }

        //get name milestone from gitlab
        Vector<String> milestone_names = new Vector<>();
        Vector<Integer> milestone_ids = new Vector<>();
        JSONArray jsons = API.getJsonArray("groups", group_id, "milestones", token);
        if (jsons != null) {
            for (int i = 0; i < jsons.length(); i++) {
                JSONObject json = jsons.getJSONObject(i);
                milestone_names.add(json.get("title").toString().trim());
                milestone_ids.add((json.getInt("id")));
            }
        }

        //get milestone from database
        for (int i = 0; i < milestones.size(); i++) {
            int index = milestone_names.indexOf(milestones.get(i).getMilestone_name());
            Vector<String> values = new Vector<>();
            values.add(milestones.get(i).getMilestone_name());
            values.add(milestones.get(i).getStatus() == 1 ? "activate" : "close");
            values.add(milestones.get(i).getFrom_date());
            values.add(milestones.get(i).getTo_date());
            values.add(milestones.get(i).getDescription());
            if (index > -1 && API.putApi("groups", group_id, "milestones/" + milestone_ids.get(index), keys, values, token) == 200) {
                n++;
            }
        }

        return n == milestones.size() ? 1 : 0;
    }

    public String getGitLab_id(int class_id) {
        String sql = "select gitlab_id from class where class_id = " + class_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getString("gitlab_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Class getClass(int class_id) {
        String sql = "select * from class where class_id = " + class_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Class cla = new Class();
                cla.setClass_id(rs.getInt("class_id"));
                cla.setClass_code(rs.getString("class_code"));
                return cla;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        DAOMilestone dao = new DAOMilestone();
//        System.out.println(dao.syncMilestoneGit("55154620", "glpat-Szr8A4qSNf27ASk9psY6", 1));;
//    }
}
