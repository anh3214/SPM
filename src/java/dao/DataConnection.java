/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ptuan
 */
public class DataConnection {

    private static Connection con;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static Connection getConnection() {
        if (con == null) {
            URL = "jdbc:mysql://localhost:3306/student_project_managerment?useSSL=false&allowPublicKeyRetrieval=true";
            USER = "sa";
            PASSWORD = "123456";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // driver register
                con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (con);
    }

    public ResultSet getData(String sql) {
        Connection c = getConnection();
        ResultSet rs = null;
        try {
            Statement state = c.createStatement();
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
        if (c == null) {
            System.out.println("something wrong");
        } else {
            System.out.println("ok");
        }
    }
}
