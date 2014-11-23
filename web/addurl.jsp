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
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <%            User u = new User();
            HttpSession userSession = request.getSession(false);
            

        %>
        <div class="row" >
            <div class="col-md-4 col-md-offset-4  colored border-login">
                <h2>URL</h2>
                <span class="glyphicon glyphicon-globe"></span>
                <%                    if (request.getAttribute("urlCorta") != null) {
                %>
                <form class="form-horizontal" role="form" method="post" action="addurl.do">
                    <input type="hidden" name="form_action" value="addurl" />
                    <%
                    } else {
                    %>
                    <form class="form-horizontal" role="form" method="post" action="cut.do">
                        <input type="hidden" name="form_action" value="cutUrl" />
                        <%
                            }
                        %>
                        <div class="col-md-10">                        
                            <table id ="tableurl">
                                <tr>
                                    <td id="inputurl">
                                        <%
                                            if (request.getAttribute("urlLarga") == null) {
                                                
                                        %>
                                        <input required="" placeholder="Url a escurçar" type="url" name="url" class="form-control"> 
                                        <% } else {
                                        String urlLong = (String) request.getAttribute("urlLarga");%>
                                        <input value="<%=urlLong%>"  name="url" class="form-control">
                                        <% } %>
                                    </td>
                                    <td id="buttoncut">
                                        <button type="submit" class="btn btn-info" >Cutter</button>
                                    </td>

                                </tr>
                            </table>
                            <% if (request.getAttribute("urlCorta") != null) {
                                String urlShort = (String) request.getAttribute("urlCorta");
                            %>
                            <input id="tableurl" type="url" name="urlShort" class="form-control" value="<%= urlShort%>">
                            <%
                                }
                            %>
                            <%
                                if (request.getAttribute("lengthUrl") != null) {
                            %>
                            <div class="alert alert-danger">
                                <a href="#" class="close" data-dismiss="alert">&times;</a>
                                <strong>ERROR!</strong> URL massa curta!.
                            </div>
                            <%
                                }
                            %>
                            <%
                                if (request.getAttribute("insertUrl") != null) {
                            %>
                            <div class="alert alert-success">
                                <a href="#" class="close" data-dismiss="alert">&times;</a>
                                <strong>PERFECTE!</strong> URL introduida!.
                            </div>
                            <%
                                }
                            %>
                            <div class="row">
                                <div class="col-md-6">
                                    <table id ="tableurl">
                                        <tr>
                                            <%
                                                if (request.getAttribute("urlCorta") != null) {
                                            %>
                                            <td>
                                                <button type="submit" class="btn btn-success">Confirmar</button>
                                            </td>
                                            <%
                                                }
                                            %>
                                            <td>
                                                <a href="addurl.jsp" class="btn btn-danger btn-block">Cancelar</a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
            </div>                
        </div>
    </div>
</body>
</html>
