<%--
  about us
  This page will be used for
  ABOUT US
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <h1>About Us</h1>
        <p>
            <c:forEach var="desc" items="${showDesc}">
                <c:out value="${desc.getDesc()}"/>
            </c:forEach>
        </p>
        <h3>Contact me</h3>
        <input type="text" name="name" placeholder="Name"/>
        <input type="email" name="emailAddr" placeholder="Email"/>
        <textarea rows="10" cols="100"></textarea>
        <input type="submit" name="contactSubmit" value="Submit"/>


        <!--import footer from footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>

</html>
