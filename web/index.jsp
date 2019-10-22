<%--
  Created by IntelliJ IDEA.
  User: fernandobraga
  Date: 17/10/19
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form action="/BlogServlet" method="post">
    <input type="hidden" name="action" value="test2">
    <input type="submit" value="run test">
  </form>


  <form action="/BlogServlet" method="post">
      <input type="hidden" name="action" value="test">
      <input type="submit" value="run test()">
  </form>

  <img src="images/flower.png">

  </body>
</html>
