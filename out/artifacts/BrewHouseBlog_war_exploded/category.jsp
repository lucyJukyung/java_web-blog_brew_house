<%--
  This page will be used for:
  ADD CATEGORY
  EDIT About us
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>Brew House Blog</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- <script src="/js/bootstrap.min.js"></script> -->
    <script src="https://kit.fontawesome.com/b4abea9736.js" crossorigin="anonymous"></script>

    <style>
        #editAboutBox, #addCatBox {
            width: 80%;
            margin: 50px 0;
        }

        #addTxt {
            width: 80%;
        }
        #saveCat #saveAbout, #cancelBtn{
            margin-left: 30px;
            width: 150px;
        }

        #saveAbout, #cancelBtn{
            margin-left: 30px;
            margin-top: 50px;
            width: 150px;

        }

        #showCategories {
            width: 95%;
        }
    </style>
</head>

<body>
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>
<div align="center">

    <%
        //Add category form will appear when Add category is clicked
        if (request.getParameter("action").equals("showCategories")) {
    %>
    <div class="card" id="addCatBox">
        <div class="card-header" align="left">
            Edit Category
        </div>
        <div class="card-body">
            <h5 class="card-title" align="left">Manage your Categories</h5>
            <p class="card-text" align="left">Add new category or delete category</p>

            <div class="form-group">

                <form action="/BlogServlet" method="post">
                    <div id="addTxtBox" align="left" class="form-inline">
                        <input type="hidden" name="action" value="addCat">
                        <input type="text" name="category" class="form-control" id="addTxt"
                               placeholder="Add New Category" align="left" required/>
                        <input type="submit" name="action" value="Save" class="btn btn-primary btn-lg" id="saveCat"/>
                    </div>
                </form>

                <div align="left">
                    <form action="/BlogServlet" method="post">
                        <div id="showCategories" align="left">
                            <ul class="list-group list-group-flush">
                                <c:forEach var="category" items="${showCategories}">
                                    <li class="list-group-item"><c:out value="${category.getCname()}"/>
                                            <%--
                                            <input type="hidden" name="action" value="delCat">
                                            <input type="hidden" name="id" value="<c:out value="${category.getCid()}"/>" />
                                            this can be a lot of work to figure out since it involves foreign key delettion.
                                            I will leave this commented since it is not in the requirement.
                                            <input type="submit" class="close" aria-label="Close" value="&times;" />
                                            --%>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <%
        }
        //Edit About Us form will appear when Edit About us is clicked
        if (request.getParameter("action").equals("openEditAbout")) { %>
    <form action="/BlogServlet" method="post">
        <div class="card" id="editAboutBox">
            <div class="card-header" align="left">
                Edit About Us
            </div>
            <div class="card-body">
                <h5 class="card-title" align="left">Edit your 'About Us' description</h5>

                <div class="form-group">
                    <input type="hidden" name="action" value="editAbout">
                    <c:if test="${aboutus != null}">
                        <input type="hidden" name="id" value="<c:out value='${aboutus.getDid()}' />"/>
                    </c:if>
                    <textarea class="form-control" id="exampleFormControlTextarea1" name="aboutDesc"
                              value="<c:out value="${aboutus.getDesc()}"/>" rows="10" required></textarea>

                    <button type="submit" class="btn btn-secondary btn-lg" id="cancelBtn" onclick="location.href='main.jsp';">Cancel</button>
                    <button type="submit" class="btn btn-primary btn-lg" value="Save" id="saveAbout">Save</button>

                </div>

            </div>
        </div>
        <%
            }
        %>
    </form>
</div>

<!--import footer from footer.jsp-->
<jsp:include page="footer.jsp"/>
</body>
</html>
