package blogpackage.model.dao;

import blogpackage.model.bean.AboutUs;

import java.sql.*;
import java.util.*;

public class AboutUsDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";
    private String UPDATEABOUTSQL = "UPDATE aboutUs SET description=" + " (?) WHERE descId=1;";
    // private String SELECTDESCSQL = "SELECT * FROM aboutUs;";
    private String SELECTABOUTSQL = "SELECT descId, description FROM aboutUs WHERE descId=?;";

    //constructor
    public AboutUsDAO() {
    }

    // Creates a connection to the database
    protected Connection getConnection() {
        Connection connection = null;
        System.out.println("DAO AboutUsDAO - getConnection()"); //debugging

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    } // end getConnection


    //insert about us sql connection and statement
    public void insertAboutUs(AboutUs about) throws SQLException {
        System.out.println(UPDATEABOUTSQL);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // try-with-resource statement will auto close the connection.
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATEABOUTSQL);
            preparedStatement.setString(1, about.getDesc());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
    }

    public AboutUs showAboutUs(int Did) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AboutUs about = null;
        ResultSet rs = null;

        // try-with-resource statement will auto close the connection.
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTABOUTSQL);
            preparedStatement.setInt(1, Did);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int descId = rs.getInt("descId");
                String desc = rs.getString("description");
                about = new AboutUs(descId, desc);
            }

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }

        return about;
    }

   /* public List <AboutUs> selectAboutUs() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        List <AboutUs> about = new ArrayList<>();
        // using try-with-resources to avoid closing resources (boilerplate code)

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTDESCSQL);
            //statement will retrieve desc from descId=1
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                int descId = rs.getInt("descId");
                String desc = rs.getString("description");
                about.add(new AboutUs(descId, desc));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        finally {
            finallySQLException(connection,preparedStatement,rs);
        }
        return  about;
    }*/

    //catch sql message when error occurs
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException)
                        e).getSQLState());
                System.err.println("Error Code: " + ((SQLException)
                        e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    //method to close DB connection
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r) {
        System.out.println("DAO AboutUs - closing connections");
        if (r != null) {
            try {
                r.close();
            } catch (SQLException e) {
                r = null;
            }
        } // end if resultset is not null

        if (p != null) {
            try {
                p.close();
            } catch (SQLException e) {
                p = null;
            }
        } // end if prepared statement is not null

        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                c = null;
            }
        } // end if connection is not null

    } // end finallySQLException

}
