package persistence;

import entity.Instrument;
import exception.AlreadyInDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstrumentRepository {

    public boolean addInstrument(Instrument instrument) throws AlreadyInDBException{
        DataSource DS = new DataSource();
        Connection conn = null;
        String query1, query2;
        PreparedStatement st1,st2 = null;

        try {
            query1 = "INSERT INTO instrument values(?,?)";
            query2 = "UPDATE instrument SET satellite = ? WHERE name = ?";
            conn = DS.getConnection();
            st1 = conn.prepareStatement(query1);
            st1.setString(1,instrument.getName());
            st1.setString(2,instrument.getSatellite());
            try {
                st1.executeUpdate();
                return true;
            } catch (SQLException e) {
                st2 = conn.prepareStatement(query2);
                st2.setString(1,instrument.getSatellite());
                st2.setString(2,instrument.getName());
                st2.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate the driver. (Exception in InstrumentRepository.addInstrument()");
            return false;
        }
    }

    public boolean addBand(Double band, String instrument) throws AlreadyInDBException {

        DataSource DS = new DataSource();
        Connection conn = null;
        String query;
        PreparedStatement st = null;
        try {
            query = "INSERT INTO bands VALUES(?,?)";
            conn = DS.getConnection();
            st = conn.prepareStatement(query);
            st.setDouble(1,band);
            st.setString(2,instrument);
            try{
                st.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new AlreadyInDBException();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find the database driver. (Exception in InstrumentRepository.addBand())");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
