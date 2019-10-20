package blogpackage.model.dao;

import  blogpackage.model.bean.Comment;
import java.sql.*;
import java.util.*;

public class CommentDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/EmployeeDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";

    // constructor
    public CommentDAO() {
    }

    // Creates a connection to the database
    protected Connection getConnection(){
        Connection connection = null;
        System.out.println("DAO CommentDAO - getConnection()"); //debugging

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
