<%-- 
    Document   : addurl
    Created on : 12-nov-2014, 20:08:00
    Author     : KILIAN
--%>
<%@page import="cat.urv.deim.sob.Config"%>
<%@page import="cat.urv.deim.sob.model.User"%>
<%@ page session="true" %>
<%@page import="cat.urv.deim.sob.model.IUserDao"%>
<%@page import="cat.urv.deim.sob.model.UserDaoFactory"%>
<%@page import="cat.urv.deim.sob.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <%
            User u = new User();
            HttpSession userSession = request.getSession(false);
            String urlShort = (String) userSession.getAttribute(Config.ATTR_URL_URLSHORT);
        %>
        <div class="container">
            <div class="row" >
                <div class="col-md-4 col-md-offset-4  colored border-login">
                    <h2>URL</h2>
                    <form class="form-horizontal" role="form" method="post" action="addurl.do">
                        <input type="hidden" name="form_action" value="addurl" />
                        <div class="col-sm-10">                        
                            <input required="" type="text" name="url" class="form-control" id="url"> 
                            <button type="submit" class="btn btn-info col-md-offset-5 col-md-3">Cut</button>
                            <output name="x" for="a b"><%= urlShort%></output>
                            <div class="row">
                                <div class="col-md-6">
                                    <p>
                                        <a href="http://localhost:8080/SOB/index.jsp" class="btn btn-danger btn-block">Aceptar</a>
                                    </p>
                                </div>
                            </div>
                    </form>
                </div>                
            </div>
        </div>   
    </body>
</html>
