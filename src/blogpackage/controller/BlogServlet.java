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

@WebServlet(name = "BrewHouse", value = "/")
public class BlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - doPost()");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet - from doGet to doPost");
        doPost(request, response);
    }
}
