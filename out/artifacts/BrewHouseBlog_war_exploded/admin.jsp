<%--
  admin console page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<<<<<< HEAD
=======
<<<<<<< HEAD
<html>
<head>
    <title>Brew House - Admin Console</title>
</head>
<body>

</body>
</html>
=======
>>>>>>> 3ab0b011279ea8df2f4064eae5ab574fde7b4d1b
<!--import header from WEB-INF/main-->
<jsp:include page="header.jsp" />



<!-- Lucy temp buttons for testing -->

<!-- form action for edit category and about us-->
<form name="editCatAbout" action="category.jsp" method="get">
    <h3> <input type="submit" name="addCat" value="Add category"/></a> </h3>
    <h3> <input type="submit" name="about" value="Edit about us"/></a> </h3>
</form>

<!-- test button for about us page-->
<h3> <input type="submit" name="" value="About Us" onclick="window.location='showAbout'"/></h3>

<!-- test button for show posts page-->
<h3> <input type="submit" name="" value="Posts" onclick="window.location='openPosts'"/>
    <% System.out.println(request.getContextPath() + "posts button clicked from admin.jsp");%></h3>

<!-- Lucy temp buttons for testing end-->



<!--import footer from WEB-INF/main-->
<jsp:include page="footer.jsp" />
<<<<<<< HEAD
=======
>>>>>>> origin/newLucy
>>>>>>> 3ab0b011279ea8df2f4064eae5ab574fde7b4d1b
