package persistence;

import entity.FilamentIdName;
import entity.SegmentPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegmentRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<FilamentIdName> filamentIdNames;
    public ArrayList<SegmentPoint> Extremes;


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
                    "SELECT filaments.idfil, filaments.name " +
                            "FROM segments JOIN filaments ON (segments.idfil = filaments.idfil)" +
                            "GROUP BY filaments.idfil" +
                            "having COUNT(*) <= ? AND COUNT(*) >= ?";


            int id;
            String name;

            PreparedStatement st1;
            FilamentIdName FIN;

            connect();
            st1 = conn.prepareStatement(query1);
            st1.setInt(1,maxVal);
            st1.setInt(2,minVal);

            rs = st1.executeQuery();

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

    public ArrayList<SegmentPoint> segmentExtremes(int idbranch) {

        try {
            String query1 = "SELECT max(num_prog),glat,glon " + //max num prog
                    "FROM pos_segment " +
                    "WHERE idbranch = ? " +
                    "GROUP BY idbranch";

            String query2 = "SELECT min(num_prog),glat,glon " + //min num prog
                    "FROM pos_segment " +
                    "WHERE idbranch = ? " +
                    "GROUP BY idbranch";

            PreparedStatement st1;
            PreparedStatement st2;
            SegmentPoint SP;
            float glat, glon;

            connect();

            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);

            st1.setInt(1, idbranch);
            st2.setInt(1, idbranch);

            rs = st1.executeQuery();

            if (rs.next()) {
                glat = rs.getFloat(2);
                glon = rs.getFloat(3);
                SP = new SegmentPoint(glon, glat, 0, 0);
                Extremes.add(SP);
            }

            rs = st1.executeQuery();

            if (rs.next()) {
                glat = rs.getFloat(2);
                glon = rs.getFloat(3);
                SP = new SegmentPoint(glon, glat, 0, 0);
                Extremes.add(SP);
            }

            return Extremes; //list of 2 extremes of a segment


        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
