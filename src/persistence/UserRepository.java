//Methods to interact between user's database and the entity "User"
//Da finire e ricontrollare porca puttana

package persistence;

import entity.User;
import exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    PreparedStatement st = null;
    Connection conn = null;

    public boolean insertUser(User user) {
        try {
            if (!isPresent(user.getUsername())) {
                DataSource dataSource = new DataSource();
                conn = dataSource.getConnection();
                String query = "INSERT INTO users VALUES(?,?,?,?,?,?)";
                st = conn.prepareStatement(query);
                st.setString(1, user.getUsername());
                st.setString(2, user.getPassword());
                st.setString(3, user.getName());
                st.setString(4, user.getSurname());
                st.setString(5, user.getEmail());
                st.setBoolean(6,user.isAdmin());
                st.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch(ClassNotFoundException e) {
            System.out.println("Couldn't locate the database driver.");
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean deleteUser(String user, String password) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            if (isPresent(user,password)) {
                DataSource dataSource = new DataSource();
                conn = dataSource.getConnection();
                String query = "DELETE FROM users WHERE username = ? AND password = ?";
                st = conn.prepareStatement(query);
                st.setString(1, user);
                st.setString(2, password);
                st.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isPresent(String username) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DataSource dataSource = new DataSource();
            conn = dataSource.getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            st = conn.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isPresent(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DataSource dataSource = new DataSource();
            conn = dataSource.getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
          System.out.println("Couldn't locate the database driver.");
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean LogIn(String username, String password) throws UserNotFoundException {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DataSource dataSource = new DataSource();
            conn = dataSource.getConnection();
            String query = "SELECT admin " +
                    "FROM users " +
                    "WHERE username = ? AND password = ?";
            st = conn.prepareStatement(query);
            st.setString(1,username);
            st.setString(2,password);
            st.executeQuery();

            if(rs.next()) {
                return rs.getBoolean(1);
            } else {
                throw new UserNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't locale the database driver.");
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean signIn(User user) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DataSource dataSource = new DataSource();
            conn = dataSource.getConnection();

            if(!isPresent(user.getUsername(),user.getPassword())) {
                String query = "INSERT INTO users VALUES(?,?,?,?,?,?)";
                st = conn.prepareStatement(query);
                st.setString(1,user.getUsername());
                st.setString(2,user.getPassword());
                st.setString(3,user.getName());
                st.setString(4,user.getSurname());
                st.setString(5,user.getEmail());
                st.setBoolean(6,user.isAdmin());
                st.executeUpdate();
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}