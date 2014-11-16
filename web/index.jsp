<%-- 
    Document   : index
    Created on : 16-oct-2014, 17:30:18
    Author     : danie_000
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="cat.urv.deim.sob.model.Url"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoImp"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoFactory"%>
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
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <!--
            Testing div URL container
        --> 
        <div class="row" id="divurl">
            <a type="button" class="btn btn-info" id="buttonaddurl" href="addurl.jsp">Add</a>
            <div class="col-md-offset-4 col-md-8 colored border-radius" >
                <table id="idtable">
                    <%
                        UrlDaoImp urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
                        HttpSession userSession = request.getSession(false);
                        User idUser = (User)userSession.getAttribute(Config.ATTR_SERVLET_USER);
                        Collection urlCol = urlDao.showUrl(idUser.getId());
                        Iterator it = urlCol.iterator();
                        while (it.hasNext()) {
                            Url urlShow = (Url)it.next();
                            String url = urlShow.getUrl();
                            int nVisits = urlShow.getNumVisits();
                    %>
                    <tr>
                        <td>
                            <strong><%= url%></strong>
                        </td>
                        <td>
                            <strong><%= nVisits%></strong>
                        </td>
                    </tr>
                    <% }%>
                </table>    
            </div>
        </div>
    </body>
</html>
