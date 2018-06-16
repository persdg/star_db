package persistence;

import entity.Filament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class REQs {

    private Connection conn;
    private ResultSet rs;
    private LinkedList<Filament> filaments;

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
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFilaments(int id) {
        try {

            String query1 =
                    "SELECT avg(glon), avg(glat), max(glon) - min(glon) AS height, max(glat) - min(glat) AS width " +
                    "FROM boundaries " +
                    "WHERE idfil = ? " +
                    "GROUP BY idfil";

            String query2 =
                    "SELECT count(*) AS count " +
                    "FROM skeletons " +
                    "WHERE idfil = ?";

            float avg_glon, avg_glat, height,width;
            PreparedStatement st1, st2;
            int count;

            connect();
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);
            st1.setInt(1,id);
            st2.setInt(1,id);
            rs = st1.executeQuery();
            if(rs.next())
            {
                avg_glon = rs.getFloat(1);
                avg_glat = rs.getFloat(2);
                height = rs.getFloat(3);
                width = rs.getFloat(4);
                rs = st2.executeQuery();
                rs.next();
                count = rs.getInt(1);
                System.out.println("Average galactic longitude -> " + avg_glon);
                System.out.println("Average galactic latitude -> " + avg_glat);
                System.out.println("Height -> " + height + "\tWidth -> " + width);
                System.out.println("This filament has " + count + " segments in his skeleton");
            } else {
                System.out.println("Filament not found.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFilaments(String name) {
        try {

            String query1 =
                    "SELECT idfil, avg(glon), avg(glat), max(glon) - min(glon) AS height, max(glat) - min(glat) AS width " +
                    "FROM boundaries NATURAL JOIN filaments " +
                    "WHERE name = ? " +
                    "GROUP BY idfil";

            String query2 =
                    "SELECT count(*) AS count " +
                    "FROM skeletons NATURAL JOIN filaments " +
                    "WHERE name = ? " +
                    "GROUP BY idfil";

            int idfil;
            float avg_glon, avg_glat, height, width;
            PreparedStatement st1, st2;
            int count;
            boolean found = false;

            connect();
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);
            st1.setString(1,name);
            st2.setString(1,name);
            rs = st1.executeQuery();
            while(rs.next()) //assumo che il nome non sia unique
            {
                found = true;
                idfil = rs.getInt(1);
                avg_glon = rs.getFloat(2);
                avg_glat = rs.getFloat(3);
                height = rs.getFloat(4);
                width = rs.getFloat(5);
                rs = st2.executeQuery();
                rs.next();
                count = rs.getInt(1);
                System.out.println("Id -> " + idfil);
                System.out.println("Average galactic longitude -> " + avg_glon);
                System.out.println("Average galactic latitude -> " + avg_glat);
                System.out.println("Height -> " + height + "\tWidth -> " + width);
                System.out.println("This filament has " + count + " segments in his skeleton");
            }
            if(!found) {
                System.out.println("Filament not found.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
