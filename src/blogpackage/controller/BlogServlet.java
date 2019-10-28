package blogpackage.controller;

import blogpackage.model.bean.*;
import blogpackage.model.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

@WebServlet(name = "BlogServlet", value = "/BlogServlet")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO catDAO;
    private BlogPostDAO postDAO;
    private AdminDAO adminDAO;
    private CommentDAO cmmtDAO;
    private AboutUsDAO aboutDAO;
    private static boolean isSession = false;

///BrewHouseBlog_war_exploded

    public BlogServlet(){
        adminDAO = new AdminDAO();
        catDAO = new CategoryDAO();
        postDAO = new BlogPostDAO();
        cmmtDAO = new CommentDAO();
        aboutDAO = new AboutUsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - Running doPost()");

        // instead of getting the ServletPath we will be
        // getting the parameter action that is passed through by the form
        String action = request.getParameter("action");
        System.out.println("Servlet - action passed is: " + action);

        // if no parameter is passed through to the servlet,
        // it displays the main.jsp with posts
        if (action == null) {
            System.out.println("running if statement for action = null");
            try {
                showVisiblePosts(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // endif action == null

        try {
            switch (action) {
                case "addCat":
                    insertCategory(request, response);
                    System.out.println("BlogServlet - switch: addCat executed");
                    break;

                /*//delete category added but not been used yet
                case "delCat":
                    delCategory(request, response);
                    System.out.println("BlogServlet - switch: delCat executed");*/

                    //edit about us
                case "editAbout":
                    editAboutUs(request, response);
                    System.out.println("BlogServlet - switch: editAbout executed");
                    break;

                //load about us page
                case "showAbout":
                    showAboutUs(request, response);
                    System.out.println("BlogServlet - switch: showAbout executed");
                    break;

                //load list of posts
                case "openPosts":
                    System.out.println("ive ran");
                    showVisiblePosts(request, response);
                    break;

                //load individual post
                case "post":
                    System.out.println("BlogServlet - switch: post() executed");
                    loadPost(request, response);
                    break;

                //button search on header
                case "search":
                    System.out.println("Servlet - Search()");
                    searchQuery(request, response);
                    break;

                case "login":
                    System.out.println("Servlet - login()");
                    userLogin(request, response);
                    break;

                //logout from admin console
                case "logout":
                    System.out.println("Servlet - logout()");
                    userLogout(request, response);
                    break;

                // delete post
                case "delete":
                    System.out.println("Servlet - delete()");
                        deletePost(request, response);
                    break;

                //show category added
                case "showCategories":
                    showCategory(request, response);
                    System.out.println("BlogServlet - switch: showCategory executed");
                    break;

                //add comment added
                case "addCmmt":
                    insertComment(request, response);
                    System.out.println("BlogServlet - switch: addCmmt executed");
                    break;

                //edit show category added
                case "editCat":
                    editShowCategory(request,response);
                    System.out.println("BlogServlet - switch: editCat executed");
                    break;

                //update category added
                case "updateCat":
                    updateCategory(request, response);
                    System.out.println("BlogServlet - switch: updateCat executed");
                    break;

                //delete comment added
                case "delCmmt":
                    delComment(request, response);
                    System.out.println("BlogServlet - switch: delCmmt executed");
                    break;

                //open edit about us added
                case "openEditAbout":
                    showEditAboutUs(request, response);
                    System.out.println("BlogServlet - switch: openEditAbout executed");
                    break;


                default:
                    System.out.println("running the default from Servlet - switch(action)");
                    showVisiblePosts(request, response);
                    break;
            } // switch end


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("running doGet");
        doPost(request, response);
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String ctitle = request.getParameter("category");
        Category c = new Category(ctitle.trim());
        catDAO.insertCategory(c);
        response.sendRedirect("/BlogServlet?action=showCategories");
    }

    private void showEditAboutUs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = 1;
        AboutUs showAbout = aboutDAO.showAboutUs(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category.jsp");
        request.setAttribute("aboutus", showAbout);
        dispatcher.forward(request, response);
    }

    //used AboutUsDAO, update this method
    private void editAboutUs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String desc = request.getParameter("aboutDesc");
        AboutUs a = new AboutUs(desc);
        aboutDAO.insertAboutUs(a);
        response.sendRedirect("/BlogServlet?action=showAbout");
    }

    //used AboutUsDAO, update this method
    private void showAboutUs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = 1;
        AboutUs showAbout = aboutDAO.showAboutUs(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("about.jsp");
        request.setAttribute("aboutus", showAbout);
        dispatcher.forward(request, response);
    }

    private void showPosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List <BlogPost> post = postDAO.selectAllPosts();
        request.setAttribute("showPost", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    //only returns visible posts
    private void showVisiblePosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List <BlogPost> post = postDAO.selectAllVisiblePosts();
        request.setAttribute("showPost", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    private void loadPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt((request.getParameter("id")));
        BlogPost existingPost = postDAO.selectPost(id);
        List<Comment> cmmt = cmmtDAO.selectAllComments(id);
        System.out.println("Comment excecuted: " + cmmt.size());
        request.setAttribute("displayPost", existingPost);
        request.setAttribute("displayComment", cmmt);
        RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
        System.out.println("BlogServlet - loadPost executed");
        dispatcher.forward(request, response);
    }

    //method that retrieves posts based on a user query
    private void searchQuery(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String userQuery = request.getParameter("query");
        System.out.println("Servlet The user search for: " + userQuery);
        List <BlogPost> fetchedPosts = postDAO.selectAllPostsWhere(userQuery);
        System.out.println("Servlet - searchQuery() fetchedPosts length: " + fetchedPosts.size());
        request.setAttribute("fetchedPosts", fetchedPosts);
        request.setAttribute("query", userQuery);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        dispatcher.forward(request, response);
        System.out.println("Servlet - end of searchQuery");
    }

    // method called when user tries to login on Admin Console
    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        System.out.println("Servlet - userLogin()");

        // store the parameter "username" that was passed through the form
        String username = request.getParameter("username");
        System.out.println("Servlet - username passed: " + username);

        // store the parameter "password" that was passed through the form
        String password = request.getParameter("password");
        System.out.println("Servlet - password passed: " + password);

        Admin userAdmin = new Admin();
        System.out.println("Servlet - userAdmin object created");

        userAdmin = adminDAO.checkCredentials(username, password);
        System.out.println("Servlet - calling the AdminDAO method");

        if (userAdmin.getAuthenticated()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            isSession = true;
        }

        if (isSession) {
            List <BlogPost> post = postDAO.selectAllPosts();
            request.setAttribute("showPost", post);
        }

        request.setAttribute("userAdmin", userAdmin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
        System.out.println("Servlet - end of userLogin");

    } // end of userLogin()


    // method called when user logout from admin console
    private void userLogout(HttpServletRequest request, HttpServletResponse response) throws  ServletException, SQLException, IOException {
        // this method retrieves the session from the user and removes it while redirecting to admin.jsp
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        response.sendRedirect("admin.jsp");
        isSession = false;
    }

    // method that delete posts from the admin console
    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, SQLException, IOException{
        System.out.println("Servlet - deletePost()");

        int postID = Integer.parseInt(request.getParameter("id"));
        boolean isPostDeleted = postDAO.deletePost(postID);
        System.out.println("Servlet - post: " + postID + " deleted? " + isPostDeleted);
        response.sendRedirect("BlogServlet?action=login");
    }

    // method to display categories
    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Category> cat = catDAO.selectCategory();
        request.setAttribute("showCategories", cat);
        System.out.println("BlogServlet - showCategory executed");
        RequestDispatcher dispatcher = request.getRequestDispatcher("category.jsp");
        dispatcher.forward(request, response);
    }

    //insertComment function added.
    private void insertComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String cOwner = request.getParameter("cmmtOwner");
        String cmmt = request.getParameter("cmmt");
        int pId = Integer.parseInt(request.getParameter("id"));
        Comment c = new Comment(cOwner.trim(), cmmt.trim(), pId);
        cmmtDAO.insertComment(c);
        System.out.println("BlogServlet - loadPost executed" + c);
        response.sendRedirect("/BlogServlet?action=post&id=" + pId);
    }

    //deleteComment function added
    private void delComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int pId = Integer.parseInt(request.getParameter("Pid"));
        cmmtDAO.deleteComment(id);
        System.out.println("deleted comment id = " + id + "post id = " + pId);
        response.sendRedirect("/BlogServlet?action=post&id=" + pId);
    }

    private void editShowCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category currentCat = catDAO.selectCategory(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BlogServlet?action=showCategories");
        request.setAttribute("currentCat", currentCat);
        dispatcher.forward(request, response);
        System.out.println("selected categoryId = " + id);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String Cname = request.getParameter("category").trim();
        Category c = new Category(id, Cname);
        catDAO.updateCategory(c);
        System.out.println("updated category Id = " + id);
        response.sendRedirect("/BlogServlet?action=showCategories");
    }

    /*//deleteCategory added but not been used yet
    private void delCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        catDAO.deleteCategory(id);
        System.out.println("deleted comment id = " + id);
        response.sendRedirect("/BlogServlet?action=showCategories");
    }*/
} //end servlet

