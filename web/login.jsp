<%-- 
    Document   : login
    Created on : 15-oct-2014, 17:54:44
    Author     : danie_000
--%>
<%@page import="cat.urv.deim.sob.Config"%>
<%@page import="cat.urv.deim.sob.model.User"%>
<%@ page session="true" %>
<%@page import="cat.urv.deim.sob.model.IUserDao"%>
<%@page import="cat.urv.deim.sob.model.UserDaoFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Loggin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
    </head>
    <body>
        <%
            IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
            User newUser = new User();
            boolean trobat = false;
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Config.COOKIE_USER)) {
                        userName = cookie.getValue();                      
                    }
                }
                if (userName != null) {
                    newUser.setUserName(userName);
                    User user = userDAO.findUserByName(newUser);
                    if (user.getId() !=-1) {
                        HttpSession userSession = request.getSession(true);
                        userSession.setAttribute(Config.ATTR_SERVLET_USER, user);
                        response.sendRedirect("http://localhost:8080/SOB/login.do?form_action=showUrl&page=0");
                    }
                }
            }
        %>
        <div class="container">
            <div class="row" >
                <div class="col-md-4 col-md-offset-4  colored border-login">
                    <h1>Escurçador d'URL</h1>
                    <form class="form-horizontal" role="form" method="post" action="login.do">
                        <input type="hidden" name="form_action" value="login" />
                        <div class="form-group" id="positionLogin">
                            <div class="col-sm-10">
                                <input required="" type="text" name="username" class="form-control" id="username" onblur="check_values()" onkeydown="check_values()" placeholder="Username">
                            </div>
                        </div>

                        <div class="form-group"  id="positionLogin">
                            <div class="col-sm-10">
                                <input required="" type="password" name="password" class="form-control" id="password" onblur="check_values()" onkeydown="check_values()" placeholder="Password">
                                <a class="col-md-12" href="rememberPass.jsp">Recordar contrasenya</a>
                            </div>                         
                        </div>
                        <div class="form-group" id="positionLogin">
                            <div class="col-sm-10">
                                <button type="submit" id="buttonSubmit" class="btn btn-default wide hidden"><span class="glyphicon glyphicon-ok med"></span></button>
                                <span id="lockSubmit" class="glyphicon glyphicon-lock medhidden redborder"></span>
                            </div>
                        </div>
                        <a href="registre.jsp" class="btn btn-info col-md-offset-7 col-md-3">Registra't</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

