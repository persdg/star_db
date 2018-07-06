package persistence;

import entity.Filament;
import entity.FilamentIdName;
import entity.FilamentInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FilamentRepository {

    private Connection conn;
    private ResultSet rs;
    private ArrayList<Filament> filaments;
    public ArrayList<FilamentIdName> filamentIdNames;

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

    public FilamentInfo searchFilaments(int id) {
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
            FilamentInfo FI;

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

                FI = new FilamentInfo(avg_glat,avg_glon,height,width,count);

                return FI;

            } else {
                System.out.println("Filament not found.");
                return null;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            disconnect();
        }
    }

    public FilamentInfo searchFilaments(String name) {
        try {

            String query1 =
                    "SELECT avg(glon), avg(glat), max(glon) - min(glon) AS height, max(glat) - min(glat) AS width " +
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
            FilamentInfo FI;

            connect();
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);
            st1.setString(1,name);
            st2.setString(1,name);
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

                FI = new FilamentInfo(avg_glat, avg_glon, height, width, count);

                return FI;

            }
            else {
                System.out.println("Filament not found.");
                return null;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<FilamentIdName> contrastEllipticityFilament(int minEllipticity, int maxEllipticity, double contrast){
        try {
            String query1 =
                    "SELECT id, name" +
                            "FROM filaments" +
                            "WHERE ellipticity > ? && ellipticity < ? && contrast > ?";

            int id;
            String name;
            PreparedStatement st1;
            FilamentIdName FIN;

            connect();
            st1 = conn.prepareStatement(query1);
            st1.setInt(1,minEllipticity);
            st1.setInt(2,maxEllipticity);
            st1.setDouble(3,contrast);

            rs = st1.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);
                name = rs.getString(2);

                FIN = new FilamentIdName(id, name);
                filamentIdNames.add(FIN);
            }
                return filamentIdNames;
            }
        catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



    }
}
