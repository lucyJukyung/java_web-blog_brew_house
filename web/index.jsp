<%--
  Created by IntelliJ IDEA.
  User: fernandobraga
  Date: 17/10/19
  Time: 22:44
  To change this template use File | Settings | File Templates.
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

    <body>
        <%--Header imported from header.jsp--%>
        <jsp:include page="header.jsp" />
        <%----%>

          <form action="/BlogServlet" method="post">
            <input type="hidden" name="action" value="test2">
            <input type="submit" value="run test">
          </form>


          <form action="/BlogServlet" method="post">
              <input type="hidden" name="action" value="test">
              <input type="submit" value="run test()">
          </form>

          <img src="images/flower.png">

        <%--footer imported from footer.jsp--%>
        <jsp:include page="footer.jsp" />
    </body>
</html>
