package persistence;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Star;


public class StarRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<Star> starList;

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

    public ArrayList<Star> starsInfo() {

        try {

            String query1 =
                    "SELECT glon, glat, type " +
                            "FROM stars";

            String type;
            float glon, glat;
            PreparedStatement st;
            Star star;


            connect();
            st = conn.prepareStatement(query1);
            rs = st.executeQuery();

            while(rs.next()){
                glon = rs.getFloat(1);
                glat = rs.getFloat(2);
                type = rs.getString(3);

                star = new Star(0,null,type,glon,glat,0,0);

                starList.add(star);
            }

            return starList;

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
