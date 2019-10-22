<%--
  main page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--import header from WEB-INF/main-->
<jsp:include page="header.jsp" />

<!--Lucy test show posts for testing-->
<h1>Main List</h1>
<img src="<%=request.getContextPath()%>/images/tasting.jpg">
<% System.out.println(request.getContextPath()+"tasting.jpg loaded");%>
<main role="main">

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <c:forEach var="post" items="${showPost}">
                    <div class="col-md-4" align="center">
                        <div class="card mb-4 box-shadow">
                            <p class="postCategory" style="text-decoration-line: underline; letter-spacing: 3px; margin-top: 10px;">
                                <c:out value="${post.getCategoryTitle()}" /> </p>

                            <a href="post?id=<c:out value='${post.getPostID()}'/>" class="stretched-link" style="color: inherit;">
                                <p class="postName" style="font-size: 40px"> <c:out value="${post.getPostTitle()}" /> </p>
                            </a>

                            <div class="card-body">
                                <p class="postContent"> <c:out value="${post.getPostContent()}" /> </p>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</main>
<!--Lucy test show posts for testing end-->

<!--import footer from WEB-INF/main-->
<jsp:include page="footer.jsp" />
