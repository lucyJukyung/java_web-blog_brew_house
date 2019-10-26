package blogpackage.controller;

import blogpackage.model.bean.AboutUs;
import blogpackage.model.bean.Admin;
import blogpackage.model.bean.BlogPost;
import blogpackage.model.bean.Category;
import blogpackage.model.dao.AdminDAO;
import blogpackage.model.dao.BlogPostDAO;
import blogpackage.model.dao.CategoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BlogServlet", value = "/BlogServlet")
public class BlogServlet extends HttpServlet {
    private  static final long serialVersionUID =1L;
    private CategoryDAO catDAO;
    private BlogPostDAO postDAO;
    private AdminDAO adminDAO;
    private static boolean isSession = false;

///BrewHouseBlog_war_exploded

    public BlogServlet(){
        adminDAO = new AdminDAO();
        catDAO = new CategoryDAO();
        postDAO = new BlogPostDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - Running doPost()");

        //String action = request.getServletPath();

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
                //add category
                case "/addCat":
                    insertCategory(request, response);
                    break;

                //edit about us
                case "/editAbout":
                    editAboutUs(request,response);
                    break;

                //load about us page
                case "/showAbout":
                    showAboutUs(request, response);
                    break;

                //load list of posts
                case "openPosts":
                    System.out.println("ive ran");
                    showVisiblePosts(request, response);
                    break;

                //load individual post
                case "post":
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

                case "logout":
                    System.out.println("Servlet - logout()");
                    userLogout(request, response);
                    break;

                default:
                    System.out.println("running the default from Servlet - switch(action)");
                    showVisiblePosts(request, response);
                    break;
            } // switch end


        } catch (SQLException ex) {
            throw new ServletException(ex);
        } // end try
    } // end doPost

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("running doGet");
        doPost(request, response);
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String ctitle = request.getParameter("category");
        Category c = new Category(ctitle);
        catDAO.insertCategory(c);
        response.sendRedirect("main.jsp");
    }

    private void editAboutUs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String desc = request.getParameter("description");
        AboutUs a = new AboutUs(desc);
        catDAO.insertAboutUs(a);
        response.sendRedirect("showAbout");
    }

    private void showAboutUs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List <AboutUs> about = catDAO.selectAboutUs();
        request.setAttribute("showDesc", about);
        RequestDispatcher dispatcher = request.getRequestDispatcher("about.jsp");
        dispatcher.forward(request, response);
    }

    private void showPosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List <BlogPost> post = postDAO.selectAllPosts();
        request.setAttribute("showPost", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    private void showVisiblePosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List <BlogPost> post = postDAO.selectAllVisiblePosts();
        request.setAttribute("showPost", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    private void loadPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int id = Integer.parseInt((request.getParameter("id")));
        BlogPost existingPost = postDAO.selectPost(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
        request.setAttribute("displayPost", existingPost);
        dispatcher.forward(request, response);
    }

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
    private void userLogout(HttpServletRequest request, HttpServletResponse response) throws  ServletException, ServletException, IOException {
        // this method retrieves the session from the user and removes it while redirecting to admin.jsp
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        response.sendRedirect("admin.jsp");
        isSession = false;
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{

    }

} //end servlet
