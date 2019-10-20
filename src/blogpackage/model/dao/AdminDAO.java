package blogpackage.model.dao;

import blogpackage.model.bean.Admin;
import java.sql.*;
import java.util.*;

public class AdminDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/EmployeeDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";

    // constructor
    public AdminDAO() {
    }

    // Creates a connection to the database
    protected Connection getConnection(){
        Connection connection = null;
        System.out.println("DAO AdminDAO- getConnection()"); //debugging

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
}
