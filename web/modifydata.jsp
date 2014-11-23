<%-- 
    Document   : modifyuser
    Created on : 17-nov-2014, 12:38:52
    Author     : KILIAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <%      HttpSession userSession = request.getSession(true);
            User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        %>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6 col-md-offset-3  colored border-radius">
                    <h2>Modifica</h2>
                    <table id="tmodify">
                        <tbody>
                            <tr>
                                <td class="camp">Nom: </td>
                                <td class="active"> <%= user.getFirstName() + " " + user.getLastName()%> </td>
                                <td class="success"> <a href="modifyname.jsp" class="btn btn-info col-md-offset-7">Modifica</a> </td>
                            </tr>

                            <tr>
                                <td class="camp">Nom user: </td>
                                <td class="active"> <%= user.getUserName()%> </td>
                                <td class="success"> <a href="modifyuser.jsp" class="btn btn-info col-md-offset-7 ">Modifica</a> </td>
                            </tr>


                            <tr>
                                <td class="camp">Password: </td>
                                <td class="active"> ***** </td>
                                <td class="success"> <a href="modifypw.jsp" class="btn btn-info col-md-offset-7">Modifica</a> </td>                              
                            </tr>
                            <tr>
                                <td class="camp">E-mail: </td>
                                <td class="active"> <%= user.getEmail()%> </td>
                                <td class="success"> <a href="modifymail.jsp" class="btn btn-info col-md-offset-7">Modifica</a> </td>                              
                            </tr>


                        </tbody>
                    </table>
                    <%if (request.getAttribute("nameUpdated") != null) {%>
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>PERFECTE!</strong> Nom i cognom actualitzat!.
                    </div>
                    <% } %>
                    <%if (request.getAttribute("emailUpdated") != null) {%>
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>PERFECTE!</strong> Correu actualitzat!.
                    </div>
                    <% } %>
                    <%if (request.getAttribute("passUpdated") != null) {%>
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>PERFECTE!</strong> Contranseya actualitzada!.
                    </div>
                    <% } %>
                    <%if (request.getAttribute("error") != null) {%>
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>ERROR!</strong> Ha hagut algun error al modificar la informació!.
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </body>
</html>
