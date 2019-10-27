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

<body>
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>


<!-- Lucy temp buttons for testing -->

<!-- form action for edit category and about us-->

<form action="/BlogServlet" method="post">
    <input type="hidden" name="action" value="showCategories"/>
    <input type="submit" name="action" value="Add category"/>
</form>
<form action="/BlogServlet" method="post">
    <input type="hidden" name="action" value="openEditAbout"/>
    <input type="submit" name="about" value="Edit about us"/>
</form>

<!-- test button for show posts page-->
<form action="/BlogServlet" method="post">
    <input type="hidden" name="action" value="openPosts"/>
    <input type="submit" name="action" value="Show all Posts"/>
</form>
<!-- Lucy temp buttons for testing end-->


<!--import footer from footer.jsp-->
<jsp:include page="footer.jsp"/>

</body>
</html>
