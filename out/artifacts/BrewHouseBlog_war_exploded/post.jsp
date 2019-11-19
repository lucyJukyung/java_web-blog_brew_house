<%--
 display individual post
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
    <style>
        #postPic {
            display: block;
            width: 85%;
            margin: 20px auto;
        }

        #categoryDiv {
            margin: 50px;
        }

        #categoryTitle {
            display: inline;
            background: black;
            color: white;
            font-size: 30px;
            letter-spacing: 8px;
            padding: 0 10px;
        }

        #postContainerDiv {
            border-style: solid;
            border-color: grey;
            border-width: 1px;
            width: 85%;
            margin: auto auto 50px auto;
        }

        #personIcon {
            height: 50px;
            width: 50px;
        }

        #postAuthorDiv {
            margin: 20px 0 20px 30px;
            font-size: 14px;
        }

        #authorTimeDiv {
            margin-left: 15px;
        }

        #author{
            margin: 0;
        }
        #date{
            margin-top: 2px;
        }

        #postContentDiv {
            margin: 20px 0 20px 30px;
        }

        #commentBox {
            margin: 40px 0px;
        }

        #commentShowDiv {
            margin: 10px 60px 10px 35px;
            padding: 0 5px;
            vertical-align: middle;
            border-bottom: 1px dotted #ccc;
        }
        #commentShowDiv:hover{
            background-color: #EFF0F0;
        }

        #commentInputDiv {
            margin: 50px 0 20px 30px;
        }

        #cmmtOwner {
            width: 10%;
            margin-right: 10px;
            font-size: 15px;
            font-weight: bold;
        }

        #inputCmmtName {
            width: 10%;
            margin-right: 10px;
        }

        #comment {
            width: 76%;
            margin-left: 3px;
            font-size: 13px;
        }

        #inputCmmt, #comment {
            width: 76%;
            margin-right: 12px;
        }
        #delBtn{
            border: none;
            background-color: white;
        }
    </style>
</head>

<body class="bg-light">
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>
<div id="categoryDiv" align="center">
    <h2 id="categoryTitle" class="align-middle">
        <c:out value="${displayPost.getCategoryTitle()}"/></h2>
</div>


<%-- category name div --%>


<%-- post container div --%>
<div id="postContainerDiv" align="center" class="card">
    <img id="postPic" src="<%=request.getContextPath()%>/images/post02.jpg">

    <%-- author and posted time div --%>
    <div id="postAuthorDiv" class="row">
        <img id="personIcon" src="<%=request.getContextPath()%>/images/icons/person.png">

        <div id="authorTimeDiv">
            <p align="left" id="author"><c:out value="${displayPost.getPostAuthor()}"/></p>
            <p align="left" id="date"><c:out value="${displayPost.getPostDate()}"/></p>
        </div>
    </div>

    <%-- post content div --%>
    <div id="postContentDiv">
        <h2 align="left" style="font-weight: bold;"><c:out value="${displayPost.getPostTitle()}"/></h2>
        <article>${displayPost.getPostContent()}</article>
    </div>

    <%-- horizontal line for division with post and comment --%>
    <hr width="95%">

    <%-- comment show and input div --%>


    <div id="commentBox">

        <c:forEach var="comment" items="${displayComment}">
            <form action="/BlogServlet" method="post">
                <div id="commentShowDiv" class="row">
                    <input type="hidden" name="action" value="delCmmt"/>

                    <p id="cmmtOwner" align="left"><c:out value="${comment.getCommentOwner()}"/></p>
                    <p id="comment" align="left"><c:out value="${comment.getCommentContent()}"/></p>

                    <%-- comment delete button appears when admin logged in--%>
                    <c:if test="${sessionScope.username != null}">
                        <input type="hidden" name="id" value="<c:out value="${comment.getCommentID()}"/>"/>
                        <input type="hidden" name="Pid" value="<c:out value="${displayPost.getPostID()}"/>"/>
                        <input type="submit" class="close" aria-label="Close" value="&times;" id="delBtn"/>
                    </c:if>

                </div>
            </form>
        </c:forEach>

        <form action="/BlogServlet" method="post">
            <div id="commentInputDiv" class="row">
                <input type="hidden" name="action" value="addCmmt"/>

                <input type="hidden" name="id" value="<c:out value="${displayPost.getPostID()}"/>"/>
                <input type="text" class="form-control" placeholder="Name" id="inputCmmtName" name="cmmtOwner" required/>
                <input type="text" class="form-control" placeholder="Insert Comment" id="inputCmmt" name="cmmt" required/>

                <input type="submit" value="Comment" class="btn btn-primary btn-sm" />

            </div>
        </form>
    </div>
    </form>

</div>
<!--import footer from WEB-INF/main-->
<jsp:include page="footer.jsp"/>

</body>

</html>
