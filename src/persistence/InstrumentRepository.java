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
        String query;
        PreparedStatement st = null;

        try {
            query = "INSERT INTO instrument values(?,?)";
            conn = DS.getConnection();
            st = conn.prepareStatement(query);
            st.setString(1,instrument.getName());
            st.setString(2,instrument.getSatellite());
            try {
                st.executeQuery();
                return true;
            } catch (SQLException e) {
                throw new AlreadyInDBException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate the driver. (Exception in InstrumentRepository.addInstrument()");
            return false;
        }
    }
}
