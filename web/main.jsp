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

<body>
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>

<img src="<%=request.getContextPath()%>/images/flower.png">
<% String contextPath = request.getContextPath(); %>
<% System.out.println(contextPath);%>
<c:out value="${contextPath}"></c:out>


<!--Lucy test show posts for testing-->
<h1>Main List</h1>
<main role="main">
    <div class="album py-5 bg-light">
        <div class="container">
            <div id="postContainer" class="row">
                <c:forEach var="post" items="${showPost}">
                    <div class="card mb-4" align="center">
                        <div class="card mb-4 box-shadow">
                            <p class="postCategory"
                               style="text-decoration-line: underline; letter-spacing: 3px; margin-top: 10px;">
                                <c:out value="${post.getCategoryTitle()}"/>
                            </p>
                            <a href="BlogServlet?action=post&id=<c:out value="${post.getPostID()}" />" type="hidden"
                               value="post?id" style="color: inherit;">
                                <p class="postName" style="font-size: 40px"><c:out value="${post.getPostTitle()}"/></p>
                            </a>
                            <div class="card-body">
                                <p class="postContent"><c:out value="${post.getPostContent()}"/></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>


</body>
</html>
