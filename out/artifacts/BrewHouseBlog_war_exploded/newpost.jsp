<%@ page import="blogpackage.model.dao.CategoryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">

    <!-- <script src="/js/bootstrap.min.js"></script> -->
    <script src="https://kit.fontawesome.com/b4abea9736.js" crossorigin="anonymous"></script>
    <%----%>

    <% /* import tiny mce */ %>
    <script type="text/javascript" src="js/tinymce/jquery.tinymce.min.js"></script>
    <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>

    <script>
        tinymce.init({
            forced_root_block : "", selector: 'textarea#default'
        });
    </script>
    <%----%>

    <%/*import JSTL*/%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%----%>

</head>
<body class="bg-light">
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp" />
<%----%>


    <div class="container mt-4">
        <div class="page-header">
            <h1 class="text-monospace">New Post</h1>
        </div>

        <form action="/BlogServlet" method="post" class="mt-3">

            <c:choose>
                <c:when test="${existingPost == null}">
                    <input type="hidden" name="action" value="insertPost">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="updatePost">
                    <input type="hidden" name="postID" value="<c:out value="${existingPost.getPostID()}"></c:out>">
                </c:otherwise>
            </c:choose>

            <div class="form-row">
                <!-- <input type="hidden" name="action" value="insertPost"> -->
                <div class="form-group col-md-6">
                    <label for="title">Post Title</label>
                    
                    <%--conditional for new post and edit post - TITLE--%>
                    <c:choose>
                        <c:when test="${existingPost != null}">
                            <input type="text" name="title" class="form-control" required value="<c:out value="${existingPost.getPostTitle()}"></c:out>">
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="title" class="form-control" placeholder="Title..." required>
                        </c:otherwise>
                    </c:choose>
                    <%-- END of conditional for new post and edit post - TITLE--%>
                </div>
            </div>

            <% /* load all categories from db to dropdown menu*/
                CategoryDAO catDAO = new CategoryDAO();
                request.setAttribute("AllCategories", catDAO.SelectAllCategories());
            %>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <% //input all categories into dropdown list %>
                    Select category
                    <label for="inputState">Select Category</label>
                    <select id="inputState" name="category" class="form-control">

                        <%-- Conditional for EDIT post--%>
                        <c:if test="${existingPost != null}">
                            <option selected value="${existingPost.getCategoryId()}">${existingPost.getCategoryTitle()}</option>
                        </c:if>
                        <%-- END Conditional for EDIT post--%>

                        <c:forEach var="tempCat" items="${AllCategories}">
                            <option value="${tempCat.categoryID}">${tempCat.categoryTitle}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="form-check">

                    <%--conditional for new post and edit post - POST VISIBILITY--%>
                    <c:choose>
                        <c:when test="${existingPost != null}">

                            <%--IF post is VISIBLE--%>
                            <c:choose>
                                <c:when test="${existingPost.isPostVisable()}">
                                    <input type="checkbox" name="ticked" value="checked" class="form-check-input"/>
                                </c:when>

                                <c:otherwise>
                                    <input type="checkbox" name="ticked" value="checked" class="form-check-input" checked/>
                                </c:otherwise>

                            </c:choose>

                        </c:when>

                        <c:otherwise>
                            <input type="checkbox" name="ticked" value="checked" class="form-check-input"/>
                        </c:otherwise>
                    </c:choose>
                    <%-- END conditional for new post and edit post - POST VISIBILITY--%>


                    <label class="form-check-label" for="gridCheck">
                        Make Post Invisible
                    </label>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-8">
                    <% /* use tinymce */ %>

                    <c:choose>
                        <c:when test="${existingPost != null}">
                            <textarea id="default" name="content">
                                <c:out value="${existingPost.getPostContent()}"></c:out>
                            </textarea>

                        </c:when>

                        <c:otherwise>
                            <textarea id="default" name="content"></textarea>
                        </c:otherwise>

                    </c:choose>

                    <%----%>
                </div>
            </div>

            <%--<input type="hidden" name="action" value="login">--%>
            <input type="submit" class="btn btn-primary" value="Post">
        </form>
    </div>





<%-- Isaac code starts here
<div class="container">
    <div class="page-header">
        <h1>Insert a new post</h1>
    </div>

    <form action="/BlogServlet" method="post">
        <input type="hidden" name="action" value="insertPost">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Post Title </label>
            <div class="col-sm-1">
                <input type="text" name="title" class="form-control" placeholder="title"/>
            </div>
        </div>

        <div class="row">
            <div class="col-1">
                Set post invisible <input type="checkbox" name="ticked" value="checked" />
            </div>


            <% /* load all categories from db to dropdown menu*/
                CategoryDAO catDAO = new CategoryDAO();
                request.setAttribute("AllCategories", catDAO.SelectAllCategories());
            %>

            <div class="col-8">
                <% //input all categories into dropdown list %>
                Select category
                <select name="category">
                    <c:forEach var="tempCat" items="${AllCategories}">
                        <option value="${tempCat.categoryID}">${tempCat.categoryTitle}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <% /* use tinymce */ %>
        <textarea id="default" name="content"></textarea>
        &lt;%&ndash;&ndash;%&gt;
        <input type="hidden" name="action" value="login">
        <input type="submit" value="Post">
    </form>
    &lt;%&ndash;footer imported from footer.jsp&ndash;%&gt;
</div>
--%>




<jsp:include page="footer.jsp" />
</body>
</html>