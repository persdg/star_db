package persistence;

import entity.FilamentIdName;
import entity.Segment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegmentRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<FilamentIdName> filamentIdNames;


    private void connect() throws ClassNotFoundException, SQLException{
        DataSource dataSource = new DataSource();
        conn = dataSource.getConnection();
    }

    private void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<FilamentIdName> SegmentNumber(int minVal, int maxVal){

        try {
            String query1 =
                    "CREATE VIEW NSEGMENTS(idfil,num)" +
                    "SELECT idfil, count(*)" +
                            "FROM segments " +
                            "GROUP BY idfil" +
                            "HAVING num <= ? && num >= ?";

            String query2 =
                    "SELECT idfil, name " +
                            "FROM filaments NATURAL JOIN NSEGMENTS";

            int id;
            String name;

            PreparedStatement st1;
            PreparedStatement st2;
            FilamentIdName FIN;

            connect();
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);
            st1.setInt(1,maxVal);
            st1.setInt(2,minVal);

            rs = st2.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);
                name = rs.getString(2);

                FIN = new FilamentIdName(id, name);
                filamentIdNames.add(FIN);
            }
            return filamentIdNames;

    } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

}
}
