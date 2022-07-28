/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Function;
import entity.Issue;
import entity.Milestone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.API;

/**
 *
 * @author buitr
 */
public class DAOIssue extends DataConnection {

    Connection conn = getConnection();

    public Vector<Issue> getClass(int u_id, int role_id) {
        Vector<Issue> vector = new Vector<>();

        String condition = " where 1=1 ";

        if (role_id == 4) {
            condition = " join team  t on t.class_id = c.class_id"
                    + " join class_user on c.class_id = class_user.class_id where user_id = " + u_id;
        }
        if (role_id == 3) {
            condition = " join team  t on t.class_id = c.class_id  where trainer_id = " + u_id;
        }
        if (role_id == 2) {
            condition = " join subject s on c.subject_id = s.subject_id "
                    + "join team  t on t.class_id = c.class_id  where s.author_id = " + u_id;
        }

        String sql = "SELECT distinct c.class_code, t.team_name,t.team_id    from class c\n" + " " + condition;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String class_code = rs.getString(1);
                String team_name = rs.getString(2);
                int team_id = rs.getInt(3);
                vector.add(new Issue(team_id, class_code, team_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Issue> getIssue(int u_id, int role_id, int team_id, int pageIndex, int pageSize, int status, String assignee, String description, String function, String milestone, String filter_col, String filter_type) {
        Vector<Issue> vec = new Vector<>();

        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and i.status = " + status;
        }
        if (description != null) {
            condition += " and i.issue_title like '%" + description + "%'";
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

        String sql = "SELECT i.*, cs1.color as colorOfLabel, cs2.color as colorOfStatus, u1.full_name, t.team_name, m.milestone_name,\n"
                + "f.function_name, cs1.type_title as labels_title, cs2.type_title as issue_status_title FROM issue i\n"
                + "inner join milestone m on m.milestone_id = i.milestone_id\n"
                + "inner join user u1 on u1.user_id = i.assignee_id\n"
                + "inner join team t on t.team_id = i.team_id\n"
                + "left join `function` f on f.function_id = i.function_ids\n"
                + "left join class_setting cs1 on cs1.type_id = 3 and cs1.type_value = i.labels and t.class_id = cs1.class_id\n"
                + "left join class_setting cs2 on cs2.type_id = 2 and cs2.type_value = i.issue_status and t.class_id = cs2.class_id\n"
                + "where i.team_id = " + team_id
                + condition
                + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Issue temp = new Issue();
                temp.setIssue_id(rs.getInt("issue_id"));
                temp.setAssignee_id(rs.getInt("assignee_id"));
                temp.setIssue_title(rs.getString("issue_title"));
                temp.setDesciption(rs.getString("description"));
                temp.setGitlab_id(rs.getInt("gitlab_id"));
                temp.setGitlab_url(rs.getString("gitlab_url"));
                temp.setCreated_at(rs.getString("created_at"));
                temp.setDue_date(rs.getString("due_date"));
                temp.setTeam_id(rs.getInt("team_id"));
                temp.setMilestone_id(rs.getInt("milestone_id"));
                temp.setFunction_ids(rs.getString("function_ids"));
                temp.setLabels(rs.getString("labels"));

                temp.setStatus(rs.getInt("status"));
                temp.setIssue_type(rs.getInt("issue_type"));
                temp.setIssue_status(rs.getInt("issue_status"));
                temp.setColorOfLabel(rs.getString("colorOfLabel"));
                temp.setColorOfStatus(rs.getString("colorOfStatus"));
                temp.setAssignee_name(rs.getString("full_name"));
                temp.setTeam_name(rs.getString("team_name"));
                temp.setMilestone_name(rs.getString("milestone_name"));
                temp.setFunction_name(rs.getString("function_name"));
                temp.setLabel_title(rs.getString("labels_title"));
                temp.setIssue_status_title(rs.getString("issue_status_title"));
                vec.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countIssue(int u_id, int role_id, int team_id, int status, String assignee, String description, String function, String milestone) {
        String condition = " and 1 = 1 ";
        if (status != -1) {
            condition += " and i.status = " + status;
        }
        if (description != null) {
            condition += " and i.issue_title like '%" + description + "%'";
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

        String sql = "SELECT count(*) FROM issue i\n"
                + "inner join milestone m on m.milestone_id = i.milestone_id\n"
                + "inner join user u1 on u1.user_id = i.assignee_id\n"
                + "inner join team t on t.team_id = i.team_id\n"
                + "left join `function` f on f.function_id = i.function_ids\n"
                + "left join class_setting cs1 on cs1.type_id = 6 and cs1.type_value = i.labels and t.class_id = cs1.class_id\n"
                + "left join class_setting cs2 on cs2.type_id = 5 and cs2.type_value = i.issue_status and t.class_id = cs2.class_id\n"
                + "where i.team_id = " + team_id
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

    public int updateStatus(int issue_id, int status) {
        int n = 0;
        try {
            String sql = "UPDATE `issue`\n"
                    + "SET\n"
                    + "`status` = " + status + "\n"
                    + "WHERE `issue_id` = " + issue_id + ";";
            PreparedStatement ps = conn.prepareStatement(sql);

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public Vector<String> getAssignee(int u_id, int role_id, int team_id) {
        Vector<String> vector = new Vector<>();

        String sql = "select distinct  u.full_name from user u \n"
                + "inner join issue i on u.user_id = i.assignee_id\n"
                + "inner join team t on i.team_id = t.team_id\n"
                + "where t.team_id = " + " " + team_id;

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

    public boolean checkIssu(int id) {
        String sql = "SELECT * FROM issue where issue_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Function searchFuntion(int team_id, String title) {

        String sql = "select * from `function` where team_id = ? and function_name like ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, team_id);
            pre.setString(2, title);
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
                e.setAccessRoles(rs.getString("access_roles"));
                e.setPriority(rs.getString("priority"));
                e.setDescription(rs.getString("description"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Milestone searchMiles(String tile, int class_id) {

        String sql = "select * from milestone where milestone_name like ? and class_id = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tile);
            pre.setInt(2, class_id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Milestone m = new Milestone();
                m.setMilestone_id(rs.getInt("milestone_id"));
                m.setMilestone_name(rs.getString("milestone_name"));
                m.setIteration_id(rs.getInt("iteration_id"));
                m.setClass_id(rs.getInt("class_id"));
                m.setFrom_date(rs.getString("from_date"));
                m.setTo_date(rs.getString("to_date"));
                m.setDescription(rs.getString("description"));
                m.setStatus(rs.getInt("status"));
                return m;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getType(String issuetype, String issuestatus, String lables, int class_id) {
        String sql = "SELECT class.class_setting_id  FROM class_setting class \n"
                + "inner join setting c on c.setting_id = class.type_id\n"
                + "where class.type_title like ? and c.setting_title like ? and class.class_id = ? ;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, lables);
            if (issuestatus != null) {
                pre.setString(2, issuestatus);
            }
            if (issuetype != null) {
                pre.setString(2, issuetype);
            }
            pre.setInt(3, class_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int syncIssue(String project_id, String token, int team_id, int class_id) {

        JSONArray jsonIssue;
        JSONArray jsonIssueLinks;

        int n = 0;
        Issue issue = new Issue();
        jsonIssue = API.getJsonArray("projects", project_id, "issues", token);
        System.out.println("xxxxxxx");
        if (jsonIssue != null) {
            for (int i = 0; i < jsonIssue.length(); i++) {
                JSONObject json = jsonIssue.getJSONObject(i);
                JSONArray labels = (JSONArray) json.get("labels");
                if (labels.length() != 0) {

                    JSONObject milstone = (JSONObject) json.get("milestone");
                    jsonIssueLinks = API.getJsonArrayLinks("projects", project_id, "issues", token, json.getInt("iid"), "links");
                    JSONObject json2 = jsonIssueLinks.getJSONObject(0);
                    Function e = searchFuntion(team_id, (String) json2.get("title"));
                    Milestone m = searchMiles((String) milstone.get("title"), class_id);
                    if (json2 != null && e != null && m != null) {
                        issue.setIssue_id(json.getInt("iid"));
                        issue.setIssue_title((String) json.get("title"));
                        issue.setAssignee_id(e.getOwnerId());
                        System.out.println(e.getOwnerId());
                        issue.setDesciption((String) json.get("description"));
                        issue.setGitlab_id(json.getInt("project_id"));
                        issue.setGitlab_url((String) json.get("web_url"));
                        String date = (String) json.get("created_at");
                        String[] result = date.split("T");
                        issue.setCreated_at(result[0]);
                        if (json.isNull("due_date")) {
                            issue.setDue_date("");
                        } else {
                            issue.setDue_date((String) json.get("due_date"));
                        }
                        issue.setTeam_id(team_id);
                        issue.setMilestone_id(m.getMilestone_id());
                        issue.setFunction_ids(e.getId() + "");

                        if (json.get("state").equals("opened")) {
                            issue.setStatus(1);
                        } else {
                            issue.setStatus(0);
                        }
                        String lables1 = "";
                        for (int j = 0; j < labels.length(); j++) {
                            lables1 += labels.getString(j) + ",";

                            int issuetype = getType("issue type", null, labels.getString(j), class_id);
                            if (issuetype != 0) {
                                issue.setIssue_type(issuetype);
                            }
                            int issuestatus = getType(null, "issue status", labels.getString(j), class_id);
                            if (issuestatus != 0) {
                                issue.setIssue_status(issuestatus);
                            }
                        }
                        System.out.println(issue.getIssue_type());
                        System.out.println(issue.getIssue_status());
                        issue.setLabels(lables1.substring(0, lables1.length() - 1));
                        if (!checkIssu(json.getInt("iid"))) {
                            n = addIssue(issue);
                        } else {
                            n = update(issue);
                        }
                    }
                }
            }
        }
        return n;
    }

    public int addIssue(Issue i) {
        int n = 0;
        String sql = "INSERT INTO `issue`\n"
                + "(`issue_id`,`issue_title`,`assignee_id`,`description`,`gitlab_id`,`gitlab_url`,"
                + "`created_at`,`due_date`,`team_id`,`milestone_id`,`function_ids`,`labels`,`status`,`issue_type`,`issue_status`)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, i.getIssue_id());
            pre.setString(2, i.getIssue_title());
            pre.setInt(3, i.getAssignee_id());
            pre.setString(4, i.getDesciption());
            pre.setInt(5, i.getGitlab_id());
            pre.setString(6, i.getGitlab_url());
            pre.setString(7, i.getCreated_at());
            pre.setString(8, i.getDue_date());
            pre.setInt(9, i.getTeam_id());
            pre.setInt(10, i.getMilestone_id());
            pre.setString(11, i.getFunction_ids());
            pre.setString(12, i.getLabels());
            pre.setInt(13, i.getStatus());
            pre.setInt(14, i.getIssue_type());
            pre.setInt(15, i.getIssue_status());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int update(Issue i) {
        int n = 0;
        String sql = "UPDATE `issue`\n"
                + "SET `issue_title` = ?,`assignee_id` = ?,`description` = ?,`gitlab_id` = ?,`gitlab_url` = ?"
                + ",`created_at` = ?,`due_date` = ?,`team_id` = ?,`milestone_id` = ?"
                + ",`function_ids` = ?,`labels` = ?,`status` = ?,`issue_type` = ?,`issue_status` = ?\n"
                + "WHERE `issue_id` = ?;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, i.getIssue_title());
            pre.setInt(2, i.getAssignee_id());
            pre.setString(3, i.getDesciption());
            pre.setInt(4, i.getGitlab_id());
            pre.setString(5, i.getGitlab_url());
            pre.setString(6, i.getCreated_at());
            pre.setString(7, i.getDue_date());
            pre.setInt(8, i.getTeam_id());
            pre.setInt(9, i.getMilestone_id());
            pre.setString(10, i.getFunction_ids());
            pre.setString(11, i.getLabels());
            pre.setInt(12, i.getStatus());
            pre.setInt(13, i.getIssue_type());
            pre.setInt(14, i.getIssue_status());
            pre.setInt(15, i.getIssue_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOIssue dao = new DAOIssue();
        int n = dao.syncIssue("37597853", "glpat-BHfEsGqeyvhXhhi8myTo", 2, 1);
        System.out.println(n);
    }
}
