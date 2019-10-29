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

    <%-- importing javascript for modal --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#exampleModal').on('show.bs.modal', function (e) {
                var name = $('#contactName').val();
                /*var email = $('#contactEmail').val();
                var detail = $('#detail').val();*/
                //Pass Values
                $('#prntName').html(name);
                //$('#prntEmail').html(email);
                /*$("#contactSave").click(function(){
                    var name = $('#contactName').val();
                    var email = $('#contactEmail').val();
                    var detail = $('#detail').val();
                    var error = null;
                    if(name == ""){
                        error="Name is requried";
                        else{
                            $('#prntName').html(name);
                            if(email==""){
                                error="Email is requried";
                                else{
                                    $('#prntEmail').html(email);
                                    if(detail==""){
                                        error="Please type your message";
                                    }
                                }
                            }
                        }
                    }
                    if(error != null){
                        alert(error);
                    }else{
                        $('#exampleModal').modal('show');
                        return false;
                    }*/
                /* else{
                     //Pass Values
                     $('#prntName').html(name);
                     $('#prntEmail').html(email);
                 }*/
            });
        });
    </script>

    <%-- importing javascript for modal --%>

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

<body class="bg-light">
<%--Header imported from header.jsp--%>
<jsp:include page="header.jsp"/>
<%----%>

<div id="aboutMain" align="center">
    <%-- about us main picture --%>
    <img id="aboutPic" src="<%=request.getContextPath()%>/images/aboutUs.jpeg">

    <%-- about us main description contatiner --%>
    <div id="aboutContainer" class="card text-center bg-white">
        <div class="card-body" id="aboutus">
            <h1 class="card-title">About Us</h1>
            <p class="card-text">
                <c:if test="${aboutus != null}">
                    <c:out value="${aboutus.getDesc()}"/>
                </c:if>
            </p>
        </div>
        <%-- modal function added --%>
        <div id="contact">
            <h3 class="card-title">Contact me</h3>
            <form>
                <div class="form-row" id="contactDetail">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="Name" id="contactName" required>
                    </div>
                    <div class="col">
                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email" id="contactEmail"
                               required>
                    </div>
                </div>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" id="detail"
                          required></textarea>
                <button type="button" class="btn btn-primary btn-lg btn-block" id="contactSave" data-toggle="modal"
                        data-target="#exampleModal">Submit
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thank You!</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Thank you for reaching us <span id="prntName"></span>, we will get back to
                                    you <%-- displaying email not working <span id="prntEmail"></span>--%> as soon as we
                                    can!</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal"
                                        onclick="window.location='BlogServlet?action=showAbout';">Okay
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>


        <%-- modal function added finish --%>

    </div>
</div>

<!--import footer from footer.jsp-->
<jsp:include page="footer.jsp"/>
</body>

</html>