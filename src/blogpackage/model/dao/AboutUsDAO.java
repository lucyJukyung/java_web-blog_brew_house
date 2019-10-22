package blogpackage.model.dao;

import blogpackage.model.bean.AboutUs;
import java.sql.*;
import java.util.*;

public class AboutUsDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";

    //constructor
    public AboutUsDAO() {
    }

    // Creates a connection to the database
    protected Connection getConnection(){
        Connection connection = null;
        System.out.println("DAO AboutUsDAO - getConnection()"); //debugging

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return connection;
    } // end getConnection


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
