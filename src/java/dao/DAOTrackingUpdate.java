package dao;

import entity.Milestone;
import entity.TrackingUpdate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOTrackingUpdate extends DataConnection {

    public int addTrackingUpdate(TrackingUpdate trackingUpdate) {
        int n = 0;
        String sql = "INSERT INTO `update` (`tracking_id`, `milestone_id`, `Date`, `Note`) VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, trackingUpdate.getTracking_id());
            pre.setInt(2, trackingUpdate.getMilestone_id());
            pre.setString(3, trackingUpdate.getDate());
            pre.setString(4, trackingUpdate.getNote());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateTrackingUpdate(TrackingUpdate trackingUpdate) {
        int n = 0;
        String sql = "update `update` set note = '" + trackingUpdate.getNote() + "'"
                + " where update_id = " + trackingUpdate.getUpdate_id();
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteTrackingUpdate(int id) {
        int n = 0;
        String sql = "delete from `update` where update_id = " + id;
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<TrackingUpdate> getTrackingUpdate(int pageIndex, int pageSize, int tracking_id, int milestone_id, String note, String filter_col, String filter_type) {
        Vector<TrackingUpdate> vec = new Vector<>();
        String sql = "select `update`.*, milestone.milestone_name\n"
                + " from `update`\n"
                + " inner join tracking on `update`.tracking_id = tracking.tracking_id\n"
                + " inner join milestone on `update`.milestone_id = milestone.milestone_id"
                + " where `update`.tracking_id = " + tracking_id;

        if (milestone_id != -1) {
            sql += " and `update`.milestone_id = " + milestone_id;
        }

        if (note != null) {
            sql += " and `update`.note like '%" + note + "%'";
        }

        if (filter_col != null && filter_type != null) {
            sql += " order by " + filter_col + " " + filter_type;
        } else {
            sql += " order by `update`.date desc";
        }

        sql += " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;

        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                TrackingUpdate update = new TrackingUpdate();
                update.setUpdate_id(rs.getInt("update_id"));
                update.setTracking_id(rs.getInt("tracking_id"));
                update.setMilestone_id(rs.getInt("milestone_id"));
                update.setDate(rs.getString("date"));
                update.setNote(rs.getString("note"));
                update.setMilestone_name(rs.getString("milestone_name"));
                vec.add(update);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return vec;
    }
    
    public int countTrackingUpdate(int tracking_id, int milestone_id, String note, String filter_col, String filter_type) {
        Vector<TrackingUpdate> vec = new Vector<>();
        String sql = "select count(*) "
                + " from `update`\n"
                + " inner join tracking on `update`.tracking_id = tracking.tracking_id\n"
                + " inner join milestone on `update`.milestone_id = milestone.milestone_id"
                + " where `update`.tracking_id = " + tracking_id;

        if (milestone_id != -1) {
            sql += " and `update`.milestone_id = " + milestone_id;
        }

        if (note != null) {
            sql += " and `update`.note like '%" + note + "%'";
        }

        if (filter_col != null && filter_type != null) {
            sql += " order by " + filter_col + " " + filter_type;
        } else {
            sql += " order by `update`.date desc";
        }

        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return 0;
    }

    public String getAssignee_name(int tracking_id) {
        String sql = "select user.full_name\n"
                + "from tracking\n"
                + "inner join user on user.user_id = tracking.assignee_id\n"
                + "where tracking.tracking_id = " + tracking_id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Vector<Milestone> getMilestoneOfTeam(int tracking_id) {
        Vector<Milestone> vec = new Vector<>();
        String sql = "select milestone.*\n"
                + "from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join team on team.class_id = class.class_id\n"
                + "inner join tracking on tracking.team_id = team.team_id\n"
                + "where tracking.tracking_id = " + tracking_id;
        try {
            ResultSet rs = getData(sql);
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
}
