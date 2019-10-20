<%--
  admin console page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--import header from WEB-INF/main-->
<jsp:include page="WEB-INF/main/header.jsp" />



<!-- Lucy temp buttons for testing -->

<!-- form action for edit category and about us-->
<form name="editCatAbout" action="category.jsp" method="get">
    <h3> <input type="submit" name="addCat" value="Add category"/></a> </h3>
    <h3> <input type="submit" name="about" value="Edit about us"/></a> </h3>
</form>

<!-- test button for about us page-->
<h3> <input type="submit" name="" value="About Us" onclick="window.location='showAbout'"/></h3>

<!-- test button for show posts page-->
<h3> <input type="submit" name="" value="Posts" onclick="window.location='openPosts'"/></h3>

<!-- Lucy temp buttons for testing end-->



<!--import footer from WEB-INF/main-->
<jsp:include page="WEB-INF/main/footer.jsp" />
