package blogpackage.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import blogpackage.model.bean.*;
import blogpackage.model.dao.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "/", value = "/BrewHouse")
public class BlogServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //instace variables
    private BlogPostDAO bpostDAO;

    //constructor
    public void init(){
        System.out.println("Servlet - init()");
        bpostDAO = new BlogPostDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - running doPost()");

        String action = request.getServletPath();

        switch (action) {
            case "/home":
                System.out.println("Servlet - Switch - home");

        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - from doGet to doPost");
        doPost(request, response);
    }
}
