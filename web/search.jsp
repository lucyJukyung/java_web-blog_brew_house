<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Search Page
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

    <c:if test="${fetchedPosts.isEmpty()}">
        <div>
            <p class="h4 text-center mt-4 text-info">Sorry, there are no results for <span class="font-italic h3"><c:out value="${query}"></c:out></span> ... </p>
        </div>
    </c:if>

    <c:if test="${!fetchedPosts.isEmpty()}">
        <div>
            <p class="h4 text-center mt-4 text-info">Showing results for <span class="font-italic h3"><c:out value="${query}"></c:out></span> ... </p>
        </div>

        <div class="container mb-5 mt-5">
            <div class="row">
                <c:forEach var="post" items="${fetchedPosts}">
                    <%-- col 1, row 2--%>
                    <div class="col-4 d-flex justify-content-center">
                        <div class="card  mb-5" style="max-width: 18rem;">
                            <div class="card-header"><c:out value="${post.getCategoryTitle()}" /></div>
                            <div class="card-body text-secondary">
                                <h5 class="card-title">

                                    <a href="BlogServlet?action=post&id=<c:out value='${post.getPostID()}'/>">
                                        <c:out value="${post.getPostTitle()}" />
                                    </a>

                                </h5>
                                <p class="card-text"><c:out value="${post.getPostSummary()}"/>...</p>
                                <p class="">Date Posted: <c:out value="${post.getPostDate()}"/></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>


    </c:if>



    <%--Footer imported from footer.jsp--%>
    <jsp:include page="footer.jsp" />
    </body>
</html>
