package blogpackage.model.dao;

import blogpackage.model.bean.BlogPost;
import java.sql.*;
import java.util.*;

public class BlogPostDAO {

    //defining instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";

    // constructor
    public BlogPostDAO() {
    }

    //--------------------------
    //      SQL QUERIES
    //--------------------------
    // retireve all posts from DB
    private String SELECTALLPOSTS = "select * from post";



    // Creates a connection to the database
    protected Connection getConnection(){
        Connection connection = null;
        System.out.println("DAO BlogPostDAO- getConnection()"); //debugging

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


    // retrieve all posts and store it on an array list <BlogPost>
    public List <BlogPost> selectAllPosts(){
        System.out.println("BlogPostDAO - selectAllPosts"); //debugging

        // create an List Array "blogPosts" to store objects of the type BlogPost (java bean)
        List <BlogPost> blogPosts = new ArrayList<>();

        //initialize DB variables
        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet rs = null;

        //stablishing a connection
        try {
            // 1. step 1, connection
            connection = getConnection();

            // 2. step 2, set the prepared statement
            preparedStatement = connection.prepareStatement(SELECTALLPOSTS);
            System.out.println("BlogPostDAO - Running query: " + preparedStatement);

            // 3. step 3, execute the query using the prepared statement
            rs = preparedStatement.executeQuery();

            // 4. step 4, iterating through the ResultSet and adding values to the array
            while (rs.next()){
                int postID = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                Timestamp postDate = rs.getTimestamp("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                Boolean isPostVisible = rs.getBoolean("postVisible");
                int categoryID = rs.getInt("categoryID");

                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                blogPosts.add(new BlogPost(postID, postTitle, postDate, postAuthor, postContent, isPostVisible, categoryID));

            } // end while

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection,preparedStatement,rs);
        } //end try/catch/finally

        return blogPosts;

    } // end selectAllPosts








    //method to close DB connection
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r) {
        System.out.println("DAO BlogPostDAO - closing connections");
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
