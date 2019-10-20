<%--
 display individual post
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--import header from WEB-INF/main-->
<jsp:include page="WEB-INF/main/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="category_name" align="center" style="margin: 50px;">
    <p style="display: inline; background: black; color: white; font-size: 30px; letter-spacing: 6px;">
        <c:out value="${displayPost.getCategoryTitle()}"/> </p>
</div>

<div style="background-color: azure; width:85%; height: 85%; margin: auto;" align="center">
<div class="author" align="left" style="margin: 20px 0 20px 20px; font-size: 14px;">
    <p> <c:out value="${displayPost.getPostAuthor()}"/> </p>
    <p> <c:out value="${displayPost.getPostDate()}"/> </p>
</div>
    <div class="content" style="margin: 20px 0 20px 20px;">
        <h2 align="left"> <c:out value="${displayPost.getPostTitle()}"/> </h2>
        <article> <c:out value="${displayPost.getPostContent()}"/> </article>
    </div>

</div>
<!--import footer from WEB-INF/main-->
<jsp:include page="WEB-INF/main/footer.jsp" />
