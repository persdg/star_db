package persistence;

import entity.Satellite;
import exception.AlreadyInDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SatelliteRepository {


    public boolean addSatellite(Satellite satellite) {

        DataSource DS;
        Connection conn = null;
        String query1, query2;
        PreparedStatement st1 = null, st2 = null;

        try {
            DS = new DataSource();
            conn = DS.getConnection();
            query1 = "INSERT INTO satellite VALUES(?,?,?)";
            query2 = "UPDATE satellite SET firstobv = ?, lastobv = ? WHERE name = ?";
            st1 = conn.prepareStatement(query1);
            st1.setString(1,satellite.getName());
            st1.setDate(2, satellite.getFirstObservation());
            st1.setDate(3,satellite.getLastObservation());
            try {
                st1.executeUpdate();
                return true;
            } catch(SQLException e) {
                st2 = conn.prepareStatement(query2);
                st2.setDate(1, satellite.getFirstObservation());
                st2.setDate(2,satellite.getLastObservation());
                st2.setString(3,satellite.getName());
                st2.executeUpdate();
                return true;
            }
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
                if (st1 != null) {
                    st1.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception in disconnect() in SatelliteRepository: Couldn't close prepared statement.");
            }
            try {
                if (st2 != null) {
                    st2.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception in disconnect() in SatelliteRepository: Couldn't close prepared statement.");
            }
        }
    }

    public boolean addAgency(String agency, String satelliteName) throws AlreadyInDBException {

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
                st.executeUpdate();
                return true;
            } catch(SQLException e) {
                throw new AlreadyInDBException();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate driver. (Exception in SatelliteRepository.addAgency()");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        finally {
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
}
