package dao;

import entity.Iteration;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOIteration extends DataConnection {

    public int addIteration(Iteration iter) {
        int n = 0;
        String check = "select * from iteration where subject_id = "
                + iter.getSubject_id() + " and iteration_name = '"
                + iter.getIteration_name() + "'";
        try {
            if (getData(check).next()) {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String sql = "INSERT INTO iteration "
                + "(subject_id, "
                + "iteration_name, "
                + "duration, "
                + "status, "
                + "is_ongoin, "
                + "iteration_weight)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, iter.getSubject_id());
            pre.setString(2, iter.getIteration_name());
            pre.setInt(3, iter.getDuration());
            pre.setInt(4, iter.getStatus());
            pre.setInt(5, iter.getIs_ongoin());
            pre.setInt(6, iter.getIteration_weight());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateIteration(Iteration iter) {
        int n = 0;
        String check = "select * from iteration where subject_id = "
                + iter.getSubject_id() + " and iteration_name = '"
                + iter.getIteration_name() + "' and iteration_id <> "
                + iter.getIteration_id();
        try {
            if (getData(check).next()) {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String sql = "UPDATE iteration "
                + "SET iteration_name = ?, duration = ?, status = ?, is_ongoin = ?, iteration_weight = ? "
                + "WHERE iteration_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, iter.getIteration_name());
            pre.setInt(2, iter.getDuration());
            pre.setInt(3, iter.getStatus());
            pre.setInt(4, iter.getIs_ongoin());
            pre.setInt(5, iter.getIteration_weight());
            pre.setInt(6, iter.getIteration_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Iteration getIteration(int id) {
        String sql = "select iteration.*, subject.subject_code, subject.subject_name, user.user_id ,user.full_name\n"
                + " from iteration\n"
                + " inner join subject on subject.subject_id = iteration.subject_id\n"
                + " inner join user on user.user_id = subject.author_id where iteration.iteration_id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                Iteration temp = new Iteration();
                temp.setIteration_id(rs.getInt("iteration_id"));
                temp.setSubject_id(rs.getInt("subject_id"));
                temp.setIteration_name(rs.getString("iteration_name"));
                temp.setDuration(rs.getInt("duration"));
                temp.setStatus(rs.getInt("status"));
                temp.setIteration_weight(rs.getInt("iteration_weight"));
                temp.setIs_ongoin(rs.getInt("is_ongoin"));
                temp.setSubject_code(rs.getString("subject_code"));
                temp.setSubject_name(rs.getString("subject_name"));
                temp.setAuthor_id(rs.getInt("user_id"));
                temp.setAuthor_name(rs.getString("full_name"));
                return temp;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int editStatus(int id, int status) {
        int n = 0;
        String sql = "UPDATE iteration SET status = " + status + " WHERE iteration_id = " + id;
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int editOngoin(int id, int ongoin) {
        int n = 0;
        String sql = "UPDATE iteration SET is_ongoin = " + ongoin + " WHERE iteration_id = " + id;
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Iteration> queryIteration(int pageIndex, int pageSize, int subject_id, int status, int is_ongoin, String search, String filter_col, String filter_type) {
        Vector<Iteration> vec = new Vector<>();
        String sql = "select iteration.*, subject.subject_code, subject.subject_name, user.user_id ,user.full_name\n"
                + " from iteration\n"
                + " inner join subject on subject.subject_id = iteration.subject_id\n"
                + " inner join user on user.user_id = subject.author_id where 1 = 1 ";
        if (subject_id != -1) {
            sql += " and iteration.subject_id = " + subject_id;
        }

        if (status != -1) {
            sql += " and iteration.status = " + status;
        }
        if (search != null) {
            sql += " and iteration.iteration_name like '%" + search + "%'";
        }
        if (is_ongoin != -1) {
            sql += " and iteration.is_ongoin = " + is_ongoin;
        }

        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }
        sql += filter_sort_condition;
        sql += " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Iteration temp = new Iteration();
                temp.setIteration_id(rs.getInt("iteration_id"));
                temp.setSubject_id(rs.getInt("subject_id"));
                temp.setIteration_name(rs.getString("iteration_name"));
                temp.setDuration(rs.getInt("duration"));
                temp.setStatus(rs.getInt("status"));
                temp.setIteration_weight(rs.getInt("iteration_weight"));
                temp.setIs_ongoin(rs.getInt("is_ongoin"));
                temp.setSubject_code(rs.getString("subject_code"));
                temp.setSubject_name(rs.getString("subject_name"));
                temp.setAuthor_id(rs.getInt("user_id"));
                temp.setAuthor_name(rs.getString("full_name"));
                vec.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countIteration(int subject_id, int status, int is_ongoin, String search) {
        String sql = "select count(*)\n"
                + " from iteration\n"
                + " inner join subject on subject.subject_id = iteration.subject_id\n"
                + " inner join user on user.user_id = subject.author_id where 1 = 1 ";

        if (subject_id != -1) {
            sql += " and iteration.subject_id = " + subject_id;
        }

        if (status != -1) {
            sql += " and iteration.status = " + status;
        }
        if (search != null) {
            sql += " and iteration.iteration_name like '%" + search + "%'";
        }
        if (is_ongoin != -1) {
            sql += " and iteration.is_ongoin = " + is_ongoin;
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

    public Subject getSubjectInfo(int subject_id) {
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
