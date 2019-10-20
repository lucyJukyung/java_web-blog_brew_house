package blogpackage.model.dao;

import blogpackage.model.bean.BlogPost;
import java.sql.*;
import java.util.*;

public class BlogPostDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/EmployeeDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";
}
