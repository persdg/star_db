package persistence;

import entity.Filament;
import entity.SegmentPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegmentRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<Filament> filaments = new ArrayList<>();
    public ArrayList<SegmentPoint> segPointList = new ArrayList<>();


    private void connect() throws ClassNotFoundException, SQLException {
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

    public ArrayList<Filament> SegmentNumber(int minVal, int maxVal) {

        try {
            String query1 =
                    "SELECT filaments.idfil, filaments.name " +
                            "FROM segments JOIN filaments ON (segments.idfil = filaments.idfil)" +
                            "GROUP BY filaments.idfil, filaments.name " +
                            "having COUNT(*) <= ? AND COUNT(*) >= ?";


            int id;

            PreparedStatement st1;
            Filament FI;

            connect();
            st1 = conn.prepareStatement(query1);
            st1.setInt(1, maxVal);
            st1.setInt(2, minVal);

            rs = st1.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);


                FI = new Filament(id, null, 0, 0, 0, 0,
                        null, null);

                filaments.add(FI);
            }
            return filaments;

        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /*
    * RETURN: 2 segmentPoints, the first one is the MAX, the last one is the MIN
     */

    public ArrayList<SegmentPoint> segmentExtremes(int idbranch) {

        try {
            String query1 = "SELECT glon, glat " + //max num prog
                    "FROM pos_segment " +
                    "WHERE idbranch = ? AND prog_num " +
                    "IN " +
                    "(SELECT max(prog_num) " +
                    "FROM pos_segment " +
                    "WHERE idbranch = ? " +
                    "GROUP BY idbranch)";

            String query2 = "SELECT glon, glat " + //min num prog
                    "FROM pos_segment " +
                    "WHERE idbranch = ? AND prog_num " +
                    "IN " +
                    "(SELECT min(prog_num) " +
                    "FROM pos_segment " +
                    "WHERE idbranch = ? " +
                    "GROUP BY idbranch)";

            PreparedStatement st1;
            PreparedStatement st2;
            SegmentPoint SP;
            float glat, glon;

            connect();

            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);

            st1.setInt(1, idbranch);
            st1.setInt(2, idbranch);
            st2.setInt(1, idbranch);
            st2.setInt(2, idbranch);

            rs = st1.executeQuery();

            if (rs.next()) {
                glat = rs.getFloat(1);
                glon = rs.getFloat(2);
                SP = new SegmentPoint(glon, glat, 0, 0);
                segPointList.add(SP);
            }

            rs = st2.executeQuery();

            if (rs.next()) {
                glat = rs.getFloat(1);
                glon = rs.getFloat(2);
                SP = new SegmentPoint(glon, glat, 0, 0);
                segPointList.add(SP);
            }

            return segPointList; //list of 2 extremes of a segment


        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<SegmentPoint> skeletonSegmentPointInFil(int idfil) {

        try {
            String query =
                    "SELECT pos_segment.glon, pos_segment.glat " +
                            "FROM segments JOIN pos_segment ON (segments.idbranch = pos_segment.idbranch)" +
                            "WHERE segments.idfil = ? AND segments.type = ?";

            PreparedStatement st;
            SegmentPoint SP;

            float glat, glon;

            connect();

            st = conn.prepareStatement(query);

            st.setInt(1,idfil);
            st.setString(2,"S");

            rs = st.executeQuery();

            while(rs.next()){

                glon = rs.getFloat(1);
                glat = rs.getFloat(2);
                SP = new SegmentPoint(glon, glat, 0, 0);
                segPointList.add(SP);
            }

            return segPointList;



        }catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
