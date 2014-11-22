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
                    <h1>Registre</h1>
                    
                    <form role="form" method="post" action="registre.do" id="myForm">
                        <input type="hidden" name="form_action" value="form" />
                        <div class="form-group col-md-6 has-success">
                            <label for="name"> Nom:</label>
                            <input required="" name="firstname" type="text" class="form-control" id="firstNameForm" placeholder="Nom">
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="nomusuari">Nom usuari:</label>
                            <input required="" name="username" type="text" class="form-control" id="nomUsuariForm" placeholder="Nom Usuari">
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="secondname"> Cognoms:</label>
                            <input required="" name="lastname" type="text" class="form-control" id="lastNameForm" placeholder="Cognoms">
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="pwd">Contrasenya:    <span class="glyphicon glyphicon-question-sign"></span></label>
                            <input required="" name="password1" type="password" class="form-control" id="pwd1" placeholder="Contrasenya">
                            
                        </div>
                        <div class="form-group col-md-6 has-success">
                            <label for="email">E-mail:</label>
                            <input required="" name="email" type="email" class="form-control" id="emailForm" placeholder="Email">
                        </div>
                        
                        <div class="form-group col-md-6 has-success">
                            <label for="pwd"> Confirma la contrasenya:</label>
                            <input required="" name="password2" type="password" class="form-control" id="pwd2" placeholder="Contrasenya">
                        </div>
                        <%
                          
                          if(request.getAttribute("pass") != null) {  
                        %>
                         <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert">&times;</a>
                            <strong>ERROR!</strong> Contrasenya no coincideix.
                        </div>
                        <%
                          }
                        %>
                        <button type="submit" class="btn btn-info col-md-4 col-md-offset-7">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
