package dao;

import static dao.DataConnection.getConnection;
import entity.Setting;
import entity.SubjectSetting;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ptuan
 */
public class DAOSetting extends DataConnection {

    Connection conn = getConnection();

    public Vector<Setting> viewAll() {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT * FROM setting";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_tile = rs.getString(3);
                String setting_value = rs.getString(4);
                String display_order = rs.getString(5);
                int status = rs.getInt(6);
                vector.add(new Setting(setting_id, type_id, setting_tile, setting_value, display_order, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<String> viewAllType() {
        Vector<String> vector = new Vector<>();
        String sql = "SELECT distinct type_id FROM setting;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString(1);
                vector.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int countSettingType(int s) {
        String sql = "SELECT count(setting_id) setting where type_id = ?";
        int count = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public Vector<Setting> readSettingType(int s, int first, int last) {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT * FROM setting where type_id = ? Limit ?,?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s);
            pre.setInt(2, first);
            pre.setInt(3, last);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_tile = rs.getString(3);
                String setting_value = rs.getString(4);
                String display_order = rs.getString(5);
                int status = rs.getInt(6);
                vector.add(new Setting(setting_id, type_id, setting_tile, setting_value, display_order, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int countSettingSta(int s) {
        String sql = "SELECT count(setting_id) FROM setting where status = ?";
        int count = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public Vector<Setting> readSettingSta(int s, int first, int last) {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT * FROM setting where status = ? Limit ?,?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s);
            pre.setInt(2, first);
            pre.setInt(3, last);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_tile = rs.getString(3);
                String setting_value = rs.getString(4);
                String display_order = rs.getString(5);
                int status = rs.getInt(6);
                vector.add(new Setting(setting_id, type_id, setting_tile, setting_value, display_order, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int countSettingName(String s) {
        String sql = "SELECT count(setting_id) FROM setting where setting_title like  '%" + s + "%'";
        int count = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public Vector<Setting> readSettingName(String s, int first, int last) {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT * FROM setting where setting_title like '%" + s + "%' Limit ? ,?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, first);
            pre.setInt(2, last);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_tile = rs.getString(3);
                String setting_value = rs.getString(4);
                String display_order = rs.getString(5);
                int status = rs.getInt(6);
                vector.add(new Setting(setting_id, type_id, setting_tile, setting_value, display_order, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int deleteSetting(int id, int status) {
        int n = 0;
        String sql;
        if (status == 0) {
            sql = "UPDATE `setting` SET `status` = 1 WHERE `setting_id` = " + id + ";";
        } else {
            sql = "UPDATE `setting` SET status = 0 WHERE `setting_id` = " + id + ";";
        }
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateSetting(Setting s) {
        int n = 0;
        try {
            String sql = "UPDATE `setting`\n"
                    + "SET\n"
                    + "`type_id` = ?,\n"
                    + "`setting_title` = ?,\n"
                    + "`setting_value` = ?,\n"
                    + "`display_order` = ?,\n"
                    + "`status` = ?\n"
                    + "WHERE `setting_id` = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getType_id());
            ps.setString(2, s.getSetting_title());
            ps.setString(3, s.getSetting_value());
            ps.setString(4, s.getDisplay_order());
            ps.setInt(5, s.getStatus());
            ps.setInt(6, s.getSetting_id());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Setting listAll(int setting_id) {
        String sql = "select * from setting where setting_id = '" + setting_id + "'";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_title = rs.getString(3);
                String display_order = rs.getString(4);
                String setting_value = rs.getString(5);
                int status = rs.getInt(6);
                return new Setting(setting_id, type_id, setting_title, display_order, setting_value, status);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int addSetting(Setting s) {
        int n = 0;
        String sql = "INSERT INTO `setting`\n"
                + "(`type_id`,\n"
                + "`setting_title`,\n"
                + "`setting_value`,\n"
                + "`display_order`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setInt(1, s.getSetting_id());
            pre.setInt(1, s.getType_id());
            pre.setString(2, s.getSetting_title());
            pre.setString(3, s.getSetting_value());
            pre.setString(4, s.getDisplay_order());
            pre.setInt(5, s.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Vector<Setting> getSetting(int a, int b) {
        Vector<Setting> vector = new Vector();
        String sql = "SELECT * FROM setting Limit ?,?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, a);
            stmt.setInt(2, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int type_id = rs.getInt(2);
                String setting_tile = rs.getString(3);
                String setting_value = rs.getString(4);
                String display_order = rs.getString(5);
                int status = rs.getInt(6);
                vector.add(new Setting(setting_id, type_id, setting_tile, setting_value, display_order, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    public int getCountSetting() {
        String sql = "SELECT count(setting_id) FROM setting";
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    
    
    
    
    
    // DAo Subject setting
    
    public Vector<SubjectSetting> viewAllSubjectSetting(int a, int b) {
        Vector<SubjectSetting> vector = new Vector();
        String sql = "SELECT * FROM subject_setting order by subject_id asc limit ?,?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, a);
            pre.setInt(2, b);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int subject_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                String title = rs.getString(4);
                String value = rs.getString(5);
                String order = rs.getString(6);
                int status = rs.getInt(7);
                SubjectSetting s = new SubjectSetting(id, subject_id, type_id, title, value, order, status);
                vector.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<SubjectSetting> searchSubjectSetting(int id) {
        Vector<SubjectSetting> vector = new Vector();
        String sql = "SELECT * FROM subject_setting where setting_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id2 = rs.getInt(1);
                int subject_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                String title = rs.getString(4);
                String value = rs.getString(5);
                String order = rs.getString(6);
                int status = rs.getInt(7);
                SubjectSetting s = new SubjectSetting(id2, subject_id, type_id, title, value, order, status);
                vector.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int addSubjectSetting(SubjectSetting s) {
        String sql = "INSERT INTO `subject_setting`\n"
                + "(`subject_id`,`type_id`,`setting_title`,`setting_value`,`display_order`,`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s.getSubject_id());
            pre.setInt(2, s.getType_id());
            pre.setString(3, s.getSetting_tile());
            pre.setString(4, s.getSetting_value());
            pre.setString(5, s.getDisplay_order());
            pre.setInt(6, s.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteSubjectSetting(int id, int status) {
        int n = 0;
        String sql;
        if (status == 0) {
            sql = "UPDATE `subject_setting` SET`status` = 1 WHERE `setting_id` = " + id;
        } else {
            sql = "UPDATE `subject_setting` SET`status` = 0 WHERE `setting_id` =" + id;
        }
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateSubjectSetting(SubjectSetting s) {
        String sql = "UPDATE `subject_setting`\n"
                + "SET\n"
                + "`subject_id` = ?, `type_id` = ? , `setting_title` = ?, `setting_value` = ? , `display_order` = ? , `status` = ? WHERE `setting_id` = ?";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s.getSubject_id());
            pre.setInt(2, s.getType_id());
            pre.setString(3, s.getSetting_tile());
            pre.setString(4, s.getSetting_value());
            pre.setString(5, s.getDisplay_order());
            pre.setInt(6, s.getStatus());
            pre.setInt(7, s.getSetting_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int getCountSubjectSetting() {
        String sql = "SELECT count(setting_id) FROM subject_setting";
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Vector<String> viewAllTypeSetting() {
        Vector<String> vector = new Vector<>();
        String sql = "select distinct s.type_id from setting s";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString(1);
                vector.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector<String> viewAllTypeSubjectSetting() {
        Vector<String> vector = new Vector<>();
        String sql = "select distinct type_id from subject_setting";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString(1);
                vector.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<SubjectSetting> sortSettingSubject(String by, int a, int b) {
        Vector<SubjectSetting> vector = new Vector<>();
        String sql = "SELECT * FROM  subject_setting ORDER BY " + by + " limit ?,?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, a);
            pre.setInt(2, b);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int subject_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                String title = rs.getString(4);
                String value = rs.getString(5);
                String order = rs.getString(6);
                int status = rs.getInt(7);
                SubjectSetting s = new SubjectSetting(id, subject_id, type_id, title, value, order, status);
                vector.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int queryUsersCount(int type, int status) {
        String condition = ""; // search all
        int n =0;
        String sql ="";
        if (type != -1) {
            sql = "select count(*) from subject_setting where type_id = "+type;
        }
        if (status != -1) {
           sql = "select count(*) from subject_setting where status = "+status;
        }
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Vector<SubjectSetting> querySubjectSettings(int start, int total, int type, int status) {
        Vector<SubjectSetting> vector = new Vector<>();
        String condition =""; // search all
        if (type != -1) {
            condition = " type_id = ?";
        }
        if (status != -1) {
            condition = " status = ?";
        }
        String sql = "select * from subject_setting where " + condition + " limit " + start + ", " + total;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            int _curIndex = 1;
            if (condition.contains("type_id")) {
                pre.setInt(_curIndex, type);
                _curIndex += 1;
            }
            if (condition.contains("status")) {
                pre.setInt(_curIndex, status);
                _curIndex += 1;
            }
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int subject_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                String setting_tile = rs.getString(4);
                String setting_value = rs.getString(5);
                String display_order = rs.getString(6);
                int statu2s = rs.getInt(7);
                vector.add(new SubjectSetting(setting_id, subject_id, type_id, setting_tile, setting_value, display_order, statu2s));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    public Vector<SubjectSetting> searcsubject(String subject) {
        Vector<SubjectSetting> vector = new Vector<>();
        String sql = "SELECT * FROM subject_setting s join subject se on s.subject_id = se.subject_id"
                + " where se.subject_name like '%"+subject+"%'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                int subject_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                String setting_tile = rs.getString(4);
                String setting_value = rs.getString(5);
                String display_order = rs.getString(6);
                int statu2s = rs.getInt(7);
                vector.add(new SubjectSetting(setting_id, subject_id, type_id, setting_tile, setting_value, display_order, statu2s));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public int countSubj(String subject) {
        int n=0;
        String sql = "SELECT count(*) FROM subject_setting s join subject se on s.subject_id = se.subject_id"
                + " where se.subject_name like '%"+subject+"%'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
}
