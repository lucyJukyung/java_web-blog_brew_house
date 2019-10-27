package blogpackage.controller;

import blogpackage.model.bean.AboutUs;
import blogpackage.model.bean.BlogPost;
import blogpackage.model.bean.Category;
import blogpackage.model.bean.Comment;
import blogpackage.model.dao.AboutUsDAO;
import blogpackage.model.dao.BlogPostDAO;
import blogpackage.model.dao.CategoryDAO;
import blogpackage.model.dao.CommentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

@WebServlet(name = "BlogServlet", value = "/BlogServlet")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO catDAO;
    private BlogPostDAO postDAO;
    private CommentDAO cmmtDAO;
    private AboutUsDAO aboutDAO;

    public BlogServlet() {
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

        try {
            switch (action) {
                //add category
                case "addCat":
                    insertCategory(request, response);
                    System.out.println("BlogServlet - switch: addCat executed");
                    break;

                //delete category added but not been used yet
                case "delCat":
                    delCategory(request, response);
                    System.out.println("BlogServlet - switch: delCat executed");

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
                    showPosts(request, response);
                    System.out.println("BlogServlet - switch: openPosts executed");
                    break;

                //load individual post
                case "post":
                    System.out.println("BlogServlet - switch: post executed");
                    loadPost(request, response);
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
                    break;
            }
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

    //used AboutUsDAO, update this method (newly added)
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

    private void showPosts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<BlogPost> post = postDAO.selectAllPosts();
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

    //deleteCategory added but not been used yet
    private void delCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        catDAO.deleteCategory(id);
        System.out.println("deleted comment id = " + id);
        response.sendRedirect("/BlogServlet?action=showCategories");
    }
} //end servlet

