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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
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
            <div class="col-md-8 col-md-offset-2  colored border-login">
                <h2>URL</h2>
                <span class="glyphicon glyphicon-globe"></span>
                <%                    if (request.getAttribute("urlCorta") != null) {
                %>
                <form class="form-horizontal" role="form" method="post" action="addurl.do">
                    <input type="hidden" name="form_action" value="addurl" id="addurl" />
                    
                    <%
                    } else {
                    %>
                    <form class="form-horizontal" role="form" method="post" action="cut.do">
                        <input type="hidden" name="form_action" value="cutUrl" />
                        
                        <%
                            }
                        %>
                        <div class="col-md-12">                        
                            <table id ="tableurl">
                                <tr>
                                    <td id="inputurl">
                                        <%
                                            if (request.getAttribute("urlLarga") == null) {

                                        %>
                                        <input id="url" required="" type="url" name="url" class="form-control"> 
                                        <% } else {
                                            String urlLong = (String) request.getAttribute("urlLarga");%>
                                        <input value="<%=urlLong%>"  name="url" class="form-control">
                                        <% } %>
                                    </td>
                                    <td id="buttoncut">
                                        <button id="SubmitAddUrl" type="submit" class="btn btn-info" >Cut</button>
                                    </td>

                                </tr>
                            </table>
                                    
                                    
                            <div class="col-md-12" id="divurlshort">
                                <% if (request.getAttribute("urlCorta") != null) {
                                        String urlShort = (String) request.getAttribute("urlCorta");
                                %>
                                <h3> Cutted URL </h3> <input id="urlshort" readonly type="url" name="urlShort" class="form-control" value="<%= urlShort%>">
                                <%
                                    }
                                %>

                                <div id="shorturl" class="hide alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>ERROR!</strong><span>  Url < 26 caracters</span>
                                </div>

                                <%
                                    if (request.getAttribute("insertUrl") != null) {
                                %>
                                <div class="alert alert-success">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>PERFECTE!</strong> <%=request.getAttribute("insertUrl")%>!.
                                </div>
                                <%
                                    }
                                %>
                                <%
                                    if (request.getAttribute("repeatUrl") != null) {
                                %>
                                <div class="alert alert-warning">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>WARNING!</strong> <%=request.getAttribute("repeatUrl")%>!.
                                </div>
                                <%
                                    }
                                %> 
                        
                                <table id ="tableurl">
                                    <tr>
                                        <%
                                            if (request.getAttribute("urlCorta") != null) {
                                        %>

                                        <td>
                                            <a href="addurl.jsp" class="btn btn-danger">Cancel</a>
                                        </td>

                                        <td>
                                            <button type="submit" class="btn btn-success" id="submiturl">Submit</button>
                                        </td>
                                        <%
                                            }
                                        %>
                                    </tr>
                                </table>
                               
                            </div> 
                        </div>
                    </form>
            </div>                
        </div>
    </div>
</body>
</html>
