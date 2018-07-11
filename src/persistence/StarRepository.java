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
    public ArrayList<Star> starList = new ArrayList<Star>();
    public double[] starsRectValues = new double[3]; //1st num of protostellars in rect, 2nd num of prestellars in rect,
    //3rd num of unbounds in rect


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
                    "SELECT glon, glat, type, idstar, flux " +
                            "FROM stars " +
                            "ORDER BY flux";

            String type;
            float glon, glat;
            double flux;
            int id;
            PreparedStatement st;
            Star star;


            connect();
            st = conn.prepareStatement(query1);
            rs = st.executeQuery();

            while(rs.next()){
                glon = rs.getFloat(1);
                glat = rs.getFloat(2);
                type = rs.getString(3);
                id = rs.getInt(4);
                flux = rs.getDouble(5);

                star = new Star(id,null,type,glon,glat,0,flux);

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

    public double[] starsInRect(float glat,float glon, float basis, float height){

        float topSide = glat + height/2;
        float botSide = glat - height/2;
        float leftSide = glon - basis/2;
        float rightSide = glon + basis/2;

        try{
            String query1 = //stars in rect with ID and type
                    "CREATE VIEW StarsInRect(idstar,type) AS "+
                            "SELECT idstar,type "+
                            "FROM stars "+
                            "WHERE glat <= ? AND glat >= ? AND glon <= ? AND glon >= ?";

            String query2 = //number of stars in rect per type
                    "SELECT COUNT(*) "+
                            "FROM StarsInRect "+
                            "WHERE type = ?";

            PreparedStatement st1,st2;

            connect();

            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);


            st1.setFloat(1,topSide);
            st1.setFloat(2,botSide);
            st1.setFloat(3,rightSide);
            st1.setFloat(4,leftSide);

            st2.setString(1,"PROTOSTELLAR");//number of protostellars in rect

            rs = st2.executeQuery();

            if(rs.next()){starsRectValues[0] = rs.getDouble(1);}

            st2.setString(1,"PRESTELLAR");//number of  in rect

            rs = st2.executeQuery();

            if(rs.next()){starsRectValues[1] = rs.getDouble(1);}

            st2.setString(1,"UNBOUND");//number of unbounds in rect

            rs = st2.executeQuery();

            if(rs.next()){starsRectValues[2] = rs.getDouble(1);}

            return starsRectValues;

        }catch (ClassNotFoundException e) {
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
