<%-- 
    Document   : deleteurl
    Created on : 17-nov-2014, 19:10:15
    Author     : KILIAN
--%>


<%@page import="cat.urv.deim.sob.model.Url"%>
<%@page import="java.util.Iterator"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoFactory"%>
<%@page import="java.util.Collection"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoImp"%>
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
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <!--
            Testing div URL container
        --> 

        <div class="row" id="divurl">
            <div class="col-md-offset-4 col-md-9 colored border-radius" >
                <table id="idtable">
                    <%
                        UrlDaoImp urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
                        HttpSession userSession = request.getSession(false);
                        User idUser = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
                        Collection urlCol = urlDao.showUrl(idUser.getId());
                        Iterator it = urlCol.iterator();
                        while (it.hasNext()) {
                            Url urlShow = (Url) it.next();
                            String url = urlShow.getUrl();
                            int nVisits = urlShow.getNumVisits();
                    %>
                    <tr>
                        <td>
                            <a class="center-block" href=""> <%= url%> </a>
                        </td>
                        <td>
                            <a href="" class="btn btn-info col-md-offset-5 buttondelete">Borra</a>
                        </td>
                    </tr>
                    <% }%>
                </table>    
            </div>
    </body>
</html>
