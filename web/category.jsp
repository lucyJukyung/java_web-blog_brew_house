<%--
  This page will be used for:
  ADD CATEGORY
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--import header from WEB-INF/main-->
<jsp:include page="header.jsp" />

<div align="center">
    <%
        //Add category form will appear when Add category is clicked
        if (request.getParameter("addCat") != null) { %>
    <form action="addCat" method="post">
        <h1>ADD CATEGORY</h1>
        <h3>Add New Category</h3>
        <input type="text" name="category" size="50" required />
        <input type = "submit" value="Save" />
    </form>
    <%
    }
    //Edit About Us form will appear when Edit About us is clicked
    else { %>
    <form action="editAbout" method="post">
        <h1>Edit About Us</h1>
        <h3>Edit About Us</h3>
        <textarea name="description" rows="50" cols="100" required ></textarea>
        <input type = "submit" value="Save" />
    </form>
    <%
        }
    %>
</div>

<!--import footer from WEB-INF/main-->
<jsp:include page="footer.jsp" />
