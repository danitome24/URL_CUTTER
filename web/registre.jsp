<%-- 
    Document   : registre
    Created on : 15-oct-2014, 17:55:44
    Author     : danie_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registre</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
    </head>
    <body>
        <div class="container" >
            <div class="row" style="margin-top:10%">
                <div class="col-md-6 col-md-offset-3  colored border-radius">
                    <h1>Register</h1>

                    <form role="form" method="post" action="registre.do" id="myForm" onsubmit="return checkForm(this);">
                        <input type="hidden" name="form_action" value="form" />
                        <div class="form-group col-md-6 has-success">
                            <label for="name"> Name:</label>
                            <input required="" name="firstname" type="text" class="form-control" id="firstNameForm" >
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="nomusuari">User Name:</label>
                            <input required="" name="username" type="text" class="form-control" id="nomUsuariForm" >
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="secondname"> Last Name:</label>
                            <input required="" name="lastname" type="text" class="form-control" id="lastNameForm" >
                        </div>
                        <div class="form-group col-md-6 has-success" >
                            <label for="pwd">Password:<span class="glyphicon glyphicon-question-sign" data-placement="right" title="Composta per: minúscules, majúscules, números i major de 6" data-toggle="tooltip"></span></label>       
                            <input required="" name="password" type="password" class="form-control" id="pwd1" >

                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="email">E-mail:</label>
                            <input required="" name="email" type="email" class="form-control" id="emailForm" >
                        </div>

                        <div class="form-group col-md-6 has-success">
                            <label for="pwd"> Confirm Password:</label>
                            <input required="" name="password2" type="password" class="form-control" id="pwd2" >
                        </div>
                        <%if (request.getAttribute("user") != null) {%>
                        <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert">&times;</a>
                            <strong>ERROR!</strong> <%=request.getAttribute("user")%>
                        </div>
                        <% } %>
                        <%if (request.getAttribute("password") != null) {%>
                        <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert">&times;</a>
                            <strong>ERROR!</strong> <%=request.getAttribute("password")%>
                        </div>
                        <% }%>
                        <button type="submit" class="btn btn-info col-md-4 col-md-offset-7" >Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
