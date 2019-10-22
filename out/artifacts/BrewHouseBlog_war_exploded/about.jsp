<%--
  about us
  This page will be used for
  ABOUT US
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--import header from WEB-INF/main-->
<jsp:include page="header.jsp" />

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


<!--import footer from WEB-INF/main-->
<jsp:include page="footer.jsp" />
