/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataConnection.getConnection;
import entity.ClassSetting;
import entity.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class DAOClassSetting extends DataConnection {

    Connection conn = getConnection();

    public Vector<ClassSetting> getClassSetting(int u_id, int role_id, int pageIndex, int pageSize, int class_id, int type_id, String label, String filter_col, String filter_type) {
        Vector<ClassSetting> vec = new Vector<>();

        String condition = " ";

        if (type_id != -1) {
            condition += " and cs.type_id = " + type_id;
        }
        if (label != null) {
            condition += " and cs.type_title like '%" + label + "%'";
        }
        //condition for sorting
        String filter_sort_condition = " ";
        if (filter_col != null) {
            filter_sort_condition += " order by " + filter_col + " " + filter_type;
        }

        String sql = "SELECT cs.*, c.class_code, s.setting_title FROM class_setting cs\n"
                + "inner join class c on c.class_id = cs.class_id\n"
                + "inner join setting s on s.setting_id = cs.type_id\n"
                + "where cs.class_id = " + class_id
                + condition
                + filter_sort_condition
                + " limit " + (pageIndex - 1) * pageSize + ", " + pageSize;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int class_setting_id = rs.getInt("class_setting_id");
                String _type_title = rs.getString("type_title");
                int _type_id = rs.getInt("type_id");
                String _color = rs.getString("color");
                String _description = rs.getString("description");
                int _class_id = rs.getInt("class_id");
                int type_value = rs.getInt("type_value");
                String class_code = rs.getString("class_code");
                String setting_title = rs.getString("setting_title");

                vec.add(new ClassSetting(class_setting_id, _type_id, _type_title, type_value, _color, _class_id, _description, class_code, setting_title));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int countClassSetting(int u_id, int role_id, int class_id, int type_id, String label) {
        String condition = " ";

        if (type_id != -1) {
            condition += " and cs.type_id = " + type_id;
        }
        if (label != null) {
            condition += " and cs.type_title like '%" + label + "%'";
        }

        String sql = "SELECT count(*) FROM class_setting cs\n"
                + "inner join class c on c.class_id = cs.class_id\n"
                + "inner join setting s on s.setting_id = cs.type_id\n"
                + "where cs.class_id = " + class_id
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

    public ClassSetting listAllByID(int class_setting_id) {
        Vector<ClassSetting> vec = new Vector<>();

        String sql = "SELECT cs.*, c.class_code, s.setting_title FROM class_setting cs\n"
                + "inner join class c on c.class_id = cs.class_id\n"
                + "inner join setting s on s.setting_id = cs.type_id\n"
                + "where cs.class_setting_id = " + class_setting_id;
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                class_setting_id = rs.getInt("class_setting_id");
                String _type_title = rs.getString("type_title");
                int type_id = rs.getInt("type_id");
                String _color = rs.getString("color");
                String _description = rs.getString("description");
                int _class_id = rs.getInt("class_id");
                int type_value = rs.getInt("type_value");
                String class_code = rs.getString("class_code");
                String setting_title = rs.getString("setting_title");

                return new ClassSetting(class_setting_id, type_id, _type_title, type_value, _color, _class_id, _description, class_code, setting_title);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int updateClassSetting(ClassSetting f) {

        int n = 0;
        try {
            String sql = "UPDATE `class_setting`\n"
                    + "SET\n"
                    + "`type_id` = ?,\n"
                    + "`type_title` = ?,\n"
                    + "`type_value` = ?,\n"
                    + "`color` = ?,"
                    + "`class_id` = ?,"
                    + "`description` = ?\n"
                    + "WHERE `class_setting_id` = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getType_id());
            ps.setString(2, f.getType_title());
            ps.setInt(3, f.getType_value());
            ps.setString(4, f.getColor());
            ps.setInt(5, f.getClass_id());
            ps.setString(6, f.getDescription());
            ps.setInt(7, f.getClass_setting_id());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return n;
    }

    public int addClassSetting(ClassSetting f) {
        int n = 0;
        String count_value = "select count(*) + 1"
                + " from class_setting where type_id = " + f.getType_id() + " and class_id = " + f.getClass_id();
        ResultSet rs = getData(count_value);
        int type_value = 0;
        try {
            if (rs.next()) {
                type_value = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql = "INSERT INTO `class_setting`( `type_id`,`type_title`,`color`, `class_id`, `description`, type_value) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getType_id());
            ps.setString(2, f.getType_title());
            ps.setString(3, f.getColor());
            ps.setInt(4, f.getClass_id());
            ps.setString(5, f.getDescription());
            ps.setInt(6, type_value);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ArrayList<ClassSetting> getAllClassSetting(int class_id) {
        ArrayList<ClassSetting> list = new ArrayList<>();
        String sql = "select * FROM class_setting where class_id= ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, class_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSetting class2 = new ClassSetting();
                class2.setClass_setting_id(rs.getInt("class_setting_id"));
                class2.setType_title(rs.getString("type_title"));
                class2.setType_id(rs.getInt("type_id"));
                class2.setColor(rs.getString("color"));
                class2.setDescription(rs.getString("description"));
                class2.setClass_id(rs.getInt("class_id"));
                class2.setType_value(rs.getInt("type_value"));
                list.add(class2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int syncClass(String group_id, String token, int class_id) {
        int n = 0;
        Vector<String> keys = new Vector<>();
        ArrayList<ClassSetting> list = getAllClassSetting(class_id);
        keys.add("name");
        keys.add("color");
        keys.add("description");
        Vector<String> labels_names = new Vector<>();
        JSONArray jsons = API.getJsonArray("groups", group_id, "labels", token);
        if (jsons != null) {
            for (int i = 0; i < jsons.length(); i++) {
                JSONObject json = jsons.getJSONObject(i);
                labels_names.add(json.get("name").toString().trim());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int index = labels_names.indexOf(list.get(i).getType_title());
            if (list.get(i).getType_id() != 9) {
                Vector<String> values = new Vector<>();
                values.add(list.get(i).getType_title());
                values.add(list.get(i).getColor());
                values.add(list.get(i).getDescription());
                if (index > -1) {
                    API.putApi("groups", group_id, "labels", keys, values, token);
                } else {
                    API.postApi("groups", group_id, "labels", keys, values, token);
                }
            }
            n++;
        }
        return n == list.size() ? 1 : 0;
    }

    public static void main(String[] args) {
        DAOClassSetting dao = new DAOClassSetting();
        int n = dao.syncClass("55253129", "glpat-BHfEsGqeyvhXhhi8myTo", 1);
        System.out.println(n);

    }
}
