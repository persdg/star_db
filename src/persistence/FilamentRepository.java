package persistence;

import entity.Filament;
import entity.FilamentIdName;
import entity.FilamentInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilamentRepository {

    private Connection conn;
    private ResultSet rs;
    public ArrayList<Filament> filaments;
    public ArrayList<FilamentIdName> filamentIdNames;
    private float topSide,botSide,rightSide,leftSide;
    private ArrayList<Integer> IDs = new ArrayList<>();

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
    public ArrayList<FilamentIdName> contrastEllipticityFilament(int minEllipticity, int maxEllipticity, double contrast) {
        try {
            String query1 =
                    "SELECT id, name " +
                            "FROM filaments " +
                            "WHERE ellipticity > ? AND ellipticity < ? AND contrast > ?";

            int id;
            String name;
            PreparedStatement st1;
            FilamentIdName FIN;

            connect();
            st1 = conn.prepareStatement(query1);
            st1.setInt(1, minEllipticity);
            st1.setInt(2, maxEllipticity);
            st1.setDouble(3, contrast);

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

    public List<Integer>  scanCircle(float glat, float glon, float radius){


            try {
                String query =
                        "SELECT idfil " +
                                "FROM boundaries " +
                                "EXCEPT " +
                                "SELECT idfil " +
                                "FROM boundaries " +
                                "WHERE SQRT((glat - ?)^2 + (glon - ?)^2) >= ?";
                PreparedStatement st;

                connect();

                st = conn.prepareStatement(query);

                st.setDouble(1, glat);
                st.setDouble(2, glon);
                st.setDouble(3, radius);

                rs = st.executeQuery();

                while (rs.next()) {
                    IDs.add(rs.getInt(1));
                }

                return IDs;

            } catch (SQLException e) {
                e.printStackTrace();
                return IDs;
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't locate the database driver.");
                return IDs;
            } finally {
                disconnect();
            }
        }

        public List<Integer> scanSquare(float glat, float glon, float side) {

                topSide = glat + side/2;
                botSide = glat - side/2;
                leftSide = glon - side/2;
                rightSide = glon + side/2;



                try {
                    String query =
                            "SELECT idfil " +
                                    "FROM boundaries " +
                                    "EXCEPT " +
                                    "SELECT idfil " +
                                    "FROM boundaries " +
                                    "WHERE glat >= ? OR glat <= ? OR glon >= ? OR glon <= ?";

                    PreparedStatement st;

                    connect();

                    st = conn.prepareStatement(query);

                    st.setDouble(1, topSide);
                    st.setDouble(2, botSide);
                    st.setDouble(3, rightSide);
                    st.setDouble(4,leftSide);

                    rs = st.executeQuery();

                    while(rs.next()) {
                        IDs.add(rs.getInt(1));
                    }

                    return IDs;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return IDs;
                } catch (ClassNotFoundException e) {
                    System.out.println("Couldn't locate the database driver.");
                    return IDs;
                } finally {
                    disconnect();
                }
            }


            public ArrayList<Filament> filamentInRect(float glat,float glon, float basis,float height){

                topSide = glat + height/2;
                botSide = glat - height/2;
                leftSide = glon - basis/2;
                rightSide = glon + basis/2;

                Filament FI;
                int id;

                try {

                    String query1 =
                            "CREATE VIEW pointsInBoundary(ID,TotPoints) AS "+//number of boundary's points per filament
                                    "SELECT idfil, count(*) "+
                                    "FROM boundaries "+
                                    "GROUP BY idfil";

                    String query2 =
                            "CREATE VIEW pointsInRect(ID,RectPoints) AS "+//num of boundary's points
                                    "SELECT idfil, count(*) "+//per filament in rect
                                    "FROM boundaries "+
                                    "WHERE glat <= ? AND glat >= ? AND glon <= ? AND glon >= ? "+
                                    "GROUP BY idfil";

                    String query3 =
                            "SELECT ID "+//list of filament's IDs completely contained in the rect
                                    "FROM pointsInBoundary JOIN pointsInRect "+
                                    "ON (pointsInBoundary.TotPoints = pointsInRect.RectPoints)";

                    PreparedStatement st1,st2,st3;

                    connect();

                    st1 = conn.prepareStatement(query1);
                    st2 = conn.prepareStatement(query2);
                    st3 = conn.prepareStatement(query3);

                    st2.setFloat(1,topSide);
                    st2.setFloat(2,botSide);
                    st2.setFloat(3,rightSide);
                    st2.setFloat(4,leftSide);

                    rs = st3.executeQuery();

                    while(rs.next()){

                        id = rs.getInt(1);

                        FI = new Filament(id,null,0,0,0,0,null,null);
                        filaments.add(FI);

                    }

                    return filaments;//list of IDs



                }catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                } catch (ClassNotFoundException e) {
                    System.out.println("Couldn't locate the database driver.");
                    return null;
                } finally {
                    disconnect();
                }
                }


            }








