/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.Criteria;
import entity.Iteration;
import entity.Subject;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luu Dat
 */
public class DAOCriteria extends DataConnection {

    public Vector<Criteria> queryCriteria(
            int user_id, int role_id,
            int pageIndex, int pageSize, int subject_id,
            int iterationID, int status,
            String criteria_id, String title,
            String filter_col, String filter_type) {
        Vector<Criteria> vec = new Vector<>();
        String sql = "select evalution_criteria.*, iteration.iteration_name, subject. *\n"
                + "                           from   iteration\n"
                + "                   inner join evalution_criteria  on iteration.iteration_id= evalution_criteria.iteration_id\n"
                + "                   inner join subject  on iteration.subject_id= subject.subject_id\n"
                + "                   where subject.subject_id = " + subject_id;
        if (iterationID != -1) {
            sql += " and evalution_criteria.iteration_id = " + iterationID;
        }
        if (status != -1) {
            sql += " and evalution_criteria.status = " + status;
        }
        sql += " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;

        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                Criteria cri = new Criteria();
                cri.setCriteria_id(rs.getInt(1));
                cri.setIteration_id(rs.getInt(2));
                cri.setEvalution_title(rs.getString(3));
                cri.setEvalution_weight(rs.getString(4));
                cri.setTeam_evaluation(rs.getString(5));
                cri.setCriteria_order(rs.getString(6));
                cri.setMax_loc(rs.getString(7));
                cri.setStatus(rs.getInt(8));
                cri.setIteration_name(rs.getString(9));
                cri.setSubject_id(rs.getInt("subject_id"));
                cri.setSubject_code(rs.getString("subject_code"));

                vec.add(cri);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<Iteration> queryIterationCriteria() {
        Vector<Iteration> vec = new Vector<>();
        String sql = "select distinct iteration.iteration_id, iteration.iteration_name\n"
                + "              from iteration \n"
                + "              inner join evalution_criteria on iteration.iteration_id = evalution_criteria.iteration_id ";

        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Iteration iter = new Iteration();
                iter.setIteration_id(rs.getInt(1));
                iter.setIteration_name(rs.getString(2));

                vec.add(iter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countCriteria(int user_id, int role, int subject_id, int iterationID, int status, String criteria_id, String iteration) {

        String sql = "select count(*) \n"
                + "    from evalution_criteria\n"
                + "         inner join iteration on iteration.iteration_id = evalution_criteria.iteration_id\n"
                + "         inner join subject on iteration.subject_id = subject.subject_id\n"
                + "         where subject.subject_id = " + subject_id;
        if (iterationID != -1) {
            sql += " and evalution_criteria.iteration_id = " + iterationID;
        }
        if (status != -1) {
            sql += " and evalution_criteria.status = " + status;
        }

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

    public int updateCriteriaStatus(int criteria_id, int status) {
        int n = 0;
        String sql = "update evalution_criteria set status = ? where criterial_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, criteria_id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Criteria getCriteriaById(int user_id, int role, int criteria_id) {
        String sql = "select *\n"
                + "              from evalution_criteria\n"
                + "                 inner join iteration on iteration.iteration_id = evalution_criteria.iteration_id\n"
                + "              inner join subject on subject.subject_id = iteration.subject_id\n"
                + "              where evalution_criteria.criterial_id = " + criteria_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Criteria crite = new Criteria();
                crite.setCriteria_id(rs.getInt("criterial_id"));
                crite.setIteration_id(rs.getInt("iteration_id"));
                crite.setEvalution_title(rs.getString("evalution_title"));
                crite.setEvalution_weight(rs.getString("evalution_weight"));
                crite.setTeam_evaluation(rs.getString("team_evaluation"));
                crite.setCriteria_order(rs.getString("criteria_order"));
                crite.setMax_loc(rs.getString("max_loc"));
                crite.setStatus(rs.getInt("status"));
                crite.setIteration_name(rs.getString("iteration_name"));
                crite.setSubject_id(rs.getInt("subject_id"));
                crite.setSubject_code(rs.getString("subject_code"));
                return crite;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<Iteration> getIterationBySubject(int subject_id) {
        Vector<Iteration> vec = new Vector<>();
        String sql = "select *\n"
                + "                from iteration\n"
                + "             inner join subject on subject.subject_id = iteration.subject_id\n"
                + "                where subject.subject_id =" + subject_id;
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

    public int updateCriteria(Criteria temp) {
        int n = 0;
        String sql = "update evalution_criteria set iteration_id = ?, evalution_title = ?, \n"
                + "evalution_weight = ?, team_evaluation = ?, criteria_order = ?,max_loc = ? , status = ? \n"
                + "where evalution_criteria.criterial_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, temp.getIteration_id());
            pre.setString(2, temp.getEvalution_title());
            pre.setString(3, temp.getEvalution_weight());
            pre.setString(4, temp.getTeam_evaluation());
            pre.setString(5, temp.getCriteria_order());
            pre.setString(6, temp.getMax_loc());
            pre.setInt(7, temp.getStatus());
            pre.setInt(8, temp.getCriteria_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addCriteria(Criteria temp) {
        int n = 0;
        String sql = "INSERT INTO `evalution_criteria`\n"
                + "(`iteration_id`, `evalution_title`, `evalution_weight`, `team_evaluation`, `criteria_order`, `max_loc`, `status`)\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, temp.getIteration_id());
            pre.setString(2, temp.getEvalution_title());
            pre.setString(3, temp.getEvalution_weight());
            pre.setString(4, temp.getTeam_evaluation());
            pre.setString(5, temp.getCriteria_order());
            pre.setString(6, temp.getMax_loc());
            pre.setInt(7, temp.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Subject getSubject(int subject_id) {
        Subject temp = new Subject();
        String sql = "select subject.*, user.full_name from subject"
                + " inner join user on user.user_id = subject.author_id"
                + " where subject_id = " + subject_id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                temp.setSubject_id(rs.getInt("subject_id"));
                temp.setSubject_code(rs.getString("subject_code"));
                temp.setSubject_name(rs.getString("subject_name"));
                temp.setFull_name(rs.getString("full_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }
}
