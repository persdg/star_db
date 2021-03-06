package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import entity.BoundaryPoint;

public class BoundaryRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<BoundaryPoint> boundaryList = new ArrayList<BoundaryPoint>();

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

    public ArrayList<BoundaryPoint> filamentBoundary(int id){

        try {

            String query1 =
                    "SELECT glon, glat  " +
                            "FROM boundaries "+
                            "WHERE idfil = ?";

            float glon, glat;
            PreparedStatement st;
            BoundaryPoint BP;

            connect();
            st = conn.prepareStatement(query1);
            st.setInt(1,id);
            rs = st.executeQuery();

            while(rs.next()){
                glon = rs.getFloat(1);
                glat = rs.getFloat(2);

                BP = new BoundaryPoint(glon,glat);

                boundaryList.add(BP);
            }

            return boundaryList;

        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnect();
        }
    }

    public ArrayList<BoundaryPoint> segmentBoundary(int idbranch){

        try{

            String query =

                    "SELECT boundaries.glon, boundaries.glat " +
                    "FROM segments JOIN boundaries ON (segments.idfil = boundaries.idfil)" +
                    "WHERE segments.idbranch = ?";

            float glon,glat;
            PreparedStatement st;
            BoundaryPoint BP;

            connect();
            st = conn.prepareStatement(query);
            st.setInt(1,idbranch);

            rs = st.executeQuery();

            while(rs.next()){

                glon = rs.getFloat(1);
                glat = rs.getFloat(2);

                BP = new BoundaryPoint(glon,glat);

                boundaryList.add(BP);
            }

            return boundaryList;


        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnect();
        }


    }

    }


