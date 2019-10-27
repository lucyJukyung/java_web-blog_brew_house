package blogpackage.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blogpackage.model.bean.BlogPost;


public class BlogPostDAO {
    //Define instance variables
    //--------------------------
    //      SQL QUERIES
    //--------------------------
    // retireve all posts from DB
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";
    //sql query modified
    private String SELECTALLPOSTS = "SELECT * FROM post p INNER JOIN category c ON p.categoryId = c.categoryId ORDER BY postId DESC;";
    //sql query modified
    private String SELECTPOST = "SELECT * FROM post p INNER JOIN category c ON p.categoryId = c.categoryId WHERE postId=?;";

    //constructor
    public BlogPostDAO() {
    }

    // Creates a connection to the database
    protected Connection getConnection() {
        Connection connection = null;
        System.out.println("BlogPostDAO - getConnection()");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //select post from user click
    public BlogPost selectPost(int Pid) {
        System.out.println("BlogPostDAO - selectPost");
        BlogPost post = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTPOST);
            preparedStatement.setInt(1, Pid);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                String postDate = rs.getString("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                Boolean isPostVisible = rs.getBoolean("postVisible");
                int categoryID = rs.getInt("categoryID");
                String categoryTitle = rs.getString("categoryTitle");

                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                post = new BlogPost(postID, postTitle, postDate, postAuthor, postContent, isPostVisible, categoryID, categoryTitle);

            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return post;
    }

    // retrieve all posts and store it on an array list <BlogPost>
    public List<BlogPost> selectAllPosts() {
        System.out.println("BlogPostDAO - selectAllPosts"); //debugging

        // create an List Array "blogPosts" to store objects of the type BlogPost (java bean)
        List<BlogPost> blogPosts = new ArrayList<>();

        //initialize DB variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
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
            while (rs.next()) {
                int postID = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                String postDate = rs.getString("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                Boolean isPostVisible = rs.getBoolean("postVisible");
                int categoryID = rs.getInt("categoryID");
                String categoryTitle = rs.getString("categoryTitle");

                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                blogPosts.add(new BlogPost(postID, postTitle, postDate, postAuthor, postContent, isPostVisible, categoryID, categoryTitle));

            } // end while

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        } //end try/catch/finally

        return blogPosts;

    } // end selectAllPosts


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

    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r) {
        System.out.println("BlogPostDAO Comment - closing connections");
        if (r != null) {
            try {
                r.close();
            } catch (Exception e) {
            }
            r = null;
        }
        if (p != null) {
            try {
                p.close();
            } catch (Exception e) {
            }
            p = null;
        }
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {
                c = null;
            }
        }
    }

}
