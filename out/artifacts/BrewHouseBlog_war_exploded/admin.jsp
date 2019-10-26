<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  admin console page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Brew House Blog</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
        <!-- <script src="/js/bootstrap.min.js"></script> -->
        <script src="https://kit.fontawesome.com/b4abea9736.js" crossorigin="anonymous"></script>
    </head>

    <body class="bg-light">
    <%--Header imported from header.jsp--%>
    <jsp:include page="header.jsp" />
    <%----%>

    <div class="text-center mt-5">

        <form class="w-25 mx-auto" action="/BlogServlet" method="POST">

                <span style="font-size: 13em;">
                  <i class="fas fa-beer ml-5"></i>
                </span>


            <h1 class="h3 mb-3 bt-5 font-weight-normal mb-4">Please sign in</h1>

            <label for="username" class="sr-only">
                Enter username
            </label>

            <input class="form-control" type="text" placeholder="Enter Username" name="username" required autofocus>

            <label for="password" class="sr-only">
                Password:
            </label>

            <input class="form-control" type="password" placeholder="Password" name="password" required>

            <br>

            <input type="hidden" name="action" value="login">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Login <i class="fas fa-sign-in-alt pl-2"></i></button>
        </form>

        <c:if test="${(userAdmin != null) && (userAdmin.getAdminID() == -1) }">
            <h1>Usuario invalido</h1>
        </c:if>

    </div>


















<%--    <!-- Lucy temp buttons for testing -->

    <!-- form action for edit category and about us-->
    <form name="editCatAbout" action="category.jsp" method="get">
        <h3> <input type="submit" name="addCat" value="Add category"/></a> </h3>
        <h3> <input type="submit" name="about" value="Edit about us"/></a> </h3>
    </form>

    <!-- test button for about us page-->
    <h3> <input type="submit" name="" value="About Us" onclick="window.location='showAbout'"/></h3>

    <!-- test button for show posts page-->

    <h3> <input type="submit"  name="action" value="Posts"/>
        <% System.out.println(request.getContextPath() + "posts button clicked from admin.jsp");%></h3>

    <form action="/BlogServlet" method="post">
        &lt;%&ndash;<input type="hidden" name="action" value="openPosts">&ndash;%&gt;
        <input type="submit" name="action" value="Show all Posts"/>
        <input type="submit" name="action" value="openPosts">
        <input type="submit" name="action" value="test">
    </form>

    <!-- Lucy temp buttons for testing end-->



    <!--import footer from footer.jsp-->
    <jsp:include page="footer.jsp" />

    </body>--%>
</html>
