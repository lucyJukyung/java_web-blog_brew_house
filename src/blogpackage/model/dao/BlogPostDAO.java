package blogpackage.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blogpackage.model.bean.BlogPost;

public class BlogPostDAO {
    //Define instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";
    private String SELECTALLPOSTS = "SELECT *, c.categoryTitle FROM post p INNER JOIN category c ON p.categoryId = c.categoryId ORDER BY postId DESC;";
    private String SELECTPOST = "SELECT *, c.categoryTitle FROM post p INNER JOIN category c ON p.categoryId = c.categoryId WHERE postId=?;";

    //constructor
    public BlogPostDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
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
    public BlogPost selectPost(int Pid){
        BlogPost post = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTPOST);
            preparedStatement.setInt(1, Pid);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String pTitle = rs.getString("postTitle");
                String pDate = rs.getString("postDate");
                String pAuthor = rs.getString("postAuthor");
                String pContent = rs.getString("postContent");
                int categoryId = rs.getInt("categoryId");
                String categoryTitle = rs.getString("categoryTitle");

                post = new BlogPost(Pid, pTitle,pDate,pAuthor,pContent,categoryId,categoryTitle);
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        finally {
            finallySQLException(connection,preparedStatement,rs);
        }
        return post;
    }

    //select all posts to list (Descending order)
    public List <BlogPost> selectAllPosts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List <BlogPost> posts = new ArrayList<>();

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTALLPOSTS);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                int postId = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                String postDate = rs.getString("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                int categoryId = rs.getInt("categoryId");
                String categoryTitle = rs.getString("categoryTitle");

                posts.add(new BlogPost(postId, postTitle, postDate, postAuthor, postContent, categoryId, categoryTitle));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
            finallySQLException(connection,preparedStatement,rs);
        }
        return posts;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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

    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
        if (r != null) {
            try {
                r.close();
            } catch (Exception e) {}
            r = null;
        }
        if (p != null) {
            try {
                p.close();
            } catch (Exception e) {}
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
