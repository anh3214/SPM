package dao;

/**
 *
 * @author RxZ
 */
import static dao.DataConnection.getConnection;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAODashboard extends DataConnection {
    
    public static int countUsers() {
        
        try {
            Connection conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select count(*) from user");
           
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            };

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public static int countClass() {
        
        try {
            Connection conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select count(*) from class");
           
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            };

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    
}
