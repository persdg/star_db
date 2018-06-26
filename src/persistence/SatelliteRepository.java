package persistence;

import entity.Satellite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SatelliteRepository {


    public boolean addSatellite(Satellite satellite) {

        DataSource DS;
        Connection conn = null;
        String query;
        PreparedStatement st = null;

        try {
            DS = new DataSource();
            conn = DS.getConnection();
            query = "INSERT INTO satellite VALUES(?,?,?)";
            st = conn.prepareStatement(query);
            st.setString(1,satellite.getName());
            st.setDate(2, satellite.getFirstObservation());
            st.setDate(3,satellite.getLastObservation());
            st.executeUpdate();
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate driver. (Exception in SatelliteRepository.addSatellite())");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                System.out.println("Exception in disconnect() in SatelliteRepository: Couldn't close connection.");
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception in disconnect() in SatelliteRepository: Couldn't close prepared statement.");
            }
        }
    }

    public boolean addAgency(String agency, String satelliteName) {

        DataSource DS;
        Connection conn = null;
        String query;
        PreparedStatement st = null;

        try {
            DS = new DataSource();
            conn = DS.getConnection();
            query = "INSERT INTO agency VALUES(?,?)";
            st = conn.prepareStatement(query);
            st.setString(1,agency);
            st.setString(2,satelliteName);
            try {
                st.executeQuery();
                return true;
            } catch(SQLException e) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate driver. (Exception in SatelliteRepository.addAgency()");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
