package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileImporter {

    private Connection conn = null;
    private PreparedStatement st1, st2, st3, st12, st22;
    private BufferedReader br = null;
    private String line;
    private final String cvsSplitBy = ",";
    private String query1, query2, query3, query12, query22;

    private void connect() throws ClassNotFoundException, SQLException{

        DataSource dataSource = new DataSource();
        conn = dataSource.getConnection();
    }

    private void disconnect() {

        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (st1 != null) {
            try {
                st1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st2 != null) {
            try {
                st2.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st3 != null) {
            try {
                st3.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clear(String table) throws SQLException {
        query1 = "DELETE from " + table;
        st1 = conn.prepareStatement(query1);
        st1.executeUpdate();
    }

    private void importFilaments() throws SQLException{

        try {
            query1 = "INSERT INTO filaments VALUES(?,?,?,?,?,?,?,?,?)";
            query2 = "UPDATE filaments SET name = ?, total_flux = ?, mean_dens = ?, mean_temp = ?, " +
                    "ellipticity = ?, contrast = ?, satellite = ?, instrument = ? WHERE idfil = ?";
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);

            while ((line = br.readLine()) != null) {
                String[] filament = line.split(cvsSplitBy);
                st1.setInt(1, Integer.parseInt(filament[0]));
                st1.setString(2, filament[1]);
                st1.setDouble(3, Double.parseDouble(filament[2]));
                st1.setDouble(4, Double.parseDouble(filament[3]));
                st1.setFloat(5, Float.parseFloat(filament[4]));
                st1.setFloat(6, Float.parseFloat(filament[5]));
                st1.setFloat(7, Float.parseFloat(filament[6]));
                st1.setString(8, filament[7]);
                st1.setString(9, filament[8]);
                try {
                    st1.executeUpdate();
                } catch (SQLException e) {
                    st2.setString(1, filament[1]);
                    st2.setDouble(2, Double.parseDouble(filament[2]));
                    st2.setDouble(3, Double.parseDouble(filament[3]));
                    st2.setFloat(4, Float.parseFloat(filament[4]));
                    st2.setFloat(5, Float.parseFloat(filament[5]));
                    st2.setFloat(6, Float.parseFloat(filament[6]));
                    st2.setString(7, filament[7]);
                    st2.setString(8, filament[8]);
                    st2.setInt(9,Integer.parseInt(filament[0]));
                    st2.executeUpdate();
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected I/O exception.");
        }
    }

    private void importBoundaries() throws SQLException {

        try {

            query1 = "INSERT INTO boundaries VALUES(?,?,?)";
            st1 = conn.prepareStatement(query1);

            while((line = br.readLine()) != null) {
                String[] boundary  = line.split(cvsSplitBy);
                st1.setInt(1, Integer.parseInt(boundary[0]));
                st1.setFloat(2, Float.parseFloat(boundary[1]));
                st1.setFloat(3, Float.parseFloat(boundary[2]));
                try {
                    st1.executeUpdate();
                } catch (SQLException e) {
                    //do nothing
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected I/O exception.");
        }
    }

    private void importStars() throws SQLException {
        try {
            query1 = "INSERT INTO stars VALUES(?,?,?,?,?,?)";
            query2 = "UPDATE stars SET name = ?, glon = ?, glat = ?, flux = ?, type = ? WHERE idstar = ?";
            st1 = conn.prepareStatement(query1);
            st2 = conn.prepareStatement(query2);

            while((line = br.readLine()) != null) {
                String[] star = line.split(cvsSplitBy);
                st1.setInt(1,Integer.parseInt(star[0]));
                st1.setString(2,star[1]);
                st1.setFloat(3,Float.parseFloat(star[2]));
                st1.setFloat(4,Float.parseFloat(star[3]));
                st1.setFloat(5,Float.parseFloat(star[4]));
                st1.setString(6,star[5]);
                try {
                    st1.executeUpdate();
                } catch (SQLException e) {
                    st2.setString(1,star[1]);
                    st2.setFloat(2,Float.parseFloat(star[2]));
                    st2.setFloat(3,Float.parseFloat(star[3]));
                    st2.setFloat(4,Float.parseFloat(star[4]));
                    st2.setString(5,star[5]);
                    st2.setInt(6,Integer.parseInt(star[0]));
                    st2.executeUpdate();
                }
            }
        }
        catch(IOException e) {
            System.out.println("Unexpected I/O exception.");
        }
    }

    private void importSkeletons() throws SQLException {

        try {
            query1 = "INSERT INTO skeletons VALUES(?,?,?)";
            query12 = "UPDATE skeletons SET idfil = ?, type = ? WHERE idbranch = ?";
            query2 = "INSERT INTO pos_segment VALUES(?,?,?,?,?)";
            query22 = "UPDATE pos_segment SET prog_num = ?, flux = ? WHERE idbranch = ? AND glon = ? AND glat = ?";
            st1 = conn.prepareStatement(query1);
            st12 = conn.prepareStatement(query12);
            st2 = conn.prepareStatement(query2);
            st22 = conn.prepareStatement(query22);

            long t1 = System.nanoTime();

            while((line = br.readLine()) != null) {
                String[] segment = line.split(cvsSplitBy);

                st1.setInt(1, Integer.parseInt(segment[1]));
                st1.setInt(2, Integer.parseInt(segment[0]));
                st1.setString(3, segment[2]);
                try {
                    st1.executeUpdate();
                } catch (SQLException e) {
                    st12.setInt(1,Integer.parseInt(segment[0]));
                    st12.setString(2, segment[2]);
                    st12.setInt(3, Integer.parseInt(segment[1]));
                    st12.executeUpdate();
                }

                st2.setInt(1,Integer.parseInt(segment[1]));
                st2.setInt(2, Integer.parseInt(segment[5]));
                st2.setFloat(3, Float.parseFloat(segment[3]));
                st2.setFloat(4, Float.parseFloat(segment[4]));
                st2.setDouble(5, Double.parseDouble(segment[6]));
                st2.executeUpdate();
            }
            long t2 = System.nanoTime();
            System.out.println(t2-t1/1000000000);
        }
        catch(IOException e) {
            System.out.println("Unexpected I/O exception.");

        }
    }

    public void fileImport(String path) {

        final String filaments_check = "IDFIL,NAME,TOTAL_FLUX,MEAN_DENS,MEAN_TEMP,ELLIPTICITY,CONTRAST,SATELLITE,INSTRUMENT";
        final String boundaries_check = "IDFIL,GLON_CONT,GLAT_CONT";
        final String stars_check = "IDSTAR,NAMESTAR,GLON_ST,GLAT_ST,FLUX_ST,TYPE_ST";
        final String skeletons_check = "IDFIL,IDBRANCH,TYPE,GLON_BR,GLAT_BR,N,FLUX";
        try {
            br = new BufferedReader(new FileReader(path));
            if ((line = br.readLine()) == null){
                System.out.println("Empty file!");
            }
            else {

                connect();

                switch (line) {
                    case filaments_check:
                        //clear("filaments");
                        importFilaments();
                        break;
                    case boundaries_check:
                        //clear("boundaries");
                        importBoundaries();
                        break;
                    case stars_check:
                        //clear("stars");
                        importStars();
                        break;
                    case skeletons_check:
                        //clear("skeletons");
                        //clear("pos_segment");
                        importSkeletons();
                        break;
                    default:
                        System.out.println("Not a data file compatible.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound: Couldn't locate" + path);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locate the database driver.");
        }
        finally {
            disconnect();
        }
    }
}