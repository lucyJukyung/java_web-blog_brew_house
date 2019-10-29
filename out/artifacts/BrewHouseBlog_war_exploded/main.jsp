<%--
  main page
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
<body class="bg-light">
    <%--Header imported from header.jsp--%>
    <jsp:include page="header.jsp" />
    <%----%>


    <%-- probably how I will use anchor tags
    <a href="CenterController?action=gohome">Go Home</a>
    --%>

    <div class="container mb-5">
        <div class="row mb-5"> <%--row 1--%>

            <div class="col-6 d-flex justify-content-start">
                <img class="" src="images/hero_beer.jpeg">
            </div>

            <div class="col-6 d-flex flex-column justify-content-center bg-white">
                <p class="h1 align-self-center text-monospace text-center">
                    BREW HOUSE
                </p>
                <p class="align-self-center text-center h5 pt-2 font-weight-light">
                    The luck is gone, the brain is shot, but the liquor we still got
                </p>
            </div>
        </div> <%--end row 1--%>

        <%--
        row 2 - blog posts
        --%>

        <div class="row">
            <c:forEach var="post" items="${showPost}">
                <%-- col 1, row 2--%>
                <div class="col-4 d-flex mb-4 justify-content-center">
                    <div class="card  mb-3" style="max-width: 18rem; min-width: 18rem;">
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

<jsp:include page="footer.jsp"/>

</body>
</html>
