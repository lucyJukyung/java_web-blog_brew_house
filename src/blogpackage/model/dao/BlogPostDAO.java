package blogpackage.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blogpackage.model.bean.BlogPost;

    //defining instance variables
public class BlogPostDAO {
    //Define instance variables
    //--------------------------
    //      SQL QUERIES
    //--------------------------
    // retireve all posts from DB
    private String DBURL = "jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Sydney";
    private String DBUsername = "root";
    private String DBPassword = "mysql123";

    //I've changed the query so the date is captured in the right format
    private String SELECTALLPOSTS = "SELECT postId, postTitle, date_format(postDate, \"%d-%m-%y\") as postDate, postAuthor, postContent, postVisible, p.categoryID, c.categoryTitle FROM post p INNER JOIN category c ON p.categoryId = c.categoryId ORDER BY postId DESC;";
    private String SELECTALLVISIBLEPOSTS = "SELECT postId, postTitle, date_format(postDate, \"%d-%m-%y\") as postDate, postAuthor, postContent, postVisible, p.categoryID, c.categoryTitle " +
            "FROM post p INNER JOIN category c ON p.categoryId = c.categoryId " +
            "WHERE postVisible = 1 " +
            "ORDER BY postId DESC;";

    private String SELECTPOST = "SELECT *, c.categoryTitle FROM post p INNER JOIN category c ON p.categoryId = c.categoryId WHERE postId=?;";

    //Search query
    private String SELECTALLPOSTSWHERE = "SELECT postId, postTitle, date_format(postDate, \"%d-%m-%y\") as postDate, postAuthor, postContent, postVisible, p.categoryID, c.categoryTitle " +
            "FROM post p INNER JOIN category c ON p.categoryId = c.categoryId " +
            "WHERE postTitle LIKE ? " +
            "OR postContent LIKE ? " +
            "AND postVisible = 1 " +
            "ORDER BY postId DESC " +
            "LIMIT 10;";

    //Delete post query
    private String DELETPOST = "Delete from post where postid = ?";


    //Update Post Query
    private String UPDATEPOST = "UPDATE post set postTitle = ?, postDate = ?, postAuthor = ?, postContent = ?, postVisible = ?, categoryId = ? " +
            "where postId = ?;";


    // Creates a connection to the database
    protected Connection getConnection() {
        Connection connection = null;
        System.out.println("BlogPostDAO - getConnection()");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                //System.out.println("post content: " + postContent);
                String[] subString = postContent.split("\\.", 0);//creating regular expression(regex) is by \\
                //\\. means split string at dot(.).
                String postSummary = subString[0];


                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                blogPosts.add(new BlogPost(postID,postTitle,postDate,postAuthor,postContent,isPostVisible,categoryID,categoryTitle, postSummary));


            } // end while

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection,preparedStatement,rs);
        } //end try/catch/finally

        return blogPosts;

    } // end selectAllPosts


    // retrieve all posts and store it on an array list <BlogPost>
    public List <BlogPost> selectAllVisiblePosts(){
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
            preparedStatement = connection.prepareStatement(SELECTALLVISIBLEPOSTS);
            System.out.println("BlogPostDAO - Running query: " + preparedStatement);

            // 3. step 3, execute the query using the prepared statement
            rs = preparedStatement.executeQuery();

            // 4. step 4, iterating through the ResultSet and adding values to the array
            while (rs.next()){
                int postID = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                String postDate = rs.getString("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                Boolean isPostVisible = rs.getBoolean("postVisible");
                int categoryID = rs.getInt("categoryID");
                String categoryTitle = rs.getString("categoryTitle");
                //System.out.println("post content: " + postContent);
                String[] subString = postContent.split("\\.", 0);
                String postSummary = subString[0];


                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                blogPosts.add(new BlogPost(postID,postTitle,postDate,postAuthor,postContent,isPostVisible,categoryID,categoryTitle, postSummary));


            } // end while

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        } //end try/catch/finally

        return blogPosts;

    } // end selectAllPosts


    // retrieve all posts and store it on an array list <BlogPost>
    public List <BlogPost> selectAllPostsWhere(String userQuery){
        System.out.println("BlogPostDAO - selectAllPostsWhere()"); //debugging

        // create an List Array "blogPosts" to store objects of the type BlogPost (java bean)
        List <BlogPost> blogPosts = new ArrayList<>();
        System.out.println("BlogPostDAO - blogPosts arraylist created");

        //initialize DB variables
        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet rs = null;
        System.out.println("BlogPostDAO - connection prepStatement and rs created");

        //stablishing a connection
        try {
            // 1. step 1, connection
            connection = getConnection();
            System.out.println("BlogPostDAO - getConnection()");

            // 2. step 2, set the prepared statement
            preparedStatement = connection.prepareStatement(SELECTALLPOSTSWHERE);
            System.out.println("BlogPostDAO - Query SELECTALLPOSTSWHERE called");

            preparedStatement.setString(1, "%" + userQuery + "%");
            System.out.println("BlogPostDAO - adding first ?");

            preparedStatement.setString(2, "%" + userQuery + "%");
            System.out.println("BlogPostDAO - Running query: " + preparedStatement);

            // 3. step 3, execute the query using the prepared statement
            rs = preparedStatement.executeQuery();

            // 4. step 4, iterating through the ResultSet and adding values to the array
            while (rs.next()){
                int postID = rs.getInt("postId");
                String postTitle = rs.getString("postTitle");
                String postDate = rs.getString("postDate");
                String postAuthor = rs.getString("postAuthor");
                String postContent = rs.getString("postContent");
                Boolean isPostVisible = rs.getBoolean("postVisible");
                int categoryID = rs.getInt("categoryID");
                String categoryTitle = rs.getString("categoryTitle");
                //System.out.println("post content: " + postContent);
                String[] subString = postContent.split("\\.", 0);
                String postSummary = subString[0];


                // create an new object of type BlogPost and adds it to the list array <BlogPosts>
                blogPosts.add(new BlogPost(postID,postTitle,postDate,postAuthor,postContent,isPostVisible,categoryID,categoryTitle, postSummary));


            } // end while

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection,preparedStatement,rs);
        } //end try/catch/finally

        return blogPosts;

    } // end selectAllPostsWhere


    public boolean deletePost(int postid) throws SQLException{
        System.out.println("BlogPostDAO - deletePost()");

        boolean isPostDeleted = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETPOST);
            preparedStatement.setInt(1, postid);

            System.out.println("DAO - executing query: " + preparedStatement);
            isPostDeleted = preparedStatement.executeUpdate() > 0 ? true:false; //returns true if a post is deleted, otherwise, false


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection, preparedStatement, null);
        } // end try-catch

        return isPostDeleted;

    } // end delete post

    public void InsertPost(BlogPost blogPost) {
        System.out.println("Inserting Post");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO post(postTitle, postDate, postAuthor, postContent,  postVisible, categoryId) VALUES (?,?,?,?,?,?);";
        //TODO write test to test insert and delete

        try {
            System.out.println("connecting to DB - insertPost");
            connection = getConnection(); // connect to db
            System.out.println("inserting data into prepared statment");
            preparedStatement = connection.prepareStatement(sql);

            /*ID - automatically created by mySQL*/
            preparedStatement.setString(1, blogPost.getPostTitle()); 	/*title*/ System.out.println("inserted Title : "+ blogPost.getPostTitle());
            preparedStatement.setString(2, (blogPost.getPostDate()));	/*Date*/	System.out.println("inserted date " + blogPost.getPostDate());
            preparedStatement.setString(3, blogPost.getPostAuthor());	/*Author*/	System.out.println("inserted Author" + blogPost.getPostAuthor());
            preparedStatement.setString(4, blogPost.getPostContent()); /*Content*/ System.out.println("inserted Content" + blogPost.getPostContent());
            preparedStatement.setBoolean(5, blogPost.getPostVisible()); /*Visible*/ System.out.println("inserted visabiltiy " + blogPost.getPostVisible());
            preparedStatement.setInt(6, blogPost.category.getCategoryID());		/*categoryID*/ System.out.println("inserted cat ID" + blogPost.category.getCategoryID());

            //execute the command
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            finallySQLException(connection, preparedStatement, resultSet);
            e.printStackTrace();
        }

    } // end InsertPost


    // method called to update a post
    public boolean updatePost(BlogPost modifiedPost) throws SQLException{

        System.out.println("BlogPostDAO - updatePost()");

        boolean isPostUpdated = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        /*"UPDATE post set postTitle = ?, postDate = ?, postAuthor = ?, postContent = ?, postVisible = ?, categoryId = ?" +
                "where postId = ?;";*/
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATEPOST);

            preparedStatement.setString(1, modifiedPost.getPostTitle());
            preparedStatement.setString(2, modifiedPost.getPostDate());
            preparedStatement.setString(3, modifiedPost.getPostAuthor());
            preparedStatement.setString(4, modifiedPost.getPostContent());
            preparedStatement.setBoolean(5, modifiedPost.getPostVisible());
            preparedStatement.setInt(6,  modifiedPost.getCategoryId());
            preparedStatement.setInt(7, modifiedPost.getPostID());

            System.out.println("BlogPostDAO - executing query: " +preparedStatement);
            isPostUpdated = preparedStatement.executeUpdate() > 0 ? true:false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallySQLException(connection,preparedStatement,null);
        } // end try-catch

        return isPostUpdated;


    } // end updatePost
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
