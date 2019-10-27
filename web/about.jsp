<%--
  about us
  This page will be used for
  ABOUT US
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
        #aboutMain {
            position: relative;
            width: 80%;
            margin: 50px;
        }

        #aboutPic {
            display: block;
            position: absolute;
            height: 700px;
            margin: 100px 0 70px 70px;
            z-index: 1;
        }

        #aboutContainer {
            position: absolute;
            left: 450px;
            background-color: #F8F8F8;
            border: none;
            margin: 50px 80px;
            width: 620px;
        }

        #aboutus, #contact {
            margin: 30px 50px;
        }

        #contactDetail {
            margin: 30px 0;
        }

        #contactSave {
            margin: 20px 0;
        }
    </style>
</head>

<body>
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>

<div id="aboutMain" align="center">
    <%-- about us main picture --%>
    <img id="aboutPic" src="<%=request.getContextPath()%>/images/aboutUs.jpeg">

    <%-- about us main description contatiner --%>
    <div id="aboutContainer" class="card text-center">
        <div class="card-body" id="aboutus">
            <h1 class="card-title">About Us</h1>
            <p class="card-text">
                <c:if test="${aboutus != null}">
                    <c:out value="${aboutus.getDesc()}"/>
                </c:if>
            </p>
        </div>

        <div id="contact">
            <h3 class="card-title">Contact me</h3>
            <form>
                <div class="form-row" id="contactDetail">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="Name" required>
                    </div>
                    <div class="col">
                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email" required>
                    </div>
                </div>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" required></textarea>
                <button type="button" class="btn btn-primary btn-lg btn-block" id="contactSave">Submit</button>
            </form>
        </div>

    </div>
</div>

<!--import footer from footer.jsp-->
<jsp:include page="footer.jsp"/>
</body>

</html>
