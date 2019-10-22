<%--
  main page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Brew House Blog</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">

</head>
<body>

<div class="jumbotron">
    <h1 class="display-4">Hello, pld!</h1>
    <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
    <hr class="my-4">
    <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
    <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
</div>

    <img src="<%=request.getContextPath()%>/images/flower.png">
    <% String contextPath = request.getContextPath(); %>
    <% System.out.println(contextPath);%>
    <c:out value="${contextPath}"></c:out>

</body>
</html>
