<%--
  Created by IntelliJ IDEA.
  User: lucy
  Date: 20/10/19
  Time: 3:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Brew House Header -->
<nav class="navbar navbar-expand-lg navbar-light bg-white ">
    <span class="navbar-brand" href="#">
        <i class="fas fa-beer fa-3x pl-2 align-middle"></i>
        <span class="pl-5 text-monospace">
                    Brew House
        </span>
    </span>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto text-left">
            <li class="nav-item ml-5 ">
                <a class="nav-link ml-2" href="BlogServlet">HOME</a>
            </li>

            <li class="nav-item">
                <a class="nav-link ml-5" href="about.jsp">ABOUT US</a>
            </li>

            <li class="nav-item">
                <a class="nav-link ml-5" href="admin.jsp">ADMIN CONSOLE</a>
            </li>

        </ul>

        <form class="form-inline" action="/BlogServlet" method="POST">
            <input class="form-control mr-sm-2" name="query" type="search" placeholder="Search..." required>
            <input type="hidden" name="action" value="search">
            <button class="btn btn-outline-dark my-2 my-sm-0" type="submit"><i class="fas fa-search"></i></button>
        </form>

    </div>
</nav>
